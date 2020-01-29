package dream.mgr.intf.dto;

import common.bean.BaseDTO;
/**
 * Interface Page - 공통 DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrIntfCommonDTO extends BaseDTO
{
	/**인터페이스 ID*/
	private String intfId 			    = "";
	/**인터페이스 NO*/
	private String intfNo 			    = "";
	/**인터페이스 명*/
	private String intfDesc 			= "";
	/**Filter 인터페이스명*/
	private String filterIntfDesc   	= "";
	/**Filter 사용여부 ID*/
	private String filterIsUse  		= "";
	/**Filter 사용여부 */
	private String filterIsUseDesc	 	= "";
	
    public String getIntfDesc()
    {
        return intfDesc;
    }
    public void setIntfDesc(String intfDesc)
    {
        this.intfDesc = intfDesc;
    }
    public String getIntfNo()
    {
        return intfNo;
    }
    public void setIntfNo(String intfNo)
    {
        this.intfNo = intfNo;
    }
    public String getIntfId()
    {
        return intfId;
    }
    public void setIntfId(String intfId)
    {
        this.intfId = intfId;
        super.setAuditKey(intfId);
    }
    public String getFilterIntfDesc()
    {
        return filterIntfDesc;
    }
    public void setFilterIntfDesc(String filterIntfDesc)
    {
        this.filterIntfDesc = filterIntfDesc;
    }
    public String getFilterIsUse()
    {
        return filterIsUse;
    }
    public void setFilterIsUse(String filterIsUse)
    {
        this.filterIsUse = filterIsUse;
    }
    public String getFilterIsUseDesc()
    {
        return filterIsUseDesc;
    }
    public void setFilterIsUseDesc(String filterIsUseDesc)
    {
        this.filterIsUseDesc = filterIsUseDesc;
    }
	
}
