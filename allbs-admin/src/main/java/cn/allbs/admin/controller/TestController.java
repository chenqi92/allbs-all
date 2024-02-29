package cn.allbs.admin.controller;

import cn.allbs.admin.config.R;
import cn.allbs.admin.config.enums.SexEnum;
import cn.allbs.admin.entity.test.TestFirEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 类 TestController
 *
 * @author ChenQi
 * @date 2024/2/26
 */
@Tag(name = "测试类")
@RequestMapping(value = "/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final RedisTemplate<String, Object> redisTemplate;

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

    @GetMapping("testRedis")
    public R<Map<Object, Object>> testRedis() {
        Map<String, Object> map = new HashMap<>();
        map.put("1", "test1");
        map.put("2", 2222);
        redisTemplate.opsForHash().putAll("test:redis", map);
        return R.ok(redisTemplate.opsForHash().entries("test:redis"));
    }

    @GetMapping("/test4/{label}")
    @Parameter(name = "label", schema = @Schema(description = "性别枚举", type = "int32", allowableValues = {"0", "1"}))
    @Operation(summary = "枚举接口测试", description = "测试枚举参数传递和返回")
    public SexEnum test01(@PathVariable SexEnum label) {
        return label;
    }
}
