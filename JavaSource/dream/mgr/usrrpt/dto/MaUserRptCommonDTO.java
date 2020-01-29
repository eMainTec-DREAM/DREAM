package dream.mgr.usrrpt.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 傍烹 DTO
 * @author  jung7126
 * @version $Id: McDataCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaUserRptCommonDTO extends BaseDTO
{
	/** key ID */
	private String usrrptlistId 		= "";
	
	private String usrrpttabId		= "";
	/** 力格 */
	private String title 			= "";
	/** 积己磊 */
	private String creBy 			= "";
	/** 积己磊疙 */
	private String creByDesc 		= "";
	/** 积己老磊 From */
	private String creDateFrom	 	= "";
	/** 积己老磊 To */
	private String creDateTo	 	= "";
	
	private String usrdataType	 	= "";
	
	private String usrdataTypeDesc 	= "";
	
	private String compNo			= "";
	
	private String usrrptjoinId		= "";
	
	private String stepNum			= "";
	
	private String usrrptcolId		= "";
	
	private String tabcolId			= "";
	
	private String usrrptwhrId		= "";
	
	private String usrrptordId		= "";
	
	
	public String getUsrrptordId() {
		return usrrptordId;
	}
	public void setUsrrptordId(String usrrptordId) {
		this.usrrptordId = usrrptordId;
	}
	public String getUsrrptwhrId() {
		return usrrptwhrId;
	}
	public void setUsrrptwhrId(String usrrptwhrId) {
		this.usrrptwhrId = usrrptwhrId;
	}
	public String getTabcolId() {
		return tabcolId;
	}
	public void setTabcolId(String tabcolId) {
		this.tabcolId = tabcolId;
	}
	public String getUsrrptcolId() {
		return usrrptcolId;
	}
	public void setUsrrptcolId(String usrrptcolId) {
		this.usrrptcolId = usrrptcolId;
	}
	public String getStepNum() {
		return stepNum;
	}
	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}
	public String getUsrrptjoinId() {
		return usrrptjoinId;
	}
	public void setUsrrptjoinId(String usrrptjoinId) {
		this.usrrptjoinId = usrrptjoinId;
	}
	public String getUsrrpttabId() {
		return usrrpttabId;
	}
	public void setUsrrpttabId(String usrrpttabId) {
		this.usrrpttabId = usrrpttabId;
	}

	public String getUsrdataType() {
		return usrdataType;
	}
	public void setUsrdataType(String usrdataType) {
		this.usrdataType = usrdataType;
	}
	public String getUsrdataTypeDesc() {
		return usrdataTypeDesc;
	}
	public void setUsrdataTypeDesc(String usrdataTypeDesc) {
		this.usrdataTypeDesc = usrdataTypeDesc;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreBy() {
		return creBy;
	}
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}
	public String getCreByDesc() {
		return creByDesc;
	}
	public void setCreByDesc(String creByDesc) {
		this.creByDesc = creByDesc;
	}
	public String getCreDateFrom() {
		return creDateFrom;
	}
	public void setCreDateFrom(String creDateFrom) {
		this.creDateFrom = CommonUtil.convertDate(creDateFrom);
	}
	public String getCreDateTo() {
		return creDateTo;
	}
	public void setCreDateTo(String creDateTo) {
		this.creDateTo = CommonUtil.convertDate(creDateTo);
	}
	public String getUsrrptlistId() {
		return usrrptlistId;
	}
	public void setUsrrptlistId(String usrrptlistId) {
		this.usrrptlistId = usrrptlistId;
		super.setAuditKey(usrrptlistId);
	}
	
}
