package dream.mgr.usrrpt.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� DTO
 * @author  kim21017
 * @version $Id: MaUserRptDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaUserRptDetailDTO extends BaseDTO
{
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
	/** ����[��ȸ,��/��/����] */
	private String usrdataType 		= "";
	/** ����[��ȸ,��/��/����] */
	private String usrdataTypeDesc 	= "";
	/** ���������Report ID */
	private String usrrptlistId 	= "";
	/** compNo */
	private String compNo	 		= "";
	
	
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
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
	public String getUsrrptlistId() {
		return usrrptlistId;
	}
	public void setUsrrptlistId(String usrrptlistId) {
		this.usrrptlistId = usrrptlistId;
		super.setAuditKey(usrrptlistId);
	}

}
