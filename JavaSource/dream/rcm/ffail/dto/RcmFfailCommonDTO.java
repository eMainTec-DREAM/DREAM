package dream.rcm.ffail.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 질의 공통 DTO
 * @author  kim21017
 * @version $Id: RcmFfailCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class RcmFfailCommonDTO extends BaseDTO
{
	/** 징의 id */
	private String rcmFuncId 				= "";
	/** 필터 System 분석 ID  */
	private String filterRcmListId			= "";
	/** 필터 System 분석 명  */
	private String filterRcmDesc			= "";
	
	
	public String getRcmFuncId() {
		return rcmFuncId;
	}
	public void setRcmFuncId(String rcmFuncId) {
		this.rcmFuncId = rcmFuncId;
		super.setAuditKey(rcmFuncId);
	}
	public String getFilterRcmListId() {
		return filterRcmListId;
	}
	public void setFilterRcmListId(String filterRcmListId) {
		this.filterRcmListId = filterRcmListId;
	}
	public String getFilterRcmDesc() {
		return filterRcmDesc;
	}
	public void setFilterRcmDesc(String filterRcmDesc) {
		this.filterRcmDesc = filterRcmDesc;
	}
	
}
