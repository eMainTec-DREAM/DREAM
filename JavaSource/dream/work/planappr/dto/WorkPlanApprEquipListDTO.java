package dream.work.planappr.dto;

import common.bean.BaseDTO;

/**
 * ÀÛ¾÷°èÈ¹½ÂÀÎ-ÀÛ¾÷°èÈ¹ DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 */
public class WorkPlanApprEquipListDTO extends BaseDTO
{
    /** ¼³ºñ ID */
    private String equipId       = "";

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
}
