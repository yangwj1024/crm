package com.ywj.crm.service.impl;

import com.ywj.crm.bean.Chance;
import com.ywj.crm.bean.User;
import com.ywj.crm.dao.SalesDao;
import com.ywj.crm.service.ISalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements ISalesService {
    @Autowired
    private SalesDao salesDao;
    @Override
    public List<Chance> findAllChances() {
        return salesDao.findAll();
    }

    @Override
    public Page<Chance> findAllChancesOfPage(User user, Integer page) {
        Pageable pageable = PageRequest.of(page,3);
        return salesDao.findByCreator(user,pageable);
    }

    @Override
    public void deleteChanceById(Integer id) {
        salesDao.deleteById(id);
    }

    @Override
    public Chance findChanceById(Integer id) {
        return salesDao.findById(id).get();
    }

    @Override
    public void saveOrUpdateChance(Chance chance) {
        chance.setStatus("待处理");
        salesDao.save(chance);
    }

    @Override
    public Page<Chance> findChancesByChanceOfPage(User user, Chance chance, Integer page) {
        Pageable pageable = PageRequest.of(page,3);
        if(chance==null){
            return salesDao.findByCreator(user,pageable);
        }else {
            if(chance.getAddress()!=null&&chance.getCustomer()==null){
                return salesDao.findByCreatorAndAddress(user,chance.getAddress(),pageable);
            }else if (chance.getCustomer()!=null&&chance.getAddress()==null){
                return salesDao.findByCreatorAndCustomerLike(user,"%"+chance.getCustomer()+"%",pageable);
            }else {
                return salesDao.findByCreatorAndAddressAndCustomerLike(user,chance.getAddress(),"%"+chance.getCustomer()+"%",pageable);
            }
        }
    }


}
