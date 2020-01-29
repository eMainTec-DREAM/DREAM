package dream.part.rpt.ptusehist.dto;

import common.bean.BaseDTO;

/**
 * ��ǰ���м� - Detail DTO
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 *
 */
public class PartRptPtUseHistDetailDTO extends BaseDTO
{
	/** �����ȣ ID */
	private String partId 		= "";
	/** �����ȣ */
	private String partNo	 	= "";
	/** ���� */
	private String partDesc	 	= "";
	
	/** ����(����) */
    private String startDate    = "";
    /** ����(��) */
    private String endDate    	= "";
	/** �μ� ID*/
	private String deptId 		= "";
	/** �μ� */
	private String deptDesc 	= "";
	/** ���� ID*/
	private String plantId 		= "";
	/** ���� */
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
