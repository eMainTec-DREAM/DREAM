package dream.consult.program.linkedfunc.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.linkedfunc.dao.ConsultPgmLinkedFuncListDAO;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncCommonDTO;

/**
 * PgmLinkedFunc Page - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="consultPgmLinkedFuncListDAOTarget"
 * @spring.txbn id="consultPgmLinkedFuncListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmLinkedFuncListDAOOraImpl  extends BaseJdbcDaoSupportOra implements ConsultPgmLinkedFuncListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultPgmLinkedFuncCommonDTO
     * @return List
     */
    public List findPgmLinkedFuncList(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("    ''                        seqNo      			");
        query.append("  , ''                        isDelCheck 			");
        query.append("  , x.linkedfunc_id 			LINKEDFUNCID		");
        query.append("  , x.linkedfunc_no 			LINKEDFUNCNO		");
        query.append("  , x.description 			LINKEDFUNCDESC		");
        query.append("  , x.linkedfunc_method 		METHOD				");
        query.append("  , SFACODE_TO_DESC( x.is_use, 'IS_USE','SYS','', '"+user.getLangId()+"') 	ISUSEDESC		");
        query.append("FROM TALINKEDFUNC x								");
        query.append("WHERE  1=1                                        ");
        query.append(this.getWhere(consultPgmLinkedFuncCommonDTO, user));
        query.getOrderByQuery("x.linkedfunc_no", consultPgmLinkedFuncCommonDTO.getOrderBy(), consultPgmLinkedFuncCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultPgmLinkedFuncCommonDTO.getIsLoadMaxCount(), consultPgmLinkedFuncCommonDTO.getFirstRow()));

    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultPgmLinkedFuncCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();

        if(!"".equals(consultPgmLinkedFuncCommonDTO.getLinkedFuncId())){
            query.getAndQuery("x.linkedfunc_id", consultPgmLinkedFuncCommonDTO.getLinkedFuncId());
            return query.toString();
        }
        
        //Linked Function 명
        query.getLikeQuery("x.description", consultPgmLinkedFuncCommonDTO.getFilterLinkedFuncDesc());

        return query.toString();
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultPgmLinkedFuncCommonDTO
     * @return
     * @throws Exception
     */
    public int deletePgmLinkedFuncList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TALINKEDFUNC  ");
        query.append("WHERE linkedfunc_id = ?   ");
        
        Object[] objects = new Object[] {   
                id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public String findTotalCount(
            ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM TALINKEDFUNC x		");
        query.append("WHERE  1=1                ");
        query.append(this.getWhere(consultPgmLinkedFuncCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
