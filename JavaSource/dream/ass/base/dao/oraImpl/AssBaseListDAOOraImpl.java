package dream.ass.base.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.ass.base.dao.AssBaseListDAO;
import dream.ass.base.dto.AssBaseCommonDTO;

/**
 * 설비등급 평가기준 - List DAO implements
 * @author kim21017
 * @version $Id: AssBaseListDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assBaseListDAOTarget"
 * @spring.txbn id="assBaseListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssBaseListDAOOraImpl extends BaseJdbcDaoSupportOra implements AssBaseListDAO
{
	public List findList(AssBaseCommonDTO assBaseCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT																			");
        query.append("		''														AS seqNo			");
        query.append("		,''														AS isDelCheck		");
        query.append("		,x.assbaselist_id										AS assBaseListId	");
        query.append("		,x.assbaselist_no										AS assBaseListNo	");
        query.append("		,x.description											AS assBaseListDesc	");
        query.append("		,(SELECT full_desc															");
        query.append("		   FROM TAEQCTG																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		    AND eqctg_id = x.eqctg_id)							AS eqCtgDesc		");
        query.append("		,x.is_use												AS isUseId			");
        query.append("		,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''									");
        query.append("							,'"+user.getLangId()+"') 			AS isUseDesc		");
        query.append("		,x.reg_date												AS regDate			");
        query.append("		,x.remark												AS remark			");
        query.append("FROM TAASSBASELIST x																");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(assBaseCommonDTO, user));
        query.getOrderByQuery("x.assbaselist_no", assBaseCommonDTO.getOrderBy(), assBaseCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assBaseCommonDTO.getIsLoadMaxCount(), assBaseCommonDTO.getFirstRow()));
    } 

	private String getWhere(AssBaseCommonDTO assBaseCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if(!"".equals(assBaseCommonDTO.getAssBaseListId())){
        	query.getAndNumKeyQuery("x.assbaselist_id", assBaseCommonDTO.getAssBaseListId());
        	return query.toString();
        }
        //평가기준 명
        query.getLikeQuery("x.description", assBaseCommonDTO.getFilterAssBaseListDesc());

    	return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAASSBASELIST			");
        query.append("WHERE  comp_no 			= ?		");
        query.append("  AND  assbaselist_id		= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        	};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteGradeList(String id, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAASSBASEGRADE		");
        query.append("WHERE  comp_no 			= ?		");
        query.append("  AND  assbaselist_id		= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        	};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deletePointList(String id, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAASSBASEPOINT		");
        query.append("WHERE  comp_no 			= ?		");
        query.append("  AND  assbaselist_id		= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        	};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteValList(String id, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAASSBASEPVAL			");
        query.append("WHERE  comp_no 			= ?		");
        query.append("  AND  assbaselist_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM TAASSBASELIST x			");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(assBaseCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}