package dream.pers.mamylink.dto;

import common.bean.BaseDTO;

/**
 * ���ѱ׷� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaMyLinkDTO extends BaseDTO
{	
	/** ȸ���ڵ� */
	private String compNo                  = "";
	/** ���ѱ׷�Id */
	private String usrGrpId                = "";
	/** ���ѱ׷�No */
	private String usrGrpNo                = "";
	/** �����Id */
	private String userId                  = "";
	/** Filter-ȸ���ڵ� */
	private String filterCompNo 		   = "";
	/** Filter-�׷�� */
	private String filterGroupName		   = "";
	/** menuId */
	private String menuId                  = "";
	/** �޴��� */
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