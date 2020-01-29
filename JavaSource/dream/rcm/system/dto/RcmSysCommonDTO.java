package dream.rcm.system.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사원 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class RcmSysCommonDTO extends BaseDTO
{
	
	/** 회사코드 */
	private String compNo                  = "";
	/** System 분석Id */
	private String rcmListId               = "";
	/** Filter-분석명 */
	private String filterRcmDesc		   = "";
	/** Filter-시작일자 */
	private String filterStartDate 		   = "";
	/** Filter-종료일자 */
	private String filterEndDate		   = "";
	
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getRcmListId() {
		return rcmListId;
	}
	public void setRcmListId(String rcmListId) {
		this.rcmListId = rcmListId;
		super.setAuditKey(rcmListId);
	}
	public String getFilterRcmDesc() {
		return filterRcmDesc;
	}
	public void setFilterRcmDesc(String filterRcmDesc) {
		this.filterRcmDesc = filterRcmDesc;
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
	
}
