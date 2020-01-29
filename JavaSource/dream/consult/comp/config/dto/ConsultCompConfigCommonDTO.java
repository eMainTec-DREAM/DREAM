package dream.consult.comp.config.dto;

import common.bean.BaseDTO;

/**
 * �ý��� ȯ�溯�� ���� DTO
 * @author  syyang
 * @version $Id: ConsultCompConfigCommonDTO.java,v 1.0 2015/12/02 09:13:08 syyang Exp $
 * @since   1.0
 * 
 */
public class ConsultCompConfigCommonDTO extends BaseDTO
{
	/** config id */
	private String compconfigId 	= "";
	/** ���� ���� */
	private String filterDesc 		= "";
	
	
	/** ȸ���ڵ� */
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
