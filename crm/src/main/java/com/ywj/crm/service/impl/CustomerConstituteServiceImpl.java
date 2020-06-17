package com.ywj.crm.service.impl;

import com.ywj.crm.bean.CustomerConstitute;
import com.ywj.crm.dao.CustomerDao;
import com.ywj.crm.service.ICustomerConstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerConstituteServiceImpl implements ICustomerConstituteService {
    @Autowired
    private CustomerDao customerDao;
    @Override
    public List<CustomerConstitute> regionAnalyze() {
        List<CustomerConstitute> list = new ArrayList<>();
        float nums = customerDao.findCustomerSize();
        String[] regions = {"华中","华南","华东","华北"};
        for (String reginon:regions){
            float num = customerDao.findCustomerSizeOfAddress(reginon);
            float rate = num/nums*100;
            CustomerConstitute cc = new CustomerConstitute(reginon,rate);
            list.add(cc);
        }
        return list;
    }

    @Override
    public List<CustomerConstitute> levelAnalyze() {
        List<CustomerConstitute> list = new ArrayList<>();
        float nums = customerDao.findCustomerSize();
        String[] regions = {"普通客户","大客户","重点开发客户","合作伙伴","战略合作伙伴"};
        for (String reginon:regions){
            float num = customerDao.findCustomerSizeOfLevel(reginon);
            float rate = num/nums*100;
            CustomerConstitute cc = new CustomerConstitute(reginon,rate);
            list.add(cc);
        }
        return list;
    }
}
