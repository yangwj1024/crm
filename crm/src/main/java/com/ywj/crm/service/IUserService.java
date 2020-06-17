package com.ywj.crm.service;

import com.ywj.crm.bean.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    /**
     * 通过name查询user
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 查询第几页的所有用户
     * @param page
     * @return
     */
    Page<User> findAllUsersOfPage(Integer page);

    /**
     * 通过id查询用户
     */
    User findUserById(Integer id);

    /**
     * 通过用户类型查询第几页的用户
     * @param roleId 用户类型 如：高管，经理等
     * @param page 查询第几页
     * @return
     */
    Page<User> findAllUserByTypeOfPage(Integer roleId, Integer page);

    /**
     * 保存user
     * @param user
     */
    void saveOrUpdateUser(User user);

    void deleteUserById(Integer id);

    List<User> findUserByRoleId(Integer id);
}
