/**
 * Created by Yaochao on 2016/5/20.
 */
var username, beginDate, endDate
$(function () {
    path("用户管理", "用户日志管理");
    initUserOptLogList();
    initBtn();
})

function initBtn() {
    $(".search-btn").click(function () {
        username = $("#username").val();
        var $beginDateInput = $(this).parent().find('input[name = "begin"]');
        var $endDateInput = $(this).parent().find('input[name = "end"]');
        var begin = $beginDateInput.val();
        var end = $endDateInput.val();
        if (begin == '' && end != '') {
            $beginDateInput.focus();
            return;
        }
        if (begin != '' && end == '') {
            $endDateInput.focus();
            return;
        }
        beginDate = begin;
        endDate = end;
        if (username == '') {
            username = undefined;
        }
        if (beginDate == '' || endDate == '') {
            beginDate = undefined;
            endDate = undefined;
        }
        initUserOptLogList();
    })
}

function initUserOptLogList() {
    $("#panel-0 table tbody").html("");
    var params = {
        username: username,
        beginDate: beginDate,
        endDate: endDate,
        page: 1
    }

    S.urm.userOptLogList(params, function (data) {
        data = JSON.parse(data);
        bindUserOptLogData(data.pageData);
        //初始化分页
        $('#paginationUserOptLogList').empty();
        $('#paginationUserOptLogList').removeData("twbs-pagination");
        $('#paginationUserOptLogList').unbind("page");
        $('#paginationUserOptLogList').twbsPagination({
            totalPages: data.totalPages,
            visiblePages: 7,
            first: '首页',
            prev: '上一页',
            next: '下一页',
            last: '末页',
            initiateStartPageClick: false,
            onPageClick: function (event, page) {
                params.page = page;
                S.sdm.userOptLogList(params, bindUserOptLogData, false);
            }
        });
    }, true);

    function bindUserOptLogData(data) {
        var listStr = '';
        for (var i = 0; i < data.length; i++) {
            listStr += '<tr> <td>' + data[i].num + '</td> <td>' + data[i].username + '</td> <td>' + data[i].optDate + '</td> <td>' + data[i].optDes + '</td> </tr>';
        }
        $("#panel-0 table tbody").html(listStr);
    }
}