package com.drug.platform.job.calculate;

import com.drug.platform.dal.JDBCCallBack;
import com.drug.platform.model.DrugAmountDept;
import com.drug.platform.model.DrugAmountDoctor;
import com.drug.platform.model.DrugAmountGlobal;
import com.drug.platform.utils.DateFormatUtils;
import com.drug.platform.utils.StaUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/5.
 */
public class CalculateDrugAmount {

    public static List<DrugAmountGlobal> calculateGlobal(String time) throws Exception {
        String beginDate = time + " 00:00:00";
        String endDate = time + " 23:59:59";
        List<DrugAmountGlobal> drugAmountOutp = calculateOutp(beginDate, endDate);
        List<DrugAmountGlobal> drugAmountInp = calculateInp(beginDate, endDate);
        drugAmountOutp.addAll(drugAmountInp);
        return drugAmountOutp;
    }

    public static List<DrugAmountDept> calculateDept(String time) throws Exception {
        final String beginDate = time + " 00:00:00";
        final String endDate = time + " 23:59:59";
        JDBCCallBack<List<DrugAmountDept>> jdbcCallBack = new JDBCCallBack<List<DrugAmountDept>>() {
            @Override
            public List<DrugAmountDept> doInJDBCCallBack() throws SQLException, ParseException {
                String sql = "SELECT\n" +
                        "\t*\n" +
                        "FROM\n" +
                        "\t(\n" +
                        "\t\tSELECT\n" +
                        "\t\t\tdept_code,\n" +
                        "\t\t\tdrug_name,\n" +
                        "\t\t\tdrug_code,\n" +
                        "\t\t\tdrug_spec,\n" +
                        "\t\t\tunits,\n" +
                        "\t\t\tTYPE,\n" +
                        "\t\t\tSUM (total) total,\n" +
                        "\t\t\tSUM (quantity) amount\n" +
                        "\t\tFROM\n" +
                        "\t\t\t(\n" +
                        "\t\t\t\tSELECT\n" +
                        "\t\t\t\t\ts.drug_name,\n" +
                        "\t\t\t\t\tT .drug_code,\n" +
                        "\t\t\t\t\tT .drug_spec,\n" +
                        "\t\t\t\t\tT .drug_units units,\n" +
                        "\t\t\t\t\tT .ordered_by dept_code,\n" +
                        "\t\t\t\t\t'inp' AS TYPE,\n" +
                        "\t\t\t\t\tSUM (T .costs) total,\n" +
                        "\t\t\t\t\tSUM (T .dispense_amount) quantity\n" +
                        "\t\t\t\tFROM\n" +
                        "\t\t\t\t\tdrug_dispense_rec T,\n" +
                        "\t\t\t\t\tdrug_name_dict s\n" +
                        "\t\t\t\tWHERE\n" +
                        "\t\t\t\t\ts.drug_code = T .drug_code\n" +
                        "\t\t\t\tAND s.std_indicator = 1\n" +
                        "\t\t\t\tAND T .dispensing_date_time >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\tAND T .dispensing_date_time <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\tT .ordered_by,\n" +
                        "\t\t\t\t\ts.drug_name,\n" +
                        "\t\t\t\t\tT .drug_code,\n" +
                        "\t\t\t\t\tT .drug_spec,\n" +
                        "\t\t\t\t\tT .drug_units\n" +
                        "\t\t\t\tUNION\n" +
                        "\t\t\t\t\tSELECT\n" +
                        "\t\t\t\t\t\tb.drug_name,\n" +
                        "\t\t\t\t\t\tb.drug_code,\n" +
                        "\t\t\t\t\t\tb.drug_spec,\n" +
                        "\t\t\t\t\t\tb.package_units units,\n" +
                        "\t\t\t\t\t\tA .ordered_by dept_code,\n" +
                        "\t\t\t\t\t\t'outp' AS TYPE,\n" +
                        "\t\t\t\t\t\tSUM (b.costs) total,\n" +
                        "\t\t\t\t\t\tSUM (b.quantity) quantity\n" +
                        "\t\t\t\t\tFROM\n" +
                        "\t\t\t\t\t\tdrug_presc_master A,\n" +
                        "\t\t\t\t\t\tdrug_presc_detail b\n" +
                        "\t\t\t\t\tWHERE\n" +
                        "\t\t\t\t\t\tA .presc_date = b.presc_date\n" +
                        "\t\t\t\t\tAND A .presc_no = b.presc_no\n" +
                        "\t\t\t\t\tAND A .presc_date >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\t\tAND A .presc_date <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\t\tA .ordered_by,\n" +
                        "\t\t\t\t\t\tb.drug_name,\n" +
                        "\t\t\t\t\t\tb.drug_code,\n" +
                        "\t\t\t\t\t\tb.drug_spec,\n" +
                        "\t\t\t\t\t\tb.package_units\n" +
                        "\t\t\t)\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\tTYPE,\n" +
                        "\t\t\tdept_code,\n" +
                        "\t\t\tdrug_name,\n" +
                        "\t\t\tdrug_code,\n" +
                        "\t\t\tdrug_spec,\n" +
                        "\t\t\tunits\n" +
                        "\t) x\n" +
                        "WHERE\n" +
                        "\tx.AMOUNT > 0\n" +
                        "AND x.TOTAL > 0\n" +
                        "AND x.DEPT_CODE IS NOT NULL";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<DrugAmountDept> drugAmountDepts = new ArrayList<>();
                while (resultSet.next()) {
                    DrugAmountDept drugAmountDept = new DrugAmountDept();
                    drugAmountDept.setDrugCode(resultSet.getString(3));
                    drugAmountDept.setDrugName(resultSet.getString(2));
                    drugAmountDept.setDrugSpec(resultSet.getString(4));
                    drugAmountDept.setUnits(resultSet.getString(5));
                    drugAmountDept.setType(resultSet.getString(6));
                    drugAmountDept.setTotal(resultSet.getFloat(7));
                    drugAmountDept.setAmount(resultSet.getInt(8));
                    drugAmountDept.setDeptCode(resultSet.getString(1));
                    drugAmountDept.setTime(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
                    drugAmountDepts.add(drugAmountDept);
                }
                return drugAmountDepts;
            }
        };
        return jdbcCallBack.execute();
    }

    public static List<DrugAmountDoctor> calculateDoctor(String time) throws Exception {
        final String beginDate = time + " 00:00:00";
        final String endDate = time + " 23:59:59";
        JDBCCallBack<List<DrugAmountDoctor>> jdbcCallBack = new JDBCCallBack<List<DrugAmountDoctor>>() {
            @Override
            public List<DrugAmountDoctor> doInJDBCCallBack() throws SQLException, ParseException {
                String sql = "SELECT * FROM\n" +
                        "(SELECT\n" +
                        "\tdept_code,\n" +
                        "\tdoctor,\n" +
                        "\tdrug_name,\n" +
                        "\tdrug_code,\n" +
                        "\tdrug_spec,\n" +
                        "\tunits,\n" +
                        "  type,\n" +
                        "\tSUM (total) total,\n" +
                        "\tSUM (quantity) amount\n" +
                        "FROM\n" +
                        "\t(\n" +
                        "\t\tSELECT\n" +
                        "\t\t\tordered_by dept_code,\n" +
                        "\t\t\tA .prescribed_by doctor,\n" +
                        "\t\t\tb.drug_name,\n" +
                        "\t\t\tb.drug_code,\n" +
                        "\t\t\tb.drug_spec,\n" +
                        "\t\t\tb.package_units units,\n" +
                        "      'outp' AS TYPE,\n" +
                        "\t\t\tSUM (b.costs) total,\n" +
                        "\t\t\tSUM (b.quantity) quantity\n" +
                        "\t\tFROM\n" +
                        "\t\t\tdrug_presc_master A,\n" +
                        "\t\t\tdrug_presc_detail b\n" +
                        "\t\tWHERE\n" +
                        "\t\t\tA .presc_date >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\tAND A .presc_date <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\tAND A .presc_date = b.presc_date\n" +
                        "\t\tAND A .presc_no = b.presc_no\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\tordered_by,\n" +
                        "\t\t\tA .prescribed_by,\n" +
                        "\t\t\tb.drug_name,\n" +
                        "\t\t\tb.drug_code,\n" +
                        "\t\t\tb.drug_spec,\n" +
                        "\t\t\tb.package_units\n" +
                        "\t\tUNION\n" +
                        "\t\t\tSELECT\n" +
                        "\t\t\t\tc.ordering_dept dept_code,\n" +
                        "\t\t\t\tc.doctor,\n" +
                        "\t\t\t\tD .ITEM_NAME drug_name,\n" +
                        "\t\t\t\tD .ITEM_CODE drug_code,\n" +
                        "\t\t\t\tD .ITEM_SPEC drug_spec,\n" +
                        "\t\t\t\tD .UNITS units,\n" +
                        "        'inp' AS TYPE,\n" +
                        "\t\t\t\tSUM (D .costs) total,\n" +
                        "\t\t\t\tSUM (D .total_amount) quantity\n" +
                        "\t\t\tFROM\n" +
                        "\t\t\t\torders c,\n" +
                        "\t\t\t\torders_costs D\n" +
                        "\t\t\tWHERE\n" +
                        "\t\t\t\tc.patient_id = D .patient_id\n" +
                        "\t\t\tAND c.visit_id = D .visit_id\n" +
                        "\t\t\tAND c.order_no = D .order_no\n" +
                        "\t\t\tAND c.order_sub_no = D .order_sub_no\n" +
                        "\t\t\tAND D .item_class = 'A'\n" +
                        "\t\t\tAND c.enter_date_time >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\tAND c.enter_date_time <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\tGROUP BY\n" +
                        "\t\t\t\tc.ordering_dept,\n" +
                        "\t\t\t\tc.doctor,\n" +
                        "\t\t\t\tD .ITEM_NAME,\n" +
                        "\t\t\t\tD .ITEM_CODE,\n" +
                        "\t\t\t\tD .ITEM_SPEC,\n" +
                        "\t\t\t\tD .UNITS\n" +
                        "\t)\n" +
                        "GROUP BY\n" +
                        "  type,\n" +
                        "\tdept_code,\n" +
                        "\tdoctor,\n" +
                        "\tdrug_name,\n" +
                        "\tdrug_code,\n" +
                        "\tdrug_spec,\n" +
                        "\tunits)x WHERE x.AMOUNT>0 AND x.TOTAL>0 AND x.DOCTOR is not NULL AND x.DEPT_CODE is not NULL";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<DrugAmountDoctor> drugAmountDoctors = new ArrayList<>();
                while (resultSet.next()) {
                    DrugAmountDoctor drugAmountDoctor = new DrugAmountDoctor();
                    drugAmountDoctor.setDrugCode(resultSet.getString(4));
                    drugAmountDoctor.setDrugName(resultSet.getString(3));
                    drugAmountDoctor.setDrugSpec(resultSet.getString(5));
                    drugAmountDoctor.setUnits(resultSet.getString(6));
                    drugAmountDoctor.setType(resultSet.getString(7));
                    drugAmountDoctor.setTotal(resultSet.getFloat(8));
                    drugAmountDoctor.setAmount(resultSet.getInt(9));
                    drugAmountDoctor.setDeptCode(resultSet.getString(1));
                    drugAmountDoctor.setDoctor(resultSet.getString(2));
                    drugAmountDoctor.setTime(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
                    drugAmountDoctors.add(drugAmountDoctor);
                }
                return drugAmountDoctors;
            }
        };
        return jdbcCallBack.execute();
    }

    private static List<DrugAmountGlobal> calculateOutp(final String beginDate, final String endDate) throws Exception {
        JDBCCallBack<List<DrugAmountGlobal>> jdbcCallBack = new JDBCCallBack<List<DrugAmountGlobal>>() {
            @Override
            public List<DrugAmountGlobal> doInJDBCCallBack() throws SQLException, ParseException {
                String sql = "SELECT\n" +
                        "\t*\n" +
                        "FROM\n" +
                        "\t(\n" +
                        "\t\tSELECT\n" +
                        "\t\t\titem_code drug_code,\n" +
                        "\t\t\titem_name drug_name,\n" +
                        "\t\t\titem_spec drug_spec,\n" +
                        "\t\t\tunits,\n" +
                        "\t\t\tSUM (costs) total,\n" +
                        "\t\t\tSUM (amount) amount\n" +
                        "\t\tFROM\n" +
                        "\t\t\toutp_bill_items\n" +
                        "\t\tWHERE\n" +
                        "\t\t\tvisit_date >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\tAND visit_date <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\tAND item_class = 'A'\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\titem_code,\n" +
                        "\t\t\titem_name,\n" +
                        "\t\t\titem_spec,\n" +
                        "\t\t\tunits\n" +
                        "\t) x\n" +
                        "WHERE\n" +
                        "\tx.AMOUNT > 0\n" +
                        "AND x.TOTAL > 0";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<DrugAmountGlobal> drugAmountGlobals = new ArrayList<>();
                while (resultSet.next()) {
                    DrugAmountGlobal drugAmountGlobal = new DrugAmountGlobal();
                    drugAmountGlobal.setDrugCode(resultSet.getString(1));
                    drugAmountGlobal.setDrugName(resultSet.getString(2));
                    drugAmountGlobal.setDrugSpec(resultSet.getString(3));
                    drugAmountGlobal.setUnits(resultSet.getString(4));
                    drugAmountGlobal.setTotal(resultSet.getFloat(5));
                    drugAmountGlobal.setAmount(resultSet.getInt(6));
                    drugAmountGlobal.setType("outp");
                    drugAmountGlobal.setTime(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
                    drugAmountGlobals.add(drugAmountGlobal);
                }
                return drugAmountGlobals;
            }
        };
        return jdbcCallBack.execute();
    }

    private static List<DrugAmountGlobal> calculateInp(final String beginDate, final String endDate) throws Exception {
        JDBCCallBack<List<DrugAmountGlobal>> jdbcCallBack = new JDBCCallBack<List<DrugAmountGlobal>>() {
            @Override
            public List<DrugAmountGlobal> doInJDBCCallBack() throws SQLException, ParseException {
                String sql = "SELECT\n" +
                        "\t*\n" +
                        "FROM\n" +
                        "\t(\n" +
                        "\t\tSELECT\n" +
                        "\t\t\tc.item_code drug_code,\n" +
                        "\t\t\tc.item_name drug_name,\n" +
                        "\t\t\tc.item_spec drug_spec,\n" +
                        "\t\t\tc.units,\n" +
                        "\t\t\tSUM (c.costs) total,\n" +
                        "\t\t\tSUM (c.total_amount) amount\n" +
                        "\t\tFROM\n" +
                        "\t\t\torders_costs c,\n" +
                        "\t\t\torders D\n" +
                        "\t\tWHERE\n" +
                        "\t\t\tc.patient_id = D .patient_id\n" +
                        "\t\tAND c.visit_id = D .visit_id\n" +
                        "\t\tAND c.order_no = D .order_no\n" +
                        "\t\tAND c.order_sub_no = D .order_sub_no\n" +
                        "\t\tAND c.item_class = 'A'\n" +
                        "\t\tAND D .enter_date_time >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\tAND D .enter_date_time <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\tAND c.costs > 0\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\tc.item_code,\n" +
                        "\t\t\tc.item_name,\n" +
                        "\t\t\tc.item_spec,\n" +
                        "\t\t\tc.units\n" +
                        "\t) x\n" +
                        "WHERE\n" +
                        "\tx.AMOUNT > 0\n" +
                        "AND x.TOTAL > 0";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<DrugAmountGlobal> drugAmountGlobals = new ArrayList<>();
                while (resultSet.next()) {
                    DrugAmountGlobal drugAmountGlobal = new DrugAmountGlobal();
                    drugAmountGlobal.setDrugCode(resultSet.getString(1));
                    drugAmountGlobal.setDrugName(resultSet.getString(2));
                    drugAmountGlobal.setDrugSpec(resultSet.getString(3));
                    drugAmountGlobal.setUnits(resultSet.getString(4));
                    drugAmountGlobal.setTotal(resultSet.getFloat(5));
                    drugAmountGlobal.setAmount(resultSet.getInt(6));
                    drugAmountGlobal.setType("inp");
                    drugAmountGlobal.setTime(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
                    drugAmountGlobals.add(drugAmountGlobal);
                }
                return drugAmountGlobals;
            }
        };
        return jdbcCallBack.execute();
    }

    public static void main(String[] args) {
        try {
            System.out.println(CalculateDrugAmount.calculateDoctor("2015-02-02").size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
