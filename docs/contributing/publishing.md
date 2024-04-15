# 发布

该插件使用GitHub操作来发布构建。以下说明是维护者的参考。

## 发布发行版

请按照以下步骤发布新版本。创建GitHub发行版后，将触发GITHUB操作，该操作将构建插件并将其推入插件存储库。

1. 使用新版本号和更改注释更新ChangElog.md文件。
1. 将所有更改推向主。
1. 转到[发布页面](https://github.com/one-dark/jetbrains-one-dark-theme/releases)
1. 单击“草稿新版本”按钮。
1. 输入标签和发行名称的新版本号（例如`v1.0.0`）。
1. 将更改注释从ChangElog添加到描述。
1. 单击“发布版本”。

## 创建个人访问令牌

请按照以下步骤创建个人访问令牌。应将其添加到名为“ Publish_Token”的GitHub秘密中。

1. 登录[Jetbrains Hub](https://hub.jetbrains.com)
1. 转到您的用户个人资料
1. 单击**身份验证**选项卡
1. 单击**新令牌... **
1. 输入任何名称
1. 选择**市场**范围
