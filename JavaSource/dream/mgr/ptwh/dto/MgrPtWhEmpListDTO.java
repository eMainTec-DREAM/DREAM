package dream.mgr.ptwh.dto;

import common.bean.BaseDTO;

/**
 * 부품창고 담당자 - List DTO
 * 
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 */
public class MgrPtWhEmpListDTO extends BaseDTO
{
	/** 창고 ID */
	private String wcodeId		= "";
    /** 창고 담당자 ID */
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
