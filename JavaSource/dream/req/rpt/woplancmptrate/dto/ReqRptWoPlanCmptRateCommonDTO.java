package dream.req.rpt.woplancmptrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업의뢰 계획대비 실행 비율 목록 - 공통 DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class ReqRptWoPlanCmptRateCommonDTO extends BaseDTO
{
	/** 시작일자 */ 
    private String filterStartDate           = "";
    /** 종료일자 */ 
    private String filterEndDate             = "";
    /** 공장 ID */ 
    private String filterPlantId             = "";
    /** 공장명 */ 
    private String filterPlantDesc           = "";
    
    
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
