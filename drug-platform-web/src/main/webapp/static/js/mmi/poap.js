/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;
var beginDate, endDate, deptCode, costType;

$(function () {
    path('用药指标监控', '门诊抗菌药物处方比例');
    initDate();
    initBtn();
    allPoap();
})

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
            allPoap();
        } else if (deptCode != 'none') {
            //科室
            switchToDepartment(deptCode, $dept.html());
        }
    });
}

function allPoap() {
    $("#panel-1").addClass("hide");
    $("#panel-0").removeClass("hide");
    var params = {
        costType: costType,
        beginDate: beginDate,
        endDate: endDate,
    }
    S.poap.global(params, bindGlobalData);
    setTitle("全院" + beginDate + "至" + endDate + "门诊抗菌药物处方比例", "全院门诊抗菌药物处方比例趋势图", "全院" + beginDate + "至" + endDate + "各科室门诊抗菌药物处方比例");

    function bindGlobalData(data) {
        data = JSON.parse(data);
        var nopp = data.nopp;
        var nopa = data.nopa;
        var poap = data.poap;
        var targetPoap = data.targetPoap;
        var trend = data.trend;
        var deptPoapList = data.deptPoapList;

        $("#nopp-0").html(nopp);
        $("#nopa-0").html(nopa);
        $("#poap-0").html(poap);
        $("#targetPoap-0").html(targetPoap);

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
            name: '抗菌药物使用率',
            data: trendData,
            dataLabels: {
                enabled: true,
            }
        }]
        chartHelper.column('poapColumn', categories, series, "%");
        var deptPoapListStr = '';
        for (var i = 0; i < deptPoapList.length; i++) {
            deptPoapListStr += ' <tr> <td><a onclick=switchToDepartment("' + deptPoapList[i].deptCode + '","' + deptPoapList[i].deptName + '")>' + deptPoapList[i].deptName + '</a></td> <td>' + deptPoapList[i].nopp + '</td>' +
                ' <td>' + deptPoapList[i].nopa + '</td> <td>' + deptPoapList[i].poap + '</td> <td>' + deptPoapList[i].targetPoap + '</td><td>' + deptPoapList[i].rank + '</td> </tr>';
        }
        $("#deptPoapList tbody").html(deptPoapListStr);
    }
}

function switchToDepartment(deptCode, deptName) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    departmentPoap(deptCode, deptName);
}

function departmentPoap(deptCode, deptName) {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        deptCode: deptCode,
        costType: costType
    }
    S.poap.dept(params, bindDeptData);
    setTitle(deptName + beginDate + "至" + endDate + "门诊抗菌药物处方比例", deptName + "门诊抗菌药物处方比例趋势图", null);
    function bindDeptData(data) {
        data = JSON.parse(data);
        var nopp = data.nopp;
        var nopa = data.nopa;
        var poap = data.poap;
        var targetPoap = data.targetPoap;
        var trend = data.trend;

        $("#nopp-1").html(nopp);
        $("#nopa-1").html(nopa);
        $("#poap-1").html(poap);
        $("#targetPoap-1").html(targetPoap);

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
            name: '抗菌药物处方比例',
            data: trendData,
            dataLabels: {
                enabled: true,
            }
        }]
        chartHelper.column('departmentPoapColumn', categories, series, "%");
    }
}