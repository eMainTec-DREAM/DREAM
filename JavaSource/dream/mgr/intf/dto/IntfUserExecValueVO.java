package dream.mgr.intf.dto;

/**
 * �������̽� �Ķ���� - DTO
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
    /** �������̽� ���� ���� ID */
    private String intfUserExecId       = "";
    /** �������̽� ID */
    private String intfId               = "";
    /** �������̽� Map Id */
    private String intfMapId            = "";
    /** Parameter �� */
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