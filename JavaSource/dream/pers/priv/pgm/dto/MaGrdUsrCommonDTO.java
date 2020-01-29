package dream.pers.priv.pgm.dto;

import common.bean.BaseDTO;

/**
 * 화면 공통 DTO
 * @author  jung7126
 * @version $Id: MaGrdUsrCommonDTO.java,v 1.0 2015/12/02 09:13:08 jung7126 Exp $
 * @since   1.0
 * 
 */
public class MaGrdUsrCommonDTO extends BaseDTO
{
	/** 화면 Grid ID */
	private String pgGridId   			= "";
	/** 리스트 ID */
	private String gridObjId 			= "";
	/** 화면 명 */
	private String pageDesc 			= "";
	
	private String pgGridColId          = "";
	
	private String usrPgGridColId       = "";
    
	private String pageId               = "";
	
    public String getUsrPgGridColId()
    {
        return usrPgGridColId;
    }
    public void setUsrPgGridColId(String usrPgGridColId)
    {
        this.usrPgGridColId = usrPgGridColId;
    }
    public String getPageId()
    {
        return pageId;
    }
    public void setPageId(String pageId)
    {
        this.pageId = pageId;
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
