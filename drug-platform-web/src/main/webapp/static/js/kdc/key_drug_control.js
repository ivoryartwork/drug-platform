/**
 * Created by Yaochao on 2016/4/19.
 */
var panel_index = 0;
$(function () {
    path('重点药品监控', '');
    initBtn();
    bindDrugMonitorData("outp");
})

function initBtn() {

    //导出按钮
    $("#exportBtn").click(function () {
        var type = $("#drugsMonitorSwitch option:selected").val();
        if (type == 0) {
            $("#type").val("outp")
        } else {
            $("#type").val("inp")
        }
        $("#export").submit();
    })

    $('#back').click(function () {
        back();
    })
    var outpatientThead = '<tr><th>序号</th><th>药品代码</th><th>药品名称</th><th>用药途径</th><th>单次用量</th><th>频次</th><th>规格厂家</th>' +
        '<th>本月不合理处方数</th><th>本月不合理用药人数</th><th>操作</th></tr>';
    var hospitalizedThead = '<tr><th>序号</th><th>药品代码</th><th>药品名称</th><th>用药途径</th><th>单次用量</th><th>频次</th><th>规格厂家</th>' +
        '<th>本月不合理医嘱数</th><th>本月不合理用药人数</th><th>操作</th></tr>';
    $("#drugsMonitorSwitch").on('change', function () {
        var type = $(this).children("option:selected").val();
        if (type == 0) {
            //门诊
            $("#drugList thead").html(outpatientThead)
            bindDrugMonitorData('outp');
        } else {
            //住院
            $("#drugList thead").html(hospitalizedThead)
            bindDrugMonitorData('inp');
        }
    })

    $(".similar-drug").click(function () {
        similarDrugSelectEvent();
    })

    $("#addDrug").click(function () {
        clearAddDrugModal();
        $("#addDrugModal").modal("show");
    })

    $("#addKeyDrug").click(function () {
        var keyDrugName = $(".keyDrugName").val();
        if (keyDrugName == '') {
            $(".keyDrugName").focus();
            return;
        }
        var $keyDrugSpecSelect = $(".keyDrugSpec option:selected")
        var keyDrugSpec = $keyDrugSpecSelect.html();
        var keyDrugCode = $keyDrugSpecSelect.val();
        var type = $(".type option:selected").val();
        var frequency0 = $(".frequency").find("select").eq(0).find("option:selected").val();
        var frequency1 = $(".frequency").find("select").eq(1).find("option:selected").val();
        //var frequency = frequency0 + "_" + frequency1;
        var frequency = frequency0 + frequency1;

        var dose0 = $(".dose").find("select").eq(0).find("option:selected").val();
        var doseVal = $(".dose").find("input").val();
        if (doseVal == '') {
            $(".dose").find("input").focus();
            return;
        }
        var dose1 = $(".dose").find("select").eq(1).find("option:selected").val();
        //var dose = dose0 + "_" + doseVal + "_" + dose1;
        var dose = dose0 + doseVal + dose1;

        var singleDose0 = $(".singleDose").find("select").eq(0).find("option:selected").val();
        var singleDoseVal = $(".singleDose").find("input").val()
        if (singleDoseVal == '') {
            $(".singleDose").find("input").focus();
            return;
        }
        var singleDose1 = $(".singleDose").find("select").eq(1).find("option:selected").val();
        //var singleDose = singleDose0 + "_" + singleDoseVal + "_" + singleDose1;
        var singleDose = singleDose0 + singleDoseVal + singleDose1;

        var routeOfAdmin = $(".routeOfAdmin option:selected").val();

        var similarDrugs = new Array();
        $("#addDrugModal .similar-drug-selected").each(function (i) {
            var tmp = $(this).find(".similar-drug-name").html();
            var m = tmp.indexOf("(");
            var n = tmp.indexOf(")");
            var drugName = tmp.substring(0, m);
            var drugSpec = tmp.substring(m + 1, n);
            var drugCode = $(this).find(".x-glyphicon-remove").attr("index");
            similarDrugs[i] = {
                drugName: drugName,
                drugSpec: drugSpec,
                drugCode: drugCode
            }
        });
        var params = {
            drugName1: keyDrugName,
            drugSpec1: keyDrugSpec,
            drugCode1: keyDrugCode,
            type: type,
            frequency: frequency,
            dose: dose,
            singleDose: singleDose,
            routeOfAdmin: routeOfAdmin,
            similarDrugs: similarDrugs
        }
        S.kdc.addKeyDrug({data: JSON.stringify(params)}, function (data) {
            if (data == SUCCESS) {
                alert("添加成功！")
                location.reload();
            } else {
                alert("添加失败！")
            }
        })
    })

    //根据名称查询重点药品
    $("#searchKeyDrug button").click(function () {
        var drugName = $("#searchKeyDrug input").val();
        if (drugName == '') {
            $("#searchKeyDrug input").focus();
            return;
        }
        S.kdc.getKeyDrugStaInfoByName({drugName: drugName}, function (data) {
            data = JSON.parse(data);
            bindKeyDrugStaData(data)
        })
    })

    $("#addDrugModal .keyDrugSpec").on("change", function () {
        var drugCode = $(this).find("option:selected").val();
        initRecomendSimilarKeyDrugList(drugCode, $("#addDrugModal .similar-drugs"));
    })

    $("#addSimilarKeyDrug").click(function () {
        var $target = $(this).parent().parent();
        var $drugNameSelect = $target.find("#drugName input");
        var drugName = $drugNameSelect.val();
        if (drugName == '') {
            return;
        }
        var $drugSpecSelect = $target.find(".drugSpec option:selected");
        var drugSpec = $drugSpecSelect.html();
        var drugCode = $drugSpecSelect.val();
        if (drugCode == '' || drugSpec == '') {
            return;
        }
        var flag = true;
        $(".added-drugs .x-glyphicon-remove").each(function () {
            if ($(this).attr("index") == drugCode) {
                flag = false;
            }
        })
        if (flag) {
            var added = '<span class="similar-drug-selected"><span class="similar-drug-name">' + drugName + "(" + drugSpec + ")" + '</span><span onclick="removeDrug(this)" class="glyphicon glyphicon-remove x-glyphicon-remove" index="' + drugCode + '"></span></span>';
            $(".added-drugs").append(added);
        }
        $drugNameSelect.val("");
        $target.find(".drugSpec select").val("");
    })
}

