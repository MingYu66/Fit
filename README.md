![屏幕截图 2025-05-24 202615](https://github.com/user-attachments/assets/d00f3fbd-9ef8-41f8-b27b-4cb85d4ee777)
# 健康饮食管理系统

## 项目简介
本项目是一个完整的健康饮食管理系统，包含移动端应用、管理后台和服务器端三个主要部分。系统旨在帮助用户管理日常饮食，提供健康饮食建议，并支持管理员进行系统管理和数据维护。

## 系统架构
项目采用前后端分离架构，主要包含以下三个部分：

### 1. 移动端应用 (app)
- 基于uni-app框架开发
- 支持微信小程序运行环境
- 使用GraceUI5组件库
- 主要功能：
  - 用户饮食记录
  - 健康饮食建议
  - 个人数据统计
  - 用户信息管理

### 2. 管理后台 (mint-ui)
- 基于Vue.js开发
- 使用Mint UI组件库
- 主要功能：
  - 用户管理
  - 饮食数据管理
  - 系统配置
  - 数据统计分析

### 3. 服务器端 (healthyDiet)
- 基于Java开发
- 采用模块化架构设计
- 主要模块：
  - healthyDiet-system：系统核心模块
  - healthyDiet-framework：框架模块
  - healthyDiet-common：公共模块
  - healthyDiet-admin：管理模块

## 技术栈
- 前端：
  - uni-app
  - Vue.js
  - Mint UI
  - GraceUI5
- 后端：
  - Java
  - Spring Boot
  - MyBatis
  - MySQL

## 运行环境要求
- Node.js >= 12.0.0
- JDK >= 1.8
- MySQL >= 5.7
- 微信开发者工具

## 快速开始

### 移动端应用
1. 进入app目录
```bash
cd app
```
2. 安装依赖
```bash
npm install
```
3. 使用HBuilderX打开项目
4. 运行到微信开发者工具

### 管理后台
1. 进入mint-ui目录
```bash
cd mint-ui
```
2. 安装依赖
```bash
npm install
```
3. 启动开发服务器
```bash
npm run serve
```

### 服务器端
1. 进入healthyDiet目录
2. 配置数据库（参考sql目录下的脚本）
3. 修改配置文件
4. 使用Maven构建项目
```bash
mvn clean install
```
5. 运行项目
```bash
# Windows
ry.bat

# Linux/Mac
./ry.sh
```

## 项目结构
```
├── app/                # 移动端应用
│   ├── pages/         # 页面文件
│   ├── components/    # 组件
│   └── static/        # 静态资源
├── mint-ui/           # 管理后台
│   ├── src/          # 源代码
│   └── public/       # 公共资源
└── healthyDiet/      # 服务器端
    ├── healthyDiet-system/
    ├── healthyDiet-framework/
    ├── healthyDiet-common/
    └── healthyDiet-admin/
```

## 开发团队
[在此添加开发团队信息]

## 许可证
[在此添加许可证信息]

## 联系方式
[在此添加联系方式] 
