package dream.doc.manual.dto;

import common.bean.BaseDTO;

/**
 * 사용자매뉴얼 공통 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 */
public class DocManualCommonDTO extends BaseDTO
{
	/** 도움말 ID */
	private String onlineHelpId 					= "";
	/** 필터 Title */
    private String filterTitle                 = "";
	/** 필터 메뉴 ID */
	private String filterMenuId 			= "";
	/** 필터 메뉴 명 */
	private String filterMenuDesc 			= "";
	
    public String getOnlineHelpId()
    {
        return onlineHelpId;
    }
    public void setOnlineHelpId(String onlineHelpId)
    {
        this.onlineHelpId = onlineHelpId;
        super.setAuditKey(onlineHelpId);
    }
    public String getFilterTitle()
    {
        return filterTitle;
    }
    public void setFilterTitle(String filterTitle)
    {
        this.filterTitle = filterTitle;
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
	
}
