package dream.invt.list.dto;

import common.bean.BaseDTO;

/**
 * DTO
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 */
public class InvtWoRsltDTO extends BaseDTO {
    /** ���������۾�id */
    private String invtworkId           = "";
    /** ��������id */
    private String invtlistId           = "";
	/** �۾�id */
	private String wkorId 				= "";
	/** �۾�desc */
	private String wkorDesc 			= "";
	/** �����۾�������� */
	private String invtworkMethod       = "";
	
    public String getInvtworkMethod()
    {
        return invtworkMethod;
    }
    public void setInvtworkMethod(String invtworkMethod)
    {
        this.invtworkMethod = invtworkMethod;
    }
    public String getInvtlistId()
    {
        return invtlistId;
    }
    public void setInvtlistId(String invtlistId)
    {
        this.invtlistId = invtlistId;
    }
    public String getInvtworkId()
    {
        return invtworkId;
    }
    public void setInvtworkId(String invtworkId)
    {
        this.invtworkId = invtworkId;
    }
    public String getWkorId()
    {
        return wkorId;
    }
    public void setWkorId(String wkorId)
    {
        this.wkorId = wkorId;
    }
    public String getWkorDesc()
    {
        return wkorDesc;
    }
    public void setWkorDesc(String wkorDesc)
    {
        this.wkorDesc = wkorDesc;
    }
	
}
