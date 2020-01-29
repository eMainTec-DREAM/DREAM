package dream.org.rpt.craft.work.time.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.org.rpt.craft.work.time.dao.OrgRptCraftWorkTimeDailyDAO;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeDailyDTO;

/**
 * 작업자 일별 작업시간
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="orgRptCraftWorkTimeDailyDAOTarget"
 * @spring.txbn id="orgRptCraftWorkTimeDailyDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OrgRptCraftWorkTimeDailyDAOOraImpl extends BaseJdbcDaoSupportOra implements OrgRptCraftWorkTimeDailyDAO
{

	@Override
	public List findList(OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("		SELECT  '' 		                                     SEQNO		");
        query.append("             ,NVL(a.emp_id, b.emp_id)                      EMPID		");
        query.append("             ,NVL(a.start_date, b.start_date)              STARTDATE	");
        query.append("             ,ROUND(NVL(a.work_time,0)/60,1)        		 WOWORKTIME	");
        query.append("             ,ROUND(NVL(b.work_time,0)/60,1)        		 PMWORKTIME	");
        query.append("             ,(ROUND(NVL(a.work_time,0)/60,1) + ROUND(NVL(b.work_time,0)/60,1))   TOTWORKTIME  ");
        query.append("             , (SELECT x.key_name                                     			");
        query.append("              FROM TALANG x  inner join TADAY y									");
        query.append("              on    x.key_type = y.dow_key_type and x.key_no = y.dow_key_no		");
        query.append("              WHERE lang = ?                                                		");
        query.append("               AND y.tday = NVL(a.start_date, b.start_date)  )     DAY      		");
        query.append("		FROM (																");
        query.append("           	SELECT x.comp_no											");
        query.append("            		, x.emp_id												");
        query.append("            		, x.start_date											");
        query.append("            		, sum(x.work_time) 	work_time							");
        query.append("        FROM TAWOCRAFT x inner join TAWORKORDER y							");
        query.append("        on x.comp_no = y.comp_no and x.wkor_id = y.wkor_id				");
        query.append("    			WHERE 1=1													");
        query.append("    			AND  x.comp_no = ?											");
        query.append("    			AND  x.emp_id = ?											");
        query.append("        		AND x.WORK_TIME is NOT NULL									");
        query.append("        		AND  y.wo_status in ('C', 'PRC')							");
        query.getAndDateQuery("SUBSTR(x.start_date,0,6)", orgRptCraftWorkTimeDailyDTO.getStartDate(), orgRptCraftWorkTimeDailyDTO.getEndDate());
        query.append("        		GROUP BY x.comp_no, x.emp_id, x.start_date					");
        query.append("       ) a full outer join (												");
        query.append("          	SELECT y.comp_no											");
        query.append("           		, y.emp_id												");
        query.append("          		, y.start_date											");
        query.append("           		, sum(y.work_time) 	work_time							");
        query.append("    			FROM TAPMINSLIST y											");
        query.append("    			WHERE 1=1													");
        query.append("    			AND  y.comp_no = ?											");
        query.append("    			and  y.emp_id = ?											");
        query.append("        		AND y.WORK_TIME is NOT NULL									");
        query.append("        		AND  y.PMSCHED_STATUS in ('C', 'PRC')						");
        query.getAndDateQuery("SUBSTR(y.start_date,0,6)", orgRptCraftWorkTimeDailyDTO.getStartDate(), orgRptCraftWorkTimeDailyDTO.getEndDate());
        query.append("        		GROUP BY y.comp_no, y.emp_id, y.start_date					");
        query.append("      ) b                                     							");
        query.append("    	ON a.comp_no = b.comp_no                                            ");
        query.append("    	AND a.emp_id = b.emp_id                                            	");
        query.append("    	AND a.START_DATE = b.START_DATE  									");
        
        query.getOrderByQuery("NVL(a.start_date, b.start_date) asc", orgRptCraftWorkTimeDailyDTO.getOrderBy(), orgRptCraftWorkTimeDailyDTO.getDirection());
        
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getCompNo()
        		,orgRptCraftWorkTimeDailyDTO.getEmpId()
        		,user.getCompNo()
        		,orgRptCraftWorkTimeDailyDTO.getEmpId()
        };


        return getJdbcTemplate().queryForList(query.toString(orgRptCraftWorkTimeDailyDTO.getIsLoadMaxCount(), orgRptCraftWorkTimeDailyDTO.getFirstRow()), objects);
    }

	@Override
	public String findTotalCount(OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO, User user) {
    	QueryBuffer query = new QueryBuffer();
    	
        query.append("    SELECT  count(*)    													");
        query.append("    FROM (    															");
        query.append("		SELECT  '' 		                                     	SEQNO		");
        query.append("             ,NVL(a.emp_id, b.emp_id)                      EMPID		");
        query.append("             ,NVL(a.start_date, b.start_date)              STARTDATE	");
        query.append("             ,NVL(a.work_time,0)                           WOWORKTIME	");
        query.append("             ,NVL(b.work_time,0)                           PMWORKTIME	");
        query.append("             ,NVL(a.work_time,0)+NVL(b.work_time,0)    	 TOTWORKTIME			");
        query.append("             , (SELECT x.key_name                                     			");
        query.append("              FROM TALANG x  inner join TADAY y									");
        query.append("              on    x.key_type = y.dow_key_type and x.key_no = y.dow_key_no		");
        query.append("              WHERE lang = ?                                                		");
        query.append("               AND y.tday = NVL(a.start_date, b.start_date)  )     DAY      		");
        query.append("		FROM (																");
        query.append("           	SELECT x.comp_no											");
        query.append("            		, x.emp_id												");
        query.append("            		, x.start_date											");
        query.append("            		, sum(x.work_time) 	work_time							");
        query.append("        FROM TAWOCRAFT x inner join TAWORKORDER y							");
        query.append("        on x.comp_no = y.comp_no and x.wkor_id = y.wkor_id				");
        query.append("    			WHERE 1=1													");
        query.append("    			AND  x.comp_no = ?											");
        query.append("    			AND  x.emp_id = ?											");
        query.append("        		AND x.WORK_TIME is NOT NULL									");
        query.append("        		AND  y.wo_status in ('C', 'PRC')							");
        query.getAndDateQuery("SUBSTR(x.start_date,0,6)", orgRptCraftWorkTimeDailyDTO.getStartDate(), orgRptCraftWorkTimeDailyDTO.getEndDate());
        query.append("        		GROUP BY x.comp_no, x.emp_id, x.start_date					");
        query.append("       ) a full outer join (												");
        query.append("          	SELECT y.comp_no											");
        query.append("           		, y.emp_id												");
        query.append("          		, y.start_date											");
        query.append("           		, sum(y.work_time) 	work_time							");
        query.append("    			FROM TAPMINSLIST y											");
        query.append("    			WHERE 1=1													");
        query.append("    			AND  y.comp_no = ?											");
        query.append("    			and  y.emp_id = ?											");
        query.append("        		AND y.WORK_TIME is NOT NULL									");
        query.append("        		AND  y.PMSCHED_STATUS in ('C', 'PRC')						");
        query.getAndDateQuery("SUBSTR(y.start_date,0,6)", orgRptCraftWorkTimeDailyDTO.getStartDate(), orgRptCraftWorkTimeDailyDTO.getEndDate());
        query.append("        		GROUP BY y.comp_no, y.emp_id, y.start_date					");
        query.append("      ) b                                     							");
        query.append("    	ON a.comp_no = b.comp_no                                            ");
        query.append("    	AND a.emp_id = b.emp_id                                            	");
        query.append("    	AND a.START_DATE = b.START_DATE  									");
        query.append("   ) aa																	");
        

        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getCompNo()
        		,orgRptCraftWorkTimeDailyDTO.getEmpId()
        		,user.getCompNo()
        		,orgRptCraftWorkTimeDailyDTO.getEmpId()
        };


        List resultList=  getJdbcTemplate().queryForList(query.toString(), objects);
        return QueryBuffer.listToString(resultList);
		
	}
    
}