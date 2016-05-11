package com.drug.platform.job.calculate;

import com.drug.platform.dal.JDBCCallBack;
import com.drug.platform.model.Par;
import com.drug.platform.model.ParByDoctor;
import com.drug.platform.utils.DateFormatUtils;
import com.drug.platform.utils.StaUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by ivoryartwork on 2016/5/10.
 * 统计处方金额排名
 */
public class CalculatePar {

    public static List<Par> calculate(String time) throws Exception {
        final String beginDate = time + " 00:00:00";
        final String endDate = time + " 23:59:59";
        JDBCCallBack<List<Par>> jdbcCallBack = new JDBCCallBack<List<Par>>() {
            @Override
            public List<Par> doInJDBCCallBack() throws SQLException, ParseException {
                String sql = "SELECT\n" +
                        "\tf.patient_id,\n" +
                        "\tE .visit_date,\n" +
                        "\tE .visit_no,\n" +
                        "\tE .rcpt_no,\n" +
                        "\tG .dept_name,\n" +
                        "\tE .ordered_by_doctor,\n" +
                        "\tNAME,\n" +
                        "\tSUM (costs) total\n" +
                        "FROM\n" +
                        "\toutp_bill_items D,\n" +
                        "\toutp_order_desc E,\n" +
                        "\tpat_master_index f,\n" +
                        "\tdept_dict G\n" +
                        "WHERE\n" +
                        "\tD .visit_date = E .visit_date\n" +
                        "AND D .visit_no = E .visit_no\n" +
                        "AND E .patient_id = f.patient_id\n" +
                        "AND D .visit_date >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "AND D .visit_date <= TO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "AND item_class = 'A'\n" +
                        "AND E .ordered_by_dept = G .dept_code\n" +
                        "GROUP BY\n" +
                        "\tf.patient_id,\n" +
                        "\tE .visit_date,\n" +
                        "\tE .visit_no,\n" +
                        "\tE .rcpt_no,\n" +
                        "\tG .dept_name,\n" +
                        "\tE .ordered_by_doctor,\n" +
                        "\tNAME\n" +
                        "ORDER BY\n" +
                        "\ttotal DESC";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<Par> pars = new ArrayList<>();
                while (resultSet.next()) {
                    Par par = new Par();
                    par.setPatientId(resultSet.getString(1));
                    par.setVisitDate(resultSet.getDate(2));
                    par.setVisitNo(resultSet.getString(3));
                    par.setRcptNo(resultSet.getString(4));
                    par.setDeptName(resultSet.getString(5));
                    par.setDoctor(resultSet.getString(6));
                    par.setPatientName(resultSet.getString(7));
                    par.setTotal(resultSet.getFloat(8));
                    pars.add(par);
                }
                return pars;
            }
        };
        return jdbcCallBack.execute();
    }

