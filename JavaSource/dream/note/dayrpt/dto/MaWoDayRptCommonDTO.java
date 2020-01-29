package dream.note.dayrpt.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaWoDayRptCommonDTO extends BaseDTO
{
	/** ����-�۾����� From */
	private String filterStartDate		= "";
	/** ����-�۾����� To */
    private String filterEndDate        = "";
    /** ����-�μ�ID */
    private String filterDeptId         = "";
    /** ����-�μ��� */
    private String filterDeptDesc       = "";
    /** ����-�ۼ���ID */
    private String filterEmpId          = "";
    /** ����-�ۼ��ڸ� */
    private String filterEmpDesc        = "";
    /** ID */
    private String wrkDayRptId          = "";
    
	public String getWrkDayRptId() {
		return wrkDayRptId;
	}
	public void setWrkDayRptId(String wrkDayRptId) {
		this.wrkDayRptId = wrkDayRptId;
		super.setAuditKey(wrkDayRptId);
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = CommonUtil.convertDate(filterStartDate);
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = CommonUtil.convertDate(filterEndDate);
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
	public String getFilterEmpId() {
		return filterEmpId;
	}
	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}
	public String getFilterEmpDesc() {
		return filterEmpDesc;
	}
	public void setFilterEmpDesc(String filterEmpDesc) {
		this.filterEmpDesc = filterEmpDesc;
	}
    
}
