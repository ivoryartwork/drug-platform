/**
 * Created by Yaochao on 2016/4/28.
 */
var panel_index = 0;
$(function () {
    path("特殊管理药品监控", "在院用量统计");
    initBtn();
})

function initBtn() {
    $('#back').click(function () {
        back();
    })
}

function switchDetail() {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
}