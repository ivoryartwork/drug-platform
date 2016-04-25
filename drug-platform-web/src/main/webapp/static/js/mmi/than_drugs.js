/**
 * Created by Yaochao on 2016/4/18.
 */
var panel_index = 0;
var beginDate, endDate, type, deptCode, wardCode, costType;
var dateFormatStr = 'yyyy-MM-dd';

$(function () {
    path('用药指标监控', '药费比');
    initDate();
    allThanDrugs();
    initBtn();
})

function initDate() {
    var now = new Date();
    endDate = now.Format(dateFormatStr);
    beginDate = now.AddDate(-30).Format(dateFormatStr);
}

/**
 * 全院统计药费比
 */
function allThanDrugs() {
    $("#panel-1").addClass("hide");
    $("#panel-2").addClass("hide");
    $("#panel-0").removeClass("hide");
    var params = {
        type: 1,
        beginDate: beginDate,
        endDate: endDate,
    }
    S.thanDrugs.global(params, bindGlabalData);
    setTitle("全院" + beginDate + "至" + endDate + "药费比", "全院总药费比趋势图", "全院" + beginDate + "至" + endDate + "各科室药费比");
}

function setTitle(title1, title2, title3) {
    $("#panel-" + panel_index + " .des").each(function (i) {
        if (i == 0) {
            $(this).html(title1);
        } else if (i == 1) {
            $(this).html(title2);
        } else {
            $(this).html(title3);
        }
    })
}

//绑定全局数据
function bindGlabalData(data) {
    data = JSON.parse(data);
    var rate = data.rate;
    var deptRates = data.deptRates;
    var trend = data.trend;
    $("#rate-0").html(rate.rate);
    $("#totalDrugCost-0").html(rate.totalDrugCost);
    $("#totalTreatCost-0").html(rate.totalTreatCost);
    $("#totalCost-0").html(rate.totalCost);

    var data = [
        ['药费', rate.rate],
        ['其他', 100 - Number(rate.rate)],
    ];
    chartHelper.pie('thanDrugsPie', data);
    var trendArray = trend.split(",");
    var trendData = [];
    for (var i = 0; i < trendArray.length; i++) {
        trendData[i] = Number(trendArray[i]);
    }
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
        data: trendData,
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('thanDrugsColumn', categories, series, "%");

    var deptRatesList = '';
    for (var i = 0; i < deptRates.length; i++) {
        deptRatesList += ' <tr> <td><a onclick=switchToDepartment("' + deptRates[i].deptCode + '","' + deptRates[i].deptName + '")>' + deptRates[i].deptName + '</a></td> <td>' + deptRates[i].drugCost + '</td>' +
            ' <td>' + deptRates[i].treatCost + '</td> <td>' + deptRates[i].totalCost + '</td> <td>' + deptRates[i].rate + '</td> <td>' + deptRates[i].targetRate + '</td> <td>' + deptRates[i].rank + '</td> </tr>';
    }
    $("#panel-0 table tbody").html(deptRatesList);
}


