package com.dongyp.poi.excelExport;

import com.dongyp.vo.ExcelHeaderVo;
import com.dongyp.vo.ExcelSheetVo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Copyright (C), 2014-2015, 深圳云集智造系统技术有限公司
 *
 * @Title:
 * @Description:
 * @Author by dongyp
 * @date on 2017/12/4
 */
public class ExcelPoiWriter {
    public static void writer(List<ExcelSheetVo> sheetVos, OutputStream out){
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            for(int i = 0; i < sheetVos.size(); i++){
                ExcelSheetVo sheetVo = sheetVos.get(i);
                int contentRow = sheetVo.getExcelContentVo().getValues().size();
                int totalColumn = sheetVo.getExcelContentVo().getValues().get(0).size();

                //标题栏
                HSSFSheet sheet = wb.createSheet();
                wb.setSheetName(i,sheetVo.getSheetName());
                for(ExcelHeaderVo headerVo : sheetVo.getExcelHeaderVos()){
                    HSSFRow row = sheet.getRow(headerVo.getRow());
                    if(row == null){
                        row = sheet.createRow(headerVo.getRow());
                    }
                    HSSFCell cell = row.getCell(headerVo.getColumn());
                    if(cell == null){
                        cell = row.createCell(headerVo.getColumn());
                    }
                    cell.setCellValue(headerVo.getValue());
                }

                //数据内容
                for(int rowCount = 0; rowCount < contentRow; rowCount++){
                    for(int columnCount = 0; columnCount < totalColumn; columnCount++){
                        HSSFRow row = sheet.getRow(rowCount + sheetVo.getExcelContentVo().getStartRow());
                        if(row == null){
                            row = sheet.createRow(rowCount + sheetVo.getExcelContentVo().getStartRow());
                        }
                        HSSFCell cell = row.getCell(columnCount);
                        if(cell == null){
                            cell = row.createCell(columnCount);
                        }
                        cell.setCellValue(sheetVo.getExcelContentVo().getValues().get(rowCount).get(columnCount));
                    }
                }
            }

            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
