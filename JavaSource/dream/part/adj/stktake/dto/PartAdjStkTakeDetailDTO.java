package dream.part.adj.stktake.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 부품실사 - 상세 DTO
 * @author  kim21017
 * @version $Id: PartAdjStkTakeHdrDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class PartAdjStkTakeDetailDTO extends BaseDTO
{	
	private String ptStkTakeListId		 		= "";
	
	private String ptStkTakeListNo		 		= "";
	
	private String ptStkTakeStatusDesc		 	= "";
	
	private String description		 			= "";
	
	private String wcodeId		 				= "";
	
	private String wname		 				= "";
	
	private String whType		 				= "";
	
	private String pmDate		 				= "";
	
	private String remark		 				= "";
	
	private String actDate		 				= "";
	
	private String deptDesc		 				= "";
	
	private String deptId		 				= "";
	
	private String userDesc		 				= "";
	
	private String userId		 				= "";
	
	private String regDate		 				= "";
	
	private String ptStkTakeStatus		 		= "";

	/** 공장  */
	private String plantId						= "";
	/** 공장명  */
	private String plantDesc					= "";
	/** G/W승인번호 */
	private String lappNo						= "";
	
	public String getLappNo() {
		return lappNo;
	}

	public void setLappNo(String lappNo) {
		this.lappNo = lappNo;
	}

	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public String getPlantDesc() {
		return plantDesc;
	}

	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}

	public String getPtStkTakeListNo() {
		return ptStkTakeListNo;
	}

	public void setPtStkTakeListNo(String ptStkTakeListNo) {
		this.ptStkTakeListNo = ptStkTakeListNo;
	}

	public String getPtStkTakeStatus() {
		return ptStkTakeStatus;
	}

	public void setPtStkTakeStatus(String ptStkTakeStatus) {
		this.ptStkTakeStatus = ptStkTakeStatus;
	}

	public String getPtStkTakeListId() {
		return ptStkTakeListId;
	}

	public void setPtStkTakeListId(String ptStkTakeListId) {
		this.ptStkTakeListId = ptStkTakeListId;
		super.setAuditKey(ptStkTakeListId);
	}

	public String getPtStkTakeStatusDesc() {
		return ptStkTakeStatusDesc;
	}

	public void setPtStkTakeStatusDesc(String ptStkTakeStatusDesc) {
		this.ptStkTakeStatusDesc = ptStkTakeStatusDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWcodeId() {
		return wcodeId;
	}

	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getWhType() {
		return whType;
	}

	public void setWhType(String whType) {
		this.whType = whType;
	}

	public String getPmDate() {
		return pmDate;
	}

	public void setPmDate(String pmDate) {
		this.pmDate = CommonUtil.convertDate(pmDate);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = CommonUtil.convertDate(actDate);
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = CommonUtil.convertDate(regDate);
	}
	
	
	
}
