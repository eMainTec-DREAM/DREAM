package dream.pers.priv.info.dto;

import common.bean.BaseDTO;

/**
 * 상세 dto
 * @author  jung7126
 * @version $Id: MaMyMenuDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class MaLinkMenuDetailDTO extends BaseDTO
{
    /** 조회순서 */
    private String ordNo = "";
    /** 연결매뉴번호 */
    private String linkedMenuId = "";
    
    private String linkedMenuDesc   = "";
    /** 주매뉴번호 */
    private String menuId = "";
    
    private String menuDesc = "";
    /** 사용자ID */
    private String userId = "";
    /** 회사코드 */
    private String compNo = "";
    /** 연결매뉴ID */
    private String linkMenuId = "";
    
    public String getOrdNo()
    {
        return ordNo;
    }
    public void setOrdNo(String ordNo)
    {
        this.ordNo = ordNo;
    }
    public String getLinkedMenuId()
    {
        return linkedMenuId;
    }
    public void setLinkedMenuId(String linkedMenuId)
    {
        this.linkedMenuId = linkedMenuId;
    }
    public String getLinkedMenuDesc()
    {
        return linkedMenuDesc;
    }
    public void setLinkedMenuDesc(String linkedMenuDesc)
    {
        this.linkedMenuDesc = linkedMenuDesc;
    }
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
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getLinkMenuId()
    {
        return linkMenuId;
    }
    public void setLinkMenuId(String linkMenuId)
    {
        this.linkMenuId = linkMenuId;
    }
}