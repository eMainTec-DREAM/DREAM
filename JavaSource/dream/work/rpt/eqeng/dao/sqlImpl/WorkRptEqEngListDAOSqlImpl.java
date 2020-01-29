package dream.work.rpt.eqeng.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.eqeng.dao.WorkRptEqEngListDAO;
import dream.work.rpt.eqeng.dto.WorkRptEqEngCommonDTO;
import dream.work.rpt.eqeng.form.WorkRptEqEngListForm;

/**
 * 에너지사용량(설비별) 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptEqEngListDAOTarget"
 * @spring.txbn id="workRptEqEngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptEqEngListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptEqEngListDAO
{
    @Override
    public List findPlantList(WorkRptEqEngListForm workRptEqEngListForm, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        List totalUsageList = workRptEqEngListForm.getWorkRptEqEngCommonDTO().getTotalUsageList();
        
        int year = Integer.parseInt(workRptEqEngListForm.getWorkRptEqEngCommonDTO().getFilterYear());
        String month = "";
        int prevMon = 1;
        String prevMonStr = "";
        
        query.append("SELECT							");
        query.append("      '' 				seqNo		");
        query.append("    , x.equip_id     	equipId		");
        query.append("    , y.plant       	PLANT		");
        query.append("    , y.eqctg_id    	EQCTGID		");
        query.append("    , y.eqloc_id    	EQLOCID		");
        query.append("    , y.usage_dept  	USAGEDEPTID	");
        query.append("    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = y.plant) 			PLANTDESC		");
        query.append("    , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = y.eqloc_id) 		EQLOCDESC		");
        query.append("    , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = y.usage_dept) 		USAGEDEPTDESC	");
        query.append("    , (SELECT a.description FROM TAEQCTG a WHERE a.comp_no = x.comp_no AND a.eqctg_id = y.eqctg_id) 		EQCTGDESC		");
        query.append("    , (SELECT a.item_no FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)  		ITEMNO			");
        query.append("    , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id) 	EQUIPDESC		");
        query.append("    , ROUND(SUM(x.p"+year+"01),2)                         AS \"P"+year+"01\"      ");
        query.append("    , ROUND(SUM(x.u"+year+"01),2)    					   	AS \"U"+year+"01\"		");
        query.append("    , ROUND(SUM(x.u"+year+"01)-SUM(x.u"+(year-1)+"12),2) 	AS \"C"+year+"01\"		");
        
        List result = null;
        Map totalUsageMap = (Map) result;
        		
		if(0<totalUsageList.size())
        {
        	totalUsageMap = (Map) totalUsageList.get(0);
        	if(!"0".equals(String.valueOf(totalUsageMap.get("totalUsage")))){
        		query.append("    , ROUND(100 * SUM(x.u"+year+"01) / "+totalUsageMap.get("totalUsage")+",2)  				  AS \"R"+year+"01\"		");
        	}else{
        		query.append("    , 0 			  																			  AS \"R"+year+"01\"		");
        	}	        	
        }
        else
        {
        	query.append("    , 0									  				  							  			  AS \"R"+year+"01\"		");
        }

        for(int i=2; i<=12; i++)
        {
        	if(i<10) {	month = "0"+i;	} 
        	else {	month = ""+i;	}
        	
        	if(prevMon<10) {	prevMonStr = "0"+prevMon;	} 
        	else {	prevMonStr = ""+prevMon;	}
        	
        	if(0<totalUsageList.size() && i <= totalUsageList.size())
            {
    	    	totalUsageMap = (Map) totalUsageList.get(i-1);
    	    	query.append("    , ROUND(SUM(x.P"+year+month+"),2)                                 AS \"P"+year+month+"\"      ");
    	    	query.append("    , ROUND(SUM(x.U"+year+month+"),2)	    					  		AS \"U"+year+month+"\"		");
	        	query.append("    , ROUND(SUM(x.U"+year+month+")-SUM(x.u"+year+prevMonStr+"),2) 	AS \"C"+year+month+"\"		");

	        	if(!"0".equals(String.valueOf(totalUsageMap.get("totalUsage")))){
	        		query.append("    , ROUND(100 * SUM(x.U"+year+month+") / "+totalUsageMap.get("totalUsage")+",2)  				  		AS \"R"+year+month+"\"		");
	        	}else{
	        		query.append("    , 0 			  												AS \"R"+year+month+"\"		");
	        	}
            }
    	    else
    	    {
    	        query.append("    , ROUND(SUM(x.P"+year+month+"),2)                                 AS \"P"+year+month+"\"      ");
    	        query.append("    , ROUND(SUM(x.U"+year+month+"),2)    					  			AS \"U"+year+month+"\"		");
    	    	query.append("    , ROUND(SUM(x.U"+year+month+")-SUM(x.u"+year+prevMonStr+"),2) 	AS \"C"+year+month+"\"		");
    	    	query.append("    , 0 			  													AS \"R"+year+month+"\"		");
    	    }
        	
        	prevMon = Integer.parseInt(month);
        }
        
        query.append("    , ROUND(SUM(x.totusage),2)    TOTUSAGE		");
        query.append("FROM									");
        query.append("(										");
        query.append("    SELECT 							");
        query.append("        x.comp_no						");
        query.append("       , x.equip_id					");
        query.append("       , x.usage_date					");
        query.append("       , CASE x.usage_date WHEN '"+(year-1)+"12' THEN SUM(x.USAGE) ELSE 0  END AS \"U"+(year-1)+"12\"		");
        for (int j=1; j<=12; j++)
        {
        	if(j<10) {	month = "0"+j;	} 
        	else {	month = ""+j;	}
        	
        	query.append("   , CASE x.usage_date WHEN '"+year+month+"' THEN SUM(x.USAGE) ELSE 0  END 	AS \"U"+year+month+"\"		");
        	query.append("   , CASE x.usage_date WHEN '"+year+month+"' THEN SUM(x.USAGE) * MAX(unitPrice) ELSE 0  END    AS \"P"+year+month+"\"   ");	
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
        query.append(this.getWhere(workRptEqEngListForm,loginUser));
        query.append("    GROUP BY x.comp_no, x.equip_id, x.usage_date		");
        query.append(") x INNER JOIN TAEQUIPMENT y			");
        query.append("ON x.comp_no = y.comp_no				");
        query.append("AND x.equip_id = y.equip_id			");
        query.append("GROUP BY x.comp_no, x.equip_id, y.plant, y.eqctg_id, y.eqloc_id, y.usage_dept		");
        query.append("ORDER BY x.comp_no, x.equip_id		");
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }

    public String getWhere(WorkRptEqEngListForm workRptEqEngListForm,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        WorkRptEqEngCommonDTO workRptEqEngCommonDTO = workRptEqEngListForm.getWorkRptEqEngCommonDTO();
        
        String fromDate = workRptEqEngCommonDTO.getFilterYear()+"01";
        String toDate   = workRptEqEngCommonDTO.getFilterYear()+"12";
        
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
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT c.description FROM  TAPLANT c WHERE c.comp_no = '"+loginUser.getCompNo()+"' AND c.plant = a.plant )", 
        		workRptEqEngCommonDTO.getFilterPlant(), workRptEqEngCommonDTO.getFilterPlantDesc());
        //위치
        query.getEqLocLevelQuery("a.eqloc_id", workRptEqEngCommonDTO.getFilterEqLocId(), workRptEqEngCommonDTO.getFilterEqLocDesc(), loginUser.getCompNo());
        //사용부서
        query.getDeptLevelQuery("a.usage_dept", workRptEqEngCommonDTO.getFilterUsageDeptId(), workRptEqEngCommonDTO.getFilterUsageDeptDesc(), loginUser.getCompNo());
        //종류
        query.getEqCtgLevelQuery("a.eqctg_id", workRptEqEngCommonDTO.getFilterEqCtgId(), workRptEqEngCommonDTO.getFilterEqCtgDesc(), loginUser.getCompNo());
        //설비
        query.getAndQuery("a.equip_id", workRptEqEngCommonDTO.getFilterEquipId());
        query.getAndQuery("a.description", workRptEqEngCommonDTO.getFilterEquipDesc());
        query.append("                    )       					");

        return query.toString();
    }
    public String getOuterWhere(WorkRptEqEngListForm workRptEqEngListForm,User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	WorkRptEqEngCommonDTO workRptEqEngCommonDTO = workRptEqEngListForm.getWorkRptEqEngCommonDTO();
    	
    	String fromDate = workRptEqEngCommonDTO.getFilterYear()+"01";
    	String toDate   = workRptEqEngCommonDTO.getFilterYear()+"12";
    	
    	if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
    	{
    		query.append("AND a.tmonth >= '"+fromDate+"'     ");
    	}
    	
    	if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
    	{
    		query.append("AND a.tmonth <= '"+toDate+"'       ");
    	}          
    	
    	return query.toString();
    }

    public List findTotalCount(WorkRptEqEngListForm workRptEqEngListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	int year = Integer.parseInt(workRptEqEngListForm.getWorkRptEqEngCommonDTO().getFilterYear());
        
    	query.append("SELECT 												");
    	query.append("        a.tmonth        				usageDate		");
    	query.append("       , CONVERT(INT,ISNULL(MAX(z.totalUsage),0))  totalUsage		");
    	query.append("FROM TADAY a LEFT OUTER JOIN (						");
    	query.append("SELECT                                         		");
    	query.append("      ISNULL(a.usage_date,'')        	usageDate       ");
    	query.append("    , ISNULL(SUM(a.TOTUSAGE),0)     	totalUsage      ");
    	query.append("FROM (                                        		");
    	query.append("SELECT                                        		");
    	query.append("      usage_date                                		");
    	query.append("    , SUM(x.totusage)    TOTUSAGE                		");
    	query.append("FROM                                            		");
    	query.append("(                                                		");
    	query.append("    SELECT                                     		");
    	query.append("        x.comp_no                                		");
    	query.append("       , x.equip_id                            		");
    	query.append("       , x.usage_date                          		");
    	query.append("       , CASE SUBSTRING(MAX(x.usage_date),1,4) WHEN '"+year+"' THEN SUM(x.USAGE) ELSE 0 END TOTUSAGE                		");
    	query.append("    FROM TAFEMSMONUSAGE x                        		");
    	query.append("      WHERE 1=1                                		");
    	query.append(this.getWhere(workRptEqEngListForm,loginUser));
    	query.append("    GROUP BY x.comp_no, x.equip_id, x.usage_date      ");
    	query.append(") x INNER JOIN TAEQUIPMENT y                    		");
    	query.append("ON x.comp_no = y.comp_no                        		");
    	query.append("AND x.equip_id = y.equip_id                    		");
    	query.append("GROUP BY x.comp_no, x.equip_id, y.plant, y.eqctg_id, y.eqloc_id, y.usage_dept  ,x.Usage_date              		");
    	query.append(") a                                            		");
    	query.append("GROUP BY a.usage_date                          		");
    	query.append(") z             										");
    	query.append("ON a.tmonth = z.usageDate								");
    	query.append("WHERE 1=1 											");
    	query.append(this.getOuterWhere(workRptEqEngListForm,loginUser));
    	query.append("GROUP BY a.tmonth		                                ");
    	query.append("ORDER BY a.tmonth		                                ");

        return getJdbcTemplate().queryForList(query.toString());
    }
}