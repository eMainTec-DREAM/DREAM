package dream.tool.iss.rent.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����԰� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPttIssCommonDTO extends BaseDTO
{
	/** ID */
	private String ptIssListId         = "";
	
	/** ����-�μ�Id */
	private String filterDeptId        = "";
	/** ����-�μ��� */
	private String filterDeptDesc      = "";
	/** ����-�԰����� From */
	private String filterIssStartDate  = "";
	/** ����-�԰����� To */
	private String filterIssEndDate    = "";
	/** ����-������Id */
	private String filterRecBy     = "";
	/** ����-�����ڸ� */
	private String filterRecName = "";
	/** ����-ǰ��/�԰ݸ� */
	private String filterPartNameSize  = "";

	/** ���� - �۾���Ȳ���� �˾� ���� �� ��� */
	private String ptIssStatus        = "";
	/** ���¸� - �۾���Ȳ���� �˾� ���� �� ��� */
	private String ptIssStatusDesc    = "";
	
	/** ����-â���ڵ� */
	private String filterWcodeId        = "";
	/** ����-â��� */
	private String filterWname      = "";
	
    public String getFilterWcodeId() {
		return filterWcodeId;
	}
	public void setFilterWcodeId(String filterWcodeId) {
		this.filterWcodeId = filterWcodeId;
	}
	public String getFilterWname() {
		return filterWname;
	}
	public void setFilterWname(String filterWname) {
		this.filterWname = filterWname;
	}

    public String getPtIssStatus() {
		return ptIssStatus;
	}
	public void setPtIssStatus(String ptIssStatus) {
		this.ptIssStatus = ptIssStatus;
	}
	public String getPtIssStatusDesc() {
		return ptIssStatusDesc;
	}
	public void setPtIssStatusDesc(String ptIssStatusDesc) {
		this.ptIssStatusDesc = ptIssStatusDesc;
	}
	public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
    public String getFilterPartNameSize()
    {
        return filterPartNameSize;
    }
    public void setFilterPartNameSize(String filterPartNameSize)
    {
        this.filterPartNameSize = filterPartNameSize;
    }
	public String getPtIssListId() {
		return ptIssListId;
	}
	public void setPtIssListId(String ptIssListId) {
		this.ptIssListId = ptIssListId;
		super.setAuditKey(ptIssListId);
	}
	public String getFilterIssStartDate() {
		return filterIssStartDate;
	}
	public void setFilterIssStartDate(String filterIssStartDate) {
		this.filterIssStartDate = filterIssStartDate;
	}
	public String getFilterIssEndDate() {
		return filterIssEndDate;
	}
	public void setFilterIssEndDate(String filterIssEndDate) {
		this.filterIssEndDate = filterIssEndDate;
	}
	public String getFilterRecBy() {
		return filterRecBy;
	}
	public void setFilterRecBy(String filterRecBy) {
		this.filterRecBy = filterRecBy;
	}
	public String getFilterRecName() {
		return filterRecName;
	}
	public void setFilterRecName(String filterRecName) {
		this.filterRecName = filterRecName;
	}
}