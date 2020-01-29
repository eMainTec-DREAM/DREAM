package dream.req.rpt.emwogenrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사후 작업오더 발생률 목록 - 공통 DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class ReqRptEmWoGenRateCommonDTO extends BaseDTO
{
	/** 월 */ 
    private String month	           		 = "";
    /** 공장 ID */ 
    private String plantId             		 = "";
    /** 공장명 */ 
    private String plantDesc           		 = "";
    /** Filter 시작일자 */ 
    private String filterStartDate           = "";
    /** Filter 종료일자 */ 
    private String filterEndDate             = "";
    /** Filter 공장 ID */ 
    private String filterPlantId             = "";
    /** Filter 공장명 */ 
    private String filterPlantDesc           = "";
    
    
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
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
    
}
