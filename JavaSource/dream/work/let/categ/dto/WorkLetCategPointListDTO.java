package dream.work.let.categ.dto;

import common.bean.BaseDTO;

/**
 * �����۾����� �����׸� List DTO - LIST DTO
 * @author euna0207
 * @version $Id: WorkLetCategPointDetailDTO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class WorkLetCategPointListDTO extends BaseDTO
{
	/** �����۾� ǥ�������׸� id (key) */
	private String woLetCtgPointId  = "";
	
	/** �����۾����� id */
	private String woLetCtgId 		= "";

	public String getWoLetCtgPointId() {
		return woLetCtgPointId;
	}

	public void setWoLetCtgPointId(String woLetCtgPointId) {
		this.woLetCtgPointId = woLetCtgPointId;
		super.setAuditKey(woLetCtgPointId);
	}

	public String getWoLetCtgId() {
		return woLetCtgId;
	}

	public void setWoLetCtgId(String woLetCtgId) {
		this.woLetCtgId = woLetCtgId;
	}


}
