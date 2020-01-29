package dream.main.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * Inspection Chart DTO
 * @author  pochul2423
 * @version $Id: InspChartDTO.java,v 1.3 2015/01/09 00:16:39 pochul2423 Exp $
 * @since   1.0
 */
public class InspChartDTO extends BaseDTO
{
    /** 일요일 계획*/
    private String sundayPlan = "";
    /** 월요일 계획*/
    private String mondayPlan = "";
    /** 화요일 계획*/
    private String tuesdayPlan = "";
    /** 수요일 계획*/
    private String wednesdayPlan = "";
    /** 목요일 계획*/
    private String thursdayPlan = "";
    /** 금요일 계획*/
    private String fridayPlan = "";
    /** 토요일 계획*/
    private String saturdayPlan = "";
    
    /** 일요일 실적*/
    private String sundayResult = "";
    /** 월요일 실적*/
    private String mondayResult = "";
    /** 화요일 실적*/
    private String tuesdayResult = "";
    /** 수요일 실적*/
    private String wednesdayResult = "";
    /** 목요일 실적*/
    private String thursdayResult = "";
    /** 금요일 실적*/
    private String fridayResult = "";
    /** 토요일 실적*/
    private String saturdayResult = "";
    
    /** 일요일 */
    private String sunday = "";
    /** 월요일 */
    private String monday = "";
    /** 화요일 */
    private String tuesday = "";
    /** 수요일 */
    private String wednesday = "";
    /** 목요일 */
    private String thursday = "";
    /** 금요일 */
    private String friday = "";
    /** 토요일 */
    private String saturday = "";
    
    /** 계획 토탈*/
    private String totalPlan = "";
    /** 실적 토탈*/
    private String totalResult = "";
    
    
    public String getSunday()
    {
        return sunday;
    }
    public void setSunday(String sunday)
    {
        this.sunday = sunday;
    }
    public String getMonday()
    {
        return monday;
    }
    public void setMonday(String monday)
    {
        this.monday = monday;
    }
    public String getTuesday()
    {
        return tuesday;
    }
    public void setTuesday(String tuesday)
    {
        this.tuesday = tuesday;
    }
    public String getWednesday()
    {
        return wednesday;
    }
    public void setWednesday(String wednesday)
    {
        this.wednesday = wednesday;
    }
    public String getThursday()
    {
        return thursday;
    }
    public void setThursday(String thursday)
    {
        this.thursday = thursday;
    }
    public String getFriday()
    {
        return friday;
    }
    public void setFriday(String friday)
    {
        this.friday = friday;
    }
    public String getSaturday()
    {
        return saturday;
    }
    public void setSaturday(String saturday)
    {
        this.saturday = saturday;
    }
    public String getTotalPlan()
    {
        return totalPlan;
    }
    public void setTotalPlan(String totalPlan)
    {
        this.totalPlan = CommonUtil.convertMoney(totalPlan);
    }
    
    public String getTotalResult()
    {
        return totalResult;
    }
    public void setTotalResult(String totalResult)
    {
        this.totalResult = CommonUtil.convertMoney(totalResult);
    }
    public String getSundayPlan()
    {
        return sundayPlan;
    }
    public void setSundayPlan(String sundayPlan)
    {
        this.sundayPlan = sundayPlan;
    }
    public String getMondayPlan()
    {
        return mondayPlan;
    }
    public void setMondayPlan(String mondayPlan)
    {
        this.mondayPlan = mondayPlan;
    }
    public String getTuesdayPlan()
    {
        return tuesdayPlan;
    }
    public void setTuesdayPlan(String tuesdayPlan)
    {
        this.tuesdayPlan = tuesdayPlan;
    }
    public String getWednesdayPlan()
    {
        return wednesdayPlan;
    }
    public void setWednesdayPlan(String wednesdayPlan)
    {
        this.wednesdayPlan = wednesdayPlan;
    }
    public String getThursdayPlan()
    {
        return thursdayPlan;
    }
    public void setThursdayPlan(String thursdayPlan)
    {
        this.thursdayPlan = thursdayPlan;
    }
    public String getFridayPlan()
    {
        return fridayPlan;
    }
    public void setFridayPlan(String fridayPlan)
    {
        this.fridayPlan = fridayPlan;
    }
    public String getSaturdayPlan()
    {
        return saturdayPlan;
    }
    public void setSaturdayPlan(String saturdayPlan)
    {
        this.saturdayPlan = saturdayPlan;
    }
    public String getSundayResult()
    {
        return sundayResult;
    }
    public void setSundayResult(String sundayResult)
    {
        this.sundayResult = sundayResult;
    }
    public String getMondayResult()
    {
        return mondayResult;
    }
    public void setMondayResult(String mondayResult)
    {
        this.mondayResult = mondayResult;
    }
    public String getTuesdayResult()
    {
        return tuesdayResult;
    }
    public void setTuesdayResult(String tuesdayResult)
    {
        this.tuesdayResult = tuesdayResult;
    }
    public String getWednesdayResult()
    {
        return wednesdayResult;
    }
    public void setWednesdayResult(String wednesdayResult)
    {
        this.wednesdayResult = wednesdayResult;
    }
    public String getThursdayResult()
    {
        return thursdayResult;
    }
    public void setThursdayResult(String thursdayResult)
    {
        this.thursdayResult = thursdayResult;
    }
    public String getFridayResult()
    {
        return fridayResult;
    }
    public void setFridayResult(String fridayResult)
    {
        this.fridayResult = fridayResult;
    }
    public String getSaturdayResult()
    {
        return saturdayResult;
    }
    public void setSaturdayResult(String saturdayResult)
    {
        this.saturdayResult = saturdayResult;
    }
}