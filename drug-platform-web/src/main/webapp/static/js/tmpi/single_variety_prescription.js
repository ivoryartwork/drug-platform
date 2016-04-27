/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;
$(function () {
    path('用药指标监控', '单品种处方查询');
    initBtn();
})

function initBtn() {
    $('#back').click(function () {
        back();
    })
}

function viewPrescription(prescriptionId) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
}