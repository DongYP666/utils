package com.dongyp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (C), 2014-2015, 深圳云集智造系统技术有限公司
 *
 * @Title:
 * @Description:
 * @Author by dongyp
 * @date on 2017/12/5
 */
public class YearMonthVo implements Serializable {
    private int yearMonth;
    private Date monthBeginDate;
    private Date monthEndDate;

    public YearMonthVo(int yearMonth, Date monthBeginDate, Date monthEndDate) {
        this.yearMonth = yearMonth;
        this.monthBeginDate = monthBeginDate;
        this.monthEndDate = monthEndDate;
    }

    public int getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(int yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Date getMonthBeginDate() {
        return monthBeginDate;
    }

    public void setMonthBeginDate(Date monthBeginDate) {
        this.monthBeginDate = monthBeginDate;
    }

    public Date getMonthEndDate() {
        return monthEndDate;
    }

    public void setMonthEndDate(Date monthEndDate) {
        this.monthEndDate = monthEndDate;
    }
}
