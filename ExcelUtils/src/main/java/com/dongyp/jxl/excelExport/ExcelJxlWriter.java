package com.dongyp.jxl.excelExport;

import com.dongyp.vo.ExcelHeaderVo;
import com.dongyp.vo.ExcelSheetVo;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Copyright (C), 2014-2015, 深圳云集智造系统技术有限公司
 *
 * @Title:
 * @Description:
 * @Author by dongyp
 * @date on 2017/11/28
 */
public class ExcelJxlWriter {
    public static void writer(List<ExcelSheetVo> sheetVos, OutputStream out) {
        if(sheetVos == null || out == null){
            throw new RuntimeException("参数不能为空");
        }
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(out);
            for(int i = 0; i < sheetVos.size(); i++){
                ExcelSheetVo sheetVo = sheetVos.get(i);

                WritableSheet sheet = workbook.createSheet(sheetVo.getSheetName(),i);
                int contentRow = sheetVo.getExcelContentVo().getValues().size();
                int totalColumn = sheetVo.getExcelContentVo().getValues().get(0).size();
                //标题
                for (ExcelHeaderVo excelHeaderVo : sheetVo.getExcelHeaderVos()){
                    Label label = new Label(excelHeaderVo.getColumn(),excelHeaderVo.getRow(),excelHeaderVo.getValue()); //  第一个参数是第几列,第二个参数是第几行
                    sheet.addCell(label);
                }
                //导出数据
                for (int row = 0; row < contentRow; row++){
                    for (int column = 0; column < totalColumn; column++){
                        Label label = new Label(column,row + sheetVo.getExcelContentVo().getStartRow(), sheetVo.getExcelContentVo().getValues().get(row).get(column));
                        sheet.addCell(label);
                    }
                }
            }
            workbook.write();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            try {
                if(workbook != null){
                    workbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }
}
