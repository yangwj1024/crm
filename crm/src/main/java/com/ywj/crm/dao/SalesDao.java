package com.ywj.crm.dao;

import com.ywj.crm.bean.Chance;
import com.ywj.crm.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesDao extends JpaRepository<Chance,Integer> {
    Page<Chance> findByCreator(User user, Pageable pageable);
    Page<Chance> findByCreatorAndAddress(User user, String address, Pageable pageable);
    Page<Chance> findByCreatorAndCustomerLike(User user, String customer, Pageable pageable);
    Page<Chance> findByCreatorAndAddressAndCustomerLike(User user, String address, String customer, Pageable pageable);
    Page<Chance> findByHandler(User user, Pageable pageable);
    Page<Chance> findByHandlerAndAddress(User user, String address, Pageable pageable);
    Page<Chance> findByHandlerAndCustomerLike(User user, String customer, Pageable pageable);
    Page<Chance> findByHandlerAndAddressAndCustomerLike(User user, String address, String customer, Pageable pageable);
}
