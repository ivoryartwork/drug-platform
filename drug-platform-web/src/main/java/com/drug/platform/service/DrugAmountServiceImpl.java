package com.drug.platform.service;

import com.drug.platform.dao.DrugAmountDAO;
import com.drug.platform.model.DrugAmountDept;
import com.drug.platform.model.DrugAmountDoctor;
import com.drug.platform.model.DrugAmountGlobal;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/5.
 */
@Service("drugAmountService")
public class DrugAmountServiceImpl implements DrugAmountService {

    @Resource
    private DrugAmountDAO drugAmountDAO;

    /**
     * 保存全局统计结果
     *
     * @param drugAmountGlobals
     */
    @Override
    public void addDrugAmountGlobalBatch(List<DrugAmountGlobal> drugAmountGlobals) {
        drugAmountDAO.saveGlobal(drugAmountGlobals);
    }

    /**
     * 保存科室统计结果
     *
     * @param drugAmountDepts
     */
    @Override
    public void addDrugAmountDeptBatch(List<DrugAmountDept> drugAmountDepts) {
        drugAmountDAO.saveDept(drugAmountDepts);
    }

    /**
     * 保存医生统计结果
     *
     * @param drugAmountDoctors
     */
    @Override
    public void addDrugAmountDoctorBatch(List<DrugAmountDoctor> drugAmountDoctors) {
        drugAmountDAO.saveDoctor(drugAmountDoctors);
    }
}
