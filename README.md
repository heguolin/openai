# Spring Boot API 接口文档

## 📋 概述

本文档提供了 Spring Boot 聊天 API 接口的详细说明，包含三个主要接口用于与 AI 模型进行交互。

------

## 🔗 基础信息

- **基础URL**: `http://localhost:8080`
- **默认端口**: `8080`
- **Content-Type**: `application/json`
- **认证方式**: API Key (通过配置文件中 `api.key` 设置)

------

## 📊 接口列表

### 1. 简单聊天接口 (GET)

快速获取 AI 回复的简单接口。

### 2. 标准聊天接口 (POST)

完整的聊天交互接口，返回结构化数据。

### 3. 批量聊天接口 (POST)

支持批量处理多个消息的接口。

------

## 🔍 接口详情

### 1. GET /api/simple-chat

**简要描述**: 获取 AI 的简单文本回复

**请求方式**: GET

**请求参数**:

| 参数名  | 类型   | 必填 | 说明                   |
| :------ | :----- | :--- | :--------------------- |
| message | string | 是   | 要发送给 AI 的消息内容 |

**请求示例**:

http

```
GET http://localhost:8080/api/simple-chat?message=你好，请自我介绍
```

**响应示例**:

text

```
你好！我是DeepSeek AI助手，很高兴为您服务...
```

**状态码**:

- `200`: 请求成功
- `500`: 服务器内部错误

------

### 2. POST /api/chat

**简要描述**: 与 AI 进行完整对话，返回结构化响应

**请求方式**: POST

**请求头**:

text

```
Content-Type: application/json
```

**请求体**:

json

```
{
  "message": "string"
}
```

**请求示例**:

http

```
POST http://localhost:8080/api/chat
Content-Type: application/json

{
  "message": "请写一首关于春天的诗"
}
```

**响应体** (ApiResponse):

json

```
{
  "id": "string",
  "object": "string",
  "created": 1677652288,
  "model": "string",
  "choices": [
    {
      "index": 0,
      "message": {
        "role": "string",
        "content": "string"
      },
      "finishReason": "string"
    }
  ],
  "usage": {
    "promptTokens": 0,
    "completionTokens": 0,
    "totalTokens": 0
  }
}
```

**字段说明**:

- `id`: 本次对话的唯一标识
- `model`: 使用的 AI 模型名称
- `choices[].message.content`: AI 回复内容
- `usage`: token 使用情况统计

**状态码**:

- `200`: 请求成功
- `400`: 请求参数错误
- `500`: 服务器内部错误

------

### 3. POST /api/batch-chat

**简要描述**: 批量处理多个消息

**请求方式**: POST

**请求头**:

text

```
Content-Type: application/json
```

**请求体**:

json

```
{
  "messages": ["string", "string", "string"]
}
```

**请求示例**:

http

```
POST http://localhost:8080/api/batch-chat
Content-Type: application/json

{
  "messages": [
    "你好，请自我介绍",
    "什么是人工智能？",
    "写一个简单的Java程序"
  ]
}
```

**响应示例**:

text

```
Q: 你好，请自我介绍
A: 你好！我是DeepSeek AI助手...

Q: 什么是人工智能？
A: 人工智能是模拟人类智能的技术...

Q: 写一个简单的Java程序  
A: public class HelloWorld { public static void main(String[] args) { System.out.println("Hello World"); } }
```

**状态码**:

- `200`: 请求成功
- `400`: 请求参数错误
- `500`: 服务器内部错误

------

## 🔧 配置说明

### application.yml 配置

yaml

```
api:
  base-url: https://api.example.com  # API 基础地址
  key: your-api-key-here            # API 密钥
  timeout: 5000                     # 请求超时时间(毫秒)
```

### 环境变量配置

支持通过环境变量覆盖配置：

bash

```
export API_BASE_URL=https://api.deepseek.com
export API_KEY=your_actual_api_key
export API_TIMEOUT=10000
```

------

## 🚀 快速开始

### 1. 启动应用

bash

```
mvn spring-boot:run
# 或
java -jar your-application.jar
```

### 2. 测试接口

bash

```
# 测试简单接口
curl "http://localhost:8080/api/simple-chat?message=你好"

# 测试标准接口
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"你好"}'

# 测试批量接口
curl -X POST http://localhost:8080/api/batch-chat \
  -H "Content-Type: application/json" \
  -d '{"messages":["问题1", "问题2"]}'
```

------

## 🔍 监控与日志

### 日志输出

应用会记录以下信息：

- API 调用开始和结束
- 请求和响应详情
- 错误和异常信息

### 健康检查

http

```
GET http://localhost:8080/actuator/health
```

