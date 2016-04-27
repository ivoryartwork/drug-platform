/**
 * Created by Yaochao on 2016/4/20.
 */
$(function (s) {

    //药费比
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

    //用药记录
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

    s.drugsOutPatient = {}
    s.drugsOutPatient.global = function (params, callback) {
        $.ajax({
            url: 'mmi/drugsOutPatient/global',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.drugsOutPatient.dept = function (params, callback) {
        $.ajax({
            url: 'mmi/drugsOutPatient/dept',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.ahe = {}
    s.ahe.global = function (params, callback) {
        $.ajax({
            url: 'mmi/ahe/global',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.ahe.dept = function (params, callback) {
        $.ajax({
            url: 'mmi/ahe/dept',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.ahe.ward = function (params, callback) {
        $.ajax({
            url: 'mmi/ahe/ward',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.uaa = {}
    s.uaa.global = function (params, callback) {
        $.ajax({
            url: 'mmi/uaa/global',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.uaa.dept = function (params, callback) {
        $.ajax({
            url: 'mmi/uaa/dept',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.uaa.ward = function (params, callback) {
        $.ajax({
            url: 'mmi/uaa/ward',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }


    s.poap = {}
    s.poap.global = function (params, callback) {
        $.ajax({
            url: 'mmi/poap/global',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.poap.dept = function (params, callback) {
        $.ajax({
            url: 'mmi/poap/dept',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.paed = {}
    s.paed.global = function (params, callback) {
        $.ajax({
            url: 'mmi/paed/global',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.paed.dept = function (params, callback) {
        $.ajax({
            url: 'mmi/paed/dept',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.ipuaa = {}
    s.ipuaa.global = function (params, callback) {
        $.ajax({
            url: 'mmi/ipuaa/global',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.ipuaa.dept = function (params, callback) {
        $.ajax({
            url: 'mmi/ipuaa/dept',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.ipuaa.ward = function (params, callback) {
        $.ajax({
            url: 'mmi/ipuaa/ward',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.oir = {}
    s.oir.getRecords = function (params, callback) {
        $.ajax({
            url: 'tmpi/oir',
            data: params,
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }
}(window.S = {}))