package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * 화면 공통 DTO
 * @author  jung7126
 * @version $Id: MaGrdMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 jung7126 Exp $
 * @since   1.0
 * 
 */
public class MaGrdMngCommonDTO extends BaseDTO
{
	/** 화면 Grid ID */
	private String pgGridId   			= "";
	/** 리스트 ID */
	private String gridObjId 			= "";
	/** 화면 명 */
	private String pageDesc 			= "";
	
	private String pgGridColId          = "";
	
	private String fileName             = "";
	
	private String columnId             = "";
	
	private String filterPageId        = "";
	private String filterFileName          = "";
	private String filterPageDesc          = "";
	private String filterGridObjId          = "";
	private String filterGridDesc          = "";
	private String filterColumnId          = "";
	private String filterColumnDesc          = "";
    private String filterType          = "";
    private String filterTypeDesc          = "";
    private String filterAlign          = "";
    private String filterAlignDesc          = "";
    private String filterHidden          = "";
    private String filterSystemCol          = "";
    private String filterKeyType          = "";
    private String filterKeyNo          = "";
	
    
    public String getFilterPageId()
    {
        return filterPageId;
    }
    public void setFilterPageId(String filterPageId)
    {
        this.filterPageId = filterPageId;
    }
    public String getFilterFileName()
    {
        return filterFileName;
    }
    public void setFilterFileName(String filterFileName)
    {
        this.filterFileName = filterFileName;
    }
    public String getFilterPageDesc()
    {
        return filterPageDesc;
    }
    public void setFilterPageDesc(String filterPageDesc)
    {
        this.filterPageDesc = filterPageDesc;
    }
    public String getFilterGridObjId()
    {
        return filterGridObjId;
    }
    public void setFilterGridObjId(String filterGridObjId)
    {
        this.filterGridObjId = filterGridObjId;
    }
    public String getFilterGridDesc()
    {
        return filterGridDesc;
    }
    public void setFilterGridDesc(String filterGridDesc)
    {
        this.filterGridDesc = filterGridDesc;
    }
    public String getFilterColumnId()
    {
        return filterColumnId;
    }
    public void setFilterColumnId(String filterColumnId)
    {
        this.filterColumnId = filterColumnId;
    }
    public String getFilterColumnDesc()
    {
        return filterColumnDesc;
    }
    public void setFilterColumnDesc(String filterColumnDesc)
    {
        this.filterColumnDesc = filterColumnDesc;
    }
    public String getFilterType()
    {
        return filterType;
    }
    public void setFilterType(String filterType)
    {
        this.filterType = filterType;
    }
    public String getFilterTypeDesc()
    {
        return filterTypeDesc;
    }
    public void setFilterTypeDesc(String filterTypeDesc)
    {
        this.filterTypeDesc = filterTypeDesc;
    }
    public String getFilterAlign()
    {
        return filterAlign;
    }
    public void setFilterAlign(String filterAlign)
    {
        this.filterAlign = filterAlign;
    }
    public String getFilterAlignDesc()
    {
        return filterAlignDesc;
    }
    public void setFilterAlignDesc(String filterAlignDesc)
    {
        this.filterAlignDesc = filterAlignDesc;
    }
    public String getFilterHidden()
    {
        return filterHidden;
    }
    public void setFilterHidden(String filterHidden)
    {
        this.filterHidden = filterHidden;
    }
    public String getFilterSystemCol()
    {
        return filterSystemCol;
    }
    public void setFilterSystemCol(String filterSystemCol)
    {
        this.filterSystemCol = filterSystemCol;
    }
    public String getFilterKeyType()
    {
        return filterKeyType;
    }
    public void setFilterKeyType(String filterKeyType)
    {
        this.filterKeyType = filterKeyType;
    }
    public String getFilterKeyNo()
    {
        return filterKeyNo;
    }
    public void setFilterKeyNo(String filterKeyNo)
    {
        this.filterKeyNo = filterKeyNo;
    }
    public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getColumnId()
    {
        return columnId;
    }
    public void setColumnId(String columnId)
    {
        this.columnId = columnId;
    }
    public String getPgGridColId()
    {
        return pgGridColId;
    }
    public void setPgGridColId(String pgGridColId)
    {
        this.pgGridColId = pgGridColId;
    }
    public String getPgGridId()
    {
        return pgGridId;
    }
    public void setPgGridId(String pgGridId)
    {
        this.pgGridId = pgGridId;
    }
    public String getGridObjId()
    {
        return gridObjId;
    }
    public void setGridObjId(String gridObjId)
    {
        this.gridObjId = gridObjId;
    }
    public String getPageDesc()
    {
        return pageDesc;
    }
    public void setPageDesc(String pageDesc)
    {
        this.pageDesc = pageDesc;
    }
}
