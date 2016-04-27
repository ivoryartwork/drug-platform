package com.drug.platform.controller;

import com.drug.platform.controller.annotation.UserAuthenticate;
import com.drug.platform.controller.annotation.UserType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
}
