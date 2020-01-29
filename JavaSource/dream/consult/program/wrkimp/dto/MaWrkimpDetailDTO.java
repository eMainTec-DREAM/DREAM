package dream.consult.program.wrkimp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaWrkimpDetailDTO extends BaseDTO
{
	
	/** ����ID */
	private String gowrkimpId = "";
	/** ���� ��ȣ */
	private String gowrkimpNo = "";
	/** ���� */
	private String description = "";
	/** �������[�۾���,�۾���,�Ϸ�] */
	private String gowrkimpStatus = "";
	/** �������[�۾���,�۾���,�Ϸ�] */
	private String gowrkimpStatusDesc = "";
	/** �ۼ��ڸ� */
	private String writeByName = "";
	/** �ۼ����� */
	private String writeDate = "";
	/** �����ڸ� */
	private String viewByName = "";
	/** �������� */
	private String viewDate = "";
	/** �۾��������� */
	private String workSdate = "";
	/** �۾��������� */
	private String workEdate = "";
	/** �۾��ڸ� */
	private String workByName = "";
	private String perform = "";
	private String wrkimpSite = "";
	private String wrkimpCreTypeId = "";
	private String wrkimpCreTypeDesc = "";
	private String workImpTime = "";

	
	
	/** Wrkimp Desk ��ȣ */
	private String helpdeskNo = "";
	/** ������� */
	private String helpdeskStatus = "";
	/** ������¸� */
	private String helpdeskStatusDesc = "";
	/** ��û�� */
	private String reqBy = "";
	/** ��û�ڸ� */
	private String reqByName = "";
	/** ��û���� */
	private String reqDate = "";
	/** ��û���� */
	private String request = "";
	/** ȸ���ڵ� */
	private String compNo = "";
	/** Wrkimp Desk ID */
	private String helpdeskId = "";
	
	
//	
//	
//	
//	/** �۾����� */
//	private String perform = "";
//	/** ������ */
//	private String viewBy = "";
//	/** �۾��� */
//	private String workBy = "";
//	/** �ۼ��� */
//	private String writeBy = "";
	
	
	
	
	
	public String getHelpdeskId() {
		return helpdeskId;
	}
	public String getPerform() {
		return perform;
	}
	public void setPerform(String perform) {
		this.perform = perform;
	}
	public String getWrkimpSite() {
		return wrkimpSite;
	}
	public void setWrkimpSite(String wrkimpSite) {
		this.wrkimpSite = wrkimpSite;
	}
	public String getWrkimpCreTypeId() {
		return wrkimpCreTypeId;
	}
	public void setWrkimpCreTypeId(String wrkimpCreTypeId) {
		this.wrkimpCreTypeId = wrkimpCreTypeId;
	}
	public String getWrkimpCreTypeDesc() {
		return wrkimpCreTypeDesc;
	}
	public void setWrkimpCreTypeDesc(String wrkimpCreTypeDesc) {
		this.wrkimpCreTypeDesc = wrkimpCreTypeDesc;
	}
	public String getWorkImpTime() {
		return workImpTime;
	}
	public void setWorkImpTime(String workImpTime) {
		this.workImpTime = workImpTime;
	}
	public void setHelpdeskId(String helpdeskId) {
		this.helpdeskId = helpdeskId;
	}
	public String getHelpdeskNo() {
		return helpdeskNo;
	}
	public void setHelpdeskNo(String helpdeskNo) {
		this.helpdeskNo = helpdeskNo;
	}
	public String getHelpdeskStatus() {
		return helpdeskStatus;
	}
	public void setHelpdeskStatus(String helpdeskStatus) {
		this.helpdeskStatus = helpdeskStatus;
	}
	public String getHelpdeskStatusDesc() {
		return helpdeskStatusDesc;
	}
	public void setHelpdeskStatusDesc(String helpdeskStatusDesc) {
		this.helpdeskStatusDesc = helpdeskStatusDesc;
	}
	public String getWriteByName() {
		return writeByName;
	}
	public void setWriteByName(String writeByName) {
		this.writeByName = writeByName;
	}
	public String getGowrkimpId() {
		return gowrkimpId;
	}
	public void setGowrkimpId(String gowrkimpId) {
		this.gowrkimpId = gowrkimpId;
	}
	public String getGowrkimpNo() {
		return gowrkimpNo;
	}
	public void setGowrkimpNo(String gowrkimpNo) {
		this.gowrkimpNo = gowrkimpNo;
	}
	public String getGowrkimpStatus() {
		return gowrkimpStatus;
	}
	public void setGowrkimpStatus(String gowrkimpStatus) {
		this.gowrkimpStatus = gowrkimpStatus;
	}
	public String getGowrkimpStatusDesc() {
		return gowrkimpStatusDesc;
	}
	public void setGowrkimpStatusDesc(String gowrkimpStatusDesc) {
		this.gowrkimpStatusDesc = gowrkimpStatusDesc;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = CommonUtil.convertDate(writeDate);
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getWrkimpdeskId() {
		return helpdeskId;
	}
	public void setWrkimpdeskId(String helpdeskId) {
		this.helpdeskId = helpdeskId;
	}
	public String getWrkimpdeskNo() {
		return helpdeskNo;
	}
	public void setWrkimpdeskNo(String helpdeskNo) {
		this.helpdeskNo = helpdeskNo;
	}
	public String getWrkimpdeskStatus() {
		return helpdeskStatus;
	}
	public void setWrkimpdeskStatus(String helpdeskStatus) {
		this.helpdeskStatus = helpdeskStatus;
	}
	public String getWrkimpdeskStatusDesc() {
		return helpdeskStatusDesc;
	}
	public void setWrkimpdeskStatusDesc(String helpdeskStatusDesc) {
		this.helpdeskStatusDesc = helpdeskStatusDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = CommonUtil.convertDate(reqDate);
	}
	public String getReqBy() {
		return reqBy;
	}
	public void setReqBy(String reqBy) {
		this.reqBy = reqBy;
	}
	public String getReqByName() {
		return reqByName;
	}
	public void setReqByName(String reqByName) {
		this.reqByName = reqByName;
	}
	public String getViewDate() {
		return viewDate;
	}
	public void setViewDate(String viewDate) {
		this.viewDate = CommonUtil.convertDate(viewDate);
	}
	public String getViewByName() {
		return viewByName;
	}
	public void setViewByName(String viewByName) {
		this.viewByName = viewByName;
	}
	public String getWorkSdate() {
		return workSdate;
	}
	public void setWorkSdate(String workSdate) {
		this.workSdate = CommonUtil.convertDate(workSdate);
	}
	public String getWorkEdate() {
		return workEdate;
	}
	public void setWorkEdate(String workEdate) {
		this.workEdate = CommonUtil.convertDate(workEdate);
	}
	public String getWorkByName() {
		return workByName;
	}
	public void setWorkByName(String workByName) {
		this.workByName = workByName;
	}
	
}
