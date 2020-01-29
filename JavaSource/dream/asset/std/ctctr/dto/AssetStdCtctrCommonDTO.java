package dream.asset.std.ctctr.dto;

import common.bean.BaseDTO;

/**
 * CostCenter ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class AssetStdCtctrCommonDTO extends BaseDTO
{
	/** CostCenterId */
	private String ctctrId                   = "";
	/** ���� CostCenter�ڵ� */
	private String filterCtctrNo            	= "";
	/** ���� CostCenter�� */
	private String filterDescription 		= "";
	/** ��뿩�� */
	private String filterIsUse              = "";
	
	/** ����-���� ID */
    private String filterPlantId         	= "";
    /** ����-���� DESC */
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
