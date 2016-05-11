/**
 * Created by Yaochao on 2016/4/27.
 */
var panel_index = 0;
var beginDate, endDate
$(function () {
    path("用药排名", "处方金额排名");
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
        par();
    });

    $("#selectType").on("change", function () {
        var type = $(this).val();
        if (type == 0) {
            //单张处方
            par();
        } else {
            //医生处方
            parByDoctor();
        }
    })
}

function parByDoctor() {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        page: 1
    }
    S.par.listByDoctor(params, bindParByDoctorData, true);
    function initParByDoctorList(data) {
        var listStr = "";
        var headStr = '<tr> <th>序号</th> <th>开处方诊室</th> <th>医生姓名</th><th>处方金额</th> </tr>';
        for (var i = 0; i < data.length; i++) {
            listStr += ' <tr> <td>' + data[i].num + '</td> <td>' + data[i].deptName + '</td> <td>' + data[i].doctor + '</td> <td>' + data[i].total + '</td></tr>';
        }
        $("#panel-0 table tbody").html(listStr);
        $("#panel-0 table thead").html(headStr);
        title(beginDate + '至' + endDate + '医生处方排名');
    }

    function bindParByDoctorData(data) {
        data = JSON.parse(data);
        initParByDoctorList(data.pageData);
        //初始化分页
        $('#paginationParList').empty();
        $('#paginationParList').removeData("twbs-pagination");
        $('#paginationParList').unbind("page");
        $('#paginationParList').twbsPagination({
            totalPages: data.totalPages,
            visiblePages: 7,
            first: '首页',
            prev: '上一页',
            next: '下一页',
            last: '末页',
            initiateStartPageClick: false,
            onPageClick: function (event, page) {
                var params = {
                    beginDate: beginDate,
                    endDate: endDate,
                    page: page
                }
                S.par.listByDoctor(params, initParByDoctorList, false);
            }
        });
    }
}

/**
 * 单张处方排名
 */
function par() {
    var params = {
        beginDate: beginDate,
        endDate: endDate,
        page: 1
    }
    S.par.list(params, bindParData, true);
    function initParList(data) {
        var listStr = "";
        var headStr = '<tr> <th>序号</th> <th>处方号</th> <th>开处方诊室</th> <th>医生姓名</th> <th>门诊ID</th> <th>患者姓名</th> <th>处方金额</th> </tr>';
        for (var i = 0; i < data.length; i++) {
            listStr += ' <tr> <td>' + data[i].num + '</td> <td><a onclick=parDetail(' + JSON.stringify(data[i]) + ')>' + data[i].rcptNo + '</a></td> <td>' + data[i].deptName + '</td> <td>' + data[i].doctor + '</td> <td>' + data[i].visitNo + '</td> <td>' + data[i].patientName + '</td> <td>' + data[i].total + '</td> </tr>';
        }
        $("#panel-0 table tbody").html(listStr);
        $("#panel-0 table thead").html(headStr);
        panel_index = 0;
        $("#panel-0").removeClass("hide");
        $("#panel-1").addClass("hide");
        title(beginDate + '至' + endDate + '单张处方排名');
    }

    function bindParData(data) {
        data = JSON.parse(data);
        initParList(data.pageData);
        //初始化分页
        $('#paginationParList').empty();
        $('#paginationParList').removeData("twbs-pagination");
        $('#paginationParList').unbind("page");
        $('#paginationParList').twbsPagination({
            totalPages: data.totalPages,
            visiblePages: 7,
            first: '首页',
            prev: '上一页',
            next: '下一页',
            last: '末页',
            initiateStartPageClick: false,
            onPageClick: function (event, page) {
                var params = {
                    beginDate: beginDate,
                    endDate: endDate,
                    page: page
                }
                S.par.list(params, initParList, false);
            }
        });
    }
}

function parDetail(con) {
    panel_index = 1;
    $("#panel-1").removeClass("hide");
    $("#panel-0").addClass("hide");
    title('处方号' + con.rcptNo + "明细");
    $("#deptName").html(con.deptName);
    $("#doctor").html(con.doctor);
    $("#visitNo").html(con.visitNo);
    $("#patientName").html(con.patientName);
    var params = {
        visitNo: con.visitNo,
        rcptNo: con.rcptNo,
        visitDate: con.visitDate
    }
    S.par.listDetail(params, bindParDetailData);
    function bindParDetailData(data) {
        data = JSON.parse(data);
        var listStr = '';
        for (var i = 0; i < data.length; i++) {
            listStr += '<tr> <td>' + data[i].num + '</td> <td>' + con.rcptNo + '</td> ' +
                '<td>' + data[i].visitDate + '</td> <td></td> <td>' + data[i].drugCode + '</td> <td>' + data[i].drugName + '</td> ' +
                '<td>' + data[i].drugSpec + '</td> <td>' + data[i].units + '</td> <td>' + data[i].amount + '</td> <td>' + data[i].costs + '</td> </tr>';
        }
        $("#panel-1 table tbody").html(listStr);
    }

}

function title(titel) {
    $("#panel-" + panel_index + " .des").html(titel);
}

