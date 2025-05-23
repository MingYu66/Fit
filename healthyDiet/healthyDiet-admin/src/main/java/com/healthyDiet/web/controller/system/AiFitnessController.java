package com.healthyDiet.web.controller.system;

import com.healthyDiet.system.service.impl.DeepSeekService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.healthyDiet.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version: java version 1.8
 */
@Controller
@RequestMapping("/ai/fitness")
public class AiFitnessController {

    @Autowired
    private DeepSeekService deepSeekService;

    @PostMapping("/ask")
    @ResponseBody
    public AjaxResult getAdvice(@RequestBody FitnessRequest request) {
        System.out.println(request);
        try {
//            LoginUser loginUser = SecurityUtils.getLoginUser();
            String advice = deepSeekService.getFitnessAdvice(
                    request.getUserId(),
                    request.getUserMessage()
            );
            System.out.println(advice);
            return AjaxResult.success(advice);
        } catch (Exception e) {
            return AjaxResult.success("获取建议失败：" + e.getMessage());
        }
    }

}

@Data
class FitnessRequest {
    private String userMessage;
    private Long userId;
}