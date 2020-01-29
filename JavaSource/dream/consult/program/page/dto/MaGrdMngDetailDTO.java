package dream.consult.program.page.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 DTO
 * @author  jung7126
 * @version $Id: MaGrdMngDetailDTO.java,v 1.0 2015/12/02 08:44:16 jung7126 Exp $
 * @since   1.0
 *
 */
public class MaGrdMngDetailDTO extends BaseDTO
{
	/** 화면 Grid ID */
	private String pgGridId 			= "";
	/** 화면 ID */
	private String pageId 		        = "";
	/** 화면명 */
	private String pageDesc 		    = "";
	/** Grid Object Id */
	private String gridObjId 			= "";
	/** 설명 */
	private String description 			= "";
	/** Grid Height */
	private String height               = "";

    public String getHeight()
    {
        return height;
    }
    public void setHeight(String height)
    {
        this.height = CommonUtil.convertMoney(height);
    }
    public String getPgGridId()
    {
        return pgGridId;
    }
    public void setPgGridId(String pgGridId)
    {
        this.pgGridId = pgGridId;
    }
    public String getPageId()
    {
        return pageId;
    }
    public void setPageId(String pageId)
    {
        this.pageId = pageId;
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
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
}
