/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;
var beginDate, endDate, deptCode, costType;
var dateFormatStr = 'yyyy-MM-dd';

function initDate() {
    var now = new Date();
    endDate = now.Format(dateFormatStr);
    beginDate = now.AddDate(-30).Format(dateFormatStr);
}

$(function () {
    path('用药指标监控', '门诊次均药费');
    initDate();
    allDrugsOutpatient();
    initBtn();
})

/**
 * 全院统计
 */
function allDrugsOutpatient() {
    $("#panel-1").addClass("hide");
    $("#panel-0").removeClass("hide");
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        costType: costType
    }

    S.drugsOutPatient.global(params, bindGlobalData)
    setTitle("全院" + beginDate + "至" + endDate + "门诊次均药费", "全院门诊次均药费趋势图", "全院" + beginDate + "至" + endDate + "各科室次均药费");
}

function bindGlobalData(data) {
    data = JSON.parse(data);
    var timesDrugCost = data.timesDrugCost;
    var targetTimesDrugCost = data.targetTimesDrugCost;
    var trend = data.trend;
    var timesDrugCostList = data.timesDrugCostList;

    $("#globalTarget").html(targetTimesDrugCost);
    $("#globalTimesDrugCost").html('全院' + beginDate + '至' + endDate + '门诊次均药费<span class="x-prom">' + timesDrugCost + '元</span>');

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
    chartHelper.column('drugsOutpatientColumn', categories, series, "元");

    var timesDrugCostListStr = '';
    for (var i = 0; i < timesDrugCostList.length; i++) {
        timesDrugCostListStr += '<tr><td><a onclick=switchToDepartment("' + timesDrugCostList[i].deptCode + '","' + timesDrugCostList[i].deptName + '")>' + timesDrugCostList[i].deptName + '</a>' +
            '</td><td>' + timesDrugCostList[i].times + '</td><td>' + timesDrugCostList[i].totalDrugCost + '</td> <td>' + timesDrugCostList[i].timesDrugCost + '</td> ' +
            '<td>' + timesDrugCostList[i].targetTimesDrugCost + '</td> <td>' + timesDrugCostList[i].rank + '</td> </tr>';
    }
    $("#panel-0 table tbody").html(timesDrugCostListStr);
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
        deptCode = $dept.val();
        costType = $(this).parent().find('.costType').find('option:selected').val();
        if (costType == 'none') {
            costType = null;
        }
        if (deptCode == 'none') {
            //全局
            allDrugsOutpatient();
        } else if (deptCode != 'none') {
            //科室
            switchToDepartment(deptCode, $dept.html());
        }
    });
}

//跳转到科室统计
function switchToDepartment(deptCode, deptName) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    departmentDrugsOutpatient(deptCode, deptName);
}

/**
 * 科室统计
 */
function departmentDrugsOutpatient(deptCode, deptName) {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        deptCode: deptCode,
        costType: costType
    }
    S.drugsOutPatient.dept(params, bindDeptData);
    setTitle(deptName + "门诊次均药费趋势图");
}

function bindDeptData(data) {
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
        name: '肾病科次均药费',
        data: trendData,
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('departmentDrugsOutPatientColumn', categories, series, "%");
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