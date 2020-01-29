package dream.asset.std.ctctr.dto;

import common.bean.BaseDTO;

/**
 * CostCenter - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class AssetStdCtctrDetailDTO extends BaseDTO
{
	/** CostCenterID */
	private String ctctrId 			= "";
	/** CostCenter코드 */
	private String ctctrNo 			= "";
	/** CostCenter명 */
	private String description 		= "";
	/** 사용여부 ID */
	private String isUseId 			= "";
	/** 사용여부 */
	private String isUse 			= "";
	/** 비고 */
	private String remark           = "";
	/** 공장 */
	private String plant            = "";
    /** 공장명 */
    private String plantDesc        = "";
    /** 부서 ID */
    private String deptId           = "";
    /** 부서 DESC */
    private String deptDesc         = "";
    /** 사용창고 ID */
    private String wcodeId			= "";
    /** 사용창고명 */
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
