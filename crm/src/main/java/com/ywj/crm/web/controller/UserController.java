package com.ywj.crm.web.controller;

import com.ywj.crm.bean.Role;
import com.ywj.crm.bean.User;
import com.ywj.crm.service.IRoleService;
import com.ywj.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("user/login")
    @ResponseBody
    public String login(String name, String password,HttpSession session){
        User user = userService.findByName(name);
        if(user==null){
            return "当前用户不存在！";
        }
        if(!user.getPassword().equals(password)){
            return "密码错误！";
        }
        if(user.getFlag()==2){
            return "该用户已注销!";
        }
        session.setAttribute("user",user);
        return "success";
    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping("toUser")
    public String toUser(Model model, HttpSession session){
        Page<User> users = userService.findAllUsersOfPage(0);
        model.addAttribute("users",users);
        List<Role> roles = roleService.findAllRoles();
        session.setAttribute("roles",roles);
        return "pages/user";
    }
    @RequestMapping("pageUser")
    public String toUser(Integer page, Model model){
        Page<User> users = userService.findAllUsersOfPage(page);
        model.addAttribute("users",users);
        return "pages/user";
    }

    @RequestMapping("saveOrUpdateUser")
    @ResponseBody
    public String saveOrUpdateUser(User user){
        if (user.getId()==null){
            userService.saveOrUpdateUser(user);
            return "保存成功！";
        }else{
            userService.saveOrUpdateUser(user);
            return "修改成功！";
        }
    }

    @RequestMapping("deleteUserById")
    @ResponseBody
    public String deleteRoleById(Integer id) {
        System.out.println(id);
        userService.deleteUserById(id);
        return "删除成功";
    }

    @RequestMapping("findUserById")
    @ResponseBody
    public User findUserById(Integer id){

        return  userService.findUserById(id);
    }

    @RequestMapping("findAllUserByTypeOfPage")
    public String findAllUserByTypeOfPage(Integer roleId, Integer page, Model model){
        Page<User> users = userService.findAllUserByTypeOfPage(roleId, page);
        model.addAttribute("users",users);
        model.addAttribute("roleId",roleId);
        return "pages/user";
    }
}
