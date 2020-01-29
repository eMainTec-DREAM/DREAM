package common.config.dao.oraImpl;

import java.util.List;

import common.config.dao.ResourceDAO;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

/**
 * @author pksup
 * @version $Id: ResourceDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
 * @since 1.0
 * @spring.bean id="resourceDAOTarget"
 * @spring.txbn id="resourceDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ResourceDAOOraImpl extends BaseJdbcDaoSupportOra implements ResourceDAO
{
    
    /**
     * Resource 조회
     * @author  javaworker
     * @version $Id: ResourceDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @param localeKey
     * @return
     */
    public List findResources(String localeKey)
    {
        QueryBuffer buffer = new QueryBuffer();
        
        buffer.append("SELECT                                                         ");
        buffer.append("       a.keyType||'.'||a.keyNo MESSAGE_KEY,                    ");
        buffer.append("       a.keyName             MESSAGE                           ");
        buffer.append("FROM   (                                                       ");
        buffer.append("     SELECT key_type keyType,                                  ");
        buffer.append("        key_no keyNo,                                          ");
        buffer.append("        lang lang,                                             ");
        buffer.append("        key_name keyName                                       ");
        buffer.append("        FROM TALANG                                            ");
//        buffer.append("     WHERE NVL(is_comm_js_use, 'N')= 'N'						  ");
        buffer.append("        ) a                                                    ");
        buffer.append("WHERE  a.lang = '" + localeKey + "'                            ");
        return this.getJdbcTemplate().queryForList(buffer.toString());
    }

    /**
     * 해당 LocalKey와 key로 Resouce 를 조회한다.
     * @author  javaworker
     * @version $Id: ResourceDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @param localeKey
     * @param key
     * @return
     */
    public String getMessage(String localeKey, String key)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                         ");
        query.append("       a.keyName                                               ");
        query.append("FROM   (                                                       ");
        query.append("   SELECT key_type keyType,                                    ");
        query.append("        key_no keyNo,                                          ");
        query.append("        lang,                                                  ");
        query.append("        key_name keyName                                       ");
        query.append("        FROM TALANG                                            ");
        query.append("        ) a                                                    ");
        query.append("WHERE  1=1                                                     ");
        query.append("  AND  a.lang||'.'||a.keyType||'.'||a.keyNo = '" + key + "'    ");
        
        List resultList = this.getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    public String getGaiaMessage(String localeKey, String key)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                         ");
        query.append("       a.keyName                                               ");
        query.append("FROM   (                                                       ");
        query.append("   SELECT key_type keyType,                                    ");
        query.append("        key_no keyNo,                                          ");
        query.append("        lang,                                                  ");
        query.append("        key_name keyName                                       ");
        query.append("        FROM TSLANG                                            ");
        query.append("        ) a                                                    ");
        query.append("WHERE  1=1                                                     ");
        query.append("  AND  a.lang||'.'||'GAIA.'||a.keyType||'.'||a.keyNo = '" + key + "'    ");
        
        List resultList = this.getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

	public List findJsResources(String localeKey) 
	{
		QueryBuffer buffer = new QueryBuffer();
        
        buffer.append("SELECT                                                         ");
        buffer.append("       a.keyType||'.'||a.keyNo MESSAGE_KEY,                    ");
        buffer.append("       a.keyName             MESSAGE                           ");
        buffer.append("FROM   (                                                       ");
        buffer.append("     SELECT 'COMMON' keyType,                                  ");
        buffer.append("            key_no keyNo,                                      ");
        buffer.append("            lang lang,                                         ");
        buffer.append("            key_name keyName                                   ");
        buffer.append("     FROM   TALANG                                             ");
        buffer.append("     WHERE  is_comm_js_use = 'Y'      						  ");
        buffer.append("        ) a                                                    ");
        buffer.append("WHERE  a.lang = '" + localeKey + "'                            ");
        return this.getJdbcTemplate().queryForList(buffer.toString());
	}

    public List findGaiaResources(String localeKey)
    {
        QueryBuffer buffer = new QueryBuffer();
        
        buffer.append("SELECT                                                         ");
        buffer.append("       'GAIA.'||a.keyType||'.'||a.keyNo MESSAGE_KEY,           ");
        buffer.append("       a.keyName             MESSAGE                           ");
        buffer.append("FROM   (                                                       ");
        buffer.append("     SELECT key_type keyType,                                  ");
        buffer.append("            key_no keyNo,                                      ");
        buffer.append("            lang lang,                                         ");
        buffer.append("            key_name keyName                                   ");
        buffer.append("     FROM   TSLANG                                             ");
        buffer.append("        ) a                                                    ");
        buffer.append("WHERE  a.lang = '" + localeKey + "'                            ");
        return this.getJdbcTemplate().queryForList(buffer.toString());
    }
}