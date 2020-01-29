package dream.mgr.usrdata.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� DTO
 * @author  jung7126
 * @version $Id: McDataCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class McDataSelectCommonDTO extends BaseDTO
{
	/** key ID */
	private String usrrptId 		= "";
	/** ���� */
	private String title 			= "";
	/** ������ */
	private String creBy 			= "";
	/** �����ڸ� */
	private String creByDesc 		= "";
	/** �������� From */
	private String creDateFrom	 	= "";
	/** �������� To */
	private String creDateTo	 	= "";
	/** ���������� */
	private String usrdataType	 	= "";
	
	private String usrrptType	 	= "";
	/** ������������ */
	private String usrdataTypeDesc	= "";
	/** Script */
	private String script			= "";
	
	private String compNo			= "";
	
	
	public String getUsrrptType() {
		return usrrptType;
	}
	public void setUsrrptType(String usrrptType) {
		this.usrrptType = usrrptType;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public String getUsrrptId() {
		return usrrptId;
	}
	public void setUsrrptId(String usrrptId) {
		this.usrrptId = usrrptId;
		super.setAuditKey(usrrptId);
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
}
