<template>
  <div>
    <el-tree
      :data="menus"
      :props="defaultProps"
      :expand-on-click-node="false"
      show-checkbox
      node-key="catId"
      :default-expanded-keys="expandedKey"
    >
      <!-- span标签内容 -->
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <!-- 非叶子节点才能显示append按钮 -->
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)"
          >
            Append
          </el-button>

          <!-- 所有节点都要显示edit按钮 -->
          <el-button type="text" size="mini" @click="edit(data)">
            edit
          </el-button>

          <!-- 只有叶子节点(没有子节点)才能显示delete -->
          <el-button
            v-if="node.childNodes.length == 0"
            type="text"
            size="mini"
            @click="() => remove(node, data)"
          >
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="30%"
      :close-on-click-modal="false"
    >
      <!-- 将表单与三级分类实体类category绑定 -->
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input
            v-model="category.productUnit"
            autocomplete="off"
          ></el-input>
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
      if ((this.dialogType == "add")) {
        this.addCategory();
      }
      if ((this.dialogType == "edit")) {
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