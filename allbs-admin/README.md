### api文档访问
#### 路径 
http://{ip}:{port}/doc.html
如 http://127.0.0.1:8888/doc.html
#### 注解对应关系

| SpringFox | SpringDoc                                                    |
| -- |--------------------------------------------------------------|
| @Api | @Tag                                                         |
| @ApiIgnore | @Parameter(hidden = true)或@Operation(hidden = true)或@Hidden |
| @ApiImplicitParams | @Parameters                                                  |
| @ApiImplicitParam | @Parameter                                                   |
| @ApiModel | @Schema                                                      |
| @ApiModelProperty | @Schema                                                      |
| @ApiOperation(value = "foo", notes = "bar") | @Operation(summary = "foo", description = "bar")             |
| @ApiParam | @Parameter                                                   |
| @ApiResponse(code = 404, message = "foo") | ApiResponse(responseCode = "404", description = "foo")       |

#### 时间格式自动转换
接口输入或者返回的时间将会自动转化为yyyy-MM-dd HH:mm:ss的形式，实现查看文件
cn.allbs.admin.config.core.WebmvcConfig

#### redis配置
配置文件:cn.allbs.admin.config.redis.RedisConfig
只注入了RedisTemplate<Object, Object>
序列化和反序列化方式为Jackson2JsonRedisSerializer

#### 登录
POST auth/login
{
    "userName":"",
    "password":""
}
