package dream.consult.program.onlinehelp.dto;

import common.bean.BaseDTO;

/**
 * 도움말 - 상세 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class ConsultProgramOnlinehelpDetailDTO extends BaseDTO
{
	/** 도움말ID */
	private String onlineHelpId 			= "";
	/** compNo */
    private String compNo             = "";
	/** 파일명 */
	private String fileName 		= "";
	/** 화면ID */
    private String pageId            = "";
	/** 화면명 */
	private String pageDesc 		= "";
	/** 작성일 */
    private String updDate         = "";
    /** 작성자 */
    private String updBy         = "";
	/** 작성일 / 작성자 */
    private String updDateBy         = "";
    /** 제목 */
    private String title         = "";
    /** 내용 */
    private String contents         = "";
    
    public String getUpdDate()
    {
        return updDate;
    }
    public void setUpdDate(String updDate)
    {
        this.updDate = updDate;
    }
    public String getUpdDateBy()
    {
        return updDateBy;
    }
    public void setUpdDateBy(String updDateBy)
    {
        this.updDateBy = updDateBy;
    }
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
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
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
    public String getUpdBy()
    {
        return updBy;
    }
    public void setUpdBy(String updBy)
    {
        this.updBy = updBy;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getContents()
    {
        return contents;
    }
    public void setContents(String contents)
    {
        this.contents = contents;
    }
	
}
