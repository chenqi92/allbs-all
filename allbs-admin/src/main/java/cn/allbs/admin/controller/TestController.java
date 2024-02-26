package cn.allbs.admin.controller;

import cn.allbs.admin.config.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类 TestController
 *
 * @author ChenQi
 * @date 2024/2/26
 */
@RequestMapping(value = "/test")
@RestController
public class TestController {

    /**
     * 第一个接口测试
     *
     * @return String
     */
    @GetMapping("test1")
    public String test1() {
        return "Hello World";
    }

    @GetMapping("test2")
    public R<String> test2() {
        return R.failed("Hello World!");
    }

}
