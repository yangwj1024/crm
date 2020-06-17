package com.ywj.crm.service;

import com.ywj.crm.bean.CustomerConstitute;

import java.util.List;

public interface ICustomerConstituteService {
    List<CustomerConstitute> regionAnalyze();
    List<CustomerConstitute> levelAnalyze();
}
