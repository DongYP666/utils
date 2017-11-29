package com.dongyp.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright (C), 2014-2015, 深圳云集智造系统技术有限公司
 *
 * @Title: 固定资产档案实体类
 * @Description:
 * @Author by DongYP
 * @date on 2017/3/13
 */
public class Asset {
    /* 资产编码 */
    private String code;

    /* 资产名称 */
    private String name;

    /* 资产类型编码 */
    private String assetCategoryCode;

    /* 资产类型名称 */
    private String assetCategoryName;

    /* 折旧方式编码 */
    private String depreciationMethodCode;

    /* 折旧方式名称 */
    private String depreciationMethodName;

    /* 规格型号 */
    private String spec;

    /* 购入日期 */
    private Date purchaseDate;

    /* 使用年限(年) */
    private Integer usePeriod;

    /* 资产价值 */
    private BigDecimal purchasePrice;

    /* 资产管理员编号 */
    private String managerCode;

    /* 资产管理员名称 */
    private String managerName;

    /* 使用部门编码 */
    private String departmentCode;

    /* 使用部门名称 */
    private String departmentName;

    /* 使用人编码 */
    private String userCode;

    /* 使用人名称 */
    private String userName;

    /* 备注 */
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssetCategoryCode() {
        return assetCategoryCode;
    }

    public void setAssetCategoryCode(String assetCategoryCode) {
        this.assetCategoryCode = assetCategoryCode;
    }

    public String getAssetCategoryName() {
        return assetCategoryName;
    }

    public void setAssetCategoryName(String assetCategoryName) {
        this.assetCategoryName = assetCategoryName;
    }

    public String getDepreciationMethodCode() {
        return depreciationMethodCode;
    }

    public void setDepreciationMethodCode(String depreciationMethodCode) {
        this.depreciationMethodCode = depreciationMethodCode;
    }

    public String getDepreciationMethodName() {
        return depreciationMethodName;
    }

    public void setDepreciationMethodName(String depreciationMethodName) {
        this.depreciationMethodName = depreciationMethodName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getUsePeriod() {
        return usePeriod;
    }

    public void setUsePeriod(Integer usePeriod) {
        this.usePeriod = usePeriod;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

