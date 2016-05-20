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

    s.sdm = {}
    s.sdm.ihus = function (params, callback, init) {
        $.ajax({
            url: 'sdm/ihus',
            data: params,
            type: 'get',
            timeout: 10000,
            success: function (data) {
                if (init) {
                    callback(data);
                } else {
                    data = JSON.parse(data);
                    callback(data.pageData);
                }
            }
        });
    }

    s.sdm.ihusDetail = function (params, callback) {
        $.ajax({
            url: 'sdm/ihusDetail',
            data: params,
            type: 'post',
            timeout: 10000,
            success: function (data) {
                callback(data);
            }
        });
    }

    s.sdm.spta = function (params, callback) {
        $.ajax({
            url: 'sdm/spta',
            data: params,
            type: 'post',
            timeout: 10000,
            success: function (data) {
                callback(data);
            }
        });
    }

    s.drugAmount = {}
    s.drugAmount.rank = function (params, callback) {
        $.ajax({
            url: 'mmi/drugAmount/rank',
            data: params,
            type: 'post',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.drugAmount.singleGlobal = function (params, callback) {
        $.ajax({
            url: 'mmi/drugAmount/singleGlobal',
            data: params,
            type: 'post',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.par = {}
    s.par.list = function (params, callback, init) {
        $.ajax({
            url: 'par/list',
            data: params,
            type: 'post',
            success: function (data) {
                if (init) {
                    callback(data);
                } else {
                    data = JSON.parse(data);
                    callback(data.pageData);
                }
            }
        });
    }

    s.par.listDetail = function (params, callback) {
        $.ajax({
            url: 'par/list/detail',
            data: params,
            type: 'post',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.par.listByDoctor = function (params, callback, init) {
        $.ajax({
            url: 'par/listByDoctor',
            data: params,
            type: 'post',
            success: function (data) {
                if (init) {
                    callback(data);
                } else {
                    data = JSON.parse(data);
                    callback(data.pageData);
                }
            }
        });
    }

    s.urm = {}
    s.urm.authModules = function (callback) {
        $.ajax({
            url: 'urm/authModules',
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.urm.createUser = function (params, callback) {
        $.ajax({
            url: 'urm/createUser',
            type: 'post',
            data: params,
            success: function (data) {
                callback(data);
            }
        });
    }

    s.urm.deleteUser = function (params, callback) {
        $.ajax({
            url: 'urm/deleteUser',
            type: 'post',
            data: params,
            success: function (data) {
                callback(data);
            }
        });
    }

    s.urm.userList = function (callback) {
        $.ajax({
            url: 'urm/userList',
            type: 'get',
            success: function (data) {
                callback(data);
            }
        });
    }

    s.urm.userInfo = function (params, callback) {
        $.ajax({
            url: 'urm/userInfo',
            type: 'get',
            data: params,
            success: function (data) {
                callback(data);
            }
        });
    }

    s.urm.updateUser = function (params, callback) {
        $.ajax({
            url: 'urm/updateUser',
            type: 'post',
            data: params,
            success: function (data) {
                callback(data);
            }
        });
    }

    s.urm.userOptLogList = function (params, callback, init) {
        $.ajax({
            url: 'urm/userOptLogList',
            type: 'get',
            data: params,
            success: function (data) {
                if (init) {
                    callback(data);
                } else {
                    data = JSON.parse(data);
                    callback(data.pageData);
                }
            }
        });
    }
}(window.S = {}))