package com.drug.platform.controller;

import com.drug.platform.utils.BytesHelper;
import com.drug.platform.utils.JExcel;
import jxl.write.Label;
import jxl.write.WritableCell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaochao on 2016/4/20.
 * 数据导出接口
 */
@Controller
@RequestMapping("/export")
public class DataExportController {

    /**
     * 导出药费比数据
     *
     * @param request
     */
    @RequestMapping("/thanDrugsData")
    public void exportThanDrugsData(HttpServletRequest request, HttpServletResponse response) {

        String fileName = "thanDrugs_" + System.currentTimeMillis() + ".xls";
        Label label0 = new Label(0, 0, "科室名称");
        Label label1 = new Label(1, 0, "药费（元）");
        Label label2 = new Label(2, 0, "治疗费（元）");
        Label label3 = new Label(3, 0, "总数（元）");
        Label label4 = new Label(4, 0, "药占比（%）");
        Label label5 = new Label(5, 0, "目标值（%）");
        Label label6 = new Label(6, 0, "排名");
        List<WritableCell> cells = new ArrayList<>();
        cells.add(label0);
        cells.add(label1);
        cells.add(label2);
        cells.add(label3);
        cells.add(label4);
        cells.add(label5);
        cells.add(label6);
        JExcel.create(fileName, cells);
        response.setContentType("application/vnd.ms-excel");
        String excelFileName = "drugs.xls";
        try {
            excelFileName = URLEncoder.encode("药费比（2015-10-03至2016-11-03）.xls", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename=" + excelFileName);
        try {
            OutputStream ouputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(fileName);
            byte[] bytes = BytesHelper.readFromInputStream(inputStream);
            ouputStream.write(bytes);
            ouputStream.flush();
            ouputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File thanDrugsExcel = new File(fileName);
        if (thanDrugsExcel.exists()) {
            thanDrugsExcel.delete();
        }
    }

//    @RequestMapping(value = "/chart", method = RequestMethod.POST)
//    public void chart(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            request.setCharacterEncoding("utf-8");// 设置编码，解决乱码问题
//            String type = request.getParameter("type");
//            String svg = request.getParameter("svg");
//            String filename = request.getParameter("filename");
//            response.setCharacterEncoding("utf-8");// 设置编码，解决乱码问题
//            ServletOutputStream out = response.getOutputStream();
//            if (null != type && null != svg) {
//                svg = svg.replaceAll(":rect", "rect");
//                String ext = "";
//                SVGAbstractTranscoder t = null;
//                if (type.equals("image/png")) {
//                    ext = "png";
//                    t = new PNGTranscoder();
//
//                } else if (type.equals("image/jpeg")) {
//                    ext = "jpg";
//                    t = new JPEGTranscoder();
//
//                } else if (type.equals("image/svg+xml")) {
//                    ext = "svg";
//                }
//                response.addHeader("Content-Disposition", "attachment; filename="
//                        + new String(filename.getBytes("utf-8"), "iso-8859-1") + "." + ext);
//                response.addHeader("Content-Type", type);
//
//                if (null != t) {
//                    TranscoderInput input = new TranscoderInput(new StringReader(svg));
//                    TranscoderOutput output = new TranscoderOutput(out);
//                    try {
//                        t.transcode(input, output);
//                    } catch (TranscoderException e) {
//                        out.print("Problem transcoding stream. See the web logs for more details.");
//                        e.printStackTrace();
//                    }
//
//                } else if (ext.equals("svg")) {
//                    out.print(svg);
//                } else {
//                    out.print("Invalid type: " + type);
//                }
//            } else {
//                response.addHeader("Content-Type", "text/html");
//                out.println("Usage:\n\tParameter [svg]: The DOM Element to be converted.\n\tParameter [type]: The destination MIME type for the elment to be transcoded.");
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
