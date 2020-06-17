package com.ywj.crm.dao;

import com.ywj.crm.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleDao extends JpaRepository<Role,Integer> {
    @Query(value = "select count(*) from role",nativeQuery = true)
    Integer findTotal();
}
