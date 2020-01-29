package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * ���� �������� ��� dto
 * @author  kim21017
 * @version $Id: MaEqMstrPartListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrMoldModHistListDTO extends BaseDTO
{
	/** �������� id */
	private String eqMoldModHistId 	= "";

	public String getEqMoldModHistId() {
		return eqMoldModHistId;
	}

	public void setEqMoldModHistId(String eqMoldModHistId) {
		this.eqMoldModHistId = eqMoldModHistId;
		super.setAuditKey(eqMoldModHistId);
	}
	
	
}