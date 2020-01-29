package dream.work.rpt.dailyeng.dto;

import common.bean.BaseDTO;

/**
 * ��������뷮(�Ϻ�) �� dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptDailyEngDetailListDTO extends BaseDTO
{
    /** ���� ID */
    private String plantId  		= "";
    /** ���� Desc */
    private String plantDesc		= "";
    /** ��ġ ID */
    private String eqLocId   		= "";
    /** ��ġ Desc */
    private String eqLocDesc   		= "";
	/** ���μ� ID */
    private String usageDeptId   	= "";
    /** ���μ� Desc */
    private String usageDeptDesc   	= "";
    /** ���� ID */
    private String eqCtgId   		= "";
    /** ���� Desc */
    private String eqCtgDesc   		= "";
	/** ���� ID */
    private String equipId  		= "";
    /** ���� Desc */
    private String equipDesc  		= "";
	/** ��뷮 */
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