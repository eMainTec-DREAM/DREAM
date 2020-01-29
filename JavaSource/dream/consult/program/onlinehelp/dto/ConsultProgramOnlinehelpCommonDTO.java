package dream.consult.program.onlinehelp.dto;

import common.bean.BaseDTO;

/**
 * ���� ���� DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 */
public class ConsultProgramOnlinehelpCommonDTO extends BaseDTO
{
	/** ���� ID */
	private String onlineHelpId 					= "";
	/** compNo */
    private String compNo                        = "";
	/** ���� ȭ�� ID */
	private String filterFileName 			= "";
	/** ���� Title */
    private String filterTitle                 = "";
	/** ���� Contents */
	private String filterContents  		= "";
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
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getFilterFileName()
    {
        return filterFileName;
    }
    public void setFilterFileName(String filterFileName)
    {
        this.filterFileName = filterFileName;
    }
    public String getFilterTitle()
    {
        return filterTitle;
    }
    public void setFilterTitle(String filterTitle)
    {
        this.filterTitle = filterTitle;
    }
    public String getFilterContents()
    {
        return filterContents;
    }
    public void setFilterContents(String filterContents)
    {
        this.filterContents = filterContents;
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
