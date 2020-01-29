package dream.consult.comp.config.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.config.dao.ConsultCompConfigListDAO;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;

/**
 * 시스템환경변수 - 목록 dao
 * @author  syyang
 * @version $Id: ConsultCompConfigListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
 * @since   1.0
 * @spring.bean id="consultCompConfigListDAOTarget"
 * @spring.txbn id="consultCompConfigListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompConfigListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompConfigListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id: ConsultCompConfigListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigCommonDTO
     * @return List
     */
    public List findConfigList(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT																");
        query.append("       '' 										AS seqNo			");
        query.append("		 ,'' 										AS isDelCheck		");
        query.append("       ,x.comp_no 								AS compNo			");
        query.append("       ,(select aa.description from tacomp aa where x.comp_no = aa.comp_no) AS compDesc	");
        query.append("       ,x.compconfig_id 							AS compconfigId		");
        query.append("       ,x.name 									AS compconfigName	");
        query.append("       ,x.value$ 									AS compconfigValue	");
        query.append("       ,x.description 							AS compconfigDesc	");
        query.append("		 ,x.is_system 								AS isSystem			");
        query.append("FROM   TACOMPCONFIG x													");
        query.append("WHERE  1=1															");
        query.append(this.getWhere(consultCompConfigCommonDTO));
        query.getOrderByQuery("x.compconfig_id","x.name", consultCompConfigCommonDTO.getOrderBy(), consultCompConfigCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultCompConfigCommonDTO.getIsLoadMaxCount(), consultCompConfigCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  syyang
     * @version $Id: ConsultCompConfigListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultCompConfigCommonDTO consultCompConfigCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(consultCompConfigCommonDTO.getCompconfigId()) && !"".equals(consultCompConfigCommonDTO.getCompNo()))
        {
            query.getAndQuery("x.comp_no", consultCompConfigCommonDTO.getCompNo());
            query.getAndQuery("x.compconfig_id", consultCompConfigCommonDTO.getCompconfigId());
            return query.toString();
        }
        query.getLikeQuery("x.description", consultCompConfigCommonDTO.getFilterDesc());
        query.getAndQuery("x.comp_no", consultCompConfigCommonDTO.getFilterCompNo());
        
        return query.toString();
    }
    /**
     * delete
     * @author syyang
     * @version $Id: ConsultCompConfigListDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteConfig(String configId, String compNo, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TACOMPCONFIG					");
    	query.append("WHERE compconfig_id	= '"+configId+"'	");
    	query.append("  AND comp_no 		= '"+compNo+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }

	public String findTotalCount(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TACOMPCONFIG x        								");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(consultCompConfigCommonDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}