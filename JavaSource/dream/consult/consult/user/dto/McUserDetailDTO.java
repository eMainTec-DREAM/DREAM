package dream.consult.consult.user.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class McUserDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 			= "";
	/** ����� ID */
	private String userId 			= "";
	/** ����� ��ȣ */
	private String userNo 			= "";
	/** ��й�ȣ */
	private String password 		= "";
	/** ����ڸ� */
	private String userName 		= "";
	/** ����ڱ׷�Id */
	private String usrGrpId         = "";
	/** ����ڱ׷�� */
	private String usrGrpName       = "";
	/** �ʱ�ȭ�� Id */
	private String initMenuId		= "";
	/** �ʱ�ȭ���  */
	private String initMenuDesc     = "";
	/** ��� ID */
	private String empId            = "";
	/** ��� ��ȣ */
	private String empNo            = "";
	/** ����� */
	private String empName          = "";
	/** �ڵ��� */
	private String mphone           = "";
	/** �̸��� */
	private String email            = "";
	/** ��뿩��  */
	private String isUse            = "";
	/** ��뿩�θ� */
	private String isUseDesc        = "";
	/** ����� */
	private String regDate	        = "";
	/** �ֱ������� */
	private String loginDate		= "";
	/** ����͸� ��󿩺� */
	private String isMonitor		= "";
	/** ������id */
	private String shiftTypeId		= "";
	/** �������� */
	private String shiftTypeDesc	= "";
	/** ��������id */
	private String eqCtgTypeId		= "";
	/** ���������� */
	private String eqCtgTypeDesc	= "";
	
	public String getEqCtgTypeId() {
		return eqCtgTypeId;
	}
	public void setEqCtgTypeId(String eqCtgTypeId) {
		this.eqCtgTypeId = eqCtgTypeId;
	}
	public String getEqCtgTypeDesc() {
		return eqCtgTypeDesc;
	}
	public void setEqCtgTypeDesc(String eqCtgTypeDesc) {
		this.eqCtgTypeDesc = eqCtgTypeDesc;
	}
	public String getShiftTypeId() {
		return shiftTypeId;
	}
	public void setShiftTypeId(String shiftTypeId) {
		this.shiftTypeId = shiftTypeId;
	}
	public String getShiftTypeDesc() {
		return shiftTypeDesc;
	}
	public void setShiftTypeDesc(String shiftTypeDesc) {
		this.shiftTypeDesc = shiftTypeDesc;
	}
	public String getIsMonitor() {
		return isMonitor;
	}
	public void setIsMonitor(String isMonitor) {
		this.isMonitor = isMonitor;
	}
	public String getIsUseDesc()
    {
        return isUseDesc;
    }
    public void setIsUseDesc(String isUseDesc)
    {
        this.isUseDesc = isUseDesc;
    }
    public String getEmpNo()
    {
        return empNo;
    }
    public void setEmpNo(String empNo)
    {
        this.empNo = empNo;
    }
    public String getUserNo()
    {
        return userNo;
    }
    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }
    public String getUsrGrpId()
    {
        return usrGrpId;
    }
    public void setUsrGrpId(String usrGrpId)
    {
        this.usrGrpId = usrGrpId;
    }
    public String getUsrGrpName()
    {
        return usrGrpName;
    }
    public void setUsrGrpName(String usrGrpName)
    {
        this.usrGrpName = usrGrpName;
    }
    public String getInitMenuId()
    {
        return initMenuId;
    }
    public void setInitMenuId(String initMenuId)
    {
        this.initMenuId = initMenuId;
    }
    public String getInitMenuDesc()
    {
        return initMenuDesc;
    }
    public void setInitMenuDesc(String initMenuDesc)
    {
        this.initMenuDesc = initMenuDesc;
    }
    public String getEmpName() 
	{
		return empName;
	}
	public void setEmpName(String empName) 
	{
		this.empName = empName;
	}
	public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getUserName() 
	{
		return userName;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	public String getEmpId() 
	{
		return empId;
	}
	public void setEmpId(String empId) 
	{
		this.empId = empId;
	}
	public String getMphone() 
	{
		return mphone;
	}
	public void setMphone(String mphone) 
	{
		this.mphone = mphone;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getIsUse() 
	{
		return isUse;
	}
	public void setIsUse(String isUse) 
	{
		this.isUse = isUse;
	}
	public String getRegDate() 
	{
		return regDate;
	}
	public void setRegDate(String regDate) 
	{
		this.regDate = CommonUtil.convertDate(regDate);
	}
	public String getLoginDate() 
	{
		return loginDate;
	}
	public void setLoginDate(String loginDate) 
	{
		this.loginDate = CommonUtil.convertDate(loginDate);
	}
	
}
