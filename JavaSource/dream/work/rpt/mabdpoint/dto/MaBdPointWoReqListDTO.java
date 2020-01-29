package dream.work.rpt.mabdpoint.dto;

import common.bean.BaseDTO;

/**
 * 이상점검조치 - 작업요청 DTO
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaBdPointWoReqListDTO extends BaseDTO
{
	/** 점검이상값 ID */
	private String woNgPointId      = "";
	/** 이상점검응답 ID */
	private String woNgPointResId	= "";
	/** 작업요청 ID */
	private String woReqId			= "";
	/** 작업요청 명 */
	private String woReqDesc		= "";
	
	/** wkorId */
	private String wkorId			= "";
	/** 작업요청상태 */
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
