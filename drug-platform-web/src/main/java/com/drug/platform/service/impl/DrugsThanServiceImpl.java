package com.drug.platform.service.impl;

import com.drug.platform.dao.DrugsThanDAO;
import com.drug.platform.model.DrugsThan;
import com.drug.platform.service.DrugsThanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/3.
 */
@Service("drugsThanService")
public class DrugsThanServiceImpl implements DrugsThanService {

    @Resource
    private DrugsThanDAO drugsThanDAO;

    /**
     * 批量插入药费比
     *
     * @param drugsThans
     */
    @Override
    public void addDrugsThanBatch(List<DrugsThan> drugsThans) {
        drugsThanDAO.save(drugsThans);
    }
}
