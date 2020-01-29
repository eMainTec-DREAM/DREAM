package dream.main.dto;

import common.bean.BaseDTO;

/**
 * Work Notice �Ǽ� DTO
 * @author  javaworker
 * @version $Id: WorkNoticeCntDTO.java,v 1.4 2014/01/20 07:23:58 javaworker Exp $
 * @since   1.0
 */
public class WorkNoticeCntDTO extends BaseDTO
{
    /** ���籸���԰� �Ǽ� */
    private String purRecCount = "0";
    /** ��������û �Ǽ� */
    private String ptIssCount = "0";
    /** ���̰� ���� �Ǽ� */
    private String holdPlanCount = "0";
    /** ���̰� �۾� �Ǽ� */
    private String holdPerformCount = "0";
    /** ��ü�۾������� �Ǽ� */
    private String totalPlanCount = "0";
    /** ��ü�۾������� �Ǽ� */
    private String totalWorkCount = "0";
    /** ��ü�˻� �Ǽ� */
    private String inspBodyCount = "0";
    /** MOC��� */
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