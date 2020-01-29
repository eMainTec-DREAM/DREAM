package dream.asset.std.ctctr.dto;

import common.bean.BaseDTO;

/**
 * CostCenter 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class AssetStdCtctrCommonDTO extends BaseDTO
{
	/** CostCenterId */
	private String ctctrId                   = "";
	/** 필터 CostCenter코드 */
	private String filterCtctrNo            	= "";
	/** 필터 CostCenter명 */
	private String filterDescription 		= "";
	/** 사용여부 */
	private String filterIsUse              = "";
	
	/** 필터-공장 ID */
    private String filterPlantId         	= "";
    /** 필터-공장 DESC */
    private String filterPlantDesc         	= "";
    
    
    public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getCtctrId()
    {
        return ctctrId;
    }
    public void setCtctrId(String ctctrId)
    {
        this.ctctrId = ctctrId;
        super.setAuditKey(ctctrId);
    }
    public String getFilterCtctrNo()
    {
        return filterCtctrNo;
    }
    public void setFilterCtctrNo(String filterCtctrNo)
    {
        this.filterCtctrNo = filterCtctrNo;
    }
    public String getFilterDescription()
    {
        return filterDescription;
    }
    public void setFilterDescription(String filterDescription)
    {
        this.filterDescription = filterDescription;
    }
    public String getFilterIsUse()
    {
        return filterIsUse;
    }
    public void setFilterIsUse(String filterIsUse)
    {
        this.filterIsUse = filterIsUse;
    }
	
}
