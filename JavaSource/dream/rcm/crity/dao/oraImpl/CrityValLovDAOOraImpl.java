package dream.rcm.crity.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.rcm.crity.dao.CrityValLovDAO;
import dream.rcm.crity.dto.CrityValLovDTO;


/**
 * Criticality Matrix Page
 * @author hyosung
 * @version $Id: CrityValLovDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
 * @since 1.0
 * 
 * @spring.bean id="crityValLovDAOTarget"
 * @spring.txbn id="crityValLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CrityValLovDAOOraImpl extends BaseJdbcDaoSupportOra implements CrityValLovDAO
{
    
    
    /**
     * Crity °Ë»ö
     * @author  hyosung
     * @version $Id: CrityValLovDAOOraImpl.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param crityValLovDTO
     * @param loginUser
     * @param conditionMap
     * @return
     */
	public List findList(CrityValLovDTO crityValLovDTO,User loginUser, Map<String, String> conditionMap) 
    { 
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT                                           ");
        query.append("       critylist_id                               ");
        query.append("       ,critylist_no                              ");
        query.append("       ,description                               ");
        query.append("FROM   TACRITYLIST                                ");
        query.append("WHERE  comp_no = '"+ loginUser.getCompNo()+"'     ");
        query.getLikeQuery("description",crityValLovDTO.getCrityListDesc() );
       
        return getJdbcTemplate().queryForList(query.toString());
    } 

	

	
}