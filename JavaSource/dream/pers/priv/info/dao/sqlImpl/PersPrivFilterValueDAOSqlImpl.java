package dream.pers.priv.info.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.pers.priv.info.dao.PersPrivFilterValueDAO;
import dream.pers.priv.info.dto.PersPrivFilterValueDTO;

/**
 *  ¸ñ·Ï dao
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 * @spring.bean id="persPrivFilterValueDAOTarget"
 * @spring.txbn id="persPrivFilterValueDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersPrivFilterValueDAOSqlImpl extends BaseJdbcDaoSupportSql implements PersPrivFilterValueDAO
{
 
	@Override
	public List findList(PersPrivFilterValueDTO persPrivFilterValueDTO, User loginUser) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append(getColums(persPrivFilterValueDTO, loginUser));
        query.append(getTables(persPrivFilterValueDTO, loginUser));
        query.append(this.getWhere(persPrivFilterValueDTO, loginUser));
        query.append(getOrderBy(persPrivFilterValueDTO, loginUser));
        
		return getJdbcTemplate().queryForList(query.toString(persPrivFilterValueDTO.getIsLoadMaxCount(), persPrivFilterValueDTO.getFirstRow()));
	
	}

	@Override
	public String findTotalCount(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(persPrivFilterValueDTO, user));
        query.append(this.getWhere(persPrivFilterValueDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}


	@Override
	public int[] deleteList(final List<PersPrivFilterValueDTO> list, final User user) {

		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("DELETE TAUSRFILTERVALUE					");
    	query.append(" WHERE usrfiltervalue_id		= ?			");
    	query.append("  AND comp_no					= ?		");
    	query.append("  AND user_id					= ?		");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				PersPrivFilterValueDTO persPrivFilterValueDTO = list.get(i);
				
				Object[] objects = new Object[] {
						persPrivFilterValueDTO.getUsrFilterValueId()    
						,user.getCompNo()
						,user.getUserId()
                };
				
				for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
			}
    		
			@Override
			public int getBatchSize() {
				return list.size();
			}
    	});
	}


	@Override
	public int[] insertDetail(final List<PersPrivFilterValueDTO> list, final User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAUSRFILTERVALUE(                    					");
		query.append("		usrfiltervalue_id	, user_id      		, page_id			");
		query.append("    , cre_date	 		, cre_time        	, file_name			");
		query.append("    , user_no				, title				, is_default        ");
		query.append("    , setvalue		    , comp_no								");
		query.append(")VALUES(                                      					");
		query.append("  	 ?              , ?              		, ?          		");
		query.append("     , ?              , ?              		, ?          		");
		query.append("     , ?				, ?                     , ?					");
		query.append("     , ?				, ?										)	");

		return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException
			{
				PersPrivFilterValueDTO persPrivFilterValueDTO = list.get(i);
				
				Object[] objects = new Object[] {
						persPrivFilterValueDTO.getUsrFilterValueId()
						, user.getUserId()
						, persPrivFilterValueDTO.getPageId()
						, persPrivFilterValueDTO.getCreDate()
						, DateUtil.getTimeStamp(user.getOffset(), "HHmmss")
						, persPrivFilterValueDTO.getFileName()
						, user.getUserNo()
						, persPrivFilterValueDTO.getTitle()
						, persPrivFilterValueDTO.getIsDefault()
						, persPrivFilterValueDTO.getSetValue()
						, user.getCompNo()
				};
				
				for(int j=0;j<objects.length;j++){
					ps.setObject(j+1, objects[j]);
				}
			}
			
			@Override
			public int getBatchSize()
			{
				return list.size();
			}
		});
	}

	@Override
	public String findPageId(PersPrivFilterValueDTO persPrivFilterValueDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
	    query.append("SELECT 	page_id                    							");
	    query.append(" FROM TAPAGE	                      							");
	    query.append(" WHERE file_name = '"+persPrivFilterValueDTO.getFileName()+"'	");

	    List resultList = getJdbcTemplate().queryForList(query.toString());

	    return QuerySqlBuffer.listToString(resultList);
	}

	@Override
	public int updatedefault(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		int rtnValue  = 0;

    	query.append("UPDATE TAUSRFILTERVALUE SET				");
    	query.append("  is_default	 			= ?				");
    	query.append("WHERE comp_no   			= ?				");
    	query.append(" AND user_id   			= ?				");
    	query.append(" AND page_id   			= ?				");
    	
    	Object[] objects = new Object[] {
    			"N"
    			, user.getCompNo()
    			, user.getUserId()
    			, persPrivFilterValueDTO.getPageId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
	}

	@Override
	public String getColums(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT																															");
		query.append("		''                 																						AS ISDELCHECK 		");
		query.append("	  , ''               																						AS SEQNO 			");
		query.append("    , x.usrfiltervalue_id																						AS usrFilterValueId	");
		query.append("    , x.title																									AS title			");
		query.append("    , 																															");
		query.getDate("x.cre_date", "creDate");
		query.append("    , (SELECT dbo.SFACODE_TO_DESC(x.is_default, 'IS_USE', 'SYS', '', '"+user.getLangId()+"')) 				AS isDefault		");
		query.append("    , x.setvalue																								AS setValue			");

		return query.toString();
	}

	@Override
	public String getTables(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("  FROM TAUSRFILTERVALUE x		");
		
		return query.toString();
	}

	@Override
	public String getOrderBy(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getOrderByQuery("x.usrfiltervalue_id","x.usrfiltervalue_id", persPrivFilterValueDTO.getOrderBy(), persPrivFilterValueDTO.getDirection());		
		
		return query.toString();
	}

	@Override
	public String getWhere(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("WHERE 1 = 1	");
		query.getAndQuery("x.comp_no", user.getCompNo());
		query.getAndQuery("x.user_id", user.getUserId());
		query.getAndQuery("x.page_id", persPrivFilterValueDTO.getPageId());
		
        if(!"".equals(persPrivFilterValueDTO.getUsrFilterValueId())){
        	query.getAndQuery("x.usrfiltervalue_id", persPrivFilterValueDTO.getUsrFilterValueId());
        	return query.toString();
        }
        query.getLikeQuery("x.title", persPrivFilterValueDTO.getFilterTitle());
        query.getAndDateQuery("x.cre_date", persPrivFilterValueDTO.getFilterCreDate(), persPrivFilterValueDTO.getFilterCreEndDate());
        
		return query.toString();
	}

}