package dream.pers.priv.db.set.dto;

import common.bean.BaseDTO;

/**
 * LovDbConAcList DTO
 * @author  nhkim8548
 * @version $Id: LovDbConAcList.java,v 1.0 2018/08/06 09:21:20 nhkim8548 Exp $
 * @since   1.0
 */
public class LovDbConAcListDTO extends BaseDTO
{
    /** 牧刨明 ID */
    private String usrDbCntId           = "";
    
    /** 牧刨明 力格 */
    private String usrCntDesc         = "";
    /** 牧刨明 备盒 ID */
    private String usrCntTypeId        = "";
    /** 牧刨明 备盒 DESC */
    private String usrCntTypeDesc      = "";
    
    public String getUsrDbCntId()
    {
        return usrDbCntId;
    }
    public void setUsrDbCntId(String usrDbCntId)
    {
        this.usrDbCntId = usrDbCntId;
    }
    public String getUsrCntDesc()
    {
        return usrCntDesc;
    }
    public void setUsrCntDesc(String usrCntDesc)
    {
        this.usrCntDesc = usrCntDesc;
    }
    public String getUsrCntTypeId()
    {
        return usrCntTypeId;
    }
    public void setUsrCntTypeId(String usrCntTypeId)
    {
        this.usrCntTypeId = usrCntTypeId;
    }
    public String getUsrCntTypeDesc()
    {
        return usrCntTypeDesc;
    }
    public void setUsrCntTypeDesc(String usrCntTypeDesc)
    {
        this.usrCntTypeDesc = usrCntTypeDesc;
    }
}
