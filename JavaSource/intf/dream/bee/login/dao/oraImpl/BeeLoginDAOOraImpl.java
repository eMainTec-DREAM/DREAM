package intf.dream.bee.login.dao.oraImpl;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.bee.login.dao.BeeLoginDAO;

/**
 * Android Login DAO
 * @author  kim21017
 * @version $Id: BeeLoginDAOOraImpl.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="beeLoginDAOTarget"
 * @spring.txbn id="beeLoginDAO"
 * @spring.property name="dataSource" ref="dataSource" 
 */
public class BeeLoginDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeLoginDAO
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
    	String compNo = String.valueOf(map.get("compNo"));
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                     ");
        query.append("        a.comp_no compNo,                                  ");
        query.append("        a.user_id userId,                                  ");
        query.append("        a.user_no userNo,                                  ");
        query.append("        a.password,                                        ");
        query.append("        a.change_pwd_date	AS changePwdDate                 ");
        query.append("         ,nvl((SELECT x.value$                                ");
        query.append("             FROM TACONFIG  x                                    ");
        query.append("            WHERE x.comp_no = a.comp_no                          ");
        query.append("              AND x.name  = 'GRID_MAX_LOAD_COUNT'),'0') GRIDMAXLOADCOUNT ");
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