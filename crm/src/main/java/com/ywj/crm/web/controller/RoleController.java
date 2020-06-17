package com.ywj.crm.web.controller;

import com.ywj.crm.bean.Role;
import com.ywj.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("toRole")
    public String toRole(Model model){
        Page<Role> roles = roleService.findAllRoles(0);
        model.addAttribute("roles",roles);
/*        Integer total = roleService.findTotal();
       List<Integer> pageTotal = new ArrayList<>();
        //计算分页总条数
        if(total%5==0){
            for(int i=1;i<=(total/5);i++){
                pageTotal.add(i);
            }
        }else {
            for(int i=1;i<=(total/5)+1;i++){
                pageTotal.add(i);
            }
        }
        model.addAttribute("pageTotal",pageTotal);*/
        return "pages/role";
    }


    /**
     *查询当前页的数据
     * @param pageIndex 当前第几页
     * @param model 当前页数据
     * @return
     */
    @RequestMapping("findAllRoles")
    public String findAllRoles(Integer pageIndex, Model model) {
        Page<Role> roles = roleService.findAllRoles(pageIndex-1);
        model.addAttribute("roles",roles);
        return "pages/role::tablePage";
    }

    /**
     *
     * @param role 数据
     * @param operate 操作，用于区别新增还是修改
     * @return
     */
    @PostMapping("saveRole")
    @ResponseBody
    public String saveRole(Role role, String operate){
        System.out.println(role.getId());
        if(role.getId()==null){
            roleService.saveOrUpdateRole(role);
            return "添加成功！";
        }else {
            roleService.saveOrUpdateRole(role);
            return "修改成功!";
        }
    }

    @GetMapping("deleteRole")
    @ResponseBody
    public String deleteRole(Integer id){
        roleService.deleteRole(id);
        return "删除成功！";
    }

    @RequestMapping("findRoleById")
    @ResponseBody
    public Role findRoleById(Integer id){
        return roleService.findRoleById(id);
    }
}
