/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;
var beginDate, endDate, deptCode, wardCode, costType;

$(function () {
    path('用药指标监控', '住院次均药费');
    initDate();
    allAhe();
    initBtn();
})

/**
 * 全院统计
 */
function allAhe() {
    $("#panel-1").addClass("hide");
    $("#panel-2").addClass("hide");
    $("#panel-0").removeClass("hide");
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        costType: costType
    }
    S.ahe.global(params, bindGlobalData);
    setTitle("全院" + beginDate + "至" + endDate + "住院次均药费", "全院住院次均药费趋势图", "全院" + beginDate + "至" + endDate + "各科室住院次均药费");
}

function bindGlobalData(data) {
    data = JSON.parse(data);
    var timesDrugCost = data.timesDrugCost;
    var targetTimesDrugCost = data.targetTimesDrugCost;
    var trend = data.trend;
    var timesDrugCostList = data.timesDrugCostList;
    $("#globalTarget").html(targetTimesDrugCost);
    $("#globalTimesDrugCost").html('全院' + beginDate + '至' + endDate + '住院次均药费<span class="x-prom">' + timesDrugCost + '元</span>');

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
        name: '次均药费',
        data: trendData,
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('aheColumn', categories, series, "元");

    var timesDrugCostListStr = '';
    for (var i = 0; i < timesDrugCostList.length; i++) {
        timesDrugCostListStr += '<tr><td><a onclick=switchToDepartment("' + timesDrugCostList[i].deptCode + '","' + timesDrugCostList[i].deptName + '")>' + timesDrugCostList[i].deptName + '</a>' +
            '</td><td>' + timesDrugCostList[i].times + '</td><td>' + timesDrugCostList[i].totalDrugCost + '</td> <td>' + timesDrugCostList[i].timesDrugCost + '</td> ' +
            '<td>' + timesDrugCostList[i].targetTimesDrugCost + '</td> <td>' + timesDrugCostList[i].rank + '</td> </tr>';
    }
    $("#panel-0 table tbody").html(timesDrugCostListStr);
}

/**
 * 科室统计
 */
function departmentAhe(deptCode, deptName) {

    var params = {
        beginDate: beginDate,
        endDate: endDate,
        deptCode: deptCode,
        costType: costType
    }
    S.ahe.dept(params, bindDeptData);
    setTitle(deptName + beginDate + "至" + endDate + "住院次均药费", deptName + "住院次均药费趋势图", deptName + beginDate + "至" + endDate + "各病区住院次均药费");

    function bindDeptData(data) {
        data = JSON.parse(data);
        var timesDrugCost = data.timesDrugCost;
        var targetTimesDrugCost = data.targetTimesDrugCost;
        var trend = data.trend;
        var timesDrugCostList = data.timesDrugCostList;
        $("#deptTarget").html(targetTimesDrugCost);
        $("#deptTimesDrugCost").html(deptName + beginDate + '至' + endDate + '住院次均药费<span class="x-prom">' + timesDrugCost + '元</span>');

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
            name: '次均药费',
            data: trendData,
            dataLabels: {
                enabled: true,
            }
        }]
        chartHelper.column('departmentAheColumn', categories, series, "元");

        var timesDrugCostListStr = '';
        for (var i = 0; i < timesDrugCostList.length; i++) {
            timesDrugCostListStr += '<tr><td><a onclick=switchToWard("' + timesDrugCostList[i].wardCode + '","' + timesDrugCostList[i].wardName + '")>' + timesDrugCostList[i].wardName + '</a>' +
                '</td><td>' + timesDrugCostList[i].times + '</td><td>' + timesDrugCostList[i].totalDrugCost + '</td> <td>' + timesDrugCostList[i].timesDrugCost + '</td> ' +
                '<td>' + timesDrugCostList[i].targetTimesDrugCost + '</td> <td>' + timesDrugCostList[i].rank + '</td> </tr>';
        }
        $("#panel-1 table tbody").html(timesDrugCostListStr);
    }
}

/**
 * 病区统计
 */
function wardAhe(wardCode, wardName) {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        deptCode: '123455',
        wardCode: wardCode,
        costType: costType
    }
    S.ahe.ward(params, bindWardData);
    setTitle(wardName, null, null);
}

function bindWardData(data) {
    data = JSON.parse(data);
    var trend = data.trend;
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
    chartHelper.column('wardAheColumn', categories, series, "元");
}

function initBtn() {
    $('#back').click(function () {
        back();
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
        var $dept = $(this).parent().find('.dept').find('option:selected');
        var $ward = $(this).parent().find('.dept-ward').find('option:selected');
        deptCode = $dept.val();
        wardCode = $ward.val();
        costType = $(this).parent().find('.costType').find('option:selected').val();
        if (costType == 'none') {
            costType = null;
        }
        if (deptCode == 'none') {
            //全局
            allAhe();
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
    departmentAhe(deptCode, deptName);
}

function switchToWard(wardCode, wardName) {
    panel_index = 2;
    $("#panel-1").addClass("hide");
    $("#panel-0").addClass("hide");
    $("#panel-2").removeClass("hide");
    wardAhe(wardCode, wardName);
}