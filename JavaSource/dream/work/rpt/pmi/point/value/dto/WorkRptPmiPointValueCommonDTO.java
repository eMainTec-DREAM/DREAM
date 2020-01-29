package dream.work.rpt.pmi.point.value.dto;

import common.bean.BaseDTO;

/**
 * 정기점검항목결과 - 목록 DTO
 * @author  nhkim8548
 * @version $Id: WorkRptPmiPointValueCommonDTO.java, v1.0 2019/07/10 10:35:45 nhkim8548 Exp $
 * @since   1.0
 */
public class WorkRptPmiPointValueCommonDTO extends BaseDTO
{
	/** 점검(시작)일자 */
	private String filterInsStartDate          = "";
	/** 점검(종료)일자 */
	private String filterInsEndDate            = "";
	/** 공장 ID */
	private String filterPlantId               = "";
	/** 공장 DESC */
	private String filterPlantDesc             = "";
	/** 작업부서 ID */
	private String filterWorkDeptId            = "";
	/** 작업부서 DESC */
	private String filterWorkDeptDesc          = "";
	/** 작업그룹 ID */
	private String filterWkCtrId               = "";
	/** 작업그룹 DESC */
	private String filterWkCtrDesc             = "";
	/** 작업담당자 ID */
	private String filterWoManagerId           = "";
	/** 작업담당자 DESC */
	private String filterWoManagerDesc         = "";
	/** 설비 ID */
	private String filterEquipId               = "";
	/** 설비 DESC */
	private String filterEquipName             = "";
	/** 설비위치 ID */
	private String filterEqLocId               = "";
	/** 설비위치 DESC */
	private String filterEqLocDesc             = "";
	/** 설비종류 ID */
	private String filterEqCtgId               = "";
	/** 설비종류 DESC */
	private String filterEqCtgDesc             = "";
	/** 예방작업번호 */
	private String filterPmInsListNo           = "";
	/** 점검결과 ID */
	private String filterRsltStatusId          = "";
	/** 점검결과 DESC */
	private String filterRsltStatusDesc        = "";
    
	public String getFilterInsStartDate()
    {
        return filterInsStartDate;
    }
    public void setFilterInsStartDate(String filterInsStartDate)
    {
        this.filterInsStartDate = filterInsStartDate;
    }
    public String getFilterInsEndDate()
    {
        return filterInsEndDate;
    }
    public void setFilterInsEndDate(String filterInsEndDate)
    {
        this.filterInsEndDate = filterInsEndDate;
    }
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getFilterWorkDeptId()
    {
        return filterWorkDeptId;
    }
    public void setFilterWorkDeptId(String filterWorkDeptId)
    {
        this.filterWorkDeptId = filterWorkDeptId;
    }
    public String getFilterWorkDeptDesc()
    {
        return filterWorkDeptDesc;
    }
    public void setFilterWorkDeptDesc(String filterWorkDeptDesc)
    {
        this.filterWorkDeptDesc = filterWorkDeptDesc;
    }
    public String getFilterWkCtrId()
    {
        return filterWkCtrId;
    }
    public void setFilterWkCtrId(String filterWkCtrId)
    {
        this.filterWkCtrId = filterWkCtrId;
    }
    public String getFilterWkCtrDesc()
    {
        return filterWkCtrDesc;
    }
    public void setFilterWkCtrDesc(String filterWkCtrDesc)
    {
        this.filterWkCtrDesc = filterWkCtrDesc;
    }
    public String getFilterWoManagerId()
    {
        return filterWoManagerId;
    }
    public void setFilterWoManagerId(String filterWoManagerId)
    {
        this.filterWoManagerId = filterWoManagerId;
    }
    public String getFilterWoManagerDesc()
    {
        return filterWoManagerDesc;
    }
    public void setFilterWoManagerDesc(String filterWoManagerDesc)
    {
        this.filterWoManagerDesc = filterWoManagerDesc;
    }
    public String getFilterEquipId()
    {
        return filterEquipId;
    }
    public void setFilterEquipId(String filterEquipId)
    {
        this.filterEquipId = filterEquipId;
    }
    public String getFilterEquipName()
    {
        return filterEquipName;
    }
    public void setFilterEquipName(String filterEquipName)
    {
        this.filterEquipName = filterEquipName;
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
    public String getFilterEqCtgId()
    {
        return filterEqCtgId;
    }
    public void setFilterEqCtgId(String filterEqCtgId)
    {
        this.filterEqCtgId = filterEqCtgId;
    }
    public String getFilterEqCtgDesc()
    {
        return filterEqCtgDesc;
    }
    public void setFilterEqCtgDesc(String filterEqCtgDesc)
    {
        this.filterEqCtgDesc = filterEqCtgDesc;
    }
    public String getFilterPmInsListNo()
    {
        return filterPmInsListNo;
    }
    public void setFilterPmInsListNo(String filterPmInsListNo)
    {
        this.filterPmInsListNo = filterPmInsListNo;
    }
    public String getFilterRsltStatusId()
    {
        return filterRsltStatusId;
    }
    public void setFilterRsltStatusId(String filterRsltStatusId)
    {
        this.filterRsltStatusId = filterRsltStatusId;
    }
    public String getFilterRsltStatusDesc()
    {
        return filterRsltStatusDesc;
    }
    public void setFilterRsltStatusDesc(String filterRsltStatusDesc)
    {
        this.filterRsltStatusDesc = filterRsltStatusDesc;
    }
}