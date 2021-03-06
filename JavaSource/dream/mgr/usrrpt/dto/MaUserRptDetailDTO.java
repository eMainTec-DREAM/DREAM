package dream.mgr.usrrpt.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 DTO
 * @author  kim21017
 * @version $Id: MaUserRptDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaUserRptDetailDTO extends BaseDTO
{
	/** 상세설명 */
	private String description 		= "";
	/** 제목 */
	private String title 			= "";
	/** 생성일자 */
	private String creDate			= "";
	/** 생성자 */
	private String creBy 			= "";
	/** 생성자 */
	private String creByDesc		= "";
	/** 부서 */
	private String creDept 			= "";
	/** 생성자 */
	private String creDeptDesc		= "";
	/** 유형[조회,입/수/삭제] */
	private String usrdataType 		= "";
	/** 유형[조회,입/수/삭제] */
	private String usrdataTypeDesc 	= "";
	/** 사용자지정Report ID */
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
