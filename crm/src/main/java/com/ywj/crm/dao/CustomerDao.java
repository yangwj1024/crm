package com.ywj.crm.dao;

import com.ywj.crm.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerDao extends JpaRepository<Customer,Integer> {
    //客户管理相关内容

    //数据分析相关内容
    @Query(value = "select count(1) from customer",nativeQuery = true)
    Integer findCustomerSize();
    @Query(value = "select count(1) from customer where region =?1",nativeQuery = true)
    Integer findCustomerSizeOfAddress(String addr);
    @Query(value = "select count(1) from customer where level=?1",nativeQuery = true)
    Integer findCustomerSizeOfLevel(String level);
}
