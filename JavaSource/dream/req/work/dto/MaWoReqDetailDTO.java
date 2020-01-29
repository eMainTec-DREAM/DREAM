package dream.req.work.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 *  상세 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaWoReqDetailDTO extends BaseDTO
{
	/** 작업요청 ID */
	private String woReqId				= "";
	/** 작업요청 No */
	private String woReqNo				= "";
	/** 진행상태 ID */
	private String woReqStatusId		= "";
	/** 진행상태 명 */
	private String woReqStatusDesc		= "";
	/** 요청일자 */
	private String reqDate				= "";
	/** 요청자ID */
	private String reqEmpId				= "";
	/** 요청부서ID */
	private String reqDeptId			= "";
	/** 요청부서 DESC */
	private String reqDeptDesc			= "";
	/** 요청자명 */
	private String reqEmpDesc			= "";
	/** 요청자 전화번호 */
	private String reqPhone				= "";
	/** 요청자 이메일 */
	private String reqEmail				= "";
	/** 위치Id */
	private String reqEqLocId			= "";
	/** 위치명 */
	private String reqEqLocDesc			= "";
	/** 설비Id */
	private String reqEquipId			= "";
	/** 설비No */
	private String reqEquipNo			= "";
	/** 설비명 */
	private String reqEquipDesc			= "";
	/** 요청명 */
	private String reqDesc				= "";
	/** 요청상세내용 */
	private String reqRequest			= "";
	/** 처리부서id */
	private String recDeptId 			= "";
	/** 처리부서명 */
	private String recDeptDesc 			= "";
	/** 작업그룹Id */
	private String recWkCtrId			= "";
	/** 작업그룹명 */
	private String recWkCtrDesc			= "";
	/** 처리부서담당자 id */
	private String recEmpId 			= "";
	/** 처리부서담당자 명 */
	private String recEmpName 			= "";
	/** 우선순위 id */
	private String reqPriorityId 		= "";
	/** 우선순위 명 */
	private String reqPriorityDesc 		= "";
	/** 처리요청일 */
	private String reqComDate	 		= "";
	/** 검토내용 */
	private String review		 		= "";
	/** 요청타입id */
	private String eqClassId	 		= "";
	/** 요청타입 명 */
	private String eqClassDesc	 		= "";
	
	/** 투자구분 */
	private String invtCateg			= "";
	/** 투자구분명 */
	private String invtCategDesc		= "";
	/** 분류 */
	private String invtType				= "";
	/** 분류명 */
	private String invtTypeDesc			= "";
	
	/** 고장현상 */
	private String moCd					= "";
	/** 고장현상명 */
	private String moCdDesc				= "";
	/** 현상설명 */
	private String moDesc				= "";

	/** 공장  */
	private String plantId				= "";
	/** 공장명  */
	private String plantDesc			= "";
	
	/** 고장발생일자  */
	private String eqDnDate				= "";
	/** 고장발생시간  */
	private String eqDnTime				= "";
	
	/** 작업요청 Alarm Id  */
	private String alarmListId			= "";
	
	/** 작업요청발생구분 ID */
	private String reqChannelId			= "";
	/** 작업요청발생구분 DESC */
	private String reqChannelDesc		= "";
	/** 부위 ID */
	private String reqEqAsmbId			= "";
	/** 부위 DESC */
	private String reqEqAsmbDesc		= "";
	
	public String getReqEqAsmbId() {
		return reqEqAsmbId;
	}
	public void setReqEqAsmbId(String reqEqAsmbId) {
		this.reqEqAsmbId = reqEqAsmbId;
	}
	public String getReqEqAsmbDesc() {
		return reqEqAsmbDesc;
	}
	public void setReqEqAsmbDesc(String reqEqAsmbDesc) {
		this.reqEqAsmbDesc = reqEqAsmbDesc;
	}
	public String getAlarmListId() {
		return alarmListId;
	}
	public void setAlarmListId(String alarmListId) {
		this.alarmListId = alarmListId;
	}
	
	public String getReqDeptDesc() {
		return reqDeptDesc;
	}
	public void setReqDeptDesc(String reqDeptDesc) {
		this.reqDeptDesc = reqDeptDesc;
	}
	public String getReqChannelId() {
		return reqChannelId;
	}
	public void setReqChannelId(String reqChannelId) {
		this.reqChannelId = reqChannelId;
	}
	public String getReqChannelDesc() {
		return reqChannelDesc;
	}
	public void setReqChannelDesc(String reqChannelDesc) {
		this.reqChannelDesc = reqChannelDesc;
	}
	
	public String getEqDnDate() {
		return eqDnDate;
	}
	public void setEqDnDate(String eqDnDate) {
		this.eqDnDate = CommonUtil.convertDate(eqDnDate);
	}
	public String getEqDnTime() {
		return eqDnTime;
	}
	public void setEqDnTime(String eqDnTime) {
		this.eqDnTime = CommonUtil.convertTime(eqDnTime);
	}
	public String getReqEquipNo()
    {
        return reqEquipNo;
    }
    public void setReqEquipNo(String reqEquipNo)
    {
        this.reqEquipNo = reqEquipNo;
    }
    public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getMoCd() {
		return moCd;
	}
	public void setMoCd(String moCd) {
		this.moCd = moCd;
	}
	public String getMoCdDesc() {
		return moCdDesc;
	}
	public void setMoCdDesc(String moCdDesc) {
		this.moCdDesc = moCdDesc;
	}
	public String getMoDesc() {
		return moDesc;
	}
	public void setMoDesc(String moDesc) {
		this.moDesc = moDesc;
	}
	public String getInvtType() {
		return invtType;
	}
	public void setInvtType(String invtType) {
		this.invtType = invtType;
	}
	public String getInvtTypeDesc() {
		return invtTypeDesc;
	}
	public void setInvtTypeDesc(String invtTypeDesc) {
		this.invtTypeDesc = invtTypeDesc;
	}
	public String getInvtCateg() {
		return invtCateg;
	}
	public void setInvtCateg(String invtCateg) {
		this.invtCateg = invtCateg;
	}
	public String getInvtCategDesc() {
		return invtCategDesc;
	}
	public void setInvtCategDesc(String invtCategDesc) {
		this.invtCategDesc = invtCategDesc;
	}
	public String getEqClassId() {
		return eqClassId;
	}
	public void setEqClassId(String eqClassId) {
		this.eqClassId = eqClassId;
	}
	public String getEqClassDesc() {
		return eqClassDesc;
	}
	public void setEqClassDesc(String eqClassDesc) {
		this.eqClassDesc = eqClassDesc;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getReqPriorityId() {
		return reqPriorityId;
	}
	public void setReqPriorityId(String reqPriorityId) {
		this.reqPriorityId = reqPriorityId;
	}
	public String getReqPriorityDesc() {
		return reqPriorityDesc;
	}
	public void setReqPriorityDesc(String reqPriorityDesc) {
		this.reqPriorityDesc = reqPriorityDesc;
	}
	public String getReqComDate() {
		return reqComDate;
	}
	public void setReqComDate(String reqComDate) {
		this.reqComDate = CommonUtil.convertDate(reqComDate);
	}
	public String getRecDeptId() {
		return recDeptId;
	}
	public void setRecDeptId(String recDeptId) {
		this.recDeptId = recDeptId;
	}
	public String getRecDeptDesc() {
		return recDeptDesc;
	}
	public void setRecDeptDesc(String recDeptDesc) {
		this.recDeptDesc = recDeptDesc;
	}
	public String getRecWkCtrId() {
		return recWkCtrId;
	}
	public void setRecWkCtrId(String recWkCtrId) {
		this.recWkCtrId = recWkCtrId;
	}
	public String getRecWkCtrDesc() {
		return recWkCtrDesc;
	}
	public void setRecWkCtrDesc(String recWkCtrDesc) {
		this.recWkCtrDesc = recWkCtrDesc;
	}
	public String getRecEmpId() {
		return recEmpId;
	}
	public void setRecEmpId(String recEmpId) {
		this.recEmpId = recEmpId;
	}
	public String getRecEmpName() {
		return recEmpName;
	}
	public void setRecEmpName(String recEmpName) {
		this.recEmpName = recEmpName;
	}
	public String getReqDeptId() {
		return reqDeptId;
	}
	public void setReqDeptId(String reqDeptId) {
		this.reqDeptId = reqDeptId;
	}
	public String getWoReqId() {
		return woReqId;
	}
	public void setWoReqId(String woReqId) {
		this.woReqId = woReqId;
		super.setAuditKey(woReqId);
	}
	public String getWoReqNo() {
		return woReqNo;
	}
	public void setWoReqNo(String woReqNo) {
		this.woReqNo = woReqNo;
	}
	public String getWoReqStatusId() {
		return woReqStatusId;
	}
	public void setWoReqStatusId(String woReqStatusId) {
		this.woReqStatusId = woReqStatusId;
	}
	public String getWoReqStatusDesc() {
		return woReqStatusDesc;
	}
	public void setWoReqStatusDesc(String woReqStatusDesc) {
		this.woReqStatusDesc = woReqStatusDesc;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = CommonUtil.convertDate(reqDate);
	}
	public String getReqEmpId() {
		return reqEmpId;
	}
	public void setReqEmpId(String reqEmpId) {
		this.reqEmpId = reqEmpId;
	}
	public String getReqEmpDesc() {
		return reqEmpDesc;
	}
	public void setReqEmpDesc(String reqEmpDesc) {
		this.reqEmpDesc = reqEmpDesc;
	}
	public String getReqPhone() {
		return reqPhone;
	}
	public void setReqPhone(String reqPhone) {
		this.reqPhone = reqPhone;
	}
	public String getReqEmail() {
		return reqEmail;
	}
	public void setReqEmail(String reqEmail) {
		this.reqEmail = reqEmail;
	}
	public String getReqEqLocId() {
		return reqEqLocId;
	}
	public void setReqEqLocId(String reqEqLocId) {
		this.reqEqLocId = reqEqLocId;
	}
	public String getReqEqLocDesc() {
		return reqEqLocDesc;
	}
	public void setReqEqLocDesc(String reqEqLocDesc) {
		this.reqEqLocDesc = reqEqLocDesc;
	}
	public String getReqEquipId() {
		return reqEquipId;
	}
	public void setReqEquipId(String reqEquipId) {
		this.reqEquipId = reqEquipId;
	}
	public String getReqEquipDesc() {
		return reqEquipDesc;
	}
	public void setReqEquipDesc(String reqEquipDesc) {
		this.reqEquipDesc = reqEquipDesc;
	}
	public String getReqDesc() {
		return reqDesc;
	}
	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}
	public String getReqRequest() {
		return reqRequest;
	}
	public void setReqRequest(String reqRequest) {
		this.reqRequest = reqRequest;
	}
	
}
