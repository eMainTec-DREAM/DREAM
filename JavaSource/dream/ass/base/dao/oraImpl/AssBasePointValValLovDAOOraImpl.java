package dream.ass.base.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.ass.base.dao.AssBasePointValValLovDAO;
import dream.ass.base.dto.AssBasePointValValLovDTO;

/**
 * 평가항목 평가기준 LOV - List DAO implements
 * @author kim21017
 * @version $Id: AssBasePointValValLovDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assBasePointValValLovDAOTarget"
 * @spring.txbn id="assBasePointValValLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssBasePointValValLovDAOOraImpl extends BaseJdbcDaoSupportOra implements AssBasePointValValLovDAO
{
	public List findList(AssBasePointValValLovDTO assBasePointValValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT																			");
        query.append("		x.assbaselist_id										AS assBaseListId	");
        query.append("		,x.assbasepoint_id 										AS assBasePointId	");
        query.append("		,x.assbasepval_id										AS assBasePvalId	");
        query.append("		,x.eval_value											AS evalValue		");
        query.append("		,x.ass_eval												AS assEval			");
        query.append("		,x.is_use												AS isUseId			");
        query.append("		,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''									");
        query.append("							,'"+user.getLangId()+"') 			AS isUseDesc		");
        query.append("FROM TAASSBASEPVAL x																");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(assBasePointValValLovDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.ord_no", assBasePointValValLovDTO.getOrderBy(), assBasePointValValLovDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assBasePointValValLovDTO.getIsLoadMaxCount(), assBasePointValValLovDTO.getFirstRow()));
    } 

	private String getWhere(AssBasePointValValLovDTO assBasePointValValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        query.getAndQuery("assbaselist_id", conditionMap);
        query.getAndQuery("assbasepoint_id", conditionMap);
        //평가기준 명
        query.getLikeQuery("x.ass_eval", assBasePointValValLovDTO.getFilterAssEval());

    	return query.toString();
    }

    public String findTotalCount(AssBasePointValValLovDTO assBasePointValValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM TAASSBASEPVAL x			");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(assBasePointValValLovDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}