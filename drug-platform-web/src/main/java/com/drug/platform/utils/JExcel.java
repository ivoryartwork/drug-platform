package com.drug.platform.utils;

import jxl.Workbook;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yaochao on 2016/4/20.
 * excel操作类
 */
public class JExcel {

    /**
     * 创建excel表格
     *
     * @param path
     * @param cells
     */
    public static void create(String path, List<WritableCell> cells) {
        if (cells.size() == 0) {
            return;
        }
        try {
            WritableWorkbook book = Workbook.createWorkbook(new File(path));
            WritableSheet sheet = book.createSheet("第一页", 0);
            for (WritableCell cell : cells) {
                sheet.addCell(cell);
            }
            book.write();
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        try {
//            JExcel jExcel = new JExcel();
//            List<WritableCell> cells = new ArrayList<>();
//            Label label = new Label(0, 0, "testtestclb");
//            //将值添加到单元格中
//            cells.add(label);
//            //生成一个保存数字的单元格，必须使用Number的完整包路径，否则将出现歧异
//            //单元格位置为第二列，第一行，值为555.1234
//            jxl.write.Number number = new jxl.write.Number(0, 1, 555.12345);
//            jxl.write.Number number1 = new jxl.write.Number(0, 2, 555.12345);
//            jxl.write.Number number2 = new jxl.write.Number(0, 3, 555.12345);
//            //将值添加到单元格中
//            cells.add(number);
//            cells.add(number1);
//            cells.add(number2);
//            jExcel.create("testclb.xls", cells);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            //打开文件
//            Workbook book = Workbook.getWorkbook(new File("test.xls"));
//            //获得第一个表的工作对象，“0”表示第一个表
//            Sheet sheet = book.getSheet(0);
//            //得到第一列，第一行的单元格（0，0）
//            Cell cell = sheet.getCell(0, 0);
//            String result = cell.getContents();
//            Cell cell2 = sheet.getCell(0, 1);
//            String result2 = cell2.getContents();
//            System.out.println("**" + result + "**");
//            System.out.println("**" + result2 + "**");
//
//            //取数字的时候强转一下,否则默认只取出小数点后3位
////            if (cell2.getType() == CellType.NUMBER) {
////                NumberCell numberCell = (NumberCell) cell2;
////                double value = numberCell.getValue();
////                System.out.println(value);
////            }
//
//
//            book.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (BiffException e) {
//            e.printStackTrace();
//        }
    }
}
