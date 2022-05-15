# 项目管理服务

此项目为项目管理服务

## 目录
* [环境准备](#环境准备)
* [启动命令](#启动命令)
* [词汇表](#词汇表)

## 环境准备
- Java8
- Docker

## 启动命令
```bash
//Step0 在启动该项目前，请预先安装好 Java8 以及 Docker，下载地址：
//Java8: https://www.java.com/en/download/manual.jsp
//Docker: https://docs.docker.com/get-docker/
//本项目正常运行需要占用两个端口，分别为 8081 与 23306, 请提前预留，或自行修改端口号

// Step1  创建该项目所需的数据库实例
sh ./env-build-scripts/create_database_instance.sh  //在项目根目录下执行

// Step2 创建数据库。 待上一步执行成功后，使用任一数据库连接工具连上此数据库实例（示例用户名密码在命令脚本中），在 sql console 中执行以下sql 脚本,创建数据库
// env-build-scripts/init_db.sql  

// Step3 启动项目，在首次运行时， 如果系统中没有对应版本的 gradle，那么会先自动下载，之后项目启动时，会自动运行db/migration下的所有sql 脚本，创建对应的表，数据库结构等等, 最后项目启动在 8081 端口下
./gradlew bootRun       

// 附：构建项目命令
./gradlew build      

```

## 词汇表

### [项目]词汇表
|  领域名词 |  英文   |  模型  |   表名  |  备注  |
|  :----:  | :----: | :----: | :----: | :----: |
|    项目   | project |  Project  | project  |  / |
|    项目名称   | name |  name  | /  |  / |
|    项目类型   | type |  type  | /  |  / |
|    客户项目   | client project |  ClientProject  | /  |  / |
|    通用项目   | general project |  GeneralProject  | /  |  / |
|    项目类型   | type |  type  | /  |  / |
|    地点   | location |  location  | /  |  / |
|    子项目   | sub project |  SubProject  | sub_project  |  / |

### 【子项目词汇表
|  领域名词 |  英文   |  模型  |   表名  |  备注  |
|  :----:  | :----: | :----: | :----: | :----: |
|    子项目   | sub project |  SubProject  | sub_project  |  / |
|    子项目名称   | name |  name  | /  |  / |
|    地点   | location |  location  | /  |  / |
|    子项目   | sub project |  SubProject  | /  |  / |

### 【子项目类型]词汇表
|  领域名词 |  英文   |  模型  |   表名  |  备注  |
|  :----:  | :----: | :----: | :----: | :----: |
|    子项目类型   | sub project type |  SubProjectType  | sub_project_type  |  / |
|    子项目名称   | name |  name  | /  |  / |

### [合同]词汇表
|  领域名词 |  英文   |  模型  |   表名  |  备注  |
|  :----:  | :----: | :----: | :----: | :----: |
|    合同   | contract |  Contract  | contract  |  / |
|    合同名称   | name |  name  |  / |  / |

### [客户]词汇表
|  领域名词 |  英文   |  模型  |   表名  |  备注  |
|  :----:  | :----: | :----: | :----: | :----: |
|    客户   | client |  Client  | client  |  / |
|    客户名称   | name |  name  |  / |  / |
|    客户名称   | client manager |  clientManager  |  / |  / |

### [员工分配]词汇表
|  领域名词 |  英文   |  模型  |   表名  |  备注  |
|  :----:  | :----: | :----: | :----: | :----: |
|    员工分配   | assignment |  Assignment  | assignment  |  / |
|    被分配人   | assignee |  assignee  |  / |  / |
|    项目角色   | project role  |  projectRole  |  / |  / |
|    费率   | rate  |  rate  |  / |  / |
