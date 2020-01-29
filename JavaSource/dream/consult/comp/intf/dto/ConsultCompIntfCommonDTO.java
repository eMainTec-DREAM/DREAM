package dream.consult.comp.intf.dto;

import common.bean.BaseDTO;
/**
 * Interface Page - ���� DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class ConsultCompIntfCommonDTO extends BaseDTO
{
	/**�������̽� ID*/
	private String intfId 			    = "";
	/**Filter �������̽���*/
	private String filterIntfDesc   	= "";
	/**Filter ȸ���ڵ�*/
	private String filterCompNo		    = "";
	/**Filter ȸ���*/
	private String filterCompDesc   	= "";
	/**Filter ��뿩�� ID*/
	private String filterIsUse  		= "";
	/**Filter ��뿩�� */
	private String filterIsUseDesc	 	= "";
	/** ȸ���ڵ�*/
	private String compNo		    	= "";
	
    public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getFilterCompNo() {
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) {
		this.filterCompNo = filterCompNo;
	}
	public String getFilterCompDesc() {
		return filterCompDesc;
	}
	public void setFilterCompDesc(String filterCompDesc) {
		this.filterCompDesc = filterCompDesc;
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
