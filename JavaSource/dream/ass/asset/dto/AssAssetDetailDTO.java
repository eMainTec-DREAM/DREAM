package dream.ass.asset.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * AssAsset Page - Detail DTO
 * @author youngjoo38
 * @version $Id: AssAssetDetailDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class AssAssetDetailDTO extends BaseDTO
{
    /**Key 설비등급평가ID */ 
    private String eqasslistId            = "";    
    
    /**설비등급평가No */ 
    private String eqasslistNo            = "";
    /**상태ID*/
    private String eqasslistStatusId      = "";    
    /**상태Desc*/
    private String eqasslistStatusDesc    = "";    
    /**설비위치ID */
    private String eqLocId                = "";
    /**설비위치명 */ 
    private String eqLocDesc              = "";
    /**설비ID*/
    private String equipId                = "";    
    /**설비명*/
    private String equipDesc              = "";    
    /**설비코드*/
    private String equipNo                = "";    
    /**평가등급기준표ID*/
    private String assBaseListId          = "";   
    /**평가등급기준표DESC*/
    private String assBaseListDesc        = "";   
    /**담당자ID */ 
    private String empId                  = "";
    /**담당자명 */ 
    private String empName                = "";
    /**최종평가등급ID */ 
    private String eqGradeId              = "";
    /**최종평가등급DESC */ 
    private String eqGradeDesc            = "";
    /**설비등급평가점수 */ 
    private String evalValue              = "";
    /**평가일자*/
    private String assDate                = "";    
    /**비고*/
    private String remark                 = "";
    private String pmiActionType          = "";
    private String pmiActionTypeDesc      = "";
    
    /**상위설비위치ID */
    private String parEqLocId             = "";
    /**상위설비위치명 */ 
    private String parEqLocDesc           = "";
    /**상위설비ID*/
    private String parEquipId             = "";    
    /**상위설비명*/
    private String parEquipDesc           = "";    
    
    /** 공장 ID */
    private String plantId				  = "";    
    /** 공장명 */
    private String plantDesc			  = "";    
    /** 평가구분 ID */
    private String assTypeId			  = "";    
    /** 평가구분명 */
    private String assTypeDesc			  = "";    
    
    public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getAssTypeId() {
		return assTypeId;
	}
	public void setAssTypeId(String assTypeId) {
		this.assTypeId = assTypeId;
	}
	public String getAssTypeDesc() {
		return assTypeDesc;
	}
	public void setAssTypeDesc(String assTypeDesc) {
		this.assTypeDesc = assTypeDesc;
	}
	public String getEquipNo()
    {
        return equipNo;
    }
    public void setEquipNo(String equipNo)
    {
        this.equipNo = equipNo;
    }
    public String getParEquipId() {
		return parEquipId;
	}
	public void setParEquipId(String parEquipId) {
		this.parEquipId = parEquipId;
	}
	public String getParEquipDesc() {
		return parEquipDesc;
	}
	public void setParEquipDesc(String parEquipDesc) {
		this.parEquipDesc = parEquipDesc;
	}
	public String getParEqLocId() {
		return parEqLocId;
	}
	public void setParEqLocId(String parEqLocId) {
		this.parEqLocId = parEqLocId;
	}
	public String getParEqLocDesc() {
		return parEqLocDesc;
	}
	public void setParEqLocDesc(String parEqLocDesc) {
		this.parEqLocDesc = parEqLocDesc;
	}
	public String getPmiActionType() {
		return pmiActionType;
	}
	public void setPmiActionType(String pmiActionType) {
		this.pmiActionType = pmiActionType;
	}
	public String getPmiActionTypeDesc() {
		return pmiActionTypeDesc;
	}
	public void setPmiActionTypeDesc(String pmiActionTypeDesc) {
		this.pmiActionTypeDesc = pmiActionTypeDesc;
	}
	public String getEqasslistId()
    {
        return eqasslistId;
    }
    public void setEqasslistId(String eqasslistId)
    {
        this.eqasslistId = eqasslistId;
        super.setAuditKey(eqasslistId);
    }
    public String getEqasslistNo()
    {
        return eqasslistNo;
    }
    public void setEqasslistNo(String eqasslistNo)
    {
        this.eqasslistNo = eqasslistNo;
    }
    public String getEqasslistStatusId()
    {
        return eqasslistStatusId;
    }
    public void setEqasslistStatusId(String eqasslistStatusId)
    {
        this.eqasslistStatusId = eqasslistStatusId;
    }
    public String getEqasslistStatusDesc()
    {
        return eqasslistStatusDesc;
    }
    public void setEqasslistStatusDesc(String eqasslistStatusDesc)
    {
        this.eqasslistStatusDesc = eqasslistStatusDesc;
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
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
    }
    public String getAssBaseListId()
    {
        return assBaseListId;
    }
    public void setAssBaseListId(String assBaseListId)
    {
        this.assBaseListId = assBaseListId;
    }
    public String getEmpId()
    {
        return empId;
    }
    public void setEmpId(String empId)
    {
        this.empId = empId;
    }
    public String getEmpName()
    {
        return empName;
    }
    public void setEmpName(String empName)
    {
        this.empName = empName;
    }
    public String getEqGradeId()
    {
        return eqGradeId;
    }
    public void setEqGradeId(String eqGradeId)
    {
        this.eqGradeId = eqGradeId;
    }
    public String getEqGradeDesc()
    {
        return eqGradeDesc;
    }
    public void setEqGradeDesc(String eqGradeDesc)
    {
        this.eqGradeDesc = eqGradeDesc;
    }
    public String getEvalValue()
    {
        return evalValue;
    }
    public void setEvalValue(String evalValue)
    {
        this.evalValue = evalValue;
    }
    public String getAssDate()
    {
        return assDate;
    }
    public void setAssDate(String assDate)
    {
        this.assDate = CommonUtil.convertDate(assDate);
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getAssBaseListDesc()
    {
        return assBaseListDesc;
    }
    public void setAssBaseListDesc(String assBaseListDesc)
    {
        this.assBaseListDesc = assBaseListDesc;
    }    
}
