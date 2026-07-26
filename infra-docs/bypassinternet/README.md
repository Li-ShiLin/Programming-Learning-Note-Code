本文档记录在自有云服务器（如 AWS EC2）上搭建网络代理的步骤

---

1.AWS安全组

```bash
# AWS 安全组
# 在 EC2 控制台的安全组中，添加入站规则
# 自定义 TCP，端口 443（或选用的端口），来源 0.0.0.0/0
# SSH 端口 22 建议限制来源 IP 或仅的 IP，增强安全性。
```

2.方案：Xray + VLESS + XTLS（推荐，高效隐蔽）

```bash
# 1. 连接服务器，更新系统
# ubuntu@<EC2 内部主机名>:~$ sudo apt update && sudo apt upgrade -y
ssh -i your-key.pem ubuntu@<EC2公网IP>
sudo apt update && sudo apt upgrade -y



# 2. 安装 Xray
# 使用官方脚本一键安装（需 root 权限）
# sudo su -
# bash -c "$(curl -L https://github.com/XTLS/Xray-install/raw/main/install-release.sh)" @ install
root@<EC2 内部主机名>:~# bash -c "$(curl -L https://github.com/XTLS/Xray-install/raw/main/install-release.sh)" @ install

# 安装后会自动生成基础配置，可先查看
# cat /usr/local/etc/xray/config.json
root@ip-173-33-11-203:~# cat /usr/local/etc/xray/config.json
{}


# 3.生成UUID和密钥
# 安全保存生成的UUID、Private key和Public key
# 生成UUID
xray uuid
# 生成公私钥对
xray x25519
```

3.配置Xray服务端

```bash
# 4.配置Xray服务端
# 编辑配置文件
# vi /usr/local/etc/xray/config.json
# 将其内容替换为如下配置，并将id、privateKey、shortIds替换为上一步生成的值
```

```bash
sudo tee /usr/local/etc/xray/config.json > /dev/null <<EOF
{
  "log": { "loglevel": "warning" },
  "inbounds": [
    {
      "listen": "0.0.0.0",
      "port": 443,
      "protocol": "vless",
      "settings": {
        "clients": [
          {
            "id": "你的UUID", // 请替换
            "flow": "xtls-rprx-vision"
          }
        ],
        "decryption": "none"
      },
      "streamSettings": {
        "network": "tcp",
        "security": "reality",
        "realitySettings": {
          "dest": "www.microsoft.com:443", // 伪装目标网站
          "serverNames": [ "www.microsoft.com" ],
          "privateKey": "你的Private_Key", // 请替换
          "shortIds": [ "" ]
        }
      },
      "sniffing": { "enabled": true, "destOverride": ["http", "tls"] }
    }
  ],
  "outbounds": [ { "protocol": "freedom", "tag": "direct" } ]
}
EOF
```

4.设置防火墙与系统优化

```bash
# 开启BBR拥塞控制
echo "net.core.default_qdisc=fq" >> /etc/sysctl.conf
echo "net.ipv4.tcp_congestion_control=bbr" >> /etc/sysctl.conf
sysctl -p
```

5.启动Xray服务

```bash
systemctl enable xray
systemctl restart xray
systemctl status xray
```

6.配置客户端并连接Chrome

```bash
# 前提：在本地电脑下载Xray客户端核心（如Xray-windows-64或带GUI的v2rayN）
# 强烈建议Win11系统选择 v2rayN 这个图形化客户端
# Xray-windows-64下载地址：https://github.com/XTLS/Xray-core/releases
# v2rayN下载地址：https://github.com/2dust/v2rayN
# 配置：在客户端创建配置文件config.json，填入关键信息
```

```bash
# 方案一：使用 v2rayN 客户端（强推）
# v2rayN版本选择：
# 前往官方发布页，下载名为 v2rayN-With-Core.zip 的压缩包。这个已内置Xray核心，无需单独下载。
# 下载：点击 v2rayN-windows-64.zip（约 144 MB），下载后解压到本地文件夹。
# 安装：v2rayN是一个绿色软件，解压后双击 v2rayN.exe 即可运行。
# 运行：进入解压后的文件夹，找到 v2rayN.exe，双击启动。
# 打开v2rayN，点击顶部菜单栏的 服务器，选择 添加[VLESS]服务器。
# 在弹出的窗口中，根据你之前在服务端准备好的一一对应填入信息：
# 地址 (Address)： AWS EC2 公网 IP。
# 端口 (Port)：你服务端配置的端口，例如 443。
# 用户ID (id)：你通过 xray uuid 命令生成的 UUID。
# 流控 (Flow)：选择 xtls-rprx-vision。
# 传输 (Network)：选择 tcp。
# 安全 (Security)：选择 reality。
# 伪装域名 (serverName)：填入你服务端 dest 字段中的网站，如 www.microsoft.com。
# 公钥 (PublicKey)：填入你通过 xray x25519 生成的 Public Key。
# ShortId：填入你服务端 shortIds 字段设置的值，如未设置可留空。
# 点击“确定”保存。
# 启用代理
# 右键点击v2rayN任务栏图标，选择 系统代理 -> 自动配置系统代理。v2rayN会自动设置系统代理并应用预设的“绕过大陆”路由规则，实现智能分流。
```

