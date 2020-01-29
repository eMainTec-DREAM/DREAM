package dream.doc.manual.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����ڸŴ��� - �� DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class DocManualDetailDTO extends BaseDTO
{
	/** ����ID */
	private String onlineHelpId 			= "";
	/** ����NO */
    private String onlineHelpNo             = "";
    /** �޴�ID */
    private String menuId            = "";
    /** �޴��� */
    private String menuDesc         = "";
	/** ���ϸ� */
	private String fileName 		= "";
	/** ȭ��ID */
    private String pageId            = "";
	/** ȭ��� */
	private String pageDesc 		= "";
	/** ������� */
    private String updDate         = "";
    /** ��Ϻμ� */
    private String updDept         = "";
    /** ����� */
    private String updBy         = "";
    /** ���� */
    private String title         = "";
    /** ���� */
    private String contents         = "";
    
    public String getMenuId()
    {
        return menuId;
    }
    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }
    public String getMenuDesc()
    {
        return menuDesc;
    }
    public void setMenuDesc(String menuDesc)
    {
        this.menuDesc = menuDesc;
    }
    public String getOnlineHelpId()
    {
        return onlineHelpId;
    }
    public void setOnlineHelpId(String onlineHelpId)
    {
        this.onlineHelpId = onlineHelpId;
        super.setAuditKey(onlineHelpId);
    }
    public String getOnlineHelpNo()
    {
        return onlineHelpNo;
    }
    public void setOnlineHelpNo(String onlineHelpNo)
    {
        this.onlineHelpNo = onlineHelpNo;
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
    public String getUpdDate()
    {
        return updDate;
    }
    public void setUpdDate(String updDate)
    {
        this.updDate = CommonUtil.convertDate(updDate);
    }
    public String getUpdDept()
    {
        return updDept;
    }
    public void setUpdDept(String updDept)
    {
        this.updDept = updDept;
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
