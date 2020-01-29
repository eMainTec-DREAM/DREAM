package dream.consult.program.help.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaHelpDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo = "";
	/** Help Desk ID */
	private String helpdeskId = "";
	/** Help Desk ��ȣ */
	private String helpdeskNo = "";
	/** ������� */
	private String helpdeskStatus = "";
	/** ������¸� */
	private String helpdeskStatusDesc = "";
	/** ���� */
	private String description = "";
	/** ��û���� */
	private String request = "";
	/** ��û���� */
	private String reqDate = "";
	/** ��û�� */
	private String reqBy = "";
	/** ��û�ڸ� */
	private String reqByName = "";
	/** �۾����� */
	private String perform = "";
	/** �������� */
	private String viewDate = "";
	/** ������ */
	private String viewBy = "";
	/** �����ڸ� */
	private String viewByName = "";
	/** �۾��������� */
	private String workSdate = "";
	/** �۾��������� */
	private String workEdate = "";
	/** �۾��� */
	private String workBy = "";
	/** �۾��ڸ� */
	private String workByName = "";
	/** ����ID */
	private String gowrkimpId = "";
	/** ���� ��ȣ */
	private String gowrkimpNo = "";
	/** �������[�۾���,�۾���,�Ϸ�] */
	private String gowrkimpStatus = "";
	/** �������[�۾���,�۾���,�Ϸ�] */
	private String gowrkimpStatusDesc = "";
	/** �ۼ����� */
	private String writeDate = "";
	/** �ۼ��� */
	private String writeBy = "";
	/** �ۼ��ڸ� */
	private String writeByName = "";
	
	
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
	public String getWriteBy() {
		return writeBy;
	}
	public void setWriteBy(String writeBy) {
		this.writeBy = writeBy;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getHelpdeskId() {
		return helpdeskId;
	}
	public void setHelpdeskId(String helpdeskId) {
		this.helpdeskId = helpdeskId;
		super.setAuditKey(helpdeskId);
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
	public String getPerform() {
		return perform;
	}
	public void setPerform(String perform) {
		this.perform = perform;
	}
	public String getViewDate() {
		return viewDate;
	}
	public void setViewDate(String viewDate) {
		this.viewDate = CommonUtil.convertDate(viewDate);
	}
	public String getViewBy() {
		return viewBy;
	}
	public void setViewBy(String viewBy) {
		this.viewBy = viewBy;
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
	public String getWorkBy() {
		return workBy;
	}
	public void setWorkBy(String workBy) {
		this.workBy = workBy;
	}
	public String getWorkByName() {
		return workByName;
	}
	public void setWorkByName(String workByName) {
		this.workByName = workByName;
	}
	
}
