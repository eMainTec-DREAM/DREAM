package dream.consult.program.error.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * Error Page - Detail DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class ConsultPgmErrorDetailDTO extends BaseDTO
{
	/** Error Log ID*/
	private String errorLogId 			= "";
	
	/** Error Log No*/
	private String errorLogNo 			= "";
	/** 일자*/
	private String errorDate 			= "";
	/** 회사/UserNo */
	private String compUser 			= "";
	/** url */
	private String url 					= "";
	/** 확인여부 id */
	private String checkYnId 			= "";
	/** 확인여부 */
	private String checkYn 				= "";
	/** 확인일자 */
	private String checkDate 			= "";
	/** 비고 */
	private String remark 				= "";
	/** error log */
	private String errorlog 			= "";
	
	public String getErrorLogId() {
		return errorLogId;
	}
	public void setErrorLogId(String errorLogId) {
		this.errorLogId = errorLogId;
	}
	public String getErrorLogNo() {
		return errorLogNo;
	}
	public void setErrorLogNo(String errorLogNo) {
		this.errorLogNo = errorLogNo;
	}
	public String getErrorDate() {
		return errorDate;
	}
	public void setErrorDate(String errorDate) {
		this.errorDate = CommonUtil.convertDateTime(errorDate);
	}
	public String getCompUser() {
		return compUser;
	}
	public void setCompUser(String compUser) {
		this.compUser = compUser;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCheckYnId() {
		return checkYnId;
	}
	public void setCheckYnId(String checkYnId) {
		this.checkYnId = checkYnId;
	}
	public String getCheckYn() {
		return checkYn;
	}
	public void setCheckYn(String checkYn) {
		this.checkYn = checkYn;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = CommonUtil.convertDateTime(checkDate);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getErrorlog() {
		return errorlog;
	}
	public void setErrorlog(String errorlog) {
		this.errorlog = errorlog;
	}
}
