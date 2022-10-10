package cn.edu.cumt.ec.shop.controller;

import cn.edu.cumt.ec.shop.dto.UserAddEditParam;
import cn.edu.cumt.ec.shop.dto.UserQueryParam;
import cn.edu.cumt.ec.shop.vo.ResourceVo;
import cn.edu.cumt.ec.shop.vo.UserVo;
import cn.edu.cumt.ec.shop.service.IUserService;
import cn.edu.cumt.ec.shop.vo.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@Api(value = "用户相关的操作")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/id")
    public UserVo getById(@RequestParam("userId") Integer userId){
        return UserMapper.INSTANCE.userToUserVo(userService.getById(userId));
    }
    @GetMapping("")
    public List<UserVo> list(){
        return UserMapper.INSTANCE.userToUserVo(userService.list());
    }

    @PostMapping("")
    public Boolean add(@RequestBody UserAddEditParam userAddEditParam){
        return userService.add(userAddEditParam);
    }

    @PutMapping("")
    public Boolean update(@RequestBody UserAddEditParam userAddEditParam){
        return userService.update(userAddEditParam);
    }

    @PostMapping("/page")
    public Page<UserVo> page(@RequestBody UserQueryParam userQueryParam){
        return userService.page(userQueryParam);
    }

    /**
     * 获取当前用户的资源项
     * @return
     */
    @GetMapping("/current/resources")
    public List<ResourceVo> resources(){
        return userService.resources();
    }
}