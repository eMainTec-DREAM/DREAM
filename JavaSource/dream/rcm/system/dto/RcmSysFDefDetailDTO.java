package dream.rcm.system.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������� �� DTO
 * @author  jung7126
 * @version $Id: RcmSysFDefDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class RcmSysFDefDetailDTO extends BaseDTO
{
	/** �����۾�����ID */
	private String rcmListId			= "";
	/** ����id*/
	private String rcmFuncId			= "";
	/** �����*/
	private String rcmFuncNo			= "";
	/** ������ġ�� */
	private String description			= "";
	/** �۾����� */
	private String remark				= "";
	
	public String getRcmListId() {
		return rcmListId;
	}
	public void setRcmListId(String rcmListId) {
		this.rcmListId = rcmListId;
	}
	public String getRcmFuncId() {
		return rcmFuncId;
	}
	public void setRcmFuncId(String rcmFuncId) {
		this.rcmFuncId = rcmFuncId;
		super.setAuditKey(rcmFuncId);
	}
	public String getRcmFuncNo() {
		return rcmFuncNo;
	}
	public void setRcmFuncNo(String rcmFuncNo) {
		this.rcmFuncNo = rcmFuncNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}