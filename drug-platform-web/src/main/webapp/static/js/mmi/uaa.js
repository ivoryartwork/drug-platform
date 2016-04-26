/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;
var beginDate, endDate, deptCode, wardCode, costType;

$(function () {
    path('用药指标监控', '住院抗菌药物使用率');
    initDate();
    allUaa();
    initBtn();
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
        var $ward = $(this).parent().find('.dept-ward').find('option:selected');
        deptCode = $dept.val();
        wardCode = $ward.val();
        costType = $(this).parent().find('.costType').find('option:selected').val();
        if (costType == 'none') {
            costType = null;
        }
        if (deptCode == 'none') {
            //全局
            allUaa();
        } else if (deptCode != 'none' && wardCode == 'none') {
            //科室
            switchToDepartment(deptCode, $dept.html());
        } else if (deptCode != 'none' && wardCode != 'none') {
            //病区
            switchToWard(wardCode, $ward.html());
        }
    });
}

function allUaa() {
    $("#panel-1").addClass("hide");
    $("#panel-2").addClass("hide");
    $("#panel-0").removeClass("hide");
    var params = {
        costType: costType,
        beginDate: beginDate,
        endDate: endDate,
    }
    S.uaa.global(params, bindGlobalData);
    setTitle("全院" + beginDate + "至" + endDate + "住院抗菌药物使用率", "全院住院抗菌药物使用率趋势图", "全院" + beginDate + "至" + endDate + "各科室住院抗菌药物使用率");
    function bindGlobalData(data) {
        data = JSON.parse(data);
        var nhp = data.nhp;
        var nadu = data.nadu;
        var puaa = data.puaa;
        var targetPuaa = data.targetPuaa;
        var trend = data.trend;
        var deptUaaList = data.deptUaaList;

        $("#nhp-0").html(nhp);
        $("#nadu-0").html(nadu);
        $("#puaa-0").html(puaa);
        $("#targetPuaa-0").html(targetPuaa);

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
        chartHelper.column('uaaColumn', categories, series, "%");

        var deptUaaListStr = '';
        for (var i = 0; i < deptUaaList.length; i++) {
            deptUaaListStr += ' <tr> <td><a onclick=switchToDepartment("' + deptUaaList[i].deptCode + '","' + deptUaaList[i].deptName + '")>' + deptUaaList[i].deptName + '</a></td> <td>' + deptUaaList[i].nhp + '</td>' +
                ' <td>' + deptUaaList[i].nadu + '</td> <td>' + deptUaaList[i].puaa + '</td> <td>' + deptUaaList[i].targetPuaa + '</td><td>' + deptUaaList[i].rank + '</td> </tr>';
        }
        $("#deptUaaList tbody").html(deptUaaListStr);
    }
}

function switchToDepartment(deptCode, deptName) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    $("#panel-2").addClass("hide");
    departmentUaa(deptCode, deptName);
}

function departmentUaa(deptCode, deptName) {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        deptCode: deptCode,
        costType: costType
    }
    S.uaa.dept(params, bindDeptData);
    setTitle(deptName + beginDate + "至" + endDate + "住院抗菌药物使用率", deptName + "住院抗菌药物使用率趋势图", deptName + beginDate + "至" + endDate + "各病区住院抗菌药物使用率");

    function bindDeptData(data) {
        data = JSON.parse(data);
        var nhp = data.nhp;
        var nadu = data.nadu;
        var puaa = data.puaa;
        var targetPuaa = data.targetPuaa;
        var trend = data.trend;
        var wardUaaList = data.wardUaaList;

        $("#nhp-1").html(nhp);
        $("#nadu-1").html(nadu);
        $("#puaa-1").html(puaa);
        $("#targetPuaa-1").html(targetPuaa);

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
        chartHelper.column('departmentUaaColumn', categories, series, "%");

        var wardUaaListStr = '';
        for (var i = 0; i < wardUaaList.length; i++) {
            wardUaaListStr += ' <tr> <td><a onclick=switchToWard("' + wardUaaList[i].wardCode + '","' + wardUaaList[i].wardName + '")>' + wardUaaList[i].wardName + '</a></td> <td>' + wardUaaList[i].nhp + '</td>' +
                ' <td>' + wardUaaList[i].nadu + '</td> <td>' + wardUaaList[i].puaa + '</td> <td>' + wardUaaList[i].targetPuaa + '</td><td>' + wardUaaList[i].rank + '</td> </tr>';
        }
        $("#wardUaaList tbody").html(wardUaaListStr);
    }
}

function switchToWard(wardCode, wardName) {
    panel_index = 2;
    $("#panel-1").addClass("hide");
    $("#panel-0").addClass("hide");
    $("#panel-2").removeClass("hide");
    wardUaa(wardCode, wardName);
}

function wardUaa(wardCode, wardName) {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        wardCode: wardCode,
        costType: costType
    }
    S.uaa.ward(params, bindWardData);
    setTitle(wardName + beginDate + "至" + endDate + "住院抗菌药物使用率", wardName + "住院抗菌药物使用率趋势图", null);

    function bindWardData(data) {
        data = JSON.parse(data);
        var nhp = data.nhp;
        var nadu = data.nadu;
        var puaa = data.puaa;
        var targetPuaa = data.targetPuaa;
        var trend = data.trend;

        $("#nhp-2").html(nhp);
        $("#nadu-2").html(nadu);
        $("#puaa-2").html(puaa);
        $("#targetPuaa-2").html(targetPuaa);

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
        chartHelper.column('wardUaaColumn', categories, series, "%");
    }
}