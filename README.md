
# 项目名称

本项目是一个改进的计算器，使用Java编写，并结合MySQL数据库实现了查看历史记录的功能。

## 软件设计思路

题目要求编写一个简单的计算器，具体的软件设计思路如下：

1. **UI的设计**：参考了普通计算器的界面布局，添加了`ans`按钮。
2. **计算器的功能设计**：
    - 运算：实现了加、减、乘、除、取余五种运算。
    - 清除：清除当前数据。
    - 日志：将计算结果写入MySQL数据库，并提供查看历史记录的功能。

## 每个类的功能

1. **Calculator2_0**：该类实现了计算器的UI界面，包括基本运算功能，并能调用历史记录的UI界面和数据库连接功能。
2. **Database**：该类实现了与MySQL数据库的交互，包括将数据存储到数据库中和从数据库中读取数据到历史记录页面。
3. **HistoryUI**：该类设计了历史记录页面的UI，用于显示计算历史记录。

## 安装与运行

1. 首先，确保你的计算机已经安装了Java和MySQL数据库。
2. 克隆或下载本项目到你的本地目录。
3. 创建一个MySQL数据库，并将数据库连接配置信息填写到项目的配置文件中。
4. 编译并运行`Calculator2_0.java`文件。
5. 计算器界面将显示出来，你可以进行计算操作，并查看历史记录。