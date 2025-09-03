package org.example.openai.Controller;

import lombok.RequiredArgsConstructor;
import org.example.openai.DTO.ApiRequest;
import org.example.openai.DTO.ApiResponse;
import org.example.openai.Service.APiService;
import org.springframework.web.bind.annotation.*;

/**
 * 作者: hgl
 * 描述:API控制类
 * 创建时间: 2025/9/2
 */


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ApiController {
    private final APiService apiService;

    @PostMapping("/chat")
    public ApiResponse chat(@RequestBody ApiRequest.ChatRequest request) {
        return apiService.callChatApi(request.getMessage());
    }

        @GetMapping("/simple-chat")
    public String simpleChat(@RequestParam String message) {
        return apiService.getSimpleResponse(message);
    }

    @PostMapping("/batch-chat")
    public String batchChat(@RequestBody ApiRequest.BatchChatRequest request) {
        StringBuilder result = new StringBuilder();
        for (String message : request.getMessages()) {
            String response = apiService.getSimpleResponse(message);
            result.append("Q: ").append(message).append("\n");
            result.append("A: ").append(response).append("\n\n");
        }
        return result.toString();
    }
}
