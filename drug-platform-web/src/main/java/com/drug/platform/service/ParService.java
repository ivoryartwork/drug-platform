package com.drug.platform.service;

import com.drug.platform.model.Par;
import com.drug.platform.model.ParByDoctor;

import java.util.List;

/**
 * Created by Yaochao on 2016/5/11.
 */
public interface ParService {

    public void addParBatch(List<Par> pars);

    public void addParByDoctorBatch(List<ParByDoctor> parByDoctors);

    public String getParList(String beginDate, String endDate, int page);

    public String getParDetailList(String visitNo, String rcptNo, String visitDate);

    public String getParByDoctorList(String beginDate, String endDate, int page);
}
