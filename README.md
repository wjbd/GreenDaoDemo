## 前言
在数据持久化上大概都知道，目前为最好的是GreenDao，执行效率可以达到Sqlite的两倍。所以最近研究了一下GreenDao的使用方法。这里没有那些基础文档，只有使用方式。在项目中我加上了最近正在使用的Butter Knife注解库，顺便提一下Butter Knife的使用方式。
## GreenDao的导入
我选择通过gradle方式进行导入GreenDao库  
1.在Project的build.gradle上添加
```
buildscript {
    dependencies {
        classpath 'org.greenrobot:greendao-gradle-plugin:3.2.0'//版本号可以自己选择
    }
}
```
2.在Module的build.gradle上添加
```
apply plugin: 'org.greenrobot.greendao'
dependencies {
    ...
    compile 'org.greenrobot:greendao:3.2.0'
}
```
这样就算是把GreenDao库给导入进来了，这里我也留下GreenDao的官方网站，方便查阅文档。  
[传送门--GreenDao官方网站](http://greenrobot.org/greendao/)
## Butter Knife的导入
我也选择通过gradle的方式进行导入，我选择了最新的ButterKnife依赖库8.4.0版本。ButterKnife自从8.0以后的版本导入方式跟以前有些差距。这里列举一下导入步骤。  
1.在Project的build.gradle上添加
```
buildscript {
    dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'//版本号可以自己选择
    }
}
```
2.在Module的build.gradle上添加
```
apply plugin: 'com.neenbedankt.android-apt'
dependencies {
    ...
    compile 'com.jakewharton:butterknife:8.4.0'
    apt 'com.jakewharton:butterknife-compiler:8.4.0'
}
```
这样才算是把ButterKnife导入进来  
然后这里不多说ButterKife的使用方法了，大家可以去官网上查阅，官网上有详细的使用方法。  
[传送门--ButterKnife官网](http://jakewharton.github.io/butterknife/)
## GreenDao的使用方式
1.创建实体类
```
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;//注意id类型是long的包装类Long
    @Unique
    @NotNull
    private String name;
    private int age;


    @Generated(hash = 586692638)
    public User() {
    }

    @Generated(hash = 955858333)
    public User(Long id, @NotNull String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    //省去getter，setter方法
}
```
2.此时通过编译会生成以下目录，其中包括DaoMaster，DaoSession，Dao  
![image](https://github.com/wjbd/GreenDaoDemo/raw/master/screenshots/list.png)  
3.当然，你也可以指定这些文件的生成目录，只需要在Project的build.grdle上加上以下代码:
```
greendao{
    targetGenDir 'src/main/java'
}
```
在这里你可以配置以下属性
- schemaVersion： 数据库schema版本，也可以理解为数据库版本号
- daoPackage：设置DaoMaster 、DaoSession、Dao包名
- targetGenDir：设置DaoMaster 、DaoSession、Dao目录
- targetGenDirTest：设置生成单元测试目录
- generateTests：设置自动生成单元测试用例  
  
4.实体@Entity的注解  
- schema：告知GreenDao当前实体属于哪个schema
- active：标记一个实体处于活动状态，活动实体有更新、删除和刷新方法
- nameInDb：在数据中使用的别名，默认使用的是实体的类名
- indexes：定义索引，可以跨越多个列
- createInDb：标记创建数据库表  
  
5.属性注解  
- @Id :主键 Long型，可以通过@Id(autoincrement = true)设置自增长
- @Property：设置一个非默认关系映射所对应的列名，默认是的使用字段名 举例：@Property (nameInDb="name")
- @NotNul：设置数据库表当前列不能为空
- @Transient ：添加次标记之后不会生成数据库表的列  
  
6.索引注解
- @Index：使用@Index作为一个属性来创建一个索引，通过name设置索引别名，也可以通过unique给索引添加约束
- @Unique：向数据库列添加了一个唯一的约束  
  
7.关系注解
- @ToOne：定义与另一个实体的关系
- @ToMany：定义与多个实体对象的关系  
## 实战效果图  
![image](https://github.com/wjbd/GreenDaoDemo/raw/master/screenshots/greendao.gif)  
