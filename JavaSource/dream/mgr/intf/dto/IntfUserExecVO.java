package dream.mgr.intf.dto;

/**
 * 인터페이스 파라미터 - DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public class IntfUserExecVO
{
    /** key */
    private String compNo           = "";
    /** key */
    private String intfUserExecId   = "";
    /** 인터페이스 ID */
    private String intfId           = "";
    /** 실행자 */
    private String exeBy            = "";
    
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getIntfUserExecId()
    {
        return intfUserExecId;
    }
    public void setIntfUserExecId(String intfUserExecId)
    {
        this.intfUserExecId = intfUserExecId;
    }
    public String getIntfId()
    {
        return intfId;
    }
    public void setIntfId(String intfId)
    {
        this.intfId = intfId;
    }
    public String getExeBy()
    {
        return exeBy;
    }
    public void setExeBy(String exeBy)
    {
        this.exeBy = exeBy;
    }
    
}