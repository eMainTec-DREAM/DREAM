package dream.consult.comp.user.dto;

import common.bean.BaseDTO;

/**
 * Consult 권한그룹 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class ConsultCompUsrGrpCommonDTO extends BaseDTO
{
	/** Page ID */
	private String pageId				= "";
	/** 회사코드 */
	private String compNo                  = "";
	/** 권한그룹Id */
	private String usrGrpId                = "";
	/** 권한그룹No */
	private String usrGrpNo                = "";
	/** 저장 Json Array */
	private String jsonArray				= "";
	/** selected Menu Id */
	private String menuId					= "";
	
	/** Filter-회사코드 */
	private String filterCompNo 		   = "";
	/** Filter-그룹명 */
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
