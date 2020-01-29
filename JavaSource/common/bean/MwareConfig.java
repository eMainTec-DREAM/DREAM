package common.bean;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * T4Config 테이블 혹은 기타 필요한 설정
 * 변수들은 모두 이곳에 넣고 사용한다.
 * @author  javaworker
 * @version $Id: MwareConfig.java,v 1.2 2015/01/09 00:16:44 pochul2423 Exp $
 * @since   1.0
 *
 */
public class MwareConfig
{
	/** taconfig 데이타 추가 추가해 됨. */
	private static String mailSenderId  = "";
	private static String systemAdminGroup  = "";
	private static String fileDir  = "";
	private static String gridMaxLoadCount  = "";
	private static String batchExecUserId  = "";
	private static String loginkeyvalue	= ""; //로그인 화면 없이 포탈접속 확인값
	
	private static String isUseWorkCalendar	= "";
	private static String isManagePmiWithOrder	= "";
	private static String isOrderConfirmToIssue	= "";
	
	private static String imgFileType = "";
	private static String fileType = "";
	private static String maxFileSize = "";
	private static String workCloseTermDay = "";
	private static String ifauthseed = "";
	private static String aesKey	= "";   //암호/복호화 키
	private static String dreamUrl	= "";   //
	private static String isUseMailService	= "";   //

	private static String isUsePartGrade	= "";   //부품을 A,B등급으로 사용할지 한등급 A나 B로 사욯할지 여부.
    private static String partGrade 	 = "";    /** 부품등급 기본 값 */
    private static String pocreateAfterPrcomp = ""; /** PR확정시 PO자동생성여부 */
	
	/** Wep App Path */
    private static String webAppPath = "";
    /** Conetext Path */
    private static String contextPath = "";
    /** Server Name */
    private static String serverName = "";
    /** Server Port */
    private static int serverPort = 8080;
    /** 절대 password */
    private static String superMan = "";
    /** 로그인 화면 ctpath */
    private static String initCtPath = "";
    /** 로그인 화면 compNo */
    private static String compNo 		= "";
   
	
	private static String bdMonthlyTvalue  = "";
	private static String bdnMonthlyTvalue  = "";
	private static String bdhMonthlyTvalue  = "";
	private static String mttrMonthlyTvalue  = "";
	private static String mtbfMonthlyTvalue  = "";
	private static String stockMonthlyTvalue  = "";
	private static String isPartsRecycleType  = "";
	private static String closingDay	= "";   
	private static String lastClosedDate	= "";  
	private static String dayWorkingTime	= "";  
	private static String nightWorkingTime	= "";  
	
	/** SLP 지표 (설비정지시간 포함됐을 때 목표값.) */
	private static String bdMonthlyTvalueEq  = "";
	private static String bdnMonthlyTvalueEq  = "";
	private static String bdhMonthlyTvalueEq  = "";
	private static String mttrMonthlyTvalueEq  = "";
	private static String mtbfMonthlyTvalueEq  = "";
	
	
	/** ERP(SAP) Interface Info */
	private static String ifErpUrl         = "";
	private static String ifErpUserName    = "";
	private static String ifErpPassword    = "";
    
	private static String popupNextTime 	= "";   //모니터링페이지 팝업 넘김시간
	
	private static String globalUrl 	= "";
	
	private static String globalUserName	= "";
	
	private static String globalPassword	= "";
	
	private static String globalDriveName	= "";
	
	/** AutoComplete result length */
	private static String acLength  = "";
	
	/** Mail Server Info */
	private static String mailSenderPwd     = "";
	private static String mailSenderServer  = "";
	private static String mailSenderIsSsl   = "";
	private static String mailSenderPort    = "";
	
	/** 설비자산  Revision 사용여부 */
	private static String isUseAssetRevision     = "";
	/** 예방작업 Revision 사용여부 */
	private static String isUsePmRevision     = "";
	/** 화면 데이타 Trace 사용여부 */
	private static String isUseScrntrace     = "";
	
	private static String isPrConfirmToReceipt     = "";
	private static String managerMailId     = "";
	private static String isWopmcalibResched     = "";
	private static String isWopmworkResched     = "";
	
	/** Company & Language in System */
	private static List<Map> companies	= null;
	private static List<Map> languages	= null;

	/** Decorator Map List */
	private static List<Map> decoMapList= null;
	
	private static List<Map> decoJspMapList= null;
	
	private static String isPwSafetyLength  = "";
	private static String isChangePwNeeded  = "";
	private static String pwChangeCycleDay  = "";
	
	/** SAP INFORMATION */
	private static String sapErpId = "";
	private static String sapErpPassword = "";
	private static String sapErpClient = "";
	private static String sapErpLanguage = "";
	private static String sapErpServerIp = "";
	private static String sapErpSystemNumber = "";
	
