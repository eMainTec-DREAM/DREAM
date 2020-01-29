package dream.consult.comp.user.dto;

import common.bean.BaseDTO;

/**
 * CompUser Page - Detail DTO
 * @author youngjoo38
 * @version $Id: ConsultCompUserDetailDTO.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class ConsultCompUserDetailDTO extends BaseDTO
{
    /** 사용자ID*/ 
    private String userId            = "";
    
    /** 회사코드*/ 
    private String compNo            = "";
    /** 회사명*/ 
    private String compDesc          = "";
    /** 로그인 계정*/ 
    private String userNo            = "";
    /** 로그인 계정명*/ 
    private String userName          = "";
    /** 사원 ID*/
    private String empId             = "";
    /** 사원명*/ 
    private String empName           = "";
    /** 권한그룹Id */ 
    private String usrGrpId          = ""; 
    /** 권한그룹명 */ 
    private String usrGrpDesc        = "";
    /** 연락처 */
    private String phone            = "";
    /** 초기화면 ID */
    private String initMenuId        = "";
    /** 초기화면 */ 
    private String initMenuDesc      = "";
    /** 이메일 */
    private String email             = "";
    /** 교대조 ID */
    private String shiftTypeId       = "";
    /** 교대조명 */ 
    private String shiftTypeDesc     = "";
    /** 사용여부 ID*/
    private String isUseId           = "";
    /** 사용여부 */ 
    private String isUseDesc         = "";
    /** 설비위치ID */
    private String eqLocId           = "";
    /** 설비위치명 */ 
    private String eqLocDesc         = "";
    /** 알람여부ID */
    private String alarmViewYnId     = "";
    /** 알람여부 */ 
    private String alarmViewYn       = "";
    /** 작업그룹ID */
    private String wkctrId           = "";
    /** 작업그룹명 */ 
    private String wkctrDesc         = "";
    /** 관리설비유형ID */
    private String eqCtgTypeId       = "";
    /** 관리설비유형명 */ 
    private String eqCtgTypeDesc     = "";
    /** 자주보는 부서 ID*/
    private String deptId            = "";
    /** 자주보는 부서명*/ 
    private String deptDesc          = "";    
    /** 메뉴표시 ID */
    private String menuDisplayId     = "";
    /** 메뉴표시 */ 
    private String menuDisplayDesc   = "";
    /** 등록일 */
    private String regDate           = "";
    private String passWord           = "";
    
    
    public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getCompDesc()
    {
        return compDesc;
    }
    public void setCompDesc(String compDesc)
    {
        this.compDesc = compDesc;
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
    public String getEmpId()
    {
        return empId;
    }
    public void setEmpId(String empId)
    {
        this.empId = empId;
    }
    public String getEmpName()
    {
        return empName;
    }
    public void setEmpName(String empName)
    {
        this.empName = empName;
    }
    public String getUsrGrpId()
    {
        return usrGrpId;
    }
    public void setUsrGrpId(String usrGrpId)
    {
        this.usrGrpId = usrGrpId;
    }
    public String getUsrGrpDesc()
    {
        return usrGrpDesc;
    }
    public void setUsrGrpDesc(String usrGrpDesc)
    {
        this.usrGrpDesc = usrGrpDesc;
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
    public String getShiftTypeId()
    {
        return shiftTypeId;
    }
    public void setShiftTypeId(String shiftTypeId)
    {
        this.shiftTypeId = shiftTypeId;
    }
    public String getShiftTypeDesc()
    {
        return shiftTypeDesc;
    }
    public void setShiftTypeDesc(String shiftTypeDesc)
    {
        this.shiftTypeDesc = shiftTypeDesc;
    }
    public String getIsUseId()
    {
        return isUseId;
    }
    public void setIsUseId(String isUseId)
    {
        this.isUseId = isUseId;
    }
    public String getIsUseDesc()
    {
        return isUseDesc;
    }
    public void setIsUseDesc(String isUseDesc)
    {
        this.isUseDesc = isUseDesc;
    }
    public String getEqLocId()
    {
        return eqLocId;
    }
    public void setEqLocId(String eqLocId)
    {
        this.eqLocId = eqLocId;
    }
    public String getEqLocDesc()
    {
        return eqLocDesc;
    }
    public void setEqLocDesc(String eqLocDesc)
    {
        this.eqLocDesc = eqLocDesc;
    }
    public String getAlarmViewYnId()
    {
        return alarmViewYnId;
    }
    public void setAlarmViewYnId(String alarmViewYnId)
    {
        this.alarmViewYnId = alarmViewYnId;
    }
    public String getAlarmViewYn()
    {
        return alarmViewYn;
    }
    public void setAlarmViewYn(String alarmViewYn)
    {
        this.alarmViewYn = alarmViewYn;
    }
    public String getWkctrId()
    {
        return wkctrId;
    }
    public void setWkctrId(String wkctrId)
    {
        this.wkctrId = wkctrId;
    }
    public String getWkctrDesc()
    {
        return wkctrDesc;
    }
    public void setWkctrDesc(String wkctrDesc)
    {
        this.wkctrDesc = wkctrDesc;
    }
    public String getEqCtgTypeId()
    {
        return eqCtgTypeId;
    }
    public void setEqCtgTypeId(String eqCtgTypeId)
    {
        this.eqCtgTypeId = eqCtgTypeId;
    }
    public String getEqCtgTypeDesc()
    {
        return eqCtgTypeDesc;
    }
    public void setEqCtgTypeDesc(String eqCtgTypeDesc)
    {
        this.eqCtgTypeDesc = eqCtgTypeDesc;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getMenuDisplayId()
    {
        return menuDisplayId;
    }
    public void setMenuDisplayId(String menuDisplayId)
    {
        this.menuDisplayId = menuDisplayId;
    }
    public String getMenuDisplayDesc()
    {
        return menuDisplayDesc;
    }
    public void setMenuDisplayDesc(String menuDisplayDesc)
    {
        this.menuDisplayDesc = menuDisplayDesc;
    }
    public String getRegDate()
    {
        return regDate;
    }
    public void setRegDate(String regDate)
    {
        this.regDate = regDate;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    
}
