package dream.consult.program.btn.dto;

import common.bean.BaseDTO;

/**
 * 버튼 공통 DTO
 * @author  kim21017
 * @version $Id: MaBtnMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaBtnMngCommonDTO extends BaseDTO
{
	/** 버튼 ID */
	private String buttonId 				= "";
	/** 필터 버튼 No */
	private String filterButtonNo 			= "";
	/** 필터 버튼 명 */
	private String filterButtonDesc 		= "";

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
	public String getFilterButtonNo() {
		return filterButtonNo;
	}

	public void setFilterButtonNo(String filterButtonNo) {
		this.filterButtonNo = filterButtonNo;
	}

	public String getFilterButtonDesc() {
		return filterButtonDesc;
	}

	public void setFilterButtonDesc(String filterButtonDesc) {
		this.filterButtonDesc = filterButtonDesc;
	}
}
