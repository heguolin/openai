package org.example.openai.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.openai.Configration.ApiConfig;
import org.example.openai.DTO.ApiRequest;
import org.example.openai.DTO.ApiResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 作者: hgl
 * 描述:Api服务类
 * 创建时间: 2025/9/2
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class APiService {
    private final RestTemplate restTemplate;
    private final ApiConfig apiConfig;

    public ApiResponse callChatApi(String message) {
        String url = apiConfig.getBaseUrl() + "/v1/chat/completions";


        //构建请求体
        ApiRequest request = new ApiRequest();
        request.setModel("deepseek-chat");
        request.setMessages(List.of(new ApiRequest.Message("user",message)));
        request.setTemperature(0.7);
        request.setMaxTokens(1000);

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);//发送的请求体是json格式
        headers.set("Authorization", "Bearer " + apiConfig.getApikey());
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);//收到的数据是json格式

        //把请求体和请求头封装成一个http实体，方便发送post或put请求
        HttpEntity<ApiRequest> entity = new HttpEntity<>(request, headers);

        log.info("调用api：{}", url);
        ResponseEntity<ApiResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, ApiResponse.class);
        if (response.getStatusCode()==HttpStatus.OK && response.getBody() != null) {
            log.info("api调用成功");
            return response.getBody();
        }else {
            log.error("api调用失败{}", response.getStatusCode());
            throw new RuntimeException("API 调用失败: " + response.getStatusCode());
        }
    }
    public String getSimpleResponse(String message){
        ApiResponse response = callChatApi(message);
        if (response != null
                && response.getChoices() != null
                && !response.getChoices().isEmpty()) {
            return response.getChoices().get(0).getMessage().getContent();
        }
        return "未获取有效响应";
    }
}
