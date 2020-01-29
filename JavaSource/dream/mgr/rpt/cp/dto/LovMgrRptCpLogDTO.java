package dream.mgr.rpt.cp.dto;

import common.bean.BaseDTO;

/**
 * 출력 로그 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class LovMgrRptCpLogDTO extends BaseDTO
{
    /** 출력물 실행 LOGID */
    private String rptCpFileAccLogId    = "";
    /** 출력물 파일 ID */
    private String rptCpFileId          = "";
    /** 회사별 출력물 ID */
    private String rptCpListId 	        = "";
    /** 사용자 ID */
    private String userNo 	            = "";
    /** 사용자명 */
    private String userName 	        = "";
    /** 실행일자 */
    private String exeDate 		        = "";
    /** 실행시간 */
    private String exeTime 		        = "";
    /** 실행터미널No */
    private String terminalNo 		    = "";
    /** 실행파라미터 */
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
