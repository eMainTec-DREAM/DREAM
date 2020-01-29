package dream.work.rpt.pmiequipcmptrate.dto;

import common.bean.BaseDTO;

/**
 * �������� ���� �� ��� DTO
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 *
 */
public class WorkRptPmiEquipCmptDetailListDTO extends BaseDTO
{
	/** ��� */ 
    private String yyyymm		= "";
    /** ���� ID */ 
    private String plantId      = "";
    /** ����� */ 
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
