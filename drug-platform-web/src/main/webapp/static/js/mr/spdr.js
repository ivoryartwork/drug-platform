/**
 * Created by Yaochao on 2016/4/27.
 */
var beginDate, endDate, drugCode, drugSpec, drugName

$(function () {
    path("用药排名", "单品种药品用量排名");
    initBtn();
})

function initBtn() {

    $(".search-panel .search-btn").click(function () {
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
        drugName = drug_name;
        drugCode = $("#drugSpec").children("option:selected").val();
        if (drugCode == 'none') {
            return;
        }
        drugSpec = $("#drugSpec").children("option:selected").html();
        beginDate = begin;
        endDate = end;
        $("#rankList").removeClass("hide");
        deptRank();
    });

    $("#selectType").on("change", function () {
        var type = $(this).val();
        if (type == 0) {
            deptRank();
        }
        else if (type == 1) {
            wardRank();
        }
        else if (type == 2) {
            doctorRank();
        }
        else if (type == 3) {
            outpDept();
        }
        else if (type == 4) {
            outpDoctor();
        }
    })
}

/**
 * 科室排名
 */
function deptRank() {
    title(drugName + beginDate + "至" + endDate + "各科室用量排名");
    var params = {
        drugCode: drugCode,
        drugSpec: drugSpec,
        beginDate: beginDate,
        endDate: endDate,
        rankBy: 'dept'
    }
    S.drugAmount.rank(params, bindDeptRankData);
    function bindDeptRankData(data) {
        data = JSON.parse(data);
        var listStr = '';
        if (data.length > 0) {
            var headStr = '<tr> <th>序号</th> <th>科室</th> <th>用量（' + data[0].UNITS + '）</th> <th>金额（元）</th> </tr>'
            for (var i = 0; i < data.length; i++) {
                listStr += ' <tr> <td>' + data[i].NUM + '</td> <td>' + data[i].DEPT_NAME + '</td> <td>' + data[i].AMOUNT + '</td> <td>' + data[i].TOTAL + '</td> </tr>';
            }
            $("#rankList table thead").html(headStr);
            $("#rankList table tbody").html(listStr);
        }
    }
}

/**
 * 病区排名
 */
function wardRank() {
    title(drugName + beginDate + "至" + endDate + "各病区用量排名");
}

/**
 * 医生用量排名
 */
function doctorRank() {
    title(drugName + beginDate + "至" + endDate + "各医生用量排名");
    var params = {
        drugCode: drugCode,
        drugSpec: drugSpec,
        beginDate: beginDate,
        endDate: endDate,
        rankBy: 'doctor'
    }
    S.drugAmount.rank(params, bindDeptRankData);
    function bindDeptRankData(data) {
        data = JSON.parse(data);
        var listStr = '';
        if (data.length > 0) {
            var headStr = '<tr> <th>序号</th> <th>科室</th> <th>医生</th><th>用量（' + data[0].UNITS + '）</th> <th>金额（元）</th> <th>用药人数</th></tr>'
            for (var i = 0; i < data.length; i++) {
                listStr += ' <tr> <td>' + data[i].NUM + '</td> <td>' + data[i].DEPT_NAME + '</td><td>' + data[i].DOCTOR + '</td> <td>' + data[i].AMOUNT + '</td> <td>' + data[i].TOTAL + '</td> <td>' + data[i].PATIENTAMOUNT + '</td></tr>';
            }
            $("#rankList table thead").html(headStr);
            $("#rankList table tbody").html(listStr);
        }
    }
}

/**
 * 门诊科室排名
 */
function outpDept() {
    title(drugName + beginDate + "至" + endDate + "各门诊科室用量排名");
    var params = {
        drugCode: drugCode,
        drugSpec: drugSpec,
        beginDate: beginDate,
        endDate: endDate,
        rankBy: 'outpDept'
    }
    S.drugAmount.rank(params, bindDeptRankData);
    function bindDeptRankData(data) {
        data = JSON.parse(data);
        var listStr = '';
        if (data.length > 0) {
            var headStr = '<tr> <th>序号</th> <th>门诊科室</th> <th>用量（' + data[0].UNITS + '）</th> <th>金额（元）</th> </tr>'
            for (var i = 0; i < data.length; i++) {
                listStr += ' <tr> <td>' + data[i].NUM + '</td> <td>' + data[i].DEPT_NAME + '</td> <td>' + data[i].AMOUNT + '</td> <td>' + data[i].TOTAL + '</td> </tr>';
            }
            $("#rankList table thead").html(headStr);
            $("#rankList table tbody").html(listStr);
        }
    }
}

/**
 * 门诊医生排名
 */
function outpDoctor() {
    title(drugName + beginDate + "至" + endDate + "各门诊医生用量排名");
    var params = {
        drugCode: drugCode,
        drugSpec: drugSpec,
        beginDate: beginDate,
        endDate: endDate,
        rankBy: 'outpDoctor'
    }
    S.drugAmount.rank(params, bindDeptRankData);
    function bindDeptRankData(data) {
        data = JSON.parse(data);
        var listStr = '';
        if (data.length > 0) {
            var headStr = '<tr> <th>序号</th> <th>门诊科室</th> <th>医生</th><th>用量（' + data[0].UNITS + '）</th> <th>金额（元）</th> <th>用药人数</th></tr>'
            for (var i = 0; i < data.length; i++) {
                listStr += ' <tr> <td>' + data[i].NUM + '</td> <td>' + data[i].DEPT_NAME + '</td><td>' + data[i].DOCTOR + '</td> <td>' + data[i].AMOUNT + '</td> <td>' + data[i].TOTAL + '</td> <td>' + data[i].PATIENTAMOUNT + '</td></tr>';
            }
            $("#rankList table thead").html(headStr);
            $("#rankList table tbody").html(listStr);
        }
    }
}

function title(con) {
    $("#rankList .des").html(con);
}

