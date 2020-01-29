package dream.mgr.intf.dto;

import common.bean.BaseDTO;

/**
 * Interface Log Page - Detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrIntfLogDetailDTO extends BaseDTO
{
    /**�������̽� �α� ID*/
    private String intfLogId        = "";
	/**�������̽� ID*/
	private String intfId 		    = "";
	/**����ð�*/
    private String exeTime          = "";
    /**������*/
    private String exeBy            = "";
    /**������ desc*/
    private String exeByDesc        = "";
    /**��������*/
    private String rtnYn            = "";
    /**���� �� �޼���*/
    private String rtnMsg           = "";
    /**����α�*/
    private String exeLog           = "";
    
    public String getIntfLogId()
    {
        return intfLogId;
    }
    public void setIntfLogId(String intfLogId)
    {
        this.intfLogId = intfLogId;
    }
    public String getIntfId()
    {
        return intfId;
    }
    public void setIntfId(String intfId)
    {
        this.intfId = intfId;
    }
    public String getExeTime()
    {
        return exeTime;
    }
    public void setExeTime(String exeTime)
    {
        this.exeTime = exeTime;
    }
    public String getExeBy()
    {
        return exeBy;
    }
    public void setExeBy(String exeBy)
    {
        this.exeBy = exeBy;
    }
    public String getExeByDesc()
    {
        return exeByDesc;
    }
    public void setExeByDesc(String exeByDesc)
    {
        this.exeByDesc = exeByDesc;
    }
    public String getRtnYn()
    {
        return rtnYn;
    }
    public void setRtnYn(String rtnYn)
    {
        this.rtnYn = rtnYn;
    }
    public String getRtnMsg()
    {
        return rtnMsg;
    }
    public void setRtnMsg(String rtnMsg)
    {
        this.rtnMsg = rtnMsg;
    }
    public String getExeLog()
    {
        return exeLog;
    }
    public void setExeLog(String exeLog)
    {
        this.exeLog = exeLog;
    }
    
}
