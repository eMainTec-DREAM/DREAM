package common.bean;

import java.util.Locale;

/**
 * User ��ü�̴�.
 * Login�� �ʿ��� ������ ������ session�� ����ȴ�. 
 * @author  javaworker
 * @version $Id: User.java,v 1.1 2013/08/30 09:13:54 javaworker Exp $
 * @since   1.0
 */
public class User
{
	/** User Login ȸ���ڵ� */
	private String compNo       = "";
	
	
    /** ����� ��ȣ */
    private String userNo       = "";
    /** ����� ID */
    private String userId       = ""; 
    /** ����� �׷� */
    private String usrgrpId     = "";
    /** ����� �׷�� */
    private String usrgrpName   = "";
    /** UserName */
    private String userName     = "";
    /** �ʱ�޴� */
    private String initMenuId   = "";
    /** ��� ID */
    private String empId        = "";
    /** ����� */
    private String empName      = "";
    /** �̸��� */
    private String eMail        = "";
    /** ����ó */
    private String mPhone       = "";
    /** ����� */
    private String regDate      = "";
    /** �ֱ� ������ */
    private String loginDate    = "";
    /** ��� �ð� */
    private long loginTimeMillis;
    /** ���� ��� */
    private Locale locale = null;
    /** ��� **/
    private String lang         = "";
    /** ��� ID */
    private String langId       = "";
    /** ��� �� */
    private String langDesc     = "";
    /** File Name */
    private String fileName     = "";
    
    /** �μ� */
    private String deptId       = "";
    /** �μ� */
    private String deptDesc     = "";
    /** filter�μ� */
    private String filterDeptId       = "";
    /** filter�μ� */
    private String filterDeptDesc     = "";
    /** filter����� */
    private String filterEmpId       = "";
    /** filter����� */
    private String filterEmpDesc     = "";
    /** â��Id */
    private String wcodeId      = "";
    /** â��� */
    private String wname        = "";
    /** ���ⱸâ��Id */
    private String twcodeId      = "";
    /** ���ⱸâ��� */
    private String twname        = "";
    /** �������� */
    private String securGrade   = "";
    /** ȭ�����ũ�� */
    private String scrnFontSize = "";
    /** �α���ȭ�� ���� �α��� Ű */
    private String loginKeyValue= "";
    /** Css Path */
    private String ctPath		= "";
    /** ������ */
    private String shiftType	= "";
    /** �������� */
    private String shiftTypeDesc= "";
    /** ��ġID */
    private String eqLocId		= "";
    /** ��ġ�� */
    private String eqLocDesc	= "";
    /** �����ڵ� */
    private String plant		= "";
    /** ����� */
    private String plantDesc	= "";
    /** ���ֺ��� �����ڵ� */
    private String filterPlant		= "";
    /** ���ֺ��� ����� */
    private String filterPlantDesc	= "";
    /** Dashboard View */
    private String alarmViewYn  = "";
    /** �۾��׷�ID */
    private String wkctrId		= "";
    /** �۾��׷�� */
    private String wkctrDesc	= "";
    /** ��������ID */
    private String eqCtgTypeId	= "";
    /** ���������� */
    private String eqCtgTypeDesc= "";
    /** Menu Expand? */
    private String menuDisplay  = "";
    /** GAIA, CONSULT, DREAM */
    private String pageType     = "";
    /** ���â��Id */
    private String fromWcodeId  = "";
    /** ���â��� */
    private String fromWname    = "";
    /** ����â��Id */
    private String toWcodeId      = "";
    /** ����â��� */
    private String toWname        = "";
    /** ���ֺ��� �۾��׷�id */
    private String filterWkCtrId    = "";
    /** ���ֺ��� �۾��׷�� */
    private String filterWkCtrDesc  = "";
	/** Filter â���ڵ� */
	private String filterWcodeId 	= "";
	/** Filter â��� */
	private String filterWcodeDesc 	= "";
	
	private String empNo               	="";
	
	private String deptNo              	= "";

    /** ���ֺ��� ���μ� */
    private String filterUsageDeptId	= "";
    /** ���ֺ��� ���μ� */
    private String filterUsageDeptDesc  = "";
    /** offset */
    private int offset              = MwareConfig.getDefaultOffset();
    
