/**
 * Created by Yaochao on 2016/4/28.
 */
$(function () {
    path("用户权限管理", "");
    initBtn();
})

function initBtn() {
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
    $(".similar-drug[index='" + index + "']").removeClass("disabled")
}

function editUser() {
    $("#editUser").modal('show');
}

function deleteUser(userId, target) {
    $(target).parent().parent().parent().parent().parent().remove();
}