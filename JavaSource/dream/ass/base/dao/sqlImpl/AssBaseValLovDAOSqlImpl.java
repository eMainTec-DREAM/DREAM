package dream.ass.base.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.ass.base.dao.AssBaseValLovDAO;
import dream.ass.base.dto.AssBaseValLovDTO;

/**
 * 설비등급 평가기준 LOV - List DAO implements
 * @author kim21017
 * @version $Id: AssBaseValLovDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assBaseValLovDAOTarget"
 * @spring.txbn id="assBaseValLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssBaseValLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssBaseValLovDAO
{
	public List findList(AssBaseValLovDTO assBaseValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT																			");
        query.append("		x.assbaselist_id										AS assBaseListId	");
        query.append("		,x.assbaselist_no										AS assBaseListNo	");
        query.append("		,x.description											AS assBaseListDesc	");
        query.append("		,x.is_use												AS isUseId			");
        query.append("		,dbo.SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''									");
        query.append("							,'"+user.getLangId()+"') 			AS isUseDesc		");
        query.append("		,x.reg_date												AS regDate			");
        query.append("		,x.remark												AS remark			");
        query.append("FROM TAASSBASELIST x																");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(assBaseValLovDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.assbaselist_id", "x.assbaselist_no", assBaseValLovDTO.getOrderBy(), assBaseValLovDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assBaseValLovDTO.getIsLoadMaxCount(), assBaseValLovDTO.getFirstRow()));
    } 

	private String getWhere(AssBaseValLovDTO assBaseValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        //평가기준 명
        query.getLikeQuery("x.description", assBaseValLovDTO.getFilterAssBaseDesc());

    	return query.toString();
    }

    public String findTotalCount(AssBaseValLovDTO assBaseValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM TAASSBASELIST x			");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(assBaseValLovDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}