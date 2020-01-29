package dream.consult.comp.cdsys.dto;

import common.bean.BaseDTO;

/**
 * 그리드 DTO
 * @author  kim21017
 * @version $Id: MaCdSysDTOList.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaCdSysDTOList extends BaseDTO
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
