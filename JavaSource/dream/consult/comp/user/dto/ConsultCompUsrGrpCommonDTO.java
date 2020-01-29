package dream.consult.comp.user.dto;

import common.bean.BaseDTO;

/**
 * Consult ���ѱ׷� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class ConsultCompUsrGrpCommonDTO extends BaseDTO
{
	/** Page ID */
	private String pageId				= "";
	/** ȸ���ڵ� */
	private String compNo                  = "";
	/** ���ѱ׷�Id */
	private String usrGrpId                = "";
	/** ���ѱ׷�No */
	private String usrGrpNo                = "";
	/** ���� Json Array */
	private String jsonArray				= "";
	/** selected Menu Id */
	private String menuId					= "";
	
	/** Filter-ȸ���ڵ� */
	private String filterCompNo 		   = "";
	/** Filter-�׷�� */
	private String filterGroupName		   = "";
	
	private String filterServiceType	   = "";
	
	private String filterCompDesc          = "";
	
	
	public String getFilterCompDesc()
    {
        return filterCompDesc;
    }
    public void setFilterCompDesc(String filterCompDesc)
    {
        this.filterCompDesc = filterCompDesc;
    }
    public String getFilterServiceType() {
		return filterServiceType;
	}
	public void setFilterServiceType(String filterServiceType) {
		this.filterServiceType = filterServiceType;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(String jsonArray) {
		this.jsonArray = jsonArray;
	}
	public String getUsrGrpNo() {
		return usrGrpNo;
	}
	public void setUsrGrpNo(String usrGrpNo) {
		this.usrGrpNo = usrGrpNo;
	}
	public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
	public String getUsrGrpId()
    {
        return usrGrpId;
    }
    public void setUsrGrpId(String usrGrpId)
    {
        this.usrGrpId = usrGrpId;
        super.setAuditKey(usrGrpId);
    }
    public String getFilterCompNo() 
	{
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) 
	{
		this.filterCompNo = filterCompNo;
	}
	public String getFilterGroupName() {
		return filterGroupName;
	}
	public void setFilterGroupName(String filterGroupName) 
	{
		this.filterGroupName = filterGroupName;
	}

	

}
