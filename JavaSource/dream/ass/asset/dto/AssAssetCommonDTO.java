package dream.ass.asset.dto;

import common.bean.BaseDTO;

/**
 * AssAsset Page - 공통 DTO
 * @author youngjoo38
 * @version $Id: AssAssetCommonDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class AssAssetCommonDTO extends BaseDTO
{
    /**Key 설비등급평가ID */ 
    private String eqasslistId                  = "";
    
    /**Filter 평가시작일자*/
    private String filterAssStartDate           = "";    
    /**Filter 평가종료일자*/
    private String filterAssEndDate             = "";    
    /**Filter 상태ID*/
    private String filterEqasslistStatusId      = "";    
    /**Filter 상태Desc*/
    private String filterEqasslistStatusDesc    = "";    
    /**Filter 설비ID*/
    private String filterEquipId                = "";    
    /**Filter 설비명*/
    private String filterEquipDesc              = "";    
    /**Filter 설비위치ID */
    private String filterEqLocId                = "";
    /**Filter 설비위치명 */ 
    private String filterEqLocDesc              = "";
    /**Filter 담당자ID */ 
    private String filterEmpId                  = "";
    /**Filter 담당자명 */ 
    private String filterEmpName                = "";
    /**Filter 평가등급ID */ 
    private String filterEqGradeId              = "";
    /**Filter 평가등급DESC */ 
    private String filterEqGradeDesc            = "";
    /**Filter 설비번호*/
    private String filterEquipNo                = "";    
    /** Filter 평가점수 */
    private String assScore                     = "";
    /** Filter 부등호명 */
    private String conOperDesc                  = "";
    /** Filter 부등호 */
    private String conOper                      = "";    
    /**Filter 상위설비ID*/
    private String filterParEquipId             = "";    
    /**Filter 상위설비명*/
    private String filterParEquipDesc           = "";
    /**Filter 상위설비위치ID*/
    private String filterParEqLocId             = "";    
    /**Filter 상위설비위치명*/
    private String filterParEqLocDesc           = "";
    /**Filter 설비사용부서ID*/
    private String filterParUsageDeptId         = "";    
    /**Filter 설비사용부서명*/
    private String filterParUsageDeptDesc       = "";
    
    /** EQCTG_TYPE */
    private String eqCtgType			        = "";    
    
    /** 공장 */
	private String filterPlantId 				= "";
	private String filterPlantDesc 				= "";
	
    /**Filter 평가등급ID */ 
    private String filterAssTypeId              = "";
    /**Filter 평가등급DESC */ 
    private String filterAssTypeDesc            = "";
    
    public String getFilterAssTypeId() {
		return filterAssTypeId;
	}
	public void setFilterAssTypeId(String filterAssTypeId) {
		this.filterAssTypeId = filterAssTypeId;
	}
	public String getFilterAssTypeDesc() {
		return filterAssTypeDesc;
	}
	public void setFilterAssTypeDesc(String filterAssTypeDesc) {
		this.filterAssTypeDesc = filterAssTypeDesc;
	}
	public String getEqCtgType() {
		return eqCtgType;
	}
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
	public void setEqCtgType(String eqCtgType) {
		this.eqCtgType = eqCtgType;
	}
	public String getFilterParEqLocId() {
		return filterParEqLocId;
	}
	public void setFilterParEqLocId(String filterParEqLocId) {
		this.filterParEqLocId = filterParEqLocId;
	}
	public String getFilterParEqLocDesc() {
		return filterParEqLocDesc;
	}
	public void setFilterParEqLocDesc(String filterParEqLocDesc) {
		this.filterParEqLocDesc = filterParEqLocDesc;
	}
	public String getFilterParUsageDeptId() {
		return filterParUsageDeptId;
	}
	public void setFilterParUsageDeptId(String filterParUsageDeptId) {
		this.filterParUsageDeptId = filterParUsageDeptId;
	}
	public String getFilterParUsageDeptDesc() {
		return filterParUsageDeptDesc;
	}
	public void setFilterParUsageDeptDesc(String filterParUsageDeptDesc) {
		this.filterParUsageDeptDesc = filterParUsageDeptDesc;
	}
	public String getFilterParEquipId() {
		return filterParEquipId;
	}
	public void setFilterParEquipId(String filterParEquipId) {
		this.filterParEquipId = filterParEquipId;
	}
	public String getFilterParEquipDesc() {
		return filterParEquipDesc;
	}
	public void setFilterParEquipDesc(String filterParEquipDesc) {
		this.filterParEquipDesc = filterParEquipDesc;
	}
	public String getAssScore()
    {
        return assScore;
    }
    public void setAssScore(String assScore)
    {
        this.assScore = assScore;
    }
    public String getConOperDesc()
    {
        return conOperDesc;
    }
    public void setConOperDesc(String conOperDesc)
    {
        this.conOperDesc = conOperDesc;
    }
    public String getConOper()
    {
        return conOper;
    }
    public void setConOper(String conOper)
    {
        this.conOper = conOper;
    }
    public String getFilterEquipNo()
    {
        return filterEquipNo;
    }
    public void setFilterEquipNo(String filterEquipNo)
    {
        this.filterEquipNo = filterEquipNo;
    }
    public String getFilterEqGradeId()
    {
        return filterEqGradeId;
    }
    public void setFilterEqGradeId(String filterEqGradeId)
    {
        this.filterEqGradeId = filterEqGradeId;
    }
    public String getFilterEqGradeDesc()
    {
        return filterEqGradeDesc;
    }
    public void setFilterEqGradeDesc(String filterEqGradeDesc)
    {
        this.filterEqGradeDesc = filterEqGradeDesc;
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
    public String getFilterAssStartDate()
    {
        return filterAssStartDate;
    }
    public void setFilterAssStartDate(String filterAssStartDate)
    {
        this.filterAssStartDate = filterAssStartDate;
    }
    public String getFilterAssEndDate()
    {
        return filterAssEndDate;
    }
    public void setFilterAssEndDate(String filterAssEndDate)
    {
        this.filterAssEndDate = filterAssEndDate;
    }
    public String getFilterEqasslistStatusId()
    {
        return filterEqasslistStatusId;
    }
    public void setFilterEqasslistStatusId(String filterEqasslistStatusId)
    {
        this.filterEqasslistStatusId = filterEqasslistStatusId;
    }
    public String getFilterEqasslistStatusDesc()
    {
        return filterEqasslistStatusDesc;
    }
    public void setFilterEqasslistStatusDesc(String filterEqasslistStatusDesc)
    {
        this.filterEqasslistStatusDesc = filterEqasslistStatusDesc;
    }
    public String getFilterEquipId()
    {
        return filterEquipId;
    }
    public void setFilterEquipId(String filterEquipId)
    {
        this.filterEquipId = filterEquipId;
    }
    public String getFilterEquipDesc()
    {
        return filterEquipDesc;
    }
    public void setFilterEquipDesc(String filterEquipDesc)
    {
        this.filterEquipDesc = filterEquipDesc;
    }
    public String getFilterEqLocId()
    {
        return filterEqLocId;
    }
    public void setFilterEqLocId(String filterEqLocId)
    {
        this.filterEqLocId = filterEqLocId;
    }
    public String getFilterEqLocDesc()
    {
        return filterEqLocDesc;
    }
    public void setFilterEqLocDesc(String filterEqLocDesc)
    {
        this.filterEqLocDesc = filterEqLocDesc;
    }
    public String getFilterEmpId()
    {
        return filterEmpId;
    }
    public void setFilterEmpId(String filterEmpId)
    {
        this.filterEmpId = filterEmpId;
    }
    public String getFilterEmpName()
    {
        return filterEmpName;
    }
    public void setFilterEmpName(String filterEmpName)
    {
        this.filterEmpName = filterEmpName;
    }

    
    
    
}
