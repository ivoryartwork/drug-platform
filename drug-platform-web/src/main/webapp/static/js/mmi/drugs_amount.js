/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;
var beginDate, endDate, drugCode, drugName, drugSpec, type = 1, deptCode, doctor, costType;
$(function () {
    path('用药指标监控', '药品用量');
    initBtn();
})

function initBtn() {
    $('#back-0').click(function () {
        back();
    })

    $("#searchBySingle").click(function () {
        var drug_name = $("#drugName input").val();
        if (typeof drug_name == 'undefined' || drug_name == '') {
            $("#drugName input").focus();
            return;
        }
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
        drugName = drug_name;
        drugCode = $("#drugName input").attr("name");
        drugSpec = $(this).parent().find('.drugSpec').find('option:selected').val();

        type = $(this).parent().find('.type').find('option:selected').val();
        var $dept = $(this).parent().find('.dept').find('option:selected');
        var $doctor = $(this).parent().find('.doctor').find('option:selected');
        deptCode = $dept.val();
        doctor = $doctor.val();
        costType = $(this).parent().find('.costType').find('option:selected').val();
        if (costType == 'none') {
            costType = null;
        }
        if (deptCode == 'none') {
            //全局
            singleSwitchToAll(drugCode, drugName, drugSpec);
        } else if (deptCode != 'none' && doctor == 'none') {
            //科室
            singleSwitchToDepartment();
        } else if (deptCode != 'none' && doctor != 'none') {
            //医生
            singleSwitchToDoctor();
        }
    });
}

function singleSwitchToAll(drugCode, drugName, drugSpec) {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-2").addClass("hide");
    $("#panel-3").addClass("hide");
    $("#panel-1").removeClass("hide");

    var params = {
        drugCode: drugCode,
        drugName: drugName,
        drugSpec: drugSpec,
        type: type,
        beginDate: beginDate,
        endDate: endDate,
        costType: costType
    }
    S.drugAmount.singleGlobal(params, bindSingleGlobalData);
    function bindSingleGlobalData(data) {
        alert(data);
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
        data: [1, 2, 3, 4, 5, 6],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('singleDrugAmountColumn', categories, series, "%");
}

function singleSwitchToDepartment() {
    panel_index = 2;
    $("#panel-0").addClass("hide");
    $("#panel-1").addClass("hide");
    $("#panel-3").addClass("hide");
    $("#panel-2").removeClass("hide");
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
        data: [1, 2, 3, 4, 5, 6],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('singleDepartmentDrugAmountColumn', categories, series, "%");
}

function singleSwitchToDoctor() {
    panel_index = 3;
    $("#panel-0").addClass("hide");
    $("#panel-1").addClass("hide");
    $("#panel-2").addClass("hide");
    $("#panel-3").removeClass("hide");
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
        data: [1, 2, 3, 4, 5, 6],
        dataLabels: {
            enabled: true,
        }
    }]
    chartHelper.column('singleDoctorDrugAmountColumn', categories, series, "%");
}

