package dream.consult.program.report.file.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 출력물 설정 파일 - 목록 DTO 
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
	/** File 명 */
	private String fileDesc   	     		= "";
	/** Report 종류 ID */
	private String reportFileTypeId     	= "";
	/** Report 종류 명 */
	private String reportFileType		   	= "";
	/** 작성일자 */
	private String repRegDate			   	= "";
	/** 사용여부 */
	private String isUse				   	= "";
	/** 작성자 */
	private String writeBy				   	= "";
	/** 비고 */
	private String remark				   	= "";
	/** 서버 주소 */
	private String serverUrl			   	= "";
	/** Report File 명 */
	private String reportFileName		   	= "";
	/** Query File 명 */
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
