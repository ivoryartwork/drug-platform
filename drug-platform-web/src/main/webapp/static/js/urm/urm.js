/**
 * Created by Yaochao on 2016/4/28.
 */
$(function () {
    path("用户权限管理", "");
    initBtn();
})

function initBtn() {
    $("#createUserBtn").click(function () {
        var username = $("#username-0").val();
        if (username == '') {
            createUserResult("用户名不能为空")
            $("#username-0").focus();
            return;
        }
        var nickname = $("#nickname-0").val();
        var password = $("#password-0").val();
        if (password == '') {
            createUserResult("密码不能为空")
            $("#password-0").focus();
            return;
        }
        var phoneNumber = $("#phoneNumber-0").val();
        var email = $("#email-0").val();
        var deptCode = $("#dept-0").children("option:selected").val();
        var deptName = $("#dept-0").children("option:selected").html();
        var modulesCode = new Array();
        var modulesName = new Array();
        $(".added-drugs .x-glyphicon-remove").each(function (i) {
            modulesCode[i] = $(this).attr("index");
            modulesName[i] = $(this).parent().children(".similar-drug-name").html();
        })
        if (modulesCode.length == 0) {
            createUserResult("请至少选择一个授权模块")
            return;
        }
        //保存用户并获取用户Id

        var addedUserId;
        //将用户信息添加到前台表中
        var modulesStr = '';
        for (var i = 0; i < modulesCode.length; i++) {
            modulesStr += '<span name="' + modulesCode[i] + '">[' + modulesName[i] + ']</span>';
        }
        var user = '<tr> <td>1</td> <td>' + username + '</td> <td>' + nickname + '</td> <td>' + deptName + '</td> <td>' + userInfo.userName + '</td> <td>' + phoneNumber + '</td> <td>' + email + '</td> <td>' + modulesStr + '</td> <td> <div class="btn-group"> <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown"aria-haspopup="true" aria-expanded="false">操作 <span class="caret"></span> </button> <ul class="dropdown-menu"> <li><a onclick=editUser("' + addedUserId + '",this)>修改</a></li> <li><a onclick=deleteUser("' + addedUserId + '",this)>删除</a></li> </ul> </div> </td> </tr>'
        $("#userList tbody").prepend(user);
        $("#userList tbody tr").each(function (i) {
            $(this).children("td").eq(0).html(i + 1);
        })
        alert("用户创建成功");
        $("#createUser").modal('hide');
    })
}

function createUserResult(con) {
    $("#createUserResult").removeClass("hide");
    $("#createUserResult").html(con);
}

function removeDrug(remove) {
    var index = $(remove).attr("index");
    $(remove).parent().remove();
    $(".similar-drug[index='" + index + "']").removeClass("disabled")
}

function editUser(userId, target) {
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
}

function clearCreateUserModal() {
    $("#createUserResult").addClass("hide");
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
        var added = '<span class="similar-drug-selected"><span class="similar-drug-name">' + name + '</span><span onclick="removeDrug(this)" class="glyphicon glyphicon-remove x-glyphicon-remove" index="' + index + '"></span></span>';
        $(this).parent().parent().children(".added-drugs").append(added);
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