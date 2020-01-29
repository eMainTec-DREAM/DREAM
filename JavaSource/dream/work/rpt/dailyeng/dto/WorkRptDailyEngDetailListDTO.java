package dream.work.rpt.dailyeng.dto;

import common.bean.BaseDTO;

/**
 * 에너지사용량(일별) 상세 dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptDailyEngDetailListDTO extends BaseDTO
{
    /** 공장 ID */
    private String plantId  		= "";
    /** 공장 Desc */
    private String plantDesc		= "";
    /** 위치 ID */
    private String eqLocId   		= "";
    /** 위치 Desc */
    private String eqLocDesc   		= "";
	/** 사용부서 ID */
    private String usageDeptId   	= "";
    /** 사용부서 Desc */
    private String usageDeptDesc   	= "";
    /** 종류 ID */
    private String eqCtgId   		= "";
    /** 종류 Desc */
    private String eqCtgDesc   		= "";
	/** 설비 ID */
    private String equipId  		= "";
    /** 설비 Desc */
    private String equipDesc  		= "";
	/** 사용량 */
	private String usageAmt 		= "";
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
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getUsageDeptId() {
		return usageDeptId;
	}
	public void setUsageDeptId(String usageDeptId) {
		this.usageDeptId = usageDeptId;
	}
	public String getUsageDeptDesc() {
		return usageDeptDesc;
	}
	public void setUsageDeptDesc(String usageDeptDesc) {
		this.usageDeptDesc = usageDeptDesc;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getUsageAmt() {
		return usageAmt;
	}
	public void setUsageAmt(String usageAmt) {
		this.usageAmt = usageAmt;
	}
}