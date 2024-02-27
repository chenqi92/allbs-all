### api文档访问
#### 路径 
http://localhost:8888/doc.html 或者 http://localhost:8888/swagger-ui/index.html

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
