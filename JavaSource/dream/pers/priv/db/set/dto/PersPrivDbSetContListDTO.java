package dream.pers.priv.db.set.dto;

import common.bean.BaseDTO;

/**
 * PersPrivDbSetCont Page - DTO
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContListDTO.java,v 1.0 2018/08/03 11:06:40 nhkim8548 Exp $
 * @since 1.0
 *
 */
public class PersPrivDbSetContListDTO extends BaseDTO
{
    /** ����� DB Menu ID */
    private String usrDbMenuCntId   = "";
    /** ����� Contents ID */
    private String usrDbCntId       = "";
    
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
    public void setUsrCntId(String usrDbCntId)
    {
        this.usrDbCntId = usrDbCntId;
    }
}
