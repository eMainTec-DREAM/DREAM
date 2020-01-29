package dream.mgr.cal.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * Working Calendar ���� DTO
 * @author kim21017
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaWoCalCommonDTO extends BaseDTO
{
    
    /** ī����Id */
    private String wrkCalendarId			= "";
    /** filter - �������� */
    private String filterStartDate			= "";
    /** filter - ������ */
    private String filterEndDate			= "";
    /** filter - ����Id */
    private String filterPlantId			= "";
    /** filter - ����� */
    private String filterPlantDesc			= "";
    /** filter - �ٹ����� */
    private String filterIsJoin				= "";
    /** filter - �ٹ��޷�Id */
    private String filterWrkcalListId			= "";
    /** filter - �ٹ��޷¸� */
    private String filterWrkcalListDesc			= "";
    
    /** ���� Array */
    private String selArray				    = "";
    /** filter - ���ں��� */
    private String popupChangeDate		    = "";
    
	public String getFilterWrkcalListId() {
		return filterWrkcalListId;
	}
	public void setFilterWrkcalListId(String filterWrkcalListId) {
		this.filterWrkcalListId = filterWrkcalListId;
	}
	public String getFilterWrkcalListDesc() {
		return filterWrkcalListDesc;
	}
	public void setFilterWrkcalListDesc(String filterWrkcalListDesc) {
		this.filterWrkcalListDesc = filterWrkcalListDesc;
	}
	public String getPopupChangeDate() {
		return popupChangeDate;
	}
	public void setPopupChangeDate(String popupChangeDate) {
		this.popupChangeDate = CommonUtil.convertDate(popupChangeDate);
	}
	public String getSelArray() {
		return selArray;
	}
	public void setSelArray(String selArray) {
		this.selArray = selArray;
	}
	public String getWrkCalendarId() {
		return wrkCalendarId;
	}
	public void setWrkCalendarId(String wrkCalendarId) {
		this.wrkCalendarId = wrkCalendarId;
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
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getFilterIsJoin() {
		return filterIsJoin;
	}
	public void setFilterIsJoin(String filterIsJoin) {
		this.filterIsJoin = filterIsJoin;
	}
}
