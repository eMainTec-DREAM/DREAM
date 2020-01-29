package dream.part.iss.wo.dto;

import common.bean.BaseDTO;

/**
 * 자재출고WO - 상세 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class PartIssWoPartListDTO extends BaseDTO
{
    /** 자재출고WO ID */
    private String woPartId          = "";
    /** 출고결과 ID */
    private String ptIssListId       = "";
    /** multi WO ID */
    private String multiWkOrId       = "";
    /** multi WO Desc */
    private String multiWkOrDesc     = "";
    /** multi WO Part ID */
    private String multiWoPartId     = "";
    /** multi WO Part Desc */
    private String multiWoPartDesc   = "";
    
    public String getMultiWkOrId()
    {
        return multiWkOrId;
    }
    public void setMultiWkOrId(String multiWkOrId)
    {
        this.multiWkOrId = multiWkOrId;
    }
    public String getMultiWkOrDesc()
    {
        return multiWkOrDesc;
    }
    public void setMultiWkOrDesc(String multiWkOrDesc)
    {
        this.multiWkOrDesc = multiWkOrDesc;
    }
    public String getMultiWoPartId()
    {
        return multiWoPartId;
    }
    public void setMultiWoPartId(String multiWoPartId)
    {
        this.multiWoPartId = multiWoPartId;
    }
    public String getMultiWoPartDesc()
    {
        return multiWoPartDesc;
    }
    public void setMultiWoPartDesc(String multiWoPartDesc)
    {
        this.multiWoPartDesc = multiWoPartDesc;
    }
    public String getWoPartId()
    {
        return woPartId;
    }
    public void setWoPartId(String woPartId)
    {
        this.woPartId = woPartId;
    }
    public String getPtIssListId()
    {
        return ptIssListId;
    }
    public void setPtIssListId(String ptIssListId)
    {
        this.ptIssListId = ptIssListId;
    }
}
