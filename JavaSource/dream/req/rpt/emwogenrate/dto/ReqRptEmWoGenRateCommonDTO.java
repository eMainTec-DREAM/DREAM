package dream.req.rpt.emwogenrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� �۾����� �߻��� ��� - ���� DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class ReqRptEmWoGenRateCommonDTO extends BaseDTO
{
	/** �� */ 
    private String month	           		 = "";
    /** ���� ID */ 
    private String plantId             		 = "";
    /** ����� */ 
    private String plantDesc           		 = "";
    /** Filter �������� */ 
    private String filterStartDate           = "";
    /** Filter �������� */ 
    private String filterEndDate             = "";
    /** Filter ���� ID */ 
    private String filterPlantId             = "";
    /** Filter ����� */ 
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
