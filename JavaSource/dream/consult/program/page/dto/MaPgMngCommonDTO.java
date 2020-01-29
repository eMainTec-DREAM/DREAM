package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * ȭ�� ���� DTO
 * @author  kim21017
 * @version $Id: MaPgMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaPgMngCommonDTO extends BaseDTO
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

	/** ���� �޴� ID */
	private String filterMenuId 			= "";
	/** ���� �޴� �� */
	private String filterMenuDesc 			= "";
	
	/** ���� �׸񼳸� */
	private String filterPgFieldDesc 			= "";
	/** ���� ���迩�� */
	private String filterHiddenYn 			= "";
	/** ���� ȭ�� Display ���� */
	private String filterDisplayYn 			= "";
	/** ���� Label ID */
	private String filterKeyNo 			= "";
	/** ���� Label�� */
	private String filterKeyName 			= "";
	/** ���� �׸�ɼ� ID */
	private String filterFieldOptionId 			= "";
	/** ���� �׸�ɼ� DESC */
	private String filterFieldOptionDesc 			= "";
	/** ���� �б����뿩�� */
	private String filterReadonlyYn 			= "";
	/** ���� �б����뿩�θ� */
    private String filterReadonlyYnDesc         = "";
	
	
	public String getFilterReadonlyYnDesc()
    {
        return filterReadonlyYnDesc;
    }
    public void setFilterReadonlyYnDesc(String filterReadonlyYnDesc)
    {
        this.filterReadonlyYnDesc = filterReadonlyYnDesc;
    }
    public String getFilterKeyName()
    {
        return filterKeyName;
    }
    public void setFilterKeyName(String filterKeyName)
    {
        this.filterKeyName = filterKeyName;
    }
    public String getFilterPgFieldDesc()
    {
        return filterPgFieldDesc;
    }
    public void setFilterPgFieldDesc(String filterPgFieldDesc)
    {
        this.filterPgFieldDesc = filterPgFieldDesc;
    }
    public String getFilterHiddenYn()
    {
        return filterHiddenYn;
    }
    public void setFilterHiddenYn(String filterHiddenYn)
    {
        this.filterHiddenYn = filterHiddenYn;
    }
    public String getFilterDisplayYn()
    {
        return filterDisplayYn;
    }
    public void setFilterDisplayYn(String filterDisplayYn)
    {
        this.filterDisplayYn = filterDisplayYn;
    }
    public String getFilterKeyNo()
    {
        return filterKeyNo;
    }
    public void setFilterKeyNo(String filterKeyNo)
    {
        this.filterKeyNo = filterKeyNo;
    }
    public String getFilterFieldOptionId()
    {
        return filterFieldOptionId;
    }
    public void setFilterFieldOptionId(String filterFieldOptionId)
    {
        this.filterFieldOptionId = filterFieldOptionId;
    }
    public String getFilterFieldOptionDesc()
    {
        return filterFieldOptionDesc;
    }
    public void setFilterFieldOptionDesc(String filterFieldOptionDesc)
    {
        this.filterFieldOptionDesc = filterFieldOptionDesc;
    }
    public String getFilterReadonlyYn()
    {
        return filterReadonlyYn;
    }
    public void setFilterReadonlyYn(String filterReadonlyYn)
    {
        this.filterReadonlyYn = filterReadonlyYn;
    }
    public String getFilterMenuId()
    {
        return filterMenuId;
    }
    public void setFilterMenuId(String filterMenuId)
    {
        this.filterMenuId = filterMenuId;
    }
    public String getFilterMenuDesc()
    {
        return filterMenuDesc;
    }
    public void setFilterMenuDesc(String filterMenuDesc)
    {
        this.filterMenuDesc = filterMenuDesc;
    }
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
