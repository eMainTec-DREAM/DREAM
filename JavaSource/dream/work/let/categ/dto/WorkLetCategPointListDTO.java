package dream.work.let.categ.dto;

import common.bean.BaseDTO;

/**
 * 안전작업유형 점검항목 List DTO - LIST DTO
 * @author euna0207
 * @version $Id: WorkLetCategPointDetailDTO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class WorkLetCategPointListDTO extends BaseDTO
{
	/** 안전작업 표준점검항목 id (key) */
	private String woLetCtgPointId  = "";
	
	/** 안전작업유형 id */
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
