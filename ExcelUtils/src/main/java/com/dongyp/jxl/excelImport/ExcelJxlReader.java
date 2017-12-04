package com.dongyp.jxl.excelImport;

import com.dongyp.vo.ExcelContentVo;
import com.dongyp.vo.ExcelHeaderVo;
import com.dongyp.vo.ExcelSheetVo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

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
 * @date on 2017/11/28
 */
public class ExcelJxlReader {
    /**
     *
     * @param in
     * @param startRow        excel内容开始行数,从0开始算(标题行结束的下一行)
     * @param startColumn     excel内容开始列数,从0开始算
     * @return
     */
        public static List<ExcelSheetVo> reader(InputStream in, int startRow, int startColumn) {
        List<ExcelSheetVo> sheetVos = new ArrayList<ExcelSheetVo>();
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(in);
            Sheet[] sheets = workbook.getSheets();

            for (int i = 0; i < sheets.length; i++){
                ExcelSheetVo sheetVo = new ExcelSheetVo();
                Sheet sheet = workbook.getSheet(i);
                sheetVo.setSheetName(sheet.getName());

                // 标题
                List<ExcelHeaderVo> excelHeaderVos = new ArrayList<ExcelHeaderVo>();
                for (int row = 0; row < startRow; row++){
                    for(int column = 0; column < sheet.getColumns(); column++){
                        Cell cell = sheet.getCell(column,row); // 第一个参数是第几列,第二个参数是第几行
                        excelHeaderVos.add(new ExcelHeaderVo(row, column, cell.getContents()));
                    }
                }
                sheetVo.setExcelHeaderVos(excelHeaderVos);

                // excel表格数据
                ExcelContentVo contentVo = new ExcelContentVo(startRow,startColumn);
                List<List<String>> valueLists = new ArrayList<List<String>>();
                for (int row = startRow; row < sheet.getRows(); row++){
                    List<String> values = new ArrayList<String>();
                    for(int column = 0; column < sheet.getColumns(); column++){
                        Cell cell = sheet.getCell(column,row);
                        values.add(cell.getContents());
                    }
                    valueLists.add(values);
                }
                contentVo.setValues(valueLists);
                sheetVo.setExcelContentVo(contentVo);
                sheetVos.add(sheetVo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {
            workbook.close();
        }
        return sheetVos;
    }
}
