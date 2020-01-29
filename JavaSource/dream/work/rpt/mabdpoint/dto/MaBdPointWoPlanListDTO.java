package dream.work.rpt.mabdpoint.dto;

import common.bean.BaseDTO;

/**
 * �̻�������ġ - �۾���ȹ  DTO
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaBdPointWoPlanListDTO extends BaseDTO
{
	/** �����̻� ID */
	private String woNgPointId		= "";
	/** �̻��������� ID */
	private String woNgPointResId	= "";
	/** wkorId */
	private String wkorId			= "";
	/** wkorId */
	private String woDesc			= "";
	/** �۾���û ID */
	private String woReqId			= "";
    /** �۾���û ���䳻�� ID */
    private String woReqResId       = "";
    /** �۾����� */
    private String woStatusId      	= "";
    
	public String getWoStatusId() {
		return woStatusId;
	}
	public void setWoStatusId(String woStatusId) {
		this.woStatusId = woStatusId;
	}
	public String getWoDesc() {
		return woDesc;
	}
	public void setWoDesc(String woDesc) {
		this.woDesc = woDesc;
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
	public String getWkorId() {
		return wkorId;
	}
	public void setWkorId(String wkorId) {
		this.wkorId = wkorId;
	}
	public String getWoReqId() {
		return woReqId;
	}
	public void setWoReqId(String woReqId) {
		this.woReqId = woReqId;
	}
	public String getWoReqResId() {
		return woReqResId;
	}
	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
	}
	
}
