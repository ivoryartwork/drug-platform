package com.drug.platform.dao;

import com.drug.platform.model.Par;

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
}
