package dream.consult.program.onlinehelp.dto;

import common.bean.BaseDTO;

/**
 * 도움말 공통 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 */
public class ConsultProgramOnlinehelpCommonDTO extends BaseDTO
{
	/** 도움말 ID */
	private String onlineHelpId 					= "";
	/** compNo */
    private String compNo                        = "";
	/** 필터 화면 ID */
	private String filterFileName 			= "";
	/** 필터 Title */
    private String filterTitle                 = "";
	/** 필터 Contents */
	private String filterContents  		= "";
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
