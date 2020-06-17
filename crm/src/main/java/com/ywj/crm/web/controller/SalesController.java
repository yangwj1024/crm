package com.ywj.crm.web.controller;

import com.ywj.crm.bean.Chance;
import com.ywj.crm.bean.User;
import com.ywj.crm.service.ISalesService;
import com.ywj.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class SalesController {
    @Autowired
    private ISalesService salesService;
    @Autowired
    private IUserService userService;
    @RequestMapping("toSales")
    public String toSales(HttpSession session){
        session.removeAttribute("chance");
        session.setAttribute("chances",salesService.findAllChancesOfPage((User) session.getAttribute("user"),0));
        session.setAttribute("handles",userService.findUserByRoleId(4));
        return "pages/sales";
    }

    @RequestMapping("pageSales")
    public String pageSales(HttpSession session,Integer page){
        session.setAttribute("chances",salesService.findChancesByChanceOfPage((User) session.getAttribute("user"),(Chance)session.getAttribute("chance"), page));
        session.setAttribute("handles",userService.findUserByRoleId(4));
        return "pages/sales";
    }

    @RequestMapping("findChancesByChanceOfPage")
    public String findChancesByChanceOfPage(Chance chance, Integer page, HttpSession session){
        session.setAttribute("chance",chance);
        session.setAttribute("chances",salesService.findChancesByChanceOfPage((User) session.getAttribute("user"),chance, page));
        session.setAttribute("handles",userService.findUserByRoleId(4));
        return "pages/sales";
    }

    @PostMapping("saveChance")
    @ResponseBody
    public String saveChance(Chance chance){
        if(chance.getId()==null){
            salesService.saveOrUpdateChance(chance);
            return "保存成功！";
        }else {
            salesService.saveOrUpdateChance(chance);
            return "修改成功！";
        }
    }

    @RequestMapping("findChanceById")
    @ResponseBody
    public Chance findChanceById(Integer id){
        return salesService.findChanceById(id);
    }

    @RequestMapping("deleteChanceById")
    @ResponseBody
    public String deleteChanceById(Integer id){
        salesService.deleteChanceById(id);
        return "删除成功！";
    }

    @RequestMapping("resetChance")
    @ResponseBody
    public String resetUser(HttpSession session) {
        session.removeAttribute("chance");
        return "重置成功";
    }

}
