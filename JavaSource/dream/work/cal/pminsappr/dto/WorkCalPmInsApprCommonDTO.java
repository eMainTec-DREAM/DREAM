package dream.work.cal.pminsappr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방점검계획승인 공통 DTO
 * @author  kim21017
 * @version $Id$
 * @since   1.0
 * 
 */
public class WorkCalPmInsApprCommonDTO extends BaseDTO
{
	/** 계획승인 ID */
	private String pmInsSchedApprId				= "";
	/** filter- 작성일자 시작일 */
	private String filterStartDate				= "";
	/** filter- 작성일자 종료일 */
	private String filterEndDate				= "";
	/** filter- 부서ID */
	private String filterDeptId					= "";
	/** filter- 부서명 */
	private String filterDeptDesc				= "";
	/** filter- 제목 */
	private String filterDesc					= "";
	/** filter- 공장ID */
	private String filterPlantId				= "";
	/** filter- 공장명 */
	private String filterPlantDesc				= "";
	/** filter - 년 */
	private String filterYyyy					= "";
	/** filter - 년월 */
	private String filterYyyymm					= "";
	/** filter - 점검계획승인 구분 ID */
	private String filterPminsschedapprType		= "";
	/** filter- 점검계획승인 구분 명 */
	private String filterPminsschedapprTypeDesc	= "";
	/** filter- 점검계획승인 번호 */
	private String filterPmInsSchedApprNo		= "";
	
	/** 점검계획 승인 구분 */
	private String pminsschedapprType           = "";
	/** 점검계획 상태  */
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
