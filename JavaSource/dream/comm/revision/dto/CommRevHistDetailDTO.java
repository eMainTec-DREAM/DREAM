package dream.comm.revision.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� DTO
 * @author  jung7126
 * @version $Id: CommRevHistDetailPmDTO.java,v 1.0 2015/12/02 08:44:16 jung7126 Exp $
 * @since   1.0
 *
 */
public class CommRevHistDetailDTO extends BaseDTO
{
    /** �����۾�ID */
    private String objectId = "";
    /** �����۾���ȣ */
    private String objectNo = "";
    /** RevisionHist ID */
    private String revisionhistId = "";
    /** �����۾��� */
    private String description = "";
    /** ����/�������� ID */
    private String revisionType = "";
    /** ����/�������� DESC */
    private String revisionTypeDesc = "";
    /** Revision �������� ID */
    private String revisionObjType = "";
    /** Revision �������� DESC */
    private String revisionObjTypeDesc = "";
    /** ������� ID */
    private String revisionStatusId = "";
    /** ������� DESC */
    private String revisionStatusDesc = "";
    /** ���� ���μ� ID */
    private String wrkDeptId = "";
    /** ���� ���μ� DESC */
    private String wrkDeptDesc = "";
    /** ���� ����� ID */
    private String wrkEmpId = "";
    /** ���� ����� DESC */
    private String wrkEmpDesc = "";
    /** ������ȣ */
    private String docNo = "";
    /** Revision ��ȣ */
    private String revNo = "";
    /** �������� */
    private String wrkDate = "";
    /** �������� */
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
