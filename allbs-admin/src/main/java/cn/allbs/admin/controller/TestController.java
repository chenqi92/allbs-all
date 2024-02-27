package cn.allbs.admin.controller;

import cn.allbs.admin.config.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类 TestController
 *
 * @author ChenQi
 * @date 2024/2/26
 */
@Tag(name = "测试类")
@RequestMapping(value = "/test")
@RestController
public class TestController {

    /**
     * 第一个接口测试
     *
     * @return String
     */
    @Operation(summary = "测试方法1")
    @GetMapping("test1")
    public String test1() {
        return "Hello World";
    }

    /**
     * 第二个测试接口
     *
     * @return R
     */
    @Operation(summary = "测试方法2")
    @GetMapping("test2")
    public R<String> test2() {
        return R.ok("Hello World!");
    }

}
