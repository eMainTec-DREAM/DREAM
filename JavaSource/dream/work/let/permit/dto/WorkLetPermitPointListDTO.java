package dream.work.let.permit.dto;

import common.bean.BaseDTO;

/**
 * 안전작업허가서 작업유형 - 점검항목 목록 DTO
 * @author syyang
 * @version $Id: WorkLetPermitPointListDTO.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 *
 */
public class WorkLetPermitPointListDTO extends BaseDTO
{
	/** 안전작업 표준점검항목 ID (key) */
	private String woLetListPointId  = "";
	

	public String getWoLetListPointId() {
		return woLetListPointId;
	}

	public void setWoLetListPointId(String woLetListPointId) {
		this.woLetListPointId = woLetListPointId;
		super.setAuditKey(woLetListPointId);
	}

}
