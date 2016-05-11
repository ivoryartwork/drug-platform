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
                        "\t\t\tSUM (quantity) amount,\n" +
                        "      chargeType\n" +
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
                        "\t\t\t\t\tSUM (T .dispense_amount) quantity,\n" +
                        "          E.CHARGE_TYPE chargeType\n" +
                        "\t\t\t\tFROM\n" +
                        "\t\t\t\t\tdrug_dispense_rec T,\n" +
                        "\t\t\t\t\tdrug_name_dict s,\n" +
                        "          pat_master_index E\n" +
                        "\t\t\t\tWHERE\n" +
                        "\t\t\t\t\ts.drug_code = T .drug_code\n" +
                        "\t\t\t\tAND s.std_indicator = 1\n" +
                        "        AND T.PATIENT_ID = E.PATIENT_ID\n" +
                        "\t\t\t\tAND T .dispensing_date_time >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\tAND T .dispensing_date_time <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\tT .ordered_by,\n" +
                        "\t\t\t\t\ts.drug_name,\n" +
                        "\t\t\t\t\tT .drug_code,\n" +
                        "\t\t\t\t\tT .drug_spec,\n" +
                        "\t\t\t\t\tT .drug_units,\n" +
                        "          E.CHARGE_TYPE\n" +
                        "\t\t\t\tUNION\n" +
                        "\t\t\t\t\tSELECT\n" +
                        "\t\t\t\t\t\tb.drug_name,\n" +
                        "\t\t\t\t\t\tb.drug_code,\n" +
                        "\t\t\t\t\t\tb.drug_spec,\n" +
                        "\t\t\t\t\t\tb.package_units units,\n" +
                        "\t\t\t\t\t\tA .ordered_by dept_code,\n" +
                        "\t\t\t\t\t\t'outp' AS TYPE,\n" +
                        "\t\t\t\t\t\tSUM (b.costs) total,\n" +
                        "\t\t\t\t\t\tSUM (b.quantity) quantity,\n" +
                        "            C.CHARGE_TYPE chargeType\n" +
                        "\t\t\t\t\tFROM\n" +
                        "\t\t\t\t\t\tdrug_presc_master A,\n" +
                        "\t\t\t\t\t\tdrug_presc_detail b,\n" +
                        "            pat_master_index C\n" +
                        "\t\t\t\t\tWHERE\n" +
                        "\t\t\t\t\t\tA .presc_date = b.presc_date\n" +
                        "          AND A.PATIENT_ID = C.PATIENT_ID\n" +
                        "\t\t\t\t\tAND A .presc_no = b.presc_no\n" +
                        "\t\t\t\t\tAND A .presc_date >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\t\tAND A .presc_date <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\t\tA .ordered_by,\n" +
                        "\t\t\t\t\t\tb.drug_name,\n" +
                        "\t\t\t\t\t\tb.drug_code,\n" +
                        "\t\t\t\t\t\tb.drug_spec,\n" +
                        "\t\t\t\t\t\tb.package_units,\n" +
                        "            C.CHARGE_TYPE\n" +
                        "\t\t\t)\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\tTYPE,\n" +
                        "\t\t\tdept_code,\n" +
                        "\t\t\tdrug_name,\n" +
                        "\t\t\tdrug_code,\n" +
                        "\t\t\tdrug_spec,\n" +
                        "\t\t\tunits,\n" +
                        "      chargeType\n" +
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
                    drugAmountDept.setChargeType(resultSet.getString(9));
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
                String sql = "SELECT\n" +
                        "\t*\n" +
                        "FROM\n" +
                        "\t(\n" +
                        "\t\tSELECT\n" +
                        "\t\t\tdept_code,\n" +
                        "\t\t\tdoctor,\n" +
                        "\t\t\tdrug_name,\n" +
                        "\t\t\tdrug_code,\n" +
                        "\t\t\tdrug_spec,\n" +
                        "\t\t\tunits,\n" +
                        "\t\t\tTYPE,\n" +
                        "\t\t\tSUM (total) total,\n" +
                        "\t\t\tSUM (quantity) amount,\n" +
                        "\t\t\tSUM (patient_amount) patient_amount,\n" +
                        "\t\t\tchargeType\n" +
                        "\t\tFROM\n" +
                        "\t\t\t(\n" +
                        "\t\t\t\tSELECT\n" +
                        "\t\t\t\t\tordered_by dept_code,\n" +
                        "\t\t\t\t\tA .prescribed_by doctor,\n" +
                        "\t\t\t\t\tb.drug_name,\n" +
                        "\t\t\t\t\tb.drug_code,\n" +
                        "\t\t\t\t\tb.drug_spec,\n" +
                        "\t\t\t\t\tb.package_units units,\n" +
                        "\t\t\t\t\t'outp' AS TYPE,\n" +
                        "\t\t\t\t\tSUM (b.costs) total,\n" +
                        "\t\t\t\t\tSUM (b.quantity) quantity,\n" +
                        "\t\t\t\t\tCOUNT (C.PATIENT_ID) patient_amount,\n" +
                        "\t\t\t\t\tC.CHARGE_TYPE chargeType\n" +
                        "\t\t\t\tFROM\n" +
                        "\t\t\t\t\tdrug_presc_master A,\n" +
                        "\t\t\t\t\tdrug_presc_detail b,\n" +
                        "\t\t\t\t\tpat_master_index C\n" +
                        "\t\t\t\tWHERE\n" +
                        "\t\t\t\t\tA .presc_date >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\tAND A .presc_date <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\tAND A .presc_date = b.presc_date\n" +
                        "\t\t\t\tAND A .presc_no = b.presc_no\n" +
                        "\t\t\t\tAND A .PATIENT_ID = C.PATIENT_ID\n" +
                        "\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\tordered_by,\n" +
                        "\t\t\t\t\tA .prescribed_by,\n" +
                        "\t\t\t\t\tb.drug_name,\n" +
                        "\t\t\t\t\tb.drug_code,\n" +
                        "\t\t\t\t\tb.drug_spec,\n" +
                        "\t\t\t\t\tb.package_units,\n" +
                        "\t\t\t\t\tC.CHARGE_TYPE\n" +
                        "\t\t\t\tUNION\n" +
                        "\t\t\t\t\tSELECT\n" +
                        "\t\t\t\t\t\tc.ordering_dept dept_code,\n" +
                        "\t\t\t\t\t\tc.doctor,\n" +
                        "\t\t\t\t\t\tD .ITEM_NAME drug_name,\n" +
                        "\t\t\t\t\t\tD .ITEM_CODE drug_code,\n" +
                        "\t\t\t\t\t\tD .ITEM_SPEC drug_spec,\n" +
                        "\t\t\t\t\t\tD .UNITS units,\n" +
                        "\t\t\t\t\t\t'inp' AS TYPE,\n" +
                        "\t\t\t\t\t\tSUM (D .costs) total,\n" +
                        "\t\t\t\t\t\tSUM (D .total_amount) quantity,\n" +
                        "\t\t\t\t\t\tCOUNT (D .PATIENT_ID) patient_amount,\n" +
                        "\t\t\t\t\t\tE .CHARGE_TYPE chargeType\n" +
                        "\t\t\t\t\tFROM\n" +
                        "\t\t\t\t\t\torders c,\n" +
                        "\t\t\t\t\t\torders_costs D,\n" +
                        "\t\t\t\t\t\tpat_master_index E\n" +
                        "\t\t\t\t\tWHERE\n" +
                        "\t\t\t\t\t\tc.patient_id = D .patient_id\n" +
                        "\t\t\t\t\tAND D .PATIENT_ID = E .PATIENT_ID\n" +
                        "\t\t\t\t\tAND c.visit_id = D .visit_id\n" +
                        "\t\t\t\t\tAND c.order_no = D .order_no\n" +
                        "\t\t\t\t\tAND c.order_sub_no = D .order_sub_no\n" +
                        "\t\t\t\t\tAND D .item_class = 'A'\n" +
                        "\t\t\t\t\tAND c.enter_date_time >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\t\tAND c.enter_date_time <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\t\t\t\tGROUP BY\n" +
                        "\t\t\t\t\t\tc.ordering_dept,\n" +
                        "\t\t\t\t\t\tc.doctor,\n" +
                        "\t\t\t\t\t\tD .ITEM_NAME,\n" +
                        "\t\t\t\t\t\tD .ITEM_CODE,\n" +
                        "\t\t\t\t\t\tD .ITEM_SPEC,\n" +
                        "\t\t\t\t\t\tD .UNITS,\n" +
                        "\t\t\t\t\t\tE .CHARGE_TYPE\n" +
                        "\t\t\t)\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\tTYPE,\n" +
                        "\t\t\tdept_code,\n" +
                        "\t\t\tdoctor,\n" +
                        "\t\t\tdrug_name,\n" +
                        "\t\t\tdrug_code,\n" +
                        "\t\t\tdrug_spec,\n" +
                        "\t\t\tunits,\n" +
                        "\t\t\tchargeType\n" +
                        "\t) x\n" +
                        "WHERE\n" +
                        "\tx.AMOUNT > 0\n" +
                        "AND x.TOTAL > 0\n" +
                        "AND x.DOCTOR IS NOT NULL\n" +
                        "AND x.DEPT_CODE IS NOT NULL";
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
                    drugAmountDoctor.setPatientAmount(resultSet.getInt(10));
                    drugAmountDoctor.setChargeType(resultSet.getString(11));
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
                        "\t\t\tA.item_code drug_code,\n" +
                        "\t\t\tA.item_name drug_name,\n" +
                        "\t\t\tA.item_spec drug_spec,\n" +
                        "\t\t\tA.units,\n" +
                        "\t\t\tSUM (A.costs) total,\n" +
                        "\t\t\tSUM (A.amount) amount,\n" +
                        "      C.CHARGE_TYPE chargeType\n" +
                        "\t\tFROM\n" +
                        "\t\t\toutp_bill_items A,\n" +
                        "      outp_order_desc B,\n" +
                        "      pat_master_index C\n" +
                        "\t\tWHERE\n" +
                        "      A.VISIT_DATE = B.VISIT_DATE\n" +
                        "    AND A.VISIT_NO = B.VISIT_NO\n" +
                        "    AND B.PATIENT_ID = C.PATIENT_ID\n" +
                        "\t\tAND\tA.visit_date >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\tAND A.visit_date <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "\t\tAND A.item_class = 'A'\n" +
                        "\t\tGROUP BY\n" +
                        "\t\t\tA.item_code,\n" +
                        "\t\t\tA.item_name,\n" +
                        "\t\t\tA.item_spec,\n" +
                        "\t\t\tA.units,\n" +
                        "      C.CHARGE_TYPE\n" +
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
                    drugAmountGlobal.setChargeType(resultSet.getString(7));
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
                        "\t\t\tSUM (c.total_amount) amount,\n" +
                        "      E.CHARGE_TYPE chargeType\n" +
                        "\t\tFROM\n" +
                        "\t\t\torders_costs c,\n" +
                        "\t\t\torders D,\n" +
                        "      pat_master_index E\n" +
                        "\t\tWHERE\n" +
                        "\t\t\tc.patient_id = D .patient_id\n" +
                        "    AND E.PATIENT_ID = D.PATIENT_ID\n" +
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
                        "\t\t\tc.units,\n" +
                        "      E.CHARGE_TYPE\n" +
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
                    drugAmountGlobal.setChargeType(resultSet.getString(7));
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
