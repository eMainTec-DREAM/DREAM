package dream.consult.comp.cdsys.dto;

import common.bean.BaseDTO;

/**
 * �׸��� DTO
 * @author  kim21017
 * @version $Id: ListTypeCodeDTOList.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class ListTypeCodeDTOList extends BaseDTO
{
	/** �ý��������з�ID */
	private String cdSysMId 					= "";
	/** �ý����ڵ�������ID */
	private String cdSysDId 					= "";
	/** �������� */
	private String isDelCheck					= "";
	
	public String getCdSysMId() {
		return cdSysMId;
	}
	public void setCdSysMId(String cdSysMId) {
		this.cdSysMId = cdSysMId;
	}
	public String getIsDelCheck() {
		return isDelCheck;
	}
	public void setIsDelCheck(String isDelCheck) {
		this.isDelCheck = isDelCheck;
	}
	public String getCdSysDId() {
		return cdSysDId;
	}
	public void setCdSysDId(String cdSysDId) {
		this.cdSysDId = cdSysDId;
	}
}
