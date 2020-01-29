package dream.doc.notice.dto;

import common.bean.BaseDTO;

/**
 * DocNoticeDept - Detail DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class DocNoticeTargetDetailDTO extends BaseDTO
{ 
	/**Key ������� ID */ 
    private String notiUsrId		= "";    
    
    /**���� ID */ 
    private String noticeId         = "";    
    /**��������� ID */ 
    private String targetId         = "";    
    /**��������� NO */ 
    private String targetNo         = "";    
    /**��������� DESC */ 
    private String targetDesc       = "";
    /**��������� �μ� ID */ 
    private String targetDeptId     = "";    
    /**��������� �μ� DESC */ 
    private String targetDeptDesc   = "";
    /**���� Ȯ�γ�¥(�ð�) */ 
    private String noticeReadDate   = "";
    /**���� Ȯ�ο��� DESC*/ 
    private String readYnId       	= "";
    /**���� Ȯ�ο��� DESC*/ 
    private String readYnDesc       = "";
    /**��������� PLANT ID */ 
    private String targetPlantId    = "";    
    
	public String getTargetPlantId() {
		return targetPlantId;
	}
	public void setTargetPlantId(String targetPlantId) {
		this.targetPlantId = targetPlantId;
	}
	public String getNotiUsrId() {
		return notiUsrId;
	}
	public void setNotiUsrId(String notiUsrId) {
		this.notiUsrId = notiUsrId;
		super.setAuditKey(notiUsrId);
	}
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getTargetNo() {
		return targetNo;
	}
	public void setTargetNo(String targetNo) {
		this.targetNo = targetNo;
	}
	public String getTargetDesc() {
		return targetDesc;
	}
	public void setTargetDesc(String targetDesc) {
		this.targetDesc = targetDesc;
	}
	public String getTargetDeptId() {
		return targetDeptId;
	}
	public void setTargetDeptId(String targetDeptId) {
		this.targetDeptId = targetDeptId;
	}
	public String getTargetDeptDesc() {
		return targetDeptDesc;
	}
	public void setTargetDeptDesc(String targetDeptDesc) {
		this.targetDeptDesc = targetDeptDesc;
	}
	public String getNoticeReadDate() {
		return noticeReadDate;
	}
	public void setNoticeReadDate(String noticeReadDate) {
		this.noticeReadDate = noticeReadDate;
	}
	public String getReadYnId() {
		return readYnId;
	}
	public void setReadYnId(String readYnId) {
		this.readYnId = readYnId;
	}
	public String getReadYnDesc() {
		return readYnDesc;
	}
	public void setReadYnDesc(String readYnDesc) {
		this.readYnDesc = readYnDesc;
	}
}
