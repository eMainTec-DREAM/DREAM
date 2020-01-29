package dream.pers.priv.db.set.dto;

import common.bean.BaseDTO;

/**
 * Guide Page - Detail DTO
 * @author nhkim8548
 * @version $Id: PersPrivDbSetDetailDTO.java,v 1.0 2018/07/31 13:06:40 nhkim8548 Exp $
 * @since 1.0
 *
 */
public class PersPrivDbSetDetailDTO extends BaseDTO
{
    /** ��ú��� ID */
    private String usrDbId               = "";
    /** ��ú��� ���� */
    private String usrDbDesc             = "";
    /** �Ŵ� ǥ�ø� ID */
    private String usrDbMenuKeyNo        = "";
    /** �Ŵ� ǥ�ø� Type */
    private String usrDbMenuKeyType      = "";
    /** �Ŵ� ǥ�ø� DESC */
    private String usrDbMenuDesc         = "";
    /** �Ŵ� ��뿩�� ID */
    private String usrDbMenuIsUseId      = "";
    /** �Ŵ� ��뿩�� DESC */
    private String usrDbMenuIsUseDesc    = "";
    /** �Ŵ� ��� */
    private String usrDbMenuRemark       = "";
    
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
    public String getUsrDbMenuDesc()
    {
        return usrDbMenuDesc;
    }
    public void setUsrDbMenuDesc(String usrDbMenuDesc)
    {
        this.usrDbMenuDesc = usrDbMenuDesc;
    }
    public String getUsrDbMenuKeyNo()
    {
        return usrDbMenuKeyNo;
    }
    public void setUsrDbMenuKeyNo(String usrDbMenuKeyNo)
    {
        this.usrDbMenuKeyNo = usrDbMenuKeyNo;
    }
    public String getUsrDbMenuKeyType()
    {
        return usrDbMenuKeyType;
    }
    public void setUsrDbMenuKeyType(String usrDbMenuKeyType)
    {
        this.usrDbMenuKeyType = usrDbMenuKeyType;
    }
    public String getUsrDbMenuIsUseId()
    {
        return usrDbMenuIsUseId;
    }
    public void setUsrDbMenuIsUseId(String usrDbMenuIsUseId)
    {
        this.usrDbMenuIsUseId = usrDbMenuIsUseId;
    }
    public String getUsrDbMenuIsUseDesc()
    {
        return usrDbMenuIsUseDesc;
    }
    public void setUsrDbMenuIsUseDesc(String usrDbMenuIsUseDesc)
    {
        this.usrDbMenuIsUseDesc = usrDbMenuIsUseDesc;
    }
    public String getUsrDbMenuRemark()
    {
        return usrDbMenuRemark;
    }
    public void setUsrDbMenuRemark(String usrDbMenuRemark)
    {
        this.usrDbMenuRemark = usrDbMenuRemark;
    }
}
