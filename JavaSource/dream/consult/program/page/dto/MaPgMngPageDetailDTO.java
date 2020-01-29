package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * 화면별 탭페이지 상세 dto
 * @author  kim21017
 * @version $Id: MaPgMngPageDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPgMngPageDetailDTO extends BaseDTO
{
	/** 탭페이지_ID(시퀀스) */
	private String pgPageId				= "";
	/** 부모화면명 */
	private String pgDesc				= "";
	/** 탭페이지 화면id*/
	private String pageId				= "";
	/** 탭페이지 화면명 */
	private String pageDesc				= "";
	/** 사용여부 */
	private String isUse				= "";
	/** 조회순서 */
	private String ordNo				= "";
	/** 다국어KEY_TYPE */
	private String keyType				= "";
	/** 다국어KEY_NO */
	private String keyNo				= "";
	/** 다국어명 */
	private String langDesc				= "";
	/** 파일명 */
	private String fileName				= "";
	/** 탭페이지 설명 */
	private String remark				= "";
	
	
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLangDesc() {
		return langDesc;
	}
	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}
	public String getPgPageId() {
		return pgPageId;
	}
	public void setPgPageId(String pgPageId) {
		this.pgPageId = pgPageId;
	}
	public String getPgDesc() {
		return pgDesc;
	}
	public void setPgDesc(String pgDesc) {
		this.pgDesc = pgDesc;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getPageDesc() {
		return pageDesc;
	}
	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
}