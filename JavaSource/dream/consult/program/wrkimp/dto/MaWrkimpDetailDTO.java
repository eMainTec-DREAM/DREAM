package dream.consult.program.wrkimp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaWrkimpDetailDTO extends BaseDTO
{
	
	/** 개선ID */
	private String gowrkimpId = "";
	/** 개선 번호 */
	private String gowrkimpNo = "";
	/** 제목 */
	private String description = "";
	/** 진행상태[작업중,작업중,완료] */
	private String gowrkimpStatus = "";
	/** 진행상태[작업중,작업중,완료] */
	private String gowrkimpStatusDesc = "";
	/** 작성자명 */
	private String writeByName = "";
	/** 작성일자 */
	private String writeDate = "";
	/** 검토자명 */
	private String viewByName = "";
	/** 검토일자 */
	private String viewDate = "";
	/** 작업시작일자 */
	private String workSdate = "";
	/** 작업종료일자 */
	private String workEdate = "";
	/** 작업자명 */
	private String workByName = "";
	private String perform = "";
	private String wrkimpSite = "";
	private String wrkimpCreTypeId = "";
	private String wrkimpCreTypeDesc = "";
	private String workImpTime = "";

	
	
	/** Wrkimp Desk 번호 */
	private String helpdeskNo = "";
	/** 진행상태 */
	private String helpdeskStatus = "";
	/** 진행상태명 */
	private String helpdeskStatusDesc = "";
	/** 요청자 */
	private String reqBy = "";
	/** 요청자명 */
	private String reqByName = "";
	/** 요청일자 */
	private String reqDate = "";
	/** 요청내용 */
	private String request = "";
	/** 회사코드 */
	private String compNo = "";
	/** Wrkimp Desk ID */
	private String helpdeskId = "";
	
	
//	
//	
//	
//	/** 작업내용 */
//	private String perform = "";
//	/** 검토자 */
//	private String viewBy = "";
//	/** 작업자 */
//	private String workBy = "";
//	/** 작성자 */
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
