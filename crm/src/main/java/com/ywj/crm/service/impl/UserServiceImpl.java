package com.ywj.crm.service.impl;

import com.ywj.crm.bean.User;
import com.ywj.crm.dao.UserDao;
import com.ywj.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 和User用户相关的Service层逻辑
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public Page<User> findAllUsersOfPage(Integer page) {
        Pageable pageable = PageRequest.of(page, 3);
        return userDao.findAll(pageable);
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.getOne(id);
    }

    @Override
    public Page<User> findAllUserByTypeOfPage(Integer roleId, Integer page) {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("role").get("id"),roleId);
            }
        };
        Pageable pageable = PageRequest.of(page,3);
        return userDao.findAll(spec,pageable);
    }

    @Override
    public void saveOrUpdateUser(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = userDao.getOne(id);
        user.getRole().getUsers().remove(user);
        userDao.deleteById(id);
    }

    @Override
    public List<User> findUserByRoleId(Integer id) {
        return userDao.findUserByRoleId(id);
    }
}
