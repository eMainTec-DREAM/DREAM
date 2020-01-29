package dream.consult.program.page.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 dto
 * @author  jung7126
 * @version $Id: MaGrdUsrColDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class MaGrdUsrColDetailDTO extends BaseDTO
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
    /** 화면 Grid id */
    private String pgGridId     = "";
    /** 화면 Grid id */
    private String pgGridColId  = "";
    
    private String keyType      = "";
    
    private String systemCol    = "";
    
    private String usrPgGridId  = "";
    
    private String usrPgGridColId   = "";
    /** 화면 Display여부  */
    private String displayYn    = "";
    
    
    public String getDisplayYn()
    {
        return displayYn;
    }
    public void setDisplayYn(String displayYn)
    {
        this.displayYn = displayYn;
    }
    public String getUsrPgGridColId()
    {
        return usrPgGridColId;
    }
    public void setUsrPgGridColId(String usrPgGridColId)
    {
        this.usrPgGridColId = usrPgGridColId;
    }
    public String getUsrPgGridId()
    {
        return usrPgGridId;
    }
    public void setUsrPgGridId(String usrPgGridId)
    {
        this.usrPgGridId = usrPgGridId;
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
        if("true".equals(hidden)) hidden = "Y";
        else if("false".equals(hidden)) hidden ="N";
        else if("Y".equals(hidden)) hidden ="true";
        else if("N".equals(hidden)) hidden = "false";
        
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
        this.width = CommonUtil.convertMoney(width);
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
        this.ordNo = CommonUtil.convertMoney(ordNo);
    }
    public String getColumnId()
    {
        return columnId;
    }
    public void setColumnId(String columnId)
    {
        this.columnId = columnId;
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