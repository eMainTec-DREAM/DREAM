package dream.work.rpt.mawotype.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾���������Ȳ DTO
 * @author  kim21017
 * @version $Id: MaWoTypeChartDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaWoTypeChartDTO extends BaseDTO
{
	/** ����-������̵� */
	private String filterPlantDesc 		= "";
	/** ����-����� */
	private String filterPlantId 		= "";
	/** ����-��ġ���̵� */
	private String filterEqLocDesc 		= "";
	/** ����-��ġ�� */
	private String filterEqLocId 		= "";
	/** ����-�������̵� */
	private String filterEqCtgDesc 		= "";
	/** ����-������ */
	private String filterEqCtgId 		= "";
	/** ����-���� */
	private String filterStartDate 		= "";
	/** ����-���� */
	private String filterEndDate 		= "";
	
	
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	public String getFilterEqLocId() {
		return filterEqLocId;
	}
	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}
	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = CommonUtil.convertDate(filterStartDate);
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = CommonUtil.convertDate(filterEndDate);
	}
}
