package gaia.gaia.dao.sqlImpl;
import java.util.ArrayList;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import gaia.gaia.dao.GaiaDAO;
import gaia.gaia.dto.GaiaDTO;

/**
 * Gaia Login DAO
 * @author  jung7126
 * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="gaiaDAOTarget"
 * @spring.txbn id="gaiaDAO"
 * @spring.property name="dataSource" ref="dataSource" 
 */
public class GaiaDAOSqlImpl extends BaseJdbcDaoSupportSql implements GaiaDAO
{
    /**
     * 해당 User Id 의 정보를 조회한다.        
     * @author  jung7126
     * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
     * @since   1.0
     * 
     * @param gaiaDTO
     * @return
     * @throws Exception
     */
    public List findUserInfo(GaiaDTO gaiaDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                        ");
        query.append("       a.suser_id userId,                                     ");
        query.append("       a.suser_no userNo,                                     ");
        query.append("       a.PASSWORD,                                            ");
        query.append("       a.suser_name userName                                  ");
        query.append("FROM   TSUSER a                                               ");
        query.append("WHERE  a.suser_no = ?                                         ");
        query.append("  AND  a.isuse = 'Y'                                          ");


        Object [] object = new Object[]{
                gaiaDTO.getUserNo()   
        };
        
        return getJdbcTemplate().queryForList(query.toString(), object);
    }

    public ArrayList findGaiaMenuList(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = loginUser.getLangId();
        
        query.append("SELECT                                    ");
        query.append("      '',                                 ");
        query.append("      (SELECT sfile_name                  ");
        query.append("         FROM TSFILE                      ");
        query.append("        WHERE sfile_id = x.sfile_id       ");
        query.append("      ) pageId,                           ");
        query.append("      x.sdevmenu_id menuId,               ");
        query.append("      x.p_sdevmenu_id pMenuId,            ");
        query.append("      x.ord_no ordNo,                     ");
        query.append("      x.description description,          ");
        query.append("      (SELECT sfile_name                  ");
        query.append("       FROM   TSFILE                      ");
        query.append("       WHERE  sfile_id = x.sfile_id       ");
        query.append("      )+'?' AS url,                      ");
        query.append("      LEVEL lvl,                          ");
        query.append("      key_no keyNo,                       ");
        query.append("      'GAIA.'+key_type keyType           ");
        query.append("FROM  TSDEVMENU x                         ");
        query.append("WHERE  x.isuse = 'Y'                      ");
        query.append("  START WITH p_sdevmenu_id = '0'                      ");
        query.append("  CONNECT BY PRIOR x.sdevmenu_id = p_sdevmenu_id      ");
        query.append("ORDER BY x.ord_no                                     ");

        ArrayList resultList = (ArrayList)getJdbcTemplate().queryForList(query.toString());

        return resultList;
    }

    /**
     * Find Field Info of each Page 
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findPageFields(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                        ");
        query.append("       sfile_id pageId,                                       ");
        query.append("       (SELECT b.sfile_name FROM TSFILE b WHERE b.sfile_id = x.sfile_id) fileName,        ");
        query.append("       sfilefield_id fieldId,                                 ");
        query.append("       issystem hiddenYn,                                     ");
        query.append("       x.iseditable readonlyYn,                               ");
        query.append("       x.ord_no ordNo,                                        ");
        query.append("       x.isdisplay displayYn,                                 ");
        query.append("       x.field_option fieldOption,                            ");
        query.append("       '' +'.'+'' langKey                                   ");
        query.append("FROM   TSFILEFIELD x                                          ");
        query.append("ORDER BY x.ord_no DESC                                        ");

        return getJdbcTemplate().queryForList(query.toString());
    }

    /**
     * 개발자 로그인 히스토리
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @param loginTerminal
     * @param terminalNo
     */
    public void insertLogHist(User loginUser, String loginTerminal,String terminalNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TSLOGINLOG (                                          ");
        query.append("  sloginlog_id,                                                   ");
        query.append("  sdev_id,                    login_date,                         ");
        query.append("  login_time,                 login_terminal,                     ");
        query.append("  terminal_no                                                     ");
        query.append("  )                                                               ");
        query.append("VALUES (                                                          ");
        query.append("  NEXT VALUE FOR SQASLOGINLOG_ID,                                        ");
        query.append("  ?,                          CONVERT(VARCHAR, GETDATE(), 112),        ");
        query.append("  SYSDATE,                    ?,                                  ");
        query.append("  ?                                                               ");
        query.append("  )                                                               ");

        
        Object [] object = new Object[]{
                loginUser.getUserId(),
                loginTerminal,
                terminalNo
        };
        
        getJdbcTemplate().update(query.toString(), object);
    }
}