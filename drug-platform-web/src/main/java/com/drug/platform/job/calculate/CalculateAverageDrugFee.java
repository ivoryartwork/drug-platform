package com.drug.platform.job.calculate;

import com.drug.platform.dal.JDBCCallBack;
import com.drug.platform.model.AverageDrugFee;
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
public class CalculateAverageDrugFee {

    public static List<AverageDrugFee> calculate(String time) throws Exception {
        final String beginDate = time + " 00:00:00";
        final String endDate = time + " 23:59:59";
        List<AverageDrugFee> outp = calculateOutp(beginDate, endDate);
        List<AverageDrugFee> inp = calculateInp(beginDate, endDate);
        outp.addAll(inp);
        return outp;
    }

    private static List<AverageDrugFee> calculateOutp(final String beginDate, final String endDate) throws Exception {
        JDBCCallBack<List<AverageDrugFee>> jdbcCallBack = new JDBCCallBack<List<AverageDrugFee>>() {
            @Override
            public List<AverageDrugFee> doInJDBCCallBack() throws SQLException, ParseException {
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
                List<AverageDrugFee> averageDrugFeeList = new ArrayList<>();
                while (resultSet.next()) {
                    AverageDrugFee averageDrugFee = new AverageDrugFee();
                    averageDrugFee.setTotalCosts(resultSet.getFloat(1));
                    averageDrugFee.setDeptCode(resultSet.getString(2));
                    averageDrugFee.setVisits(resultSet.getInt(3));
                    averageDrugFee.setChargeType(resultSet.getString(4));
                    averageDrugFee.setTime(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
                    averageDrugFee.setType("outp");
                    averageDrugFeeList.add(averageDrugFee);
                }
                return averageDrugFeeList;
            }
        };
        return jdbcCallBack.execute();
    }

    private static List<AverageDrugFee> calculateInp(final String beginDate, final String endDate) throws Exception {
        JDBCCallBack<List<AverageDrugFee>> jdbcCallBack = new JDBCCallBack<List<AverageDrugFee>>() {
            @Override
            public List<AverageDrugFee> doInJDBCCallBack() throws SQLException, ParseException {
                String sql = "SELECT\n" +
                        "\tA .ordered_by deptCode,\n" +
                        "\tSUM (A .costs) totalCosts,\n" +
                        "\tCOUNT (DISTINCT A .patient_id) visits,\n" +
                        "\tB.CHARGE_TYPE chargeType\n" +
                        "FROM\n" +
                        "\tINP_BILL_DETAIL A,\n" +
                        "\tpat_master_index B\n" +
                        "WHERE\n" +
                        "\tA .BILLING_DATE_TIME >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "AND A .BILLING_DATE_TIME <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "AND A .ITEM_CLASS = 'A'\n" +
                        "AND A .PATIENT_ID = B.PATIENT_ID\n" +
                        "GROUP BY\n" +
                        "\tA .ordered_by,\n" +
                        "\tB.CHARGE_TYPE";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<AverageDrugFee> averageDrugFeeList = new ArrayList<>();
                while (resultSet.next()) {
                    AverageDrugFee averageDrugFee = new AverageDrugFee();
                    averageDrugFee.setTotalCosts(resultSet.getFloat(2));
                    averageDrugFee.setDeptCode(resultSet.getString(1));
                    averageDrugFee.setVisits(resultSet.getInt(3));
                    averageDrugFee.setChargeType(resultSet.getString(4));
                    averageDrugFee.setTime(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
                    averageDrugFee.setType("inp");
                    averageDrugFeeList.add(averageDrugFee);
                }
                return averageDrugFeeList;
            }
        };
        return jdbcCallBack.execute();
    }
}
