package cn.edu.cumt.ec.shop.controller;

import cn.edu.cumt.ec.shop.entity.Role;
import cn.edu.cumt.ec.shop.service.IRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@Api(value = "角色相关的操作")
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @GetMapping("")
    public List<Role> listAll(){
        return roleService.list();
    }
}