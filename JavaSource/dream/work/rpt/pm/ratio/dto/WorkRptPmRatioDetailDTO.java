package dream.work.rpt.pm.ratio.dto;

import common.bean.BaseDTO;

/**
 * ��ȹ������(��ġ) �� dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptPmRatioDetailDTO extends BaseDTO
{
    /** ��ġ */
    private String eqLocId    = "";
    /** ��ġ desc */
    private String eqLocDesc    = "";
    /** ������ */
    private String startDate    = "";
    /** ������ */
    private String endDate    = "";
    /** chart dataset */
    private String chartDataset = "";
    
    public String getChartDataset()
    {
        return chartDataset;
    }
    public void setChartDataset(String chartDataset)
    {
        this.chartDataset = chartDataset;
    }
    public String getEqLocId()
    {
        return eqLocId;
    }
    public void setEqLocId(String eqLocId)
    {
        this.eqLocId = eqLocId;
    }
    public String getEqLocDesc()
    {
        return eqLocDesc;
    }
    public void setEqLocDesc(String eqLocDesc)
    {
        this.eqLocDesc = eqLocDesc;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
}