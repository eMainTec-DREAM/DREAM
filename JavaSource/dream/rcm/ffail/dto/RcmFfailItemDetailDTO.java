package dream.rcm.ffail.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 질의 - 상세  DTO
 * @author  kim21017
 * @version $Id: RcmFfailItemDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmFfailItemDetailDTO extends BaseDTO
{
	/** Key ID */
	private String rcmFfailId 	= "";
	/** Key No */
	private String rcmFfailNo 	= "";
	/** desc */
	private String description 	= "";
	/** remark */
	private String remark 	= "";
	public String getRcmFfailId() {
		return rcmFfailId;
	}
	public void setRcmFfailId(String rcmFfailId) {
		this.rcmFfailId = rcmFfailId;
		super.setAuditKey(rcmFfailId);
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}