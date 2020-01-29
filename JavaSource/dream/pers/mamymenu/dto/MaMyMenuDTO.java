package dream.pers.mamymenu.dto;

import common.bean.BaseDTO;

/**
 * 사용자메뉴 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaMyMenuDTO extends BaseDTO
{
	
	/** 회사코드 */
	private String compNo                  = "";
	/** 권한그룹Id */
	private String usrGrpId                = "";
	/** 권한그룹No */
	private String usrGrpNo                = "";
	/** 사용자Id */
	private String userId                = "";
	/** Filter-회사코드 */
	private String filterCompNo 		   = "";
	/** Filter-그룹명 */
	private String filterGroupName		   = "";
	
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
