package dream.consult.consult.menu.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.consult.menu.dao.EhMenuValLovDAO;
import dream.consult.consult.menu.dto.EhMenuValLovDTO;

/**
 * 컨설트 메뉴 LOV - List DAO implements
 * @author kim21017
 * @version $Id: EhMenuValLovDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="ehMenuValLovDAOTarget"
 * @spring.txbn id="ehMenuValLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class EhMenuValLovDAOOraImpl extends BaseJdbcDaoSupportOra implements EhMenuValLovDAO
{
	public List findList(EhMenuValLovDTO ehMenuValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
        String lang =user.getLangId();
              
        query.append("SELECT												");
        query.append("		b.*												");
        query.append(" 		,MIN(level) OVER() AS MINLVL									");
        query.append(" 		,level											");
        query.append("FROM													");
        query.append("(														");
        query.append("	SELECT												");
        query.append("			x.ehmenu_id AS id							");
        query.append("			,x.ehmenu_id AS ehmenu_id					");
        query.append("			,x.ehmenu_no AS ehmenu_no					");
        query.append("			,x.ord_no AS ord_no							");
        query.append("			,x.p_ehmenu_id AS p_ehmenu_id				");
        query.append("			,y.key_name AS key_name						");
        query.append("			,'' AS seqNo								");
        query.append("	FROM TAEHMENU x, TALANG y							");
        query.append("	WHERE x.key_no||x.key_type=y.key_no||y.key_type		");
        query.getAndQuery("y.lang", lang);
    	query.append(this.getWhere(ehMenuValLovDTO, user, columnMap, conditionMap));
        query.append("	ORDER BY x.ord_no									");
        query.append(" 	)b													");
        query.append("WHERE 1=1												");
        query.append("START WITH b.p_ehmenu_id = 0							");
        query.append("CONNECT BY PRIOR b.ehmenu_id = b.p_ehmenu_id			");
        query.append("ORDER BY level asc, ord_no asc						");

        return getJdbcTemplate().queryForList(query.toString());
    } 

	private String getWhere(EhMenuValLovDTO ehMenuValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("is_use", conditionMap);
        //메뉴 명
        query.getLikeQuery("y.key_name", ehMenuValLovDTO.getFilterEhMenuDesc());

    	return query.toString();
    }
}