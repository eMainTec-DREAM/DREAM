package dream.work.let.permit.dto;

import common.bean.BaseDTO;

/**
 * �����۾� - �����۾��㰡�� �۾����� ��� DTO
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public class WorkLetPermitListDTO extends BaseDTO
{
	/** �����۾��㰡�� ���� ID */
	private String woLetListId 	= "";
	
	
	public String getWoLetListId() {
		return woLetListId;
	}
	public void setWoLetListId(String woLetListId) {
		this.woLetListId = woLetListId;
		super.setAuditKey(woLetListId);
	}
	
}