package dream.invt.rpt.invtcateg.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���ڱ��км� �� dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class InvtRptInvtCategDetailListDTO extends BaseDTO
{
    /** ���ڿϷ����� From */
    private String fromDate  		= "";
    /** ���ڿϷ����� To */
    private String toDate  			= "";
    /** ���� ID */
    private String plantId  		= "";
    /** ���� Desc */
    private String plantDesc		= "";
	/** ���μ� ID */
    private String usageDeptId   	= "";
    /** ���μ� Desc */
    private String usageDeptDesc   	= "";
	/** ���ڱ��� ID */
    private String invtCategId  	= "";
    /** ���ڱ��� Desc */
    private String invtCategDesc  	= "";
	
	public String getPlantId() {
		return plantId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = CommonUtil.convertDate(fromDate);
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = CommonUtil.convertDate(toDate);
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
	public String getUsageDeptId() {
		return usageDeptId;
	}
	public void setUsageDeptId(String usageDeptId) {
		this.usageDeptId = usageDeptId;
	}
	public String getUsageDeptDesc() {
		return usageDeptDesc;
	}
	public void setUsageDeptDesc(String usageDeptDesc) {
		this.usageDeptDesc = usageDeptDesc;
	}
	public String getInvtCategId() {
		return invtCategId;
	}
	public void setInvtCategId(String invtCategId) {
		this.invtCategId = invtCategId;
	}
	public String getInvtCategDesc() {
		return invtCategDesc;
	}
	public void setInvtCategDesc(String invtCategDesc) {
		this.invtCategDesc = invtCategDesc;
	}
}