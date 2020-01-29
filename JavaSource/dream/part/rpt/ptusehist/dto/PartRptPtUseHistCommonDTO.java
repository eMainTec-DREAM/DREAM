package dream.part.rpt.ptusehist.dto;

import common.bean.BaseDTO;

/**
 * ��ǰ���м� - ���� DTO
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 *
 */
public class PartRptPtUseHistCommonDTO extends BaseDTO
{
	/** �����ȣ ID */
	private String partId 				= "";
	
	/** ����(����) */
    private String filterStartDate    	= "";
    /** ����(��) */
    private String filterEndDate    	= "";
	/**Filter �μ� ID*/
	private String filterDeptId 		= "";
	/**Filter �μ� */
	private String filterDeptDesc 		= "";
	/**Filter ���� ID*/
	private String filterPlantId 		= "";
	/**Filter ���� */
	private String filterPlantDesc	 	= "";
	/**Filter ���� ID*/
	private String filterPartId 		= "";
	/**Filter ���� */
	private String filterPartDesc	 	= "";
	
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = filterStartDate;
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = filterEndDate;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
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
	public String getFilterPartId() {
		return filterPartId;
	}
	public void setFilterPartId(String filterPartId) {
		this.filterPartId = filterPartId;
	}
	public String getFilterPartDesc() {
		return filterPartDesc;
	}
	public void setFilterPartDesc(String filterPartDesc) {
		this.filterPartDesc = filterPartDesc;
	}
	
}
