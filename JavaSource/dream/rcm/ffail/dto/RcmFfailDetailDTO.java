package dream.rcm.ffail.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 질의 - 상세 DTO
 * @author  kim21017
 * @version $Id: RcmFfailDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class RcmFfailDetailDTO extends BaseDTO
{
	/** rcm ID */
	private String rcmFuncId 					= "";
	/** rcm ID */
	private String rcmDesc 					= "";
	
	private String funcNo 					= "";
	
	private String funcName 					= "";
	
	private String remark 					= "";
	
	private String rcmListId 					= "";
	
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
	public String getRcmDesc() {
		return rcmDesc;
	}
	public void setRcmDesc(String rcmDesc) {
		this.rcmDesc = rcmDesc;
	}
	public String getFuncNo() {
		return funcNo;
	}
	public void setFuncNo(String funcNo) {
		this.funcNo = funcNo;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
