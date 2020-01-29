package dream.rcm.crity.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.crity.dao.RcmCrityRowListDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityRowListDTO;

/**
 * Criticality Matrix Row Page - List DAO implements
 * @author kim21017
 * @version $Id: RcmCrityRowListDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityRowListDAOTarget"
 * @spring.txbn id="rcmCrityRowListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityRowListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmCrityRowListDAO
{
	public List findList(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityRowListDTO rcmCrityRowListDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT													");
        query.append("		''								AS seqNo			");
        query.append("		,''								AS isDelCheck		");
        query.append("		,x.critylist_id					AS crityListId		");
        query.append("		,x.crityrow_id					AS crityRowId		");
        query.append("		,x.row_name						AS crityRowDesc		");
        query.append("		,x.ord_no						AS ordNo			");
        query.append("		,x.remark						AS remark			");
        query.append("		,x.crity_lvl					AS crityLevel		");
        query.append("FROM TACRITYROW x											");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.critylist_id", rcmCrityCommonDTO.getCrityListId());
    	
    	if (!"".equals(rcmCrityRowListDTO.getCrityRowId()))
        {
            query.getAndQuery("x.crityrow_id", rcmCrityRowListDTO.getCrityRowId());
        }
    	
        query.getOrderByQuery("x.critylist_id","x.ord_no", rcmCrityCommonDTO.getOrderBy(), rcmCrityCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(rcmCrityCommonDTO.getIsLoadMaxCount(), rcmCrityCommonDTO.getFirstRow()));

    } 
    public int deleteList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TACRITYROW		");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  crityrow_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteValList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TACRITYVALUE		");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  crityrow_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
	@Override
	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityRowListDTO rcmCrityRowListDTO,
			User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT													");
        query.append("	COUNT(1)												");
        query.append("FROM TACRITYROW x											");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.critylist_id", rcmCrityCommonDTO.getCrityListId());
    	
    	if (!"".equals(rcmCrityRowListDTO.getCrityRowId()))
        {
            query.getAndQuery("x.crityrow_id", rcmCrityRowListDTO.getCrityRowId());
        }
    	
	    List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    
	}
}