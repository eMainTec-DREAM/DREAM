package dream.consult.comp.terminal.dto;

import common.bean.BaseDTO;

/**
 * 접근터미널 공통 DTO
 * @author  kim21017
 * @version $Id: ConsultCompTerminalCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 *
 */
public class ConsultCompTerminalCommonDTO extends BaseDTO
{
	/** 회사설정 ID */
	private String compNo 						= "";
	/** 접근터미널 ID */
	private String accessMnlId 					= "";
	/** 필터 접근터미널 설명 */
	private String filterDesc					= "";
	/** 필터 service type No */
	private String filterServiceTypeId 			= "";
	/** 필터 service type 명 */
	private String filterServiceTypeDesc 		= "";
	/** 필터 comp no */
	private String filterCompNo			 		= "";
	/** 필터 comp no 명 */
	private String filterCompNoDesc		 		= "";
	
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
	public String getAccessMnlId() {
		return accessMnlId;
	}
	public void setAccessMnlId(String accessMnlId) {
		this.accessMnlId = accessMnlId;
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
