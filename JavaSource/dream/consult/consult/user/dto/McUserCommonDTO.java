package dream.consult.consult.user.dto;

import common.bean.BaseDTO;

/**
 * 사용자 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class McUserCommonDTO extends BaseDTO
{
	/** 사용자Id */
	private String userId                  = "";
	/** 사용자번호 */
	private String userNo                  = "";
	/** 계정명 */
	private String userName                = "";
	
	/** Filter-회사코드 */
	private String filterCompNo 		   = "";
	/** Filter-권한 */
	private String filterUsrGrpId          = "";
	/** Filter-권한명 */
	private String filterUsrGrpName        = "";
	/** Filter-로그인계정 */
	private String filterUserNo            = "";
	/** Filter-계정명 */
	private String filterUserName          = "";
	/** Filter-부서Id */
	private String filterDeptId 		   = "";
	/** Filter-부서명 */
	private String filterDeptDesc 		   = "";
	/** Filter-설비유형Id */
	private String filterEqCtgTypeId 	   = "";
	/** Filter-설비유형 */
	private String filterEqCtgTypeDesc 	   = "";
	
	
	public String getFilterEqCtgTypeId() {
		return filterEqCtgTypeId;
	}
	public void setFilterEqCtgTypeId(String filterEqCtgTypeId) {
		this.filterEqCtgTypeId = filterEqCtgTypeId;
	}
	public String getFilterEqCtgTypeDesc() {
		return filterEqCtgTypeDesc;
	}
	public void setFilterEqCtgTypeDesc(String filterEqCtgTypeDesc) {
		this.filterEqCtgTypeDesc = filterEqCtgTypeDesc;
	}
	public String getFilterUserNo()
    {
        return filterUserNo;
    }
    public void setFilterUserNo(String filterUserNo)
    {
        this.filterUserNo = filterUserNo;
    }
    public String getUserNo()
    {
        return userNo;
    }
    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }
    public String getUserName() 
	{
		return userName;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	public String getFilterCompNo() 
	{
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) 
	{
		this.filterCompNo = filterCompNo;
	}
	
	public String getFilterUserName() 
	{
		return filterUserName;
	}
	public void setFilterUserName(String filterUserName) 
	{
		this.filterUserName = filterUserName;
	}
    public String getFilterUsrGrpId()
    {
        return filterUsrGrpId;
    }
    public void setFilterUsrGrpId(String filterUsrGrpId)
    {
        this.filterUsrGrpId = filterUsrGrpId;
    }
    public String getFilterUsrGrpName()
    {
        return filterUsrGrpName;
    }
    public void setFilterUsrGrpName(String filterUsrGrpName)
    {
        this.filterUsrGrpName = filterUsrGrpName;
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
	
}
