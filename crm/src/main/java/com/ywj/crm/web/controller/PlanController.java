package com.ywj.crm.web.controller;

import com.ywj.crm.bean.Chance;
import com.ywj.crm.bean.Plan;
import com.ywj.crm.bean.User;
import com.ywj.crm.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class PlanController {

    @Autowired
    private IPlanService planService;

    @RequestMapping("toPlan")
    public String toPlan(HttpSession session) {
        session.removeAttribute("chance");
        session.setAttribute("chances", planService.findAllChances((User) session.getAttribute("user"), 0));
        return "pages/plan";
    }

    @RequestMapping("pagePlan")
    public String pagePlan(HttpSession session, Integer page) {
        session.setAttribute("chances", planService.findChancesByChanceOfPage((User) session.getAttribute("user"), (Chance) session.getAttribute("chance"), page));
        return "pages/plan";
    }

    @RequestMapping("findChancesByChanceOfPageInPlan")
    public String findChancesByChanceOfPageInPlan(Chance chance, Integer page, HttpSession session) {
        session.setAttribute("chance", chance);
        session.setAttribute("chances", planService.findChancesByChanceOfPage((User) session.getAttribute("user"), chance, page));
        return "pages/plan";
    }

    @RequestMapping("toPlanAdd/{chanceId}")
    public String toPlanAdd(@PathVariable("chanceId") Integer id, Model model) {
        model.addAttribute("chance", planService.findChanceById(id));
        return "pages/plan_add";
    }

    @RequestMapping("toPlanEdit/{chanceId}")
    public String toPlanEdit(@PathVariable("chanceId") Integer id, Model model) {
        model.addAttribute("chance", planService.findChanceById(id));
        model.addAttribute("plans",planService.findByChanceId(id));
        return "pages/plan_edit";
    }

    @RequestMapping("toPlanDetail")
    public String toPlanDetail() {
        return "pages/plan_detail";
    }

    @RequestMapping("resetPlan")
    @ResponseBody
    public String resetPlan(HttpSession session) {
        session.removeAttribute("chance");
        return "重置成功";
    }

    //提交计划
    @PostMapping("toCommitPlan")
    @ResponseBody
    public String toCommitPlan(Plan plan) {
        plan.setResult("进行中");
        planService.commitPlan(plan);
        return "提交成功！";
    }

    //保存或修改计划
    @PostMapping("savevOrUpdatePlan")
    @ResponseBody
    public String savevOrUpdatePlan(Plan plan) {
        if (plan.getId()==null){
            planService.savePlan(plan);
            return "添加成功！";
        }else {
            planService.savePlan(plan);
            return "修改成功！";
        }
    }

    @RequestMapping("deletePlanById")
    @ResponseBody
    public String deletePlanById(Integer id){
        planService.deletePlanById(id);
        return "删除成功！";
    }

    @RequestMapping("findPlanById")
    @ResponseBody
    public Plan findPlanById(Integer id){
        return planService.findPlanById(id);

    }

}
