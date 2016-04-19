/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;

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
    path('用药指标监控', '住院抗菌药物使用率');
    allUaa();
    initBtn();
})

function initBtn() {
    $('#back').click(function () {
        back();
    })
}

function allUaa() {
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '抗菌药物使用率',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('uaaColumn', categories, series, "%");
}

function switchToDepartment(name) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    $("#panel-1 .title-name").html(name);
    departmentUaa();
}

function departmentUaa() {
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '抗菌药物使用率',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('departmentUaaColumn', categories, series, "%");
}

function switchToWard(name) {
    panel_index = 2;
    $("#panel-1").addClass("hide");
    $("#panel-2").removeClass("hide");
    $("#panel-2 .title-name").html(name);
    wardUaa();
}

function wardUaa() {
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '抗菌药物使用率',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('wardUaaColumn', categories, series, "%");
}