/**
 * Created by Yaochao on 2016/4/20.
 */
$(function (s) {
    s.thanDrugs = {};

    s.thanDrugs.global = function (params, callback) {
        $.ajax({
            url: 's/thanDrugs/global',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.thanDrugs.dept = function (params, callback) {
        $.ajax({
            url: 's/thanDrugs/dept',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.thanDrugs.ward = function (params, callback) {
        $.ajax({
            url: 's/thanDrugs/ward',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }
}(window.S = {}))