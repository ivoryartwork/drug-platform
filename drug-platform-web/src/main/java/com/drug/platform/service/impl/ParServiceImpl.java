package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.ParDAO;
import com.drug.platform.job.calculate.CalculatePar;
import com.drug.platform.model.Par;
import com.drug.platform.model.ParByDoctor;
import com.drug.platform.service.ParService;
import com.drug.platform.utils.DateFormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/5/11.
 */
@Service("parService")
public class ParServiceImpl implements ParService {

    @Resource
    private ParDAO parDAO;

    @Override
    public void addParBatch(List<Par> pars) {
        parDAO.save(pars);
    }

    @Override
    public void addParByDoctorBatch(List<ParByDoctor> parByDoctors) {
        parDAO.saveParByDoctor(parByDoctors);
    }

    @Override
    public String getParList(String beginDate, String endDate, int page) {
        try {
            Date begin = DateFormatUtils.parse(beginDate + " 00:00:00", DateFormatUtils.FORMAT_TIMESTAMP);
            Date end = DateFormatUtils.parse(endDate + " 23:59:59", DateFormatUtils.FORMAT_TIMESTAMP);
            PageHelper.startPage(page, 15);
            List<Par> pars = parDAO.getParListByTimeRange(begin, end);
            int offset = (page - 1) * 15 + 1;
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < pars.size(); i++) {
                JSONObject object = new JSONObject();
                Par par = pars.get(i);
                object.put("num", offset + i);
                object.put("visitDate", DateFormatUtils.format(par.getVisitDate(), DateFormatUtils.FORMAT_DATE));
                object.put("visitNo", par.getVisitNo());
                object.put("rcptNo", par.getRcptNo());
                object.put("deptName", par.getDeptName());
                object.put("doctor", par.getDoctor());
                object.put("patientName", par.getPatientName());
                object.put("total", par.getTotal());
                jsonArray.add(object);
            }
            JSONObject result = new JSONObject();
            result.put("totalPages", ((Page) pars).getPages());
            result.put("pageData", jsonArray);
            return result.toJSONString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getParDetailList(String visitNo, String rcptNo, String visitDate) {
        List<Map<String, Object>> mapList = CalculatePar.calculatePresDetail(visitNo, rcptNo, visitDate);
        JSONArray result = new JSONArray();
        for (Map<String, Object> map : mapList) {
            JSONObject object = new JSONObject();
            object.put("visitDate", DateFormatUtils.format((Date) map.get("visitDate"), DateFormatUtils.FORMAT_TIMESTAMP));
            object.put("num", map.get("num"));
            object.put("drugCode", map.get("drugCode"));
            object.put("drugName", map.get("drugName"));
            object.put("drugSpec", map.get("drugSpec"));
            object.put("units", map.get("units"));
            object.put("amount", map.get("amount"));
            object.put("costs", map.get("costs"));
            result.add(object);
        }
        return result.toJSONString();
    }

    @Override
    public String getParByDoctorList(String beginDate, String endDate, int page) {
        try {
            Date begin = DateFormatUtils.parse(beginDate + " 00:00:00", DateFormatUtils.FORMAT_TIMESTAMP);
            Date end = DateFormatUtils.parse(endDate + " 23:59:59", DateFormatUtils.FORMAT_TIMESTAMP);
            PageHelper.startPage(page, 15);
            List<ParByDoctor> parByDoctors = parDAO.getParByDoctorListByTimeRange(begin, end);
            int offset = (page - 1) * 15 + 1;
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < parByDoctors.size(); i++) {
                JSONObject object = new JSONObject();
                ParByDoctor parByDoctor = parByDoctors.get(i);
                object.put("num", offset + i);
                object.put("deptName", parByDoctor.getDeptName());
                object.put("doctor", parByDoctor.getDoctor());
                object.put("total", parByDoctor.getTotal());
                jsonArray.add(object);
            }
            JSONObject result = new JSONObject();
            result.put("totalPages", ((Page) parByDoctors).getPages());
            result.put("pageData", jsonArray);
            return result.toJSONString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
