/**
 * Created by Yaochao on 2016/4/18.
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
    path('用药指标监控','药费比');
    allThanDrugs();
    initBtn();
})

/**
 * 全院统计
 */
function allThanDrugs() {
    var data = [
        ['药费', 60],
        ['其他', 40],
    ];
    chartHelper.pie('thanDrugsPie', data);
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '药费比',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('thanDrugsColumn', categories, series, "%");
}

/**
 * 科室统计
 */
function departmentThanDrugs() {
    var data = [
        ['药费', 60],
        ['其他', 40],
    ];
    chartHelper.pie('departmentThanDrugsPie', data);
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
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('departmentAllThanDrugsColumn', categories, series, "%");
    var series = [{
        name: '住院',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }, {
        name: '门诊',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('departmentSubThanDrugsColumn', categories, series, "%");
}

/**
 * 病区统计
 */
function wardThanDrugs() {
    var data = [
        ['药费', 60],
        ['其他', 40],
    ];
    chartHelper.pie('wardThanDrugsPie', data);
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '药费比',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('wardThanDrugsColumn', categories, series, "%");
}

function initBtn() {
    $('#back').click(function () {
        back();
    })

    $(".switch-arrow").click(function () {
        if ($(this).hasClass("switch-arrow-left")) {
            $("#departSubTitle").html("总药费比趋势图");
            $("#departmentAllThanDrugsColumn").removeClass("hide");
            $("#departmentSubThanDrugsColumn").addClass("hide");
        } else {
            $("#departSubTitle").html("药费比趋势图（门诊/住院）");
            $("#departmentAllThanDrugsColumn").addClass("hide");
            $("#departmentSubThanDrugsColumn").removeClass("hide");
        }
    })
}

//跳转到科室统计
function switchToDepartment(name) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    $("#panel-1 .title-name").html(name);
    departmentThanDrugs();
}

function switchToWard(name) {
    panel_index = 2;
    $("#panel-1").addClass("hide");
    $("#panel-2").removeClass("hide");
    $("#panel-2 .title-name").html(name);
    wardThanDrugs();
}