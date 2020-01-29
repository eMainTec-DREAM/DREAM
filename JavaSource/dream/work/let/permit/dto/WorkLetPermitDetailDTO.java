package dream.work.let.permit.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 안전작업 - 안전작업허가서 작업유형 상세 DTO
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public class WorkLetPermitDetailDTO extends BaseDTO
{
	/** 안전작업허가서 유형 ID */
	private String woLetListId			= "";
	/** 안전작업허가서 유형 NO */
	private String woLetListNo			= "";
	/** 안전작업허가서유형 진행상태 */
	private String woLetListStatus		= "";
	/** 안전작업허가서유형 진행상태 명 */
	private String woLetListStatusDesc	= "";
	/** 안전작업유형 */
	private String woLetCtgType			= "";
	/** 안전작업유형 명 */
	private String woLetCtgTypeDesc		= "";
	/** 작업장소  */
	private String place				= "";
	/** 안적작업허가기간 FROM 작업일자 */
	private String startDate 			= "";
	/** 안적작업허가기간 FROM 작업시간 */
	private String startTime 			= "";
	/** 안적작업허가기간 TO 작업일자 */
	private String endDate 				= "";
	/** 안적작업허가기간 TO 작업시간 */
	private String endTime 				= "";
	/** 발행일자 */
	private String woLetDate	 		= "";
	/** 비고 */
	private String remark 				= "";
	
	/** 최초생성 시간 */
	private String creTime 			= "";
	/** 마지막 수정일자 */
	private String updTime 			= "";
	
	
	public String getWoLetListId() {
		return woLetListId;
	}
	public void setWoLetListId(String woLetListId) {
		this.woLetListId = woLetListId;
		super.setAuditKey(woLetListId);
	}
	public String getWoLetListNo() {
		return woLetListNo;
	}
	public void setWoLetListNo(String woLetListNo) {
		this.woLetListNo = woLetListNo;
	}
	public String getWoLetListStatus() {
		return woLetListStatus;
	}
	public void setWoLetListStatus(String woLetListStatus) {
		this.woLetListStatus = woLetListStatus;
	}
	public String getWoLetListStatusDesc() {
		return woLetListStatusDesc;
	}
	public void setWoLetListStatusDesc(String woLetListStatusDesc) {
		this.woLetListStatusDesc = woLetListStatusDesc;
	}
	public String getWoLetCtgType() {
		return woLetCtgType;
	}
	public void setWoLetCtgType(String woLetCtgType) {
		this.woLetCtgType = woLetCtgType;
	}
	public String getWoLetCtgTypeDesc() {
		return woLetCtgTypeDesc;
	}
	public void setWoLetCtgTypeDesc(String woLetCtgTypeDesc) {
		this.woLetCtgTypeDesc = woLetCtgTypeDesc;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = CommonUtil.convertDate(startDate);
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = CommonUtil.convertTime(startTime);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = CommonUtil.convertTime(endTime);
	}
	public String getWoLetDate() {
		return woLetDate;
	}
	public void setWoLetDate(String woLetDate) {
		this.woLetDate = CommonUtil.convertDate(woLetDate);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreTime() {
		return creTime;
	}
	public void setCreTime(String creTime) {
		this.creTime = CommonUtil.convertDateTime(creTime);
	}
	public String getUpdTime() {
		return updTime;
	}
	public void setUpdTime(String updTime) {
		this.updTime = CommonUtil.convertDateTime(updTime);
	}
	
}