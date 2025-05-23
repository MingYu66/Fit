package com.healthyDiet.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @version: java version 1.8
 */
@Service
public class DeepSeekService {

    @Value("${deepseek.api-key}")
    private String apiKey;

    @Value("${deepseek.api-url}")
    private String apiUrl;

    @Autowired
    private com.healthyDiet.common.core.redis.RedisCache redisCache; // 使用若依的Redis缓存组件

    private static final int MAX_HISTORY = 6; // 保留最近3轮对话

    public String getFitnessAdvice(Long userId, String userMessage) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // 获取对话历史
        List<Map<String, String>> history = getHistory(userId);

        // 构建消息列表
        List<Map<String, String>> messages = new ArrayList<>(history);
        messages.add(createMessage("user", "你是一个博学的多领域专家，请用中文回答：" + userMessage));

        // 构建请求体
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "deepseek-chat");
        requestBody.put("messages", messages);

        // 发送请求
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(
                requestBody,
                createHeaders()
        );

        ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);

        // 处理响应
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Map<String, Object> result = response.getBody();
            List<Map<String, Object>> choices = (List<Map<String, Object>>) result.get("choices");
            if (choices != null && !choices.isEmpty()) {
                Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                String content = (String) message.get("content");

                // 保存对话历史
                saveHistory(userId, messages, message);
                return content;
            }
        }
        throw new RuntimeException("API请求失败: " + (response.getBody() != null ? response.getBody() : ""));
    }

    public String getDeepseepHealtyh(Long userId, String userMessage) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // 获取对话历史
        List<Map<String, String>> history = getHistory(userId);

        // 构建消息列表
        List<Map<String, String>> messages = new ArrayList<>(history);
        messages.add(createMessage("user", "你是医疗领域的专家，我会给你一段健康数据，包含：当前30分钟左右的血压、心率指数，请你根据医学标准给我一些意见，比如，当前血压过高？心率过快？用简短精炼的中文回答（字数30-40字）：" + userMessage));

        // 构建请求体
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "deepseek-chat");
        requestBody.put("messages", messages);

        // 发送请求
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(
                requestBody,
                createHeaders()
        );

        ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);

        // 处理响应
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Map<String, Object> result = response.getBody();
            List<Map<String, Object>> choices = (List<Map<String, Object>>) result.get("choices");
            if (choices != null && !choices.isEmpty()) {
                Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                String content = (String) message.get("content");

                // 保存对话历史
                saveHistory(userId, messages, message);
                return content;
            }
        }
        throw new RuntimeException("API请求失败: " + (response.getBody() != null ? response.getBody() : ""));
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        return headers;
    }

    private List<Map<String, String>> getHistory(Long userId) {
        String cacheKey = "ai_history:" + userId;
        List<Map<String, String>> history = redisCache.getCacheObject(cacheKey);
        return history != null ? history : new ArrayList<>();
    }

    private void saveHistory(Long userId, List<Map<String, String>> messages, Map<String, Object> aiMessage) {
        String cacheKey = "ai_history:" + userId;
        List<Map<String, String>> newHistory = new ArrayList<>(messages);
        // 添加AI回复
        Map map = new HashMap();
        map.put("role","assistant");
        map.put("content",aiMessage.get("content").toString());
        newHistory.add(map);


        // 控制历史记录长度
        if (newHistory.size() > MAX_HISTORY) {
            newHistory = newHistory.subList(newHistory.size() - MAX_HISTORY, newHistory.size());
        }

        redisCache.setCacheObject(cacheKey, newHistory, 2, TimeUnit.HOURS); // 缓存2小时
    }

    private Map<String, String> createMessage(String role, String content) {
        Map<String, String> message = new HashMap<>();
        message.put("role", role);
        message.put("content", content);
        return message;
    }
}