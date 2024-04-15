# 开发

## 主题

一个暗色主题类似于其他JetBrains平台的[自定义UI主题](https://blog.jetbrains.com/idea/2019/03/brighten-up-your-day-add-color-to-to-intellij-idellij-idea)

编辑器配色方案是一个静态文件，可以修改并在此处找到：`buildsrc/templates/oneDark.template.json`。

构建插件或“ ./gradlew runide”任务运行时，将构建4个编辑器配色方案。

该主题的设置可以在[configuration目录]（<https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-directories-used-by-by-by-the-the-ple-the-the-the-to-store-spore-settorestings）中找到-Caches-Plugins> and Logs）
在路径`<configdir>/options/one_dark_config.xml`或开发`build/indure-sandbox/config/option/options/one_dark_theme.xml`时。

模板更换结构：

- `%bold$theme^comments%`将始终具有`bold`属性，因此，只有用户指定斜体属性：`Inalic`或`bold_italic`才是斜体。
- `theme^attribute`将将属性设置为用户为`属性`组配置的任何内容。
- `$ purple $`将用相关颜色代替该值。

## 调色板

该主题旨在创建一种配色方案，该配色方案在各种语言中尽可能一致。
因此，此主题使用以下调色板中的颜色来适用于适用的范围。

| 名称 | 值 | 预览 | 名称 | 值 | 预览 |
|:--- |:--- |:--- |:--- |:--- |:--- |
|**白垩** | \#E5C07B |![chalky](/docs/colors/chalky.jpg)|**绿色**|\#98C379 |![green](/docs/colors/green.jpg)|
|**粉红色** | \#e06c75 |![coral](/docs/colors/coral.jpg)|**浅白色**| \#abb2bf |![light-white](/docs/colors/light-white.jpg)|
|**黑暗** | \#5C6370 |![dark](/docs/colors/dark.jpg)|**马里布** | \#61FEF |![malibu](/docs/colors/malibu.jpg)|
|**错误** | \#f44747 |![error](/docs/colors/error.jpg)|**紫色** | \#C678DD |![purple](/docs/colors/purple.jpg)|
|**喷泉蓝**| \#56B6C2 |![fountain-blue](/docs/colors/fountain-blue.jpg)|**威士忌** |\#d19a66 |![whiskey](/docs/colors/whiskey.jpg)|

## 构建插件

要构建插件，请运行`./gradlew build'。
如果使用Intellij，请同步Gradle项目并运行**构建**任务。

## 测试插件

要测试插件，请运行`./gradlew runide`以在Intellij的新实例中构建和启动插件。
如果使用Intellij，请运行**运行IDE**任务。
所有插件将被禁用，设置将是新安装的默认设置。
首先运行此任务时，您将需要将外观更改为Darcula，以启用配色方案中的一个暗色主题。
选择要测试的任何方案，然后更改设置。
