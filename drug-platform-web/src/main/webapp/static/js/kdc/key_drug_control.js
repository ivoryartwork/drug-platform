/**
 * Created by Yaochao on 2016/4/19.
 */
$(function () {
    path('用药指标监控', '重点药品监控');
    initBtn();
    bindDrugMonitorData('');
})

function initBtn() {
    var outpatientThead = '<tr><th>序号</th><th>药品代码</th><th>药品名称</th><th>用药途径</th><th>单次用量</th><th>频次</th><th>规格厂家</th>' +
        '<th>本月不合理处方数</th><th>本月不合理用药人数</th><th>操作</th></tr>';
    var hospitalizedThead = '<tr><th>序号</th><th>药品代码</th><th>药品名称</th><th>用药途径</th><th>单次用量</th><th>频次</th><th>规格厂家</th>' +
        '<th>本月不合理医嘱数</th><th>本月不合理用药人数</th><th>操作</th></tr>';
    $("#drugsMonitorSwitch").on('change', function () {
        var type = $(this).children("option:selected").val();
        if (type == 0) {
            //门诊
            $("#drugList thead").html(outpatientThead)
            bindDrugMonitorData('');
        } else {
            //住院
            $("#drugList thead").html(hospitalizedThead)
            bindDrugMonitorData('');
        }
    })

    $("#similarDrugs").on('focus', function () {
        $('#addSimilarDrugs').removeClass("hide");
    })

    $(".similar-drug").click(function () {
        if ($(this).hasClass("disabled")) {
            return;
        }
        var name = $(this).html();
        var index = $(this).attr("index")
        var added = '<span class="similar-drug-selected">' + name + '<span onclick="removeDrug(this)" class="glyphicon glyphicon-remove x-glyphicon-remove" index="' + index + '"></span></span>';
        $(".added-drugs").append(added);
        $(this).addClass("disabled");
    })
}

function removeDrug(remove) {
    var index = $(remove).attr("index");
    $(remove).parent().remove();
    $(".similar-drug[index='"+index+"']").removeClass("disabled")
}

//绑定药品监控目录数据
function bindDrugMonitorData(data) {
    //var len = data.length;
    var len = 1;
    var content = '';
    for (var i = 0; i < len; i++) {
        content += '<tr><td>1</td><td>0107003CI2</td><td>复方氨基酸胶囊</td><td>口服</td>' +
            '<td><2粒</td><td><1/日</td><td>10wu粤天普生化</td><td>263</td><td>26</td>' +
            '<td><div class="btn-group"><button type="button" class="btn btn-danger btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">操作 <span class="caret"></span> </button> ' +
            '<ul class="dropdown-menu"><li><a onclick="">修改</a></li><li><a onclick="">删除</a></li></ul></div></td></tr>';
    }
    $("#drugList tbody").html(content);
}