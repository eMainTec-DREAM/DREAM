package dream.work.pm.std.dto;

import common.bean.BaseDTO;
/**
 * ����ǥ�ذ� Ÿ��
 * @author kim21017
 * @version $Id: WorkPmStdCalibCommonDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkPmStdCalibCommonDTO extends BaseDTO
{
	/** ����ǥ�ذ� Ÿ�� ID */
	private String pmCalibStdTpId					= "";
	/** Filter ����ǥ�ذ� No */
	private String filterPmCalibStdTpNo				= "";
	/** Filter �������� ID */
	private String filterPmcTypeId					= "";
	/** Filter �������� �� */
	private String filterPmcTypeDesc				= "";
	
	// �ߺ�Ȯ�ο� id
	private String validId							= "";
	// �ߺ�Ȯ�ο� �������� id
	private String validPmcTypeId					= "";
	
	public String getFilterPmCalibStdTpNo() {
		return filterPmCalibStdTpNo;
	}
	public String getValidId() {
		return validId;
	}
	public void setValidId(String validId) {
		this.validId = validId;
	}
	public String getValidPmcTypeId() {
		return validPmcTypeId;
	}
	public void setValidPmcTypeId(String validPmcTypeId) {
		this.validPmcTypeId = validPmcTypeId;
	}
	public void setFilterPmCalibStdTpNo(String filterPmCalibStdTpNo) {
		this.filterPmCalibStdTpNo = filterPmCalibStdTpNo;
	}
	public String getPmCalibStdTpId() {
		return pmCalibStdTpId;
	}
	public void setPmCalibStdTpId(String pmCalibStdTpId) {
		this.pmCalibStdTpId = pmCalibStdTpId;
		super.setAuditKey(pmCalibStdTpId);
	}
	public String getFilterPmcTypeId() {
		return filterPmcTypeId;
	}
	public void setFilterPmcTypeId(String filterPmcTypeId) {
		this.filterPmcTypeId = filterPmcTypeId;
	}
	public String getFilterPmcTypeDesc() {
		return filterPmcTypeDesc;
	}
	public void setFilterPmcTypeDesc(String filterPmcTypeDesc) {
		this.filterPmcTypeDesc = filterPmcTypeDesc;
	}
	
	
}
