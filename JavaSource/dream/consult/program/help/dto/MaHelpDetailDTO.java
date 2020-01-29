package dream.consult.program.help.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaHelpDetailDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo = "";
	/** Help Desk ID */
	private String helpdeskId = "";
	/** Help Desk 번호 */
	private String helpdeskNo = "";
	/** 진행상태 */
	private String helpdeskStatus = "";
	/** 진행상태명 */
	private String helpdeskStatusDesc = "";
	/** 제목 */
	private String description = "";
	/** 요청내용 */
	private String request = "";
	/** 요청일자 */
	private String reqDate = "";
	/** 요청자 */
	private String reqBy = "";
	/** 요청자명 */
	private String reqByName = "";
	/** 작업내용 */
	private String perform = "";
	/** 검토일자 */
	private String viewDate = "";
	/** 검토자 */
	private String viewBy = "";
	/** 검토자명 */
	private String viewByName = "";
	/** 작업시작일자 */
	private String workSdate = "";
	/** 작업종료일자 */
	private String workEdate = "";
	/** 작업자 */
	private String workBy = "";
	/** 작업자명 */
	private String workByName = "";
	/** 개선ID */
	private String gowrkimpId = "";
	/** 개선 번호 */
	private String gowrkimpNo = "";
	/** 진행상태[작업중,작업중,완료] */
	private String gowrkimpStatus = "";
	/** 진행상태[작업중,작업중,완료] */
	private String gowrkimpStatusDesc = "";
	/** 작성일자 */
	private String writeDate = "";
	/** 작성자 */
	private String writeBy = "";
	/** 작성자명 */
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
