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
    /** �����ID*/ 
    private String userId            = "";
    
    /** ȸ���ڵ�*/ 
    private String compNo            = "";
    /** ȸ���*/ 
    private String compDesc          = "";
    /** �α��� ����*/ 
    private String userNo            = "";
    /** �α��� ������*/ 
    private String userName          = "";
    /** ��� ID*/
    private String empId             = "";
    /** �����*/ 
    private String empName           = "";
    /** ���ѱ׷�Id */ 
    private String usrGrpId          = ""; 
    /** ���ѱ׷�� */ 
    private String usrGrpDesc        = "";
    /** ����ó */
    private String phone            = "";
    /** �ʱ�ȭ�� ID */
    private String initMenuId        = "";
    /** �ʱ�ȭ�� */ 
    private String initMenuDesc      = "";
    /** �̸��� */
    private String email             = "";
    /** ������ ID */
    private String shiftTypeId       = "";
    /** �������� */ 
    private String shiftTypeDesc     = "";
    /** ��뿩�� ID*/
    private String isUseId           = "";
    /** ��뿩�� */ 
    private String isUseDesc         = "";
    /** ������ġID */
    private String eqLocId           = "";
    /** ������ġ�� */ 
    private String eqLocDesc         = "";
    /** �˶�����ID */
    private String alarmViewYnId     = "";
    /** �˶����� */ 
    private String alarmViewYn       = "";
    /** �۾��׷�ID */
    private String wkctrId           = "";
    /** �۾��׷�� */ 
    private String wkctrDesc         = "";
    /** ������������ID */
    private String eqCtgTypeId       = "";
    /** �������������� */ 
    private String eqCtgTypeDesc     = "";
    /** ���ֺ��� �μ� ID*/
    private String deptId            = "";
    /** ���ֺ��� �μ���*/ 
    private String deptDesc          = "";    
    /** �޴�ǥ�� ID */
    private String menuDisplayId     = "";
    /** �޴�ǥ�� */ 
    private String menuDisplayDesc   = "";
    /** ����� */
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
