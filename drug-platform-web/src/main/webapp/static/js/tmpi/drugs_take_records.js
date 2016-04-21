/**
 * Created by Yaochao on 2016/4/19.
 */
var beginDate, endDate, patientId, citizenhood;

$(function () {
    path('用药指标监控', '取药记录查询');
    initBtn();
})

//
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
        var patientIdStr = $("#patientId").val();
        if (patientIdStr == '') {
            $("#patientId").focus();
            return;
        }
        var citizenhoodStr = $("#citizenhood").children("option:selected").val();
        patientId = patientIdStr;
        citizenhood = citizenhoodStr;
        searchByTime();
    });

    $("#selectType").on('change', function () {
        var type = $(this).children("option:selected").val();
        if (type == 0) {
            $("#drugTakeRecordsByTime").removeClass("hide");
            $("#drugTakeRecordsByDrug").addClass("hide");
            searchByTime();
        } else {
            $("#drugTakeRecordsByTime").addClass("hide");
            $("#drugTakeRecordsByDrug").removeClass("hide");
            searchByDrug();
        }
    })
}

function searchByTime() {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        citizenhood: citizenhood,
        patientId: patientId
    }
    S.drugTakeRecords.byTime(params, bindRecordsDataByTime);
}

function searchByDrug() {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        citizenhood: citizenhood,
        patientId: patientId
    }
    S.drugTakeRecords.byDrug(params, bindRecordsDataByDrug);
}

function bindRecordsDataByDrug(data) {
    data = JSON.parse(data);
    var records = data.records;

    var thead = '<tr> <th>序号</th> <th>药物类别</th> <th>药物代码</th> <th>药品名称</th> <th>规格</th> ' +
        '<th>单位</th> <th>取药次数</th> <th>可用天数</th> <th>数量</th> <th>计价金额（元）</th> <th>实收金额（元）</th> </tr>';
    $("#drugTakeRecordsByDrug thead").html(thead);

    var recordsList = '';
    for (var i = 0; i < records.length; i++) {
        recordsList += '<tr> <td>' + records[i].id + '</td> <td>' + records[i].drugType + '</td> <td>' + records[i].drugCode + '</td> ' +
            '<td>' + records[i].drugName + '</td> <td>' + records[i].specifications + '</td> <td>' + records[i].unit + '</td> ' +
            '<td>' + records[i].takeTimes + '</td> <td>' + records[i].availableDays + '</td> <td>' + records[i].number + '</td><td>' + records[i].ia + '</td> <td>' + records[i].pa + '</td></tr>';
    }
    $("#drugTakeRecordsByTime").addClass("hide");
    $("#drugTakeRecordsByDrug").removeClass("hide");
    $("#drugTakeRecordsByDrug tbody").html(recordsList);
    $("#records").removeClass("hide");
}

function bindRecordsDataByTime(data) {
    data = JSON.parse(data);
    var records = data.records;
    var userInfo = data.userInfo;

    $("#name").html(userInfo.name);
    $("#name1").html(userInfo.name + " " + beginDate + "至" + endDate + " ");
    $("#age").html(userInfo.age);
    $("#subordinateUnits").html(userInfo.subordinateUnits);
    $("#registrationNumber").html(userInfo.registrationNumber);
    $("#admission").html(userInfo.admission);
    $("#totalAmountOfDrug").html(userInfo.totalAmountOfDrug);

    var thead = '<tr> <th>序号</th> <th>取药日期</th> <th>药房名称</th> <th>药品名称</th> <th>规格</th> ' +
        '<th>单位</th> <th>药物类别</th> <th>科室名称</th> <th>医生</th> <th>数量</th> <th>计价金额（元）</th> <th>实收金额（元）</th> </tr>';
    $("#drugTakeRecordsByTime thead").html(thead);

    var recordsList = '';
    for (var i = 0; i < records.length; i++) {
        recordsList += '<tr> <td>' + records[i].id + '</td> <td>' + records[i].takeTime + '</td> <td>' + records[i].pharmacyName + '</td> ' +
            '<td>' + records[i].drugName + '</td> <td>' + records[i].specifications + '</td> <td>' + records[i].unit + '</td> ' +
            '<td>' + records[i].drugType + '</td> <td>' + records[i].deptName + '</td> <td>' + records[i].doctor + '</td> <td>' + records[i].number + '</td> ' +
            '<td>' + records[i].ia + '</td> <td>' + records[i].pa + '</td></tr>';
    }
    $("#drugTakeRecordsByTime").removeClass("hide");
    $("#drugTakeRecordsByDrug").addClass("hide");
    $("#drugTakeRecordsByTime tbody").html(recordsList);
    $("#records").removeClass("hide");
}