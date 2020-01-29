package dream.consult.comp.terminal.dto;

import common.bean.BaseDTO;

/**
 * �����͹̳� ���� DTO
 * @author  kim21017
 * @version $Id: ConsultCompTerminalExCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 *
 */
public class ConsultCompTerminalExCommonDTO extends BaseDTO
{
	/** ȸ�缳�� ID */
	private String compNo 						= "";
	/** �����͹̳� ID */
	private String accessMnlExId 				= "";
	/** ���� �����͹̳� ���� */
	private String filterDesc					= "";
	/** ���� service type No */
	private String filterServiceTypeId 		= "";
	/** ���� service type �� */
	private String filterServiceTypeDesc 		= "";
	/** ���� comp no */
	private String filterCompNo			 	= "";
	/** ���� comp no �� */
	private String filterCompNoDesc		 	= "";
	
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getFilterCompNo() {
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) {
		this.filterCompNo = filterCompNo;
	}
	
	public String getFilterCompNoDesc() {
		return filterCompNoDesc;
	}
	public void setFilterCompNoDesc(String filterCompNoDesc) {
		this.filterCompNoDesc = filterCompNoDesc;
	}
	public String getAccessMnlExId() {
		return accessMnlExId;
	}
	public void setAccessMnlExId(String accessMnlExId) {
		this.accessMnlExId = accessMnlExId;
	}
	
	public String getFilterDesc() {
		return filterDesc;
	}
	public void setFilterDesc(String filterDesc) {
		this.filterDesc = filterDesc;
	}
	public String getFilterServiceTypeId() {
		return filterServiceTypeId;
	}
	public void setFilterServiceTypeId(String filterServiceTypeId) {
		this.filterServiceTypeId = filterServiceTypeId;
	}
	public String getFilterServiceTypeDesc() {
		return filterServiceTypeDesc;
	}
	public void setFilterServiceTypeDesc(String filterServiceTypeDesc) {
		this.filterServiceTypeDesc = filterServiceTypeDesc;
	}
	
}
