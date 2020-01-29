package common.config.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.config.dao.ConfigConsultDAO;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;

/**
 * 기본적인 환경설정을 DB에서 조회할때 
 * 공통으로 사용된다.
 * @author  javaworker
 * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
 * @since   1.0
 * @spring.bean id="configConsultDAOTarget"
 * @spring.txbn id="configConsultDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConfigConsultDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConfigConsultDAO
{
    /**
     * 모든 page 와 button을 로딩한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findPageButton(String pageId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       a.sfile_name AS fileName,                          ");
        query.append("       (SELECT c.sbtnfunc_no                              ");
        query.append("        FROM   TSBTNFUNC c                                ");
        query.append("        WHERE  c.sbtnfunc_id =x.sbtnfunc_id) buttonId,    ");
        query.append("       x.filebtnfunc_loc buttonLoc,                       ");
        query.append("       x.ord_no,                                          ");
        query.append("       'BUTTON.'+x.key_no remark,                        ");
        query.append("       x.sfilefunc_id pgBtnId,                            ");
        query.append("       (SELECT c.func_img                                 ");
        query.append("        FROM   TSBTNFUNC c                                ");
        query.append("        WHERE  c.sbtnfunc_id = x.sbtnfunc_id) buttonImg,  ");
        query.append("       'N' isChkauth,                                     ");
        query.append("       'N' isChkauthBtn,                                  ");
        query.append("       'Y' isBasic,                                       ");
        query.append("       'N' isSetGroup                                     ");
        query.append("FROM   TSFILE a ,TSFILEFUNC x                             ");
        query.append("WHERE  a.sfile_id = x.sfile_id                            ");
        query.append("  AND  x.isuse = 'Y'                                      ");
        query.getAndQuery("a.sfile_name", pageId);
        query.append("ORDER BY 1,3, x.ord_no DESC                               ");
        
        return getJdbcTemplate().queryForList(query.toString());
        //return QuerySqlBuffer.toStringArray(resultList);
    }
    
    /**
     * Security Table 을 조회한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findAllTabPages()
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("       y.sfile_name pPageName,                        ");
        query.append("       'TAB.'+(SELECT b.sfile_name FROM TSFILE b WHERE b.sfile_id = x.linked_sfile_id) tabPageName,          ");
        query.append("       x.key_type+'.'+x.key_no langKey,             ");
        query.append("       sfilelink_id pgPageId,                         ");
        query.append("       y.sfile_id pageId,                             ");
        query.append("       x.isuse isUse,                                 ");
        query.append("       'N' isChkauth                                  ");
        query.append("FROM   TSFILELINK  x, TSFILE y                        ");
        query.append("WHERE x.sfile_id = y.sfile_id                         ");
        query.append("  AND x.isuse = 'Y'                                   ");
        query.append("ORDER BY x.sfile_id, TO_NUMBER(x.ord_no)              ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * DB에 등록된 전체 page_id 의 menu_path를 조회한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findAllMenuPath()
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT (SELECT file_name FROM TAPAGE WHERE page_id=a.page_id) pageId,             ");
        query.append("       CONVERT(varchar, p_ehmenu_id)                                     pMenuId,        ");
        query.append("       CONVERT(varchar, ehmenu_id)                                         menuId,         ");
        query.append("       (SELECT file_name FROM TAPAGE WHERE page_id=a.page_id) menuPageId,         ");
        query.append("       a.description description,                                                 ");
        query.append("       a.key_no keyNo,                                                            ");
        query.append("       a.param                                                                    ");
        query.append("FROM TAEHMENU a                                                                   ");
        query.append("WHERE page_id IS NOT NULL                                                         ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 접속 Log 기록
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @param user
     * @param path
     * @param sessionId 
     */
    public void saveAccessLog(User user, String path, String sessionId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAACCESSCCLOG (					");
        query.append("	accesscclog_id,				access_time,	");
        query.append("	comp_no,					user_id,		");
        query.append("	page_id										");
        query.append("	)											");
        query.append("VALUES (										");
        query.append("	NEXT VALUE FOR SQAACCESSCCLOG_ID,	GETDATE(),		");
        query.append("	?,							?,				");
        query.append("	(SELECT page_id 							");
        query.append("	   FROM TAPAGE								");
        query.append("	  WHERE file_name = ?)						");
        query.append(")												");
        
        Object [] object = new Object[]{
        		user.getCompNo(),
                user.getUserId(),
                CommonUtil.getPageIdFromPath(path)
        };
        
        getJdbcTemplate().update(query.toString(), object);
    }


    /**
     * Grid Height
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param user
     * @param currentPageId
     * @param listId
     * @return
     */
    public String getGrdHeight(User user, String currentPageId, String listId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("        height                                        ");
        query.append("FROM TAPGGRID x                                       ");
        query.append("WHERE 1 = 1                                           ");
        query.getAndQuery("(SELECT b.file_name FROM TAPAGE b WHERE x.page_id = b.page_id)", currentPageId);
        query.getAndQuery("x.grid_obj_id", listId);
        
        List resultList = getJdbcTemplate().queryForList(query.toString());

        return QuerySqlBuffer.listToString(resultList);

    }


    public List findAllPages()
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                    ");
        query.append("       sfile_id pageId,                   ");
        query.append("       sfile_name fileName,               ");
        query.append("       description,                       ");
        query.append("       REMARK,                            ");
        query.append("       'N' isChkauth                      ");
        query.append("FROM   TSFILE x                           ");
        query.append("WHERE x.isuse = 'Y'                       ");


        return getJdbcTemplate().queryForList(query.toString());
    }


    /**
     * Find Page Field Info
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @return
     */
    public List findPageFields()
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                        ");
        query.append("       page_id pageId,                        ");
        query.append("       (SELECT b.file_name FROM TAPAGE b WHERE b.page_id = x.page_id) fileName,      ");
        query.append("       field_id fieldId,                      ");
        query.append("       hidden_yn hiddenYn,                    ");
        query.append("       ord_no ordNo,                          ");
        query.append("       display_yn displayYn,                  ");
        query.append("       x.key_type+'.'+x.key_no langKey      ");
        query.append("FROM   TAPGFIELD x                            ");
        query.append("ORDER BY ISNULL(ord_no, '99999999') DESC                          ");

        return getJdbcTemplate().queryForList(query.toString());
    }


	public String findLanguage() 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT code FROM (                                        ");
		query.append("SELECT                                                	");
		query.append("       TOP 1			                                    ");
		query.append("       CDSYSD_NO code                                     ");
		query.append("FROM   TACDSYSD                                           ");
		query.append("WHERE  1=1                                                ");
		query.append("  AND  cdsysm_id IN (SELECT cdsysm_id                     ");
		query.append("                        FROM TACDSYSM                     ");
		query.append("                        WHERE list_type='LANG'            ");
		query.append("                    )                                     ");
		query.append("  AND  UPPER(is_use) = UPPER('Y')                         ");
		query.append("ORDER BY ISNULL(ord_no, '99999999')                       ");
		query.append(") langCode                                        		");

        
        List resultList = getJdbcTemplate().queryForList(query.toString());

        return QuerySqlBuffer.listToString(resultList);
	}
}