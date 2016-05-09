/**
 * Created by Yaochao on 2016/4/28.
 */
var panel_index = 0;
$(function () {
    path("特殊管理药品监控", "在院用量统计");
    initBtn();
    drugStockList();
})

function initBtn() {
    $('#back').click(function () {
        back();
    })
}

/**
 * 获取药品库存列表
 */
function drugStockList() {

    var params = {
        page: 1
    }
    S.sdm.ihus(params, bindDrugStockData, true);

    function bindDrugStockData(data) {
        data = JSON.parse(data);
        initDrugStockList(data.pageData);
        //初始化分页
        $('#paginationDrugStockList').empty();
        $('#paginationDrugStockList').removeData("twbs-pagination");
        $('#paginationDrugStockList').unbind("page");
        $('#paginationDrugStockList').twbsPagination({
            totalPages: data.totalPages,
            visiblePages: 7,
            first: '首页',
            prev: '上一页',
            next: '下一页',
            last: '末页',
            initiateStartPageClick: false,
            onPageClick: function (event, page) {
                var params = {
                    page: page
                }
                S.sdm.ihus(params, initDrugStockList, false);
            }
        });
    }

    function initDrugStockList(data) {
        var drugStockListStr = '';
        for (var i = 0; i < data.length; i++) {
            drugStockListStr += '<tr><td>' + data[i].num + '</td><td>' + data[i].drugCode + '</td><td><a onclick=switchDetail("' + data[i].drugCode + '","' + data[i].drugName + '","' + data[i].drugSpec + '","' + data[i].firmId + '")>' + data[i].drugName + '</a></td><td>' + data[i].drugSpec + '</td><td>' + data[i].firmId + '</td><td>' + data[i].totalQuantity + '</td></tr>';
        }
        $("#panel-0 table tbody").html(drugStockListStr);
    }
}

function switchDetail(drugCode, drugName, drugSpec, firmId) {
    var params = {
        drugCode: drugCode,
        drugName: drugName,
        drugSpec: drugSpec,
        firmId: firmId
    }
    S.sdm.ihusDetail(params, initDrugStockDetailList);
    panel_index = 1;
    $("#panel-0").addClass("hide");
    $("#panel-1").removeClass("hide");
    $("#panel-1 .des").html(drugName);
    function initDrugStockDetailList(data) {
        data = JSON.parse(data);
        var detailStr = '';
        for (var i = 0; i < data.length; i++) {
            var supply = Number(data[i].SUPPLY_INDICATOR);
            var supplyStr = '<span class="red-color">不可供</span>';
            if (supply == 1) {
                supplyStr = "可供"
            }
            detailStr += '<tr> <td>' + data[i].num + '</td> <td>' + data[i].STORAGE_NAME + '</td> <td>' + data[i].DRUG_CODE + '</td>' +
                ' <td>' + data[i].DRUG_NAME + '</td> <td>' + data[i].DRUG_SPEC + '</td> <td>' + data[i].UNITS + '</td> <td>' + data[i].FIRM_ID + '</td>' +
                ' <td>' + data[i].BATCH_NO + '</td> <td>' + data[i].TOTAL_QUANTITY + '</td> <td>' + supplyStr + '</td> </tr>';
        }
        $("#panel-1 table tbody").html(detailStr);
    }
}