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
    path('用药指标监控', '急诊抗菌药物处方比例');
    initBtn();
    allPaed();
})

function initBtn() {
    $('#back').click(function () {
        back();
    })
}

function allPaed() {
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '急诊抗菌药物处方比例',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('paedColumn', categories, series, "%");
}

function switchToDepartment(name) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    $("#panel-1 .title-name").html(name);
    departmentPaed();
}

function departmentPaed() {
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: '急诊抗菌药物处方比例',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('departmentPaedColumn', categories, series, "%");
}