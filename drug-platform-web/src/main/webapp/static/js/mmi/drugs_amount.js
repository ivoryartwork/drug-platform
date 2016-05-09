/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;
$(function () {
    path('用药指标监控', '药品用量');
    initBtn();
})

function initBtn() {
    $('#back-0').click(function () {
        back();
    })
}

/**
 * 所有药品用量
 */
function singleAllDrugsAmountList() {

}

function singleSwitchToAll(drugCode, drugName) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-2").addClass("hide");
    $("#panel-3").addClass("hide");
    $("#panel-1").removeClass("hide");
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '全部',
        data: [1, 2, 3, 4, 5, 6],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('singleDrugAmountColumn', categories, series, "%");
}

function singleSwitchToDepartment() {
    panel_index = 2;
    $("#panel-0").addClass("hide");
    $("#panel-1").addClass("hide");
    $("#panel-3").addClass("hide");
    $("#panel-2").removeClass("hide");
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '全部',
        data: [1, 2, 3, 4, 5, 6],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('singleDepartmentDrugAmountColumn', categories, series, "%");
}

function singleSwitchToWard() {
    panel_index = 3;
    $("#panel-0").addClass("hide");
    $("#panel-1").addClass("hide");
    $("#panel-2").addClass("hide");
    $("#panel-3").removeClass("hide");
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '全部',
        data: [1, 2, 3, 4, 5, 6],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('singleWardDrugAmountColumn', categories, series, "%");
}

