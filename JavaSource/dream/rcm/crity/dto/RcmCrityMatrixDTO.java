package dream.rcm.crity.dto;

import common.bean.BaseDTO;
/**
 * Criticality Matrix Page - ���� DTO
 * @author kim21017
 * @version $Id: RcmCrityMatrixDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class RcmCrityMatrixDTO extends BaseDTO
{
	/**Criticality ����Ʈ ID*/
	private String crityListId 				= "";
	
	public String getCrityListId() {
		return crityListId;
	}
	public void setCrityListId(String crityListId) {
		this.crityListId = crityListId;
	}
	
}
