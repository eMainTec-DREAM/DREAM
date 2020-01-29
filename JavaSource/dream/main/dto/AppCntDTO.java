package dream.main.dto;

import common.bean.BaseDTO;

/**
 * Approval 건수 DTO
 * @author  javaworker
 * @version $Id: AppCntDTO.java,v 1.1 2013/08/30 09:08:49 javaworker Exp $
 * @since   1.0
 */
public class AppCntDTO extends BaseDTO
{
    /** 결재할 건수 */
    private String appCount = "0";
    /** 결재요청 건수(진행중) */
    private String reqCount = "0";
    /** 수신문서 건수 */
    private String notCount = "0";
    
    public String getAppCount()
    {
        return appCount;
    }
    public void setAppCount(String appCount)
    {
        this.appCount = appCount;
    }
    public String getReqCount()
    {
        return reqCount;
    }
    public void setReqCount(String reqCount)
    {
        this.reqCount = reqCount;
    }
    public String getNotCount()
    {
        return notCount;
    }
    public void setNotCount(String notCount)
    {
        this.notCount = notCount;
    }
}