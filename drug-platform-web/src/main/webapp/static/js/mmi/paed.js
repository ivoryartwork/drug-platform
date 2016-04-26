/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;
var beginDate, endDate, deptCode, costType;

$(function () {
    path('用药指标监控', '急诊抗菌药物处方比例');
    initDate();
    initBtn();
    allPaed();
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
            allPaed();
        } else if (deptCode != 'none') {
            //科室
            switchToDepartment(deptCode, $dept.html());
        }
    });
}

function allPaed() {
    $("#panel-1").addClass("hide");
    $("#panel-0").removeClass("hide");
    var params = {
        costType: costType,
        beginDate: beginDate,
        endDate: endDate,
    }
    S.paed.global(params, bindGlobalData);
    setTitle("全院" + beginDate + "至" + endDate + "急诊抗菌药物处方比例", "全院急诊抗菌药物处方比例趋势图", "全院" + beginDate + "至" + endDate + "各科室急诊抗菌药物处方比例");

    function bindGlobalData(data) {
        data = JSON.parse(data);
        var npep = data.npep;
        var npepa = data.npepa;
        var paed = data.paed;
        var targetPaed = data.targetPaed;
        var trend = data.trend;
        var deptPaedList = data.deptPaedList;

        $("#npep-0").html(npep);
        $("#npepa-0").html(npepa);
        $("#paed-0").html(paed);
        $("#targetPaed-0").html(targetPaed);

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
            name: '急诊抗菌药物处方比例',
            data: trendData,
            dataLabels: {
                enabled: true,
            }
        }]
        chartHelper.column('paedColumn', categories, series, "%");

        var deptPaedListStr = '';
        for (var i = 0; i < deptPaedList.length; i++) {
            deptPaedListStr += ' <tr> <td><a onclick=switchToDepartment("' + deptPaedList[i].deptCode + '","' + deptPaedList[i].deptName + '")>' + deptPaedList[i].deptName + '</a></td> <td>' + deptPaedList[i].npep + '</td>' +
                ' <td>' + deptPaedList[i].npepa + '</td> <td>' + deptPaedList[i].paed + '</td> <td>' + deptPaedList[i].targetPaed + '</td><td>' + deptPaedList[i].rank + '</td> </tr>';
        }
        $("#deptPaedList tbody").html(deptPaedListStr);
    }
}

function switchToDepartment(deptCode, deptName) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    departmentPaed(deptCode, deptName);
}

function departmentPaed(deptCode, deptName) {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        deptCode: deptCode,
        costType: costType
    }
    S.paed.dept(params, bindDeptData);
    setTitle(deptName + beginDate + "至" + endDate + "急诊抗菌药物处方比例", deptName + "急诊抗菌药物处方比例趋势图", null);

    function bindDeptData(data) {
        data = JSON.parse(data);
        var npep = data.npep;
        var npepa = data.npepa;
        var paed = data.paed;
        var targetPaed = data.targetPaed;
        var trend = data.trend;

        $("#npep-1").html(npep);
        $("#npepa-1").html(npepa);
        $("#paed-1").html(paed);
        $("#targetPaed-1").html(targetPaed);

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
            name: '急诊抗菌药物处方比例',
            data: trendData,
            dataLabels: {
                enabled: true,
            }
        }]
        chartHelper.column('departmentPaedColumn', categories, series, "%");
    }
}