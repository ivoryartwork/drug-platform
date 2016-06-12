/**
 * Created by Yaochao on 2016/6/1.
 */
$(function () {
    path("月报表", "科室药费比")
    initBtn();
})

function initBtn() {
    $(".search-btn").click(function () {
        var time = $("#time").val();
        var deptCode = $(".dept option:selected").val();
        if (time == '') {
            $("#time").focus();
            return;
        }
        if (deptCode == 'none') {
            $(".dept select").focus();
            return;
        }
    })
}

function title(title) {
    $(".des").html(title);
}