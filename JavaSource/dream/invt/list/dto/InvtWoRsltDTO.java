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
    /** 설비투자작업id */
    private String invtworkId           = "";
    /** 설비투자id */
    private String invtlistId           = "";
	/** 작업id */
	private String wkorId 				= "";
	/** 작업desc */
	private String wkorDesc 			= "";
	/** 투자작업생성방법 */
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
