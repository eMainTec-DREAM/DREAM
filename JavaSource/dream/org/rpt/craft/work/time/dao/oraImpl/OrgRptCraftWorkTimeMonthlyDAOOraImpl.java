package dream.org.rpt.craft.work.time.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class OrgRptCraftWorkTimeMonthlyDAOOraImpl extends BaseJdbcDaoSupportOra implements OrgRptCraftWorkTimeMonthlyDAO
{

	@Override
	public List findList(OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("		SELECT     ''                                            SEQNO				");
        query.append("                ,NVL(a.emp_id, b.emp_id)                       EMPID				");
        query.append("                ,NVL(a.start_date, b.start_date)               MONTH				");
        query.append("            	  ,ROUND(NVL(a.work_time,0)/60,1)        		 WOWORKTIME			");
        query.append("            	  ,ROUND(NVL(b.work_time,0)/60,1)        		 PMWORKTIME			");
        query.append("            	  ,(ROUND(NVL(a.work_time,0)/60,1) + ROUND(NVL(b.work_time,0)/60,1))   TOTWORKTIME  ");
        query.append("       FROM (																		");
        query.append("              SELECT 	x.comp_no													");
        query.append("              		, x.emp_id													");
        query.append("              		, SUBSTR(x.start_date,0,6) 	start_date						");
        query.append("              		, SUM(x.work_time) 		work_time							");
        query.append("        FROM TAWOCRAFT x inner join TAWORKORDER y									");
        query.append("        on x.comp_no = y.comp_no and x.wkor_id = y.wkor_id						");
        query.append("    			WHERE 1=1															");
        query.append("    			AND  x.comp_no = ?													");
        query.append("    			AND  x.emp_id = ?													");
        query.append("        		AND  x.WORK_TIME is NOT NULL										");
        query.append("        		AND  y.wo_status in ('C', 'PRC')									");
        query.getAndDateQuery("SUBSTR(x.start_date,0,6)", orgRptCraftWorkTimeMonthlyDTO.getStartDate(), orgRptCraftWorkTimeMonthlyDTO.getEndDate());
        query.append("        		group by x.comp_no, x.emp_id, SUBSTR(x.start_date,0,6)					");
        query.append("        ) a FULL OUTER JOIN (														");
        query.append("              SELECT 	y.comp_no													");
        query.append("              		, y.emp_id													");
        query.append("              		, SUBSTR(y.start_date,0,6) 	start_date							");
        query.append("              		, SUM(y.work_time) 		work_time							");
        query.append("    			FROM TAPMINSLIST y													");
        query.append("    			WHERE 1=1															");
        query.append("    			AND  y.comp_no = ?													");
        query.append("    			AND  y.emp_id = ?													");
        query.append("        		AND  y.WORK_TIME is NOT NULL										");
        query.append("        		AND  y.PMSCHED_STATUS in ('C', 'PRC')								");
        query.getAndDateQuery("SUBSTR(y.start_date,0,6)", orgRptCraftWorkTimeMonthlyDTO.getStartDate(), orgRptCraftWorkTimeMonthlyDTO.getEndDate());
        query.append("        		GROUP BY y.comp_no, y.emp_id, SUBSTR(y.start_date,0,6)					");
        query.append("        ) b																		");
        query.append("    	ON a.comp_no = b.comp_no                                            		");
        query.append("    	AND a.emp_id = b.emp_id                                            			");
        query.append("    	AND a.START_DATE = b.START_DATE  											");

        query.getOrderByQuery("NVL(a.start_date, b.start_date) asc", orgRptCraftWorkTimeMonthlyDTO.getOrderBy(), orgRptCraftWorkTimeMonthlyDTO.getDirection());
        
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
    	QueryBuffer query = new QueryBuffer();

        query.append("      SELECT  count(*)    														");
        query.append("      FROM (    																	");
    	query.append("		SELECT     ''                                            	SEQNO			");
        query.append("                ,NVL(a.emp_id, b.emp_id)                       EMPID			");
        query.append("                ,NVL(a.start_date, b.start_date)               MONTH			");
        query.append("                ,NVL(a.work_time,0)                            WOWORKTIME		");
        query.append("                ,NVL(b.work_time,0)                            PMWORKTIME		");
        query.append("                ,NVL(a.work_time,0)+NVL(b.work_time,0)    	TOTWORKTIME		");
        query.append("       FROM (																		");
        query.append("              SELECT 	x.comp_no													");
        query.append("              		, x.emp_id													");
        query.append("              		, SUBSTR(x.start_date,0,6) 	start_date							");
        query.append("              		, SUM(x.work_time) 		work_time							");
        query.append("        FROM TAWOCRAFT x inner join TAWORKORDER y									");
        query.append("        on x.comp_no = y.comp_no and x.wkor_id = y.wkor_id						");
        query.append("    			WHERE 1=1															");
        query.append("    			AND  x.comp_no = ?													");
        query.append("    			AND  x.emp_id = ?													");
        query.append("        		AND  x.WORK_TIME is NOT NULL										");
        query.append("        		AND  y.wo_status in ('C', 'PRC')									");
        query.getAndDateQuery("SUBSTR(x.start_date,0,6)", orgRptCraftWorkTimeMonthlyDTO.getStartDate(), orgRptCraftWorkTimeMonthlyDTO.getEndDate());
        query.append("        		group by x.comp_no, x.emp_id, SUBSTR(x.start_date,0,6)					");
        query.append("        ) a FULL OUTER JOIN (														");
        query.append("              SELECT 	y.comp_no													");
        query.append("              		, y.emp_id													");
        query.append("              		, SUBSTR(y.start_date,0,6) 	start_date							");
        query.append("              		, SUM(y.work_time) 		work_time							");
        query.append("    			FROM TAPMINSLIST y													");
        query.append("    			WHERE 1=1															");
        query.append("    			AND  y.comp_no = ?													");
        query.append("    			AND  y.emp_id = ?													");
        query.append("        		AND  y.WORK_TIME is NOT NULL										");
        query.append("        		AND  y.PMSCHED_STATUS in ('C', 'PRC')								");
        query.getAndDateQuery("SUBSTR(y.start_date,0,6)", orgRptCraftWorkTimeMonthlyDTO.getStartDate(), orgRptCraftWorkTimeMonthlyDTO.getEndDate());
        query.append("        		GROUP BY y.comp_no, y.emp_id, SUBSTR(y.start_date,0,6)					");
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
        return QueryBuffer.listToString(resultList);
		
	}
    
}