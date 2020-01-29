package dream.app.onlinehelp.dto;

import common.bean.BaseDTO;

/**
 * ȭ�� ���� DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 */
public class AppOnlinehelpCommonDTO extends BaseDTO
{
	private String onlineHelpId        = "";
	/** ȭ�� �� */
	private String pageDesc 			= "";
    
	private String pageId               = "";

    public String getOnlineHelpId()
    {
        return onlineHelpId;
    }

    public void setOnlineHelpId(String onlineHelpId)
    {
        this.onlineHelpId = onlineHelpId;
    }

    public String getPageDesc()
    {
        return pageDesc;
    }

    public void setPageDesc(String pageDesc)
    {
        this.pageDesc = pageDesc;
    }

    public String getPageId()
    {
        return pageId;
    }

    public void setPageId(String pageId)
    {
        this.pageId = pageId;
    }
}
