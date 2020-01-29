package dream.part.rpt.ptusehist.dto;

import common.bean.BaseDTO;

/**
 * 부품사용분석 - Detail DTO
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 *
 */
public class PartRptPtUseHistDetailDTO extends BaseDTO
{
	/** 자재번호 ID */
	private String partId 		= "";
	/** 자재번호 */
	private String partNo	 	= "";
	/** 자재 */
	private String partDesc	 	= "";
	
	/** 일자(시작) */
    private String startDate    = "";
    /** 일자(끝) */
    private String endDate    	= "";
	/** 부서 ID*/
	private String deptId 		= "";
	/** 부서 */
	private String deptDesc 	= "";
	/** 공장 ID*/
	private String plantId 		= "";
	/** 공장 */
	private String plantDesc	= "";
	
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
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
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

}
