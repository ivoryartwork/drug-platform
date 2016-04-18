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
        $(".x-datepicker-month").datepicker({
            format: "yyyy-mm",
            language: "zh-CN",
            minViewMode: 1,
            autoclose: true
        });
    }

    return {
        init: function () {
            initSidebar();
            initDatepicker();
        }
    }
}();