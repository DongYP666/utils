package com.dongyp.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright (C), 2014-2015, 深圳云集智造系统技术有限公司
 *
 * @Title:
 * @Description:
 * @Author by dongyp
 * @date on 2017/12/5
 */
public class YearWeekVo {
    private int yearWeek;
    private Date weekBeginDate;
    private Date weekEndDate;
    private BigDecimal val;

    public YearWeekVo(int yearWeek, Date weekBeginDate, Date weekEndDate) {
        this.yearWeek = yearWeek;
        this.weekBeginDate = weekBeginDate;
        this.weekEndDate = weekEndDate;
    }

    public YearWeekVo(int yearWeek, Date weekBeginDate, Date weekEndDate, BigDecimal val) {
        this.yearWeek = yearWeek;
        this.weekBeginDate = weekBeginDate;
        this.weekEndDate = weekEndDate;
        this.val = val;
    }

    public int getYearWeek() {
        return yearWeek;
    }

    public void setYearWeek(int yearWeek) {
        this.yearWeek = yearWeek;
    }

    public Date getWeekBeginDate() {
        return weekBeginDate;
    }

    public void setWeekBeginDate(Date weekBeginDate) {
        this.weekBeginDate = weekBeginDate;
    }

    public Date getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(Date weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public BigDecimal getVal() {
        return val;
    }

    public void setVal(BigDecimal val) {
        this.val = val;
    }
}
