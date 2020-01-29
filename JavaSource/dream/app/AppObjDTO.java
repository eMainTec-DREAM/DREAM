package dream.app;

import common.bean.BaseDTO;

/**
 * 결재 Object DTO
 * @author  javaworker
 * @version $Id: AppObjDTO.java,v 1.1 2013/08/30 09:10:05 javaworker Exp $ 
 * @since   1.0
 */
public class AppObjDTO extends BaseDTO
{
    /** 결재구분 */
    private String apprType = "";
    /** 문서 KEY ID */
    private String objectId = "";
    /** 결재상태 */
    private String apprStatus   = "";
    
    
    
    
    /** 결재처리상태 */
    private String wfStatus = "";
    /** 문서번호 */
    private String objectNo = "";
    /** 결재종류 */
    private String wfType = "";
    
    
    public String getApprType()
    {
        return apprType;
    }
    public void setApprType(String apprType)
    {
        this.apprType = apprType;
    }
    public String getObjectId()
    {
        return objectId;
    }
    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }
    public String getApprStatus()
    {
        return apprStatus;
    }
    public void setApprStatus(String apprStatus)
    {
        this.apprStatus = apprStatus;
    }
    public String getWfStatus()
    {
        return wfStatus;
    }
    public void setWfStatus(String wfStatus)
    {
        this.wfStatus = wfStatus;
    }
    public String getObjectNo()
    {
        return objectNo;
    }
    public void setObjectNo(String objectNo)
    {
        this.objectNo = objectNo;
    }
    public String getWfType()
    {
        return wfType;
    }
    public void setWfType(String wfType)
    {
        this.wfType = wfType;
    }
}