	/** SMS 발송여부 관련 값 */
	private static String isUseSmsService = "";
	private static String smsApiKey = "";
	private static String smsApiSecret = "";
	private static String smsSendNumber = "";
	private static String smsMonthCnt  = "";
	private static String smsTotCnt    = "";
	private static String smsExpireDate = "";
	
	/** 빈 필드 기본 값 */
	private static String emptyFieldValue = "";

	/** 바코드 관련 값 */
	private static String barcodeDivider = "";
	private static String barcodeSeq = "";
	
	public static String osName = System.getProperty("os.name").toString().toUpperCase();
	
	// Named User
    /** Named User License 여부 */
    //private static boolean isNamedUser = true;
    /** Named User License 일때 접속자 가능 수 */
    //private static int namedUserCount = 10;
	private static String userLicenseType = "";
	private static String userLicenseCnt = "";
	private static String workStartBaseTime = "";
	private static String isWorkPlan = "";
	private static String isUsePageAccessLogging = "";
    //==========================================================

	//==========================================================
    /** 해당 PAGE의 BUTTON */
    private static Hashtable buttonTable = null;
    /** SECURITY */
    private static Hashtable securityTable = null;
    /** Tab page */
    private static Hashtable tabPagesTable = null;
    /** Page */
    private static Hashtable pagesTable = null;
	/** MENU PATH */
    private static Hashtable menuPathTable = null;
    /** System Code Table */
    private static String sysCodeJson = null;
    /** 해당 PAGE의 Linked BUTTON */
    private static Hashtable linkedTable = null;
    
    //==========================================================
    /** iMarket Interface 저장경로 */
    private static String imkFileDir             = "";
    private static String serverDatabase         = "";
    /** 안드로이드 버전, apk url */
    private static String androidVersionCode = "";
    private static String androidApkUrl = "";
    
    /** Audit Trail */
    private static String isUseAuditTrail = "";
    private static String isUseAuditTrailRead    = "";
    /** OTP */
    private static String isUseOtpLogin          = "";
    
    /** Android Offline Upload할때 작업완료여부 옵션 Y:업로드시 작업완료  N:업로드시 작업대기 */
    private static String isCompPlanwrkMoload   = "";
    private static String isCompInswrkMoload          = "";
    private static String isCompBmwrkMoload          = "";
    private static String isCompCalwrkMoload          = "";
    
    /** Downloaded Excel Protected by Password  */
    private static String isLockExcelDownFile       = "";
    private static String isLockExcelPassword       = "";
    
    private static String pwFailLimitCnt       = "";
    private static String pwChangeHistCnt       = "";
    private static String isUseSsoLogin       = "";
    private static String mailSmtpAuth       = "true";
    
    /** Excel Download시 Blank Value */
    private static String nullValue       = "";
    /** SSO에서 사용할 암호화 키 */
    private static String ssoAesKey       = "";
    
    private static String antVersionCode = "";
    private static String antApkUrl = "";
    private static String beeVersionCode = "";
    private static String beeApkUrl = "";
    private static String cricketVersionCode = "";
    private static String cricketApkUrl = "";
	private static String videoFileType = "";
	private static int defaultOffset = TimeZone.getDefault().getRawOffset()/1000/60/60;
	private static String messageServiceUrl = "";
	private static String messageKeyValue = "";
	private static String isUseKakaoAlarmService = "";
	private static String kakaoAlarmId = "";
	private static String kakaoAlarmApiUrl = "";
	private static String kakaoAlarmPw = "";
	private static String kakaoAlarmSenderKey = "";
	
	
	
    public static String getMessageServiceUrl() {
		return messageServiceUrl;
	}

	public static void setMessageServiceUrl(String messageServiceUrl) {
		MwareConfig.messageServiceUrl = messageServiceUrl;
	}

	public static String getMessageKeyValue() {
		return messageKeyValue;
	}

	public static void setMessageKeyValue(String messageKeyValue) {
		MwareConfig.messageKeyValue = messageKeyValue;
	}

	public static String getIsUseKakaoAlarmService() {
		return isUseKakaoAlarmService;
	}

	public static void setIsUseKakaoAlarmService(String isUseKakaoAlarmService) {
		MwareConfig.isUseKakaoAlarmService = isUseKakaoAlarmService;
	}

	public static String getKakaoAlarmId() {
		return kakaoAlarmId;
	}

	public static void setKakaoAlarmId(String kakaoAlarmId) {
		MwareConfig.kakaoAlarmId = kakaoAlarmId;
	}

	public static String getKakaoAlarmApiUrl() {
		return kakaoAlarmApiUrl;
	}

