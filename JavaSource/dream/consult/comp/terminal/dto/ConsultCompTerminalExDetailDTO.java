package dream.consult.comp.terminal.dto;

import common.bean.BaseDTO;

/**
 * �����͹̳� - �� DTO
 * @author  kim21017
 * @version $Id: ConsultCompTerminalExDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class ConsultCompTerminalExDetailDTO extends BaseDTO
{

	/** ȸ��code */
	private String compNo 				= "";
	/** ȸ��code desc */
	private String compNoDesc			= "";
	/** �����͹̳� ID */
	private String accessMnlExId 		= "";
	/** service type No */
	private String serviceTypeId 		= "";
	/** service type �� */
	private String serviceTypeDesc 	= "";
	/** �����͹̳�  No */
	private String terminalNo 			= "";
	/** �����͹̳� ���� */
	private String description 		= "";
	/** is use No */
	private String isUseId 			= "";
	/** is use �� */
	private String isUseDesc	 		= "";
	/** �󼼼��� */
	private String remark		 		= "";
	
	
	public String getCompNoDesc() {
		return compNoDesc;
	}
	public void setCompNoDesc(String compNoDesc) {
		this.compNoDesc = compNoDesc;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getAccessMnlExId() {
		return accessMnlExId;
	}
	public void setAccessMnlExId(String accessMnlExId) {
		this.accessMnlExId = accessMnlExId;
	}
	public String getServiceTypeId() {
		return serviceTypeId;
	}
	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	public String getServiceTypeDesc() {
		return serviceTypeDesc;
	}
	public void setServiceTypeDesc(String serviceTypeDesc) {
		this.serviceTypeDesc = serviceTypeDesc;
	}
	public String getTerminalNo() {
		return terminalNo;
	}
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsUseId() {
		return isUseId;
	}
	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}
	public String getIsUseDesc() {
		return isUseDesc;
	}
	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
