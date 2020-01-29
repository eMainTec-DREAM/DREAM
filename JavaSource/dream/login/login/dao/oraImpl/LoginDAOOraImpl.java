package dream.login.login.dao.oraImpl;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.login.login.dao.LoginDAO;
import dream.login.login.dto.LoginDTO;

/**
 * Login DAO
 * @author  javaworker
 * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="loginDAOTarget"
 * @spring.txbn id="loginDAO"
 * @spring.property name="dataSource" ref="dataSource" 
 */
public class LoginDAOOraImpl extends BaseJdbcDaoSupportOra implements LoginDAO
{
    /**
     * 해당 User Id 의 정보를 조회한다.         2013.10.02 로그인만 가능하게 수정하였음. 꼭 다시 작업해야함 .   이규선
     * @author  javaworker
     * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginDTO
     * @return
     * @throws Exception
     */
    public List findUserInfo(LoginDTO loginDTO)
    {
    	String userNo = loginDTO.getUserNo();
    	String localeId = loginDTO.getLocale();
    	String compNo = loginDTO.getCompNo();
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                     ");
        query.append("        a.comp_no compNo,                                  ");
        query.append("        (SELECT b.package_no                               ");
        query.append("         FROM   TACOMP b                                   ");
        query.append("         WHERE  b.comp_no = a.comp_no) compName,           ");
        query.append("        a.user_id userId,                                  ");
        query.append("        a.user_no userNo,                                  ");
        query.append("        a.password,                                        ");
        query.append("        a.user_name userName,                              ");
        query.append("        a.usrgrp_id usrgrpId,                              ");
        query.append("        a.alarm_view_yn alarmViewYn,                       ");
        query.append("        (SELECT z.usrgrp_no                                ");
        query.append("         FROM   TAUSRGRP z                                 ");
        query.append("         WHERE  a.usrgrp_id = z.usrgrp_id) usrgrpName,     ");
        query.append("        a.init_menu_id initMenuId,                         ");
        query.append("        (SELECT                                            ");
        query.append("                y.file_name                                ");
        query.append("         FROM  TAMENU x, TAPAGE y                          ");
        query.append("         WHERE x.page_id = y.page_id                       ");
        query.append("          AND  x.menu_id = a.init_menu_id) fileName,       ");
        query.append("        '"+localeId+"' langId,                             ");
        query.append("        '"+localeId+"' lang,                               ");
        query.append("        SFACODE_TO_DESC('"+localeId+"','LANG','SYS','','"+localeId+"') langDesc, ");
        query.append("        b.dept_id deptId,                                  ");
        query.append("       (SELECT dept_no FROM TADEPT                         ");
        query.append("        WHERE  comp_no = b.comp_no                         ");
        query.append("          AND  dept_id = b.dept_id) deptNo,                ");
        query.append("        SFAIDTODESC(b.dept_id,'', 'DEPT','"+compNo+"')  deptDesc, ");
        query.append("        a.filter_dept_id filterDeptId,                                  ");
        query.append("        SFAIDTODESC(a.filter_dept_id,'', 'DEPT','"+compNo+"')  filterDeptDesc, ");
        query.append("        a.filter_emp_id filterEmpId,                       ");
        query.append("        (SELECT x.emp_name                                 ");
        query.append("           FROM TAEMP x                                    ");
        query.append("          WHERE x.comp_no = a.comp_no                      ");
        query.append("            AND x.emp_id = a.filter_emp_id) filterEmpDesc, ");
        query.append("        b.emp_id   empId,                                  ");
        query.append("        b.emp_no   empNo,                                  ");
        query.append("        b.emp_name empName,                                ");
        query.append("       (SELECT wcode_id FROM TADEPT                        ");
        query.append("        WHERE  comp_no = b.comp_no                         ");
        query.append("          AND  dept_id = b.dept_id) wcodeId,               ");
        query.append("       (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("        WHERE  comp_no  = b.comp_no                        ");
        query.append("          AND  wcode_id = (SELECT wcode_id FROM TADEPT     ");
        query.append("                           WHERE  comp_no = b.comp_no      ");
        query.append("                             AND  dept_id = b.dept_id)) wname,        ");
        query.append("       (SELECT twcode_id FROM TADEPT                       ");
        query.append("        WHERE  comp_no = b.comp_no                         ");
        query.append("          AND  dept_id = b.dept_id) twcodeId,              ");
        query.append("       (SELECT dept_no FROM TADEPT                         ");
        query.append("        WHERE  comp_no = b.comp_no                         ");
        query.append("          AND  dept_id = b.dept_id) deptNo,                ");
        query.append("       (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("        WHERE  comp_no  = b.comp_no                        ");
        query.append("          AND  wcode_id = (SELECT twcode_id FROM TADEPT     ");
        query.append("                           WHERE  comp_no = b.comp_no      ");
        query.append("                             AND  dept_id = b.dept_id)) twname,       ");
        query.append("       (SELECT from_wcode_id FROM TADEPT                        ");
        query.append("        WHERE  comp_no = b.comp_no                         ");
        query.append("          AND  dept_id = b.dept_id) fromWcodeId,               ");
        query.append("       (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("        WHERE  comp_no  = b.comp_no                        ");
        query.append("          AND  wcode_id = (SELECT from_wcode_id FROM TADEPT     ");
        query.append("                           WHERE  comp_no = b.comp_no      ");
        query.append("                             AND  dept_id = b.dept_id)) fromWname,        ");
        query.append("       (SELECT to_wcode_id FROM TADEPT                        ");
        query.append("        WHERE  comp_no = b.comp_no                         ");
        query.append("          AND  dept_id = b.dept_id) toWcodeId,               ");
        query.append("       (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("        WHERE  comp_no  = b.comp_no                        ");
        query.append("          AND  wcode_id = (SELECT to_wcode_id FROM TADEPT     ");
        query.append("                           WHERE  comp_no = b.comp_no      ");
        query.append("                             AND  dept_id = b.dept_id)) toWname,        ");
        query.append("         a.secur_grade securGrade,                         ");
        query.append("         scrn_font_size scrnFontSize,                      ");
        query.append("         (SELECT b.ct_path           			             ");
        query.append("          FROM   TACOMP b                       			 ");
        query.append("          WHERE  b.comp_no = a.comp_no) ctPath,            ");
        query.append("			a.shift_type 					     shiftType,	 ");
        query.append("			a.change_pwd_date 					 changePwdDate,	 ");
        query.append("			SFACODE_TO_DESC(a.shift_type,'SHIFT_TYPE','SYS','','"+localeId+"') shiftTypeDesc,	");
        query.append("       b.wkctr_id	                             					wkCtrId,		");
        query.append("		 (SELECT description														");
        query.append("		  FROM TAWKCTR																");
        query.append("		  WHERE comp_no = b.comp_no													");
        query.append("		  AND wkctr_id = b.wkctr_id) 			 					wkCtrDesc,		");
        query.append("			a.eqloc_id eqLocId,								");
        query.append("			(SELECT full_desc								");
        query.append("			   FROM TAEQLOC x								");
        query.append("			WHERE x.eqloc_id = a.eqloc_id					");
        query.getAndQuery("x.comp_no", compNo);
        query.append("			) eqLocDesc,									");
        query.append("			b.plant plant,									");
        query.append("			SFAPLANTTODESC(b.comp_no, b.plant) plantDesc,	");
        query.append("        NVL((SELECT plant                                 ");
        query.append("             FROM   TAUSRPLANTAUTH c                      ");
        query.append("             WHERE  A.user_id = c.user_id                 ");
        query.append("				 AND  a.comp_no = c.comp_no					");
        query.append("               AND  is_auth = 'Y'                         ");
        query.append("               AND  c.plant = a.filter_plant              ");
        query.append("             ),'') filterPlant,                           ");
        query.append("        NVL((SELECT d.description                         ");
        query.append("             FROM   TAUSRPLANTAUTH c INNER JOIN TAPLANT d ON c.plant = d.plant AND d.is_use = 'Y'   ");
        query.append("             WHERE  A.user_id = c.user_id                 ");
        query.append("				 AND  a.comp_no = c.comp_no					");
        query.append("				 AND  c.comp_no = d.comp_no					");
        query.append("               AND  is_auth = 'Y'                         ");
        query.append("               AND  c.plant = a.filter_plant              ");
        query.append("            ),'') filterPlantDesc,                        ");
        query.append("			a.eqctg_type eqCtgTypeId,									");
        query.append("			SFACODE_TO_DESC(a.eqctg_type,'EQCTG_TYPE','SYS','','"+localeId+"') eqCtgTypeDesc	");
        query.append("          ,NVL(a.menu_display,(select cdsysd_no from tacdsysd where list_type ='MENU_DISPLAY' and rownum = 1)) menuDisplay          ");
        query.append("          ,a.m_phone mPhone                                ");
        query.append("          ,a.e_mail eMail                                  ");
        query.append("          ,a.filter_wkctr_id filterWkCtrId                 ");
        query.append("          ,(SELECT x.description                           ");
        query.append("           FROM TAWKCTR x                                  ");
        query.append("          WHERE x.comp_no = a.comp_no                      ");
        query.append("            AND x.wkctr_id = a.filter_wkctr_id) filterWkCtrDesc ");
        query.append("          ,a.filter_wcode_id filterWcodeId				 ");
        query.append("          ,(SELECT aa.wname                                ");
        query.append("            FROM TAWAREHOUSE aa                            ");
        query.append("            WHERE aa.comp_no = a.comp_no                   ");
        query.append("             AND aa.wcode_id = a.filter_wcode_id) filterWcodeDesc	");
        query.append("          ,a.otp_key otpKey                                ");
        query.append("			,a.is_locked			isLocked				 ");
        query.append("          ,a.login_fail_cnt       loginFailCnt			 ");
        query.append("        ,a.filter_usage_dept_id 	filterUsageDeptId		 ");
        query.append("        ,SFAIDTODESC(a.filter_usage_dept_id,'', 'DEPT','"+compNo+"')	filterUsageDeptDesc	");
        query.append("FROM    TAUSER a, TAEMP b                                  ");
        query.append("WHERE a.emp_id = b.emp_id                                  ");
        query.append("  AND   a.comp_no = b.comp_no                              ");
        query.append("  AND   a.comp_no = ?                                      ");
        query.append("  AND   UPPER(a.user_no) = ?                               ");
        query.append("  AND   a.is_use != 'N'                                    ");
        query.append("  AND   b.is_join != 'N'                                   ");

        Object [] object = new Object[]{
                compNo
        		,userNo.toUpperCase()   
        };
        
        return getJdbcTemplate().queryForList(query.toString(), object);
    }
    
    /**
     * 로그인 정보 저장
     * @author  javaworker
     * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @param loginTerminal 
     * @param terminalNo 
     */
    public void insertLogHist(User loginUser, String loginTerminal, String terminalNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TALOGINCCLOG (									");
        query.append("	logincclog_id,				comp_no,						");
        query.append("	user_id,					login_date,						");
        query.append("	login_time,					login_terminal,					");
        query.append("	terminal_no													");
        query.append("	)															");
        query.append("VALUES (														");
        query.append("	SQALOGINCCLOG_ID.nextval,	?,								");
        query.append("	?,							TO_CHAR(SYSDATE,'yyyymmdd'),	");
        query.append("	SYSDATE,					?,								");
        query.append("	?															");
        query.append("	)															");
        
        Object [] object = new Object[]{
        		loginUser.getCompNo(),
                loginUser.getUserId(),
                loginTerminal,
                terminalNo
        };
        
        getJdbcTemplate().update(query.toString(), object);
    }
    
    /**
     * 로그인 날짜저장
     * @author  javaworker
     * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginDTO
     */
    public void setLoginDate(LoginDTO loginDTO)
    {
    	String userNo = loginDTO.getUserNo();
    	String compNo = loginDTO.getCompNo();
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAUSER SET                         ");
        query.append("login_date = TO_CHAR(sysdate,'yyyymmdd') 	");
        query.append(",reset_password = NULL                 	");
        query.append("WHERE UPPER(user_no) = '"+userNo+"'		");
        query.append("    and comp_no = '"+compNo+"'			");
        
        getJdbcTemplate().update(query.toString());
    }

	/**
	 * 인터페이스 Authorization 
	 * @param map
	 * @return
	 */
	public List findIfUserInfo(LoginDTO loginDTO) 
	{
		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT				");
		query.append("       comp_no,		");
		query.append("       description,	");
		query.append("       if_pwd ifPwd,  ");
		query.append("       if_user,		");
		query.append("       is_use 		");
		query.append("FROM   TAIFAUTH		");
		query.append("WHERE  is_use  = 'Y'	");
		query.append("  AND  if_user = ?	");
		
        Object [] object = new Object[]{
        		loginDTO.getUserNo()
        };
        
        return getJdbcTemplate().queryForList(query.toString(), object);
	}
	/**
	 * 허용된 device id 찾기
	 * @param loginDTO
	 * @return
	 */
	public List findIfDeviceInfo(LoginDTO loginDTO) 
	{
		QueryBuffer query = new QueryBuffer();

    	query.append("SELECT            				");
        query.append("      service_type serviceType	");
        query.append("FROM   TAACCESSMNL				");
        query.append("WHERE  is_use= 'Y'				");
        query.append("AND service_type IN ('ANDROID','ANT','BEE','CRICKET','WEB')			");
        query.append("AND terminal_no = '"+loginDTO.getDeviceId()+"' ");
        query.append("AND comp_no = '"+loginDTO.getCompNo()+"' ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
        
	}
	
	public String  countNamedUserCnt()
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("select count(1)                                                                         ");
    	query.append("from tauser                                                                             ");
    	query.append("where 1=1                                                                               ");
    	query.append("     and usrgrp_id in (select usrgrp_id                                                 ");
    	query.append("                       from tausrgrp                                                    ");
    	query.append("                       where comp_no in (select comp_no from tacomp where is_use = 'Y') ");
    	query.append("                           and usrgrp_no not in (select value$                          ");
    	query.append("                                                 from taconfig                          ");
    	query.append("                                                 where name = 'SYSTEM_ADMIN_GROUP'      ");
    	query.append("                                                    and rownum = 1 )                    ");
    	query.append("                      )                                                                 ");
    	query.append("    and is_use ='Y'                                                                     ");
    	query.append("    and comp_no in (select comp_no from tacomp where is_use = 'Y')                      ");

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

        
    }
	
	/**
	 * 로그인 시도 이력 저장
	 * @author  youngjoo38
	 * @version $Id$
	 * @since   1.0
	 * 
	 * @param loginUser
	 * @param loginTerminal 
	 * @param terminalNo 
	 */
	public void insertLoginTryLog(LoginDTO loginDTO, String loginTerminal, String terminalNo, String isSuccess, String paramLog)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("INSERT INTO TALOGINTRYLOG (									");
		query.append("	  logintrylog_id			, comp_no						");
		query.append("	, user_no					, password						");
		query.append("	, login_date				, login_time					");
		query.append("	, login_terminal			, terminal_no					");
		query.append("	, terminal_ver				, is_success					");
		query.append("  , param_log													");
		query.append("	)															");
		query.append("VALUES (														");
		query.append("	  SQALOGINTRYLOG_ID.NEXTVAL	, ?								");
		query.append("	, ?							, ?								");
		query.append("	, ?							, ?								");
		query.append("	, ?							, ?								");
		query.append("	, ?							, ?								");
		query.append("	, ?															");
		query.append("	)															");
		
		Object [] object = new Object[]{
				loginDTO.getCompNo()
				,loginDTO.getUserNo()
				,CommonUtil.aesEncodeString(loginDTO.getPassWord())
				,DateUtil.getDate()
				,DateUtil.getDateTime().substring(8)
				,loginTerminal
				,terminalNo
				,""
				,isSuccess
				,paramLog
		};
		
		getJdbcTemplate().update(query.toString(), object);
	}
	
	public void updateFailCnt(LoginDTO loginDTO, String isSuccess)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("UPDATE TAUSER SET				");
		if("Y".equals(isSuccess)){
			query.append("	  login_fail_cnt = 0	");
			query.append("	, is_locked = 'N'		");
		}
		else{
			query.append("	  login_fail_cnt = login_fail_cnt+1	");
		}
		query.append("WHERE comp_no = ?				");
		query.append("  AND user_no = ?				");
		
		Object [] object = new Object[]{
				loginDTO.getCompNo()
				,loginDTO.getUserNo()
		};
		
		getJdbcTemplate().update(query.toString(), object);
	}
	
	public void updateLockUser(LoginDTO loginDTO)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("UPDATE TAUSER SET is_locked = CASE WHEN (SELECT a.value$ FROM TACONFIG a 		");
		query.append("                                          WHERE a.comp_no = ? AND a.NAME = ?) ");
		query.append("                                      <= login_fail_cnt				 		");
		query.append("                              THEN 'Y' ELSE 'N' END 							");
		query.append("WHERE comp_no = ?		");
		query.append("AND user_no   = ?		");
		
		Object [] object = new Object[]{
				loginDTO.getCompNo()
				,"PW_FAIL_LIMIT_CNT"
				,loginDTO.getCompNo()
				,loginDTO.getUserNo()
		};
		
		getJdbcTemplate().update(query.toString(), object);
	}

	@Override
	public List findCompInfo(LoginDTO loginDTO)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                                            	");
		query.append("       a.comp_no                      compNo	        ");
		query.append("       ,(SELECT description                        	");
		query.append("        FROM TACOMP                                	");
		query.append("        WHERE comp_no = a.comp_no)    compDesc    	");
		query.append("       ,a.user_no                     userNo  	    ");
		query.append("       ,a.password                    password    	");
		query.append("FROM   TAUSER    a                                    ");
		query.append("WHERE upper(a.user_no)  = ?                           ");

        Object [] object = new Object[]{
        		loginDTO.getUserNo().toUpperCase()
        };
		
		return getJdbcTemplate().queryForList(query.toString(), object);
	}
	
}