package dream.mgr.user.dto;

import common.bean.BaseDTO;

/**
 * ����� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaUserCommonDTO extends BaseDTO
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
	/** Filter-��뿩��  */
    private String filterIsUse            = "";
    /** Filter-��뿩�θ� */
    private String filterIsUseDesc        = "";
    /** Filter-��迩��  */
    private String filterIsLocked         = "";
    /** Filter-��迩�θ� */
    private String filterIsLockedDesc     = "";
	

    /** ���� */
	private String filterPlantId 		  = "";
	private String filterPlantDesc 		  = "";
	
	/** ���� ���� ���� */
	private String usrPlantauthId         = "";
	
	/** ����͸���� ���� */
	private String filterIsMonitor        = "";

    /** �������� */
    private String filterIsDirectDesc       = "";
    /** �ŷ�ó */
	private String filterVendorId 		  	= "";
	private String filterVendorDesc 		= "";
	
    public String getFilterIsDirectDesc() {
		return filterIsDirectDesc;
	}
	public void setFilterIsDirectDesc(String filterIsDirectDesc) {
		this.filterIsDirectDesc = filterIsDirectDesc;
	}
	public String getFilterVendorId() {
		return filterVendorId;
	}
	public void setFilterVendorId(String filterVendorId) {
		this.filterVendorId = filterVendorId;
	}
	public String getFilterVendorDesc() {
		return filterVendorDesc;
	}
	public void setFilterVendorDesc(String filterVendorDesc) {
		this.filterVendorDesc = filterVendorDesc;
	}
	public String getFilterIsMonitor() {
		return filterIsMonitor;
	}
	public void setFilterIsMonitor(String filterIsMonitor) {
		this.filterIsMonitor = filterIsMonitor;
	}
	public String getUsrPlantauthId()
    {
        return usrPlantauthId;
    }
    public String getFilterIsLocked() {
		return filterIsLocked;
	}
	public void setFilterIsLocked(String filterIsLocked) {
		this.filterIsLocked = filterIsLocked;
	}
	public String getFilterIsLockedDesc() {
		return filterIsLockedDesc;
	}
	public void setFilterIsLockedDesc(String filterIsLockedDesc) {
		this.filterIsLockedDesc = filterIsLockedDesc;
	}
	public void setUsrPlantauthId(String usrPlantauthId)
    {
        this.usrPlantauthId = usrPlantauthId;
        super.setAuditKey(usrPlantauthId);
    }
    public String getFilterIsUse()
    {
        return filterIsUse;
    }
    public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
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
		super.setAuditKey(userId);
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
