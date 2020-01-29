package dream.consult.program.config.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.config.dao.MaConfigListDAO;
import dream.consult.program.config.dto.MaConfigCommonDTO;

/**
 * 시스템환경변수 - 목록 dao
 * @author  kim21017
 * @version $Id: MaConfigListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maConfigListDAOTarget"
 * @spring.txbn id="maConfigListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaConfigListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaConfigListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaConfigListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigCommonDTO
     * @return List
     */
    public List findConfigList(MaConfigCommonDTO maConfigCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT													");
        query.append("       '' seqNo											");
        query.append("		 ,'' isDelCheck										");
        query.append("       ,x.config_id configId								");
        query.append("       ,x.name configName									");
        query.append("       ,x.value$ configValue								");
        query.append("       ,x.description configDesc							");
        query.append("		 ,x.is_system isSystem								");
        query.append("FROM   TACONFIG x											");
        query.append("WHERE  1=1												");
        query.append(this.getWhere(maConfigCommonDTO, user));
        query.getOrderByQuery("x.name", maConfigCommonDTO.getOrderBy(), maConfigCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maConfigCommonDTO.getIsLoadMaxCount(), maConfigCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaConfigListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaConfigCommonDTO maConfigCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        if (!"".equals(maConfigCommonDTO.getConfigId()) && !"".equals(user.getCompNo()))
        {
            query.getAndQuery("x.config_id", maConfigCommonDTO.getConfigId());
            return query.toString();
        }
        query.getLikeQuery("x.description", maConfigCommonDTO.getFilterDesc());
        
        return query.toString();
    }
    /**
     * delete
     * @author kim21017
     * @version $Id: MaConfigListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteConfig(String configId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TACONFIG					");
    	query.append("WHERE config_id 	= '"+configId+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }

	public String findTotalCount(MaConfigCommonDTO maConfigCommonDTO, User user) {
		QueryBuffer query = new QueryBuffer();

        query.append("SELECT COUNT(*)			");
        query.append("FROM 	TACONFIG x			");
        query.append("WHERE 1=1					");
        query.append(this.getWhere(maConfigCommonDTO , user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}