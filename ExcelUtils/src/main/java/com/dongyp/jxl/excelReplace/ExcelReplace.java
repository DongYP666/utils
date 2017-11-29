package com.dongyp.jxl.excelReplace;

import com.dongyp.vo.ExcelHeaderVo;
import com.dongyp.vo.ExcelSheetVo;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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
 * @date on 2017/11/29
 */
public class ExcelReplace {
    public static void replace(List<ExcelSheetVo> sheetVos, InputStream in, OutputStream out){
        Workbook workbook = null;
        WritableWorkbook writableWorkbook = null;

        try {
            WorkbookSettings newSettings = new WorkbookSettings();
            newSettings.setPropertySets(true);

            workbook = Workbook.getWorkbook(in);
            writableWorkbook = Workbook.createWorkbook(out,workbook,newSettings);
            //Sheet sheet = writableWorkbook.getSheet(0); //读取第一张表

            for (int i = 0; i < sheetVos.size(); i++){
                ExcelSheetVo sheetVo = sheetVos.get(i);
                if(sheetVo.getExcelContentVo().getValues().size() == 0){
                    throw new RuntimeException("导出数据为空");
                }

                writableWorkbook.copySheet(0, sheetVo.getSheetName(), i+2);
                WritableSheet writableSheet = writableWorkbook.getSheet(i+2);

                int contentRow = sheetVo.getExcelContentVo().getValues().size();
                int totalColumn = sheetVo.getExcelContentVo().getValues().get(0).size();
                //标题
                for (ExcelHeaderVo excelHeaderVo : sheetVo.getExcelHeaderVos()){
                    Label label = new Label(excelHeaderVo.getColumn(),excelHeaderVo.getRow(),excelHeaderVo.getValue()); //  第一个参数是第几列,第二个参数是第几行
                    writableSheet.addCell(label);
                }
                //导出数据
                for (int row = 0; row < contentRow; row++){
                    for (int column = 0; column < totalColumn; column++){
                        Label label = new Label(column,row + sheetVo.getExcelContentVo().getStartRow(),sheetVo.getExcelContentVo().getValues().get(row).get(column));
                        writableSheet.addCell(label);
                    }
                }
            }
            writableWorkbook.removeSheet(0);
            writableWorkbook.write();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            try {
                if(workbook != null){
                    workbook.close();
                }
                if(writableWorkbook != null){
                    writableWorkbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }
}
