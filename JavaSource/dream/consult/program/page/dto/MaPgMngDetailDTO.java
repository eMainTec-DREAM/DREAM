package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * 화면 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaPgMngDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaPgMngDetailDTO extends BaseDTO
{
	/** 화면ID */
	private String pageId 			= "";
	/** 파일명 */
	private String fileName 		= "";
	/** 화면명 */
	private String pageDesc 		= "";
	/** 비고 */
	private String remark 			= "";
	/** 사용여부 */
	private String isUse 			= "";
	/** 권한채크 여부 */
	private String isChkauth        = "";
	/**페이지 유형id*/
	private String pageTypeId        = "";
	/**페이지 유형명*/
	private String pageTypeDesc        = "";
	/**페이지 유형명*/
	private String pageCateg        = "";
	
	
	

	public String getPageCateg() {
		return pageCateg;
	}
	public void setPageCateg(String pageCateg) {
		this.pageCateg = pageCateg;
	}
	public String getIsChkauth()
    {
        return isChkauth;
    }
    public void setIsChkauth(String isChkauth)
    {
        this.isChkauth = isChkauth;
    }
    public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getPageTypeId() {
		return pageTypeId;
	}
	public void setPageTypeId(String pageTypeId) {
		this.pageTypeId = pageTypeId;
	}
	public String getPageTypeDesc() {
		return pageTypeDesc;
	}
	public void setPageTypeDesc(String pageTypeDesc) {
		this.pageTypeDesc = pageTypeDesc;
	}
	
}
