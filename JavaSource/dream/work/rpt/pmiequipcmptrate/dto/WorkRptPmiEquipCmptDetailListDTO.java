package dream.work.rpt.pmiequipcmptrate.dto;

import common.bean.BaseDTO;

/**
 * 예방점검 실행 상세 목록 DTO
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 *
 */
public class WorkRptPmiEquipCmptDetailListDTO extends BaseDTO
{
	/** 년월 */ 
    private String yyyymm		= "";
    /** 공장 ID */ 
    private String plantId      = "";
    /** 공장명 */ 
    private String plantDesc    = "";
    
	public String getYyyymm() {
		return yyyymm;
	}
	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
	}
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
    
}
