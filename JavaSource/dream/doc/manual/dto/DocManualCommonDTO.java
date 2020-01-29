package dream.doc.manual.dto;

import common.bean.BaseDTO;

/**
 * ����ڸŴ��� ���� DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 */
public class DocManualCommonDTO extends BaseDTO
{
	/** ���� ID */
	private String onlineHelpId 					= "";
	/** ���� Title */
    private String filterTitle                 = "";
	/** ���� �޴� ID */
	private String filterMenuId 			= "";
	/** ���� �޴� �� */
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
