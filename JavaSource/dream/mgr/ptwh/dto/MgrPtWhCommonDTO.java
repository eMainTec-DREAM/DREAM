package dream.mgr.ptwh.dto;

import common.bean.BaseDTO;
/**
 * 부품창고 - 공통 DTO
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 *
 */
public class MgrPtWhCommonDTO extends BaseDTO
{
	/**창고 ID*/
	private String wcodeId 			= "";

	/**Filter 창고 ID */
	private String filterWcodeId	= "";
	/**Filter 창고명 */
	private String filterWName	 	= "";
	/**Filter 공장 ID*/
	private String filterPlantId 	= "";
	/**Filter 공장 */
	private String filterPlantDesc	= "";
	
	
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
		super.setAuditKey(wcodeId);
	}
	public String getFilterWcodeId() {
		return filterWcodeId;
	}
	public void setFilterWcodeId(String filterWcodeId) {
		this.filterWcodeId = filterWcodeId;
	}
	public String getFilterWName() {
		return filterWName;
	}
	public void setFilterWName(String filterWName) {
		this.filterWName = filterWName;
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