    public static List<ParByDoctor> calculateByDoctor(String time) throws Exception {
        final String beginDate = time + " 00:00:00";
        final String endDate = time + " 23:59:59";
        JDBCCallBack<List<ParByDoctor>> jdbcCallBack = new JDBCCallBack<List<ParByDoctor>>() {
            @Override
            public List<ParByDoctor> doInJDBCCallBack() throws SQLException, ParseException {
                String sql = "SELECT\n" +
                        "\tf.dept_name,\n" +
                        "\tE .ordered_by_doctor,\n" +
                        "\tSUM (costs) total\n" +
                        "FROM\n" +
                        "\toutp_bill_items D,\n" +
                        "\toutp_order_desc E,\n" +
                        "\tdept_dict f\n" +
                        "WHERE\n" +
                        "\tD .visit_date = E .visit_date\n" +
                        "AND D .visit_no = E .visit_no\n" +
                        "AND E .ordered_by_dept = f.dept_code\n" +
                        "AND D .visit_date >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "AND D .visit_date <= (\n" +
                        "\tTO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "') + 1\n" +
                        ")\n" +
                        "AND item_class = 'A'\n" +
                        "GROUP BY\n" +
                        "\tf.dept_name,\n" +
                        "\tE .ordered_by_doctor\n" +
                        "ORDER BY\n" +
                        "\ttotal DESC";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<ParByDoctor> parByDoctors = new ArrayList<>();
                while (resultSet.next()) {
                    ParByDoctor parByDoctor = new ParByDoctor();
                    parByDoctor.setDeptName(resultSet.getString(1));
                    parByDoctor.setDoctor(resultSet.getString(2));
                    parByDoctor.setTotal(resultSet.getFloat(3));
                    parByDoctor.setTime(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
                    parByDoctors.add(parByDoctor);
                }
                return parByDoctors;
            }
        };
        return jdbcCallBack.execute();
    }

    public static List<Map<String, Object>> calculatePresDetail(final String visitNo, final String rcptNo, String time) {
        final String beginDate = time + " 00:00:00";
        final String endDate = time + " 23:59:59";
        JDBCCallBack<List<Map<String, Object>>> jdbcCallBack = new JDBCCallBack<List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> doInJDBCCallBack() throws SQLException, ParseException {
                String sql = "SELECT\n" +
                        "  ROWNUM,\n" +
                        "\tG .dept_name,\n" +
                        "\tE .ordered_by_doctor,\n" +
                        "\tf.patient_id,\n" +
                        "\tNAME,\n" +
                        "\tD .item_no,\n" +
                        "\tD .item_code,\n" +
                        "\tD .item_name,\n" +
                        "\tD .item_spec,\n" +
                        "\tD .units,\n" +
                        "\tD .amount,\n" +
                        "\tE .visit_date,\n" +
                        "\tE .visit_no,\n" +
                        "\tE .rcpt_no,\n" +
                        "\tD .costs\n" +
                        "FROM\n" +
                        "\toutp_bill_items D,\n" +
                        "\toutp_order_desc E,\n" +
                        "\tpat_master_index f,\n" +
                        "\tdept_dict G\n" +
                        "WHERE\n" +
                        "\tD .visit_date = E .visit_date\n" +
                        "AND E .patient_id = f.patient_id\n" +
                        "AND D .visit_no = E .visit_no\n" +
                        "AND D .item_class = 'A'\n" +
                        "AND E .ordered_by_dept = G .dept_code\n" +
                        "AND D .rcpt_no = '" + rcptNo + "'\n" +
                        "AND D .visit_no = '" + visitNo + "'\n" +
                        "AND D .visit_date >= TO_DATE ('" + beginDate + "', '" + StaUtil.oracleDateFormatStr + "')\n" +
                        "AND D .visit_date <= (\n" +
                        "\tTO_DATE ('" + endDate + "', '" + StaUtil.oracleDateFormatStr + "') + 1\n" +
                        ")\n" +
                        "ORDER BY\n" +
                        "\tD .item_no";
                statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<Map<String, Object>> mapList = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("num", resultSet.getInt(1));
                    map.put("deptName", resultSet.getString(2));
                    map.put("doctor", resultSet.getString(3));
                    map.put("patientId", resultSet.getString(4));
                    map.put("patientName", resultSet.getString(5));
                    map.put("drugNo", resultSet.getString(6));
                    map.put("drugCode", resultSet.getString(7));
                    map.put("drugName", resultSet.getString(8));
                    map.put("drugSpec", resultSet.getString(9));
                    map.put("units", resultSet.getString(10));
                    map.put("amount", resultSet.getInt(11));
                    map.put("visitDate", resultSet.getTimestamp(12));
                    map.put("visitNo", resultSet.getString(13));
                    map.put("rcptNo", resultSet.getString(14));
                    map.put("costs", resultSet.getFloat(15));
                    mapList.add(map);
                }
                return mapList;
            }
        };
        return jdbcCallBack.execute();
    }

//    public static void main(String[] args) {
//        try {
//            System.out.println(CalculatePar.calculateByDoctor("2015-02-05"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
