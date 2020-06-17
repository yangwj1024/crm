package com.ywj.crm.service;

import com.ywj.crm.bean.Chance;
import com.ywj.crm.bean.Plan;
import com.ywj.crm.bean.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPlanService {
    /**
     * 分页查询当前user所有商机
     * @param user 当前用户
     * @param page 当前页
     * @return
     */
    Page<Chance> findAllChances(User user, Integer page);

    /**
     * 通过处理人按条件分页查询所有chance
     * @param user 处理人
     * @param chance 条件
     * @param page 当前页
     * @return
     */
    Page<Chance> findChancesByChanceOfPage(User user, Chance chance, Integer page);

    /**
     * 通过id查询chance
     * @param id
     * @return
     */
    Chance findChanceById(Integer id);

    /**
     * 保存或更新plan
     * @param plan
     */
    void savePlan(Plan plan);

    /**
     * 保存提交的计划
     * @param plan
     */
    void commitPlan(Plan plan);

    /**
     * 通过chanceId查询plan
     * @param chanceId
     * @return
     */
    List<Plan> findByChanceId(Integer chanceId);

    void deletePlanById(Integer id);

    Plan findPlanById(Integer id);
}
