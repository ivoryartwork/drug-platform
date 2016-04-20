/**
 * Created by Yaochao on 2016/4/18.
 */
$(function (ch) {
    ch.pie = function (containerId, data) {
        $('#' + containerId).highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: ''
            },
            tooltip: {
                pointFormat: '{point.percentage:.1f}%'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                    }
                }
            },
            exporting: {
                buttons: {
                    contextButton: {
                        menuItems: [{
                            text: '导出png图片',
                            onclick: function () {
                                this.exportChart();
                            }
                        }]
                    }
                }
            },
            series: [{
                type: 'pie',
                data: data
            }]
        });
    }

    ch.column = function (containerId, categories, series, unit) {
        $('#' + containerId).highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                min: 0,
                title: {
                    text: unit
                }
            },
            exporting: {
                buttons: {
                    contextButton: {
                        menuItems: [{
                            text: '导出png图片',
                            onclick: function () {
                                this.exportChart();
                            }
                        }]
                    }
                },
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: series
        });
    }
}(window.chartHelper = {}))