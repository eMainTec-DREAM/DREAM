package dream.consult.comp.config.dto;

import common.bean.BaseDTO;

/**
 * 시스템 환경변수 공통 DTO
 * @author  syyang
 * @version $Id: ConsultCompConfigCommonDTO.java,v 1.0 2015/12/02 09:13:08 syyang Exp $
 * @since   1.0
 * 
 */
public class ConsultCompConfigCommonDTO extends BaseDTO
{
	/** config id */
	private String compconfigId 	= "";
	/** 필터 설명 */
	private String filterDesc 		= "";
	
	
	/** 회사코드 */
	private String compNo = "";
	private String filterCompNo = "";
	private String filterCompDesc = "";
	
	
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
	public String getFilterCompDesc() {
		return filterCompDesc;
	}
	public void setFilterCompDesc(String filterCompDesc) {
		this.filterCompDesc = filterCompDesc;
	}
	public String getCompconfigId() {
		return compconfigId;
	}
	public void setCompconfigId(String compconfigId) {
		this.compconfigId = compconfigId;
	}
	public String getFilterDesc() {
		return filterDesc;
	}
	public void setFilterDesc(String filterDesc) {
		this.filterDesc = filterDesc;
	}
}
