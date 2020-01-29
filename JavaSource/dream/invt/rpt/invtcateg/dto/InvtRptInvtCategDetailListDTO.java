package dream.invt.rpt.invtcateg.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 투자구분분석 상세 dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class InvtRptInvtCategDetailListDTO extends BaseDTO
{
    /** 투자완료일자 From */
    private String fromDate  		= "";
    /** 투자완료일자 To */
    private String toDate  			= "";
    /** 공장 ID */
    private String plantId  		= "";
    /** 공장 Desc */
    private String plantDesc		= "";
	/** 사용부서 ID */
    private String usageDeptId   	= "";
    /** 사용부서 Desc */
    private String usageDeptDesc   	= "";
	/** 투자구분 ID */
    private String invtCategId  	= "";
    /** 투자구분 Desc */
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