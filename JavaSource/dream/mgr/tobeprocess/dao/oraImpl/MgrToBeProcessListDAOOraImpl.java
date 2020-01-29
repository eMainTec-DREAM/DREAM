package dream.mgr.tobeprocess.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.tobeprocess.dao.MgrToBeProcessListDAO;
import dream.mgr.tobeprocess.dto.MgrToBeProcessCommonDTO;

/**
 * ToBeProcess Page - List DAO implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrToBeProcessListDAOTarget"
 * @spring.txbn id="mgrToBeProcessListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrToBeProcessListDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrToBeProcessListDAO
{
	public List findToBeProcessList(MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT 									");
        query.append("    ''     				seqNo			");
        query.append("  , x.tobeprocess_id 		processId		");
        query.append("  , x.prc_no 				processNo		");
        query.append("  , x.prc_name 			processName		");
        query.append("  , x.description 		processDesc		");
        query.append("  , x.file_name 			fileName		");
        query.append("  , SFACODE_TO_DESC( x.is_use, 'IS_USE','SYS',x.comp_no, '"+user.getLangId()+"')     isUseDesc		");
        query.append("  , x.remark 				remark         	");
        query.append("FROM TATOBEPROCESS x		            	");
        query.append("WHERE 1=1 				            	");
        query.append(this.getWhere(mgrToBeProcessCommonDTO, user));
        query.getOrderByQuery("x.prc_no", mgrToBeProcessCommonDTO.getOrderBy(), mgrToBeProcessCommonDTO.getDirection());
        
        
        return getJdbcTemplate().queryForList(query.toString(mgrToBeProcessCommonDTO.getIsLoadMaxCount(), mgrToBeProcessCommonDTO.getFirstRow()));
    } 

	private String getWhere(MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getLikeQuery("x.description", mgrToBeProcessCommonDTO.getFilterToBeProcessDesc());
        
    	return query.toString();
    }

	public String findTotalCount(MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    ");
        query.append("       COUNT(1)                           ");
        query.append("FROM TATOBEPROCESS x		            	");
    	query.append("WHERE 1=1									");    
    	query.append(this.getWhere(mgrToBeProcessCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}