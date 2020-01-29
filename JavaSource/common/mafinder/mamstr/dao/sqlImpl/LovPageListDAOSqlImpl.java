package common.mafinder.mamstr.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dao.LovPageListDAO;
import common.mafinder.mamstr.dto.LovPageListDTO;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;

/**
 * ÆäÀÌÁö ÆË¾÷
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovPageListDAOTarget"
 * @spring.txbn id="lovPageListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPageListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovPageListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPageListDTO
     * @param loginUser
     * @return
     */
    public List findPageList(LovPageListDTO lovPageListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("       page_id pageId,							");
        query.append("       file_name fileName,						");
        query.append("       description pageDesc						");
        query.append("FROM   TAPAGE										");
        query.append("WHERE  1=1										");
        query.getLikeQuery("file_name", lovPageListDTO.getFileName());
        query.getLikeQuery("file_name+description", lovPageListDTO.getPageDesc());
        query.getOrderByQuery("page_id", "page_id", lovPageListDTO.getOrderBy(), lovPageListDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(lovPageListDTO.getIsLoadMaxCount(), lovPageListDTO.getFirstRow()));
    }
    
    @Override
    public List findDeptAcList(LovPageListDTO lovPageListDTO, User user,
            Map<String, String> columnMap, Map<String, String> conditionMap)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                     ");
        query.append("       page_id              ");
        query.append("       ,file_name            ");
        query.append("       ,description          ");
        query.append("FROM   TAPAGE           ");
        query.append("WHERE  1=1               ");
        query.getLikeQuery("file_name", lovPageListDTO.getFileName());
        query.getLikeQuery("file_name+description", lovPageListDTO.getPageDesc());
        if(conditionMap.containsKey("menu_id") && !"".equals(conditionMap.get("menu_id"))) {
            query.append("AND page_id IN (");
            query.append("SELECT page_id FROM dbo.SFAPGPAGEINMENU_ALL('"+conditionMap.get("menu_id")+"')");
            query.append(")");
        }
        query.getAndQuery("is_use", conditionMap);
        query.getOrderByQuery("page_id", "page_id", lovPageListDTO.getOrderBy(), lovPageListDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(lovPageListDTO.getIsLoadMaxCount(), lovPageListDTO.getFirstRow()));
    }

	@Override
	public String findTotalCount(LovPageListDTO lovPageListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SELECT										  	");
        query.append("       COUNT(1)									");
        query.append("FROM   TAPAGE										");
        query.append("WHERE  1=1               ");
        query.getLikeQuery("file_name", lovPageListDTO.getFileName());
        query.getLikeQuery("file_name+description", lovPageListDTO.getPageDesc());
        if(conditionMap.containsKey("menu_id") && !"".equals(conditionMap.get("menu_id"))) {
            query.append("AND page_id IN (");
            query.append("SELECT page_id FROM dbo.SFAPGPAGEINMENU_ALL('"+conditionMap.get("menu_id")+"')");
            query.append(")");
        }
        query.getAndQuery("is_use", conditionMap);
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}