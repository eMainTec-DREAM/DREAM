package mobile.dream.login.dao.sqlImpl;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import mobile.dream.login.dao.MobileLoginDAO;
import mobile.dream.login.dto.MobileLoginDTO;

/**
 * Login DAO
 * @author  javaworker
 * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="mobileLoginDAOTarget"
 * @spring.txbn id="mobileLoginDAO"
 * @spring.property name="dataSource" ref="dataSource" 
 */
public class MobileLoginDAOSqlImpl extends BaseJdbcDaoSupportSql implements MobileLoginDAO
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
    public List findUserInfo(MobileLoginDTO loginDTO)
    {
    	String userNo = loginDTO.getUserNo();
    	String localeId = loginDTO.getLocale();
    	String compNo = loginDTO.getCompNo();
        QuerySqlBuffer query = new QuerySqlBuffer();
        
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
        query.append("        dbo.SFACODE_TO_DESC('"+localeId+"','LANG','SYS','','"+localeId+"') langDesc, ");
        query.append("        b.dept_id deptId,                                  ");
        query.append("        dbo.SFAIDTODESC(b.dept_id,'', 'DEPT','"+compNo+"')  deptDesc, ");
        query.append("        a.filter_dept_id filterDeptId,                                  ");
        query.append("        dbo.SFAIDTODESC(a.filter_dept_id,'', 'DEPT','"+compNo+"')  filterDeptDesc, ");
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
        query.append("         (SELECT a.ct_path           			             ");
        query.append("          FROM   TACOMP a                       			 ");
        query.append("          WHERE  a.comp_no = a.comp_no) ctPath,            ");
        query.append("			a.shift_type 					     shiftType,	 ");
        query.append("			dbo.SFACODE_TO_DESC(a.shift_type,'SHIFT_TYPE','SYS','','"+localeId+"') shiftTypeDesc,	");
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
        query.append("			dbo.SFAPLANTTODESC(b.comp_no, b.plant) plantDesc,	");
        query.append("			a.eqctg_type eqCtgTypeId,									");
        query.append("			dbo.SFACODE_TO_DESC(a.eqctg_type,'EQCTG_TYPE','SYS','','"+localeId+"') eqCtgTypeDesc	");
        query.append("          ,a.menu_display menuDisplay                      ");
        query.append("FROM    TAUSER a, TAEMP b                                  ");
        query.append("WHERE a.emp_id = b.emp_id                                  ");
        query.append("  AND   a.comp_no = b.comp_no                              ");
        query.append("  AND   a.comp_no = ?                                      ");
        query.append("  AND   a.user_no = ?                                      ");
        query.append("  AND   a.is_use != 'N'                                    ");
        query.append("  AND   b.is_join != 'N'                                   ");

        Object [] object = new Object[]{
        		compNo
        		,userNo   
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TALOGINCCLOG (									");
        query.append("	logincclog_id,				comp_no,						");
        query.append("	user_id,					login_date,						");
        query.append("	login_time,					login_terminal,					");
        query.append("	terminal_no													");
        query.append("	)															");
        query.append("VALUES (														");
        query.append("	NEXT VALUE FOR SQALOGINCCLOG_ID,	?,								");
        query.append("	?,							CONVERT(VARCHAR, GETDATE(), 112),	");
        query.append("	GETDATE(),					?,								");
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
    public void setLoginDate(MobileLoginDTO loginDTO)
    {
    	String userNo = loginDTO.getUserNo();
    	String compNo = loginDTO.getCompNo();
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAUSER SET                         ");
        query.append("login_date = CONVERT(VARCHAR, GETDATE(), 112) 	");
        query.append("WHERE user_no = '"+userNo+"'				");
        query.append("    and comp_no = '"+compNo+"'			");
        
        getJdbcTemplate().update(query.toString());
    }

	/**
	 * 인터페이스 Authorization 
	 * @param map
	 * @return
	 */
	public List findIfUserInfo(MobileLoginDTO loginDTO) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
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
}