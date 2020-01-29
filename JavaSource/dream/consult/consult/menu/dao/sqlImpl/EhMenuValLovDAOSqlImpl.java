package dream.consult.consult.menu.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.consult.menu.dao.EhMenuValLovDAO;
import dream.consult.consult.menu.dto.EhMenuValLovDTO;

/**
 * 컨설트메뉴 LOV - List DAO implements
 * @author kim21017
 * @version $Id: EhMenuValLovDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="ehMenuValLovDAOTarget"
 * @spring.txbn id="ehMenuValLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class EhMenuValLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements EhMenuValLovDAO
{
	public List findList(EhMenuValLovDTO ehMenuValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang =user.getLangId();
              

        query.append("SELECT												");
        query.append("		x.ehmenu_id AS ID							");
        query.append("		,x.ehmenu_id AS EHMENU_ID					");
        query.append("		,x.ehmenu_no AS EHMENU_NO					");
        query.append("		,x.ord_no AS ORD_NO							");
        query.append("		,x.p_ehmenu_id AS P_EHMENU_ID				");
        query.append("		,y.key_name AS KEY_NAME						");
        query.append("		,'' AS SEQNO								");
        query.append("        ,z.lvl AS LEVEL                                ");
        query.append("        ,MIN(z.lvl) OVER() AS MINLVL                                ");
        query.append("FROM TAEHMENU x, TALANG y, (SELECT * FROM dbo.SFAEHMENU_ALL(0)) z    ");
        query.append("WHERE x.key_no=y.key_no		");
        query.append("AND x.key_type=y.key_type     ");
        query.append("AND x.ehmenu_id=z.ehmenu_id    ");
        query.getAndQuery("y.lang", lang);
    	query.append(this.getWhere(ehMenuValLovDTO, user, columnMap, conditionMap));
        query.append("ORDER BY x.ord_no,	z.lvl								");

        return getJdbcTemplate().queryForList(query.toString());
    } 

	private String getWhere(EhMenuValLovDTO ehMenuValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("is_use", conditionMap);
        //메뉴 명
        query.getLikeQuery("y.key_name", ehMenuValLovDTO.getFilterEhMenuDesc());

    	return query.toString();
    }
}