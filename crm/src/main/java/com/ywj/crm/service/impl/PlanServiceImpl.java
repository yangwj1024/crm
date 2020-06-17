package com.ywj.crm.service.impl;

import com.ywj.crm.bean.Chance;
import com.ywj.crm.bean.Plan;
import com.ywj.crm.bean.User;
import com.ywj.crm.dao.PlanDao;
import com.ywj.crm.dao.SalesDao;
import com.ywj.crm.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements IPlanService {
    @Autowired
    private SalesDao salesDao;
    @Autowired
    private PlanDao planDao;
    @Override
    public Page<Chance> findAllChances(User user, Integer page) {
        Pageable pageable = PageRequest.of(page,3);
        return salesDao.findByHandler(user,pageable);
    }

    @Override
    public Page<Chance> findChancesByChanceOfPage(User user, Chance chance, Integer page) {
        Pageable pageable = PageRequest.of(page,3);
        if(chance==null){
            return salesDao.findByHandler(user,pageable);
        }else {
            if(chance.getAddress()!=null&&chance.getCustomer()==null){
                return salesDao.findByHandlerAndAddress(user,chance.getAddress(),pageable);
            }else if (chance.getCustomer()!=null&&chance.getAddress()==null){
                return salesDao.findByHandlerAndCustomerLike(user,"%"+chance.getCustomer()+"%",pageable);
            }else {
                return salesDao.findByHandlerAndAddressAndCustomerLike(user,chance.getAddress(),"%"+chance.getCustomer()+"%",pageable);
            }
        }
    }

    @Override
    public Chance findChanceById(Integer id) {
        return salesDao.findById(id).get();
    }

    @Override
    public void savePlan(Plan plan) {
        planDao.save(plan);
    }

    @Override
    public void commitPlan(Plan plan) {
        planDao.save(plan);
        Chance chance = salesDao.findById(plan.getChance().getId()).get();
        chance.setStatus("处理中");
        salesDao.save(chance);
    }

    @Override
    public List<Plan> findByChanceId(Integer chanceId) {
        return planDao.findByChanceId(chanceId);
    }

    @Override
    public void deletePlanById(Integer id) {
        planDao.deleteById(id);
    }

    @Override
    public Plan findPlanById(Integer id) {
        return planDao.findById(id).get();
    }
}
