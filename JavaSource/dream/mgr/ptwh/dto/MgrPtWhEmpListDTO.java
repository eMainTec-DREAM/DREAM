package dream.mgr.ptwh.dto;

import common.bean.BaseDTO;

/**
 * ��ǰâ�� ����� - List DTO
 * 
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 */
public class MgrPtWhEmpListDTO extends BaseDTO
{
	/** â�� ID */
	private String wcodeId		= "";
    /** â�� ����� ID */
    private String whUserId		= "";
    
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getWhUserId() {
		return whUserId;
	}
	public void setWhUserId(String whUserId) {
		this.whUserId = whUserId;
		super.setAuditKey(whUserId);
	}
}
