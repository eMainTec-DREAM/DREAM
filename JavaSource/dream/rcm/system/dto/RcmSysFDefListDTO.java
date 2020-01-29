package dream.rcm.system.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방설비 상세 DTO
 * @author  jung7126
 * @version $Id: WorkPmListEquipDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class RcmSysFDefListDTO extends BaseDTO
{
	/** 기능정의Id */
	private String rcmFuncId		= "";

	public String getRcmFuncId() {
		return rcmFuncId;
	}

	public void setRcmFuncId(String rcmFuncId) {
		this.rcmFuncId = rcmFuncId;
		super.setAuditKey(rcmFuncId);
	}
}