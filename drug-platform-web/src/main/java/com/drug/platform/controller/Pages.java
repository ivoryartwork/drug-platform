package com.drug.platform.controller;

/**
 * Created by Yaochao on 2016/3/3.
 */
public class Pages {

    public static final String LOGIN = "login";

    public static final String REDIRECT_LOGIN = "redirect:/login.html";

    public static final String INDEX = "indexPage";

    /**
     * 用药指标监控页面集合
     */
    public class MMI {

        //药费比
        public static final String THAN_DRUGS = "mmi.than_drugs.page";

        //药品用量
        public static final String DRUGS_AMOUNT = "mmi.drugs_amount.page";

        //门诊次均药费
        public static final String DRUGS_OUTPATIENT = "mmi.drugs_outpatient.page";

        //住院次均药费
        public static final String AHE = "mmi.ahe.page";

        //抗菌药物使用强度
        public static final String AUD = "mmi.aud.page";

        //住院抗菌药物使用率
        public static final String UAA = "mmi.uaa.page";

        //门诊抗菌药物处方比例
        public static final String POAP = "mmi.poap.page";

        //急诊抗菌药物处方比例
        public static final String PAED = "mmi.paed.page";

        //i类切口预防使用抗菌药物比例
        public static final String IPUAA = "mmi.ipuaa.page";

        //m目标值设置
        public static final String TARGET_VALUE_SET = "mmi.target_value_set.page";
    }

    /**
     * 重点药品监控
     */
    public class KDC {
        //重点药品监控
        public static final String KEY_DRUG_CONTROL = "kdc.key_drug_control.page";
    }

    /**
     * 取药及处方查询
     */
    public class TMPI {

        //取药记录查询
        public static final String DRUGS_TAKE_RECORDS = "tmpi.drugs_take_records.page";

        //门诊病人住院记录
        public static final String OUTPATIENT_INPATINET_RECORDS = "tmpi.outpatient_inpatient_records.page";

        //单品种处方查询
        public static final String SINGLE_VARIETY_PRESCRIPTION = "tmpi.single_variety_prescription.page";

    }

    public class ERROR {

        public static final String _404 = "404";

        public static final String AUTHENTICATION_FAILED = "authentication_failed";
    }
}
