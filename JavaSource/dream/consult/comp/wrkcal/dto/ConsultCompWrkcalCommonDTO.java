package dream.consult.comp.wrkcal.dto;

import common.bean.BaseDTO;

/**
 * ȸ�缳�� ���� DTO
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 *
 */
public class ConsultCompWrkcalCommonDTO extends BaseDTO
{
	/** ȸ�缳�� ID */
	private String compNo 				= "";
	/** �ٹ��޷� ID */
	private String wrkcalListId 		= "";
	/** ���� ȸ�缳�� No */
	private String filterCompNo 		= "";
	/** ���� ȸ��� */
	private String filterCompDesc 		= "";
	/** ���� ȸ�缳�� No */
	private String filterWrkcalNo 		= "";
	
	public String getFilterCompDesc() {
		return filterCompDesc;
	}
	public void setFilterCompDesc(String filterCompDesc) {
		this.filterCompDesc = filterCompDesc;
	}
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
	public String getFilterWrkcalNo() {
		return filterWrkcalNo;
	}
	public void setFilterWrkcalNo(String filterWrkcalNo) {
		this.filterWrkcalNo = filterWrkcalNo;
	}
	public String getWrkcalListId() {
		return wrkcalListId;
	}
	public void setWrkcalListId(String wrkcalListId) {
		this.wrkcalListId = wrkcalListId;
	}
	
}
