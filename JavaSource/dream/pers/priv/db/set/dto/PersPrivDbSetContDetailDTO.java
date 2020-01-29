package dream.pers.priv.db.set.dto;

import common.bean.BaseDTO;

/**
 * ��ú���(Contents) - Detail DTO
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContDetailDTO.java,v 1.0 2018/08/03 11:08:40 nhkim8548 Exp $
 * @since 1.0
 *
 */
public class PersPrivDbSetContDetailDTO extends BaseDTO
{ 
    /** ����� DB Menu ID */
    private String usrDbMenuCntId     = "";

    /** ������ ID */
    private String usrDbCntId           = "";
    /**������  DESC*/
    private String usrDbCntDesc         = "";
    /** ����� DB MENU ���� */
    private String usrDbMenuOder      = "";
    
    public String getUsrDbMenuCntId()
    {
        return usrDbMenuCntId;
    }
    public void setUsrDbMenuCntId(String usrDbMenuCntId)
    {
        this.usrDbMenuCntId = usrDbMenuCntId;
        super.setAuditKey(usrDbMenuCntId);
    }
    public String getUsrDbCntId()
    {
        return usrDbCntId;
    }
    public void setUsrDbCntId(String usrDbCntId)
    {
        this.usrDbCntId = usrDbCntId;
    }
    public String getUsrDbCntDesc()
    {
        return usrDbCntDesc;
    }
    public void setUsrDbCntDesc(String usrDbCntDesc)
    {
        this.usrDbCntDesc = usrDbCntDesc;
    }
    public String getUsrDbMenuOder()
    {
        return usrDbMenuOder;
    }
    public void setUsrDbMenuOder(String usrDbMenuOder)
    {
        this.usrDbMenuOder = usrDbMenuOder;
    }
}
