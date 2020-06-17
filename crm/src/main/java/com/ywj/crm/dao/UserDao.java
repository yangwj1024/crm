package com.ywj.crm.dao;

import com.ywj.crm.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    User findByName(String name);
    @Query(value = "select * from user where role_id = ?1",nativeQuery = true)
    List<User> findUserByRoleId(Integer id);
}
