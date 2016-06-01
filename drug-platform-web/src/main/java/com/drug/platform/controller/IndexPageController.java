package com.drug.platform.controller;

import com.drug.platform.controller.annotation.UserAuthenticate;
import com.drug.platform.controller.annotation.UserType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yaochao on 2016/4/18.
 */
@Controller
@RequestMapping
public class IndexPageController {

    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping
    public String mainUI(HttpServletRequest request) {
        return "redirect:index.html";
    }

    /**
     * 主页面
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
//        SessionUser sessionUser = ControllerUtil.getSessionUser(request);
//        if (Assert.notNull(sessionUser)) {
//
//        }
//        return Pages.ERROR._404;
        return Pages.INDEX;
    }

    /**
     * 药费比页面
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/than_drugs")
    public String thanDrugs(HttpServletRequest request) {
        return Pages.MMI.THAN_DRUGS;
    }

    /**
     * 药品用量页面
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/drugs_amount")
    public String drugsAmount(HttpServletRequest request) {
        return Pages.MMI.DRUGS_AMOUNT;
    }

    /**
     * 门诊次均药费
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/drugs_outpatient")
    public String drugsOutpatient(HttpServletRequest request) {
        return Pages.MMI.DRUGS_OUTPATIENT;
    }

    /**
     * 住院次均药费
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/ahe")
    public String ahe(HttpServletRequest request) {
        return Pages.MMI.AHE;
    }

    /**
     * 抗菌药物使用强度
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/aud")
    public String aud(HttpServletRequest request) {
        return Pages.MMI.AUD;
    }

    /**
     * 住院抗菌药物使用率
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/uaa")
    public String uaa(HttpServletRequest request) {
        return Pages.MMI.UAA;
    }

    /**
     * 门诊抗菌药物处方比例
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/poap")
    public String poap(HttpServletRequest request) {
        return Pages.MMI.POAP;
    }

    /**
     * 急诊抗菌药物处方比例
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/paed")
    public String paed(HttpServletRequest request) {
        return Pages.MMI.PAED;
    }

    /**
     * i类切口预防使用抗菌药物比例
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/ipuaa")
    public String ipuaa(HttpServletRequest request) {
        return Pages.MMI.IPUAA;
    }

    /**
     * 目标值设置
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/target_value_set")
    public String targetValueSet(HttpServletRequest request) {
        return Pages.MMI.TARGET_VALUE_SET;
    }

    /**
     * 重点药品监控
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/key_drug_control")
    public String keyDrugControl(HttpServletRequest request) {
        return Pages.KDC.KEY_DRUG_CONTROL;
    }

    /**
     * 取药记录查询
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/drugs_take_records")
    public String drugsTakeRecords(HttpServletRequest request) {
        return Pages.TMPI.DRUGS_TAKE_RECORDS;
    }

    /**
     * 取药记录查询
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/outpatient_inpatient_records")
    public String outpatientInpatientRecords(HttpServletRequest request) {
        return Pages.TMPI.OUTPATIENT_INPATINET_RECORDS;
    }

    /**
     * 单品种处方查询
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/single_variety_prescription")
    public String singleVarietyPrescription(HttpServletRequest request) {
        return Pages.TMPI.SINGLE_VARIETY_PRESCRIPTION;
    }

    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/spdr")
    public String spdr(HttpServletRequest request) {
        return Pages.MR.SPDR;
    }

    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/par")
    public String par(HttpServletRequest request) {
        return Pages.MR.PAR;
    }

    /**
     * 临床应用统计
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/cas")
    public String cas(HttpServletRequest request) {
        return Pages.SDM.CAS;
    }

    /**
     * 在院用量统计
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/ihus")
    public String ihus(HttpServletRequest request) {
        return Pages.SDM.IHUS;
    }

    /**
     * 单病人用量统计
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/spta")
    public String spta(HttpServletRequest request) {
        return Pages.SDM.SPTA;
    }

    /**
     * 用户权限管理
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/urm")
    public String urm(HttpServletRequest request) {
        return Pages.URM.URM;
    }

    /**
     * 用户日志管理
     *
     * @param request
     * @return
     */
    @UserAuthenticate({UserType.ADMIN})
    @RequestMapping("/uolm")
    public String uolm(HttpServletRequest request) {
        return Pages.URM.UOLM;
    }

    /**
     * 科室药费比月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/than_drugs_report")
    public String than_drugs_report(HttpServletRequest request) {
        return Pages.MREPORT.THAN_DRUGS_REPORT;
    }

    /**
     * 科室门诊次均药费月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/drugs_outpatient_report")
    public String drugs_outpatient_report(HttpServletRequest request) {
        return Pages.MREPORT.DRUGS_OUTPATIENT_REPORT;
    }

    /**
     * 病区住院次均药费月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/ahe_report")
    public String ahe_report(HttpServletRequest request) {
        return Pages.MREPORT.AHE_REPORT;
    }

    /**
     * 病区抗菌药物使用强度月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/aud_report")
    public String aud_report(HttpServletRequest request) {
        return Pages.MREPORT.AUD_REPORT;
    }

    /**
     * 病区住院抗菌药物使用率月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/uaa_report")
    public String uaa_report(HttpServletRequest request) {
        return Pages.MREPORT.UAA_REPORT;
    }

    /**
     * 科室门诊抗菌药物处方比例月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/poap_report")
    public String poap_report(HttpServletRequest request) {
        return Pages.MREPORT.POAP_REPORT;
    }

    /**
     * 急诊抗菌药物处方比例月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/paed_report")
    public String paed_report(HttpServletRequest request) {
        return Pages.MREPORT.PAED_REPORT;
    }

    /**
     * i类切口预防使用抗菌药物比例月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/ipuaa_report")
    public String ipuaa_report(HttpServletRequest request) {
        return Pages.MREPORT.IPUAA_REPORT;
    }

    /**
     * 不合理用药记录月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/idr_report")
    public String idr_report(HttpServletRequest request) {
        return Pages.MREPORT.IDR_REPORT;
    }

    /**
     * 药品用量统计月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/dus_report")
    public String dus_report(HttpServletRequest request) {
        return Pages.MREPORT.DUS_REPORT;
    }

    /**
     * 联合用药统计月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/sc_report")
    public String sc_report(HttpServletRequest request) {
        return Pages.MREPORT.SC_REPORT;
    }
}
