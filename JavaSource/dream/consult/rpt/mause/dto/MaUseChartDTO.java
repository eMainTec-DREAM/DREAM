package dream.consult.rpt.mause.dto;

import common.bean.BaseDTO;

/**
 * �����Ȳ DTO
 * @author  kim21017
 * @version $Id: MaUseChartDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaUseChartDTO extends BaseDTO
{
	/** ���� ���ڿ� */
	private String dateArrStr 			= "";
	/** ����-������������ */
	private String filterStartDate 		= "";
	/** ����-���������� */
	private String filterEndDate 		= "";
	
	public String getDateArrStr() {
		return dateArrStr;
	}
	public void setDateArrStr(String dateArrStr) {
		this.dateArrStr = dateArrStr;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = filterStartDate;
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = filterEndDate;
	}
}
