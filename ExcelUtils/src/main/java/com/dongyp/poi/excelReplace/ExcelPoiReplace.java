package com.dongyp.poi.excelReplace;

import com.dongyp.vo.ExcelHeaderVo;
import com.dongyp.vo.ExcelSheetVo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.InputStream;
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
public class ExcelPoiReplace {
    public static void replace(List<ExcelSheetVo> sheetVos, InputStream in, OutputStream out){
        HSSFWorkbook wb = null;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(in);
            wb = new HSSFWorkbook(fs);
            int initCount = wb.getNumberOfSheets();

            for(int i = 0; i < sheetVos.size(); i++){
                ExcelSheetVo sheetVo = sheetVos.get(i);
                int contentRow = sheetVo.getExcelContentVo().getValues().size();
                int totalColumn = sheetVo.getExcelContentVo().getValues().get(0).size();

                HSSFSheet sheet = wb.cloneSheet(0);
                wb.setSheetName( i + initCount, sheetVo.getSheetName());

                //标题栏
                for(ExcelHeaderVo headerVo : sheetVo.getExcelHeaderVos()){
                    setValue(sheet, headerVo.getRow(), headerVo.getColumn(), headerVo.getValue());
                }

                //数据内容
                for(int rowCount = 0; rowCount < contentRow; rowCount++){
                    for(int columnCount = 0; columnCount < totalColumn; columnCount++){
                        setValue(sheet, rowCount + sheetVo.getExcelContentVo().getStartRow(), columnCount,
                                sheetVo.getExcelContentVo().getValues().get(rowCount).get(columnCount));
                    }
                }
            }

            wb.removeSheetAt(0);
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(wb == null){
                    wb.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setValue(HSSFSheet sheet, int rowCount, int columnCount, String value){
        HSSFRow row = sheet.getRow(rowCount);
        if(row == null){
            row = sheet.createRow(rowCount);
        }

        HSSFCell cell = row.getCell(columnCount, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        cell.setCellType(CellType.STRING);
        cell.setCellValue(value);

//        HSSFCell cell = row.getCell(columnCount);
//        if(cell == null){
//            cell = row.createCell(columnCount);
//        }
//
//        cell.setCellValue(value);
    }
}
