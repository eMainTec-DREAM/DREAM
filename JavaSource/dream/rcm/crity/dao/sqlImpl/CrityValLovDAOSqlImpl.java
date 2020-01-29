package dream.rcm.crity.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.crity.dao.CrityValLovDAO;
import dream.rcm.crity.dto.CrityValLovDTO;


/**
 * Criticality Matrix Page
 * @author hyosung
 * @version $Id: CrityValLovDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
 * @since 1.0
 * 
 * @spring.bean id="crityValLovDAOTarget"
 * @spring.txbn id="crityValLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CrityValLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements CrityValLovDAO
{
    
    /**
     * Crity °Ë»ö
     * @author  hyosung
     * @version $Id: CrityValLovDAOSqlImpl.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param crityValLovDTO
     * @param loginUser
     * @param conditionMap
     * @return
     */
    public List findList(CrityValLovDTO crityValLovDTO,User loginUser, Map<String, String> conditionMap) 
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT                                           ");
        query.append("       critylist_id                               ");
        query.append("       ,critylist_no                              ");
        query.append("       ,description                               ");
        query.append("FROM   TACRITYLIST                                ");
        query.append("WHERE  comp_no = '"+ loginUser.getCompNo()+"'     ");
        
       
        return getJdbcTemplate().queryForList(query.toString());
    } 

    

    
}