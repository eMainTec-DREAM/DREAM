package dream.main.dto;

import common.bean.BaseDTO;

/**
 * My Work 건수 DTO
 * @author  javaworker
 * @version $Id: MyWorkCntDTO.java,v 1.4 2014/02/17 06:06:57 javaworker Exp $
 * @since   1.0
 */
public class MyWorkCntDTO extends BaseDTO
{
    /** 작업설계대상 건수 */
    private String woPlanCount = "0";
    /** 내 작업수행대상 건수 */
    private String woResultCount = "0";
    /** HSE Revision Request */
    private String hseRevReqCount = "0";
    /** Stop Card(Pending) */
    private String pendCardCount = "0";
    
    public String getWoPlanCount()
    {
        return woPlanCount;
    }
    public void setWoPlanCount(String woPlanCount)
    {
        this.woPlanCount = woPlanCount;
    }
    public String getWoResultCount()
    {
        return woResultCount;
    }
    public void setWoResultCount(String woResultCount)
    {
        this.woResultCount = woResultCount;
    }
    public String getHseRevReqCount()
    {
        return hseRevReqCount;
    }
    public void setHseRevReqCount(String hseRevReqCount)
    {
        this.hseRevReqCount = hseRevReqCount;
    }
    public String getPendCardCount()
    {
        return pendCardCount;
    }
    public void setPendCardCount(String pendCardCount)
    {
        this.pendCardCount = pendCardCount;
    }
}