function removeDrug(remove) {
    var index = $(remove).attr("index");
    $(remove).parent().remove();
    $(".similar-drug[index='" + index + "']").removeClass("disabled")
}

function drugSelectCallBack(target) {
    if (target.hasClass("keyDrugSpec")) {
        var drugCode = target.find("option:selected").val();
        initRecomendSimilarKeyDrugList(drugCode, $("#addDrugModal .similar-drugs"));
    }
}

function similarDrugSelectEvent() {
    if ($(this).hasClass("disabled")) {
        return;
    }
    var name = $(this).html();
    var index = $(this).attr("index")
    var added = '<span class="similar-drug-selected"><span class="similar-drug-name">' + name + '</span><span onclick="removeDrug(this)" class="glyphicon glyphicon-remove x-glyphicon-remove" index="' + index + '"></span></span>';
    $(".added-drugs").append(added);
    $(this).addClass("disabled");
}

/**
 * 初始化同类药品列表
 * @param drugCode
 */
function initRecomendSimilarKeyDrugList(drugCode, target) {
    S.kdc.recommendSimilarKeyDrugList({drugCode: drugCode}, function (data) {
        data = JSON.parse(data);
        var list = "";
        for (var i = 0; i < data.length; i++) {
            list += ' <span class="similar-drug" index="' + data[i].drugCode + '">' + data[i].drugName + '(' + data[i].drugSpec + ')</span>';
        }
        target.html(list);
        $(".similar-drug").unbind("click");
        $(".similar-drug").bind("click", similarDrugSelectEvent);
    })
}
/**
 * 清空添加药品表单
 */
