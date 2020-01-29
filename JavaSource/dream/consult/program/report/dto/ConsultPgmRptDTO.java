package dream.consult.program.report.dto;

import common.bean.BaseDTO;

/**
 * Report List ���� DTO 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class ConsultPgmRptDTO extends BaseDTO
{
	/** ID */
	private String rptListId        		= "";
    /** ���� - ��¹� # */
    private String filterReportNo	      	= "";  
	/** ���� - ReportFile ���� */
	private String filterReportFileType		= "";
	/** ���� - ReportFile ������ */
	private String filterReportFileTypeDesc	= "";
	/** ���� - ReportFile �� */
	private String filterReportName			= "";
	
	/** ��¹� # */
	private String reportNo         		= "";
	/** ��뿩�� */
	private String isUse         			= "";
	/** ��¹� �� */
	private String reportName         		= "";
	/** ��� */
	private String remark	         		= "";
	
	public String getRptListId() {
		return rptListId;
	}
	public void setRptListId(String rptListId) {
		this.rptListId = rptListId;
	}
	public String getFilterReportNo() {
		return filterReportNo;
	}
	public void setFilterReportNo(String filterReportNo) {
		this.filterReportNo = filterReportNo;
	}
	public String getFilterReportFileType() {
		return filterReportFileType;
	}
	public void setFilterReportFileType(String filterReportFileType) {
		this.filterReportFileType = filterReportFileType;
	}
	public String getFilterReportFileTypeDesc() {
		return filterReportFileTypeDesc;
	}
	public void setFilterReportFileTypeDesc(String filterReportFileTypeDesc) {
		this.filterReportFileTypeDesc = filterReportFileTypeDesc;
	}
	public String getFilterReportName() {
		return filterReportName;
	}
	public void setFilterReportName(String filterReportName) {
		this.filterReportName = filterReportName;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
