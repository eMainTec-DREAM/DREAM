package dream.mgr.cdsys.dto;

import common.bean.BaseDTO;

/**
 * �׸��� DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class MgrCdSysDTOListForDelete extends BaseDTO
{
	/** �ý����ڵ�������ID */
	private String cdSysMId 					= "";
	/** �������� */
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