    public int getOffset()
    {
        return offset;
    }
    public void setOffset(int offset)
    {
        this.offset = offset;
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
	public String getDeptNo()
    {
        return deptNo;
    }
    public void setDeptNo(String deptNo)
    {
        this.deptNo = deptNo;
    }
    public String getEmpNo()
    {
        return empNo;
    }
    public void setEmpNo(String empNo)
    {
        this.empNo = empNo;
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
	public String getFromWcodeId()
    {
        return fromWcodeId;
    }
    public void setFromWcodeId(String fromWcodeId)
    {
        this.fromWcodeId = fromWcodeId;
    }
    public String getFromWname()
    {
        return fromWname;
    }
    public void setFromWname(String fromWname)
    {
        this.fromWname = fromWname;
    }
    public String getToWcodeId()
    {
        return toWcodeId;
    }
    public void setToWcodeId(String toWcodeId)
    {
        this.toWcodeId = toWcodeId;
    }
    public String getToWname()
    {
        return toWname;
    }
    public void setToWname(String toWname)
    {
        this.toWname = toWname;
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
	public String getPageType()
    {
        return pageType;
    }
    public void setPageType(String pageType)
    {
        this.pageType = pageType;
    }
    public String getMenuDisplay()
    {
        return menuDisplay;
    }
    public void setMenuDisplay(String menuDisplay)
    {
        this.menuDisplay = menuDisplay;
    }
    public String getLangDesc() {
		return langDesc;
	}
	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
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
	public String getWkctrId() {
		return wkctrId;
	}
	public void setWkctrId(String wkctrId) {
		this.wkctrId = wkctrId;
	}
	public String getWkctrDesc() {
		return wkctrDesc;
	}
	public void setWkctrDesc(String wkctrDesc) {
		this.wkctrDesc = wkctrDesc;
	}
	public String getAlarmViewYn()
    {
        return alarmViewYn;
    }
    public void setAlarmViewYn(String alarmViewYn)
    {
        this.alarmViewYn = alarmViewYn;
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
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
    public String getTwcodeId() {
		return twcodeId;
	}
	public void setTwcodeId(String twcodeId) {
		this.twcodeId = twcodeId;
	}
	public String getTwname() {
		return twname;
	}
	public void setTwname(String twname) {
		this.twname = twname;
	}
	public String getShiftType() {
		return shiftType;
	}
	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}
	public String getShiftTypeDesc() {
		return shiftTypeDesc;
	}
	public void setShiftTypeDesc(String shiftTypeDesc) {
		this.shiftTypeDesc = shiftTypeDesc;
	}
	public String getCtPath() {
		return ctPath;
	}
	public void setCtPath(String ctPath) {
		this.ctPath = ctPath;
	}
	public String getLoginKeyValue() {
		return loginKeyValue;
	}
	public void setLoginKeyValue(String loginKeyValue) {
		this.loginKeyValue = loginKeyValue;
	}
	public String getUsrgrpName() {
		return usrgrpName;
	}
	public void setUsrgrpName(String usrgrpName) {
		this.usrgrpName = usrgrpName;
	}
	public String getSecurGrade()
    {
        return securGrade;
    }
    public void setSecurGrade(String securGrade)
    {
        this.securGrade = securGrade;
    }
    public String getScrnFontSize()
    {
        return scrnFontSize;
    }
    public void setScrnFontSize(String scrnFontSize)
    {
        this.scrnFontSize = scrnFontSize;
    }
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
    }
    public String getWname()
    {
        return wname;
    }
    public void setWname(String wname)
    {
        this.wname = wname;
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
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public String getLangId()
    {
        return langId;
    }
    public void setLangId(String langId)
    {
        this.langId = langId;
    }
    public String getLang()
    {
        return lang;
    }
    public void setLang(String lang)
    {
        this.lang = lang;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getUserNo()
    {
        return userNo;
    }
    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getUsrgrpId()
    {
        return usrgrpId;
    }
    public void setUsrgrpId(String usrgrpId)
    {
        this.usrgrpId = usrgrpId;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getInitMenuId()
    {
        return initMenuId;
    }
    public void setInitMenuId(String initMenuId)
    {
        this.initMenuId = initMenuId;
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
    public String geteMail()
    {
        return eMail;
    }
    public void seteMail(String eMail)
    {
        this.eMail = eMail;
    }
    public String getmPhone()
    {
        return mPhone;
    }
    public void setmPhone(String mPhone)
    {
        this.mPhone = mPhone;
    }
    public String getRegDate()
    {
        return regDate;
    }
    public void setRegDate(String regDate)
    {
        this.regDate = regDate;
    }
    public String getLoginDate()
    {
        return loginDate;
    }
    public void setLoginDate(String loginDate)
    {
        this.loginDate = loginDate;
    }
    public long getLoginTimeMillis()
    {
        return loginTimeMillis;
    }
    public void setLoginTimeMillis(long loginTimeMillis)
    {
        this.loginTimeMillis = loginTimeMillis;
    }
    public Locale getLocale()
    {
        return locale==null&&!"".equals(langId)?new Locale(langId):locale;
    }
    public void setLocale(Locale locale)
    {
        this.locale = locale;
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
	public String getFilterPlant() {
		return filterPlant;
	}
	public void setFilterPlant(String filterPlant) {
		this.filterPlant = filterPlant;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
    
	
	
    
}
