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
            <button id="addDrug" type="button" class="btn btn-warning x-btn">
                添加药品
            </button>

            <button class="btn btn-warning x-btn">导出</button>
            <button id="back" type="button" class="btn btn-info x-btn">返回</button>
            <div class="form-group right" id="searchKeyDrug">
                <%--<input type="text" class="form-control" placeholder="药品名称">--%>
                <div id="drugName" style="display: inline-block">
                    <input type="text" class="form-control">
                    <ul>
                    </ul>
                </div>
                <button type="button" class="btn btn-primary">搜索</button>
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

                            <div class="col-sm-10" id="drugName">
                                <input type="text" class="form-control keyDrugName">
                                <ul>
                                </ul>
                            </div>
                        </div>
                        <div class="form-group drugSpec">
                            <label class="col-sm-2 control-label">规格厂家：</label>

                            <div class="col-sm-10">
                                <select class="form-control keyDrugSpec">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">指定目录：</label>

                            <div class="col-sm-10">
                                <select class="form-control type">
                                    <option value="inp">住院</option>
                                    <option value="outp">门诊</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">频次：</label>

                            <div class="col-sm-10 frequency">
                                <select class="form-control input-inline">
                                    <option value=">">&gt;</option>
                                    <option value="<">&lt;</option>
                                    <option value="≥">&gt;=</option>
                                    <option value="≤">&lt;=</option>
                                    <option value="<>"><></option>
                                    <option value="=">=</option>
                                </select>
                                <select class="form-control input-inline">
                                    <option value="1/日">1/日</option>
                                    <option value="2/日">2/日</option>
                                    <option value="3/日">3/日</option>
                                    <option value="4/日">4/日</option>
                                    <option value="6/日">6/日</option>
                                    <option value="1/晚">1/晚</option>
                                    <option value="1/隔日">1/隔日</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用量：</label>

                            <div class="col-sm-10 dose">
                                <select class="form-control input-inline">
                                    <option value=">">&gt;</option>
                                    <option value="<">&lt;</option>
                                    <option value="≥">&gt;=</option>
                                    <option value="≤">&lt;=</option>
                                    <option value="<>"><></option>
                                    <option value="=">=</option>
                                </select>
                                <input type="text" class="form-control input-inline">
                                <select class="form-control input-inline">
                                    <option value="ml">ml</option>
                                    <option value="mg">mg</option>
                                    <option value="g">g</option>
                                    <option value="支">支</option>
                                    <option value="枚">枚</option>
                                    <option value="片">片</option>
                                    <option value="粒">粒</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">单次用量：</label>

                            <div class="col-sm-10 singleDose">
                                <select class="form-control input-inline">
                                    <option value=">">&gt;</option>
                                    <option value="<">&lt;</option>
                                    <option value="≥">&gt;=</option>
                                    <option value="≤">&lt;=</option>
                                    <option value="<>"><></option>
                                    <option value="=">=</option>
                                </select>
                                <input type="text" class="form-control input-inline">
                                <select class="form-control input-inline">
                                    <option value="ml">ml</option>
                                    <option value="mg">mg</option>
                                    <option value="g">g</option>
                                    <option value="支">支</option>
                                    <option value="枚">枚</option>
                                    <option value="片">片</option>
                                    <option value="粒">粒</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用药途径：</label>

                            <div class="col-sm-10">
                                <select class="form-control routeOfAdmin">
                                    <option value="口服">口服</option>
                                    <option value="静滴">静滴</option>
                                    <option value="静推">静推</option>
                                    <option value="肌肉注射">肌肉注射</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">同类药品：</label>

                            <div class="col-sm-10">
                                <div id="addSimilarDrugs" class="similar-drugs-panel">
                                    <div class="row">
                                        <div class="form-group col-lg-5">
                                            <label class="col-sm-5 control-label">药品名称：</label>

                                            <div class="col-sm-7" id="drugName">
                                                <input type="text" class="form-control">
                                                <ul>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-5 drugSpec">
                                            <label class="col-sm-5 control-label">规格厂家：</label>

                                            <div class="col-sm-7">
                                                <select class="form-control">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <a class="btn btn-primary" id="addSimilarKeyDrug">添加</a>
                                        </div>
                                    </div>
                                    <br>
                                    <label>已添加：</label>

                                    <div class="added-drugs">
                                    </div>
                                    <label>快速选择同类药品</label>

                                    <div class="similar-drugs">
                                        <span class="similar-drug" index="0">阿莫西林胶囊(哈尔滨1g)</span>
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
                    <button type="button" class="btn btn-primary" id="addKeyDrug">添加</button>
                </div>
            </div>
        </div>
    </div>
    <div id="panel-0" class="panel">
        <table class="table table-bordered">
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
        <ul id="keyDrugPagination" class="pagination-sm right"></ul>
    </div>
    <div id="panel-1" class="panel hide">
        <div class="row" style="height: 800px">
            <div class="col-md-4">
                <div>
                    <p class="explanation">基本信息</p>
                    <table class="table table-bordered">
                        <tbody>
                        <tr>
                            <td>药品代码</td>
                            <td>0107003CI2</td>
                        </tr>
                        <tr>
                            <td>药品名称</td>
                            <td>注射用替考拉宁</td>
                        </tr>
                        <tr>
                            <td>规格厂家</td>
                            <td>0.2g浙新昌</td>
                        </tr>
                        <tr>
                            <td>单次用量</td>
                            <td><20ml</td>
                        </tr>
                        <tr>
                            <td>频次</td>
                            <td><3/日</td>
                        </tr>
                        <tr>
                            <td>用量</td>
                            <td><50ml</td>
                        </tr>
                        <tr>
                            <td>用药途径</td>
                            <td>注射</td>
                        </tr>
                        <tr>
                            <td>同类药品</td>
                            <td>头孢氨苄胶囊 哌拉西林钠 注射用胸腺五肽</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div>
                    <p class="explanation">本月药品用量情况</p>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>用药区域</th>
                            <th>数量（盒）</th>
                            <th>金额（元）</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>全院</td>
                            <td>21654</td>
                            <td>5895352</td>
                        </tr>
                        <tr>
                            <td>住院</td>
                            <td>17546</td>
                            <td>569230</td>
                        </tr>
                        <tr>
                            <td>门诊</td>
                            <td>2356</td>
                            <td>26531</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div>
                    <p class="explanation">本月不合理用药统计</p>
                    <table class="table table-bordered">
                        <tbody>
                        <tr>
                            <td>关于此药不合理医嘱数</td>
                            <td>5895</td>
                        </tr>
                        <tr>
                            <td>关于此药医嘱总数</td>
                            <td>365215</td>
                        </tr>
                        <tr>
                            <td>关于此药不合理用药患者数</td>
                            <td>1546</td>
                        </tr>
                        <tr>
                            <td>关于此药用药患者数</td>
                            <td>152656</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-8">
                <div style="height: 50%">
                    <div class="container-fluid explanation">
                        <div class="row col-md-6">
                            <span id="des">阿莫西林胶囊2015年9月各科室用量排名</span>
                        </div>
                        <div class="col-md-6">
                            <a class="x-export-btn right"
                               style="display: inline-block;margin-left: 10px">导出</a>
                            <select id="selectType" class="right" style="width: auto;color: black">
                                <option value="0">科室排名</option>
                                <option value="1">病区排名</option>
                                <option value="1">医生排名</option>
                                <option value="1">门诊科室排名</option>
                                <option value="1">门诊医生排名</option>
                            </select>
                        </div>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>病人编号</th>
                            <th>病人姓名</th>
                            <th>医嘱名称</th>
                            <th>科室</th>
                            <th>医生</th>
                            <th>联合用药情况</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>1000812</td>
                            <td>刘武</td>
                            <td>狄奥宁（13902）</td>
                            <td>神经内科</td>
                            <td>于生</td>
                            <td>注射用胸腺五肽 、头孢唑林钠</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div style="height: 50%;">
                    <p class="explanation">本月不合理用药医嘱<a class="x-export-btn right">导出</a></p>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>医嘱名称</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>病人编号</th>
                            <th>病人姓名</th>
                            <th>科室</th>
                            <th>医生</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>狄奥宁（13902）</td>
                            <td>2015-09-03 12:12:12</td>
                            <td>2015-09-03 12:12:12</td>
                            <td>1000812</td>
                            <td>刘武</td>
                            <td>神经内科</td>
                            <td>于生</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/static/js/kdc/key_drug_control.js"/>"></script>
</body>
</html>
