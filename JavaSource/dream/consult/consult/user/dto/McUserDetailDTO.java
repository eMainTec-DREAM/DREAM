package dream.consult.consult.user.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사용자 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class McUserDetailDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo 			= "";
	/** 사용자 ID */
	private String userId 			= "";
	/** 사용자 번호 */
	private String userNo 			= "";
	/** 비밀번호 */
	private String password 		= "";
	/** 사용자명 */
	private String userName 		= "";
	/** 사용자그룹Id */
	private String usrGrpId         = "";
	/** 사용자그룹명 */
	private String usrGrpName       = "";
	/** 초기화면 Id */
	private String initMenuId		= "";
	/** 초기화면명  */
	private String initMenuDesc     = "";
	/** 사원 ID */
	private String empId            = "";
	/** 사원 번호 */
	private String empNo            = "";
	/** 사원명 */
	private String empName          = "";
	/** 핸드폰 */
	private String mphone           = "";
	/** 이메일 */
	private String email            = "";
	/** 사용여부  */
	private String isUse            = "";
	/** 사용여부명 */
	private String isUseDesc        = "";
	/** 등록일 */
	private String regDate	        = "";
	/** 최근접근일 */
	private String loginDate		= "";
	/** 모니터링 대상여부 */
	private String isMonitor		= "";
	/** 교대조id */
	private String shiftTypeId		= "";
	/** 교대조명 */
	private String shiftTypeDesc	= "";
	/** 설비유형id */
	private String eqCtgTypeId		= "";
	/** 설비유형명 */
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
