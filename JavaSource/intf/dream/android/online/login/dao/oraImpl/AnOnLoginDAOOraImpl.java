package intf.dream.android.online.login.dao.oraImpl;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.android.online.login.dao.AnOnLoginDAO;

/**
 * Android Login DAO
 * @author  kim21017
 * @version $Id: AnOnLoginDAOOraImpl.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="anOnLoginDAOTarget"
 * @spring.txbn id="anOnLoginDAO"
 * @spring.property name="dataSource" ref="dataSource" 
 */
public class AnOnLoginDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnLoginDAO
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
    public List findUserInfo(Map map)
    {
    	String userNo = String.valueOf(map.get("userNo"));
    	String localeId = String.valueOf(map.get("lang"));
    	String compNo = String.valueOf(map.get("compNo"));
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                     ");
        query.append("        a.comp_no compNo,                                  ");
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
        query.append("        (SELECT SFACODE_TO_DESC('"+localeId+"','LANG','SYS','','"+localeId+"') FROM DUAL) langDesc, ");
        query.append("        b.dept_id deptId,                                  ");
        query.append("        (SELECT SFAIDTODESC(b.dept_id,'', 'DEPT','"+compNo+"') FROM DUAL)  deptDesc, ");
        query.append("        a.filter_dept_id filterDeptId,                                  ");
        query.append("        (SELECT SFAIDTODESC(a.filter_dept_id,'', 'DEPT','"+compNo+"') FROM DUAL)  filterDeptDesc, ");
        query.append("        b.emp_id   empId,                                  ");
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
        query.append("       (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("        WHERE  comp_no  = b.comp_no                        ");
        query.append("          AND  wcode_id = (SELECT twcode_id FROM TADEPT     ");
        query.append("                           WHERE  comp_no = b.comp_no      ");
        query.append("                             AND  dept_id = b.dept_id)) twname,       ");
        query.append("         a.secur_grade securGrade,                         ");
        query.append("         scrn_font_size scrnFontSize,                      ");
        query.append("         (SELECT b.ct_path           			             ");
        query.append("          FROM   TACOMP b                       			 ");
        query.append("          WHERE  b.comp_no = a.comp_no) ctPath,            ");
        query.append("			a.shift_type 					     shiftType,	 ");
        query.append("			a.change_pwd_date 					 changePwdDate,	 ");
        query.append("			(SELECT SFACODE_TO_DESC(a.shift_type,'SHIFT_TYPE','SYS','','"+localeId+"') FROM DUAL) shiftTypeDesc,	");
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
        query.append("			(SELECT SFAPLANTTODESC(b.comp_no, b.plant) FROM DUAL) plantDesc,	");
        query.append("			a.eqctg_type eqCtgTypeId,						");
        query.append("			(SELECT SFACODE_TO_DESC(a.eqctg_type,'EQCTG_TYPE','SYS','','"+localeId+"') FROM DUAL) eqCtgTypeDesc ");
        query.append("          ,a.menu_display menuDisplay                      ");
        query.append("          ,a.filter_wkctr_id					filterWkctrId		");
        query.append("			,(SELECT description								");
        query.append("		  		FROM TAWKCTR									");
        query.append("		  		WHERE comp_no = a.comp_no						");
        query.append("		  		AND wkctr_id = a.filter_wkctr_id) 	filterWkctrDesc		");
        query.append("         ,a.filter_wcode_id					filterWcodeId		");
        query.append("         ,(SELECT wname FROM TAWAREHOUSE                      ");
        query.append("        WHERE  comp_no  = a.comp_no                        ");
        query.append("          AND  wcode_id = a.filter_wcode_id) filterWcodeDesc       ");;
        query.append("         ,a.filter_emp_id					filterEmpId		");
        query.append("         ,(SELECT emp_name FROM TAEMP                      ");
        query.append("        WHERE  comp_no  = a.comp_no                        ");
        query.append("          AND  emp_id = a.filter_emp_id) filterEmpDesc     ");
        query.append("         ,nvl((SELECT x.value$                                ");
        query.append("             FROM TACONFIG  x                                    ");
        query.append("            WHERE x.comp_no = a.comp_no                          ");
        query.append("              AND x.name  = 'BARCODE_DIVIDER'),'') barcodeDivider ");
        query.append("         ,nvl((SELECT x.value$                                ");
        query.append("             FROM TACONFIG  x                                    ");
        query.append("            WHERE x.comp_no = a.comp_no                          ");
        query.append("              AND x.name  = 'BARCODE_SEQ'),'') barcodeSeq        ");
        query.append("         ,nvl((SELECT x.value$                                ");
        query.append("             FROM TACONFIG  x                                    ");
        query.append("            WHERE x.comp_no = a.comp_no                          ");
        query.append("              AND x.name  = 'WORK_START_BASE_TIME'),'') WORKSTARTBASETIME        ");
        query.append("        ,a.filter_usage_dept_id filterUsageDeptId          ");
        query.append("        ,(SELECT SFAIDTODESC(a.filter_usage_dept_id,'', 'DEPT','"+compNo+"') FROM DUAL)  filterUsageDeptDesc ");
        query.append("        ,a.filter_day_interval  filterDayInterval                              ");
        query.append("			,CASE WHEN a.filter_plant is null THEN b.plant ELSE a.filter_plant END  filterPlant									");
        query.append("			,(SELECT SFAPLANTTODESC(b.comp_no, CASE WHEN a.filter_plant is null THEN b.plant ELSE a.filter_plant END) FROM DUAL) filterPlantDesc	");
        query.append("			,(SELECT (SELECT y.file_name FROM TAPAGE y WHERE y.page_id = x.page_id) FROM TAMENU x WHERE x.menu_id = a.bee_init_menu_id) beeInitMenuId		");
        query.append("FROM    TAUSER a, TAEMP b                                  ");
        query.append("WHERE a.emp_id = b.emp_id                                  ");
        query.append("  AND   a.comp_no = b.comp_no                              ");
        query.append("  AND   a.comp_no = ?                                      ");
        query.append("  AND   UPPER(a.user_no) = UPPER(?)                        ");
        query.append("  AND   a.is_use != 'N'                                    ");
        query.append("  AND   b.is_join != 'N'                                   ");

        Object [] object = new Object[]{
        		compNo
        		,userNo   
        };
        
        return getJdbcTemplate().queryForList(query.toString(), object);
    }
    public List findDeviceInfo(Map map)
    {
    	String compNo = String.valueOf(map.get("compNo"));
    	String deviceId = String.valueOf(map.get("deviceId"));
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT accessmnl_id			");
        query.append("FROM TAACCESSMNL				");
        query.append("WHERE 1=1						");
        query.append("AND comp_no = ?				");
        query.append("AND terminal_no = ?			");
        query.append("AND service_type IN ('ANDROID','ANT','BEE','CRICKET')			");


        Object [] object = new Object[]{
        		compNo
        		,deviceId   
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
    public void setLoginDate(Map map)
    {
    	String userNo = String.valueOf(map.get("userNo"));
    	String compNo = String.valueOf(map.get("compNo"));
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAUSER SET                         ");
        query.append("login_date = TO_CHAR(sysdate,'yyyymmdd') 	");
        query.append("WHERE UPPER(user_no) = UPPER('"+userNo+"')");
        query.append("    and comp_no = '"+compNo+"'			");
        
        getJdbcTemplate().update(query.toString());
    }

	/**
	 * 인터페이스 Authorization 
	 * @param map
	 * @return
	 */
	public List findIfUserInfo(Map map) 
	{
    	String userNo = String.valueOf(map.get("userNo"));
		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT				");
		query.append("       comp_no,		");
		query.append("       description,	");
		query.append("       if_pwd ifPwd,  ");
		query.append("       if_user,		");
		query.append("       is_use 		");
		query.append("FROM   TAIFAUTH		");
		query.append("WHERE  is_use  = 'Y'	");
		query.append("  AND  UPPER(if_user) = UPPER(?)	");
		
        Object [] object = new Object[]{
        		userNo
        };
        
        return getJdbcTemplate().queryForList(query.toString(), object);
	}
}