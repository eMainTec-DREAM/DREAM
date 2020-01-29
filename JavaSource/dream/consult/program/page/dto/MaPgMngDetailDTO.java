package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * ȭ�� - �� DTO
 * @author  kim21017
 * @version $Id: MaPgMngDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaPgMngDetailDTO extends BaseDTO
{
	/** ȭ��ID */
	private String pageId 			= "";
	/** ���ϸ� */
	private String fileName 		= "";
	/** ȭ��� */
	private String pageDesc 		= "";
	/** ��� */
	private String remark 			= "";
	/** ��뿩�� */
	private String isUse 			= "";
	/** ����äũ ���� */
	private String isChkauth        = "";
	/**������ ����id*/
	private String pageTypeId        = "";
	/**������ ������*/
	private String pageTypeDesc        = "";
	/**������ ������*/
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
