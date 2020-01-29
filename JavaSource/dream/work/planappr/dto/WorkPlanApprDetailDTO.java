package dream.work.planappr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업계획승인 - 상세 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 */
public class WorkPlanApprDetailDTO extends BaseDTO
{
	/** 계획승인ID */
	private String woPlanApprId 				= "";
	/** 작업일자 시작일 */
	private String startDate					= "";
	/** 작업일자 종료일 */
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
	private String woPlanApprStatusId			= "";
	/** 상태명 */
	private String woPlanApprStatusDesc			= "";
	/** 공장ID */
	private String plantId			 			= "";
	/** 공장명 */
	private String plantDesc                    = "";
	/** 년 */
	private String yyyy                         = "";
	/** 년월 */
	private String yyyymm                       = "";
	/** 작업계획승인구분 */
	private String woplanapprType               = "";
	
	private String woType                       = "";
	
	
	public String getWoType()
    {
        return woType;
    }
    public void setWoType(String woType)
    {
        this.woType = woType;
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
    public String getWoplanapprType()
    {
        return woplanapprType;
    }
    public void setWoplanapprType(String woplanapprType)
    {
        this.woplanapprType = woplanapprType;
    }
    public String getWoPlanApprId() {
		return woPlanApprId;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public void setWoPlanApprId(String woPlanApprId) {
		this.woPlanApprId = woPlanApprId;
		super.setAuditKey(woPlanApprId);
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
	public String getWoPlanApprStatusId() {
		return woPlanApprStatusId;
	}
	public void setWoPlanApprStatusId(String woPlanApprStatusId) {
		this.woPlanApprStatusId = woPlanApprStatusId;
	}
	public String getWoPlanApprStatusDesc() {
		return woPlanApprStatusDesc;
	}
	public void setWoPlanApprStatusDesc(String woPlanApprStatusDesc) {
		this.woPlanApprStatusDesc = woPlanApprStatusDesc;
	}
}
