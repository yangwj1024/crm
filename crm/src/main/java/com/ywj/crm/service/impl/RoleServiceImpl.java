package com.ywj.crm.service.impl;

import com.ywj.crm.bean.Role;
import com.ywj.crm.dao.RoleDao;
import com.ywj.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAll();
    }

    @Override
    public Page<Role> findAllRoles(Integer pageIndex) {
        return roleDao.findAll(PageRequest.of(pageIndex,5));
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleDao.getOne(id);
    }

    @Override
    public void saveOrUpdateRole(Role role) {
                roleDao.save(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleDao.deleteById(id);
    }

    @Override
    public Integer findTotal() {
        return roleDao.findTotal();
    }
}
