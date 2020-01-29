package dream.ass.base.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.ass.base.dao.AssBasePointListDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;

/**
 * 평가항목 - List DAO implements
 * @author kim21017
 * @version $Id: AssBasePointListDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assBasePointListDAOTarget"
 * @spring.txbn id="assBasePointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssBasePointListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssBasePointListDAO
{
	public List findList(AssBaseCommonDTO assBaseCommonDTO, AssBasePointListDTO assBasePointListDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT													");
        query.append("		''								AS seqNo			");
        query.append("		,''								AS isDelCheck		");
        query.append("		,x.assbasepoint_id				AS assBasePointId	");
        query.append("		,x.assbaselist_id				AS assBaseListId	");
        query.append("		,x.ass_point_type				AS assPointTypeId	");
        query.append("		,dbo.SFACODE_TO_DESC(x.ass_point_type,'ASS_POINT_TYPE','SYS',''	");
        query.append("			,'"+user.getLangId()+"') 	AS assPointTypeDesc	");
        query.append("		,x.ass_point					AS assPoint			");
        query.append("		,x.ord_no						AS ordNo			");
        query.append("		,x.is_use						AS isUseId			");
        query.append("		,dbo.SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''			");
        query.append("			,'"+user.getLangId()+"') 	AS isUseDesc		");
        query.append("		,x.remark						AS remark			");
        query.append("FROM TAASSBASEPOINT x										");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.assbaselist_id", assBaseCommonDTO.getAssBaseListId());
        
    	if (!"".equals(assBasePointListDTO.getAssBasePointId()))
        {
            query.getAndQuery("x.assbasepoint_id", assBasePointListDTO.getAssBasePointId());
        }
        query.getOrderByQuery("x.assbasepoint_id", "x.ord_no", assBaseCommonDTO.getOrderBy(), assBaseCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assBaseCommonDTO.getIsLoadMaxCount(), assBaseCommonDTO.getFirstRow()));
    } 
    public int deleteList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAASSBASEPOINT			");
        query.append("WHERE  comp_no 				= ?		");
        query.append("  AND  assbasepoint_id  		= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteValList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAASSBASEPVAL				");
        query.append("WHERE  comp_no 				= ?		");
        query.append("  AND  assbasepoint_id  		= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
	public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO, AssBasePointListDTO assBasePointListDTO, User user)
			throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT													");
        query.append("		COUNT(1)											");
        query.append("FROM TAASSBASEPOINT x										");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.assbaselist_id", assBaseCommonDTO.getAssBaseListId());
    	
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}