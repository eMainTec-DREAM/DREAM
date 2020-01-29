package dream.consult.program.lang.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.lang.dao.LovLangListDAO;
import dream.consult.program.lang.dto.LovLangListDTO;

/**
 * 다국어검색 팝업
 * @author  kim21017
 * @version $Id: LovLangListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovLangListDAOTarget"
 * @spring.txbn id="lovLangListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovLangListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovLangListDAO
{
    /**
     * 다국어 검색
     * @author  kim21017
     * @version $Id: LovLangListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovLangListDTO
     * @param loginUser
     * @return
     */
    public List findLangList(LovLangListDTO lovLangListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       key_type keyType,							");
        query.append("       key_no keyNo,								");
        query.append("       key_name keyName							");
        query.append("FROM TALANG										");
        query.append("WHERE 1=1											");
        query.getAndQuery("lang", loginUser.getLangId());
        query.getAndQuery("key_no", lovLangListDTO.getKeyNo());
        query.getLikeQuery("key_name", lovLangListDTO.getKeyName());
        query.getLikeQuery("key_type", lovLangListDTO.getCodeType());
      
        query.getOrderByQuery("key_no", lovLangListDTO.getOrderBy(), lovLangListDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(lovLangListDTO.getIsLoadMaxCount(), lovLangListDTO.getFirstRow()));
    }
    
    /**
     * 다국어 검색 AC
     * @author  kim21017
     * @version $Id: LovLangListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovLangListDTO
     * @param loginUser
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findLangAcList(LovLangListDTO lovLangListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT								");
    	query.append("		x.lang_id		AS lang_id		");
    	query.append("		,x.key_no		AS key_no		");
    	query.append("		,x.key_type		AS key_type		");
    	query.append("		,x.key_name		AS key_name		");
    	query.append("FROM TALANG x							");
    	query.append("WHERE 1=1								");
		query.getAndQuery("x.lang", user.getLangId());
		query.getAndQuery("x.key_type", conditionMap);
		query.getLikeQuery("x.key_no", lovLangListDTO.getKeyNo());
		query.getLikeQuery("x.key_name", lovLangListDTO.getKeyName());
    	query.getOrderByQuery("key_no", lovLangListDTO.getOrderBy(), lovLangListDTO.getDirection());
    	
    	return getJdbcTemplate().queryForList(query.toString(lovLangListDTO.getIsLoadMaxCount(), lovLangListDTO.getFirstRow()));
    }

	@Override
	public String findTotalCount(LovLangListDTO lovLangListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) {
		
        QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT								");
    	query.append("		COUNT(1)						");
    	query.append("FROM TALANG x							");
    	query.append("WHERE 1=1								");
		query.getAndQuery("x.lang", user.getLangId());
		query.getAndQuery("x.key_type", conditionMap);
		query.getLikeQuery("x.key_no", lovLangListDTO.getKeyNo());
		query.getLikeQuery("x.key_name", lovLangListDTO.getKeyName());
    	
        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}