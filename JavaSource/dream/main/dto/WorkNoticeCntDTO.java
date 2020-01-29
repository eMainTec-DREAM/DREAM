package dream.main.dto;

import common.bean.BaseDTO;

/**
 * Work Notice 건수 DTO
 * @author  javaworker
 * @version $Id: WorkNoticeCntDTO.java,v 1.4 2014/01/20 07:23:58 javaworker Exp $
 * @since   1.0
 */
public class WorkNoticeCntDTO extends BaseDTO
{
    /** 자재구매입고 건수 */
    private String purRecCount = "0";
    /** 자재출고요청 건수 */
    private String ptIssCount = "0";
    /** 장기미결 설계 건수 */
    private String holdPlanCount = "0";
    /** 장기미결 작업 건수 */
    private String holdPerformCount = "0";
    /** 전체작업설계대상 건수 */
    private String totalPlanCount = "0";
    /** 전체작업수행대상 건수 */
    private String totalWorkCount = "0";
    /** 선체검사 건수 */
    private String inspBodyCount = "0";
    /** MOC대상 */
    private String openMocCount = "0";
    
    public String getPtIssCount()
    {
        return ptIssCount;
    }
    public void setPtIssCount(String ptIssCount)
    {
        this.ptIssCount = ptIssCount;
    }
    public String getHoldPlanCount()
    {
        return holdPlanCount;
    }
    public void setHoldPlanCount(String holdPlanCount)
    {
        this.holdPlanCount = holdPlanCount;
    }
    public String getHoldPerformCount()
    {
        return holdPerformCount;
    }
    public void setHoldPerformCount(String holdPerformCount)
    {
        this.holdPerformCount = holdPerformCount;
    }
    public String getTotalPlanCount()
    {
        return totalPlanCount;
    }
    public void setTotalPlanCount(String totalPlanCount)
    {
        this.totalPlanCount = totalPlanCount;
    }
    public String getTotalWorkCount()
    {
        return totalWorkCount;
    }
    public void setTotalWorkCount(String totalWorkCount)
    {
        this.totalWorkCount = totalWorkCount;
    }
    public String getInspBodyCount()
    {
        return inspBodyCount;
    }
    public void setInspBodyCount(String inspBodyCount)
    {
        this.inspBodyCount = inspBodyCount;
    }
    public String getPurRecCount()
    {
        return purRecCount;
    }
    public void setPurRecCount(String purRecCount)
    {
        this.purRecCount = purRecCount;
    }
    public String getOpenMocCount()
    {
        return openMocCount;
    }
    public void setOpenMocCount(String openMocCount)
    {
        this.openMocCount = openMocCount;
    }
}