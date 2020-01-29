package intf.common.support.dto;

import common.bean.BaseDTO;
import intf.common.batch.dto.CommonBatchDTO;

/**
 * Interface Supporter Common DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class InterfaceSupportDTO extends BaseDTO
{
	/** Interface Id */
	private String intfId			= "";
	/** Interface Log Id */
    private String intfLogId		= "";
    /** Interface No */
    private String intfNo			= "";
    /** Interface Type */
    private String intfType			= "";
    /** 실행자 */
    private String exeBy			= "";
    /** 성공여부 */
    private String rtnYn			= "";
    /** 실패시 메세지 */
    private String rtnMsg			= "";
    /** 실행로그 */
    private String exeLog			= "";
    /** 성공일자+시간 */
    private String successTime		= "";
    /** Exec Type */
    private int execType            = CommonBatchDTO.BATCH;
    
    @Deprecated
    public InterfaceSupportDTO(String compNo, String intfNo, String intfType)
    {
        this.setCompNo(compNo);
        this.setIntfNo(intfNo);
        this.setIntfType(intfType);
    }
    @Deprecated
    public InterfaceSupportDTO(String compNo, String intfNo, String intfType, String userNo)
    {
        this.setCompNo(compNo);
        this.setIntfNo(intfNo);
        this.setIntfType(intfType);
        this.setExeBy(userNo);
    }
    public InterfaceSupportDTO(String compNo, String intfNo, String intfType, String userNo, int execType)
    {
        this.setCompNo(compNo);
        this.setIntfNo(intfNo);
        this.setIntfType(intfType);
        this.setExeBy(userNo);
        this.setExecType(execType);
    }
    
    public int getExecType()
    {
        return execType;
    }
    public void setExecType(int execType)
    {
        this.execType = execType;
    }
    public String getSuccessTime()
    {
        return successTime;
    }
    public void setSuccessTime(String successTime)
    {
        this.successTime = successTime;
    }
    public String getIntfId()
    {
        return intfId;
    }
    public void setIntfId(String intfId)
    {
        this.intfId = intfId;
    }
    public String getIntfLogId()
    {
        return intfLogId;
    }
    public void setIntfLogId(String intfLogId)
    {
        this.intfLogId = intfLogId;
    }
    public String getIntfNo()
    {
        return intfNo;
    }
    public void setIntfNo(String intfNo)
    {
        this.intfNo = intfNo;
    }
    public String getIntfType()
    {
        return intfType;
    }
    public void setIntfType(String intfType)
    {
        this.intfType = intfType;
    }
    public String getExeBy()
    {
        return exeBy;
    }
    public void setExeBy(String exeBy)
    {
        this.exeBy = exeBy;
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
