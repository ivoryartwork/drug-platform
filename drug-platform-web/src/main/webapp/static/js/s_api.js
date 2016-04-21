/**
 * Created by Yaochao on 2016/4/20.
 */
$(function (s) {
    s.thanDrugs = {};

    s.thanDrugs.global = function (params, callback) {
        $.ajax({
            url: 'mmi/thanDrugs/global',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.thanDrugs.dept = function (params, callback) {
        $.ajax({
            url: 'mmi/thanDrugs/dept',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.thanDrugs.ward = function (params, callback) {
        $.ajax({
            url: 'mmi/thanDrugs/ward',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.drugTakeRecords = {};
    s.drugTakeRecords.byTime = function (params, callback) {
        $.ajax({
            url: 'tmpi/drugTakeRecords/byTime',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.drugTakeRecords.byDrug = function (params, callback) {
        $.ajax({
            url: 'tmpi/drugTakeRecords/byDrug',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }
}(window.S = {}))