package com.drug.platform.dal;

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
     * 计算各科室药费比
     */
    public static List<DrugsThan> dept(final String time) throws Exception {
        JDBCCallBack<List<DrugsThan>> jdbcCallBack = new JDBCCallBack<List<DrugsThan>>() {
            @Override
            public List<DrugsThan> doInJDBCCallBack() throws SQLException, ParseException {
                String beginDate = time + " 00:00:00";
                String endDate = time + " 23:59:59";
                String sql = "SELECT\n" +
                        "\txx.dept_code,\n" +
                        "\txx.totals xtotal,\n" +
                        "\tyy.totals ytotal,\n" +
                        "\txx.totals / yy.totals * 100 fate\n" +
                        "FROM\n" +
                        "\t(\n" +
                        "\t\tSELECT\n" +
                        "\t\t\ty.dept_code,\n" +
                        "\t\t\tSUM (total) totals\n" +
                        "\t\tFROM\n" +
                        "\t\t\t(\n" +
                        "\t\t\t\t(\n" +
                        "\t\t\t\t\tSELECT\n" +
                        "\t\t\t\t\t\tordered_by_dept dept_code,\n" +
                        "\t\t\t\t\t\tSUM (costs) total\n" +
                        "\t\t\t\t\tFROM\n" +
                        "\t\t\t\t\t\toutp_bill_items D,\n" +
                        "\t\t\t\t\t\toutp_order_desc E\n" +
                        "\t\t\t\t\tWHERE\n" +
                        "\t\t\t\t\t\tD .visit_date = E .visit_date\n" +
                        "\t\t\t\t\tAND D .visit_no = E .visit_no\n" +
                        "\t\t\t\t\tAND D .visit_date >= TO_DATE (\n" +
                        "\t\t\t\t\t\t'" + beginDate + "',\n" +
                        "\t\t\t\t\t\t'" + dateFormatStr + "'\n" +
                        "\t\t\t\t\t)\n" +
                        "\t\t\t\t\tAND D .visit_date <= TO_DATE (\n" +
                        "\t\t\t\t\t\t'" + endDate + "',\n" +
                        "\t\t\t\t\t\t'" + dateFormatStr + "'\n" +
                        "\t\t\t\t\t)\n" +
                        "\t\t\t\t\tAND item_class = 'A'\n" +
                        "\t\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\t\tordered_by_dept\n" +
                        "\t\t\t\t)\n" +
                        "\t\t\t\tUNION\n" +
                        "\t\t\t\t\t(\n" +
                        "\t\t\t\t\t\tSELECT\n" +
                        "\t\t\t\t\t\t\tordered_by dept_code,\n" +
                        "\t\t\t\t\t\t\tSUM (costs) total\n" +
                        "\t\t\t\t\t\tFROM\n" +
                        "\t\t\t\t\t\t\tinp_bill_detail\n" +
                        "\t\t\t\t\t\tWHERE\n" +
                        "\t\t\t\t\t\t\titem_class = 'A'\n" +
                        "\t\t\t\t\t\tAND billing_date_time >= TO_DATE (\n" +
                        "\t\t\t\t\t\t\t'" + beginDate + "',\n" +
                        "\t\t\t\t\t\t\t'" + dateFormatStr + "'\n" +
                        "\t\t\t\t\t\t)\n" +
                        "\t\t\t\t\t\tAND billing_date_time <= TO_DATE (\n" +
                        "\t\t\t\t\t\t\t'" + endDate + "',\n" +
                        "\t\t\t\t\t\t\t'" + dateFormatStr + "'\n" +
                        "\t\t\t\t\t\t)\n" +
                        "\t\t\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\t\t\tordered_by\n" +
                        "\t\t\t\t\t)\n" +
                        "\t\t\t) x,\n" +
                        "\t\t\tdept_dict y\n" +
                        "\t\tWHERE\n" +
                        "\t\t\tx.dept_code = y.dept_code\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\ty.dept_code\n" +
                        "\t) xx,\n" +
                        "\t(\n" +
                        "\t\tSELECT\n" +
                        "\t\t\ty.dept_code,\n" +
                        "\t\t\tSUM (total) totals\n" +
                        "\t\tFROM\n" +
                        "\t\t\t(\n" +
                        "\t\t\t\t(\n" +
                        "\t\t\t\t\tSELECT\n" +
                        "\t\t\t\t\t\tordered_by_dept dept_code,\n" +
                        "\t\t\t\t\t\tSUM (costs) total\n" +
                        "\t\t\t\t\tFROM\n" +
                        "\t\t\t\t\t\toutp_bill_items D,\n" +
                        "\t\t\t\t\t\toutp_order_desc E\n" +
                        "\t\t\t\t\tWHERE\n" +
                        "\t\t\t\t\t\tD .visit_date = E .visit_date\n" +
                        "\t\t\t\t\tAND D .visit_no = E .visit_no\n" +
                        "\t\t\t\t\tAND D .visit_date >= TO_DATE (\n" +
                        "\t\t\t\t\t\t'" + beginDate + "',\n" +
                        "\t\t\t\t\t\t'" + dateFormatStr + "'\n" +
                        "\t\t\t\t\t)\n" +
                        "\t\t\t\t\tAND D .visit_date <= TO_DATE (\n" +
                        "\t\t\t\t\t\t'" + endDate + "',\n" +
                        "\t\t\t\t\t\t'" + dateFormatStr + "'\n" +
                        "\t\t\t\t\t)\n" +
                        "\t\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\t\tordered_by_dept\n" +
                        "\t\t\t\t)\n" +
                        "\t\t\t\tUNION\n" +
                        "\t\t\t\t\t(\n" +
                        "\t\t\t\t\t\tSELECT\n" +
                        "\t\t\t\t\t\t\tordered_by dept_code,\n" +
                        "\t\t\t\t\t\t\tSUM (costs) total\n" +
                        "\t\t\t\t\t\tFROM\n" +
                        "\t\t\t\t\t\t\tinp_bill_detail\n" +
                        "\t\t\t\t\t\tWHERE\n" +
                        "\t\t\t\t\t\t\tbilling_date_time >= TO_DATE (\n" +
                        "\t\t\t\t\t\t\t\t'" + beginDate + "',\n" +
                        "\t\t\t\t\t\t\t\t'" + dateFormatStr + "'\n" +
                        "\t\t\t\t\t\t\t)\n" +
                        "\t\t\t\t\t\tAND billing_date_time <= TO_DATE (\n" +
                        "\t\t\t\t\t\t\t'" + endDate + "',\n" +
                        "\t\t\t\t\t\t\t'" + dateFormatStr + "'\n" +
                        "\t\t\t\t\t\t)\n" +
                        "\t\t\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\t\t\tordered_by\n" +
                        "\t\t\t\t\t)\n" +
                        "\t\t\t) x,\n" +
                        "\t\t\tdept_dict y\n" +
                        "\t\tWHERE\n" +
                        "\t\t\tx.dept_code = y.dept_code\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\ty.dept_code\n" +
                        "\t) yy\n" +
                        "WHERE\n" +
                        "\txx.dept_code = yy.dept_code\n" +
                        "ORDER BY\n" +
                        "\tfate DESC";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<DrugsThan> drugsThans = new ArrayList<>();
                while (resultSet.next()) {
                    DrugsThan drugsThan = new DrugsThan();
                    drugsThan.setDeptCode(resultSet.getString(1));
                    drugsThan.setxTotal(resultSet.getFloat(2));
                    drugsThan.setyTotal(resultSet.getFloat(3));
                    drugsThan.setFate(resultSet.getInt(4));
                    drugsThans.add(drugsThan);
                    drugsThan.setTime(DateFormatUtils.parse(time, DateFormatUtils.FORMAT_DATE));
                }
                return drugsThans;
            }
        };
        return jdbcCallBack.execute();
    }

//    public static void main(String[] args) {
//        try {
//            System.out.println(JSONObject.toJSONString(CalculateDrugsThan.dept("2015-02-02")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
