<%--
  药费比
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/18
  Time: 13:24
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
            <div class="form-group type">
                <label>用药区域：</label>
                <select class="form-control">
                    <option value="1">全院</option>
                    <option value="2">门诊</option>
                    <option value="3">住院</option>
                </select>
            </div>
            <div class="form-group dept">
                <label>科室名称：</label>
                <select class="form-control">
                    <option value="none">不分科室</option>
                    <option value="112">骨科</option>
                    <option value="12">妇产科</option>
                    <option value="23213">外科</option>
                    <option value="23432">内科</option>
                </select>
            </div>
            <div class="form-group dept-ward hide">
                <label>病区名称：</label>
                <select class="form-control">
                    <option value="none">不分病区</option>
                    <option value="1">病区一</option>
                    <option value="1">病区二</option>
                    <option value="1">病区三</option>
                    <option value="1">病区四</option>
                    <option value="1">病区五</option>
                </select>
            </div>
            <div class="form-group costType">
                <label>类别：</label>
                <select class="form-control">
                    <option value="none">不分类别</option>
                    <option value="sdf">军人</option>
                    <option value="sdfsd">地方医保</option>
                    <option value="sdfsdf">全费</option>
                </select>
            </div>
        </form>
        <button type="button" class="btn btn-primary x-btn search-btn">查询</button>
        <button id="back" type="button" class="btn btn-info x-btn">返回</button>
    </div>
</div>
<div class="row">
    <div id="panel-0" class="panel">
        <div class="row">
            <div class="col-md-6">
                <p class="explanation"><span class="des"></span></p>

                <div class="col-md-4">
                    <h3 class="text-center">药费比</h3>

                    <h3 class="text-center"><span id="rate-0"></span>%</h3><br>

                    <p class="text-center">总药费：<span id="totalDrugCost-0"></span>元</p>

                    <p class="text-center">治疗费用：<span id="totalTreatCost-0"></span>元</p>

                    <p class="text-center">费用合计：<span id="totalCost-0"></span>元</p>
                </div>
                <div class="col-md-8">
                    <div id="thanDrugsPie" class="x-pie"></div>
                </div>
            </div>
            <div class="col-md-6">
                <p class="explanation"><span class="des"></span></p>

                <div id="thanDrugsColumn" class="x-column"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <p class="explanation"><span class="des">全院2015年9月各科室药费比</span><a class="right x-export-btn"
                                                                                  href="export/thanDrugsData">导出</a>
                </p>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>科室名称</th>
                        <th>药费（元）</th>
                        <th>治疗费（元）</th>
                        <th>总数（元）</th>
                        <th>药占比（%）</th>
                        <th>目标值（%）</th>
                        <th>排名</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div id="panel-1" class="panel hide">
        <div class="row">
            <div class="col-md-6">
                <p class="explanation"><span class="des"></span></p>

                <div class="col-md-4">
                    <h3 class="text-center">药费比</h3>

                    <h3 class="text-center"><span id="rate-1"></span>%</h3><br>

                    <p class="text-center">总药费：<span id="totalDrugCost-1"></span>元</p>

                    <p class="text-center">治疗费用：<span id="totalTreatCost-1"></span>元</p>

                    <p class="text-center">费用合计：<span id="totalCost-1"></span>元</p>
                </div>
                <div class="col-md-8">
                    <div id="departmentThanDrugsPie" class="x-pie"></div>
                </div>
            </div>
            <div class="col-md-6" style="position: relative">
                <a class="switch-arrow-left switch-arrow"><img src="static/images/right.png" width="25px"></a>

                <p class="explanation"><span class="des"></span></p>

                <div id="departmentAllThanDrugsColumn" class="x-column"></div>
                <div id="departmentSubThanDrugsColumn" class="x-column hide"></div>
                <a class="switch-arrow-right switch-arrow"><img src="static/images/left.png" width="25px"></a>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <p class="explanation"><span class="des"></span><a class="right x-export-btn"
                                                                   href="export/thanDrugsData">导出</a>
                </p>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>科室名称</th>
                        <th>药费（元）</th>
                        <th>治疗费（元）</th>
                        <th>总数（元）</th>
                        <th>药占比（%）</th>
                        <th>目标值（%）</th>
                        <th>排名</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div id="panel-2" class="panel hide">
        <div class="row">
            <div class="col-md-6">
                <p class="explanation"><span class="des"></span></p>

                <div class="col-md-4">
                    <h3 class="text-center">药费比</h3>

                    <h3 class="text-center"><span id="rate-2"></span>%</h3><br>

                    <p class="text-center">总药费：<span id="totalDrugCost-2"></span>元</p>

                    <p class="text-center">治疗费用：<span id="totalTreatCost-2"></span>元</p>

                    <p class="text-center">费用合计：<span id="totalCost-2"></span>元</p>
                </div>
                <div class="col-md-8">
                    <div id="wardThanDrugsPie" class="x-pie"></div>
                </div>
            </div>
            <div class="col-md-6">
                <p class="explanation"><span class="des"></span></p>

                <div id="wardThanDrugsColumn" class="x-column"></div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/static/js/mmi/than_drugs.js"/> "></script>
<script src="<c:url value="/static/js/chart_helper.js"/> "></script>
</body>
</html>