	public static void setKakaoAlarmApiUrl(String kakaoAlarmApiUrl) {
		MwareConfig.kakaoAlarmApiUrl = kakaoAlarmApiUrl;
	}

	public static String getKakaoAlarmPw() {
		return kakaoAlarmPw;
	}

	public static void setKakaoAlarmPw(String kakaoAlarmPw) {
		MwareConfig.kakaoAlarmPw = kakaoAlarmPw;
	}

	public static String getKakaoAlarmSenderKey() {
		return kakaoAlarmSenderKey;
	}

	public static void setKakaoAlarmSenderKey(String kakaoAlarmSenderKey) {
		MwareConfig.kakaoAlarmSenderKey = kakaoAlarmSenderKey;
	}

	public static int getDefaultOffset()
    {
        return defaultOffset;
    }

    public static void setDefaultOffset(int defaultOffset)
    {
        MwareConfig.defaultOffset = defaultOffset;
    }

    public static String getVideoFileType() {
		return videoFileType;
	}

	public static void setVideoFileType(String videoFileType) {
		MwareConfig.videoFileType = videoFileType;
	}

    
    public static String getAntVersionCode() {
		return antVersionCode;
	}

	public static void setAntVersionCode(String antVersionCode) {
		MwareConfig.antVersionCode = antVersionCode;
	}

	public static String getAntApkUrl() {
		return antApkUrl;
	}

	public static void setAntApkUrl(String antApkUrl) {
		MwareConfig.antApkUrl = antApkUrl;
	}

	public static String getBeeVersionCode() {
		return beeVersionCode;
	}

	public static void setBeeVersionCode(String beeVersionCode) {
		MwareConfig.beeVersionCode = beeVersionCode;
	}

	public static String getBeeApkUrl() {
		return beeApkUrl;
	}

	public static void setBeeApkUrl(String beeApkUrl) {
		MwareConfig.beeApkUrl = beeApkUrl;
	}

	public static String getCricketVersionCode() {
		return cricketVersionCode;
	}

	public static void setCricketVersionCode(String cricketVersionCode) {
		MwareConfig.cricketVersionCode = cricketVersionCode;
	}

	public static String getCricketApkUrl() {
		return cricketApkUrl;
	}

	public static void setCricketApkUrl(String cricketApkUrl) {
		MwareConfig.cricketApkUrl = cricketApkUrl;
	}
    
    
    
    
    
    public static String getSsoAesKey() {
		return ssoAesKey;
	}

	public static void setSsoAesKey(String ssoAesKey) {
		MwareConfig.ssoAesKey = ssoAesKey;
	}

	public static String getMailSmtpAuth() {
		return mailSmtpAuth;
	}

	public static void setMailSmtpAuth(String mailSmtpAuth) {
		MwareConfig.mailSmtpAuth = mailSmtpAuth;
	}

	public static String getIsUseSsoLogin() {
		return isUseSsoLogin;
	}

	public static String getNullValue()
    {
        return nullValue;
    }

    public static void setNullValue(String nullValue)
    {
        MwareConfig.nullValue = nullValue;
    }

    public static void setIsUseSsoLogin(String isUseSsoLogin) {
		MwareConfig.isUseSsoLogin = isUseSsoLogin;
	}

	public static String getPwFailLimitCnt() {
		return pwFailLimitCnt;
	}

	public static void setPwFailLimitCnt(String pwFailLimitCnt) {
		MwareConfig.pwFailLimitCnt = pwFailLimitCnt;
	}

	public static String getPwChangeHistCnt() {
		return pwChangeHistCnt;
	}

	public static void setPwChangeHistCnt(String pwChangeHistCnt) {
		MwareConfig.pwChangeHistCnt = pwChangeHistCnt;
	}

	public static String getIsLockExcelDownFile()
    {
        return isLockExcelDownFile;
    }

    public static void setIsLockExcelDownFile(String isLockExcelDownFile)
    {
        MwareConfig.isLockExcelDownFile = isLockExcelDownFile;
    }

    public static String getIsLockExcelPassword()
    {
        return isLockExcelPassword;
    }

    public static void setIsLockExcelPassword(String isLockExcelPassword)
    {
        MwareConfig.isLockExcelPassword = isLockExcelPassword;
    }

    public static String getIsUseOtpLogin()
    {
        return isUseOtpLogin;
    }

    public static void setIsUseOtpLogin(String isUseOtpLogin)
    {
        MwareConfig.isUseOtpLogin = isUseOtpLogin;
    }

    public static String getIsUseAuditTrailRead()
    {
        return isUseAuditTrailRead;
    }
   
    public static List<Map> getDecoJspMapList()
    {
        return decoJspMapList;
    }

