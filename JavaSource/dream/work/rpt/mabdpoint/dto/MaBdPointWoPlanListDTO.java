package dream.work.rpt.mabdpoint.dto;

import common.bean.BaseDTO;

/**
 * 이상점검조치 - 작업계획  DTO
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaBdPointWoPlanListDTO extends BaseDTO
{
	/** 점검이상값 ID */
	private String woNgPointId		= "";
	/** 이상점검응답 ID */
	private String woNgPointResId	= "";
	/** wkorId */
	private String wkorId			= "";
	/** wkorId */
	private String woDesc			= "";
	/** 작업요청 ID */
	private String woReqId			= "";
    /** 작업요청 응답내용 ID */
    private String woReqResId       = "";
    /** 작업상태 */
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
