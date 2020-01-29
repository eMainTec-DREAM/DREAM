package dream.work.rpt.mabdpoint.dto;

import common.bean.BaseDTO;

/**
 * �̻�������ġ - �۾���û DTO
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaBdPointWoReqListDTO extends BaseDTO
{
	/** �����̻� ID */
	private String woNgPointId      = "";
	/** �̻��������� ID */
	private String woNgPointResId	= "";
	/** �۾���û ID */
	private String woReqId			= "";
	/** �۾���û �� */
	private String woReqDesc		= "";
	
	/** wkorId */
	private String wkorId			= "";
	/** �۾���û���� */
    private String woReqStatusId   	= "";
    
	public String getWoReqStatusId() {
		return woReqStatusId;
	}
	public void setWoReqStatusId(String woReqStatusId) {
		this.woReqStatusId = woReqStatusId;
	}
	public String getWoNgPointId() {
		return woNgPointId;
	}
	public void setWoNgPointId(String woNgPointId) {
		this.woNgPointId = woNgPointId;
	}
	public String getWoNgPointResId() {
		return woNgPointResId;
	}
	public void setWoNgPointResId(String woNgPointResId) {
		this.woNgPointResId = woNgPointResId;
	}
	public String getWoReqId() {
		return woReqId;
	}
	public void setWoReqId(String woReqId) {
		this.woReqId = woReqId;
	}
	public String getWoReqDesc() {
		return woReqDesc;
	}
	public void setWoReqDesc(String woReqDesc) {
		this.woReqDesc = woReqDesc;
	}
	public String getWkorId() {
		return wkorId;
	}
	public void setWkorId(String wkorId) {
		this.wkorId = wkorId;
	}
	
}
