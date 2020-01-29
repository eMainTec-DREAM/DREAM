package dream.consult.comp.intf.dto;

import common.bean.BaseDTO;
/**
 * Interface Log Page - List DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class ConsultCompIntfMapListDTO extends BaseDTO
{
	/**�������̽� ID*/
	private String intfId 			    = "";
	/**�������̽�Map ID*/
    private String intfMapId            = "";
	/**ȸ���ڵ�*/
	private String compNo 		    	= "";
    
    public String getIntfMapId() {
		return intfMapId;
	}
	public void setIntfMapId(String intfMapId) {
		this.intfMapId = intfMapId;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getIntfId()
    {
        return intfId;
    }
    public void setIntfId(String intfId)
    {
        this.intfId = intfId;
    }
}