```bash
# 方案二：使用 Xray-core 命令行（进阶）
# 如果你偏好命令行或需要脚本化操作，可以选择此方案。
# 下载与解压
# 版本选择：打开Xray-core官方仓库，下载 Xray-windows-64.zip 文件。
# 解压：解压后进入文件夹，只需保留 xray.exe 主程序和 geoip.dat, geosite.dat 两个数据库文件 (不删除任何文件完全可以，此建议仅仅是为了文件精简和整洁)
# 创建配置文件
# 在 xray.exe 同目录下，新建名为 config.json 的文件，用 UTF-8 编码保存。
# 写入客户端配置内容，核心字段修改示范如下（注意：务必填上真实信息）：

# 客户端 config.json 关键配置
{
  "inbounds": [
    {
      "port": 10808,
      "protocol": "socks",
      "settings": { "udp": true }
    }
  ],
  "outbounds": [
    {
      "protocol": "vless",
      "settings": {
        "vnext": [
          {
            "address": "你的EC2公网IP",
            "port": 443,
            "users": [
              {
                "id": "你的UUID",
                "flow": "xtls-rprx-vision",
                "encryption": "none"
              }
            ]
          }
        ]
      },
      "streamSettings": {
        "network": "tcp",
        "security": "reality",
        "realitySettings": {
          "serverName": "www.microsoft.com",
          "publicKey": "你的Public_Key(公钥)",
          "shortId": ""
        }
      },
      "tag": "proxy"
    }
  ]
}

# 命令行启动
# 在文件夹地址栏输入 powershell 或 cmd 打开终端，执行以下命令启动：
# .\xray.exe -c config.json
# 保持此窗口不能关闭，它就是代理客户端。
# 客户端启动后，本地10808端口就处于SOCKS5代理的监听状态了。

# 启动
# 运行xray -c config.json启动客户端。
# 最后，在Chrome的SwitchyOmega插件中，配置SOCKS5代理服务器指向127.0.0.1，端口为10808即可
```

7.Chrome配置和使用

```bash
# 配置浏览器使用代理
# 方案一：通过命令行启动 Chrome 浏览器，并强制其所有网络流量都通过本地的 SOCKS5 代理转发
"C:\Program Files\Google\Chrome\Application\chrome.exe" --proxy-server="socks5://localhost:10808"

# 快速验证浏览器代理是否生效
# 最直接的方法：用命令行启动一个干净的 Chrome 实例，强制所有流量走 SOCKS5 代理。
# 先把现在所有 Chrome 窗口关掉，然后按 Win+R 输入：
"C:\Program Files\Google\Chrome\Application\chrome.exe" --proxy-server="socks5://localhost:10808" --user-data-dir="%TEMP%\chrome-proxy-test"
# 或者
"C:\Program Files\Google\Chrome\Application\chrome.exe" --proxy-server="socks5://localhost:10808" --user-data-dir="D:\chrome-proxy-profile"

# 这种方式的优缺点
# 优点：零扩展依赖，立即生效，适合临时使用。
# 缺点：整个 Chrome 的所有流量都走代理，访问国内网站也会绕道香港，速度慢且浪费流量，而且访问部分国内服务可能触发区域限制。
# 如果希望像 SwitchyOmega 那样智能分流（国内直连，国外走代理），就需要更精细的方案，下面是几个推荐的替代方法。




# 方案二：Chrome 配置：SwitchyOmega
# Chrome 配置：SwitchyOmega
# 安装 Chrome 扩展 Proxy SwitchyOmega。
# 打开选项，新建情景模式 -> “代理服务器”，输入：
# 代理协议：SOCKS5
# 代理服务器：127.0.0.1
# 代理端口：10808
# 切换到刚创建的 profile，你即可通过 Chrome 科学上网。
#（进阶）可配合 Auto Switch 规则，定义哪些域名走代理，哪些直连，使用 GFWList 或自己编写规则
```





