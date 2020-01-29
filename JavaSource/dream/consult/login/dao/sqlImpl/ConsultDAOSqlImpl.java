package dream.consult.login.dao.sqlImpl;
import java.util.ArrayList;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.login.dao.ConsultDAO;
import dream.consult.login.dto.ConsultDTO;

/**
 * Consult Login DAO
 * @author  jung7126
 * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="consultDAOTarget"
 * @spring.txbn id="consultDAO"
 * @spring.property name="dataSource" ref="dataSource" 
 */
public class ConsultDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultDAO
{
    /**
     * 해당 User Id 의 정보를 조회한다.        
     * @author  jung7126
     * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
     * @since   1.0
     * 
     * @param consultDTO
     * @return
     * @throws Exception
     */
    public List findUserInfo(ConsultDTO consultDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                            ");
        query.append("       a.ehuser_id userId,                                        ");
        query.append("       a.ehuser_no userNo,                                        ");
        query.append("       a.PASSWORD,                                                ");
        query.append("       a.euser_name userName                                      ");
        query.append("FROM   TAEHUSER a                                                 ");
        query.append("WHERE  a.ehuser_no = ?                                            ");
        query.append("  AND  a.is_use = 'Y'                                             ");

        Object [] object = new Object[]{
                consultDTO.getUserNo()   
        };
        
        return getJdbcTemplate().queryForList(query.toString(), object);
    }

    public ArrayList findConsultMenuList(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = loginUser.getLangId();
        
        query.append("SELECT                                        ");
        query.append("      '',                                     ");
        query.append("      (SELECT file_name                       ");
        query.append("         FROM TAPAGE                          ");
        query.append("        WHERE page_id = x.page_id             ");
        query.append("      ) PAGEID,                               ");
        query.append("      x.ehmenu_id MENUID,                     ");
        query.append("      x.p_ehmenu_id PMENUID,                  ");
        query.append("      x.ord_no ORDNO,                         ");
        query.append("      x.description DESCRIPTION,              ");
        query.append("       (SELECT a.key_name                     ");
        query.append("        FROM   TALANG a                       ");
        query.append("        WHERE  a.key_no = x.key_no            ");
        query.append("          AND  a.key_type ='MENU'             ");
        query.append("          AND  a.lang = '"+lang+"') AS TEXT,  ");
        query.append("      ISNULL((SELECT file_name                ");
        query.append("       FROM   TAPAGE                          ");
        query.append("       WHERE  page_id = x.page_id             ");
        query.append("      ),'')+'?'+ISNULL(x.param, '') AS URL,   ");
        query.append("      y.lvl LVL,                              ");
        query.append("      x.param PARAM,                          ");
        query.append("      key_no KEYNO,                           ");
        query.append("      key_type KEYTYPE                        ");
        query.append("FROM  TAEHMENU x                              ");
        query.append("		,(SELECT * FROM dbo.SFAEHMENU_ALL('0')) y ");
        query.append("WHERE  x.EHMENU_ID =  y.EHMENU_ID             ");
        query.append("	AND  x.is_use = 'Y'                         ");
//        query.append("  START WITH P_EHMENU_ID = '0'                ");
//        query.append("  CONNECT BY PRIOR x.EHMENU_ID = p_ehmenu_id  ");
        query.append("ORDER BY x.ord_no                             ");


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
}