package dream.work.rpt.toteng.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.toteng.dao.WorkRptTotEngDetailListDAO;
import dream.work.rpt.toteng.dto.WorkRptTotEngCommonDTO;
import dream.work.rpt.toteng.dto.WorkRptTotEngDetailListDTO;
import dream.work.rpt.toteng.form.WorkRptTotEngDetailListForm;

/**
 * 에너지사용량(집계) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptTotEngDetailListDAOTarget"
 * @spring.txbn id="workRptTotEngDetailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptTotEngDetailListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptTotEngDetailListDAO
{
    @Override
    public List findEqLocDetailList(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int year = Integer.parseInt(workRptTotEngDetailListForm.getWorkRptTotEngCommonDTO().getFilterYear());
        String month = "";
        
        query.append("SELECT									");
        query.append("      row_number() OVER(ORDER BY y.eqloc_id desc)  					seqNo			");
        query.append("    , y.plant       		PLANTID			");
        query.append("    , y.eqloc_id      	EQLOCID			");
        query.append("    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = y.plant) 			PLANTDESC		");
        query.append("    , (SELECT a.description FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = y.eqloc_id)       EQLOCDESC		");
        for(int i=1; i<=12; i++)
        {
        	if(i<10) {	month = "0"+i;	} 
        	else {	month = ""+i;	}
        	query.append("    , SUM(x.USAGE"+month+")    					  		AS \"USAGE"+month+"\"		");
        	query.append("    , SUM(x.PRICE"+month+")                               AS \"UNITPRICE"+month+"\"   ");
        }
        
        query.append("    , SUM(x.totusage)    TOTUSAGEAMT		");
        query.append("FROM										");
        query.append("(											");
        query.append("    SELECT 								");
        query.append("        x.comp_no							");
        query.append("       , x.equip_id						");
        query.append("       , x.usage_date						");
        for (int j=1; j<=12; j++)
        {
        	if(j<10) {	month = "0"+j;	} 
        	else {	month = ""+j;	}
        	
        	query.append("   , CASE x.usage_date WHEN '"+year+month+"' THEN SUM(x.USAGE) ELSE 0  END 	AS \"USAGE"+month+"\"		");
        	query.append("   , CASE x.usage_date WHEN '"+year+month+"' THEN SUM(x.USAGE) * MAX(unitPrice) ELSE 0  END    AS \"PRICE"+month+"\"   ");	
        }
        query.append("       , CASE SUBSTRING(MAX(x.usage_date),1,4) WHEN '"+year+"' THEN SUM(x.USAGE) ELSE 0 END TOTUSAGE		");
        query.append("    FROM TAFEMSMONUSAGE x					");
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
        query.append("	  WHERE 1=1								");
        query.append(this.getWhere(workRptTotEngDetailListForm,loginUser));
        query.append("    GROUP BY x.comp_no, x.equip_id, x.usage_date		");
        query.append(") x INNER JOIN TAEQUIPMENT y				");
        query.append("ON x.comp_no = y.comp_no					");
        query.append("AND x.equip_id = y.equip_id				");
        query.append("GROUP BY x.comp_no, y.plant, y.eqloc_id   ");
        query.append("ORDER BY x.comp_no, y.plant, y.eqloc_id   ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findUsageDeptDetailList(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int year = Integer.parseInt(workRptTotEngDetailListForm.getWorkRptTotEngCommonDTO().getFilterYear());
    	String month = "";
    	
    	query.append("SELECT								");
    	query.append("      row_number() OVER(ORDER BY y.usage_dept desc) 				seqNo			");
    	query.append("    , y.plant       	PLANTID			");
    	query.append("    , y.eqloc_id      EQLOCID			");
    	query.append("    , y.usage_dept    USAGEDEPTID		");
    	query.append("    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = y.plant) 			PLANTDESC		");
    	query.append("    , (SELECT a.description FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = y.eqloc_id)       EQLOCDESC		");
    	query.append("	  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = y.usage_dept) 		USAGEDEPTDESC	");
     	for(int i=1; i<=12; i++)
    	{
    		if(i<10) {	month = "0"+i;	} 
    		else {	month = ""+i;	}
    		query.append("    , SUM(x.USAGE"+month+")    					  		AS \"USAGE"+month+"\"		");
    		query.append("    , SUM(x.PRICE"+month+")                               AS \"UNITPRICE"+month+"\"   ");
    	}
    	
    	query.append("    , SUM(x.totusage)    TOTUSAGEAMT	");
    	query.append("FROM									");
    	query.append("(										");
    	query.append("    SELECT 							");
    	query.append("        x.comp_no						");
    	query.append("       , x.equip_id					");
    	query.append("       , x.usage_date					");
    	for (int j=1; j<=12; j++)
    	{
    		if(j<10) {	month = "0"+j;	} 
    		else {	month = ""+j;	}
    		
    		query.append("   , CASE x.usage_date WHEN '"+year+month+"' THEN SUM(x.USAGE) ELSE 0  END 	AS \"USAGE"+month+"\"		");
    		query.append("   , CASE x.usage_date WHEN '"+year+month+"' THEN SUM(x.USAGE) * MAX(unitPrice) ELSE 0  END    AS \"PRICE"+month+"\"  ");
    		
    	}
    	query.append("       , CASE SUBSTRING(MAX(x.usage_date),1,4) WHEN '"+year+"' THEN SUM(x.USAGE) ELSE 0 END TOTUSAGE		");
    	query.append("    FROM TAFEMSMONUSAGE x				");
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
    	query.append("	  WHERE 1=1							");
    	query.append(this.getWhere(workRptTotEngDetailListForm,loginUser));
    	query.append("    GROUP BY x.comp_no, x.equip_id, x.usage_date		");
    	query.append(") x INNER JOIN TAEQUIPMENT y			");
    	query.append("ON x.comp_no = y.comp_no				");
    	query.append("AND x.equip_id = y.equip_id			");
    	query.append("GROUP BY x.comp_no, y.plant, y.eqloc_id, y.usage_dept    ");
    	query.append("ORDER BY x.comp_no, y.plant, y.eqloc_id, y.usage_dept    ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqCtgDetailList(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int year = Integer.parseInt(workRptTotEngDetailListForm.getWorkRptTotEngCommonDTO().getFilterYear());
    	String month = "";
    	
    	query.append("SELECT								");
    	query.append("      row_number() OVER(ORDER BY y.eqctg_id desc) 				seqNo			");
    	query.append("    , y.plant       	PLANTID			");
    	query.append("    , y.eqloc_id      EQLOCID			");
    	query.append("    , y.usage_dept    USAGEDEPTID		");
    	query.append("    , y.eqctg_id      EQCTGID			");
    	query.append("    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = y.plant) 			PLANTDESC		");
    	query.append("    , (SELECT a.description FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = y.eqloc_id)       EQLOCDESC		");
    	query.append("	  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = y.usage_dept) 		USAGEDEPTDESC	");
    	query.append("    , (SELECT a.description FROM TAEQCTG a WHERE a.comp_no = x.comp_no AND a.eqctg_id = y.eqctg_id)       EQCTGDESC		");
    	for(int i=1; i<=12; i++)
    	{
    		if(i<10) {	month = "0"+i;	} 
    		else {	month = ""+i;	}
    		query.append("    , SUM(x.USAGE"+month+")    					  		AS \"USAGE"+month+"\"		");
    		query.append("    , SUM(x.PRICE"+month+")                               AS \"UNITPRICE"+month+"\"   ");
    	}
    	
    	query.append("    , SUM(x.totusage)    TOTUSAGEAMT	");
    	query.append("FROM									");
    	query.append("(										");
    	query.append("    SELECT 							");
    	query.append("        x.comp_no						");
    	query.append("       , x.equip_id					");
    	query.append("       , x.usage_date					");
    	for (int j=1; j<=12; j++)
    	{
    		if(j<10) {	month = "0"+j;	} 
    		else {	month = ""+j;	}
    		
    		query.append("   , CASE x.usage_date WHEN '"+year+month+"' THEN SUM(x.USAGE) ELSE 0  END 	AS \"USAGE"+month+"\"		");
    		query.append("   , CASE x.usage_date WHEN '"+year+month+"' THEN SUM(x.USAGE) * MAX(unitPrice) ELSE 0  END    AS \"PRICE"+month+"\"  ");
    	}
    	query.append("       , CASE SUBSTRING(MAX(x.usage_date),1,4) WHEN '"+year+"' THEN SUM(x.USAGE) ELSE 0 END TOTUSAGE		");
    	query.append("    FROM TAFEMSMONUSAGE x				");
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
    	query.append("	  WHERE 1=1							");
    	query.append(this.getWhere(workRptTotEngDetailListForm,loginUser));
    	query.append("    GROUP BY x.comp_no, x.equip_id, x.usage_date		");
    	query.append(") x INNER JOIN TAEQUIPMENT y			");
    	query.append("ON x.comp_no = y.comp_no				");
    	query.append("AND x.equip_id = y.equip_id			");
    	query.append("GROUP BY x.comp_no, y.plant, y.eqloc_id, y.usage_dept, y.eqctg_id    ");
    	query.append("ORDER BY x.comp_no, y.plant, y.eqloc_id, y.usage_dept, y.eqctg_id    ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqLocDetailChart(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int year = Integer.parseInt(workRptTotEngDetailListForm.getWorkRptTotEngCommonDTO().getFilterYear());
    	String month = "";
    	
    	query.append("SELECT							        ");
        query.append("      '' 				seqNo		        ");
        query.append("    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = y.plant) 			PLANTDESC		");
        query.append("    , (SELECT a.description FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = y.eqloc_id)       EQLOCDESC		");
        query.append("    , SUM(x.totusage)    TOTUSAGEAMT		");
        query.append("FROM									    ");
        query.append("(										    ");
        query.append("    SELECT 							    ");
        query.append("        x.comp_no						    ");
        query.append("       , x.equip_id					    ");
        query.append("       , x.usage_date					    ");
        query.append("       , CASE SUBSTRING(MAX(x.usage_date),1,4) WHEN '"+year+"' THEN SUM(x.USAGE) ELSE 0 END TOTUSAGE		");
        query.append("    FROM TAFEMSMONUSAGE x				    ");
        query.append("	  WHERE 1=1							    ");
        query.append(this.getWhere(workRptTotEngDetailListForm,loginUser));
        query.append("    GROUP BY x.comp_no, x.equip_id, x.usage_date		");
        query.append(") x INNER JOIN TAEQUIPMENT y			    ");
        query.append("ON x.comp_no = y.comp_no				    ");
        query.append("AND x.equip_id = y.equip_id			    ");
        query.append("GROUP BY x.comp_no, y.plant, y.eqloc_id   ");
        query.append("ORDER BY x.comp_no, y.plant, y.eqloc_id   ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findUsageDeptDetailChart(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int year = Integer.parseInt(workRptTotEngDetailListForm.getWorkRptTotEngCommonDTO().getFilterYear());
    	String month = "";
    	
    	query.append("SELECT							        ");
    	query.append("      '' 				seqNo		        ");
    	query.append("    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = y.plant) 			PLANTDESC		");
    	query.append("    , (SELECT a.description FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = y.eqloc_id)       EQLOCDESC		");
    	query.append("	  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = y.usage_dept) 		USAGEDEPTDESC	");
    	query.append("    , SUM(x.totusage)    TOTUSAGEAMT		");
    	query.append("FROM									    ");
    	query.append("(										    ");
    	query.append("    SELECT 							    ");
    	query.append("        x.comp_no						    ");
    	query.append("       , x.equip_id					    ");
    	query.append("       , x.usage_date					    ");
    	query.append("       , CASE SUBSTRING(MAX(x.usage_date),1,4) WHEN '"+year+"' THEN SUM(x.USAGE) ELSE 0 END TOTUSAGE		");
    	query.append("    FROM TAFEMSMONUSAGE x				    ");
    	query.append("	  WHERE 1=1							    ");
    	query.append(this.getWhere(workRptTotEngDetailListForm,loginUser));
    	query.append("    GROUP BY x.comp_no, x.equip_id, x.usage_date		");
    	query.append(") x INNER JOIN TAEQUIPMENT y			    ");
    	query.append("ON x.comp_no = y.comp_no				    ");
    	query.append("AND x.equip_id = y.equip_id			    ");
    	query.append("GROUP BY x.comp_no, y.plant, y.eqloc_id, y.usage_dept    ");
    	query.append("ORDER BY x.comp_no, y.plant, y.eqloc_id, y.usage_dept    ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqCtgDetailChart(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int year = Integer.parseInt(workRptTotEngDetailListForm.getWorkRptTotEngCommonDTO().getFilterYear());
    	String month = "";
    	
    	query.append("SELECT							        ");
    	query.append("      '' 				seqNo		        ");
    	query.append("    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = y.plant) 			PLANTDESC		");
    	query.append("    , (SELECT a.description FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = y.eqloc_id)       EQLOCDESC		");
    	query.append("	  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = y.usage_dept) 		USAGEDEPTDESC	");
    	query.append("    , (SELECT a.description FROM TAEQCTG a WHERE a.comp_no = x.comp_no AND a.eqctg_id = y.eqctg_id)       EQCTGDESC		");
    	query.append("    , SUM(x.totusage)    TOTUSAGEAMT		");
    	query.append("FROM									    ");
    	query.append("(										    ");
    	query.append("    SELECT 							    ");
    	query.append("        x.comp_no						    ");
    	query.append("       , x.equip_id					    ");
    	query.append("       , x.usage_date					    ");
    	query.append("       , CASE SUBSTRING(MAX(x.usage_date),1,4) WHEN '"+year+"' THEN SUM(x.USAGE) ELSE 0 END TOTUSAGE		");
    	query.append("    FROM TAFEMSMONUSAGE x				    ");
    	query.append("	  WHERE 1=1							    ");
    	query.append(this.getWhere(workRptTotEngDetailListForm,loginUser));
    	query.append("    GROUP BY x.comp_no, x.equip_id, x.usage_date		");
    	query.append(") x INNER JOIN TAEQUIPMENT y			    ");
    	query.append("ON x.comp_no = y.comp_no				    ");
    	query.append("AND x.equip_id = y.equip_id			    ");
    	query.append("GROUP BY x.comp_no, y.plant, y.eqloc_id, y.usage_dept, y.eqctg_id    ");
    	query.append("ORDER BY x.comp_no, y.plant, y.eqloc_id, y.usage_dept, y.eqctg_id    ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    

    public String getWhere(WorkRptTotEngDetailListForm workRptTotEngDetailListForm,User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        WorkRptTotEngCommonDTO workRptTotEngCommonDTO = workRptTotEngDetailListForm.getWorkRptTotEngCommonDTO();
        WorkRptTotEngDetailListDTO workRptTotEngDetailListDTO = workRptTotEngDetailListForm.getWorkRptTotEngDetailListDTO();
        
        query.append("AND EXISTS (SELECT 1 							");
        query.append("              FROM TAEQUIPMENT a 				");
        query.append("             WHERE a.comp_no = x.comp_no 		");
        query.append("               AND a.equip_id = x.equip_id	");
        query.getAndQuery("a.is_last_version", "Y");
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("a.plant", workRptTotEngDetailListDTO.getPlantId());
        query.getAndQuery("a.eqloc_id", workRptTotEngDetailListDTO.getEqLocId());
        query.getAndQuery("a.usage_dept", workRptTotEngDetailListDTO.getUsageDeptId());
        query.getAndQuery("a.eqctg_id", workRptTotEngDetailListDTO.getEqCtgId());
        query.getAndQuery("a.equip_id", workRptTotEngDetailListDTO.getEquipId());
        query.append("                    )       					");

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
        
        return query.toString();
    }
    
}