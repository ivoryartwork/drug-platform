/**
 * Created by Yaochao on 2016/4/18.
 */
var Main = function () {

    //初始化侧边栏
    var initSidebar = function () {
        $(".nav-sidebar").each(function (i) {
            var activeItemIndex = localStorage.getItem(userInfo.userName + "itemIndex");
            var active = localStorage.getItem(userInfo.userName + "itemIndexHead" + i);
            if (active == 1) {
                $(this).children(".active").siblings("li").show();
                $(this).children(".active").addClass("fold");
            }
            $(this).children(".active").attr("index", i);
            $(this).children(".sidebar-item").each(function (k) {
                if (activeItemIndex == i + "-" + k) {
                    $(this).addClass("select")
                }
                $(this).attr("index", i + "-" + k);
            })
        })
        $(".nav-sidebar .active").click(function () {
            var index = $(this).attr("index");
            if ($(this).hasClass("fold")) {
                $(this).siblings("li").hide(300);
                $(this).removeClass("fold");
                localStorage.setItem(userInfo.userName + "itemIndexHead" + index, 0);
            } else {
                $(this).siblings("li").show(300);
                $(this).addClass("fold");
                localStorage.setItem(userInfo.userName + "itemIndexHead" + index, 1);
            }
        });
        $(".nav-sidebar .sidebar-item").click(function () {
            localStorage.setItem(userInfo.userName + "itemIndex", $(this).attr("index"));
        })
    }

    var initDatepicker = function () {
        $(".x-datepicker-day").datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true
        });
    }

    var searchFormInit = function () {
        //获取所有科室
        $.ajax({
            url: 'info/depts',
            success: function (data) {
                data = JSON.parse(data);
                var options = ' <option value="none">不分科室</option>';
                for (var i = 0; i < data.length; i++) {
                    options += ' <option value="' + data[i].deptCode + '">' + data[i].deptName + '</option>';
                }
                $(".dept select").html(options);
            }
        });

        $(".dept select").on('change', function () {
            var $dept = $(this);
            var deptCode = $dept.children("option:selected").val()
            if (deptCode == 'none') {
                $dept.parent().siblings('.dept-ward').addClass("hide");
            } else {
                //加载科室相应病区
                $.ajax({
                    url: 'info/deptWards?deptCode=' + deptCode,
                    success: function (data) {
                        data = JSON.parse(data);
                        var options = ' <option value="none">不分病区</option>';
                        for (var i = 0; i < data.length; i++) {
                            options += ' <option value="' + data[i].wardCode + '">' + data[i].wardName + '</option>';
                        }
                        var $ward = $dept.parent().siblings('.dept-ward');
                        $ward.children('select').html(options);
                        $ward.removeClass("hide");
                    }
                })
            }
        })
    }

    return {
        init: function () {
            initSidebar();
            initDatepicker();
            searchFormInit();
        }
    }
}();

//显示当前页面路径
function path(main_tilte, sub_title) {
    $(".main_title").html(main_tilte);
    $(".sub_title").html(sub_title);
}

function back() {
    if (panel_index <= 0) {
        return;
    } else {
        $("#panel-" + panel_index).addClass("hide");
        panel_index = panel_index - 1;
        $("#panel-" + panel_index).removeClass("hide");
    }
}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

Date.prototype.AddDate = function (days) {
    var newDate = this;
    newDate.setDate(newDate.getDate() + days);
    //alert(newDate.Format(dateFormatStr));
    return newDate;
}