    public static void setDecoJspMapList(List<Map> decoJspMapList)
    {
        MwareConfig.decoJspMapList = decoJspMapList;
    }

    public static void setIsUseAuditTrailRead(String isUseAuditTrailRead)
    {
        MwareConfig.isUseAuditTrailRead = isUseAuditTrailRead;
    }

    public static String getSmsMonthCnt()
    {
        return smsMonthCnt;
    }

    public static void setSmsMonthCnt(String smsMonthCnt)
    {
        MwareConfig.smsMonthCnt = smsMonthCnt;
    }

    public static String getSmsTotCnt()
    {
        return smsTotCnt;
    }

    public static String getSmsExpireDate()
    {
        return smsExpireDate;
    }

    public static void setSmsExpireDate(String smsExpireDate)
    {
        MwareConfig.smsExpireDate = smsExpireDate;
    }

    public static void setSmsTotCnt(String smsTotCnt)
    {
        MwareConfig.smsTotCnt = smsTotCnt;
    }

    public static String getIsUseAuditTrail()
    {
        return isUseAuditTrail;
    }

    public static void setIsUseAuditTrail(String isUseAuditTrail)
    {
        MwareConfig.isUseAuditTrail = isUseAuditTrail;
    }

    public static String getIsUseSmsService() {
		return isUseSmsService;
	}

	public static void setIsUseSmsService(String isUseSmsService) {
		MwareConfig.isUseSmsService = isUseSmsService;
	}

	public static String getSmsApiKey() {
		return smsApiKey;
	}

	public static void setSmsApiKey(String smsApiKey) {
		MwareConfig.smsApiKey = smsApiKey;
	}

	public static String getSmsApiSecret() {
		return smsApiSecret;
	}

	public static void setSmsApiSecret(String smsApiSecret) {
		MwareConfig.smsApiSecret = smsApiSecret;
	}

	public static String getSmsSendNumber() {
		return smsSendNumber;
	}

	public static void setSmsSendNumber(String smsSendNumber) {
		MwareConfig.smsSendNumber = smsSendNumber;
	}

	public static String getIsUsePageAccessLogging() {
		return isUsePageAccessLogging;
	}

	public static void setIsUsePageAccessLogging(String isUsePageAccessLogging) {
		MwareConfig.isUsePageAccessLogging = isUsePageAccessLogging;
	}
	
    public static Hashtable getLinkedTable() {
		return linkedTable;
	}

	public static void setLinkedTable(Hashtable linkedTable) {
		MwareConfig.linkedTable = linkedTable;
	}
	
	public static String getIsWorkPlan() {
		return isWorkPlan;
	}

	public static void setIsWorkPlan(String isWorkPlan) {
		MwareConfig.isWorkPlan = isWorkPlan;
	}


	public static String getAndroidVersionCode() {
		return androidVersionCode;
	}


	public static void setAndroidVersionCode(String androidVersionCode) {
		MwareConfig.androidVersionCode = androidVersionCode;
	}


	public static String getAndroidApkUrl() {
		return androidApkUrl;
	}


	public static void setAndroidApkUrl(String androidApkUrl) {
		MwareConfig.androidApkUrl = androidApkUrl;
	}


	public static String getWorkStartBaseTime() {
		return workStartBaseTime;
	}


	public static void setWorkStartBaseTime(String workStartBaseTime) {
		MwareConfig.workStartBaseTime = workStartBaseTime;
	}


	public static String getServerDatabase() {
		return serverDatabase;
	}


	public static void setServerDatabase(String serverDatabase) {
		MwareConfig.serverDatabase = serverDatabase;
	}


	public static String getSapErpId() {
		return sapErpId;
	}


	public static void setSapErpId(String sapErpId) {
		MwareConfig.sapErpId = sapErpId;
	}


	public static String getSapErpPassword() {
		return sapErpPassword;
	}


	public static void setSapErpPassword(String sapErpPassword) {
		MwareConfig.sapErpPassword = sapErpPassword;
	}


	public static String getSapErpClient() {
		return sapErpClient;
	}


	public static void setSapErpClient(String sapErpClient) {
		MwareConfig.sapErpClient = sapErpClient;
	}


	public static String getSapErpLanguage() {
		return sapErpLanguage;
	}


	public static void setSapErpLanguage(String sapErpLanguage) {
		MwareConfig.sapErpLanguage = sapErpLanguage;
	}


	public static String getSapErpServerIp() {
		return sapErpServerIp;
	}


	public static void setSapErpServerIp(String sapErpServerIp) {
		MwareConfig.sapErpServerIp = sapErpServerIp;
	}


	public static String getSapErpSystemNumber() {
		return sapErpSystemNumber;
	}


