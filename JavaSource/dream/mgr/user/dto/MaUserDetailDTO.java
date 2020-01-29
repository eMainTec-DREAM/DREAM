package dream.mgr.user.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사용자 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaUserDetailDTO extends BaseDTO
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
	/** 잠김여부  */
	private String isLocked         = "";
	/** 잠김여부명 */
	private String isLockedDesc     = "";
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
	/** 메뉴표시id */
	private String menuDisplayId	= "";
	/** 메뉴표시명 */
	private String menuDisplayDesc	= "";

    /** 위치ID */
    private String eqLocId			= "";
    /** 위치명 */
    private String eqLocDesc		= "";
	/** Filter용부서Id */
	private String filterDeptId 	= "";
	/** Filter용 부서명 */
	private String filterDeptDesc 	= "";
	/** Filter 창고코드 */
	private String filterWcodeId 	= "";
	/** Filter 창고명 */
	private String filterWcodeDesc 	= "";
	/** Filter작업그룹  Id */
	private String filterWkCtrId 	= "";
	/** Filter작업그룹 명 */
	private String filterWkCtrDesc 	= "";
	/** Filter작업담당자 Id */
	private String filterEmpId 		= "";
	/** Filter작업담당자 명 */
	private String filterEmpDesc 	= "";
	
	/** 문서권한 Id */
	private String securGradeId		= "";
	/** 문서권한명 */
	private String securGradeDesc	= "";
	/** resetPassword */
    private String resetPassword    = "";
    /** OTP key */
    private String otpKey           = "";
    
    /** 자주보는 사용부서Id */
	private String filterUsageDeptId 	= "";
	/** 자주보는 사용 부서명 */
	private String filterUsageDeptDesc 	= "";
	/** Bee초기화면Id */
	private String beeInitMenuId		= "";
	/** Bee초기화면명 */
	private String beeInitMenuDesc		= "";
	
	/** 직영여부 */
	private String isDirect				= "";
	/** 직영여부 */
	private String isDirectDesc			= "";
	/** 거래처 */
	private String vendorId				= "";
	private String vendorDesc			= "";
	/** 자주보는 거래처 */
	private String filterVendorId		= "";
	private String filterVendorDesc		= "";
	/** 알람여부 */
    private String alarmViewYn          = "";
    /** 알람여부 */
    private String alarmViewYnDesc      = "";
	
	public String getAlarmViewYn()
    {
        return alarmViewYn;
    }
    public void setAlarmViewYn(String alarmViewYn)
    {
        this.alarmViewYn = alarmViewYn;
    }
    public String getAlarmViewYnDesc()
    {
        return alarmViewYnDesc;
    }
    public void setAlarmViewYnDesc(String alarmViewYnDesc)
    {
        this.alarmViewYnDesc = alarmViewYnDesc;
    }
    public String getIsDirectDesc()
    {
        return isDirectDesc;
    }
    public void setIsDirectDesc(String isDirectDesc)
    {
        this.isDirectDesc = isDirectDesc;
    }
    public String getIsDirect() {
		return isDirect;
	}
	public void setIsDirect(String isDirect) {
		this.isDirect = isDirect;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorDesc() {
		return vendorDesc;
	}
	public void setVendorDesc(String vendorDesc) {
		this.vendorDesc = vendorDesc;
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
	public String getBeeInitMenuId() {
		return beeInitMenuId;
	}
	public void setBeeInitMenuId(String beeInitMenuId) {
		this.beeInitMenuId = beeInitMenuId;
	}
	public String getBeeInitMenuDesc() {
		return beeInitMenuDesc;
	}
	public void setBeeInitMenuDesc(String beeInitMenuDesc) {
		this.beeInitMenuDesc = beeInitMenuDesc;
	}
	public String getFilterUsageDeptId() {
		return filterUsageDeptId;
	}
	public void setFilterUsageDeptId(String filterUsageDeptId) {
		this.filterUsageDeptId = filterUsageDeptId;
	}
	public String getFilterUsageDeptDesc() {
		return filterUsageDeptDesc;
	}
	public void setFilterUsageDeptDesc(String filterUsageDeptDesc) {
		this.filterUsageDeptDesc = filterUsageDeptDesc;
	}
	public String getResetPassword()
    {
        return resetPassword;
    }
    public String getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}
	public String getIsLockedDesc() {
		return isLockedDesc;
	}
	public void setIsLockedDesc(String isLockedDesc) {
		this.isLockedDesc = isLockedDesc;
	}
	public void setResetPassword(String resetPassword)
    {
        this.resetPassword = resetPassword;
    }
    public String getOtpKey()
    {
        return otpKey;
    }
    public void setOtpKey(String otpKey)
    {
        this.otpKey = otpKey;
    }
    public String getSecurGradeId() {
		return securGradeId;
	}
	public void setSecurGradeId(String securGradeId) {
		this.securGradeId = securGradeId;
	}
	public String getSecurGradeDesc() {
		return securGradeDesc;
	}
	public void setSecurGradeDesc(String securGradeDesc) {
		this.securGradeDesc = securGradeDesc;
	}
	public String getMenuDisplayId() {
		return menuDisplayId;
	}
	public void setMenuDisplayId(String menuDisplayId) {
		this.menuDisplayId = menuDisplayId;
	}
	public String getMenuDisplayDesc() {
		return menuDisplayDesc;
	}
	public void setMenuDisplayDesc(String menuDisplayDesc) {
		this.menuDisplayDesc = menuDisplayDesc;
	}
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
		super.setAuditKey(userId);
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
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterWcodeId() {
		return filterWcodeId;
	}
	public void setFilterWcodeId(String filterWcodeId) {
		this.filterWcodeId = filterWcodeId;
	}
	public String getFilterWcodeDesc() {
		return filterWcodeDesc;
	}
	public void setFilterWcodeDesc(String filterWcodeDesc) {
		this.filterWcodeDesc = filterWcodeDesc;
	}
	public String getFilterWkCtrId() {
		return filterWkCtrId;
	}
	public void setFilterWkCtrId(String filterWkCtrId) {
		this.filterWkCtrId = filterWkCtrId;
	}
	public String getFilterWkCtrDesc() {
		return filterWkCtrDesc;
	}
	public void setFilterWkCtrDesc(String filterWkCtrDesc) {
		this.filterWkCtrDesc = filterWkCtrDesc;
	}
	public String getFilterEmpId() {
		return filterEmpId;
	}
	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}
	public String getFilterEmpDesc() {
		return filterEmpDesc;
	}
	public void setFilterEmpDesc(String filterEmpDesc) {
		this.filterEmpDesc = filterEmpDesc;
	}
	
	
}
