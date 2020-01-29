package dream.pers.priv.db.set.dto;

import common.bean.BaseDTO;
/**
 * Guide Page - 공통 DTO
 * @author nhkim8548
 * @version $Id: PersPrivDbSetCommonDTO.java,v 1.0 2018/7/30 09:11:20 nhkim8548 Exp $
 * @since 1.0
 *
 */
public class PersPrivDbSetCommonDTO extends BaseDTO
{
    /** 대시보드 ID */
    private String usrDbId           = "";
    /** 대시보드 제목 */
    private String usrDbDesc         = "";
    /** 컨텐츠 구분 ID */
    private String usrCntTypeId        = "";
    /** 컨텐츠 구분 DESC */
    private String usrCntTypeDesc      = "";
    /** 매뉴표시명 Key_no */
    private String usrDbMenuTypeKeyNo    = "";
    /** 매뉴표시명 Key_type */
    private String usrDbMenuTypeKeyType  = "";
    /** 매뉴표시명 Key_name */
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
