package dream.mgr.rpt.cp.dto;

import common.bean.BaseDTO;

/**
 * ��� �α� DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class LovMgrRptCpLogDTO extends BaseDTO
{
    /** ��¹� ���� LOGID */
    private String rptCpFileAccLogId    = "";
    /** ��¹� ���� ID */
    private String rptCpFileId          = "";
    /** ȸ�纰 ��¹� ID */
    private String rptCpListId 	        = "";
    /** ����� ID */
    private String userNo 	            = "";
    /** ����ڸ� */
    private String userName 	        = "";
    /** �������� */
    private String exeDate 		        = "";
    /** ����ð� */
    private String exeTime 		        = "";
    /** �����͹̳�No */
    private String terminalNo 		    = "";
    /** �����Ķ���� */
    private String paramValue 		    = "";
    
    public String getRptCpFileAccLogId()
    {
        return rptCpFileAccLogId;
    }
    public void setRptCpFileAccLogId(String rptCpFileAccLogId)
    {
        this.rptCpFileAccLogId = rptCpFileAccLogId;
    }
    public String getRptCpFileId()
    {
        return rptCpFileId;
    }
    public void setRptCpFileId(String rptCpFileId)
    {
        this.rptCpFileId = rptCpFileId;
    }
    public String getRptCpListId()
    {
        return rptCpListId;
    }
    public void setRptCpListId(String rptCpListId)
    {
        this.rptCpListId = rptCpListId;
    }
    public String getUserNo()
    {
        return userNo;
    }
    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getExeDate()
    {
        return exeDate;
    }
    public void setExeDate(String exeDate)
    {
        this.exeDate = exeDate;
    }
    public String getExeTime()
    {
        return exeTime;
    }
    public void setExeTime(String exeTime)
    {
        this.exeTime = exeTime;
    }
    public String getTerminalNo()
    {
        return terminalNo;
    }
    public void setTerminalNo(String terminalNo)
    {
        this.terminalNo = terminalNo;
    }
    public String getParamValue()
    {
        return paramValue;
    }
    public void setParamValue(String paramValue)
    {
        this.paramValue = paramValue;
    }
}
