package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.KeyDrugControllDAO;
import com.drug.platform.model.SimilarKeyDrug;
import com.drug.platform.service.KeyDrugService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/30.
 */
@Service("keyDrugService")
public class KeyDrugServiceImpl implements KeyDrugService {

    @Resource
    private KeyDrugControllDAO keyDrugControllDAO;

    /**
     * 添加重点药品监控
     *
     * @param keyDrugData
     */
    @Override
    public void addKeyDrug(String keyDrugData) throws Exception {
        JSONObject object = JSONObject.parseObject(keyDrugData);
        KeyDrug keyDrug = new KeyDrug();
        keyDrug.setDrugName(object.getString("drugName1"));
        keyDrug.setDrugCode(object.getString("drugCode1"));
        keyDrug.setDrugSpec(object.getString("drugSpec1"));
        keyDrug.setType(object.getString("type"));
        keyDrug.setSingleDose(object.getString("singleDose"));
        keyDrug.setRouteOfAdmin(object.getString("routeOfAdmin"));
        keyDrug.setFrequency(object.getString("frequency"));
        keyDrug.setDose(object.getString("dose"));
        keyDrugControllDAO.saveKeyDrug(keyDrug);

        JSONArray similarDrugs = object.getJSONArray("similarDrugs");
        if (similarDrugs.size() == 0) {
            return;
        }
        List<SimilarKeyDrug> similarKeyDrugList = new ArrayList<>();
        for (int i = 0; i < similarDrugs.size(); i++) {
            JSONObject similarKeyDrugData = similarDrugs.getJSONObject(i);
            SimilarKeyDrug similarKeyDrug = new SimilarKeyDrug();
            similarKeyDrug.setDrugName1(keyDrug.getDrugName());
            similarKeyDrug.setDrugSpec1(keyDrug.getDrugSpec());
            similarKeyDrug.setDrugCode1(keyDrug.getDrugCode());
            similarKeyDrug.setDrugName2(similarKeyDrugData.getString("drugName"));
            similarKeyDrug.setDrugCode2(similarKeyDrugData.getString("drugCode"));
            similarKeyDrug.setDrugSpec2(similarKeyDrugData.getString("drugSpec"));
            similarKeyDrugList.add(similarKeyDrug);
        }
        keyDrugControllDAO.saveSimilarKeyDrug(similarKeyDrugList);
    }

    /**
     * 获取药品列表
     *
     * @param type
     * @param pageNum
     * @return
     */
    @Override
    public String getKeyDrugStaInfo(String type, int pageNum) {
        PageHelper.startPage(pageNum, 15);
        List<KeyDrug> keyDrugs = keyDrugControllDAO.getKeyDrugList(type);

        int offset = (pageNum - 1) * 15 + 1;
        if (offset < 1) {
            offset = 1;
        }
        JSONObject result = new JSONObject();
        JSONArray list = new JSONArray();
        for (int i = 0; i < keyDrugs.size(); i++) {
            KeyDrug keyDrug = keyDrugs.get(i);
            JSONObject object = new JSONObject();
            object.put("num", offset + i);
            object.put("drugCode", keyDrug.getDrugCode());
            object.put("drugName", keyDrug.getDrugName());
            object.put("routeOfAdmin", keyDrug.getRouteOfAdmin());
            object.put("singleDose", keyDrug.getSingleDose());
            object.put("frequency", keyDrug.getFrequency());
            object.put("drugSpec", keyDrug.getDrugSpec());
            object.put("up", 250);  //不合理处方数
            object.put("idu", 333);  //不合理用药人数
            list.add(object);
        }
        result.put("totalPages", ((Page) keyDrugs).getPages());
        result.put("pageData", list);
        return result.toJSONString();
    }

    /**
     * 删除重点药品
     *
     * @param drugCode
     */
    @Override
    public void deleteKeyDrug(String drugCode) throws Exception {
        keyDrugControllDAO.deleteKeyDrug(drugCode);
        keyDrugControllDAO.deleteSimilarKeyDrug(drugCode);
    }

    /**
     * 根据名称搜索重点药品统计信息
     *
     * @param drugName
     * @return
     */
    @Override
    public String getKeyDrugStaInfoByName(String drugName) {
        List<KeyDrug> keyDrugs = keyDrugControllDAO.getKeyDrugListByName(drugName);
        JSONArray list = new JSONArray();
        for (int i = 0; i < keyDrugs.size(); i++) {
            KeyDrug keyDrug = keyDrugs.get(i);
            JSONObject object = new JSONObject();
            object.put("num", i + 1);
            object.put("drugCode", keyDrug.getDrugCode());
            object.put("drugName", keyDrug.getDrugName());
            object.put("routeOfAdmin", keyDrug.getRouteOfAdmin());
            object.put("singleDose", keyDrug.getSingleDose());
            object.put("frequency", keyDrug.getFrequency());
            object.put("drugSpec", keyDrug.getDrugSpec());
            object.put("up", 250);  //不合理处方数
            object.put("idu", 333);  //不合理用药人数
            list.add(object);
        }
        return list.toJSONString();
    }

    /**
     * 获取同类药品
     *
     * @param drugCode
     * @return
     */
    @Override
    public String recommendSimilarKeyDrugList(String drugCode) {
        List<SimilarKeyDrug> similarKeyDrugs = keyDrugControllDAO.recommendSimilarKeyDrugList(drugCode);
        JSONArray result = new JSONArray();
        for (SimilarKeyDrug similarKeyDrug : similarKeyDrugs) {
            JSONObject object = new JSONObject();
            object.put("drugName", similarKeyDrug.getDrugName1());
            object.put("drugCode", similarKeyDrug.getDrugName1());
            object.put("drugSpec", similarKeyDrug.getDrugSpec1());
            result.add(object);
        }
        return result.toJSONString();
    }
}
