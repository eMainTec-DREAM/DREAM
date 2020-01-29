package dream.work.pm.std.dto;

import common.bean.BaseDTO;
/**
 * ����ǥ�ذ� - ���� DTO
 * @author kim21017
 * @version $Id: WorkPmStdCalibValListDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkPmStdCalibValListDTO extends BaseDTO
{
	/**   ǥ�ر����� Ÿ�� ID */
	private String pmCalibStdTpId				= "";
	/**   ������ ID*/
	private String pmCalibStdValueId 			= "";
	
	public String getPmCalibStdTpId() {
		return pmCalibStdTpId;
	}
	public void setPmCalibStdTpId(String pmCalibStdTpId) {
		this.pmCalibStdTpId = pmCalibStdTpId;
	}
	public String getPmCalibStdValueId() {
		return pmCalibStdValueId;
	}
	public void setPmCalibStdValueId(String pmCalibStdValueId) {
		this.pmCalibStdValueId = pmCalibStdValueId;
		super.setAuditKey(pmCalibStdValueId);
	}
	
	
}
