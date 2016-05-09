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
        var dcode = $("#drugName input").attr("name");
        if (typeof dcode == 'undefined' || dcode == '') {
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
        drugName = $("#drugName input").val();
        drugSpec = $("#drugSpec").children("option:selected").val();
        drugCode = dcode;
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
}

/**
 * 门诊科室排名
 */
function outpDept() {
    title(drugName + beginDate + "至" + endDate + "各门诊科室用量排名");
}

/**
 * 门诊医生排名
 */
function outpDoctor() {
    title(drugName + beginDate + "至" + endDate + "各门诊医生用量排名");
}

function title(con) {
    $("#rankList .des").html(con);
}

