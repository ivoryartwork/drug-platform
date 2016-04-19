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
    path('用药指标监控', '门诊次均药费');
    allDrugsOutpatient();
    initBtn();
})

/**
 * 全院统计
 */
function allDrugsOutpatient() {
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '次均药费',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('drugsOutpatientColumn', categories, series, "元");
}

function initBtn() {
    $('#back').click(function () {
        back();
    })
}

//跳转到科室统计
function switchToDepartment(name) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    $("#panel-1 .title-name").html(name);
    departmentDrugsOutpatient();
}

/**
 * 科室统计
 */
function departmentDrugsOutpatient() {
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '肾病科次均药费',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('departmentDrugsOutPatientColumn', categories, series, "%");
}