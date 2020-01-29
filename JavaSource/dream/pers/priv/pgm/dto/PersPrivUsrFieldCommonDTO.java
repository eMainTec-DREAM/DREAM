package dream.pers.priv.pgm.dto;

import common.bean.BaseDTO;

/**
 * ȭ�� ���� DTO
 * @author  kim21017
 * @version $Id: MaPgMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class PersPrivUsrFieldCommonDTO extends BaseDTO
{
	/** ȭ�� ID */
	private String pageId 					= "";
	/** ȭ�� Field Id  */
	private String pgFieldId                = "";
	/** ���� ȭ�� ID */
	private String filterFileName 			= "";
	/** ���� ȭ�� �� */
	private String filterPageDesc 			= "";
	/** ���� page category */
	private String filterPageCateg 			= "";
	/** ���� page type */
	private String filterPageTypeId 			= "";
	/** ���� page type */
	private String filterPageType 			= "";
	/** ���� page type */
	private String paramPageType 			= "";
	/** �ʵ� ���� ���� ����Ÿ */
	private String fieldData                = "";

	
	
	public String getParamPageType() {
		return paramPageType;
	}
	public void setParamPageType(String paramPageType) {
		this.paramPageType = paramPageType;
	}
	public String getFilterPageTypeId() {
		return filterPageTypeId;
	}
	public void setFilterPageTypeId(String filterPageTypeId) {
		this.filterPageTypeId = filterPageTypeId;
	}
	public String getFilterPageCateg() {
		return filterPageCateg;
	}
	public void setFilterPageCateg(String filterPageCateg) {
		this.filterPageCateg = filterPageCateg;
	}
	public String getFilterPageType() {
		return filterPageType;
	}
	public void setFilterPageType(String filterPageType) {
		this.filterPageType = filterPageType;
	}
	public String getFieldData()
    {
        return fieldData;
    }
    public void setFieldData(String fieldData)
    {
        this.fieldData = fieldData;
    }
    public String getPgFieldId()
    {
        return pgFieldId;
    }
    public void setPgFieldId(String pgFieldId)
    {
        this.pgFieldId = pgFieldId;
    }
    public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getFilterFileName() {
		return filterFileName;
	}
	public void setFilterFileName(String filterFileName) {
		this.filterFileName = filterFileName;
	}
	public String getFilterPageDesc() {
		return filterPageDesc;
	}
	public void setFilterPageDesc(String filterPageDesc) {
		this.filterPageDesc = filterPageDesc;
	}
}
