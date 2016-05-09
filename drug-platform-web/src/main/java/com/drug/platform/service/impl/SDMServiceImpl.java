package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.SDMDAO;
import com.drug.platform.service.SDMService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/5/9.
 */
@Service("sdmService")
public class SDMServiceImpl implements SDMService {

    @Resource
    private SDMDAO sdmdao;

    private int pageSize = 15;

    /**
     * 全院药品总量
     *
     * @param page 当前页
     * @return
     */
    @Override
    public String drugStockInHospital(int page) {
        PageHelper.startPage(page, pageSize);
        List<Map<String, Object>> list = sdmdao.drugStockInHospital();
        JSONArray drugStockList = new JSONArray();
        int offset = pageSize * (page - 1) + 1;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> drugStock = list.get(i);
            JSONObject drugStockJson = new JSONObject();
            drugStockJson.put("num", i + offset);
            drugStockJson.put("units", drugStock.get("UNITS"));
            drugStockJson.put("firmId", drugStock.get("FIRM_ID"));
            drugStockJson.put("totalQuantity", drugStock.get("TOTAL_QUANTITY"));
            drugStockJson.put("drugCode", drugStock.get("DRUG_CODE"));
            drugStockJson.put("drugSpec", drugStock.get("DRUG_SPEC"));
            drugStockJson.put("drugName", drugStock.get("DRUG_NAME"));
            drugStockList.add(drugStockJson);
        }
        JSONObject result = new JSONObject();
        result.put("totalPages", ((Page) list).getPages());
        result.put("pageData", drugStockList);
        return result.toJSONString();
    }

    /**
     * @param drugCode
     * @param drugName
     * @param drugSpec
     * @param firmId
     * @return
     */
    @Override
    public String drugStockInHospitalDetail(String drugCode, String drugName, String drugSpec, String firmId) {
        List<Map<String, Object>> mapList = sdmdao.drugStockInHospitalDetail(drugCode, drugName, drugSpec, firmId);
        JSONArray result = new JSONArray();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> detail = mapList.get(0);
            JSONObject object = new JSONObject();
            object.put("num", i + 1);
            object.put("STORAGE_NAME", detail.get("STORAGE_NAME"));
            object.put("DRUG_CODE", detail.get("DRUG_CODE"));
            object.put("DRUG_NAME", detail.get("DRUG_NAME"));
            object.put("DRUG_SPEC", detail.get("DRUG_SPEC"));
            object.put("UNITS", detail.get("UNITS"));
            object.put("FIRM_ID", detail.get("FIRM_ID"));
            object.put("BATCH_NO", detail.get("BATCH_NO"));
            object.put("TOTAL_QUANTITY", detail.get("TOTAL_QUANTITY"));
            object.put("SUPPLY_INDICATOR", detail.get("SUPPLY_INDICATOR"));
            result.add(object);
        }
        return result.toJSONString();
    }
}
