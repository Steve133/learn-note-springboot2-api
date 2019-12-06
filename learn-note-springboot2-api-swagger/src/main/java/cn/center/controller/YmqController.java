package cn.center.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.center.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Rest风格用户的增删改查接口
 * Swagger2 注解整理
 * ====================================================
 * Api：修饰整个类，描述Controller的作用
 * ApiOperation：描述一个类的一个方法，或者说一个接口
 * ApiParam：单个参数描述
 * ApiModel：用对象来接收参数
 * ApiProperty：用对象接收参数时，描述对象的一个字段
 * ApiResponse：HTTP响应其中1个描述
 * ApiResponses：HTTP响应整体描述
 * ApiIgnore：使用该注解忽略这个API
 * ApiError ：发生错误返回的信息
 * ApiImplicitParam：一个请求参数
 * ApiImplicitParams：多个请求参数
 * ====================================================
 * 访问地址
 * http://localhost:7006/swagger2/swagger-ui.html
 */
@RestController
@RequestMapping("/user")
@Api(value = "YmqController 测试", description = "RESTful API")
public class YmqController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={""}, method= RequestMethod.GET)
    public List<User> getUserList() {
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postUser(@RequestParam User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@RequestParam Long id) {
        return users.get(id);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@RequestParam Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@RequestParam Long id) {
        users.remove(id);
        return "success";
    }
    
    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/ignoreApi", method = RequestMethod.GET)
    public String  ignoreApi() {
        return "Spring Boot Swagger !";
    }
}
