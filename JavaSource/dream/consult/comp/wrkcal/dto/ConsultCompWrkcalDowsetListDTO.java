package dream.consult.comp.wrkcal.dto;

import common.bean.BaseDTO;

/**
 * 휴무요일 설정 - 상세 DTO
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class ConsultCompWrkcalDowsetListDTO extends BaseDTO
{
	/** 휴무요일ID */
	private String wrkcalDowsetId 		= "";
	
	public String getWrkcalDowsetId() {
		return wrkcalDowsetId;
	}
	public void setWrkcalDowsetId(String wrkcalDowsetId) {
		this.wrkcalDowsetId = wrkcalDowsetId;
	}
	
}
