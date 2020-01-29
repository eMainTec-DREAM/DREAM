package dream.main.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
public class MemberPopupDTO extends BaseDTO
{
	/** User ID */
	private String userID = "";
	/** 수정을  위한  User ID */
	private String historyUserID = "";
	/** User Name */
	private String userName = "";
	/** User Group */
	private String userGroup = "";
	/** 부서코드 */
	private String deptNo = "";
	/** 부서명 */
	private String deptName = "";
	/** 직급 */
	private String grade = "";
	/** 직종코드 */
	private String craft = "";
	/** 주소 */
	private String address = "";
	/** E-mail */
    private String email = "";
	/** 입사일자 */
	private String joinDate = "";
	/** 퇴사일자 */
	private String retireDate = "";
	/** Password */
	private String password = "";
	/** confirm */
	private String confirm = "";
	/** 생일 */
	private String birthDay = "";
	
    public String getUserID()
    {
        return userID;
    }
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    public String getHistoryUserID()
    {
        return historyUserID;
    }
    public void setHistoryUserID(String historyUserID)
    {
        this.historyUserID = historyUserID;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getUserGroup()
    {
        return userGroup;
    }
    public void setUserGroup(String userGroup)
    {
        this.userGroup = userGroup;
    }
    public String getDeptNo()
    {
        return deptNo;
    }
    public void setDeptNo(String deptNo)
    {
        this.deptNo = deptNo;
    }
    public String getDeptName()
    {
        return deptName;
    }
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }
    public String getGrade()
    {
        return grade;
    }
    public void setGrade(String grade)
    {
        this.grade = grade;
    }
    public String getCraft()
    {
        return craft;
    }
    public void setCraft(String craft)
    {
        this.craft = craft;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getJoinDate()
    {
        return joinDate;
    }
    public void setJoinDate(String joinDate)
    {
        this.joinDate = CommonUtil.convertDate(joinDate);
    }
    public String getRetireDate()
    {
        return retireDate;
    }
    public void setRetireDate(String retireDate)
    {
        this.retireDate = CommonUtil.convertDate(retireDate);
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getBirthDay()
    {
        return birthDay;
    }
    public void setBirthDay(String birthDay)
    {
        this.birthDay = CommonUtil.convertDate(birthDay);
    }
    public String getConfirm()
    {
        return confirm;
    }
    public void setConfirm(String confirm)
    {
        this.confirm = confirm;
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
