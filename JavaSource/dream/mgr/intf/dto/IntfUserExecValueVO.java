package dream.mgr.intf.dto;

/**
 * 인터페이스 파라미터 - DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public class IntfUserExecValueVO
{
    /** key */
    private String compNo               = "";
    /** key */
    private String intfUserExecValueId  = "";
    /** 인터페이스 실행 유저 ID */
    private String intfUserExecId       = "";
    /** 인터페이스 ID */
    private String intfId               = "";
    /** 인터페이스 Map Id */
    private String intfMapId            = "";
    /** Parameter 값 */
    private String srcFieldValue        = "";
    
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getIntfUserExecValueId()
    {
        return intfUserExecValueId;
    }
    public void setIntfUserExecValueId(String intfUserExecValueId)
    {
        this.intfUserExecValueId = intfUserExecValueId;
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
    public String getIntfMapId()
    {
        return intfMapId;
    }
    public void setIntfMapId(String intfMapId)
    {
        this.intfMapId = intfMapId;
    }
    public String getSrcFieldValue()
    {
        return srcFieldValue;
    }
    public void setSrcFieldValue(String srcFieldValue)
    {
        this.srcFieldValue = srcFieldValue;
    }
    
}