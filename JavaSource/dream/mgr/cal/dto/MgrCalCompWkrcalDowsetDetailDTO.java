package dream.mgr.cal.dto;

import common.bean.BaseDTO;

/**
 * �޹����ϼ��� - �� DTO
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDetailDTO.java,v 1.0 2015/12/02 08:44:16 euna0207 Exp $
 * @since   1.0
 *
 */
public class MgrCalCompWkrcalDowsetDetailDTO extends BaseDTO
{
	/** �޹�����ID */
	private String wrkcalDowsetId 		= "";
	
	/** ���� */
	private String dow 					= "";
	/** ���ϸ� */
	private String dowDesc 				= "";
	/** �޹����� */
	private String isOff 				= "";
	
	public String getDow() {
		return dow;
	}
	public void setDow(String dow) {
		this.dow = dow;
	}
	public String getDowDesc() {
		return dowDesc;
	}
	public void setDowDesc(String dowDesc) {
		this.dowDesc = dowDesc;
	}
	public String getIsOff() {
		return isOff;
	}
	public void setIsOff(String isOff) {
		this.isOff = isOff;
	}
	public String getWrkcalDowsetId() {
		return wrkcalDowsetId;
	}
	public void setWrkcalDowsetId(String wrkcalDowsetId) {
		this.wrkcalDowsetId = wrkcalDowsetId;
	}
	
}
