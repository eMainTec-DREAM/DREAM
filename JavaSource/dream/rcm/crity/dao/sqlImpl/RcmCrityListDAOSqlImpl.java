package dream.rcm.crity.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.crity.dao.RcmCrityListDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;

/**
 * Criticality Matrix Page - List DAO implements
 * @author kim21017
 * @version $Id: RcmCrityListDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityListDAOTarget"
 * @spring.txbn id="rcmCrityListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmCrityListDAO
{
	public List findList(RcmCrityCommonDTO rcmCrityCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT																			");
        query.append("		''														AS seqNo			");
        query.append("		,''														AS isDelCheck		");
        query.append("		,x.critylist_id											AS crityListId		");
        query.append("		,x.critylist_no											AS crityListNo		");
        query.append("		,x.description											AS crityListDesc	");
        query.append("		,x.is_use												AS isUseId			");
        query.append("		,dbo.SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''								");
        query.append("							,'"+user.getLangId()+"') 			AS isUseDesc		");
        query.append("		,x.reg_date												AS regDate			");
        query.append("		,x.remark												AS remark			");
        query.append("FROM TACRITYLIST x																");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(rcmCrityCommonDTO, user));
        query.getOrderByQuery("x.critylist_id","x.critylist_no", rcmCrityCommonDTO.getOrderBy(), rcmCrityCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(rcmCrityCommonDTO.getIsLoadMaxCount(), rcmCrityCommonDTO.getFirstRow()));

    } 

	private String getWhere(RcmCrityCommonDTO rcmCrityCommonDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if(!"".equals(rcmCrityCommonDTO.getCrityListId())){
        	query.getAndNumKeyQuery("x.critylist_id", rcmCrityCommonDTO.getCrityListId());
        	return query.toString();
        }
        //Criticality 리스트 명
        query.getLikeQuery("x.description", rcmCrityCommonDTO.getFilterCrityListDesc());

    	return query.toString();
    }

    public int deleteCrityList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TACRITYLIST		");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  critylist_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteCrityColList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TACRITYCOL		");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  critylist_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteCrityRowList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TACRITYROW		");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  critylist_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteCrityValueList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TACRITYVALUE		");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  critylist_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = rcmCrityCommonDTO.getCompNo();
        
        query.append("SELECT											");
        query.append("  COUNT(1)										");
        query.append("FROM TACRITYLIST x																");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(rcmCrityCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
   
	}
    
}