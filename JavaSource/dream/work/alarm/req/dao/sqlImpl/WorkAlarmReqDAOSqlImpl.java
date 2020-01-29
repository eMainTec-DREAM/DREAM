package dream.work.alarm.req.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.alarm.req.dao.WorkAlarmReqDAO;
import dream.work.alarm.req.dto.WorkAlarmReqDTO;

/**
 * 수리요청 접수 - 목록 dao 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workAlarmReqDAOTarget"
 * @spring.txbn id="workAlarmReqDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkAlarmReqDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkAlarmReqDAO
{
	public List findList(WorkAlarmReqDTO workAlarmReqDTO, User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append(getColums(workAlarmReqDTO, user));
        query.append(getTables(workAlarmReqDTO, user));
        query.append(this.getWhere(workAlarmReqDTO, user));
        query.append(getOrderBy(workAlarmReqDTO, user));
        
		return getJdbcTemplate().queryForList(query.toString(workAlarmReqDTO.getIsLoadMaxCount(), workAlarmReqDTO.getFirstRow()));
	}

	public String findTotalCount(WorkAlarmReqDTO workAlarmReqDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(workAlarmReqDTO, user));
        query.append(this.getWhere(workAlarmReqDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}

	@Override
	public int[] insertDetail(final List<WorkAlarmReqDTO> list, final User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAALARMREQ(                       						");
        query.append("   	 comp_no        , alarmreq_id      			, alarmlist_id		");
        query.append("     , woreq_id		, woreq_cretae_type			, cre_date        	");
        query.append("     , cre_by                                 						");
        query.append(")VALUES(                                      						");
        query.append("  	 ?              , ?              			, ?          		");
        query.append("     , ?              , ?              			, ?          		");
        query.append("     , ?                                         						");
        query.append(")                                             						");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	WorkAlarmReqDTO workAlarmReqDTO = list.get(i);
                
                Object[] objects = new Object[] {
                         user.getCompNo()
                       , workAlarmReqDTO.getAlarmReqId()
                       , workAlarmReqDTO.getAlarmListId()
                       , workAlarmReqDTO.getWoReqId()
                       , workAlarmReqDTO.getWoreqCreType()
                       , DateUtil.getDate()
                       , user.getUserId()
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
	
	public int[] deleteList(final List<WorkAlarmReqDTO> list, final User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("DELETE TAALARMREQ					");
    	query.append(" WHERE comp_no			= ?		");
    	query.append("   AND alarmreq_id		= ?		");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				WorkAlarmReqDTO workAlarmReqDTO = list.get(i);
				
				Object[] objects = new Object[] {
					     user.getCompNo()
                       , workAlarmReqDTO.getAlarmReqId()                                                                     
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
	public String getColums(WorkAlarmReqDTO workAlarmReqDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT																														");
		query.append("			''                 																					AS ISDELCHECK 	");
		query.append("	      , ''               																					AS SEQNO 		");
		query.append("	      , x.alarmreq_id               																		AS ALARMREQID	");
		query.append("	      , x.woreq_id               																			AS WOREQID		");
		query.append("        , y.woreq_no        																					AS WOREQNO		");
		query.append("        , y.req_date        																					AS WOREQDATE	");
		query.append("        , y.description 																						AS WOREQDESC	");
		query.append("        , (SELECT a.item_no FROM TAEQUIPMENT a WHERE a.comp_no  = x.comp_no AND a.equip_id = y.equip_id)      AS ITEMNO		");
		query.append("        , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no  = x.comp_no AND a.equip_id = y.equip_id)	AS EQUIPDESC	");
		query.append("        , dbo.SFACODE_TO_DESC(y.woreq_status, 'WOREQ_STATUS', 'SYS', 100, 'ko')     							AS WOREQSTATUS	");
		query.append("        , y.request           																				AS WOREQUEST	");

		return query.toString();
	}

	@Override
	public String getTables(WorkAlarmReqDTO workAlarmReqDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("  FROM TAALARMREQ x INNER JOIN TAWOREQ y 						");
		query.append("                    	 ON x.comp_no  = y.comp_no 				");
		query.append("                    	AND x.woreq_id = y.woreq_id				");
		query.append("                    INNER JOIN TAALARMLIST z 					");
		query.append("                    	 ON x.comp_no  		= z.comp_no			");
		query.append("                    	AND x.alarmlist_id 	= z.alarmlist_id	");
		
		return query.toString();
	}

	@Override
	public String getOrderBy(WorkAlarmReqDTO workAlarmReqDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getOrderByQuery("x.alarmreq_id", "x.alarmreq_id Desc", workAlarmReqDTO.getOrderBy(), workAlarmReqDTO.getDirection());		
		
		return query.toString();
	}

	@Override
	public String getWhere(WorkAlarmReqDTO workAlarmReqDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("WHERE 1 = 1	");
		
		query.getAndQuery("x.alarmList_id", workAlarmReqDTO.getAlarmListId());
		
		// 회사
		query.getAndQuery("x.comp_no", user.getCompNo());
		
		return query.toString();
	}
}