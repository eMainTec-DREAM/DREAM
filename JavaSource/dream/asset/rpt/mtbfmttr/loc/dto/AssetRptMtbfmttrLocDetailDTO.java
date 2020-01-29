package dream.asset.rpt.mtbfmttr.loc.dto;

import common.bean.BaseDTO;

/**
 * MTBF,MTTR(��ġ) �� dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptMtbfmttrLocDetailDTO extends BaseDTO
{
    /** ��ġ */
    private String eqLocId    = "";
    /** ��ġ desc */
    private String eqLocDesc    = "";
    /** ������ */
    private String startDate    = "";
    /** ������ */
    private String endDate    = "";
    
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