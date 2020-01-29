package gaia.gapg.dto;

import common.bean.BaseDTO;

/**
 * 화면 공통 DTO
 * @author  kim21017
 * @version $Id: GaPgMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class GaPgMngCommonDTO extends BaseDTO
{
	/** 화면 ID */
	private String pageId 					= "";
	/** 화면 Field Id  */
	private String pgFieldId                = "";
	/** 필터 화면 ID */
	private String filterFileName 			= "";
	/** 필터 화면 명 */
	private String filterPageDesc 			= "";
	/** 필드 최초 생성 데이타 */
	private String fieldData                = "";

	
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
