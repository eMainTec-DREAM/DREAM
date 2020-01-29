package dream.ass.asset.dto;

import common.bean.BaseDTO;

/**
 * AssAsset Page - ���� DTO
 * @author youngjoo38
 * @version $Id: AssAssetCommonDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class AssAssetCommonDTO extends BaseDTO
{
    /**Key ��������ID */ 
    private String eqasslistId                  = "";
    
    /**Filter �򰡽�������*/
    private String filterAssStartDate           = "";    
    /**Filter ����������*/
    private String filterAssEndDate             = "";    
    /**Filter ����ID*/
    private String filterEqasslistStatusId      = "";    
    /**Filter ����Desc*/
    private String filterEqasslistStatusDesc    = "";    
    /**Filter ����ID*/
    private String filterEquipId                = "";    
    /**Filter �����*/
    private String filterEquipDesc              = "";    
    /**Filter ������ġID */
    private String filterEqLocId                = "";
    /**Filter ������ġ�� */ 
    private String filterEqLocDesc              = "";
    /**Filter �����ID */ 
    private String filterEmpId                  = "";
    /**Filter ����ڸ� */ 
    private String filterEmpName                = "";
    /**Filter �򰡵��ID */ 
    private String filterEqGradeId              = "";
    /**Filter �򰡵��DESC */ 
    private String filterEqGradeDesc            = "";
    /**Filter �����ȣ*/
    private String filterEquipNo                = "";    
    /** Filter ������ */
    private String assScore                     = "";
    /** Filter �ε�ȣ�� */
    private String conOperDesc                  = "";
    /** Filter �ε�ȣ */
    private String conOper                      = "";    
    /**Filter ��������ID*/
    private String filterParEquipId             = "";    
    /**Filter ���������*/
    private String filterParEquipDesc           = "";
    /**Filter ����������ġID*/
    private String filterParEqLocId             = "";    
    /**Filter ����������ġ��*/
    private String filterParEqLocDesc           = "";
    /**Filter ������μ�ID*/
    private String filterParUsageDeptId         = "";    
    /**Filter ������μ���*/
    private String filterParUsageDeptDesc       = "";
    
    /** EQCTG_TYPE */
    private String eqCtgType			        = "";    
    
    /** ���� */
	private String filterPlantId 				= "";
	private String filterPlantDesc 				= "";
	
    /**Filter �򰡵��ID */ 
    private String filterAssTypeId              = "";
    /**Filter �򰡵��DESC */ 
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
