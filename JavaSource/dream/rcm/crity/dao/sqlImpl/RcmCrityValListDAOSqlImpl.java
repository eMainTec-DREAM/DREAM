package dream.rcm.crity.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.crity.dao.RcmCrityValListDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityValListDTO;

/**
 * Criticality Matrix Val Page - List DAO implements
 * @author kim21017
 * @version $Id: RcmCrityValListDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityValListDAOTarget"
 * @spring.txbn id="rcmCrityValListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityValListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmCrityValListDAO
{
	public List findList(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityValListDTO rcmCrityValListDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT													");
        query.append("		''								AS seqNo			");
        query.append("		,''								AS isDelCheck		");
        query.append("		,x.critylist_id					AS crityListId		");
        query.append("		,x.crityvalue_id				AS crityValId		");
        query.append("		,x.critycol_id					AS crityColId		");
        query.append("		,x.crityrow_id					AS crityRowId		");
        query.append("		,x.col_name						AS crityColDesc		");
        query.append("		,x.crity_lvl					AS crityLevel		");
        query.append("		,x.row_name						AS crityRowDesc		");
        query.append("		,x.crityvalue					AS crityValDesc		");
        query.append("		,x.critycolor					AS crityColorId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.critycolor,'CRITYCOLOR','USR','"+user.getCompNo()+"'	");
        query.append("			,'"+user.getLangId()+"') 	AS crityColorDesc	");
        query.append("		,x.is_critical					AS isCriticalId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.is_critical,'IS_USE','SYS',''	");
        query.append("			,'"+user.getLangId()+"') 	AS isCriticalDesc	");
        query.append("		,x.remark						AS remark			");
        query.append("FROM TACRITYVALUE x										");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.critylist_id", rcmCrityCommonDTO.getCrityListId());
    	
    	if (!"".equals(rcmCrityValListDTO.getCrityValId()))
        {
            query.getAndQuery("x.crityvalue_id", rcmCrityValListDTO.getCrityValId());
        }
    	
        query.getOrderByQuery("x.critycol_id","x.crityrow_id", rcmCrityCommonDTO.getOrderBy(), rcmCrityCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(rcmCrityCommonDTO.getIsLoadMaxCount(), rcmCrityCommonDTO.getFirstRow()));

    }

	@Override
	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityValListDTO rcmCrityValListDTO,
			User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("  COUNT(1)										");
        query.append("FROM TACRITYVALUE x										");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.critylist_id", rcmCrityCommonDTO.getCrityListId());
    	
    	if (!"".equals(rcmCrityValListDTO.getCrityValId()))
        {
            query.getAndQuery("x.crityvalue_id", rcmCrityValListDTO.getCrityValId());
        }
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
   
	} 
}