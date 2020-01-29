package dream.mgr.intf.dto;

import common.bean.BaseDTO;
/**
 * Interface Log Page - List DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrIntfLogListDTO extends BaseDTO
{
	/**�������̽� ID*/
	private String intfId 			    = "";
	/**�������̽��α� ID*/
    private String intfLogId            = "";
    
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
	
}
