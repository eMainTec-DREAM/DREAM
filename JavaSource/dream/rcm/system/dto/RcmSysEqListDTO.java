package dream.rcm.system.dto;

import common.bean.BaseDTO;

/**
 * 설비설정 목록 DTO
 * @author  jung7126
 * @version $Id: WorkPmListEquipDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class RcmSysEqListDTO extends BaseDTO
{
	/** 설비설정Id */
	private String rcmEqId		= "";

	public String getRcmEqId() {
		return rcmEqId;
	}

	public void setRcmEqId(String rcmEqId) {
		this.rcmEqId = rcmEqId;
		super.setAuditKey(rcmEqId);
	}

}