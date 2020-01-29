package dream.pers.priv.db.set.dto;

import common.bean.BaseDTO;
/**
 * Guide Page - ���� DTO
 * @author nhkim8548
 * @version $Id: PersPrivDbSetCommonDTO.java,v 1.0 2018/7/30 09:11:20 nhkim8548 Exp $
 * @since 1.0
 *
 */
public class PersPrivDbSetCommonDTO extends BaseDTO
{
    /** ��ú��� ID */
    private String usrDbId           = "";
    /** ��ú��� ���� */
    private String usrDbDesc         = "";
    /** ������ ���� ID */
    private String usrCntTypeId        = "";
    /** ������ ���� DESC */
    private String usrCntTypeDesc      = "";
    /** �Ŵ�ǥ�ø� Key_no */
    private String usrDbMenuTypeKeyNo    = "";
    /** �Ŵ�ǥ�ø� Key_type */
    private String usrDbMenuTypeKeyType  = "";
    /** �Ŵ�ǥ�ø� Key_name */
    private String usrDbMenuTypeKeyName  = "";
   
    public String getUsrDbId()
    {
        return usrDbId;
    }
    public void setUsrDbId(String usrDbId)
    {
        this.usrDbId = usrDbId;
        super.setAuditKey(usrDbId);
    }
    public String getUsrDbDesc()
    {
        return usrDbDesc;
    }
    public void setUsrDbDesc(String usrDbDesc)
    {
        this.usrDbDesc = usrDbDesc;
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
    public String getUsrDbMenuTypeKeyNo()
    {
        return usrDbMenuTypeKeyNo;
    }
    public void setUsrDbMenuTypeKeyNo(String usrDbMenuTypeKeyNo)
    {
        this.usrDbMenuTypeKeyNo = usrDbMenuTypeKeyNo;
    }
    public String getUsrDbMenuTypeKeyType()
    {
        return usrDbMenuTypeKeyType;
    }
    public void setUsrDbMenuTypeKeyType(String usrDbMenuTypeKeyType)
    {
        this.usrDbMenuTypeKeyType = usrDbMenuTypeKeyType;
    }
    public String getUsrDbMenuTypeKeyName()
    {
        return usrDbMenuTypeKeyName;
    }
    public void setUsrDbMenuTypeKeyName(String usrDbMenuTypeKeyName)
    {
        this.usrDbMenuTypeKeyName = usrDbMenuTypeKeyName;
    }    
}
