/**
 * Created by Yaochao on 2016/6/1.
 */
var reportTime;
$(function () {
    path("月报表", "科室药费比")
    initBtn();
})

function initBtn() {
    $(".search-btn").click(function () {
        var time = $("#time").val();
        if (time == '') {
            $("#time").focus();
            return;
        }
        reportTime = time;
        var params = {
            time: time
        }
        S.thanDrugs.mReport(params, function (data) {
            data = JSON.parse(data);
            var len = data.length;
            var listStr = '';
            for (var i = 0; i < len; i++) {
                listStr += '<tr> <td>' + data[i].deptName + '</td> <td>' + data[i].drugCost + '</td> <td>' + data[i].treatCost + '</td> <td>' + data[i].totalCost + '</td> <td>' + data[i].rate + '</td> <td>' + data[i].targetRate + '</td> <td>' + data[i].rank + '</td> </tr>';
            }
            $("#panel-0").removeClass("hide");
            $("#panel-0 table tbody").html(listStr);
            $(".des").html(time + "各科室药费比");
        })
    })

    $(".x-export-btn").click(function () {
        $("#exportForm input").val(reportTime);
        $("#exportForm").submit();
    })
}

function title(title) {
    $(".des").html(title);
}