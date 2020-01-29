package dream.ass.base.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.ass.base.dao.AssBaseGradeListDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseGradeListDTO;

/**
 * 등급기준 - List DAO implements
 * @author kim21017
 * @version $Id: AssBaseGradeListDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assBaseGradeListDAOTarget"
 * @spring.txbn id="assBaseGradeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssBaseGradeListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssBaseGradeListDAO
{
	public List findList(AssBaseCommonDTO assBaseCommonDTO, AssBaseGradeListDTO assBaseGradeListDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT													");
        query.append("		''								AS seqNo			");
        query.append("		,''								AS isDelCheck		");
        query.append("		,x.assbasegrade_id				AS assBaseGradeId	");
        query.append("		,x.assbaselist_id				AS assBaseListId	");
        query.append("		,x.eq_grade						AS eqGradeId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.eq_grade,'EQ_GRADE','SYS',''	");
        query.append("			,'"+user.getLangId()+"') 	AS eqGradeDesc		");
        query.append("		,x.grade_from					AS gradeFrom		");
        query.append("		,x.grade_to						AS gradeTo			");
        query.append("		,x.ord_no						AS ordNo			");
        query.append("		,x.remark						AS remark			");
        query.append("FROM TAASSBASEGRADE x										");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.assbaselist_id", assBaseCommonDTO.getAssBaseListId());
        
    	if (!"".equals(assBaseGradeListDTO.getAssBaseGradeId()))
        {
            query.getAndQuery("x.assbasegrade_id", assBaseGradeListDTO.getAssBaseGradeId());
        }
        query.getOrderByQuery("x.assbasegrade_id", "x.ord_no", assBaseCommonDTO.getOrderBy(), assBaseCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assBaseCommonDTO.getIsLoadMaxCount(), assBaseCommonDTO.getFirstRow()));
    } 
    public int deleteList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAASSBASEGRADE			");
        query.append("WHERE  comp_no 				= ?		");
        query.append("  AND  assbasegrade_id  		= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
	public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO, AssBaseGradeListDTO assBaseGradeListDTO, User user)
			throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT													");
        query.append("		COUNT(1)											");
        query.append("FROM TAASSBASEGRADE x										");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.assbaselist_id", assBaseCommonDTO.getAssBaseListId());
    	
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}