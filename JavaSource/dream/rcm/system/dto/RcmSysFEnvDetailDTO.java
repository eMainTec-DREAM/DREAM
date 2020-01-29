package dream.rcm.system.dto;

import common.bean.BaseDTO;

/**
 * 운전환경 - 상세  DTO
 * @author  kim21017
 * @version $Id: RcmSysFEnvDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmSysFEnvDetailDTO extends BaseDTO
{
	/** Key ID */
	private String rcmListId 		= "";

	private String rcmFEnvId 		= "";
	
	private String rcmFuncId 		= "";
	
	private String rcmFEnvType 		= "";
	
	private String rcmFEnvTypeDesc	= "";
	
	private String description 		= "";
	
	private String remark 			= "";


	public String getRcmListId() {
		return rcmListId;
	}

	public void setRcmListId(String rcmListId) {
		this.rcmListId = rcmListId;
	}

	public String getRcmFEnvId() {
		return rcmFEnvId;
	}

	public void setRcmFEnvId(String rcmFEnvId) {
		this.rcmFEnvId = rcmFEnvId;
		super.setAuditKey(rcmFEnvId);
	}

	public String getRcmFuncId() {
		return rcmFuncId;
	}

	public void setRcmFuncId(String rcmFuncId) {
		this.rcmFuncId = rcmFuncId;
	}

	public String getRcmFEnvType() {
		return rcmFEnvType;
	}

	public void setRcmFEnvType(String rcmFEnvType) {
		this.rcmFEnvType = rcmFEnvType;
	}

	public String getRcmFEnvTypeDesc() {
		return rcmFEnvTypeDesc;
	}

	public void setRcmFEnvTypeDesc(String rcmFEnvTypeDesc) {
		this.rcmFEnvTypeDesc = rcmFEnvTypeDesc;
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
