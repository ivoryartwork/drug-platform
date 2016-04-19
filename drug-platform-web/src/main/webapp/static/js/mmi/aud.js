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
function switchToSingleDepartment(name) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    $("#panel-1 .title-name").html(name);
    departmentSingleAud();
}

/**
 *
 * @param name
 */
function switchToDepartment(name) {
    panel_index_1 = 7;
    $("#panel-6").addClass("hide");
    $("#panel-7").removeClass("hide");
    $("#panel-7 .title-name").html(name);
    departmentAud();
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
/**
 *
 */
function departmentSingleAud() {
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