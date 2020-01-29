package dream.consult.program.error.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
/**
 * Error Page - 공통 DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class ConsultPgmErrorCommonDTO extends BaseDTO
{
	/** Error Log ID*/
	private String errorLogId 			= "";
	/**Filter Error 시작 일자*/
    private String filterStartDate      = "";    
    /**Filter Error 종료 일자*/
    private String filterEndDate        = "";    
    /**Filter 확인여부 ID*/
    private String filterIsCheckId 		= "";
    /**Filter 확인여부 DESC*/
    private String filterIsCheckDesc	= "";
    
	public String getErrorLogId() {
		return errorLogId;
	}
	public void setErrorLogId(String errorLogId) {
		this.errorLogId = errorLogId;
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
	public String getFilterIsCheckId() {
		return filterIsCheckId;
	}
	public void setFilterIsCheckId(String filterIsCheckId) {
		this.filterIsCheckId = filterIsCheckId;
	}
	public String getFilterIsCheckDesc() {
		return filterIsCheckDesc;
	}
	public void setFilterIsCheckDesc(String filterIsCheckDesc) {
		this.filterIsCheckDesc = filterIsCheckDesc;
	}
}
