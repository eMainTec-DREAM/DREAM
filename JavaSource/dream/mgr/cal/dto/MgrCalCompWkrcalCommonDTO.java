package dream.mgr.cal.dto;

import common.bean.BaseDTO;

/**
 * �ٹ��ϴ޷¼��� ���� DTO
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalCommonDTO.java,v 1.0 2015/12/02 09:13:08 euna0207 Exp $
 * @since   1.0
 *
 */
public class MgrCalCompWkrcalCommonDTO extends BaseDTO
{
	/** ȸ�缳�� ID */
	private String compNo 				= "";
	/** �ٹ��޷� ID */
	private String wrkcalListId 		= "";
	/** ���� ȸ�缳�� No */
	private String filterWrkcalNo 		= "";
	
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
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
