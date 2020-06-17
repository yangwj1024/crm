package com.ywj.crm.service;

import com.ywj.crm.bean.Chance;
import com.ywj.crm.bean.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISalesService {
    /**
     * 查询所有商机
     * @return
     */
    List<Chance> findAllChances();

    /**
     * 分页查询所有商机
     * @param page 第几页
     * @return
     */
    Page<Chance> findAllChancesOfPage(User user, Integer page);

    /**
     * 通过id删除chance
     * @param id
     */
    void deleteChanceById(Integer id);

    /**
     * 通过id查询chance
     * @param id
     * @return
     */
    Chance findChanceById(Integer id);

    /**
     * 保存或更新chance
     * @param chance
     */
    void saveOrUpdateChance(Chance chance);

    /**
     * 按条件分页查询chance
     * @param chance
     * @param page
     * @return
     */
    Page<Chance> findChancesByChanceOfPage(User user, Chance chance, Integer page);


}
