/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;
var beginDate, endDate, deptCode, wardCode, costType;

$(function () {
    path('用药指标监控', 'i类切口预防使用抗菌药物比例');
    initDate();
    allIpuaa();
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
            allIpuaa();
        } else if (deptCode != 'none' && wardCode == 'none') {
            //科室
            switchToDepartment(deptCode, $dept.html());
        } else if (deptCode != 'none' && wardCode != 'none') {
            //病区
            switchToWard(wardCode, $ward.html());
        }
    });
}

function allIpuaa() {
    $("#panel-1").addClass("hide");
    $("#panel-2").addClass("hide");
    $("#panel-0").removeClass("hide");
    var params = {
        costType: costType,
        beginDate: beginDate,
        endDate: endDate,
    }
    S.ipuaa.global(params, bindGlobalData);
    setTitle("全院" + beginDate + "至" + endDate + "I类切口预防使用抗菌药物比例", "全院I类切口预防使用抗菌药物比例趋势图", "全院" + beginDate + "至" + endDate + "各科室I类切口预防使用抗菌药物比例");
    function bindGlobalData(data) {
        data = JSON.parse(data);
        var inp = data.inp;
        var ianp = data.ianp;
        var ipuaa = data.ipuaa;
        var targetIpuaa = data.targetIpuaa;
        var trend = data.trend;
        var deptIpuaaList = data.deptIpuaaList;

        $("#inp-0").html(inp);
        $("#ianp-0").html(ianp);
        $("#ipuaa-0").html(ipuaa);
        $("#targetIpuaa-0").html(targetIpuaa);

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
            name: 'i类切口预防使用抗菌药物比例',
            data: trendData,
            dataLabels: {
                enabled: true,
            }
        }]
        chartHelper.column('ipuaaColumn', categories, series, "%");
        var deptIpuaaListStr = '';
        for (var i = 0; i < deptIpuaaList.length; i++) {
            deptIpuaaListStr += ' <tr> <td><a onclick=switchToDepartment("' + deptIpuaaList[i].deptCode + '","' + deptIpuaaList[i].deptName + '")>' + deptIpuaaList[i].deptName + '</a></td> <td>' + deptIpuaaList[i].inp + '</td>' +
                ' <td>' + deptIpuaaList[i].ianp + '</td> <td>' + deptIpuaaList[i].ipuaa + '</td> <td>' + deptIpuaaList[i].targetIpuaa + '</td><td>' + deptIpuaaList[i].rank + '</td> </tr>';
        }
        $("#deptIpuaaList tbody").html(deptIpuaaListStr);
    }
}

function switchToDepartment(deptCode, deptName) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-2").addClass("hide");
    $("#panel-1").removeClass("hide");
    departmentIpuaa(deptCode, deptName);
}

function departmentIpuaa(deptCode, deptName) {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        deptCode: deptCode,
        costType: costType
    }
    S.ipuaa.dept(params, bindDeptData);
    setTitle(deptName + beginDate + "至" + endDate + "I类切口预防使用抗菌药物比例", deptName + "I类切口预防使用抗菌药物比例趋势图", deptName + beginDate + "至" + endDate + "各病区I类切口预防使用抗菌药物比例");
    function bindDeptData(data) {
        data = JSON.parse(data);
        var inp = data.inp;
        var ianp = data.ianp;
        var ipuaa = data.ipuaa;
        var targetIpuaa = data.targetIpuaa;
        var trend = data.trend;
        var wardIpuaaList = data.wardIpuaaList;

        $("#inp-1").html(inp);
        $("#ianp-1").html(ianp);
        $("#ipuaa-1").html(ipuaa);
        $("#targetIpuaa-1").html(targetIpuaa);

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
            name: 'i类切口预防使用抗菌药物比例',
            data: trendData,
            dataLabels: {
                enabled: true,
            }
        }]
        chartHelper.column('departmentIpuaaColumn', categories, series, "%");
        var wardIpuaaListStr = '';
        for (var i = 0; i < wardIpuaaList.length; i++) {
            wardIpuaaListStr += ' <tr> <td><a onclick=switchToWard("' + wardIpuaaList[i].wardCode + '","' + wardIpuaaList[i].wardName + '")>' + wardIpuaaList[i].wardName + '</a></td> <td>' + wardIpuaaList[i].inp + '</td>' +
                ' <td>' + wardIpuaaList[i].ianp + '</td> <td>' + wardIpuaaList[i].ipuaa + '</td> <td>' + wardIpuaaList[i].targetIpuaa + '</td><td>' + wardIpuaaList[i].rank + '</td> </tr>';
        }
        $("#wardIpuaaList tbody").html(wardIpuaaListStr);
    }
}

function switchToWard(wardCode, wardName) {
    panel_index = 2;
    $("#panel-1").addClass("hide");
    $("#panel-0").addClass("hide");
    $("#panel-2").removeClass("hide");
    wardIpuaa(wardCode, wardName);
}

function wardIpuaa(wardCode, wardName) {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        wardCode: wardCode,
        costType: costType
    }
    S.ipuaa.ward(params, bindWardData);
    setTitle(wardName + beginDate + "至" + endDate + "I类切口预防使用抗菌药物比例", wardName + "I类切口预防使用抗菌药物比例趋势图", null);
    function bindWardData(data) {
        data = JSON.parse(data);
        var inp = data.inp;
        var ianp = data.ianp;
        var ipuaa = data.ipuaa;
        var targetIpuaa = data.targetIpuaa;
        var trend = data.trend;

        $("#inp-2").html(inp);
        $("#ianp-2").html(ianp);
        $("#ipuaa-2").html(ipuaa);
        $("#targetIpuaa-2").html(targetIpuaa);

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
            name: 'i类切口预防使用抗菌药物比例',
            data: trendData,
            dataLabels: {
                enabled: true,
            }
        }]
        chartHelper.column('wardIpuaaColumn', categories, series, "%");
    }
}