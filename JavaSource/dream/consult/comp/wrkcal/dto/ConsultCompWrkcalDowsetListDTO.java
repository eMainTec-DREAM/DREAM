package dream.consult.comp.wrkcal.dto;

import common.bean.BaseDTO;

/**
 * �޹����� ���� - �� DTO
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class ConsultCompWrkcalDowsetListDTO extends BaseDTO
{
	/** �޹�����ID */
	private String wrkcalDowsetId 		= "";
	
	public String getWrkcalDowsetId() {
		return wrkcalDowsetId;
	}
	public void setWrkcalDowsetId(String wrkcalDowsetId) {
		this.wrkcalDowsetId = wrkcalDowsetId;
	}
	
}