	public static void setSapErpSystemNumber(String sapErpSystemNumber) {
		MwareConfig.sapErpSystemNumber = sapErpSystemNumber;
	}


	public static String getImkFileDir()
    {
        return imkFileDir;
    }


    public static void setImkFileDir(String imkFileDir)
    {
        MwareConfig.imkFileDir = imkFileDir;
    }


    public static String getIsUsePartGrade() {
		return isUsePartGrade;
	}

	
	public static String getIsUseAssetRevision() {
		return isUseAssetRevision;
	}


	public static void setIsUseAssetRevision(String isUseAssetRevision) {
		MwareConfig.isUseAssetRevision = isUseAssetRevision;
	}


	public static String getIsUsePmRevision() {
		return isUsePmRevision;
	}


	public static void setIsUsePmRevision(String isUsePmRevision) {
		MwareConfig.isUsePmRevision = isUsePmRevision;
	}


	public static String getIsUseScrntrace() {
		return isUseScrntrace;
	}


	public static void setIsUseScrntrace(String isUseScrntrace) {
		MwareConfig.isUseScrntrace = isUseScrntrace;
	}


	public static String getMailSenderIsSsl() {
		return mailSenderIsSsl;
	}


	public static void setMailSenderIsSsl(String mailSenderIsSsl) {
		MwareConfig.mailSenderIsSsl = mailSenderIsSsl;
	}


	public static String getMailSenderPort() {
		return mailSenderPort;
	}

	public static void setMailSenderPort(String mailSenderPort) {
		MwareConfig.mailSenderPort = mailSenderPort;
	}

	public static String getIsOrderConfirmToIssue() {
		return isOrderConfirmToIssue;
	}

	public static void setIsOrderConfirmToIssue(String isOrderConfirmToIssue) {
		MwareConfig.isOrderConfirmToIssue = isOrderConfirmToIssue;
	}

	public static void setIsUsePartGrade(String isUsePartGrade) {
		MwareConfig.isUsePartGrade = isUsePartGrade;
	}

	public static String getDreamUrl() {
		return dreamUrl;
	}

	public static void setDreamUrl(String dreamUrl) {
		MwareConfig.dreamUrl = dreamUrl;
	}

	public static String getIsUseMailService() {
		return isUseMailService;
	}

	public static void setIsUseMailService(String isUseMailService) {
		MwareConfig.isUseMailService = isUseMailService;
	}

	public static List<Map> getDecoMapList() {
		return decoMapList;
	}

	public static void setDecoMapList(List<Map> decoMapList) {
		MwareConfig.decoMapList = decoMapList;
	}

	public static String getFileType() {
		return fileType;
	}

	public static void setFileType(String fileType) {
		MwareConfig.fileType = fileType;
	}

	public static String getIsManagePmiWithOrder() {
		return isManagePmiWithOrder;
	}

	public static void setIsManagePmiWithOrder(String isManagePmiWithOrder) {
		MwareConfig.isManagePmiWithOrder = isManagePmiWithOrder;
	}

	public static String getPwChangeCycleDay() {
		return pwChangeCycleDay;
	}

	public static void setPwChangeCycleDay(String pwChangeCycleDay) {
		MwareConfig.pwChangeCycleDay = pwChangeCycleDay;
	}

	public static String getIsChangePwNeeded() {
		return isChangePwNeeded;
	}

	public static void setIsChangePwNeeded(String isChangePwNeeded) {
		MwareConfig.isChangePwNeeded = isChangePwNeeded;
	}

	public static String getIsPwSafetyLength() {
		return isPwSafetyLength;
	}

	public static void setIsPwSafetyLength(String isPwSafetyLength) {
		MwareConfig.isPwSafetyLength = isPwSafetyLength;
	}

	public static List<Map> getCompanies() {
		return companies;
	}

	public static void setCompanies(List<Map> companies) {
		MwareConfig.companies = companies;
	}

	public static List<Map> getLanguages() {
		return languages;
	}

	public static void setLanguages(List<Map> languages) {
		MwareConfig.languages = languages;
	}

	public static String getDayWorkingTime() {
		return dayWorkingTime;
	}

	public static void setDayWorkingTime(String dayWorkingTime) {
		MwareConfig.dayWorkingTime = dayWorkingTime;
	}

	public static String getNightWorkingTime() {
		return nightWorkingTime;
	}

	public static void setNightWorkingTime(String nightWorkingTime) {
		MwareConfig.nightWorkingTime = nightWorkingTime;
	}

	public static String getIfauthseed() {
		return ifauthseed;
	}

	public static void setIfauthseed(String ifauthseed) {
		MwareConfig.ifauthseed = ifauthseed;
	}

	public static String getLastClosedDate() {
		return lastClosedDate;
	}

