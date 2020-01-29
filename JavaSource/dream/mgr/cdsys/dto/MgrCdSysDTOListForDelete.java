package dream.mgr.cdsys.dto;

import common.bean.BaseDTO;

/**
 * 그리드 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class MgrCdSysDTOListForDelete extends BaseDTO
{
	/** 시스템코드유형상세ID */
	private String cdSysMId 					= "";
	/** 삭제여부 */
	private String isDelCheck					= "";

	public String getIsDelCheck() {
		return isDelCheck;
	}
	public void setIsDelCheck(String isDelCheck) {
		this.isDelCheck = isDelCheck;
	}
	public String getCdSysMId() {
		return cdSysMId;
	}
	public void setCdSysMId(String cdSysMId) {
		this.cdSysMId = cdSysMId;
	}
}
