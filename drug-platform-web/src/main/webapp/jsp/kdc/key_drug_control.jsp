<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/19
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row">
    <div class="panel">
        <form class="form-inline search-from">
            <select id="drugsMonitorSwitch" class="form-control">
                <option value="0">门诊重点监管药品目录</option>
                <option value="1">住院重点监管药品目录</option>
            </select>
            &nbsp;&nbsp;
            <button id="addDrug" type="button" class="btn btn-warning x-btn" data-toggle="modal"
                    data-target="#addDrugModal">
                添加药品
            </button>

            <button class="btn btn-warning x-btn">导出</button>
            <div class="form-group right">
                <input type="text" class="form-control" placeholder="药品名称">
                <button class="btn btn-primary">搜索</button>
            </div>
        </form>
    </div>
    <%--添加药品模态框--%>
    <div class="modal fade" id="addDrugModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加药品</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">药品名称：</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">规格厂家：</label>

                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option></option>
                                    <option></option>
                                    <option></option>
                                    <option></option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">指定目录：</label>

                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>住院</option>
                                    <option>门诊</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">频次：</label>

                            <div class="col-sm-10">
                                <select class="form-control input-inline">
                                    <option>&gt;</option>
                                    <option>&lt;</option>
                                    <option>&gt;=</option>
                                    <option>&lt;=</option>
                                    <option><></option>
                                    <option>=</option>
                                </select>
                                <select class="form-control input-inline">
                                    <option>1/日</option>
                                    <option>2/日</option>
                                    <option>3/日</option>
                                    <option>4/日</option>
                                    <option>6/日</option>
                                    <option>1/晚</option>
                                    <option>1/隔日</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用量：</label>

                            <div class="col-sm-10">
                                <select class="form-control input-inline">
                                    <option>&gt;</option>
                                    <option>&lt;</option>
                                    <option>&gt;=</option>
                                    <option>&lt;=</option>
                                    <option><></option>
                                    <option>=</option>
                                </select>
                                <input type="text" class="form-control input-inline">
                                <select class="form-control input-inline">
                                    <option>ml</option>
                                    <option>mg</option>
                                    <option>g</option>
                                    <option>支</option>
                                    <option>枚</option>
                                    <option>片</option>
                                    <option>粒</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">单次用量：</label>

                            <div class="col-sm-10">
                                <select class="form-control input-inline">
                                    <option>&gt;</option>
                                    <option>&lt;</option>
                                    <option>&gt;=</option>
                                    <option>&lt;=</option>
                                    <option><></option>
                                    <option>=</option>
                                </select>
                                <input type="text" class="form-control input-inline">
                                <select class="form-control input-inline">
                                    <option>ml</option>
                                    <option>mg</option>
                                    <option>g</option>
                                    <option>支</option>
                                    <option>枚</option>
                                    <option>片</option>
                                    <option>粒</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用药途径：</label>

                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>口服</option>
                                    <option>静滴</option>
                                    <option>静推</option>
                                    <option>肌肉注射</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">同类药品：</label>

                            <div class="col-sm-10">
                                <input id="similarDrugs" class="form-control">

                                <div id="addSimilarDrugs" class="similar-drugs-panel hide">
                                    <label>已添加：</label>

                                    <div class="added-drugs">
                                    </div>
                                    <label>快速选择同类药品</label>

                                    <div class="similar-drugs">
                                        <span class="similar-drug" index="0">阿莫西林胶囊</span>
                                        <span class="similar-drug" index="1">注射用替考拉宁</span>
                                        <span class="similar-drug" index="2">头孢唑林钠</span>
                                        <span class="similar-drug" index="3">哌拉西林钠</span>
                                        <span class="similar-drug" index="4">哌拉西林钠</span>
                                        <span class="similar-drug" index="5">青霉素V钾片</span>
                                        <span class="similar-drug" index="6">青霉素钠</span>
                                        <span class="similar-drug" index="7">参芪扶正注射液</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary">添加</button>
                </div>
            </div>
        </div>
    </div>
    <div id="panel-0" class="panel">
        <table id="drugList" class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>药品代码</th>
                <th>药品名称</th>
                <th>用药途径</th>
                <th>单次用量</th>
                <th>频次</th>
                <th>规格厂家</th>
                <th>本月不合理处方数</th>
                <th>本月不合理用药人数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <%--<tr>--%>
            <%--<td>1</td>--%>
            <%--<td>0107003CI2</td>--%>
            <%--<td>复方氨基酸胶囊</td>--%>
            <%--<td>口服</td>--%>
            <%--<td><2粒</td>--%>
            <%--<td><1/日</td>--%>
            <%--<td>10wu粤天普生化</td>--%>
            <%--<td>263</td>--%>
            <%--<td>26</td>--%>
            <%--<td>--%>
            <%--<div class="btn-group">--%>
            <%--<button type="button" class="btn btn-danger btn-sm dropdown-toggle" data-toggle="dropdown"--%>
            <%--aria-haspopup="true" aria-expanded="false">--%>
            <%--操作 <span class="caret"></span>--%>
            <%--</button>--%>
            <%--<ul class="dropdown-menu">--%>
            <%--<li><a onclick="">修改</a></li>--%>
            <%--<li><a onclick="">删除</a></li>--%>
            <%--</ul>--%>
            <%--</div>--%>
            <%--</td>--%>
            <%--</tr>--%>
            </tbody>
        </table>
        <%--<ul class="pagination-sm right thandrugs-pagination"></ul>--%>
    </div>
    <div id="panel-1"></div>
</div>
<script src="<c:url value="/static/js/kdc/key_drug_control.js"/>"></script>
</body>
</html>
