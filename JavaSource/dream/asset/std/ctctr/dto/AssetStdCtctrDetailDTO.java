package dream.asset.std.ctctr.dto;

import common.bean.BaseDTO;

/**
 * CostCenter - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class AssetStdCtctrDetailDTO extends BaseDTO
{
	/** CostCenterID */
	private String ctctrId 			= "";
	/** CostCenter�ڵ� */
	private String ctctrNo 			= "";
	/** CostCenter�� */
	private String description 		= "";
	/** ��뿩�� ID */
	private String isUseId 			= "";
	/** ��뿩�� */
	private String isUse 			= "";
	/** ��� */
	private String remark           = "";
	/** ���� */
	private String plant            = "";
    /** ����� */
    private String plantDesc        = "";
    /** �μ� ID */
    private String deptId           = "";
    /** �μ� DESC */
    private String deptDesc         = "";
    /** ���â�� ID */
    private String wcodeId			= "";
    /** ���â��� */
    private String wcodeDesc		= "";
    
    public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getWcodeDesc() {
		return wcodeDesc;
	}
	public void setWcodeDesc(String wcodeDesc) {
		this.wcodeDesc = wcodeDesc;
	}
	public String getIsUseId() {
		return isUseId;
	}
	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getPlant()
    {
        return plant;
    }
    public void setPlant(String plant)
    {
        this.plant = plant;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
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
    public String getCtctrNo()
    {
        return ctctrNo;
    }
    public void setCtctrNo(String ctctrNo)
    {
        this.ctctrNo = ctctrNo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
	
}
