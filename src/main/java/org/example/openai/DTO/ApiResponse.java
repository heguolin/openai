package org.example.openai.DTO;

import lombok.Data;

import java.util.List;

/**
 * 作者: hgl
 * 描述:API的响应
 * 创建时间: 2025/9/2
 */
@Data
public class ApiResponse {
    private String id; //响应id
    private String object;//响应对象
    private long created;//创造时间
    private String model;//模型
    private List<Choice> choices;
    private Usage usage;

    @Data
    public static class Choice {
        private int index;//当前生成的内容在列表中的索引
        private Message message;//包含role和content
        private String finishReason;//生成回答结束的原因
    }
    @Data
    public static class Message {
        private String role;//用户
        private String content;//内容
    }

    @Data
    public static class Usage {
        private int promptTokens;//用户输入或系统提示消耗的 token 数量
        private int completionTokens;//AI 模型生成的回答消耗的 token 数量
        private int totalTokens;//总共消耗的 token 数量
    }
}
