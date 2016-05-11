package com.drug.platform.dao;

import com.drug.platform.model.Par;
import com.drug.platform.model.ParByDoctor;

import java.util.Date;
import java.util.List;

/**
 * Created by ivoryartwork on 2016/5/10.
 */
public interface ParDAO {

    /**
     * 批量保存
     *
     * @param pars
     */
    public void save(List<Par> pars);

    /**
     * 批量保存医生处方
     *
     * @param parByDoctors
     */
    public void saveParByDoctor(List<ParByDoctor> parByDoctors);

    /**
     * 根据时间范围获取处方金额排名
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<Par> getParListByTimeRange(Date beginDate, Date endDate);

    /**
     * 获取医生处方
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<ParByDoctor> getParByDoctorListByTimeRange(Date beginDate, Date endDate);
}
