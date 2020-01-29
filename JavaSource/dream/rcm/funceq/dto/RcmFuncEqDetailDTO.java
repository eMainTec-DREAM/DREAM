package dream.rcm.funceq.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 질의 - 상세 DTO
 * @author  kim21017
 * @version $Id: RcmFuncEqDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class RcmFuncEqDetailDTO extends BaseDTO
{
	/** rcm ID */
	private String rcmFfailId 					= "";
	/** rcm ID */
	private String rcmDesc 					= "";
	
	private String funcNo 					= "";
	
	private String funcId 					= "";
	
	private String funcName 					= "";
	
	private String remark 					= "";
	
	private String rcmListId 					= "";
	
	private String rcmFfailNo 					= "";
	
	private String description 					= "";
	
	
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	public String getRcmFfailNo() {
		return rcmFfailNo;
	}
	public void setRcmFfailNo(String rcmFfailNo) {
		this.rcmFfailNo = rcmFfailNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRcmListId() {
		return rcmListId;
	}
	public void setRcmListId(String rcmListId) {
		this.rcmListId = rcmListId;
	}
	public String getRcmFfailId() {
		return rcmFfailId;
	}
	public void setRcmFfailId(String rcmFfailId) {
		this.rcmFfailId = rcmFfailId;
		super.setAuditKey(rcmFfailId);
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
