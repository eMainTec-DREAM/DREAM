package dream.work.let.permit.dto;

import common.bean.BaseDTO;

/**
 * �����۾��㰡�� �۾����� - �����׸� ��� DTO
 * @author syyang
 * @version $Id: WorkLetPermitPointListDTO.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 *
 */
public class WorkLetPermitPointListDTO extends BaseDTO
{
	/** �����۾� ǥ�������׸� ID (key) */
	private String woLetListPointId  = "";
	

	public String getWoLetListPointId() {
		return woLetListPointId;
	}

	public void setWoLetListPointId(String woLetListPointId) {
		this.woLetListPointId = woLetListPointId;
		super.setAuditKey(woLetListPointId);
	}

}
