package dream.consult.consult.user.dto;

import common.bean.BaseDTO;

/**
 * ����� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class McUserCommonDTO extends BaseDTO
{
	/** �����Id */
	private String userId                  = "";
	/** ����ڹ�ȣ */
	private String userNo                  = "";
	/** ������ */
	private String userName                = "";
	
	/** Filter-ȸ���ڵ� */
	private String filterCompNo 		   = "";
	/** Filter-���� */
	private String filterUsrGrpId          = "";
	/** Filter-���Ѹ� */
	private String filterUsrGrpName        = "";
	/** Filter-�α��ΰ��� */
	private String filterUserNo            = "";
	/** Filter-������ */
	private String filterUserName          = "";
	/** Filter-�μ�Id */
	private String filterDeptId 		   = "";
	/** Filter-�μ��� */
	private String filterDeptDesc 		   = "";
	/** Filter-��������Id */
	private String filterEqCtgTypeId 	   = "";
	/** Filter-�������� */
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
