package common.bean;

import java.util.Locale;

/**
 * User 객체이다.
 * Login후 필요한 정보를 셋팅후 session에 저장된다. 
 * @author  javaworker
 * @version $Id: User.java,v 1.1 2013/08/30 09:13:54 javaworker Exp $
 * @since   1.0
 */
public class User
{
	/** User Login 회사코드 */
	private String compNo       = "";
	
	
    /** 사용자 번호 */
    private String userNo       = "";
    /** 사용자 ID */
    private String userId       = ""; 
    /** 사용자 그룹 */
    private String usrgrpId     = "";
    /** 사용자 그룹명 */
    private String usrgrpName   = "";
    /** UserName */
    private String userName     = "";
    /** 초기메뉴 */
    private String initMenuId   = "";
    /** 사원 ID */
    private String empId        = "";
    /** 사원명 */
    private String empName      = "";
    /** 이메일 */
    private String eMail        = "";
    /** 연락처 */
    private String mPhone       = "";
    /** 등록일 */
    private String regDate      = "";
    /** 최근 접근일 */
    private String loginDate    = "";
    /** 사용 시간 */
    private long loginTimeMillis;
    /** 설정 언어 */
    private Locale locale = null;
    /** 언어 **/
    private String lang         = "";
    /** 언어 ID */
    private String langId       = "";
    /** 언어 명 */
    private String langDesc     = "";
    /** File Name */
    private String fileName     = "";
    
    /** 부서 */
    private String deptId       = "";
    /** 부서 */
    private String deptDesc     = "";
    /** filter부서 */
    private String filterDeptId       = "";
    /** filter부서 */
    private String filterDeptDesc     = "";
    /** filter담당자 */
    private String filterEmpId       = "";
    /** filter담당자 */
    private String filterEmpDesc     = "";
    /** 창고Id */
    private String wcodeId      = "";
    /** 창고명 */
    private String wname        = "";
    /** 공기구창고Id */
    private String twcodeId      = "";
    /** 공기구창고명 */
    private String twname        = "";
    /** 문서권한 */
    private String securGrade   = "";
    /** 화면글자크기 */
    private String scrnFontSize = "";
    /** 로그인화면 없이 로그인 키 */
    private String loginKeyValue= "";
    /** Css Path */
    private String ctPath		= "";
    /** 교대조 */
    private String shiftType	= "";
    /** 교대조명 */
    private String shiftTypeDesc= "";
    /** 위치ID */
    private String eqLocId		= "";
    /** 위치명 */
    private String eqLocDesc	= "";
    /** 공장코드 */
    private String plant		= "";
    /** 공장명 */
    private String plantDesc	= "";
    /** 자주보는 공장코드 */
    private String filterPlant		= "";
    /** 자주보는 공장명 */
    private String filterPlantDesc	= "";
    /** Dashboard View */
    private String alarmViewYn  = "";
    /** 작업그룹ID */
    private String wkctrId		= "";
    /** 작업그룹명 */
    private String wkctrDesc	= "";
    /** 설비유형ID */
    private String eqCtgTypeId	= "";
    /** 설비유형명 */
    private String eqCtgTypeDesc= "";
    /** Menu Expand? */
    private String menuDisplay  = "";
    /** GAIA, CONSULT, DREAM */
    private String pageType     = "";
    /** 출고창고Id */
    private String fromWcodeId  = "";
    /** 출고창고명 */
    private String fromWname    = "";
    /** 보관창고Id */
    private String toWcodeId      = "";
    /** 보관창고명 */
    private String toWname        = "";
    /** 자주보는 작업그룹id */
    private String filterWkCtrId    = "";
    /** 자주보는 작업그룹명 */
    private String filterWkCtrDesc  = "";
	/** Filter 창고코드 */
	private String filterWcodeId 	= "";
	/** Filter 창고명 */
	private String filterWcodeDesc 	= "";
	
	private String empNo               	="";
	
	private String deptNo              	= "";

    /** 자주보는 사용부서 */
    private String filterUsageDeptId	= "";
    /** 자주보는 사용부서 */
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
