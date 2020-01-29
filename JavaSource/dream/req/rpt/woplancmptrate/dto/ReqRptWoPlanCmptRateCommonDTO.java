package dream.req.rpt.woplancmptrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾��Ƿ� ��ȹ��� ���� ���� ��� - ���� DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class ReqRptWoPlanCmptRateCommonDTO extends BaseDTO
{
	/** �������� */ 
    private String filterStartDate           = "";
    /** �������� */ 
    private String filterEndDate             = "";
    /** ���� ID */ 
    private String filterPlantId             = "";
    /** ����� */ 
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
