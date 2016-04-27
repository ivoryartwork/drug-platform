/**
 * Created by Yaochao on 2016/4/19.
 */
$(function () {
    path('用药指标监控', '门诊病人住院记录');
    initBtn();
})

function initBtn() {
    $(".search-btn").click(function () {
        var patientId = $("#patientId").val();
        if (patientId == '') {
            $("#patientId").focus();
            return;
        }
        getOIR(patientId);
    })
}

/**
 * 获取住院记录
 */
function getOIR(patientId) {
    var params = {
        patientId: patientId
    }
    S.oir.getRecords(params, bindRecordData);

    function bindRecordData(data) {
        data = JSON.parse(data);
        $("#clinicNum").html(data.clinicNum);
        $("#patientName").html(data.patientName);
        $("#patientAge").html(data.patientAge);
        $("#citizenship").html(data.citizenship);
        $("#licenseNum").html(data.licenseNum);
        $("#nation").html(data.nation);
        $("#maritalStatus").html(data.maritalStatus);
        $("#sex").html(data.sex);
        $("#medicareType").html(data.medicareType);
        $("#phone").html(data.phone);

        var records = data.records;
        var recordsStr = '';
        for (var i = 0; i < records.length; i++) {
            recordsStr += '<tr> <td>' + records[i].num + '</td> <td>' + records[i].deptName + '</td> <td>' + records[i].doctor + '</td> <td>' + records[i].admissionTime + '</td> <td>' + records[i].dischargeTime + '</td> <td>' + records[i].wardNum + '</td> <td>' + records[i].nurse + '</td>' +
                ' <td>' + records[i].diseaseCause + '</td> <td>' + records[i].examResult + '</td> <td>' + records[i].hospiatlSuggest + '</td> <td>' + records[i].doctorSuggest + '</td> <td><a>查看</a></td> </tr>';
        }
        $("#hospitalRecords tbody").html(recordsStr);
        $("#oir").removeClass("hide");
    }
}