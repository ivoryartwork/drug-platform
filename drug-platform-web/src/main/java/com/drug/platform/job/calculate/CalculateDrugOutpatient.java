package com.drug.platform.job.calculate;

import com.drug.platform.dal.JDBCCallBack;
import com.drug.platform.model.DrugOutpatient;
import com.drug.platform.utils.DateFormatUtils;
import com.drug.platform.utils.StaUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/24.
 * 统计门诊次均药费
 */
public class CalculateDrugOutpatient {

    public static List<DrugOutpatient> calculate(String time) throws Exception {
        final String beginDate = time + " 00:00:00";
        final String endDate = time + " 23:59:59";
        JDBCCallBack<List<DrugOutpatient>> jdbcCallBack = new JDBCCallBack<List<DrugOutpatient>>() {
            @Override
            public List<DrugOutpatient> doInJDBCCallBack() throws SQLException, ParseException {
                String sql = "SELECT\n" +
                        "\tSUM (B.costs) totalCosts,\n" +
                        "  A .ordered_by_dept deptCode,\n" +
                        "  count(a.VISIT_DATE) visits,\n" +
                        "  C.CHARGE_TYPE chargeType\n" +
                        "FROM\n" +
                        "\toutp_order_desc A,\n" +
                        "\toutp_bill_items B,\n" +
                        "  pat_master_index C\n" +
                        "WHERE\n" +
                        "\tA .visit_date >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "AND A .visit_date <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "AND B.item_class = 'A'\n" +
                        "AND A .visit_date = B.visit_date\n" +
                        "AND A .visit_no = B.visit_no\n" +
                        "AND A.PATIENT_ID=C.PATIENT_ID\n" +
                        "GROUP BY\n" +
                        "\tA .ordered_by_dept,\n" +
                        "  C.CHARGE_TYPE";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<DrugOutpatient> drugsOutpatients = new ArrayList<>();
                while (resultSet.next()) {
                    DrugOutpatient drugsOutpatient = new DrugOutpatient();
                    drugsOutpatient.setTotalCosts(resultSet.getFloat(1));
                    drugsOutpatient.setDeptCode(resultSet.getString(2));
                    drugsOutpatient.setVisits(resultSet.getInt(3));
                    drugsOutpatient.setChargeType(resultSet.getString(4));
                    drugsOutpatient.setTime(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
                    drugsOutpatients.add(drugsOutpatient);
                }
                return drugsOutpatients;
            }
        };
        return jdbcCallBack.execute();
    }
}
