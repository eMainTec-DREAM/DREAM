package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * ���ڵ� �˾� DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovCdUsrdAcListDTO extends BaseDTO
{
    /** ���ڵ� ID */
    private String cdUsrdId      = "";
    /** ���ڵ� Description */
    private String cdUsrdDesc    = "";
    /** �ڵ�  */
    private String cdUsrdNo      = "";
    /** �ڵ����� ID  */
    private String cdUsrmId      = "";
    
    
    public String getCdUsrmId()
    {
        return cdUsrmId;
    }
    public void setCdUsrmId(String cdUsrmId)
    {
        this.cdUsrmId = cdUsrmId;
    }
    public String getCdUsrdNo()
    {
        return cdUsrdNo;
    }
    public void setCdUsrdNo(String cdUsrdNo)
    {
        this.cdUsrdNo = cdUsrdNo;
    }
    public String getCdUsrdId()
    {
        return cdUsrdId;
    }
    public void setCdUsrdId(String cdUsrdId)
    {
        this.cdUsrdId = cdUsrdId;
    }
    public String getCdUsrdDesc()
    {
        return cdUsrdDesc;
    }
    public void setCdUsrdDesc(String cdUsrdDesc)
    {
        this.cdUsrdDesc = cdUsrdDesc;
    }

}
