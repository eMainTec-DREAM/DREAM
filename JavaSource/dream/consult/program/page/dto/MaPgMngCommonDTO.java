package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * 화면 공통 DTO
 * @author  kim21017
 * @version $Id: MaPgMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaPgMngCommonDTO extends BaseDTO
{
	/** 화면 ID */
	private String pageId 					= "";
	/** 화면 Field Id  */
	private String pgFieldId                = "";
	/** 필터 화면 ID */
	private String filterFileName 			= "";
	/** 필터 화면 명 */
	private String filterPageDesc 			= "";
	/** 필터 page category */
	private String filterPageCateg 			= "";
	/** 필터 page type */
	private String filterPageTypeId 			= "";
	/** 필터 page type */
	private String filterPageType 			= "";
	/** 필터 page type */
	private String paramPageType 			= "";
	/** 필드 최초 생성 데이타 */
	private String fieldData                = "";

	/** 필터 메뉴 ID */
	private String filterMenuId 			= "";
	/** 필터 메뉴 명 */
	private String filterMenuDesc 			= "";
	
	/** 필터 항목설명 */
	private String filterPgFieldDesc 			= "";
	/** 필터 숨김여부 */
	private String filterHiddenYn 			= "";
	/** 필터 화면 Display 여부 */
	private String filterDisplayYn 			= "";
	/** 필터 Label ID */
	private String filterKeyNo 			= "";
	/** 필터 Label명 */
	private String filterKeyName 			= "";
	/** 필터 항목옵션 ID */
	private String filterFieldOptionId 			= "";
	/** 필터 항목옵션 DESC */
	private String filterFieldOptionDesc 			= "";
	/** 필터 읽기전용여부 */
	private String filterReadonlyYn 			= "";
	/** 필터 읽기전용여부명 */
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
