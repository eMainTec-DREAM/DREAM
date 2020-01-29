package dream.req.work.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 *  �� DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaWoReqDetailDTO extends BaseDTO
{
	/** �۾���û ID */
	private String woReqId				= "";
	/** �۾���û No */
	private String woReqNo				= "";
	/** ������� ID */
	private String woReqStatusId		= "";
	/** ������� �� */
	private String woReqStatusDesc		= "";
	/** ��û���� */
	private String reqDate				= "";
	/** ��û��ID */
	private String reqEmpId				= "";
	/** ��û�μ�ID */
	private String reqDeptId			= "";
	/** ��û�μ� DESC */
	private String reqDeptDesc			= "";
	/** ��û�ڸ� */
	private String reqEmpDesc			= "";
	/** ��û�� ��ȭ��ȣ */
	private String reqPhone				= "";
	/** ��û�� �̸��� */
	private String reqEmail				= "";
	/** ��ġId */
	private String reqEqLocId			= "";
	/** ��ġ�� */
	private String reqEqLocDesc			= "";
	/** ����Id */
	private String reqEquipId			= "";
	/** ����No */
	private String reqEquipNo			= "";
	/** ����� */
	private String reqEquipDesc			= "";
	/** ��û�� */
	private String reqDesc				= "";
	/** ��û�󼼳��� */
	private String reqRequest			= "";
	/** ó���μ�id */
	private String recDeptId 			= "";
	/** ó���μ��� */
	private String recDeptDesc 			= "";
	/** �۾��׷�Id */
	private String recWkCtrId			= "";
	/** �۾��׷�� */
	private String recWkCtrDesc			= "";
	/** ó���μ������ id */
	private String recEmpId 			= "";
	/** ó���μ������ �� */
	private String recEmpName 			= "";
	/** �켱���� id */
	private String reqPriorityId 		= "";
	/** �켱���� �� */
	private String reqPriorityDesc 		= "";
	/** ó����û�� */
	private String reqComDate	 		= "";
	/** ���䳻�� */
	private String review		 		= "";
	/** ��ûŸ��id */
	private String eqClassId	 		= "";
	/** ��ûŸ�� �� */
	private String eqClassDesc	 		= "";
	
	/** ���ڱ��� */
	private String invtCateg			= "";
	/** ���ڱ��и� */
	private String invtCategDesc		= "";
	/** �з� */
	private String invtType				= "";
	/** �з��� */
	private String invtTypeDesc			= "";
	
	/** �������� */
	private String moCd					= "";
	/** ��������� */
	private String moCdDesc				= "";
	/** ���󼳸� */
	private String moDesc				= "";

	/** ����  */
	private String plantId				= "";
	/** �����  */
	private String plantDesc			= "";
	
	/** ����߻�����  */
	private String eqDnDate				= "";
	/** ����߻��ð�  */
	private String eqDnTime				= "";
	
	/** �۾���û Alarm Id  */
	private String alarmListId			= "";
	
	/** �۾���û�߻����� ID */
	private String reqChannelId			= "";
	/** �۾���û�߻����� DESC */
	private String reqChannelDesc		= "";
	/** ���� ID */
	private String reqEqAsmbId			= "";
	/** ���� DESC */
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