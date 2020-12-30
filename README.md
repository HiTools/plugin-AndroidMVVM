# plugin-AndroidMVVM

## 介绍

通过 Android Jetpack DataBinding 实现的MVVM极大的提高的开发效率，但是依然会有很多模板代码，本插件可以自动生成Activity、ViewModel、Layout 模板来进一步释放生产力

## 安装教程

### 0x01

直接通过AS插件商店搜索 Android MVVM Generator，下载即可

### 0x02

1. 下载源码
2. 在源码跟目录 运行 ./gradlew Jar 生成Jar包
3. 在AS插件管理目录选择 生成的Jar包

备注：
Jar生成的目录为：项目目录/build/libs

## 使用说明

### 0x01 生成Activity模板代码

选中要生成的目录，然后使用快捷键 ⇪ + ⌃ + A，在输入框中输入模块名称之后就会自动生成相应的代码

### 0x02 生成Fragment模板代码

选中要生成的目录，然后使用快捷键 ⇪ + ⌃ + F，在输入框中输入模块名称之后就会自动生成相应的代码

### 0x03 更换模板

Preference => Other Settings => Android MVVM Generator 进行更换成适合自己的模板
