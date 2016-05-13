/**
 * Created by Yaochao on 2016/4/28.
 */
$(function () {
    path("用户权限管理", "");
    initBtn();
})

function initBtn() {

}

function removeDrug(remove) {
    var index = $(remove).attr("index");
    $(remove).parent().remove();
    $(".similar-drug[index='" + index + "']").removeClass("disabled")
}

function editUser() {
    $("#editUser").modal('show');
}

function createUser() {
    $("#createUser").modal('show');
    clearCreateUserModal();
    S.urm.authModules(ininAuthModulesList);

    function ininAuthModulesList(data) {
        data = JSON.parse(data);
        var listStr = '';
        for (var i = 0; i < data.length; i++) {
            listStr += '<span class="similar-drug" index="' + data[i].moduleCode + '">' + data[i].moduleName + '</span>';
        }
        $("#createUser .similar-drugs").html(listStr);
        bindAuthModuleSelectEvent($("#createUser .similar-drug"));
    }

    //var username = $("#username").val();
    //var nickname = $("#nickname").val();
    //var password = $("#password").val();
    //var phoneNumber = $("#phoneNumber").val();
    //var email = $("#email").val();
    //var deptCode = $("#dept").children("option:selected").val();
    //$(".added-drugs .similar-drug-selected .x-glyphicon-remove").each(function () {
    //    console.log($(this).attr("index"))
    //})
}

function clearCreateUserModal() {
    $("#username-0").val("");
    $("#nickname-0").val("");
    $("#password-0").val("");
    $("#phoneNumber-0").val("");
    $("#email-0").val("");
    $("#dept-0").children("option").each(function (i) {
        if (i == 0) {
            $(this).attr("selected", true);
        } else {
            $(this).attr("selected", false);
        }
    });
    $("#createUser .similar-drug").html("");
    $("#createUser .added-drugs").html("");
}

function bindAuthModuleSelectEvent(target) {
    target.unbind("click");
    target.bind("click", function () {
        if ($(this).hasClass("disabled")) {
            return;
        }
        var name = $(this).html();
        var index = $(this).attr("index")
        var added = '<span class="similar-drug-selected">' + name + '<span onclick="removeDrug(this)" class="glyphicon glyphicon-remove x-glyphicon-remove" index="' + index + '"></span></span>';
        $(".added-drugs").append(added);
        $(this).addClass("disabled");
    });

    //$(".similar-drug").click(function () {
    //    if ($(this).hasClass("disabled")) {
    //        return;
    //    }
    //    var name = $(this).html();
    //    var index = $(this).attr("index")
    //    var added = '<span class="similar-drug-selected">' + name + '<span onclick="removeDrug(this)" class="glyphicon glyphicon-remove x-glyphicon-remove" index="' + index + '"></span></span>';
    //    $(".added-drugs").append(added);
    //    $(this).addClass("disabled");
    //})
}

function deleteUser(userId, target) {
    $(target).parent().parent().parent().parent().parent().remove();
}