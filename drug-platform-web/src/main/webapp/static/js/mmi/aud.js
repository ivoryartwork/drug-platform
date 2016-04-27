/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;
var panel_index_1 = 6;
$(".thandrugs-pagination").twbsPagination({
    totalPages: 10,
    visiblePages: 7,
    first: '首页',
    prev: '上一页',
    next: '下一页',
    last: '末页',
    initiateStartPageClick: false,
    onPageClick: function (event, page) {
    }
});
$(function () {
    path('用药指标监控', '抗菌药物使用强度');
    initBtn();
})

/**
 *
 * @param name
 */
function singleSwitchToAll() {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    $("#panel-2").addClass("hide");
    $("#panel-3").addClass("hide");
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '使用强度',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('singleAudColumn', categories, series, "DDD");
}

function singleSwitchToDepartment() {
    panel_index = 2;
    $("#panel-0").addClass("hide");
    $("#panel-2").removeClass("hide");
    $("#panel-1").addClass("hide");
    $("#panel-3").addClass("hide");
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '使用强度',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('departmentSingleAudColumn', categories, series, "DDD");
}

function singleSwitchToWard() {
    panel_index = 3;
    $("#panel-0").addClass("hide");
    $("#panel-3").removeClass("hide");
    $("#panel-1").addClass("hide");
    $("#panel-2").addClass("hide");
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '使用强度',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('wardSingleAudColumn', categories, series, "DDD");
}

/**
 *
 * @param name
 */
function switchToDepartment(name) {
    panel_index_1 = 7;
    $("#panel-6").addClass("hide");
    $("#panel-8").addClass("hide");
    $("#panel-7").removeClass("hide");
    departmentAud();
}

function switchToWard() {
    panel_index_1 = 8;
    $("#panel-6").addClass("hide");
    $("#panel-7").addClass("hide");
    $("#panel-8").removeClass("hide");
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '使用强度',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('wardAudColumn', categories, series, "DDD");
}

function initBtn() {
    $('#back-0').click(function () {
        back();
    })

    $('#back-1').click(function () {
        back1();
    })
}

function back1() {
    if (panel_index_1 <= 6) {
        return;
    } else {
        $("#panel-" + panel_index_1).addClass("hide");
        panel_index_1 = panel_index_1 - 1;
        $("#panel-" + panel_index_1).removeClass("hide");
    }
}
function departmentAud() {
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '使用强度',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('departmentAudColumn', categories, series, "DDD");
}