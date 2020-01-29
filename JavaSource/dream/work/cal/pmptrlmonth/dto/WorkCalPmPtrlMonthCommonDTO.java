package dream.work.cal.pmptrlmonth.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������������ ���� DTO
 * @author  youngjoo38
 * @version $Id: WorkCalPmPtrlMonthCommonDTO.java,v 1.0 2017/09/24 09:13:08 youngjoo38 Exp $
 * @since   1.0
 *
 */
public class WorkCalPmPtrlMonthCommonDTO extends BaseDTO
{
    
    /** PM ��ȸ���� ��� id */
    private String pmPtrlRsltListId        = "";
    /** ������ ID */
    private String pmPtrlSchedId           = "";
    
    /** ���� �� */
    private String filterYyyymm            = "";
    /** ���� �μ�id */
    private String filterDeptId            = "";
    /** ���� �μ��� */
    private String filterDeptDesc          = "";
        /** Filter ��ȸ���� ID */
        private String filterPtrlWorkId        = "";
        /** Filter ��ȸ������ */
        private String filterPtrlWorkTitle     = "";
        /** Filter ��ȸ����No */
        private String filterPtrlWorkNo        = "";
    /** Filter �۾��׷� ID */
    private String filterPtrlWorkGrpId     = "";
    /** Filter �۾��׷�� */
    private String filterPtrlWorkGrpDesc   = "";
        /** Filter ����� ID*/
        private String filterManagerId         = "";
        /** Filter ����ڸ�*/
        private String filterManagerDesc       = "";
    /** ����-pmNo */
    private String filterPmNo               = "";
    
    /** ����� */
    private String yyyymmdd                = "";
    /** schedType */
    private String schedType               = "";
    /** �μ�id(�˻��� �μ�id ����)*/
    private String deptId                  = "";
    /** �μ���(�˻��� �μ��� ����) */
    private String deptDesc                = "";
    /** ��ȸ���� ID */
    private String ptrlWorkId              = "";
    /** ��ȸ������ */
    private String ptrlWorkTitle           = "";
    /** ��ȸ����No */
    private String ptrlWorkNo              = "";
    /** �۾��׷� ID */
    private String ptrlWorkGrpId           = "";
    /** �۾��׷�� */
    private String ptrlWorkGrpDesc         = "";
    /** ����� ID*/
    private String managerId               = "";
    /** ����ڸ�*/
    private String managerDesc             = "";
    /** pm��ȣ */
    private String pmNo                 = "";
    
    /** filter-����� Id */
    private String filterEmpId              = "";
    /** filter-����� */
    private String filterEmpDesc            = "";
    /** ����� Id */
    private String empId                = "";
    /** ����� */
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