//绑定科室数据
function bindDeptData(data) {
    data = JSON.parse(data);
    var rate = data.rate;
    var wardRates = data.wardRates;
    var trend = data.trend;
    var trendOut = data.trendOut;
    var trendHis = data.trendHis;
    $("#rate-1").html(rate.rate);
    $("#totalDrugCost-1").html(rate.totalDrugCost);
    $("#totalTreatCost-1").html(rate.totalTreatCost);
    $("#totalCost-1").html(rate.totalCost);

    var data = [
        ['药费', rate.rate],
        ['其他', 100 - Number(rate.rate)],
    ];
    chartHelper.pie('departmentThanDrugsPie', data);
    var trendArray = trend.split(",");
    var trendData = [];
    for (var i = 0; i < trendArray.length; i++) {
        trendData[i] = Number(trendArray[i]);
    }

    var trendOutArray = trendOut.split(",");
    var trendOutData = [];
    for (var i = 0; i < trendOutArray.length; i++) {
        trendOutData[i] = Number(trendOutArray[i]);
    }

    var trendHisArray = trendHis.split(",");
    var trendHisData = [];
    for (var i = 0; i < trendHisArray.length; i++) {
        trendHisData[i] = Number(trendHisArray[i]);
    }
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
        data: trendData,
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('departmentAllThanDrugsColumn', categories, series, "%");
    var series = [{
        name: '住院',
        data: trendHisData,
        dataLabels: {
            enabled: true,
        }
    }, {
        name: '门诊',
        data: trendOutData,
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('departmentSubThanDrugsColumn', categories, series, "%");

    var wardRatesList = '';
    for (var i = 0; i < wardRates.length; i++) {
        wardRatesList += ' <tr> <td><a onclick=switchToWard("' + wardRates[i].wardCode + '","' + wardRates[i].wardName + '")>' + wardRates[i].wardName + '</a></td> <td>' + wardRates[i].drugCost + '</td>' +
            ' <td>' + wardRates[i].treatCost + '</td> <td>' + wardRates[i].totalCost + '</td> <td>' + wardRates[i].rate + '</td> <td>' + wardRates[i].targetRate + '</td> <td>' + wardRates[i].rank + '</td> </tr>';
    }
    $("#panel-1 table tbody").html(wardRatesList);
}

//绑定病区数据
function bindWardData(data) {
    data = JSON.parse(data);
    var rate = data.rate;
    var trend = data.trend;
    $("#rate-2").html(rate.rate);
    $("#totalDrugCost-2").html(rate.totalDrugCost);
    $("#totalTreatCost-2").html(rate.totalTreatCost);
    $("#totalCost-2").html(rate.totalCost);

    var data = [
        ['药费', rate.rate],
        ['其他', 100 - Number(rate.rate)],
    ];
    chartHelper.pie('wardThanDrugsPie', data);
    var trendArray = trend.split(",");
    var trendData = [];
    for (var i = 0; i < trendArray.length; i++) {
        trendData[i] = Number(trendArray[i]);
    }

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
        data: trendData,
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('wardThanDrugsColumn', categories, series, "%");
}

/**
 * 科室统计
 */
function departmentThanDrugs(deptCode, deptName) {
    var params = {
        type: 1,
        beginDate: beginDate,
        endDate: endDate,
        deptCode: deptCode
    }
    S.thanDrugs.dept(params, bindDeptData);
    setTitle(deptName + beginDate + "至" + endDate + "药费比", deptName + "总药费比趋势图", deptName + beginDate + "至" + endDate + "各病区药费比");
}

/**
 * 病区统计
 */
function wardThanDrugs(wardCode, wardName) {
    var params = {
        type: 1,
        beginDate: beginDate,
        endDate: endDate,
        deptCode: '123455',
        wardCode: wardCode
    }
    S.thanDrugs.ward(params, bindWardData);
    setTitle(wardName + beginDate + "至" + endDate + "药费比", wardName + "总药费比趋势图", null);
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

    $(".search-panel .search-btn").click(function () {
        var $beginDateInput = $(this).parent().find('input[name = "begin"]');
        var begin = $beginDateInput.val();
        if (begin == '') {
            $beginDateInput.focus();
            return;
        }
        var $endDateInput = $(this).parent().find('input[name = "end"]');
        var end = $endDateInput.val();
        if (end == '') {
            $endDateInput.focus();
            return;
        }
        beginDate = begin;
        endDate = end;
        type = $(this).parent().find('.type').find('option:selected').val();
        var $dept = $(this).parent().find('.dept').find('option:selected');
        var $ward = $(this).parent().find('.dept-ward').find('option:selected');
        deptCode = $dept.val();
        wardCode = $ward.val();
        costType = $(this).parent().find('.costType').find('option:selected').val();
        if (deptCode == 'none') {
            //全局
            allThanDrugs();
        } else if (deptCode != 'none' && wardCode == 'none') {
            //科室
            switchToDepartment(deptCode, $dept.html());
        } else if (deptCode != 'none' && wardCode != 'none') {
            //病区
            switchToWard(wardCode, $ward.html());
        }
    });
}

//跳转到科室统计
function switchToDepartment(deptCode, deptName) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-2").addClass("hide");
    $("#panel-1").removeClass("hide");
    $("#panel-1 .title-name").html(name);
    departmentThanDrugs(deptCode, deptName);
}

function switchToWard(wardCode, wardName) {
    panel_index = 2;
    $("#panel-0").addClass("hide");
    $("#panel-1").addClass("hide");
    $("#panel-2").removeClass("hide");
    $("#panel-2 .title-name").html(name);
    wardThanDrugs(wardCode, wardName);
}