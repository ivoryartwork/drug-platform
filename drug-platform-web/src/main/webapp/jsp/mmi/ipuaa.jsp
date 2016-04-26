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
    <div class="panel search-panel">
        <form class="form-inline search-from">
            <div class="form-group">
                <label>开始时间：</label>
                <input type="text" name="begin" class="form-control x-datepicker-day">
            </div>
            <div class="form-group">
                <label>截止时间：</label>
                <input type="text" name="end" class="form-control x-datepicker-day">
            </div>
        </form>
        <form class="form-inline search-from">
            <div class="form-group dept">
                <label>科室名称：</label>
                <select class="form-control">
                    <option>不分科室</option>
                    <option>骨科</option>
                    <option>妇产科</option>
                    <option>外科</option>
                    <option>内科</option>
                </select>
            </div>
            <div class="form-group dept-ward hide">
                <label>病区名称：</label>
                <select class="form-control">
                    <option>不分病区</option>
                    <option>病区一</option>
                    <option>病区二</option>
                    <option>病区三</option>
                    <option>病区四</option>
                    <option>病区五</option>
                </select>
            </div>
            <div class="form-group costType">
                <label>类别：</label>
                <select class="form-control">
                    <option>不分类别</option>
                    <option>军人</option>
                    <option>地方医保</option>
                    <option>全费</option>
                </select>
            </div>
        </form>
        <button type="button" class="btn btn-primary x-btn search-btn">查询</button>
        <%--<button type="button" class="btn btn-warning x-btn">导出</button>--%>
        <button id="back" type="button" class="btn btn-info x-btn">返回</button>
    </div>
</div>
<div class="row">
    <div id="panel-0" class="panel">
        <div class="row">
            <div class="col-md-6">
                <p class="explanation"><span class="des"></span></p>
                <table class="table table-bordered">
                    <tr>
                        <td>I类切口手术总患者数</td>
                        <td id="inp-0"></td>
                    </tr>
                    <tr>
                        <td>I类切口预防使用抗菌药物患者数</td>
                        <td id="ianp-0"></td>
                    </tr>
                    <tr>
                        <td>I类切口预防使用抗菌药物比例</td>
                        <td><span id="ipuaa-0"></span>%</td>
                    </tr>
                    <tr>
                        <td>目标值</td>
                        <td><span id="targetIpuaa-0"></span>%</td>
                    </tr>
                </table>
            </div>
            <div class="col-md-6">
                <p class="explanation"><span class="des"></span></p>

                <div id="ipuaaColumn" class="x-column"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <p class="explanation"><span class="des"></span></p>
                <table id="deptIpuaaList" class="table table-bordered">
                    <thead>
                    <tr>
                        <th>科室名称</th>
                        <th>I类切口总患者数</th>
                        <th>I类切口使用抗菌药物患者</th>
                        <th>I类切口使用抗菌药物比例（%）</th>
                        <th>目标值（%）</th>
                        <th>目标值（%）</th>
                        <th>排名</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <ul class="pagination-sm right thandrugs-pagination"></ul>
            </div>
        </div>
    </div>
    <div id="panel-1" class="panel hide">
        <div class="row">
            <div class="col-md-6">
                <p class="explanation"><span class="des"></span></p>
                <table class="table table-bordered">
                    <tr>
                        <td>I类切口手术总患者数</td>
                        <td id="inp-1"></td>
                    </tr>
                    <tr>
                        <td>I类切口预防使用抗菌药物患者数</td>
                        <td id="ianp-1"></td>
                    </tr>
                    <tr>
                        <td>I类切口预防使用抗菌药物比例</td>
                        <td><span id="ipuaa-1"></span>%</td>
                    </tr>
                    <tr>
                        <td>目标值</td>
                        <td><span id="targetIpuaa-1"></span>%</td>
                    </tr>
                </table>
            </div>
            <div class="col-md-6">
                <p class="explanation"><span class="des"></span>肾病科I类切口预防使用抗菌药物比例趋势图</p>

                <div id="departmentIpuaaColumn" class="x-column"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <p class="explanation"><span class="des"></span>肾病科2015年9月各病区I类切口预防使用抗菌药物比例</p>
                <table id="wardIpuaaList" class="table table-bordered">
                    <thead>
                    <tr>
                        <th>病区名称</th>
                        <th>急诊处方总患者数</th>
                        <th>急诊抗菌药物处方患者数</th>
                        <th>急诊抗菌药物处方比例</th>
                        <th>目标值（%）</th>
                        <th>目标值（%）</th>
                        <th>排名</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <ul class="pagination-sm right thandrugs-pagination"></ul>
            </div>
        </div>
    </div>
    <div id="panel-2" class="panel hide">
        <div class="row">
            <div class="col-md-6">
                <p class="explanation"><span class="des"></span></p>
                <table class="table table-bordered">
                    <tr>
                        <td>I类切口手术总患者数</td>
                        <td id="inp-2"></td>
                    </tr>
                    <tr>
                        <td>I类切口预防使用抗菌药物患者数</td>
                        <td id="ianp-2"></td>
                    </tr>
                    <tr>
                        <td>I类切口预防使用抗菌药物比例</td>
                        <td><span id="ipuaa-2"></span>%</td>
                    </tr>
                    <tr>
                        <td>目标值</td>
                        <td><span id="targetIpuaa-2"></span>%</td>
                    </tr>
                </table>
            </div>
            <div class="col-md-6">
                <p class="explanation"><span class="des"></span></p>

                <div id="wardIpuaaColumn" class="x-column"></div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/static/js/mmi/ipuaa.js"/>"></script>
<script src="<c:url value="/static/js/chart_helper.js"/> "></script>
</body>
</html>
