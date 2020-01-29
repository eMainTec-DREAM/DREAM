package dream.work.cal.pminsappr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �������˰�ȹ���� ���� DTO
 * @author  kim21017
 * @version $Id$
 * @since   1.0
 * 
 */
public class WorkCalPmInsApprCommonDTO extends BaseDTO
{
	/** ��ȹ���� ID */
	private String pmInsSchedApprId				= "";
	/** filter- �ۼ����� ������ */
	private String filterStartDate				= "";
	/** filter- �ۼ����� ������ */
	private String filterEndDate				= "";
	/** filter- �μ�ID */
	private String filterDeptId					= "";
	/** filter- �μ��� */
	private String filterDeptDesc				= "";
	/** filter- ���� */
	private String filterDesc					= "";
	/** filter- ����ID */
	private String filterPlantId				= "";
	/** filter- ����� */
	private String filterPlantDesc				= "";
	/** filter - �� */
	private String filterYyyy					= "";
	/** filter - ��� */
	private String filterYyyymm					= "";
	/** filter - ���˰�ȹ���� ���� ID */
	private String filterPminsschedapprType		= "";
	/** filter- ���˰�ȹ���� ���� �� */
	private String filterPminsschedapprTypeDesc	= "";
	/** filter- ���˰�ȹ���� ��ȣ */
	private String filterPmInsSchedApprNo		= "";
	
	/** ���˰�ȹ ���� ���� */
	private String pminsschedapprType           = "";
	/** ���˰�ȹ ����  */
	private String pmschedStatus                = "";
	
	
	public String getPmschedStatus()
    {
        return pmschedStatus;
    }
    public void setPmschedStatus(String pmschedStatus)
    {
        this.pmschedStatus = pmschedStatus;
    }
    public String getPminsschedapprType()
    {
        return pminsschedapprType;
    }
    public void setPminsschedapprType(String pminsschedapprType)
    {
        this.pminsschedapprType = pminsschedapprType;
    }
    public String getPmInsSchedApprId() {
		return pmInsSchedApprId;
	}
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public void setPmInsSchedApprId(String pmInsSchedApprId) {
		this.pmInsSchedApprId = pmInsSchedApprId;
		super.setAuditKey(pmInsSchedApprId);
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
	public String getFilterDesc() {
		return filterDesc;
	}
	public void setFilterDesc(String filterDesc) {
		this.filterDesc = filterDesc;
	}
	public String getFilterYyyy() {
		return filterYyyy;
	}
	public void setFilterYyyy(String filterYyyy) {
		this.filterYyyy = filterYyyy;
	}
	public String getFilterYyyymm() {
		return filterYyyymm;
	}
	public void setFilterYyyymm(String filterYyyymm) {
		this.filterYyyymm = filterYyyymm;
	}
	public String getFilterPminsschedapprType() {
		return filterPminsschedapprType;
	}
	public void setFilterPminsschedapprType(String filterPminsschedapprType) {
		this.filterPminsschedapprType = filterPminsschedapprType;
	}
	public String getFilterPminsschedapprTypeDesc() {
		return filterPminsschedapprTypeDesc;
	}
	public void setFilterPminsschedapprTypeDesc(String filterPminsschedapprTypeDesc) {
		this.filterPminsschedapprTypeDesc = filterPminsschedapprTypeDesc;
	}
	public String getFilterPmInsSchedApprNo() {
		return filterPmInsSchedApprNo;
	}
	public void setFilterPmInsSchedApprNo(String filterPmInsSchedApprNo) {
		this.filterPmInsSchedApprNo = filterPmInsSchedApprNo;
	}
	
}
