package dream.org.rpt.craft.work.time.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.org.rpt.craft.work.time.dao.OrgRptCraftWorkTimeMonthlyDAO;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeMonthlyDTO;

/**
 * 작업자 월별 작업시간
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="orgRptCraftWorkTimeMonthlyDAOTarget"
 * @spring.txbn id="orgRptCraftWorkTimeMonthlyDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OrgRptCraftWorkTimeMonthlyDAOSqlImpl extends BaseJdbcDaoSupportSql implements OrgRptCraftWorkTimeMonthlyDAO
{

	@Override
	public List findList(OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("		SELECT     ''                                            	SEQNO			");
        query.append("                ,ISNULL(a.emp_id, b.emp_id)                       EMPID			");
        query.append("                ,ISNULL(a.start_date, b.start_date)               MONTH			");
        query.append("                ,convert(numeric(10,1),ISNULL(a.work_time,0)/60)  WOWORKTIME		");
        query.append("                ,convert(numeric(10,1),ISNULL(b.work_time,0)/60)  PMWORKTIME		");
        query.append("                ,(convert(numeric(10,1),ISNULL(a.work_time,0)/60)+convert(numeric(10,1),ISNULL(b.work_time,0)/60))   TOTWORKTIME		");
        query.append("       FROM (																		");
        query.append("              SELECT 	x.comp_no													");
        query.append("              		, x.emp_id													");
        query.append("              		, LEFT(x.start_date,6) 	start_date							");
        query.append("              		, SUM(x.work_time) 		work_time							");
        query.append("        FROM TAWOCRAFT x inner join TAWORKORDER y									");
        query.append("        on x.comp_no = y.comp_no and x.wkor_id = y.wkor_id						");
        query.append("    			WHERE 1=1															");
        query.append("    			AND  x.comp_no = ?													");
        query.append("    			AND  x.emp_id = ?													");
        query.append("        		AND  x.WORK_TIME is NOT NULL										");
        query.append("        		AND  y.wo_status in ('C', 'PRC')									");
        query.getAndDateQuery("LEFT(x.start_date,6)", orgRptCraftWorkTimeMonthlyDTO.getStartDate(), orgRptCraftWorkTimeMonthlyDTO.getEndDate());
        query.append("        		group by x.comp_no, x.emp_id, LEFT(x.start_date,6)					");
        query.append("        ) a FULL OUTER JOIN (														");
        query.append("              SELECT 	y.comp_no													");
        query.append("              		, y.emp_id													");
        query.append("              		, LEFT(y.start_date,6) 	start_date							");
        query.append("              		, SUM(y.work_time) 		work_time							");
        query.append("    			FROM TAPMINSLIST y													");
        query.append("    			WHERE 1=1															");
        query.append("    			AND  y.comp_no = ?													");
        query.append("    			AND  y.emp_id = ?													");
        query.append("        		AND  y.WORK_TIME is NOT NULL										");
        query.append("        		AND  y.PMSCHED_STATUS in ('C', 'PRC')								");
        query.getAndDateQuery("LEFT(y.start_date,6)", orgRptCraftWorkTimeMonthlyDTO.getStartDate(), orgRptCraftWorkTimeMonthlyDTO.getEndDate());
        query.append("        		GROUP BY y.comp_no, y.emp_id, LEFT(y.start_date,6)					");
        query.append("        ) b																		");
        query.append("    	ON a.comp_no = b.comp_no                                            		");
        query.append("    	AND a.emp_id = b.emp_id                                            			");
        query.append("    	AND a.START_DATE = b.START_DATE  											");

        query.getOrderByQuery("a.emp_id", "ISNULL(a.start_date, b.start_date) asc", orgRptCraftWorkTimeMonthlyDTO.getOrderBy(), orgRptCraftWorkTimeMonthlyDTO.getDirection());
        
        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,orgRptCraftWorkTimeMonthlyDTO.getEmpId()
        		,user.getCompNo()
        		,orgRptCraftWorkTimeMonthlyDTO.getEmpId()
        };

        return getJdbcTemplate().queryForList(query.toString(orgRptCraftWorkTimeMonthlyDTO.getIsLoadMaxCount(), orgRptCraftWorkTimeMonthlyDTO.getFirstRow()), objects);
    }

	@Override
	public String findTotalCount(OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO, User user) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	

        query.append("      SELECT  count(*)    														");
        query.append("      FROM (    																	");
    	query.append("		SELECT     ''                                            	SEQNO			");
        query.append("                ,ISNULL(a.emp_id, b.emp_id)                       EMPID			");
        query.append("                ,ISNULL(a.start_date, b.start_date)               MONTH			");
        query.append("                ,ISNULL(a.work_time,0)                            WOWORKTIME		");
        query.append("                ,ISNULL(b.work_time,0)                            PMWORKTIME		");
        query.append("                ,ISNULL(a.work_time,0)+ISNULL(b.work_time,0)    	TOTWORKTIME		");
        query.append("       FROM (																		");
        query.append("              SELECT 	x.comp_no													");
        query.append("              		, x.emp_id													");
        query.append("              		, LEFT(x.start_date,6) 	start_date							");
        query.append("              		, SUM(x.work_time) 		work_time							");
        query.append("        FROM TAWOCRAFT x inner join TAWORKORDER y									");
        query.append("        on x.comp_no = y.comp_no and x.wkor_id = y.wkor_id						");
        query.append("    			WHERE 1=1															");
        query.append("    			AND  x.comp_no = ?													");
        query.append("    			AND  x.emp_id = ?													");
        query.append("        		AND  x.WORK_TIME is NOT NULL										");
        query.append("        		AND  y.wo_status in ('C', 'PRC')									");
        query.getAndDateQuery("LEFT(x.start_date,6)", orgRptCraftWorkTimeMonthlyDTO.getStartDate(), orgRptCraftWorkTimeMonthlyDTO.getEndDate());
        query.append("        		group by x.comp_no, x.emp_id, LEFT(x.start_date,6)					");
        query.append("        ) a FULL OUTER JOIN (														");
        query.append("              SELECT 	y.comp_no													");
        query.append("              		, y.emp_id													");
        query.append("              		, LEFT(y.start_date,6) 	start_date							");
        query.append("              		, SUM(y.work_time) 		work_time							");
        query.append("    			FROM TAPMINSLIST y													");
        query.append("    			WHERE 1=1															");
        query.append("    			AND  y.comp_no = ?													");
        query.append("    			AND  y.emp_id = ?													");
        query.append("        		AND  y.WORK_TIME is NOT NULL										");
        query.append("        		AND  y.PMSCHED_STATUS in ('C', 'PRC')								");
        query.getAndDateQuery("LEFT(y.start_date,6)", orgRptCraftWorkTimeMonthlyDTO.getStartDate(), orgRptCraftWorkTimeMonthlyDTO.getEndDate());
        query.append("        		GROUP BY y.comp_no, y.emp_id, LEFT(y.start_date,6)					");
        query.append("        ) b																		");
        query.append("    	ON a.comp_no = b.comp_no                                            		");
        query.append("    	AND a.emp_id = b.emp_id                                            			");
        query.append("    	AND a.START_DATE = b.START_DATE  											");
        query.append("     	) aa																		");
    	
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,orgRptCraftWorkTimeMonthlyDTO.getEmpId()
        		,user.getCompNo()
        		,orgRptCraftWorkTimeMonthlyDTO.getEmpId()
        };

        List resultList=  getJdbcTemplate().queryForList(query.toString(), objects);
        return QuerySqlBuffer.listToString(resultList);
		
	}
    
}