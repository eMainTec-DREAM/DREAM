package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 순회점검 작업결과 상세 DTO
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultMstrDetailDTO.java,v 1.1 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since   1.0
 */
public class WorkListPtrlResultMstrDetailDTO extends BaseDTO
{
 //   WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO = new WorkCalPmPtrlMonthCommonDTO();
    
    /** PM 순회점검 결과 id */
    private String pmPtrlRsltListId       = "";
    
    /** 스케쥴 ID */
    private String pmPtrlSchedId                = "";
        
    /** 점검예정일자 */
    private String ptrlDate               = "";
    /** 순회업무 ID */
    private String ptrlWorkId             = "";
    /** 순회업무명 */
    private String ptrlWorkTitle          = "";
    /** 순회업무No */
    private String ptrlWorkNo             = "";
    /**부서 ID*/
    private String deptId                 = "";
    /**부서명*/
    private String deptDesc               = "";    
    /** 교대조 ID */
    private String ptrlShiftTypeId          = "";
    /** 교대조명 */
    private String ptrlShiftTypeDesc        = "";
    /** 담당자 ID*/
    private String managerId              = "";
    /** 담당자명*/
    private String managerDesc            = "";
    /** 점검완료일자 */
    private String ptrlComDate            = "";
    /** 점검완료시간 */
    private String ptrlComTime            = "";
    /** 점검자 ID*/
    private String ptrlInspectorId        = "";
    /** 점검자명*/
    private String ptrlInspectorDesc      = "";
    /** 상태 ID*/
    private String ptrlStatusId           = "";
    /** 상태 Desc*/
    private String ptrlStatusDesc         = "";
    /** 비고 */
    private String remark 		          = "";

    /** PM param */
    private String pmParam 				  = "";
    
    
	public String getPmParam() {
		return pmParam;
	}
	public void setPmParam(String pmParam) {
		this.pmParam = pmParam;
	}
	public String getPmPtrlRsltListId()
    {
        return pmPtrlRsltListId;
    }
    public void setPmPtrlRsltListId(String pmPtrlRsltListId)
    {
        this.pmPtrlRsltListId = pmPtrlRsltListId;
        super.setAuditKey(pmPtrlRsltListId);
    }
    public String getPmPtrlSchedId()
    {
        return pmPtrlSchedId;
    }
    public void setPmPtrlSchedId(String pmPtrlSchedId)
    {
        this.pmPtrlSchedId = pmPtrlSchedId;
    }
    public String getPtrlShiftTypeId()
    {
        return ptrlShiftTypeId;
    }
    public void setPtrlShiftTypeId(String ptrlShiftTypeId)
    {
        this.ptrlShiftTypeId = ptrlShiftTypeId;
    }
    public String getPtrlShiftTypeDesc()
    {
        return ptrlShiftTypeDesc;
    }
    public void setPtrlShiftTypeDesc(String ptrlShiftTypeDesc)
    {
        this.ptrlShiftTypeDesc = ptrlShiftTypeDesc;
    }
    public String getPtrlComTime()
    {
        return ptrlComTime;
    }
    public void setPtrlComTime(String ptrlComTime)
    {
        this.ptrlComTime = CommonUtil.convertTime(ptrlComTime);
    }
    public String getPtrlDate()
    {
        return ptrlDate;
    }
    public void setPtrlDate(String ptrlDate)
    {
        this.ptrlDate = CommonUtil.convertDate(ptrlDate);
    }
    public String getPtrlWorkId()
    {
        return ptrlWorkId;
    }
    public void setPtrlWorkId(String ptrlWorkId)
    {
        this.ptrlWorkId = ptrlWorkId;
    }
    public String getPtrlWorkTitle()
    {
        return ptrlWorkTitle;
    }
    public void setPtrlWorkTitle(String ptrlWorkTitle)
    {
        this.ptrlWorkTitle = ptrlWorkTitle;
    }
    public String getPtrlWorkNo()
    {
        return ptrlWorkNo;
    }
    public void setPtrlWorkNo(String ptrlWorkNo)
    {
        this.ptrlWorkNo = ptrlWorkNo;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getManagerId()
    {
        return managerId;
    }
    public void setManagerId(String managerId)
    {
        this.managerId = managerId;
    }
    public String getManagerDesc()
    {
        return managerDesc;
    }
    public void setManagerDesc(String managerDesc)
    {
        this.managerDesc = managerDesc;
    }
    public String getPtrlComDate()
    {
        return ptrlComDate;
    }
    public void setPtrlComDate(String ptrlComDate)
    {
        this.ptrlComDate = CommonUtil.convertDate(ptrlComDate);
    }
    public String getPtrlInspectorId()
    {
        return ptrlInspectorId;
    }
    public void setPtrlInspectorId(String ptrlInspectorId)
    {
        this.ptrlInspectorId = ptrlInspectorId;
    }
    public String getPtrlInspectorDesc()
    {
        return ptrlInspectorDesc;
    }
    public void setPtrlInspectorDesc(String ptrlInspectorDesc)
    {
        this.ptrlInspectorDesc = ptrlInspectorDesc;
    }
    public String getPtrlStatusId()
    {
        return ptrlStatusId;
    }
    public void setPtrlStatusId(String ptrlStatusId)
    {
        this.ptrlStatusId = ptrlStatusId;
    }
    public String getPtrlStatusDesc()
    {
        return ptrlStatusDesc;
    }
    public void setPtrlStatusDesc(String ptrlStatusDesc)
    {
        this.ptrlStatusDesc = ptrlStatusDesc;
    }
    public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}