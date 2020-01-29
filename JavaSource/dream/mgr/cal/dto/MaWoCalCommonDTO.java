package dream.mgr.cal.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * Working Calendar 공통 DTO
 * @author kim21017
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaWoCalCommonDTO extends BaseDTO
{
    
    /** 카렌다Id */
    private String wrkCalendarId			= "";
    /** filter - 시작일자 */
    private String filterStartDate			= "";
    /** filter - 끝일자 */
    private String filterEndDate			= "";
    /** filter - 공장Id */
    private String filterPlantId			= "";
    /** filter - 공장명 */
    private String filterPlantDesc			= "";
    /** filter - 근무여부 */
    private String filterIsJoin				= "";
    /** filter - 근무달력Id */
    private String filterWrkcalListId			= "";
    /** filter - 근무달력명 */
    private String filterWrkcalListDesc			= "";
    
    /** 선택 Array */
    private String selArray				    = "";
    /** filter - 일자변경 */
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
