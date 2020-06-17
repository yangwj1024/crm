package com.ywj.crm.web.controller;

import com.ywj.crm.bean.CustomerConstitute;
import com.ywj.crm.service.ICustomerConstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 客户构成分析的Controller
 */
@Controller
public class CustomerConstituteController {
    @Autowired
    private ICustomerConstituteService service;
    @RequestMapping("toCustomerConstitute")
    public String toCustomerConstitute(){
        return "pages/customerConstitute";
    }

    @RequestMapping("regionAnalyze")
    @ResponseBody
    public List<CustomerConstitute> regionAnalyze(){
        return service.regionAnalyze();
    }
    @RequestMapping("levelAnalyze")
    @ResponseBody
    public List<CustomerConstitute> levelAnalyze(){
        return service.levelAnalyze();
    }
}
