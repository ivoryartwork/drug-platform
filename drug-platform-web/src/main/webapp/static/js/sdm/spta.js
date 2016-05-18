/**
 * Created by Yaochao on 2016/4/28.
 */
$(function () {
    path("特殊管理药品监控", "单病人用药统计");
    initBtn();
})

function initBtn() {
    $(".search-btn").click(function () {
        var patientId = $("#patientId").val();
        if (patientId == '') {
            $("#patientId").focus();
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

        var params = {
            patientId: patientId,
            beginDate: begin,
            endDate: end
        }
        S.sdm.spta(params, bindData);

        function bindData(data) {
            if (data == null || data == '') {
                $("#panel-0").addClass("hide");
                return;
            }
            $("#panel-0").removeClass("hide");
            data = JSON.parse(data);
            $("#patientName").html(data.patientName);
            $("#patientAge").html(data.patientAge);
            $("#citizenship").html(data.citizenship);
            $("#licenseNum").html(data.licenseNum);
            $("#nation").html(data.nation);
            $("#sex").html(data.sex);
            $("#medicareType").html(data.medicareType);
            $("#phone").html(data.phone);

            var records = data.records;
            var listStr = '';
            for (var i = 0; i < records.length; i++) {
                listStr += '<tr> <td>' + (i + 1) + '</td> <td>' + records[i].DRUG_NAME + '</td> <td>' + records[i].DRUG_SPEC + '</td> ' +
                    '<td>' + records[i].UNITS + '</td> <td>' + records[i].TOTAL_AMOUNT + '</td> <td>' + records[i].DEPT_NAME + '</td> </tr>';
            }
            $("#panel-0 table tbody").html(listStr);
        }
    })
}

