package dream.consult.program.page.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 dto
 * @author  jung7126
 * @version $Id: MaGrdMngColDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class MaGrdMngColDetailDTO extends BaseDTO
{
    /** 숨김여부 */
    private String hidden       = "";
    /** 정렬조건 */
    private String sort         = "";
    /** Align(center,left,right) */
    private String align        = "";
    
    private String alignDesc    = "";
    /** 속성타입(txt,date, ron[read only]..] */
    private String type         = "";
    
    private String typeDesc     = "";
    /** 넓이(PX값) */
    private String width        = "";
    /**  */
    private String keyNo        = "";
    /** 컬럼순서 */
    private String ordNo        = "";
    /** 컬럼ID */
    private String columnId     = "";
    
    private String columnDesc   = "";
    /** 화면 id */
    private String pageId     = "";
    /** 화면 Grid id */
    private String pgGridId     = "";
    /** 화면 Grid id */
    private String pgGridColId  = "";
    
    private String keyType      = "";
    
    private String systemCol    = "";
    /** 화면 Grid id */
    private String description  = "";
    
    private String pageDesc  = "";
    
    private String gridObjId  = "";
    /** 화면Display여부 */
    private String displayYn    = "";
    
    
    
    public String getDisplayYn()
    {
        return displayYn;
    }
    public void setDisplayYn(String displayYn)
    {
        this.displayYn = displayYn;
    }
    public String getPageDesc()
    {
        return pageDesc;
    }
    public void setPageDesc(String pageDesc)
    {
        this.pageDesc = pageDesc;
    }
    public String getGridObjId()
    {
        return gridObjId;
    }
    public void setGridObjId(String gridObjId)
    {
        this.gridObjId = gridObjId;
    }
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSystemCol()
    {
        return systemCol;
    }
    public void setSystemCol(String systemCol)
    {
        this.systemCol = systemCol;
    }
    public String getKeyType()
    {
        return keyType;
    }
    public void setKeyType(String keyType)
    {
        this.keyType = keyType;
    }
    public String getAlignDesc()
    {
        return alignDesc;
    }
    public void setAlignDesc(String alignDesc)
    {
        this.alignDesc = alignDesc;
    }
    public String getColumnDesc()
    {
        return columnDesc;
    }
    public void setColumnDesc(String columnDesc)
    {
        this.columnDesc = columnDesc;
    }
    public String getHidden()
    {
        return hidden;
    }
    public void setHidden(String hidden)
    {
        if("TRUE".equals(hidden.toUpperCase())) hidden = "Y";
        else if("FALSE".equals(hidden.toUpperCase())) hidden ="N";
        else if("Y".equals(hidden.toUpperCase())) hidden ="true";
        else if("N".equals(hidden.toUpperCase())) hidden = "false";
        
        this.hidden = hidden;
    }
    public String getSort()
    {
        return sort;
    }
    public void setSort(String sort)
    {
        this.sort = sort;
    }
    public String getAlign()
    {
        return align;
    }
    public void setAlign(String align)
    {
        this.align = align;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getTypeDesc()
    {
        return typeDesc;
    }
    public void setTypeDesc(String typeDesc)
    {
        this.typeDesc = typeDesc;
    }
    public String getWidth()
    {
        return width;
    }
    public void setWidth(String width)
    {
        this.width = width;
    }
    public String getKeyNo()
    {
        return keyNo;
    }
    public void setKeyNo(String keyNo)
    {
        this.keyNo = keyNo;
    }
    public String getOrdNo()
    {
        return ordNo;
    }
    public void setOrdNo(String ordNo)
    {
        this.ordNo = ordNo;
    }
    public String getColumnId()
    {
        return columnId;
    }
    public void setColumnId(String columnId)
    {
        this.columnId = columnId;
    }
    public String getPageId()
    {
        return pageId;
    }
    public void setPageId(String pageId)
    {
        this.pageId = pageId;
    }
    public String getPgGridId()
    {
        return pgGridId;
    }
    public void setPgGridId(String pgGridId)
    {
        this.pgGridId = pgGridId;
    }
    public String getPgGridColId()
    {
        return pgGridColId;
    }
    public void setPgGridColId(String pgGridColId)
    {
        this.pgGridColId = pgGridColId;
    }
    
}