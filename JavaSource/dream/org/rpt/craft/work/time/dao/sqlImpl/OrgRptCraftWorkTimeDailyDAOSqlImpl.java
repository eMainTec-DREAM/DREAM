package dream.org.rpt.craft.work.time.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
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
public class OrgRptCraftWorkTimeDailyDAOSqlImpl extends BaseJdbcDaoSupportSql implements OrgRptCraftWorkTimeDailyDAO
{

	@Override
	public List findList(OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("		SELECT  '' 		                                     	SEQNO		");
        query.append("             ,ISNULL(a.emp_id, b.emp_id)                      EMPID		");
        query.append("             ,ISNULL(a.start_date, b.start_date)              STARTDATE	");
        query.append("             ,convert(numeric(10,1),ISNULL(a.work_time,0)/60)  WOWORKTIME		");
        query.append("             ,convert(numeric(10,1),ISNULL(b.work_time,0)/60)  PMWORKTIME		");
        query.append("             ,(convert(numeric(10,1),ISNULL(a.work_time,0)/60)+convert(numeric(10,1),ISNULL(b.work_time,0)/60))   TOTWORKTIME		");
        query.append("             , (SELECT x.key_name                                     			");
        query.append("              FROM TALANG x  inner join TADAY y									");
        query.append("              on    x.key_type = y.dow_key_type and x.key_no = y.dow_key_no		");
        query.append("              WHERE lang = ?                                                		");
        query.append("               AND y.tday = ISNULL(a.start_date, b.start_date)  )     DAY      	");
        query.append("		FROM (																");
        query.append("           	SELECT x.comp_no											");
        query.append("            		, x.emp_id												");
        query.append("            		, x.start_date											");
        query.append("            		, sum(x.work_time) 	work_time							");
        query.append("        FROM TAWOCRAFT x inner join TAWORKORDER y									");
        query.append("        on x.comp_no = y.comp_no and x.wkor_id = y.wkor_id						");
        query.append("    			WHERE 1=1													");
        query.append("    			AND  x.comp_no = ?											");
        query.append("    			AND  x.emp_id = ?											");
        query.append("        		AND x.WORK_TIME is NOT NULL									");
        query.append("        		AND  y.wo_status in ('C', 'PRC')									");
        query.getAndDateQuery("LEFT(x.start_date,6)", orgRptCraftWorkTimeDailyDTO.getStartDate(), orgRptCraftWorkTimeDailyDTO.getEndDate());
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
        query.append("        		AND  y.PMSCHED_STATUS in ('C', 'PRC')								");
        query.getAndDateQuery("LEFT(y.start_date,6)", orgRptCraftWorkTimeDailyDTO.getStartDate(), orgRptCraftWorkTimeDailyDTO.getEndDate());
        query.append("        		GROUP BY y.comp_no, y.emp_id, y.start_date					");
        query.append("      ) b                                     							");
        query.append("    	ON a.comp_no = b.comp_no                                            ");
        query.append("    	AND a.emp_id = b.emp_id                                            	");
        query.append("    	AND a.START_DATE = b.START_DATE  									");

        query.getOrderByQuery("a.emp_id", "ISNULL(a.start_date, b.start_date) asc", orgRptCraftWorkTimeDailyDTO.getOrderBy(), orgRptCraftWorkTimeDailyDTO.getDirection());
        
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("    SELECT  count(*)    													");
        query.append("    FROM (    															");
        query.append("		SELECT  '' 		                                     	SEQNO		");
        query.append("             ,ISNULL(a.emp_id, b.emp_id)                      EMPID		");
        query.append("             ,ISNULL(a.start_date, b.start_date)              STARTDATE	");
        query.append("             ,ISNULL(a.work_time,0)                           WOWORKTIME	");
        query.append("             ,ISNULL(b.work_time,0)                           PMWORKTIME	");
        query.append("             ,ISNULL(a.work_time,0)+ISNULL(b.work_time,0)    	TOTWORKTIME	");
        query.append("             , (SELECT x.key_name                                     			");
        query.append("              FROM TALANG x  inner join TADAY y									");
        query.append("              on    x.key_type = y.dow_key_type and x.key_no = y.dow_key_no		");
        query.append("              WHERE lang = ?                                                		");
        query.append("               AND y.tday = ISNULL(a.start_date, b.start_date)  )     DAY      	");
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
        query.getAndDateQuery("LEFT(x.start_date,6)", orgRptCraftWorkTimeDailyDTO.getStartDate(), orgRptCraftWorkTimeDailyDTO.getEndDate());
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
        query.getAndDateQuery("LEFT(y.start_date,6)", orgRptCraftWorkTimeDailyDTO.getStartDate(), orgRptCraftWorkTimeDailyDTO.getEndDate());
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
        return QuerySqlBuffer.listToString(resultList);
		
	}
    
}