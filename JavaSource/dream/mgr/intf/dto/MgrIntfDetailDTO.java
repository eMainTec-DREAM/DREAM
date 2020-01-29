package dream.mgr.intf.dto;

import common.bean.BaseDTO;

/**
 * Interface Page - Detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrIntfDetailDTO extends BaseDTO
{
	/**인터페이스 ID*/
	private String intfId 		    = "";
	/**인터페이스 No*/
	private String intfNo 		    = "";
	/**인터페이스명*/
	private String intfDesc 		= "";
	/**인터페이스 분류*/
	private String intfType         = "";
	/**최종실행일자*/
	private String lastExeDate      = "";
	/**최종실행시간*/
	private String ExeTime          = "";
	/**최종실행자*/
	private String ExeBy            = "";
	/**최종실행자 desc*/
	private String ExeByDesc        = "";
	/**비고*/
	private String remark           = "";
	/**사용여부 ID*/
    private String isUse            = "";
    /**사용여부 */
    private String isUseDesc        = "";
    /**최종성공일자*/
	private String successDate      = "";
	/**접속IP 또는 URL*/
	private String accUrl           = "";
	/**SID, DB명*/
	private String accName          = "";
    /**접속 port*/
	private String accPort          = "";
    /**접속 ID*/
	private String accUser          = "";
    /**접속 비밀번호*/
	private String accPassword      = "";
    /**Client, Site Code*/
	private String accSite          = "";
	
    public String getExeByDesc()
    {
        return ExeByDesc;
    }
    public void setExeByDesc(String exeByDesc)
    {
        ExeByDesc = exeByDesc;
    }
    public String getIntfId()
    {
        return intfId;
    }
    public void setIntfId(String intfId)
    {
        this.intfId = intfId;
        super.setAuditKey(intfId);
    }
    public String getIntfNo()
    {
        return intfNo;
    }
    public void setIntfNo(String intfNo)
    {
        this.intfNo = intfNo;
    }
    public String getIntfDesc()
    {
        return intfDesc;
    }
    public void setIntfDesc(String intfDesc)
    {
        this.intfDesc = intfDesc;
    }
    public String getIntfType()
    {
        return intfType;
    }
    public void setIntfType(String intfType)
    {
        this.intfType = intfType;
    }
    public String getLastExeDate()
    {
        return lastExeDate;
    }
    public void setLastExeDate(String lastExeDate)
    {
        this.lastExeDate = lastExeDate;
    }
    public String getExeTime()
    {
        return ExeTime;
    }
    public void setExeTime(String exeTime)
    {
        ExeTime = exeTime;
    }
    public String getExeBy()
    {
        return ExeBy;
    }
    public void setExeBy(String exeBy)
    {
        ExeBy = exeBy;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public String getIsUseDesc()
    {
        return isUseDesc;
    }
    public void setIsUseDesc(String isUseDesc)
    {
        this.isUseDesc = isUseDesc;
    }
    public String getSuccessDate()
    {
        return successDate;
    }
    public void setSuccessDate(String successDate)
    {
        this.successDate = successDate;
    }
    public String getAccUrl()
    {
        return accUrl;
    }
    public void setAccUrl(String accUrl)
    {
        this.accUrl = accUrl;
    }
    public String getAccName()
    {
        return accName;
    }
    public void setAccName(String accName)
    {
        this.accName = accName;
    }
    public String getAccPort()
    {
        return accPort;
    }
    public void setAccPort(String accPort)
    {
        this.accPort = accPort;
    }
    public String getAccUser()
    {
        return accUser;
    }
    public void setAccUser(String accUser)
    {
        this.accUser = accUser;
    }
    public String getAccPassword()
    {
        return accPassword;
    }
    public void setAccPassword(String accPassword)
    {
        this.accPassword = accPassword;
    }
    public String getAccSite()
    {
        return accSite;
    }
    public void setAccSite(String accSite)
    {
        this.accSite = accSite;
    }
	
}
