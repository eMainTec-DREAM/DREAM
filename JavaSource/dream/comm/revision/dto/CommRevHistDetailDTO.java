package dream.comm.revision.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 DTO
 * @author  jung7126
 * @version $Id: CommRevHistDetailPmDTO.java,v 1.0 2015/12/02 08:44:16 jung7126 Exp $
 * @since   1.0
 *
 */
public class CommRevHistDetailDTO extends BaseDTO
{
    /** 예방작업ID */
    private String objectId = "";
    /** 예방작업번호 */
    private String objectNo = "";
    /** RevisionHist ID */
    private String revisionhistId = "";
    /** 예방작업명 */
    private String description = "";
    /** 제정/개정구분 ID */
    private String revisionType = "";
    /** 제정/개정구분 DESC */
    private String revisionTypeDesc = "";
    /** Revision 업무유형 ID */
    private String revisionObjType = "";
    /** Revision 업무유형 DESC */
    private String revisionObjTypeDesc = "";
    /** 진행상태 ID */
    private String revisionStatusId = "";
    /** 진행상태 DESC */
    private String revisionStatusDesc = "";
    /** 제정 담당부서 ID */
    private String wrkDeptId = "";
    /** 제정 담당부서 DESC */
    private String wrkDeptDesc = "";
    /** 제정 담당자 ID */
    private String wrkEmpId = "";
    /** 제정 담당자 DESC */
    private String wrkEmpDesc = "";
    /** 문서번호 */
    private String docNo = "";
    /** Revision 번호 */
    private String revNo = "";
    /** 제정일자 */
    private String wrkDate = "";
    /** 제정사항 */
    private String revDesc = "";
    
    
	public String getRevisionhistId() {
		return revisionhistId;
	}
	public void setRevisionhistId(String revisionhistId) {
		this.revisionhistId = revisionhistId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectNo() {
		return objectNo;
	}
	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRevisionType() {
		return revisionType;
	}
	public void setRevisionType(String revisionType) {
		this.revisionType = revisionType;
	}
	public String getRevisionTypeDesc() {
		return revisionTypeDesc;
	}
	public void setRevisionTypeDesc(String revisionTypeDesc) {
		this.revisionTypeDesc = revisionTypeDesc;
	}
	public String getRevisionObjType() {
		return revisionObjType;
	}
	public void setRevisionObjType(String revisionObjType) {
		this.revisionObjType = revisionObjType;
	}
	public String getRevisionObjTypeDesc() {
		return revisionObjTypeDesc;
	}
	public void setRevisionObjTypeDesc(String revisionObjTypeDesc) {
		this.revisionObjTypeDesc = revisionObjTypeDesc;
	}
	public String getRevisionStatusId() {
		return revisionStatusId;
	}
	public void setRevisionStatusId(String revisionStatusId) {
		this.revisionStatusId = revisionStatusId;
	}
	public String getRevisionStatusDesc() {
		return revisionStatusDesc;
	}
	public void setRevisionStatusDesc(String revisionStatusDesc) {
		this.revisionStatusDesc = revisionStatusDesc;
	}
	public String getWrkDeptId() {
		return wrkDeptId;
	}
	public void setWrkDeptId(String wrkDeptId) {
		this.wrkDeptId = wrkDeptId;
	}
	public String getWrkDeptDesc() {
		return wrkDeptDesc;
	}
	public void setWrkDeptDesc(String wrkDeptDesc) {
		this.wrkDeptDesc = wrkDeptDesc;
	}
	public String getWrkEmpId() {
		return wrkEmpId;
	}
	public void setWrkEmpId(String wrkEmpId) {
		this.wrkEmpId = wrkEmpId;
	}
	public String getWrkEmpDesc() {
		return wrkEmpDesc;
	}
	public void setWrkEmpDesc(String wrkEmpDesc) {
		this.wrkEmpDesc = wrkEmpDesc;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getRevNo() {
		return revNo;
	}
	public void setRevNo(String revNo) {
		this.revNo = revNo;
	}
	public String getWrkDate() {
		return wrkDate;
	}
	public void setWrkDate(String wrkDate) {
		this.wrkDate = CommonUtil.convertDate(wrkDate);
	}
	public String getRevDesc() {
		return revDesc;
	}
	public void setRevDesc(String revDesc) {
		this.revDesc = revDesc;
	}
    
    
}
