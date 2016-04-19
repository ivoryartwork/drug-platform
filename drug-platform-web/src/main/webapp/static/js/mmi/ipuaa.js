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
    path('用药指标监控', 'i类切口预防使用抗菌药物比例');
    allIpuaa();
    initBtn();
})

function initBtn() {
    $('#back').click(function () {
        back();
    })
}

function allIpuaa() {
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: 'i类切口预防使用抗菌药物比例',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('ipuaaColumn', categories, series, "%");
}

function switchToDepartment(name) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    $("#panel-1 .title-name").html(name);
    departmentIpuaa();
}

function departmentIpuaa() {
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: 'i类切口预防使用抗菌药物比例',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('departmentIpuaaColumn', categories, series, "%");
}

function switchToWard(name) {
    panel_index = 2;
    $("#panel-1").addClass("hide");
    $("#panel-2").removeClass("hide");
    $("#panel-2 .title-name").html(name);
    wardIpuaa();
}

function wardIpuaa() {
    var categories = [
        '4月',
        '5月',
        '6月',
        '7月',
        '8月',
        '9月'
    ]
    var series = [{
        name: 'i类切口预防使用抗菌药物比例',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('wardIpuaaColumn', categories, series, "%");
}