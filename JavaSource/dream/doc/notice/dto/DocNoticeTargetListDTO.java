package dream.doc.notice.dto;

import common.bean.BaseDTO;

/**
 * DocNoticeDept Page - DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class DocNoticeTargetListDTO extends BaseDTO
{
    /**Key 공지대상 ID */ 
    private String notiUsrId		= "";    
    
    /**공지 ID */ 
    private String noticeId         = "";    
    /**공지대상자 ID */ 
    private String targetId         = "";    
    /**공지대상자 NO */ 
    private String targetNo         = "";    
    /**공지대상자 DESC */ 
    private String targetDesc       = "";
    
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
}
