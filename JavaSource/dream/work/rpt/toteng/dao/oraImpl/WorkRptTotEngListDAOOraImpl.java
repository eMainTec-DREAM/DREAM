package dream.work.rpt.toteng.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.toteng.dao.WorkRptTotEngListDAO;
import dream.work.rpt.toteng.dto.WorkRptTotEngCommonDTO;
import dream.work.rpt.toteng.form.WorkRptTotEngListForm;

/**
 * 에너지사용량(집계) 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptTotEngListDAOTarget"
 * @spring.txbn id="workRptTotEngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptTotEngListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptTotEngListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptTotEngListForm
     * @param loginUser
     * @return List
     */
    public List findPlantList(WorkRptTotEngListForm workRptTotEngListForm, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        int year = Integer.parseInt(workRptTotEngListForm.getWorkRptTotEngCommonDTO().getFilterYear());
        String month = "";
        
        query.append("SELECT							                                                                                        ");
        query.append("       row_number() OVER(ORDER BY y.plant desc) 				                                 AS seqNo                   ");
        query.append("     , y.plant       	                                                                         AS PLANTID	                ");
        query.append("     , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = y.plant) AS PLANTDESC               ");
        for(int i=1; i<=12; i++)
        {
        	if(i<10) {	month = "0"+i;	} 
        	else {	month = ""+i;	}
        	query.append("    , SUM(x.USAGE"+month+")    					  		                                 AS \"USAGE"+month+"\"     ");
        	query.append("    , SUM(x.PRICE"+month+")    					  		                                 AS \"UNITPRICE"+month+"\" ");
        }
        query.append("     , SUM(x.totusage)                                                                         AS TOTUSAGEAMT		       ");
        query.append("  FROM									                                                                               ");
        query.append("       (									                                                                               ");
        query.append("        SELECT 							                                                                               ");
        query.append("               x.comp_no						                                                                           ");
        query.append("             , x.equip_id					                                                                               ");
        query.append("             , x.usage_date					                                                                           ");
        for (int j=1; j<=12; j++)
        {
        	if(j<10) {	month = "0"+j;	} 
        	else {	month = ""+j;	}
        	
        	query.append("         , CASE x.usage_date WHEN '"+year+month+"' THEN SUM(x.USAGE) ELSE 0  END 	                   AS \"USAGE"+month+"\"	");
        	query.append("         , CASE x.usage_date WHEN '"+year+month+"' THEN SUM(x.USAGE) * MAX(unitPrice) ELSE 0  END    AS \"PRICE"+month+"\"	");
        }
        query.append("             , CASE SUBSTR(MAX(x.usage_date),1,4) WHEN '"+year+"' THEN SUM(x.USAGE) ELSE 0 END TOTUSAGE		                    ");
        query.append("          FROM TAFEMSMONUSAGE x				");
        query.append("          LEFT OUTER JOIN  (SELECT                                                          ");
        query.append("                                   x.usage_date                                             ");
        query.append("                                 , MAX(y.unit_price) / SUM(USAGE) unitPrice                 ");
        query.append("                              FROM TAFEMSMONUSAGE x                                         ");
        query.append("                              LEFT OUTER JOIN TASTDCHKPOINTPRICE y                          ");
        query.append("                                           ON x.usage_date = y.yyyymm                       ");
        query.append("                              LEFT OUTER JOIN TASTDCHECKPOINT z                             ");
        query.append("                                           ON y.std_check_point_id = z.std_check_point_id   ");
        query.append("                                          AND z.check_point_type   = 'EP'                   ");
        query.append("                             GROUP BY x.comp_no, x.usage_date                               ");
        query.append("                           ) y                                                              ");
        query.append("                       ON x.usage_date = y.usage_date                                       ");
        query.append("	       WHERE 1=1							                                              ");
        query.append(this.getWhere(workRptTotEngListForm,loginUser));
        query.append("         GROUP BY x.comp_no, x.equip_id, x.usage_date		                                  ");
        query.append("       ) x INNER JOIN TAEQUIPMENT y			                                              ");
        query.append("                   ON x.comp_no  = y.comp_no				                                  ");
        query.append("                  AND x.equip_id = y.equip_id			                                      ");
        query.append(" GROUP BY x.comp_no, y.plant			                                                      ");
        query.append(" ORDER BY x.comp_no, y.plant			                                                      ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    
    public List findChart(WorkRptTotEngListForm workRptTotEngListForm, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int year = Integer.parseInt(workRptTotEngListForm.getWorkRptTotEngCommonDTO().getFilterYear());
    	String month = "";
    	
    	query.append("SELECT							");
        query.append("      '' 				seqNo		");
        query.append("    , y.plant       	PLANTID		");
        query.append("    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = y.plant) 			PLANTDESC		");
        query.append("    , SUM(x.totusage)    TOTUSAGEAMT		");
        query.append("FROM									");
        query.append("(										");
        query.append("    SELECT 							");
        query.append("        x.comp_no						");
        query.append("       , x.equip_id					");
        query.append("       , x.usage_date					");
        query.append("       , CASE SUBSTR(MAX(x.usage_date),1,4) WHEN '"+year+"' THEN SUM(x.USAGE) ELSE 0 END TOTUSAGE		");
        query.append("    FROM TAFEMSMONUSAGE x				");
        query.append("	  WHERE 1=1							");
        query.append(this.getWhere(workRptTotEngListForm,loginUser));
        query.append("    GROUP BY x.comp_no, x.equip_id, x.usage_date		");
        query.append(") x INNER JOIN TAEQUIPMENT y			");
        query.append("ON x.comp_no = y.comp_no				");
        query.append("AND x.equip_id = y.equip_id			");
        query.append("GROUP BY x.comp_no, y.plant			");
        query.append("ORDER BY x.comp_no, y.plant			");
        
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public String getWhere(WorkRptTotEngListForm workRptTotEngListForm,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        WorkRptTotEngCommonDTO workRptTotEngCommonDTO = workRptTotEngListForm.getWorkRptTotEngCommonDTO();
        
        String fromDate = workRptTotEngCommonDTO.getFilterYear()+"01";
        String toDate   = workRptTotEngCommonDTO.getFilterYear()+"12";
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND x.usage_date >= '"+fromDate+"'     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND x.usage_date <= '"+toDate+"'       ");
        }          

        query.append("AND EXISTS (SELECT 1 							");
        query.append("              FROM TAEQUIPMENT a 				");
        query.append("             WHERE a.comp_no = x.comp_no 		");
        query.append("               AND a.equip_id = x.equip_id	");
        query.getAndQuery("a.is_last_version", "Y");
        query.getAndQuery("a.is_deleted", "N");
        query.append("                    )       					");

        return query.toString();
    }   
}