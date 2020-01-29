package dream.work.pm.std.dto;

import common.bean.BaseDTO;
/**
 * ����ǥ�ذ� LOV DTO
 * @author kim21017
 * @version $Id: WorkPmStdCalibValLovDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkPmStdCalibValLovDTO extends BaseDTO
{
	/**   ����ǥ�ذ� ID */
	private String pmCalibStdTpId				= "";
	/**   ����ǥ�ذ� �� */
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
