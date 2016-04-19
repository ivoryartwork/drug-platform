/**
 * Created by Yaochao on 2016/4/18.
 */
var Main = function () {

    //初始化侧边栏
    var initSidebar = function () {
        $(".nav-sidebar .active").click(function () {
            if ($(this).hasClass("fold")) {
                $(this).siblings("li").hide(300);
                $(this).removeClass("fold");
            } else {
                $(this).siblings("li").show(300);
                $(this).addClass("fold");
            }
        });
    }

    var initDatepicker = function () {
        $(".x-datepicker-day").datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true
        });
    }

    var searchFormInit = function () {
        $(".dept select").on('change', function () {

            var deptCode = $(this).children("option:selected").val()
            if (deptCode == 'none') {
                $(this).parent().siblings('.dept-ward').addClass("hide");
            } else {
                //加载科室相应病区
                $(this).parent().siblings('.dept-ward').removeClass("hide");

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