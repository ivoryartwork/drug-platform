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

    public class MR {
        //单品种药品用量排名
        public static final String SPDR = "mr.spdr.page";

        //处方金额排名
        public static final String PAR = "mr.par.page";
    }

    public class SDM {
        //临床应用统计
        public static final String CAS = "sdm.cas.page";

        //在院用量统计
        public static final String IHUS = "sdm.ihus.page";

        //单病人用量统计
        public static final String SPTA = "sdm.spta.page";
    }

    public class URM {
        public static final String URM = "urm.urm.page";

        public static final String UOLM = "urm.uolm.page";
    }

    public class ERROR {

        public static final String _404 = "404";

        public static final String AUTHENTICATION_FAILED = "authentication_failed";
    }

    /**
     * 月报表
     */
    public class MREPORT {

        public static final String THAN_DRUGS_REPORT = "mReport.than_drugs_report.page";

        public static final String DRUGS_OUTPATIENT_REPORT = "mReport.drugs_outpatient_report.page";

        public static final String AHE_REPORT = "mReport.ahe_report.page";

        public static final String AUD_REPORT = "mReport.aud_report.page";

        public static final String UAA_REPORT = "mReport.uaa_report.page";

        public static final String POAP_REPORT = "mReport.poap_report.page";

        public static final String PAED_REPORT = "mReport.paed_report.page";

        public static final String IPUAA_REPORT = "mReport.ipuaa_report.page";

        public static final String IDR_REPORT = "mReport.idr_report.page";

        public static final String DUS_REPORT = "mReport.dus_report.page";

        public static final String SC_REPORT = "mReport.sc_report.page";
    }
}