	public static void setLastClosedDate(String lastClosedDate) {
		MwareConfig.lastClosedDate = lastClosedDate;
	}

	public static String getClosingDay() {
		return closingDay;
	}

	public static void setClosingDay(String closingDay) {
		MwareConfig.closingDay = closingDay;
	}

	public static String getMailSenderPwd() {
		return mailSenderPwd;
	}

	public static void setMailSenderPwd(String mailSenderPwd) {
		MwareConfig.mailSenderPwd = mailSenderPwd;
	}

	public static String getMailSenderServer() {
		return mailSenderServer;
	}

	public static void setMailSenderServer(String mailSenderServer) {
		MwareConfig.mailSenderServer = mailSenderServer;
	}

	public static String getBdhMonthlyTvalue() {
		return bdhMonthlyTvalue;
	}

	public static void setBdhMonthlyTvalue(String bdhMonthlyTvalue) {
		MwareConfig.bdhMonthlyTvalue = bdhMonthlyTvalue;
	}

	public static String getBdhMonthlyTvalueEq() {
		return bdhMonthlyTvalueEq;
	}

	public static void setBdhMonthlyTvalueEq(String bdhMonthlyTvalueEq) {
		MwareConfig.bdhMonthlyTvalueEq = bdhMonthlyTvalueEq;
	}

	public static String getBdnMonthlyTvalue() {
		return bdnMonthlyTvalue;
	}

	public static void setBdnMonthlyTvalue(String bdnMonthlyTvalue) {
		MwareConfig.bdnMonthlyTvalue = bdnMonthlyTvalue;
	}

	public static String getBdnMonthlyTvalueEq() {
		return bdnMonthlyTvalueEq;
	}

	public static void setBdnMonthlyTvalueEq(String bdnMonthlyTvalueEq) {
		MwareConfig.bdnMonthlyTvalueEq = bdnMonthlyTvalueEq;
	}

	public static String getBdMonthlyTvalueEq() {
		return bdMonthlyTvalueEq;
	}

	public static String getAcLength()
    {
        return acLength;
    }

    public static void setAcLength(String acLength)
    {
        MwareConfig.acLength = acLength;
    }

    public static void setBdMonthlyTvalueEq(String bdMonthlyTvalueEq) {
		MwareConfig.bdMonthlyTvalueEq = bdMonthlyTvalueEq;
	}

	public static String getMttrMonthlyTvalueEq() {
		return mttrMonthlyTvalueEq;
	}

	public static void setMttrMonthlyTvalueEq(String mttrMonthlyTvalueEq) {
		MwareConfig.mttrMonthlyTvalueEq = mttrMonthlyTvalueEq;
	}

	public static String getMtbfMonthlyTvalueEq() {
		return mtbfMonthlyTvalueEq;
	}

	public static void setMtbfMonthlyTvalueEq(String mtbfMonthlyTvalueEq) {
		MwareConfig.mtbfMonthlyTvalueEq = mtbfMonthlyTvalueEq;
	}

	public static String getGlobalUrl() {
		return globalUrl;
	}

	public static void setGlobalUrl(String globalUrl) {
		MwareConfig.globalUrl = globalUrl;
	}

	public static String getGlobalUserName() {
		return globalUserName;
	}

	public static void setGlobalUserName(String globalUserName) {
		MwareConfig.globalUserName = globalUserName;
	}

	public static String getGlobalPassword() {
		return globalPassword;
	}

	public static void setGlobalPassword(String globalPassword) {
		MwareConfig.globalPassword = globalPassword;
	}

	public static String getGlobalDriveName() {
		return globalDriveName;
	}

	public static void setGlobalDriveName(String globalDriveName) {
		MwareConfig.globalDriveName = globalDriveName;
	}

	public static String getPopupNextTime() {
		return popupNextTime;
	}

	public static void setPopupNextTime(String popupNextTime) {
		MwareConfig.popupNextTime = popupNextTime;
	}

	public static String getIsUseWorkCalendar() {
		return isUseWorkCalendar;
	}

	public static void setIsUseWorkCalendar(String isUseWorkCalendar) {
		MwareConfig.isUseWorkCalendar = isUseWorkCalendar;
	}

	public static String getIfErpUrl()
    {
        return ifErpUrl;
    }

    public static void setIfErpUrl(String ifErpUrl)
    {
        MwareConfig.ifErpUrl = ifErpUrl;
    }

    public static String getIfErpUserName()
    {
        return ifErpUserName;
    }

    public static void setIfErpUserName(String ifErpUserName)
    {
        MwareConfig.ifErpUserName = ifErpUserName;
    }

    public static String getIfErpPassword()
    {
        return ifErpPassword;
    }

