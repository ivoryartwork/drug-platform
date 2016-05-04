package com.drug.platform.dal;

import com.alibaba.fastjson.JSONObject;
import com.drug.platform.model.DrugsThan;
import com.drug.platform.utils.DateFormatUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/3.
 * 从301医院统计算药费比
 */
public class CalculateDrugsThan {

    private static String dateFormatStr = "yyyy-mm-dd hh24:mi:ss";

    /**
     * 计算药费比
     */
    public static List<DrugsThan> calculate(final String time) throws Exception {
        String beginDate = time + " 00:00:00";
        String endDate = time + " 23:59:59";
        List<DrugsThan> outps = outp(beginDate, endDate);
        List<DrugsThan> inps = inp(beginDate, endDate);
        outps.addAll(inps);
        return outps;
    }

    private static List<DrugsThan> outp(final String beginDate, final String endDate) {
        JDBCCallBack<List<DrugsThan>> jdbcCallBack = new JDBCCallBack<List<DrugsThan>>() {
            @Override
            public List<DrugsThan> doInJDBCCallBack() throws SQLException, ParseException {
                String sql = "SELECT\n" +
                        "\txx.dept_code,\n" +
                        "\txx.totals xtotal,\n" +
                        "\txx.CHARGE_TYPE,\n" +
                        "\tyy.totals ytotal,\n" +
                        "\txx.totals / yy.totals * 100 fate\n" +
                        "FROM\n" +
                        "\t(\n" +
                        "\t\tSELECT\n" +
                        "\t\t\ty.dept_code,\n" +
                        "\t\t\tx.CHARGE_TYPE,\n" +
                        "\t\t\tSUM (total) totals\n" +
                        "\t\tFROM\n" +
                        "\t\t\t(\n" +
                        "\t\t\t\tSELECT\n" +
                        "\t\t\t\t\tordered_by_dept dept_code,\n" +
                        "\t\t\t\t\tF.CHARGE_TYPE,\n" +
                        "\t\t\t\t\tSUM (costs) total\n" +
                        "\t\t\t\tFROM\n" +
                        "\t\t\t\t\toutp_bill_items D,\n" +
                        "\t\t\t\t\toutp_order_desc E,\n" +
                        "\t\t\t\t\tpat_master_index F\n" +
                        "\t\t\t\tWHERE\n" +
                        "\t\t\t\t\tD .visit_date = E .visit_date\n" +
                        "\t\t\t\tAND D .visit_no = E .visit_no\n" +
                        "\t\t\t\tAND E .PATIENT_ID = F.PATIENT_ID\n" +
                        "\t\t\t\tAND D .visit_date >= TO_DATE ('" + beginDate + "', '" + dateFormatStr + "')\n" +
                        "\t\t\t\tAND D .visit_date <= TO_DATE ('" + endDate + "', '" + dateFormatStr + "')\n" +
                        "\t\t\t\tAND item_class = 'A'\n" +
                        "\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\tordered_by_dept,\n" +
                        "\t\t\t\t\tF.CHARGE_TYPE\n" +
                        "\t\t\t) x,\n" +
                        "\t\t\tdept_dict y\n" +
                        "\t\tWHERE\n" +
                        "\t\t\tx.dept_code = y.dept_code\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\ty.dept_code,\n" +
                        "\t\t\tx.CHARGE_TYPE\n" +
                        "\t) xx,\n" +
                        "\t(\n" +
                        "\t\tSELECT\n" +
                        "\t\t\ty.dept_code,\n" +
                        "\t\t\tx.CHARGE_TYPE,\n" +
                        "\t\t\tSUM (total) totals\n" +
                        "\t\tFROM\n" +
                        "\t\t\t(\n" +
                        "\t\t\t\tSELECT\n" +
                        "\t\t\t\t\tordered_by_dept dept_code,\n" +
                        "\t\t\t\t\tF.CHARGE_TYPE,\n" +
                        "\t\t\t\t\tSUM (costs) total\n" +
                        "\t\t\t\tFROM\n" +
                        "\t\t\t\t\toutp_bill_items D,\n" +
                        "\t\t\t\t\toutp_order_desc E,\n" +
                        "\t\t\t\t\tpat_master_index F\n" +
                        "\t\t\t\tWHERE\n" +
                        "\t\t\t\t\tD .visit_date = E .visit_date\n" +
                        "\t\t\t\tAND D .visit_no = E .visit_no\n" +
                        "\t\t\t\tAND E .PATIENT_ID = F.PATIENT_ID\n" +
                        "\t\t\t\tAND D .visit_date >= TO_DATE ('" + beginDate + "', '" + dateFormatStr + "')\n" +
                        "\t\t\t\tAND D .visit_date <= TO_DATE ('" + endDate + "', '" + dateFormatStr + "')\n" +
                        "\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\tordered_by_dept,\n" +
                        "\t\t\t\t\tF.CHARGE_TYPE\n" +
                        "\t\t\t) x,\n" +
                        "\t\t\tdept_dict y\n" +
                        "\t\tWHERE\n" +
                        "\t\t\tx.dept_code = y.dept_code\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\ty.dept_code,\n" +
                        "\t\t\tx.CHARGE_TYPE\n" +
                        "\t) yy\n" +
                        "WHERE\n" +
                        "\txx.dept_code = yy.dept_code\n" +
                        "AND xx.CHARGE_TYPE = yy.CHARGE_TYPE\n" +
                        "ORDER BY\n" +
                        "\tfate DESC";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<DrugsThan> drugsThans = new ArrayList<>();
                while (resultSet.next()) {
                    DrugsThan drugsThan = new DrugsThan();
                    drugsThan.setDeptCode(resultSet.getString(1));
                    drugsThan.setxTotal(resultSet.getFloat(2));
                    drugsThan.setChargeType(resultSet.getString(3));
                    drugsThan.setyTotal(resultSet.getFloat(4));
                    drugsThan.setFate(resultSet.getInt(5));
                    drugsThan.setType("outp");
                    drugsThans.add(drugsThan);
                    drugsThan.setTime(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
                }
                return drugsThans;
            }
        };
        return jdbcCallBack.execute();
    }

    private static List<DrugsThan> inp(final String beginDate, final String endDate) {
        JDBCCallBack<List<DrugsThan>> jdbcCallBack = new JDBCCallBack<List<DrugsThan>>() {
            @Override
            public List<DrugsThan> doInJDBCCallBack() throws SQLException, ParseException {
                String sql = "SELECT\n" +
                        "\txx.dept_code,\n" +
                        "\txx.totals xtotal,\n" +
                        "  XX.CHARGE_TYPE,\n" +
                        "\tyy.totals ytotal,\n" +
                        "\txx.totals / yy.totals * 100 fate\n" +
                        "FROM\n" +
                        "\t(\n" +
                        "\t\tSELECT\n" +
                        "\t\t\ty.dept_code,\n" +
                        "      x.CHARGE_TYPE,\n" +
                        "\t\t\tSUM (total) totals\n" +
                        "\t\tFROM\n" +
                        "\t\t\t(\n" +
                        "\t\t\t\tSELECT\n" +
                        "\t\t\t\t\tordered_by dept_code,\n" +
                        "\t\t\t\t\tE.CHARGE_TYPE,\n" +
                        "\t\t\t\t\tSUM (costs) total\n" +
                        "\t\t\t\tFROM\n" +
                        "\t\t\t\t\tinp_bill_detail D,\n" +
                        "\t\t\t\t\tpat_master_index E\n" +
                        "\t\t\t\tWHERE\n" +
                        "\t\t\t\t\tD .item_class = 'A'\n" +
                        "\t\t\t\tAND D .PATIENT_ID = E .PATIENT_ID\n" +
                        "\t\t\t\tAND D .billing_date_time >= TO_DATE ('" + beginDate + "', '" + dateFormatStr + "')\n" +
                        "\t\t\t\tAND D .billing_date_time <= TO_DATE ('" + endDate + "', '" + dateFormatStr + "')\n" +
                        "\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\tD .ordered_by,\n" +
                        "\t\t\t\t\tE .CHARGE_TYPE\n" +
                        "\t\t\t) x,\n" +
                        "\t\t\tdept_dict y\n" +
                        "\t\tWHERE\n" +
                        "\t\t\tx.dept_code = y.dept_code\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\ty.dept_code,x.CHARGE_TYPE\n" +
                        "\t) xx,\n" +
                        "\t(\n" +
                        "\t\tSELECT\n" +
                        "\t\t\ty.dept_code,\n" +
                        "      x.CHARGE_TYPE,\n" +
                        "\t\t\tSUM (total) totals\n" +
                        "\t\tFROM\n" +
                        "\t\t\t(\n" +
                        "\t\t\t\tSELECT\n" +
                        "\t\t\t\t\tordered_by dept_code,\n" +
                        "          E.CHARGE_TYPE,\n" +
                        "\t\t\t\t\tSUM (costs) total\n" +
                        "\t\t\t\tFROM\n" +
                        "\t\t\t\t\tinp_bill_detail D,\n" +
                        "          pat_master_index E\n" +
                        "\t\t\t\tWHERE\n" +
                        "\t\t\t\t\tD.billing_date_time >= TO_DATE ('" + beginDate + "', '" + dateFormatStr + "')\n" +
                        "\t\t\t\tAND D.billing_date_time <= TO_DATE ('" + endDate + "', '" + dateFormatStr + "')\n" +
                        "        AND D .PATIENT_ID = E .PATIENT_ID\n" +
                        "\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\tD.ordered_by,E.CHARGE_TYPE\n" +
                        "\t\t\t) x,\n" +
                        "\t\t\tdept_dict y\n" +
                        "\t\tWHERE\n" +
                        "\t\t\tx.dept_code = y.dept_code\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\ty.dept_code,x.CHARGE_TYPE\n" +
                        "\t) yy\n" +
                        "WHERE\n" +
                        "\txx.dept_code = yy.dept_code\n" +
                        "  AND XX.CHARGE_TYPE=YY.CHARGE_TYPE\n" +
                        "ORDER BY\n" +
                        "\tfate DESC";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<DrugsThan> drugsThans = new ArrayList<>();
                while (resultSet.next()) {
                    DrugsThan drugsThan = new DrugsThan();
                    drugsThan.setDeptCode(resultSet.getString(1));
                    drugsThan.setxTotal(resultSet.getFloat(2));
                    drugsThan.setChargeType(resultSet.getString(3));
                    drugsThan.setyTotal(resultSet.getFloat(4));
                    drugsThan.setFate(resultSet.getInt(5));
                    drugsThan.setType("inp");
                    drugsThans.add(drugsThan);
                    drugsThan.setTime(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
                }
                return drugsThans;
            }
        };
        return jdbcCallBack.execute();
    }

    public static void main(String[] args) {
        try {
            System.out.println(CalculateDrugsThan.calculate("2015-02-02").size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
