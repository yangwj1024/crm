package com.ywj.crm.dao;

import com.ywj.crm.bean.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanDao extends JpaRepository<Plan,Integer> {
    List<Plan> findByChanceId(Integer id);
}