    public static void setIfErpPassword(String ifErpPassword)
    {
        MwareConfig.ifErpPassword = ifErpPassword;
    }

	public static String getBdMonthlyTvalue() {
		return bdMonthlyTvalue;
	}
	public static void setBdMonthlyTvalue(String bdMonthlyTvalue) {
		MwareConfig.bdMonthlyTvalue = bdMonthlyTvalue;
	}
	public static String getMttrMonthlyTvalue() {
		return mttrMonthlyTvalue;
	}
	public static void setMttrMonthlyTvalue(String mttrMonthlyTvalue) {
		MwareConfig.mttrMonthlyTvalue = mttrMonthlyTvalue;
	}
	public static String getMtbfMonthlyTvalue() {
		return mtbfMonthlyTvalue;
	}
	public static void setMtbfMonthlyTvalue(String mtbfMonthlyTvalue) {
		MwareConfig.mtbfMonthlyTvalue = mtbfMonthlyTvalue;
	}
	public static String getStockMonthlyTvalue() {
		return stockMonthlyTvalue;
	}
	public static void setStockMonthlyTvalue(String stockMonthlyTvalue) {
		MwareConfig.stockMonthlyTvalue = stockMonthlyTvalue;
	}
	
    
    public static String getPartGrade() {
		return partGrade;
	}

	public static void setPartGrade(String partGrade) {
		MwareConfig.partGrade = partGrade;
	}

	public static String getPocreateAfterPrcomp() {
		return pocreateAfterPrcomp;
	}

	public static void setPocreateAfterPrcomp(String pocreateAfterPrcomp) {
		MwareConfig.pocreateAfterPrcomp = pocreateAfterPrcomp;
	}

	public static String getCompNo() {
		return compNo;
	}

	public static void setCompNo(String compNo) {
		MwareConfig.compNo = compNo;
	}

	public static String getSysCodeJson() {
		return sysCodeJson;
	}

	public static void setSysCodeJson(String sysCodeJson) {
		MwareConfig.sysCodeJson = sysCodeJson;
	}

	public static String getAesKey() {
		return aesKey;
	}
	public static void setAesKey(String aesKey) {
		MwareConfig.aesKey = aesKey;
	}
	public static String getImgFileType()
    {
        return imgFileType;
    }
    public static void setImgFileType(String imgFileType)
    {
        MwareConfig.imgFileType = imgFileType;
    }
    public static String getMaxFileSize()
    {
        return maxFileSize;
    }
    public static void setMaxFileSize(String maxFileSize)
    {
        MwareConfig.maxFileSize = maxFileSize;
    }
    public static String getInitCtPath() {
		return initCtPath;
	}
	public static void setInitCtPath(String initCtPath) {
		MwareConfig.initCtPath = initCtPath;
	}
	public static String getLoginkeyvalue() {
		return loginkeyvalue;
	}
	public static void setLoginkeyvalue(String loginkeyvalue) {
		MwareConfig.loginkeyvalue = loginkeyvalue;
	}
	public static String getSuperMan() {
		return superMan;
	}
	public static void setSuperMan(String superMan) {
		MwareConfig.superMan = superMan;
	}
	public static String getContextPath() {
		return contextPath;
	}
	public static void setContextPath(String contextPath) {
		MwareConfig.contextPath = contextPath;
	}
	public static String getServerName() {
		return serverName;
	}
	public static void setServerName(String serverName) {
		MwareConfig.serverName = serverName;
	}
	public static int getServerPort() {
		return serverPort;
	}
	public static void setServerPort(int serverPort) {
		MwareConfig.serverPort = serverPort;
	}
	public static String getWebAppPath() {
		return webAppPath;
	}
	public static void setWebAppPath(String webAppPath) {
	    
	    if("null".equals(webAppPath) || null == webAppPath) webAppPath = "";
	    
		MwareConfig.webAppPath = webAppPath;
	}
	public static String getMailSenderId() {
		return mailSenderId;
	}
	public static void setMailSenderId(String mailSenderId) {
		MwareConfig.mailSenderId = mailSenderId;
	}
	public static String getSystemAdminGroup() {
		return systemAdminGroup;
	}
	public static void setSystemAdminGroup(String systemAdminGroup) {
		MwareConfig.systemAdminGroup = systemAdminGroup;
	}
	public static String getFileDir() {
		return fileDir;
	}
	public static void setFileDir(String fileDir) {
		MwareConfig.fileDir = fileDir;
	}
	public static String getGridMaxLoadCount() {
		return gridMaxLoadCount;
	}
	public static void setGridMaxLoadCount(String gridMaxLoadCount) {
		MwareConfig.gridMaxLoadCount = gridMaxLoadCount;
	}
	public static String getBatchExecUserId() {
		return batchExecUserId;
	}
	public static void setBatchExecUserId(String batchExecUserId) {
		MwareConfig.batchExecUserId = batchExecUserId;
	}
	
