package dream.work.cal.pmptrlmonth.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 월간예방일정 공통 DTO
 * @author  youngjoo38
 * @version $Id: WorkCalPmPtrlMonthCommonDTO.java,v 1.0 2017/09/24 09:13:08 youngjoo38 Exp $
 * @since   1.0
 *
 */
public class WorkCalPmPtrlMonthCommonDTO extends BaseDTO
{
    
    /** PM 순회점검 결과 id */
    private String pmPtrlRsltListId        = "";
    /** 스케쥴 ID */
    private String pmPtrlSchedId           = "";
    
    /** 필터 년 */
    private String filterYyyymm            = "";
    /** 필터 부서id */
    private String filterDeptId            = "";
    /** 필터 부서명 */
    private String filterDeptDesc          = "";
        /** Filter 순회업무 ID */
        private String filterPtrlWorkId        = "";
        /** Filter 순회업무명 */
        private String filterPtrlWorkTitle     = "";
        /** Filter 순회업무No */
        private String filterPtrlWorkNo        = "";
    /** Filter 작업그룹 ID */
    private String filterPtrlWorkGrpId     = "";
    /** Filter 작업그룹명 */
    private String filterPtrlWorkGrpDesc   = "";
        /** Filter 담당자 ID*/
        private String filterManagerId         = "";
        /** Filter 담당자명*/
        private String filterManagerDesc       = "";
    /** 필터-pmNo */
    private String filterPmNo               = "";
    
    /** 년월일 */
    private String yyyymmdd                = "";
    /** schedType */
    private String schedType               = "";
    /** 부서id(검색된 부서id 저장)*/
    private String deptId                  = "";
    /** 부서명(검색된 부서명 저장) */
    private String deptDesc                = "";
    /** 순회업무 ID */
    private String ptrlWorkId              = "";
    /** 순회업무명 */
    private String ptrlWorkTitle           = "";
    /** 순회업무No */
    private String ptrlWorkNo              = "";
    /** 작업그룹 ID */
    private String ptrlWorkGrpId           = "";
    /** 작업그룹명 */
    private String ptrlWorkGrpDesc         = "";
    /** 담당자 ID*/
    private String managerId               = "";
    /** 담당자명*/
    private String managerDesc             = "";
    /** pm번호 */
    private String pmNo                 = "";
    
    /** filter-담당자 Id */
    private String filterEmpId              = "";
    /** filter-담당자 */
    private String filterEmpDesc            = "";
    /** 담당자 Id */
    private String empId                = "";
    /** 담당자 */
    private String empDesc          = "";
    
    
    public String getFilterEmpId()
    {
        return filterEmpId;
    }
    public void setFilterEmpId(String filterEmpId)
    {
        this.filterEmpId = filterEmpId;
    }
    public String getFilterEmpDesc()
    {
        return filterEmpDesc;
    }
    public void setFilterEmpDesc(String filterEmpDesc)
    {
        this.filterEmpDesc = filterEmpDesc;
    }
    public String getEmpId()
    {
        return empId;
    }
    public void setEmpId(String empId)
    {
        this.empId = empId;
    }
    public String getEmpDesc()
    {
        return empDesc;
    }
    public void setEmpDesc(String empDesc)
    {
        this.empDesc = empDesc;
    }
    public String getPmPtrlRsltListId()
    {
        return pmPtrlRsltListId;
    }
    public void setPmPtrlRsltListId(String pmPtrlRsltListId)
    {
        this.pmPtrlRsltListId = pmPtrlRsltListId;
    }
    public String getPmPtrlSchedId()
    {
        return pmPtrlSchedId;
    }
    public void setPmPtrlSchedId(String pmPtrlSchedId)
    {
        this.pmPtrlSchedId = pmPtrlSchedId;
    }
    public String getFilterPtrlWorkId()
    {
        return filterPtrlWorkId;
    }
    public void setFilterPtrlWorkId(String filterPtrlWorkId)
    {
        this.filterPtrlWorkId = filterPtrlWorkId;
    }
    public String getFilterPtrlWorkTitle()
    {
        return filterPtrlWorkTitle;
    }
    public void setFilterPtrlWorkTitle(String filterPtrlWorkTitle)
    {
        this.filterPtrlWorkTitle = filterPtrlWorkTitle;
    }
    public String getFilterPtrlWorkNo()
    {
        return filterPtrlWorkNo;
    }
    public void setFilterPtrlWorkNo(String filterPtrlWorkNo)
    {
        this.filterPtrlWorkNo = filterPtrlWorkNo;
    }
    public String getFilterPtrlWorkGrpId()
    {
        return filterPtrlWorkGrpId;
    }
    public void setFilterPtrlWorkGrpId(String filterPtrlWorkGrpId)
    {
        this.filterPtrlWorkGrpId = filterPtrlWorkGrpId;
    }
    public String getFilterPtrlWorkGrpDesc()
    {
        return filterPtrlWorkGrpDesc;
    }
    public void setFilterPtrlWorkGrpDesc(String filterPtrlWorkGrpDesc)
    {
        this.filterPtrlWorkGrpDesc = filterPtrlWorkGrpDesc;
    }
    public String getFilterManagerId()
    {
        return filterManagerId;
    }
    public void setFilterManagerId(String filterManagerId)
    {
        this.filterManagerId = filterManagerId;
    }
    public String getFilterManagerDesc()
    {
        return filterManagerDesc;
    }
    public void setFilterManagerDesc(String filterManagerDesc)
    {
        this.filterManagerDesc = filterManagerDesc;
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
    public String getPtrlWorkGrpId()
    {
        return ptrlWorkGrpId;
    }
    public void setPtrlWorkGrpId(String ptrlWorkGrpId)
    {
        this.ptrlWorkGrpId = ptrlWorkGrpId;
    }
    public String getPtrlWorkGrpDesc()
    {
        return ptrlWorkGrpDesc;
    }
    public void setPtrlWorkGrpDesc(String ptrlWorkGrpDesc)
    {
        this.ptrlWorkGrpDesc = ptrlWorkGrpDesc;
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

    
    //-------------------------------------------------//

    public String getFilterPmNo() {
		return filterPmNo;
	}
	public void setFilterPmNo(String filterPmNo) {
		this.filterPmNo = filterPmNo;
	}
	public String getPmNo() {
		return pmNo;
	}
	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}
	public String getFilterYyyymm() {
		return filterYyyymm;
	}
	public void setFilterYyyymm(String filterYyyymm) {
		this.filterYyyymm = CommonUtil.convertDate(filterYyyymm);
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getYyyymmdd() {
		return yyyymmdd;
	}
	public void setYyyymmdd(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
	}
	public String getSchedType() {
		return schedType;
	}
	public void setSchedType(String schedType) {
		this.schedType = schedType;
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
}
