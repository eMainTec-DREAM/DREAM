package dream.tool.iss.rtn.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���ⱸ�ݳ� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPttRtnCommonDTO extends BaseDTO
{
	/** ID */
	private String ptRtnListId         = "";
	
	/** ����-�μ�Id */
	private String filterDeptId        = "";
	/** ����-�μ��� */
	private String filterDeptDesc      = "";
	/** ����-�ݳ����� From */
	private String filterRtnStartDate  = "";
	/** ����-�ݳ����� To */
	private String filterRtnEndDate    = "";
	/** ����-������Id */
	private String filterRecBy     = "";
	/** ����-�����ڸ� */
	private String filterRecName = "";
	/** ����-ǰ��/�԰ݸ� */
	private String filterPartNameSize  = "";

	/** ���� - �۾���Ȳ���� �˾� ���� �� ��� */
	private String ptRtnStatus        = "";
	/** ���¸� - �۾���Ȳ���� �˾� ���� �� ��� */
	private String ptRtnStatusDesc    = "";
	
	/** ����-â���ڵ� */
	private String filterWcodeId        = "";
	/** ����-â��� */
	private String filterWname      = "";
	
    public String getPtRtnListId() {
		return ptRtnListId;
	}
	public void setPtRtnListId(String ptRtnListId) {
		this.ptRtnListId = ptRtnListId;
		super.setAuditKey(ptRtnListId);
	}
	public String getFilterRtnStartDate() {
		return filterRtnStartDate;
	}
	public void setFilterRtnStartDate(String filterRtnStartDate) {
		this.filterRtnStartDate = filterRtnStartDate;
	}
	public String getFilterRtnEndDate() {
		return filterRtnEndDate;
	}
	public void setFilterRtnEndDate(String filterRtnEndDate) {
		this.filterRtnEndDate = filterRtnEndDate;
	}
	public String getPtRtnStatus() {
		return ptRtnStatus;
	}
	public void setPtRtnStatus(String ptRtnStatus) {
		this.ptRtnStatus = ptRtnStatus;
	}
	public String getPtRtnStatusDesc() {
		return ptRtnStatusDesc;
	}
	public void setPtRtnStatusDesc(String ptRtnStatusDesc) {
		this.ptRtnStatusDesc = ptRtnStatusDesc;
	}
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