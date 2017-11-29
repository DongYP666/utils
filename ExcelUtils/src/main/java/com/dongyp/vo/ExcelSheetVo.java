package com.dongyp.vo;

import java.util.List;

/**
 * Copyright (C), 2014-2015, 深圳云集智造系统技术有限公司
 *
 * @Title:
 * @Description:
 * @Author by dongyp
 * @date on 2017/11/28
 */
public class ExcelSheetVo {
    private String sheetName;

    private List<ExcelHeaderVo> excelHeaderVos;

    private ExcelContentVo excelContentVo;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<ExcelHeaderVo> getExcelHeaderVos() {
        return excelHeaderVos;
    }

    public void setExcelHeaderVos(List<ExcelHeaderVo> excelHeaderVos) {
        this.excelHeaderVos = excelHeaderVos;
    }

    public ExcelContentVo getExcelContentVo() {
        return excelContentVo;
    }

    public void setExcelContentVo(ExcelContentVo excelContentVo) {
        this.excelContentVo = excelContentVo;
    }
}
