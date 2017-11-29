package com.dongyp.jxl;

import com.dongyp.jxl.excelExport.ExcelWriter;
import com.dongyp.jxl.excelImport.ExcelReader;
import com.dongyp.jxl.excelReplace.ExcelReplace;
import com.dongyp.vo.Asset;
import com.dongyp.vo.ExcelContentVo;
import com.dongyp.vo.ExcelHeaderVo;
import com.dongyp.vo.ExcelSheetVo;
import jxl.read.biff.BiffException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        //test.testReader();
        //test.testWriter();
        test.testReplace();
    }

    private void testReader() {
        InputStream in = this.getClass().getResourceAsStream("/固定资产导入模板.xls");
        List<ExcelSheetVo> sheetVos = ExcelReader.Reader(in,2,0);
        ExcelSheetVo sheetVo = sheetVos.get(0);

        List<Asset> assetList = new ArrayList<Asset>();
        for(List<String> values : sheetVo.getExcelContentVo().getValues()){
            Asset asset = new Asset();
            asset.setName(values.get(1));
            asset.setCode(values.get(2));
            asset.setSpec(values.get(3));
            asset.setAssetCategoryName(values.get(4));
            asset.setPurchaseDate(StringUtils.isNotBlank(values.get(5)) ? DateUtil.parseYYYYMMDDDate(values.get(5)) : null);
            asset.setManagerName(values.get(6));
            asset.setDepartmentName(values.get(7));
            asset.setUserName(values.get(8));
            asset.setUsePeriod(StringUtils.isNotBlank(values.get(9)) ? Integer.valueOf(values.get(9)) : null);
            asset.setPurchasePrice(StringUtils.isNotBlank(values.get(10)) ? new BigDecimal(values.get(10)) : null);
            asset.setDepreciationMethodName(values.get(11));
            asset.setRemark(values.get(12));
            assetList.add(asset);
        };

        for(int i = 0; i < assetList.size(); i++){
            Asset asset = assetList.get(i);
            System.out.print("第" + (i+1) + "行 -- ");
            System.out.print("资产名称:[" + asset.getName());
            System.out.print("],资产编码:[" + asset.getCode());
            System.out.print("],规格型号:[" + asset.getSpec());
            System.out.print("],资产类别:[" + asset.getAssetCategoryName());
            System.out.print("],购入时间:[" + (asset.getPurchaseDate() != null ? DateUtil.getExcelDate(asset.getPurchaseDate()) : null));
            System.out.print("],资产管理员:[" + asset.getManagerName());
            System.out.print("],使用部门:[" + asset.getDepartmentName());
            System.out.print("],使用/维护人:[" + asset.getUserName());
            System.out.print("],使用年限:[" + asset.getUsePeriod());
            System.out.print("],资产价值:[" + asset.getPurchasePrice());
            System.out.print("],折旧方法:[" + asset.getDepreciationMethodName());
            System.out.println("],备注:[" + asset.getRemark() + "]");
        }

        try {
            if(in != null){
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testWriter() {
        List<ExcelSheetVo> sheetVos = new ArrayList<ExcelSheetVo>();
        ExcelSheetVo sheetVo = new ExcelSheetVo();
        sheetVo.setSheetName("固定资产导出测试表");

        List<ExcelHeaderVo> excelHeaderVos = new ArrayList<ExcelHeaderVo>();
        excelHeaderVos.add(new ExcelHeaderVo(0,0,"序号"));
        excelHeaderVos.add(new ExcelHeaderVo(0,1,"资产名称"));
        excelHeaderVos.add(new ExcelHeaderVo(0,2,"资产编码"));
        excelHeaderVos.add(new ExcelHeaderVo(0,3,"规格型号"));
        excelHeaderVos.add(new ExcelHeaderVo(0,4,"资产类别"));
        excelHeaderVos.add(new ExcelHeaderVo(0,5,"购入时间"));
        excelHeaderVos.add(new ExcelHeaderVo(0,6,"资产管理员"));
        excelHeaderVos.add(new ExcelHeaderVo(0,7,"使用部门"));
        excelHeaderVos.add(new ExcelHeaderVo(0,8,"使用/维护人"));
        excelHeaderVos.add(new ExcelHeaderVo(0,9,"使用年限"));
        excelHeaderVos.add(new ExcelHeaderVo(0,10,"资产价值"));
        excelHeaderVos.add(new ExcelHeaderVo(0,11,"折旧方法"));
        excelHeaderVos.add(new ExcelHeaderVo(0,12,"备注"));
        sheetVo.setExcelHeaderVos(excelHeaderVos);

        ExcelContentVo excelContentVo = new ExcelContentVo();
        List<List<String>> lists = generate();
        excelContentVo.setStartRow(1);
        excelContentVo.setStartColumn(0);
        excelContentVo.setValues(lists);
        sheetVo.setExcelContentVo(excelContentVo);
        sheetVos.add(sheetVo);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("固定资产导出数据.xls");
            ExcelWriter.Writer(sheetVos, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<List<String>> generate(){
        List<List<String>> lists = new ArrayList<List<String>>();
        for (int row = 0; row < 10; row++){
            List<String> strings = new ArrayList<String>();
            strings.add(Integer.toString(row+1));
            strings.add("我的电脑" + (row+1));
            strings.add("pc-00" + (row+1));
            strings.add("macbook pro");
            strings.add("办公用品");
            strings.add("2017-01-01");
            strings.add("DYP");
            strings.add("开发部");
            strings.add("PYD");
            strings.add("10");
            strings.add("10000");
            strings.add("平均年限法");
            strings.add("这是一个备注啊");
            lists.add(strings);
        }
        return lists;
    }

    private void testReplace(){
        List<ExcelSheetVo> sheetVos = new ArrayList<ExcelSheetVo>();
        for(int i = 0; i < 3; i++){
            ExcelSheetVo sheetVo = new ExcelSheetVo();
            sheetVo.setSheetName("固定资产导出测试表(" + i + ")");

            List<ExcelHeaderVo> excelHeaderVos = new ArrayList<ExcelHeaderVo>();
            sheetVo.setExcelHeaderVos(excelHeaderVos);

            ExcelContentVo excelContentVo = new ExcelContentVo();
            List<List<String>> lists = generate();
            excelContentVo.setStartRow(2);
            excelContentVo.setStartColumn(0);
            excelContentVo.setValues(lists);
            sheetVo.setExcelContentVo(excelContentVo);
            sheetVos.add(sheetVo);
        }

        InputStream in = this.getClass().getResourceAsStream("/固定资产导入模板.xls");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("固定资产导出数据.xls");
            ExcelReplace.replace(sheetVos, in, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null){
                    in.close();
                }
                if (fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
