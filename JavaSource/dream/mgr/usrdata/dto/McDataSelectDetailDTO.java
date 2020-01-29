package dream.mgr.usrdata.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� DTO
 * @author  kim21017
 * @version $Id: McDataSelectDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class McDataSelectDetailDTO extends BaseDTO
{
	/** Script */
	private String script 			= "";
	/** �󼼼��� */
	private String description 		= "";
	/** ���� */
	private String title 			= "";
	/** �������� */
	private String creDate			= "";
	/** ������ */
	private String creBy 			= "";
	/** ������ */
	private String creByDesc		= "";
	/** �μ� */
	private String creDept 			= "";
	/** ������ */
	private String creDeptDesc		= "";
	/** ����Ÿ���� */
	private String usrdataType 		= "";
	/** ����Ÿ���� */
	private String usrdataTypeDesc 	= "";
	/** ����[��ȸ,��/��/����] */
	private String usrrptType 		= "";
	/** ����[��ȸ,��/��/����] */
	private String usrrptTypeDesc 	= "";
	/** ���������Report ID */
	private String usrrptId 		= "";
	/** compNo */
	private String compNo	 		= "";
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = CommonUtil.convertDate(creDate);
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
	public String getCreDept() {
		return creDept;
	}
	public void setCreDept(String creDept) {
		this.creDept = creDept;
	}
	public String getCreDeptDesc() {
		return creDeptDesc;
	}
	public void setCreDeptDesc(String creDeptDesc) {
		this.creDeptDesc = creDeptDesc;
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
	public String getUsrrptType() {
		return usrrptType;
	}
	public void setUsrrptType(String usrrptType) {
		this.usrrptType = usrrptType;
	}
	public String getUsrrptTypeDesc() {
		return usrrptTypeDesc;
	}
	public void setUsrrptTypeDesc(String usrrptTypeDesc) {
		this.usrrptTypeDesc = usrrptTypeDesc;
	}
	public String getUsrrptId() {
		return usrrptId;
	}
	public void setUsrrptId(String usrrptId) {
		this.usrrptId = usrrptId;
		super.setAuditKey(usrrptId);
	}

	
}
