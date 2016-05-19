<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/28
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row">
    <div class="panel">
        <p class="explanation">用户列表<a class="right x-export-btn" onclick="createUser()">创建用户</a></p>
        <table class="table table-bordered" id="userList">
            <thead>
            <tr>
                <th>序号</th>
                <th>登录名</th>
                <th>昵称</th>
                <th>部门/科室</th>
                <th>授权用户</th>
                <th>手机号码</th>
                <th>邮箱地址</th>
                <th>权限</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<div class="modal fade" id="createUser" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">创建用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录名：</label>

                        <div class="col-sm-7">
                            <input type="text" id="username-0" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">昵称：</label>

                        <div class="col-sm-7">
                            <input type="text" id="nickname-0" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户密码：</label>

                        <div class="col-sm-7">
                            <input type="text" id="password-0" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号：</label>

                        <div class="col-sm-7">
                            <input type="text" id="phoneNumber-0" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电子邮箱：</label>

                        <div class="col-sm-7">
                            <input type="email" id="email-0" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">部门/科室：</label>

                        <div class="col-sm-7">
                            <select class="form-control" id="dept-0">
                                <option value="0">药材处</option>
                                <option value="2">骨科</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">授权模块：</label>

                        <div class="col-sm-7">
                            <div class="similar-drugs-panel">
                                <label>已授权：</label>

                                <div class="added-drugs">
                                </div>
                                <label>未授权：</label>

                                <div class="similar-drugs">
                                    <%--<span class="similar-drug" index="0">用药指标监控</span>--%>
                                    <%--<span class="similar-drug" index="1">重点药品监控</span>--%>
                                    <%--<span class="similar-drug" index="2">取药及处方查询</span>--%>
                                    <%--<span class="similar-drug" index="3">用药排名</span>--%>
                                    <%--<span class="similar-drug" index="4">月报表</span>--%>
                                    <%--<span class="similar-drug" index="5">特殊药品监控</span>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="createUserBtn" class="btn btn-primary">创建</button>
                <p class="text-center red-color hide" id="createUserResult"></p>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editUser" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录名：</label>

                        <div class="col-sm-7">
                            <input type="hidden" id="userId">
                            <input type="text" id="username-1" class="form-control" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">昵称：</label>

                        <div class="col-sm-7">
                            <input type="text" id="nickname-1" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户密码：</label>

                        <div class="col-sm-7">
                            <input type="text" id="password-1" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号：</label>

                        <div class="col-sm-7">
                            <input type="text" id="phoneNumber-1" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电子邮箱：</label>

                        <div class="col-sm-7">
                            <input type="email" id="email-1" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">部门/科室：</label>

                        <div class="col-sm-7">
                            <select class="form-control" id="dept-1">
                                <option value="0">药材处</option>
                                <option value="2">骨科</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">授权模块：</label>

                        <div class="col-sm-7">
                            <div class="similar-drugs-panel">
                                <label>已授权：</label>

                                <div class="added-drugs">
                                </div>
                                <label>未授权：</label>

                                <div class="similar-drugs">
                                    <span class="similar-drug" index="0">用药指标监控</span>
                                    <span class="similar-drug" index="1">重点药品监控</span>
                                    <span class="similar-drug" index="2">取药及处方查询</span>
                                    <span class="similar-drug" index="3">用药排名</span>
                                    <span class="similar-drug" index="4">月报表</span>
                                    <span class="similar-drug" index="5">特殊药品监控</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="editUserBtn">保存</button>
                <p class="text-center red-color hide" id="editUserResult"></p>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/static/js/urm/urm.js"/> "></script>
</body>
</html>
