package dream.consult.program.report.file.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��¹� ���� ���� - ��� DTO 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class ConsultPgmRptFileDTO extends BaseDTO
{
	/** Report ID */
	private String rptListId        		= "";
	/** ID */
	private String rptFileId        		= "";
	/** File �� */
	private String fileDesc   	     		= "";
	/** Report ���� ID */
	private String reportFileTypeId     	= "";
	/** Report ���� �� */
	private String reportFileType		   	= "";
	/** �ۼ����� */
	private String repRegDate			   	= "";
	/** ��뿩�� */
	private String isUse				   	= "";
	/** �ۼ��� */
	private String writeBy				   	= "";
	/** ��� */
	private String remark				   	= "";
	/** ���� �ּ� */
	private String serverUrl			   	= "";
	/** Report File �� */
	private String reportFileName		   	= "";
	/** Query File �� */
	private String queryFileName		   	= "";
	
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getReportFileName() {
		return reportFileName;
	}
	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}
	public String getQueryFileName() {
		return queryFileName;
	}
	public void setQueryFileName(String queryFileName) {
		this.queryFileName = queryFileName;
	}
	public String getRptListId() {
		return rptListId;
	}
	public void setRptListId(String rptListId) {
		this.rptListId = rptListId;
	}
	public String getRptFileId() {
		return rptFileId;
	}
	public void setRptFileId(String rptFileId) {
		this.rptFileId = rptFileId;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	public String getReportFileType() {
		return reportFileType;
	}
	public void setReportFileType(String reportFileType) {
		this.reportFileType = reportFileType;
	}
	public String getReportFileTypeId() {
		return reportFileTypeId;
	}
	public void setReportFileTypeId(String reportFileTypeId) {
		this.reportFileTypeId = reportFileTypeId;
	}
	public String getRepRegDate() {
		return repRegDate;
	}
	public void setRepRegDate(String repRegDate) {
		this.repRegDate = CommonUtil.convertDate(repRegDate);
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getWriteBy() {
		return writeBy;
	}
	public void setWriteBy(String writeBy) {
		this.writeBy = writeBy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