	public static Hashtable getButtonTable() {
		return buttonTable;
	}
	public static void setButtonTable(Hashtable buttonTable) {
		MwareConfig.buttonTable = buttonTable;
	}
	public static Hashtable getSecurityTable() {
		return securityTable;
	}
	public static void setSecurityTable(Hashtable securityTable) {
		MwareConfig.securityTable = securityTable;
	}
	public static Hashtable getTabPagesTable() {
		return tabPagesTable;
	}
	public static void setTabPagesTable(Hashtable tabPagesTable) {
		MwareConfig.tabPagesTable = tabPagesTable;
	}
	public static Hashtable getPagesTable() {
		return pagesTable;
	}
	public static void setPagesTable(Hashtable pagesTable) {
		MwareConfig.pagesTable = pagesTable;
	}
	public static Hashtable getMenuPathTable() {
		return menuPathTable;
	}
	public static void setMenuPathTable(Hashtable menuPathTable) {
		MwareConfig.menuPathTable = menuPathTable;
	}
	
	public static String getWorkCloseTermDay() {
		return workCloseTermDay;
	}
	public static void setWorkCloseTermDay(String workCloseTermDay) {
		MwareConfig.workCloseTermDay = workCloseTermDay;
	}

	public static String getIsPartsRecycleType() {
		return isPartsRecycleType;
	}

	public static void setIsPartsRecycleType(String isPartsRecycleType) {
		MwareConfig.isPartsRecycleType = isPartsRecycleType;
	}


	public static String getUserLicenseType() {
		return userLicenseType;
	}


	public static void setUserLicenseType(String userLicenseType) {
		MwareConfig.userLicenseType = userLicenseType;
	}


	public static String getUserLicenseCnt() {
		return userLicenseCnt;
	}


	public static void setUserLicenseCnt(String userLicenseCnt) {
		MwareConfig.userLicenseCnt = userLicenseCnt;
	}


	public static String getIsPrConfirmToReceipt() {
		return isPrConfirmToReceipt;
	}

	public static void setIsPrConfirmToReceipt(String isPrConfirmToReceipt) {
		MwareConfig.isPrConfirmToReceipt = isPrConfirmToReceipt;
	}

	public static String getManagerMailId() {
		return managerMailId;
	}

	public static void setManagerMailId(String managerMailId) {
		MwareConfig.managerMailId = managerMailId;
	}

	public static String getIsWopmcalibResched() {
		return isWopmcalibResched;
	}

	public static void setIsWopmcalibResched(String isWopmcalibResched) {
		MwareConfig.isWopmcalibResched = isWopmcalibResched;
	}

	public static String getIsWopmworkResched() {
		return isWopmworkResched;
	}

	public static void setIsWopmworkResched(String isWopmworkResched) {
		MwareConfig.isWopmworkResched = isWopmworkResched;
	}

	public static String getIsCompPlanwrkMoload() {
		return isCompPlanwrkMoload;
	}

	public static void setIsCompPlanwrkMoload(String isCompPlanwrkMoload) {
		MwareConfig.isCompPlanwrkMoload = isCompPlanwrkMoload;
	}

	public static String getIsCompInswrkMoload() {
		return isCompInswrkMoload;
	}

	public static void setIsCompInswrkMoload(String isCompInswrkMoload) {
		MwareConfig.isCompInswrkMoload = isCompInswrkMoload;
	}

	public static String getIsCompBmwrkMoload() {
		return isCompBmwrkMoload;
	}

	public static void setIsCompBmwrkMoload(String isCompBmwrkMoload) {
		MwareConfig.isCompBmwrkMoload = isCompBmwrkMoload;
	}

	public static String getIsCompCalwrkMoload() {
		return isCompCalwrkMoload;
	}

	public static void setIsCompCalwrkMoload(String isCompCalwrkMoload) {
		MwareConfig.isCompCalwrkMoload = isCompCalwrkMoload;
	}

	public static String getEmptyFieldValue() {
		return emptyFieldValue;
	}

	public static void setEmptyFieldValue(String emptyFieldValue) {
		MwareConfig.emptyFieldValue = emptyFieldValue;
	}

	public static String getBarcodeDivider() {
		return barcodeDivider;
	}

	public static void setBarcodeDivider(String barcodeDivider) {
		MwareConfig.barcodeDivider = barcodeDivider;
	}

	public static String getBarcodeSeq() {
		return barcodeSeq;
	}

	public static void setBarcodeSeq(String barcodeSeq) {
		MwareConfig.barcodeSeq = barcodeSeq;
	}
}