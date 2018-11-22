package com.zyh.learn.zyhlearn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/12 10:18
 * @version: 1.0
 */
@RestController
@Api(value = "ControllerTest", description = "测试swagger")
@RequestMapping(value = "/zyh/swagger")
public class ControllerTest {

    @ApiOperation(value = "测试swagger", notes = "测试swagger", httpMethod = "POST")
    @RequestMapping(value = "/testSwagger", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "message", dataType = "String", required = true, paramType = "form") })
    public String testSwagger(HttpServletRequest request, @RequestParam("message") String message) {
        String name = request.getHeader("userName");
        return message + name;
    }

    @ApiOperation(value = "helloWord", notes = "helloWord", httpMethod = "GET")
    @RequestMapping(value = "/helloWord", method = RequestMethod.GET)
    public static String helloWord() {
        return "hello world";
    }
}
