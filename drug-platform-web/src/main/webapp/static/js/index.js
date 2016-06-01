/**
 * Created by Yaochao on 2016/4/18.
 */
var SUCCESS = 'success';
var ERROR = 'error';

var Main = function () {

    var userFlag = userInfo.userName + userInfo.timestamp

    //初始化侧边栏
    var initSidebar = function () {
        $(".nav-sidebar").each(function (i) {
            var activeItemIndex = sessionStorage.getItem(userFlag + "itemIndex");
            var active = sessionStorage.getItem(userFlag + "itemIndexHead" + i);
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
                sessionStorage.setItem(userFlag + "itemIndexHead" + index, 0);
            } else {
                $(this).siblings("li").show(300);
                $(this).addClass("fold");
                sessionStorage.setItem(userFlag + "itemIndexHead" + index, 1);
            }
        });
        $(".nav-sidebar .sidebar-item").click(function () {
            sessionStorage.setItem(userFlag + "itemIndex", $(this).attr("index"));
        })
    }

    var initDatepicker = function () {
        $(".x-datepicker-day").datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true
        });

        $(".x-datepicker-month").datepicker({
            format: "yyyy-mm",
            language: "zh-CN",
            minViewMode: 1,
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

        $("#drugName input").keyup(function () {
            searchDrugByInput(this);
        })

        $("#drugName input").focus(function () {
            searchDrugByInput(this);
        })
    }

    function searchDrugByInput(input) {
        var inputCode = $(input).val();
        var $drugName = $(input).parent();
        var reg = /^[a-zA-Z]{1,}$/
        if (inputCode == '' || !reg.test(inputCode)) {
            $drugName.children("ul").hide();
            return;
        }
        $.ajax({
            url: 'drug/searchByInputCode',
            type: 'get',
            data: {
                inputCode: inputCode
            },
            success: function (data) {
                data = JSON.parse(data);
                var list = "";
                for (var i = 0; i < data.length; i++) {
                    list += "<li onclick=selectDrug('" + data[i] + "',this)>" + data[i] + "</li>";
                }
                $drugName.children("ul").html(list);
                $drugName.children("ul").show();
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

function selectDrug(drugName, target) {
    var $input = $(target).parent().parent().children("input");
    $(target).parent().hide();
    $input.val(drugName);
    var $drugSpecSelect = $(target).parent().parent().parent().parent().children(".drugSpec").find("select");
    $.ajax({
        url: 'drug/searchSpecByName',
        type: 'post',
        data: {
            drugName: drugName
        },
        success: function (data) {
            data = JSON.parse(data);
            var list = "";
            for (var i = 0; i < data.length; i++) {
                list += "<option value='" + data[i].DRUG_CODE + "'>" + data[i].DRUG_SPEC + "</option>";
            }
            $drugSpecSelect.html(list);
            drugSelectCallBack($drugSpecSelect);
        }
    })
}

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

function setTitle(title1, title2, title3) {
    $("#panel-" + panel_index + " .des").each(function (i) {
        if (i == 0) {
            $(this).html(title1);
        } else if (i == 1) {
            $(this).html(title2);
        } else {
            $(this).html(title3);
        }
    })
}

function initDate() {
    var now = new Date();
    endDate = now.Format(dateFormatStr);
    beginDate = now.AddDate(-30).Format(dateFormatStr);
}
var dateFormatStr = 'yyyy-MM-dd';