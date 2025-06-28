# Tlias Web 管理系统

## 项目简介
这是一个基于Spring Boot的Web管理系统，提供部门、员工、班级、学生等管理功能。

## 运行说明

### 环境要求
- JDK 17 或更高版本
- Maven 3.6 或更高版本
- MySQL 8.0

### 数据库配置
1. 创建名为`tlias`的数据库
2. 导入数据库脚本

### 密码加密说明
本项目使用Jasypt对敏感信息（如数据库密码）进行加密。配置文件中的密码使用`ENC()`包裹表示已加密内容。

#### 生成加密密码
在开发环境中，可以使用JasyptConfig类中的main方法生成加密密码：
1. 编辑`JasyptConfig`类中的`main`方法，设置需要加密的原始密码
2. 运行main方法，控制台会输出加密后的密码
3. 将输出的加密密码使用`ENC()`包裹，放入配置文件

#### 运行时指定密钥
为了安全起见，建议在运行时通过环境变量或命令行参数传入加密密钥，而不是在配置文件中直接指定。

使用命令行参数运行：
```bash
java -jar tlias-web-management.jar --jasypt.encryptor.password=mySecretKey
```

或使用环境变量：
```bash
# Linux/macOS
export JASYPT_ENCRYPTOR_PASSWORD=mySecretKey
java -jar tlias-web-management.jar

# Windows
set JASYPT_ENCRYPTOR_PASSWORD=mySecretKey
java -jar tlias-web-management.jar
```

## API文档
详细的API文档请参考`接口文档.md`。 