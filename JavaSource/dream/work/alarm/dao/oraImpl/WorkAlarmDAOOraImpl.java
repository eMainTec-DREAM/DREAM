package dream.work.alarm.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.alarm.dao.WorkAlarmDAO;
import dream.work.alarm.dto.WorkAlarmDTO;

/**
 * Alarm List - 목록 dao 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workAlarmDAOTarget"
 * @spring.txbn id="workAlarmDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkAlarmDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkAlarmDAO
{
    public List findList(WorkAlarmDTO workAlarmDTO, User user) throws Exception {
    	QueryBuffer query = new QueryBuffer();
		
		query.append(getColums(workAlarmDTO, user));
        query.append(getTables(workAlarmDTO, user));
        query.append(this.getWhere(workAlarmDTO, user));
        query.append(getOrderBy(workAlarmDTO, user));
        
		return getJdbcTemplate().queryForList(query.toString(workAlarmDTO.getIsLoadMaxCount(), workAlarmDTO.getFirstRow()));
	}

	public String findTotalCount(WorkAlarmDTO workAlarmDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(workAlarmDTO, user));
        query.append(this.getWhere(workAlarmDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}

	public int[] updateDetail(final List<WorkAlarmDTO> list, final User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("UPDATE TAALARMLIST SET				");
    	query.append("	     remark				= ?			");
    	query.append(" WHERE comp_no			= ?			");
    	query.append("   AND alarmlist_id		= ?			");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				WorkAlarmDTO workAlarmDTO = list.get(i);
				
				Object[] objects = new Object[] {
						 workAlarmDTO.getRemark()
					   , user.getCompNo()
                       , workAlarmDTO.getAlarmListId()                                                                     
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
	
	public int[] deleteList(final List<WorkAlarmDTO> list, final User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("DELETE TAALARMLIST					");
    	query.append(" WHERE comp_no			= ?			");
    	query.append("   AND alarmlist_id		= ?			");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				WorkAlarmDTO workAlarmDTO = list.get(i);
				
				Object[] objects = new Object[] {
					     user.getCompNo()
                       , workAlarmDTO.getAlarmListId()                                                                     
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
	public String getColums(WorkAlarmDTO workAlarmDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                                        																					");
		query.append("			''                    																				AS ISDELCHECK   	");
		query.append("        , ''                    																				AS SEQNO    		");
		query.append("        , x.alarmlist_id         																				AS ALARMLISTID		");
		query.append("        , x.start_time            																			AS ALARMSTARTTIME	");
		query.append("        , x.end_time                																			AS ALARMENDTIME    	");
		query.append("        , SFACODE_TO_DESC(x.alarm_channel, 'ALARM_CHANNEL', 'SYS', x.comp_no, '"+ user.getLangId() +"') 		AS ALARMOCCH		");
		query.append("        , x.machine_no            																			AS ALARMMCCODE		");
		query.append("        , x.machine_name            																			AS ALARMMCDESC  	");
		query.append("        , (SELECT a.item_no FROM TAEQUIPMENT a WHERE a.comp_no  = x.comp_no AND a.equip_id = x.equip_id)		AS ITEMNO			");
		query.append("        , x.equip_id																							AS EQUIPID			");
		query.append("        , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no  = x.comp_no AND a.equip_id = x.equip_id)	AS EQUIPDESC		");
		query.append("        , (SELECT a.eqloc_id FROM TAEQUIPMENT a WHERE a.comp_no  = x.comp_no AND a.equip_id = x.equip_id)		AS EQLOCID			");
		query.append("		  ,	(SELECT a.full_desc 																									");
		query.append("  		   FROM TAEQLOC a 																										");
		query.append("		  	  WHERE a.comp_no  = x.comp_no																							");
		query.append("     			AND a.eqloc_id = (SELECT aa.eqloc_id 																				");
		query.append("                                  FROM TAEQUIPMENT aa 																			");
		query.append("                                 WHERE aa.comp_no  = x.comp_no 																	");
		query.append("                                   AND aa.equip_id = x.equip_id))     										AS EQLOCDESC		");
		query.append("        , x.alarm_point            																			AS ALARMPOINT   	");
		query.append("        , x.alarm_type            																			AS ALARMTYPE    	");
		query.append("        , x.alarm_Code            																			AS ALARMCODE    	");
		query.append("        , x.alarm_name            																			AS ALARMNAME    	");
		query.append("        , x.alarm_grade            																			AS ALARMGRADE   	");
		query.append("		  , (CASE WHEN 																												");
		query.append("		  		  		(SELECT COUNT(1) FROM TAALARMREQ a WHERE a.alarmlist_id = x.alarmlist_id) > 0 then 'Y'						");
		query.append("    			  ELSE 'N'																											");
		query.append("    			  END) 																							AS ISREPWKDESC		");
		query.append("        , x.alarm_desc            																			AS ALARMDESC    	");
		query.append("        , x.remark                																			AS REMARK    		");
		
		return query.toString();
	}

	@Override
	public String getTables(WorkAlarmDTO workAlarmDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("FROM TAALARMLIST x		");

		return query.toString();
	}

	@Override
	public String getOrderBy(WorkAlarmDTO workAlarmDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.getOrderByQuery("x.alarmlist_id DESC", workAlarmDTO.getOrderBy(), workAlarmDTO.getDirection());		
		
		return query.toString();
	}

	@Override
	public String getWhere(WorkAlarmDTO workAlarmDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("WHERE 1 = 1	");
		
		if(!"".equals(workAlarmDTO.getAlarmListId())){
            query.getAndQuery("x.alarmlist_id", workAlarmDTO.getAlarmListId());
            
            return query.toString();
        }
		
		// 회사
		query.getAndQuery("x.comp_no", user.getCompNo());
		
		// 발생일자
		query.getAndDateQuery("SUBSTR(x.start_time, 1, 8)", workAlarmDTO.getFilterFromDate(), workAlarmDTO.getFilterToDate());

		// 설비
		query.getCodeLikeQuery("x.equip_id", "(SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no and a.equip_id = x.equip_id)", workAlarmDTO.getFilterEquipId(), workAlarmDTO.getFilterEquipDesc());
		
        // Alarm 설비코드
        query.getLikeQuery("x.machine_no", workAlarmDTO.getFilterAlarmMcCode());
        
        // Alarm 설비명
        query.getLikeQuery("x.machine_name", workAlarmDTO.getFilterAlarmMcDesc());
        
        // Alarm 코드
        query.getLikeQuery("x.alarm_code", workAlarmDTO.getFilterAlarmCode());
        
        // Alarm 명
        query.getLikeQuery("x.alarm_name", workAlarmDTO.getFilterAlarmDesc());
        
        // Alarm 발생채널
        query.getSysCdQuery("x.alarm_channel", workAlarmDTO.getFilterAlarmOcChId(), workAlarmDTO.getFilterAlarmOcChDesc(), "ALARM_CHANNEL", user.getCompNo(), user.getLangId());
        
        // 수리요청발행여부
        query.getAndQuery("(CASE WHEN (SELECT COUNT(1) FROM TAALARMREQ a WHERE a.alarmlist_id = x.alarmlist_id) > 0 then 'Y' ELSE 'N' END)", workAlarmDTO.getFilterIsRepairWork());
        
        // Alarm 등급
        query.getLikeQuery("x.alarm_grade", workAlarmDTO.getFilterAlarmGrade());
        
        // Alarm 포인트
        query.getLikeQuery("x.alarm_point", workAlarmDTO.getFilterAlarmPoint());
        
        // Alarm 유형
        query.getLikeQuery("x.alarm_type", workAlarmDTO.getFilterAlarmType());
        
        return query.toString();
	}

	@Override
	public int[] insertDetail(final List<WorkAlarmDTO> list, final User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
        
		query.append("INSERT INTO TAALARMLIST (                       						");
        query.append("   	 comp_no        		, alarmlist_id			, equip_id		");
        query.append("     , machine_no				, machine_name			, start_time	");
        query.append("     , end_time				, alarm_point			, alarm_type	");
        query.append("     , alarm_code				, alarm_name 			, alarm_grade	");
        query.append("     , alarm_desc				, remark 				, alarm_channel	");
        query.append("     , cre_date				, cre_by 				, upd_date		");
        query.append("     , upd_by					, alarm_object_id 						");
        query.append(")VALUES(                                      						");
        query.append("  	 ?              		, ?              		, ?         	");
        query.append("     , ?              		, ?              		, ?         	");
        query.append("     , ?              		, ?                     , ?  			");
        query.append("     , ?              		, ?                     , ?  			");
        query.append("     , ?              		, ?                     , ?  			");
        query.append("     , ?              		, ?                     , ?      		");
        query.append("     , ?              		, ?						      			");
        query.append(")                                             						");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	WorkAlarmDTO workAlarmDTO = list.get(i);
                
                Object[] objects = new Object[] {
                		 user.getCompNo()
                	   , workAlarmDTO.getAlarmListId()
                  	   , workAlarmDTO.getEquipId()
                  	   , workAlarmDTO.getAlarmMcCode()
                  	   , workAlarmDTO.getAlarmMcDesc()
                  	   , workAlarmDTO.getAlarmStartTime()
                  	   , workAlarmDTO.getAlarmEndTime()
                  	   , workAlarmDTO.getAlarmPoint()
                  	   , workAlarmDTO.getAlarmType()
                  	   , workAlarmDTO.getAlarmCode()
                  	   , workAlarmDTO.getAlarmName()
                  	   , workAlarmDTO.getAlarmGrade()
                  	   , workAlarmDTO.getAlarmDesc()
                  	   , workAlarmDTO.getRemark()
                  	   , workAlarmDTO.getAlarmChannel()
                  	   , DateUtil.getDateTime()
                  	   , user.getUserId()
                  	   , DateUtil.getDateTime()
                  	   , user.getUserId()
                  	   , workAlarmDTO.getAlarmObjectId()
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
}