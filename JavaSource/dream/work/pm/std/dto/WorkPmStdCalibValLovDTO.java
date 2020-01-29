package dream.work.pm.std.dto;

import common.bean.BaseDTO;
/**
 * 교정표준값 LOV DTO
 * @author kim21017
 * @version $Id: WorkPmStdCalibValLovDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkPmStdCalibValLovDTO extends BaseDTO
{
	/**   교정표준값 ID */
	private String pmCalibStdTpId				= "";
	/**   교정표준값 명 */
	private String filterPmCalibStdTpDesc		= "";
	
	public String getPmCalibStdTpId() {
		return pmCalibStdTpId;
	}
	public void setPmCalibStdTpId(String pmCalibStdTpId) {
		this.pmCalibStdTpId = pmCalibStdTpId;
	}
	public String getFilterPmCalibStdTpDesc() {
		return filterPmCalibStdTpDesc;
	}
	public void setFilterPmCalibStdTpDesc(String filterPmCalibStdTpDesc) {
		this.filterPmCalibStdTpDesc = filterPmCalibStdTpDesc;
	}
	
}
