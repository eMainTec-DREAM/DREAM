package dream.pers.mamylink.dto;

import common.bean.BaseDTO;

/**
 * 권한그룹 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaMyLinkDTO extends BaseDTO
{	
	/** 회사코드 */
	private String compNo                  = "";
	/** 권한그룹Id */
	private String usrGrpId                = "";
	/** 권한그룹No */
	private String usrGrpNo                = "";
	/** 사용자Id */
	private String userId                  = "";
	/** Filter-회사코드 */
	private String filterCompNo 		   = "";
	/** Filter-그룹명 */
	private String filterGroupName		   = "";
	/** menuId */
	private String menuId                  = "";
	/** 메뉴명 */
	private String menuDesc                 = "";
	
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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