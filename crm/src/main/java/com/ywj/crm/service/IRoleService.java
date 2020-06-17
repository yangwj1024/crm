package com.ywj.crm.service;

import com.ywj.crm.bean.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRoleService {
    /**
     * 查询第一页角色
     * @return 所有
     */
    List<Role> findAllRoles();

    //查询指定页上的数据信息
    Page<Role> findAllRoles(Integer pageIndex);

    Role findRoleById(Integer id);

    //保存或修改
    void saveOrUpdateRole(Role role);

    //通过id删除
    void deleteRole(Integer id);

    //查询记录总条数
    Integer findTotal();

}
