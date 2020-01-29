package dream.rcm.system.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 기능정의 상세 DTO
 * @author  jung7126
 * @version $Id: RcmSysFDefDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class RcmSysFDefDetailDTO extends BaseDTO
{
	/** 예방작업설비ID */
	private String rcmListId			= "";
	/** 설비id*/
	private String rcmFuncId			= "";
	/** 설비명*/
	private String rcmFuncNo			= "";
	/** 설비위치명 */
	private String description			= "";
	/** 작업부위 */
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