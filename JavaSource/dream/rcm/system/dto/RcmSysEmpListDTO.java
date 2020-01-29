package dream.rcm.system.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���漳�� �� DTO
 * @author  jung7126
 * @version $Id: WorkPmListEquipDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class RcmSysEmpListDTO extends BaseDTO
{
	/** �м���Id */
	private String rcmEmpId		= "";

	public String getRcmEmpId() {
		return rcmEmpId;
	}

	public void setRcmEmpId(String rcmEmpId) {
		this.rcmEmpId = rcmEmpId;
		super.setAuditKey(rcmEmpId);
	}

}