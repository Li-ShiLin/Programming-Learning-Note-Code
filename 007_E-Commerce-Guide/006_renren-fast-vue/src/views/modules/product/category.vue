<template>
  <div>
    <el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽">
    </el-switch>
    <el-button v-if="draggable" @click="batchSave">批量保存拖拽</el-button>
    <el-button type="danger" @click="batchDelete">批量删除</el-button>
    <el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId" :default-expanded-keys="expandedKey" :draggable="draggable" :allow-drop="allowDrop" @node-drop="handleDrop" ref="menuTree">
      <!-- span标签内容 -->
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <!-- 非叶子节点才能显示append按钮 -->
          <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => append(data)">
            Append
          </el-button>

          <!-- 所有节点都要显示edit按钮 -->
          <el-button type="text" size="mini" @click="edit(data)">
            edit
          </el-button>

          <!-- 只有叶子节点(没有子节点)才能显示delete -->
          <el-button v-if="node.childNodes.length == 0" type="text" size="mini" @click="() => remove(node, data)">
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog :title="title" :visible.sync="dialogVisible" width="30%" :close-on-click-modal="false">
      <!-- 将表单与三级分类实体类category绑定 -->
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <!-- 点击对话框后执行addCategory()函数新增三级分类元素 -->
        <el-button type="primary" @click="submitData">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  data() {
    return {
      // 批量保存过后要展开的菜单id
      pCid: [],
      // 是否开启拖拽
      draggable: false,
      // 拖拽时所有要要修改的节点信息
      updateNodes: [],
      // 节点的最大层级，默认为0
      maxLevel: 0,
      // 对话框被新增和修改复用，用title动态绑定对话框的标题
      title: "",
      // 点击修改时设dialogType为edit,点击新增时设dialogType为add,
      dialogType: "",
      // 和表单绑定的三级分类实体类category
      category: {
        name: "",
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        productUnit: "",
        icon: "",
        catId: null,
      },
      // 对话框属性：对话框是否可见
      dialogVisible: false,
      menus: [],
      expandedKey: [],
      defaultProps: {
        // children对应子节点的属性值，此处是CategoryEntity的children属性
        children: "children",
        // label对应要显示的字段名，此处为CategoryEntity的name属性（分类名称）
        label: "name",
      },
    };
  },
  methods: {
    batchDelete() {
      let catIds = [];
      // 获取所有被选中的元素
      let checkedNodes = this.$refs.menuTree.getCheckedNodes();
      console.log("被选中的元素", checkedNodes);
      for (let i = 0; i < checkedNodes.length; i++) {
        catIds.push(checkedNodes[i].catId);
      }
      this.$confirm(`是否批量删除【${catIds}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 点击确认以后发送删除请求
          this.$http({
            url: this.$http.adornUrl("/product/category/delete"),
            method: "post",
            data: this.$http.adornData(catIds, false),
          }).then(({ data }) => {
            this.$message({
              message: "菜单批量删除成功",
              type: "success",
            });
            this.getMenus();
          });
        })
        .catch(() => {
          // 点了取消后执行
        });
    },

    allowDrop(draggingNode, dropNode, type) {
      console.log("allowDrop", draggingNode, dropNode, type);

      // 被拖动的当前节点的所有子节点的最大的层级 + 目标父节点层数 <= 3
      // 求出被拖动的当前节点的最大深度：用countNodeLevel方法更新最大深度
      this.countNodeLevel(draggingNode);
      // 此处的deep理解为被拖拽节点距其叶子节点的最大距离
      let deep = Math.abs(this.maxLevel - draggingNode.level) + 1;
      console.log("深度：", deep);
      if (type == "inner") {
        return deep + dropNode.level <= 3;
      } else {
        return deep + dropNode.parent.level <= 3;
      }
    },

    //计算深度时，用当前数据，而不是数据库中的数据。因为可能还没来得及保存到数据库
    countNodeLevel(node) {
      // 找到所有子节点，求出最大深度
      if (node.childNodes != null && node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          if (node.childNodes[i].level > this.maxLevel) {
            this.maxLevel = node.childNodes[i].level;
          }
          this.countNodeLevel(node.childNodes[i]);
        }
      }
    },

    // 拖拽成功后执行此函数：
    handleDrop(draggingNode, dropNode, dropType, ev) {
      console.log("handleDrop: ", draggingNode, dropNode, dropType);
      //1、当前节点最新父节点的id
      let pCid = 0;
      //拖拽后的兄弟节点，分两种情况，一种是拖拽到两侧，一种是拖拽到内部
      let sibings = null;
      if (dropType == "before" || dropType == "after") {
        pCid =
          dropNode.parent.data.catId == undefined
            ? 0
            : dropNode.parent.data.catId;
        sibings = dropNode.parent.childNodes;
      } else {
        pCid = dropNode.data.catId;
        sibings = dropNode.childNodes;
      }
      this.pCid.push(pCid);
      //2、当前拖拽节点的最新顺序
      //遍历所有的兄弟节点，如果是拖拽节点，传入（catId，sort，parentCid，catLevel），如果是兄弟节点传入（catId，sort）
      for (let i = 0; i < sibings.length; i++) {
        if (sibings[i].data.catId == draggingNode.data.catId) {
          //如果遍历的是当前正在拖拽的节点
          let catLevel = draggingNode.level;
          if (sibings[i].level != draggingNode.level) {
            //当前节点的层级发生变化
            catLevel = sibings[i].level;
            //修改他子节点的层级
            this.updateChildNodeLevel(sibings[i]);
          }
          this.updateNodes.push({
            catId: sibings[i].data.catId,
            sort: i,
            parentCid: pCid,
            catLevel: catLevel,
          });
        } else {
          this.updateNodes.push({ catId: sibings[i].data.catId, sort: i });
        }
      }

      // 当前拖拽节点的最新层级信息
      console.log("updateNodes", this.updateNodes);

      // 发送请求修改拖拽后的层级信息
    },

    // 修改拖拽节点的子节点的层级
    updateChildNodeLevel(node) {
      if (node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          //遍历子节点，传入（catId，catLevel）
          var cNode = node.childNodes[i].data;
          this.updateNodes.push({
            catId: cNode.catId,
            catLevel: node.childNodes[i].level,
          });
          // 处理子节点的子节点
          this.updateChildNodeLevel(node.childNodes[i]);
        }
      }
    },

    // 批量保存拖拽后的信息
    batchSave() {
      // 发送请求修改拖拽后的层级信息
      this.$http({
        url: this.$http.adornUrl("/product/category/update/sort"),
        method: "post",
        data: this.$http.adornData(this.updateNodes, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单顺序修改成功",
          type: "success",
        });
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单：拖拽到的目的地位置的父节点要默认展开
        this.expandedKey = this.pCid;
        //每次拖拽后把数据清空，否则要修改的节点将会越拖越多
        this.updateNodes = [];
        this.maxLevel = 0;
        // this.pCid = 0;
      });
    },
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功获取到菜单数据...", data.data);
        this.menus = data.data;
      });
    },

    // append方法，调用addCategory添加三级分类
    append(data) {
      console.log("append", data);
      this.dialogType = "add";
      this.title = "添加分类";
      this.dialogVisible = true;
      this.category.parentCid = data.catId;
      this.category.catLevel = data.catLevel * 1 + 1;
      this.category.catId = null;
      this.category.name = "";
      this.category.icon = "";
      this.category.productUnit = "";
      this.category.sort = 0;
      this.category.showStatus = 1;
    },

    // 添加三级分类
    addCategory() {
      console.log("提交的三级分类数据", this.category);
      this.$http({
        url: this.$http.adornUrl("/product/category/save"),
        method: "post",
        data: this.$http.adornData(this.category, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单保存成功",
          type: "success",
        });
        // 关闭对话框
        this.dialogVisible = false;
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      });
    },

    // 修改三级分类数据
    editCategory() {
      // 将要修改的字段解构出来发送给后端，不修改的字段不发
      var { catId, name, icon, productUnit } = this.category;
      //   var data = {
      //     catId: catId,
      //     name: name,
      //     icon: icon,
      //     productUnit: productUnit,
      //   };
      this.$http({
        url: this.$http.adornUrl("/product/category/update"),
        method: "post",
        data: this.$http.adornData({ catId, name, icon, productUnit }, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单保存成功",
          type: "success",
        });
        // 关闭对话框
        this.dialogVisible = false;
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      });
    },

    // edit方法,参数data为当前节点的数据
    edit(data) {
      console.log("要修改的数据", data);
      this.dialogType = "edit";
      this.title = "修改分类";
      this.dialogVisible = true;

      // 回显数据（此种回显方法拿到的可能不是最新数据）
      //   this.category.name = data.name;
      //   this.category.catId = data.catId;

      // 回显数据: 发送请求获取当前节点最新的数据,回显最新数据
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: "get",
      }).then(({ data }) => {
        // 请求成功
        console.log("要回显的数据", data);
        this.category.name = data.data.name;
        this.category.catId = data.data.catId;
        this.category.icon = data.data.icon;
        this.category.productUnit = data.data.productUnit;
        this.category.parentCid = data.data.parentCid;
        this.category.catLevel = data.data.catLevel;
        this.category.sort = data.data.sort;
        this.category.showStatus = data.data.showStatus;
      });
    },

    submitData() {
      if (this.dialogType == "add") {
        this.addCategory();
      }
      if (this.dialogType == "edit") {
        this.editCategory();
      }
    },

    // remove 方法
    remove(node, data) {
      console.log("remove", node, data);
      var ids = [data.catId];
      this.$confirm(`是否删除【${data.name}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl("/product/category/delete"),
          method: "post",
          data: this.$http.adornData(ids, false),
        })
          .then(({ data }) => {
            console.log("删除成功");
            // 删除成功后要重新请求获取menus菜单，否则点击删除后不能立即看到菜单元素被删除
            this.$message({
              message: "菜单删除成功",
              type: "success",
            });
            this.getMenus();
            // 设置需要默认展开的菜单，删除当前节点后，让其父节点依然保持展开
            this.expandedKey = [node.parent.data.catId];
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          });
      });
    },
  },
  //声明周期 - 创建完成（可以访问当前this实例）
  created() {
    // 一创建就像后端发送请求获取数据
    this.getMenus();
  },
  //声明周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>