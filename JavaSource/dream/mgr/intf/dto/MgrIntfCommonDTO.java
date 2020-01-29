package dream.mgr.intf.dto;

import common.bean.BaseDTO;
/**
 * Interface Page - ���� DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrIntfCommonDTO extends BaseDTO
{
	/**�������̽� ID*/
	private String intfId 			    = "";
	/**�������̽� NO*/
	private String intfNo 			    = "";
	/**�������̽� ��*/
	private String intfDesc 			= "";
	/**Filter �������̽���*/
	private String filterIntfDesc   	= "";
	/**Filter ��뿩�� ID*/
	private String filterIsUse  		= "";
	/**Filter ��뿩�� */
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