function clearAddDrugModal() {
    $("#addDrugModal .keyDrugName").val("");
    $("#addDrugModal .keyDrugSpec").val("");
    $("#addDrugModal .type option").eq(0).attr("selected", true);
    $("#addDrugModal .frequency").find("select").eq(0).find("option").eq(0).attr("selected", true);
    $("#addDrugModal .frequency").find("select").eq(1).find("option").eq(0).attr("selected", true);

    $("#addDrugModal .dose").find("select").eq(0).find("option").eq(0).attr("selected", true);
    $("#addDrugModal .dose").find("input").val("");
    $("#addDrugModal .dose").find("select").eq(1).find("option").eq(0).attr("selected", true);

    $("#addDrugModal .singleDose").find("select").eq(0).find("option").eq(0).attr("selected", true);
    $("#addDrugModal .singleDose").find("input").val("")
    $("#addDrugModal .singleDose").find("select").eq(1).find("option").eq(0).attr("selected", true);

    var routeOfAdmin = $("#addDrugModal .routeOfAdmin").find("option").eq(0).attr("selected", true);

    $("#addDrugModal .added-drugs").html("");
    $("#addDrugModal .similar-drugs").html("");
}

//绑定药品监控目录数据
function bindDrugMonitorData(type) {
    var params = {
        type: type,
        pageNum: 1
    }
    S.kdc.getKeyDrugStaInfo(params, function (data) {
        data = JSON.parse(data);
        bindKeyDrugStaData(data.pageData);
        //初始化分页
        $('#keyDrugPagination').empty();
        $('#keyDrugPagination').removeData("twbs-pagination");
        $('#keyDrugPagination').unbind("page");
        $('#keyDrugPagination').twbsPagination({
            totalPages: data.totalPages,
            visiblePages: 7,
            first: '首页',
            prev: '上一页',
            next: '下一页',
            last: '末页',
            initiateStartPageClick: false,
            onPageClick: function (event, page) {
                params.page = page;
                S.sdm.userOptLogList(params, bindKeyDrugStaData, false);
            }
        });
    }, true);

}

function bindKeyDrugStaData(data) {
    var content = '';
    for (var i = 0; i < data.length; i++) {
        content += '<tr><td>' + data[i].num + '</td><td>' + data[i].drugCode + '</td><td><a onclick=switchToDetail("' + data[i].drugCode + '")>' + data[i].drugName + '</a></td><td>' + data[i].routeOfAdmin + '</td>' +
            '<td>' + data[i].singleDose + '</td><td>' + data[i].frequency + '</td><td>' + data[i].drugSpec + '</td><td>' + data[i].up + '</td><td>' + data[i].idu + '</td>' +
            '<td><div class="btn-group"><button type="button" class="btn btn-danger btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">操作 <span class="caret"></span> </button> ' +
            '<ul class="dropdown-menu"><li><a onclick="">修改</a></li><li><a onclick=deleteKeyDrug("' + data[i].drugCode + '",this)>删除</a></li></ul></div></td></tr>';
    }
    $("#panel-0 tbody").html(content);
    panel_index = 0;
    $("#panel-1").addClass("hide");
    $("#panel-0").removeClass("hide");
}

function switchToDetail() {
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
}

/**
 * 删除重点药品
 * @param drugCode
 * @param target
 */
function deleteKeyDrug(drugCode, target) {
    S.kdc.deleteKeyDrug({drugCode: drugCode}, function (data) {
        if (data == SUCCESS) {
            $(target).parent().parent().parent().parent().parent().remove();
        } else {
            alert("删除重点药品失败")
        }
    })
}