package dream.app.onlinehelp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class AppOnlinehelpDetailDTO extends BaseDTO
{
    /** Online Help ID */
    private String onlineHelpId             = "";
    /** 화면 ID */
    private String pageId               = "";
    /** 화면명 */
    private String pageDesc             = "";
    /** 제목 */
    private String title                  = "";
    /** 파일명 */
    private String fileName            = "";
    /** 내용 */
    private String contents           = "";
    
    public String getOnlineHelpId()
    {
        return onlineHelpId;
    }
    public void setOnlineHelpId(String onlineHelpId)
    {
        this.onlineHelpId = onlineHelpId;
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
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
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