package dream.pers.priv.info.dto;

import common.bean.BaseDTO;

/**
 * 상세 dto
 * @author  jung7126
 * @version $Id: MaMyMenuDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class MaMyMenuDetailDTO extends BaseDTO
{
    /** 메뉴ID */
    private String menuId   = "";
    /** 메뉴명 */
    private String menuDesc = "";
    /** 조회순서 */
    private String ordNo    = "";
    /** 사용자 메뉴 ID */
    private String usrMenuId    = "";

    public String getUsrMenuId()
    {
        return usrMenuId;
    }
    public void setUsrMenuId(String usrMenuId)
    {
        this.usrMenuId = usrMenuId;
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
    public String getOrdNo()
    {
        return ordNo;
    }
    public void setOrdNo(String ordNo)
    {
        this.ordNo = ordNo;
    }
}