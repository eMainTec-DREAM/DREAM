package dream.work.cal.pminsappr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방점검계획승인 - 상세 DTO
 * @author  kim21017
 * @version $Id$
 * @since   1.0
 *
 */
public class WorkCalPmInsApprDetailDTO extends BaseDTO
{
	/** 계획승인ID */
	private String pmInsSchedApprId 			= "";
	/** 계획승인 번호 */
	private String pmInsSchedApprNo 			= "";
	/** 점검일자 시작일 */
	private String startDate					= "";
	/** 점검일자 종료일 */
	private String endDate						= "";
	/** 부서ID */
	private String deptId			 			= "";
	/** 부서명 */
	private String deptDesc			 			= "";
	/** 제목 */
	private String description		 			= "";
	/** 작성일자 */
	private String updDate			 			= "";
	/** 작성자ID */
	private String updBy			 			= "";
	/** 작성자명 */
	private String updDesc			 			= "";
	/** 비고 */
	private String remark			 			= "";
	/** 상태 ID */
	private String pmInsSchedAppStatusId		= "";
	/** 상태명 */
	private String pmInsSchedAppStatusDesc		= "";
	/** 공장ID */
	private String plantId			 			= "";
	
	private String plantDesc                   = "";
	
	private String yyyy                        = "";
	
	private String yyyymm                      = "";

	/** 점검계획승인 구분 ID */
	private String pminsschedapprType        = "";
	/** 점검계획승인 구분 */
	private String pminsschedapprTypeDesc	   = "";
	
	
	
    public String getPmInsSchedApprNo() {
		return pmInsSchedApprNo;
	}
	public void setPmInsSchedApprNo(String pmInsSchedApprNo) {
		this.pmInsSchedApprNo = pmInsSchedApprNo;
	}
	public String getPminsschedapprTypeDesc() {
		return pminsschedapprTypeDesc;
	}
	public void setPminsschedapprTypeDesc(String pminsschedapprTypeDesc) {
		this.pminsschedapprTypeDesc = pminsschedapprTypeDesc;
	}
	public String getPminsschedapprType() {
		return pminsschedapprType;
	}
	public void setPminsschedapprType(String pminsschedapprType) {
		this.pminsschedapprType = pminsschedapprType;
	}
	public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getYyyy()
    {
        return yyyy;
    }
    public void setYyyy(String yyyy)
    {
        this.yyyy = yyyy;
    }
    public String getYyyymm()
    {
        return yyyymm;
    }
    public void setYyyymm(String yyyymm)
    {
        this.yyyymm = CommonUtil.convertDate(yyyymm);
    }
    public String getPmInsSchedAppStatusId() {
		return pmInsSchedAppStatusId;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public void setPmInsSchedAppStatusId(String pmInsSchedAppStatusId) {
		this.pmInsSchedAppStatusId = pmInsSchedAppStatusId;
	}
	public String getPmInsSchedAppStatusDesc() {
		return pmInsSchedAppStatusDesc;
	}
	public void setPmInsSchedAppStatusDesc(String pmInsSchedAppStatusDesc) {
		this.pmInsSchedAppStatusDesc = pmInsSchedAppStatusDesc;
	}
	public String getPmInsSchedApprId() {
		return pmInsSchedApprId;
	}
	public void setPmInsSchedApprId(String pmInsSchedApprId) {
		this.pmInsSchedApprId = pmInsSchedApprId;
		super.setAuditKey(pmInsSchedApprId);
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = CommonUtil.convertDate(startDate);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = CommonUtil.convertDate(updDate);
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public String getUpdDesc() {
		return updDesc;
	}
	public void setUpdDesc(String updDesc) {
		this.updDesc = updDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
