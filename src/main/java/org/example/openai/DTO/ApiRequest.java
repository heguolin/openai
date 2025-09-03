package org.example.openai.DTO;

import lombok.Data;

import java.util.List;

/**
 * 作者: hgl
 * 描述:API的请求
 * 创建时间: 2025/9/2
 */

@Data
public class ApiRequest {
    private String model;//模型名称
    private List<Message> messages;//消息列表
    private double temperature;//温度参数，决定ai回答问题的随机性和创造性
    private int maxTokens;//最大的token数量

    @Data
    public static class Message {
        private String role;//用户
        private String content;//内容

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
    @Data
    public static class ChatRequest {
        private String message;
    }

    @Data
    public static class BatchChatRequest {
        private List<String> messages;
    }



}
