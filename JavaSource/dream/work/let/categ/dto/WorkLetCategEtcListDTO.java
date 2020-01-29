package dream.work.let.categ.dto;

import common.bean.BaseDTO;

/**
 * �����۾����� - ���ϻ��� List DTO - List DTO
 * @author euna0207
 * @version $Id: WorkLetCategPointDetailDTO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class WorkLetCategEtcListDTO extends BaseDTO
{
	/** �����۾� ���ϻ��� id (key) */
	private String woLetCtgEtcId = 		"";
	
	/** �����۾����� id */
	private String woLetCtgId = 		"";

	public String getWoLetCtgEtcId() {
		return woLetCtgEtcId;
	}

	public void setWoLetCtgEtcId(String woLetCtgEtcId) {
		this.woLetCtgEtcId = woLetCtgEtcId;
		super.setAuditKey(woLetCtgEtcId);
	}

	public String getWoLetCtgId() {
		return woLetCtgId;
	}

	public void setWoLetCtgId(String woLetCtgId) {
		this.woLetCtgId = woLetCtgId;
	}
	
}
