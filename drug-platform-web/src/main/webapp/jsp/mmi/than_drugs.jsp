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
    <div class="panel">
        <form class="form-inline search-from">
            <div class="form-group">
                <label>选择时间：</label>
                <input type="text" class="form-control x-datepicker-month">
            </div>
            <div class="form-group">
                <label>类别：</label>
                <select class="form-control">
                    <option>不分类别</option>
                    <option>军人</option>
                    <option>地方医保</option>
                    <option>全费</option>
                </select>
            </div>
            <div class="form-group">
                <label>用药区域：</label>
                <select class="form-control">
                    <option>全院</option>
                    <option>门诊</option>
                    <option>住院</option>
                </select>
            </div>
            <div class="form-group">
                <label>科室名称：</label>
                <select class="form-control">
                    <option>不分科室</option>
                    <option>骨科</option>
                    <option>妇产科</option>
                    <option>外科</option>
                    <option>内科</option>
                </select>
            </div>
            <div class="form-group">
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
        </form>
        <button type="button" class="btn btn-primary x-btn">查询</button>
        <button type="button" class="btn btn-warning x-btn">导出</button>
        <button id="back" type="button" class="btn btn-info x-btn">返回</button>
    </div>
</div>
<div class="row">
    <div id="panel-0" class="panel hide">
        <div class="row">
            <div class="col-md-5">
                <div class="row container-fluid">
                    <p class="explanation">全院2015年9月药费比</p>

                    <div class="col-md-4">
                        <h3 class="text-center"> 药费比</h3>

                        <h3 class="text-center">60%</h3><br>

                        <p class="text-center">总药费：2829567元</p>

                        <p class="text-center">治疗费用：1100536元</p>

                        <p class="text-center">费用合计：4006895元</p>
                    </div>
                    <div class="col-md-8">
                        <div id="thanDrugsPie" class="x-pie"></div>
                    </div>
                </div>
                <div>
                    <p class="explanation">全院药费比趋势图</p>

                    <div id="thanDrugsColumn" class="x-column"></div>
                </div>
            </div>
            <div class="col-md-7">
                <p class="explanation">全院2015年9月各科室药费比</p>
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
                    <tr>
                        <td><a onclick="switchToDepartment('肾病科')">肾病科</a></td>
                        <td>282956</td>
                        <td>363535</td>
                        <td>4455411</td>
                        <td>76.3</td>
                        <td>53</td>
                        <td>1</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,003</td>
                        <td>Integer</td>
                        <td>nec</td>
                        <td>odio</td>
                        <td>Praesent</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    </tbody>
                </table>
                <ul class="pagination-sm right thandrugs-pagination"></ul>
            </div>
        </div>
    </div>
    <div id="panel-1" class="panel">
        <div class="row">
            <div class="col-md-5">
                <div class="row container-fluid">
                    <p class="explanation"><span class="title-name"></span>2015年9月药费比</p>

                    <div class="col-md-4">
                        <h3 class="text-center"> 药费比</h3>

                        <h3 class="text-center">60%</h3><br>

                        <p class="text-center">总药费：2829567元</p>

                        <p class="text-center">治疗费用：1100536元</p>

                        <p class="text-center">费用合计：4006895元</p>
                    </div>
                    <div class="col-md-8">
                        <div id="departmentThanDrugsPie" class="x-pie"></div>
                    </div>
                </div>
                <div style="position: relative">
                    <a class="switch-arrow-left switch-arrow"><img src="static/images/right.png" width="25px"></a>

                    <p class="explanation"><span class="title-name"></span><span id="departSubTitle"></span></p>

                    <div id="departmentAllThanDrugsColumn" class="x-column"></div>
                    <div id="departmentSubThanDrugsColumn" class="x-column hide"></div>
                    <a class="switch-arrow-right switch-arrow"><img src="static/images/left.png" width="25px"></a>
                </div>
            </div>
            <div class="col-md-7">
                <p class="explanation"><span class="title-name"></span>2015年9月各科室药费比</p>
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
                    <tr>
                        <td><a onclick="switchToWard('肾病一区')">肾病科一区</a></td>
                        <td>282956</td>
                        <td>363535</td>
                        <td>4455411</td>
                        <td>76.3</td>
                        <td>53</td>
                        <td>1</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,003</td>
                        <td>Integer</td>
                        <td>nec</td>
                        <td>odio</td>
                        <td>Praesent</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>sit</td>
                        <td>sit</td>
                    </tr>
                    </tbody>
                </table>
                <ul class="pagination-sm right thandrugs-pagination"></ul>
            </div>
        </div>
    </div>
    <div id="panel-2" class="panel hide">
        <div class="row">
            <div class="col-md-6">
                <p class="explanation"><span class="title-name"></span>2015年9月药费比</p>

                <div class="col-md-4">
                    <h3 class="text-center"> 药费比</h3>

                    <h3 class="text-center">60%</h3><br>

                    <p class="text-center">总药费：2829567元</p>

                    <p class="text-center">治疗费用：1100536元</p>

                    <p class="text-center">费用合计：4006895元</p>
                </div>
                <div class="col-md-8">
                    <div id="wardThanDrugsPie" class="x-pie"></div>
                </div>
            </div>
            <div class="col-md-6">
                <p class="explanation"><span class="title-name"></span>药费比趋势图</p>

                <div id="wardThanDrugsColumn" class="x-column"></div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/static/js/mmi/than_drugs.js"/> "></script>
<script src="<c:url value="/static/js/chart_helper.js"/> "></script>
</body>
</html>
