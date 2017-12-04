package com.dongyp.poi.excelImport;

import com.dongyp.poi.AbstractExcel;
import com.dongyp.vo.ExcelContentVo;
import com.dongyp.vo.ExcelHeaderVo;
import com.dongyp.vo.ExcelSheetVo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2014-2015, 深圳云集智造系统技术有限公司
 *
 * @Title:
 * @Description:
 * @Author by dongyp
 * @date on 2017/12/4
 */
public class ExcelPoiReader extends AbstractExcel {

    /**
     *
     * @param in
     * @param startRow      excel内容开始行数,从0开始算(标题行结束的下一行)
     * @param startColumn   excel内容开始列数,从0开始算
     * @return
     */
    public static List<ExcelSheetVo> reader (InputStream in, int startRow, int startColumn) {
        List<ExcelSheetVo> vos = new ArrayList<ExcelSheetVo>();
        HSSFWorkbook wb = null;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(in);
            wb = new HSSFWorkbook(fs);

            for(int i = 0; i < wb.getNumberOfSheets(); i++){
                HSSFSheet sheet = wb.getSheetAt(i);
                ExcelSheetVo vo = new ExcelSheetVo();
                vo.setSheetName(sheet.getSheetName());

                //读标题
                List<ExcelHeaderVo> headerVos = new ArrayList<ExcelHeaderVo>();
                for(int rowCount = 0; rowCount < startRow; rowCount++){
                    HSSFRow row = sheet.getRow(rowCount);
                    for(int columnCount = 0; columnCount < row.getLastCellNum(); columnCount++){
                        HSSFCell cell = row.getCell(columnCount);
                        ExcelHeaderVo headerVo = new ExcelHeaderVo(rowCount,columnCount,getCellStringValue(cell));
                        headerVos.add(headerVo);
                    }
                }
                vo.setExcelHeaderVos(headerVos);

                //读excel内容
                ExcelContentVo contentVo = new ExcelContentVo(startRow,startColumn);
                List<List<String>> valueList = new ArrayList<List<String>>();
                for(int rowCount = startRow; rowCount < sheet.getLastRowNum(); rowCount++){
                    HSSFRow row = sheet.getRow(rowCount);
                    if(row != null){
                        List<String> values = new ArrayList<String>();
                        for(int columnCount = 0; columnCount < row.getLastCellNum(); columnCount++){
                            HSSFCell cell = row.getCell(columnCount);
                            values.add(getCellStringValue(cell));
                        }
                        valueList.add(values);
                    }
                }
                contentVo.setValues(valueList);
                vo.setExcelContentVo(contentVo);

                vos.add(vo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(wb != null){
                    wb.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return vos;
    }
}
