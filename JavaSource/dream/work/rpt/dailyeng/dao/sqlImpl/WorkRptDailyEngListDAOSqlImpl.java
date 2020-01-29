package dream.work.rpt.dailyeng.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.dailyeng.dao.WorkRptDailyEngListDAO;
import dream.work.rpt.dailyeng.dto.WorkRptDailyEngCommonDTO;
import dream.work.rpt.dailyeng.form.WorkRptDailyEngListForm;

/**
 * 에너지사용량(일별) 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptDailyEngListDAOTarget"
 * @spring.txbn id="workRptDailyEngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptDailyEngListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptDailyEngListDAO
{
    public List findPlantList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT											");
        query.append("    '' 							seqNo 			");
        query.append("    , y.plant           			PLANTID			");
        query.append("    , y.plantDesc					PLANTDESC		");
        query.append("    , ''                  		USAGEDEPTID		");
        query.append("    , ''                  		EQLOCID			");
        query.append("    , ''                  		EQUIPID			");
        query.append("    , ''                  		USAGEDEPTDESC	");
        query.append("    , ''                  		EQLOCDESC		");
        query.append("    , ''                  		EQUIPDESC		");
    	query.append("    , ''					        EQCTGID			");
        query.append("    , ''                  		EQCTGDESC		");
        query.append("    , ''                  		ITEMNO			");
        query.append("    , ROUND(SUM(y.usageAmt),2)	USAGEAMT		");
        query.append("FROM												");
        query.append("(													");
        query.append("    SELECT 										");
        query.append("        x.femsusage_id							");
        query.append("      , x.plant                            		");
        query.append("      , x.eqloc_id								");
        query.append("      , x.usage_dept        						");
    	query.append("      , x.eqctg_id								");
        query.append("      , x.equip_id        						");
        query.append("      , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant)             plantDesc		");
        query.append("      , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = x.eqloc_id)         eqlocDesc		");
        query.append("      , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.usage_dept)       usageDeptDesc	");
        query.append("      , (SELECT a.description FROM TAEQCTG a WHERE a.comp_no = x.comp_no AND a.eqctg_id = x.eqctg_id)       eqCtgDesc		");
        query.append("      , (SELECT a.item_no FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)       itemNo		");
        query.append("      , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)   equipDesc		");
        query.append("      , x.usageAmt																						  usageAmt		");
        query.append("    FROM 													");
        query.append("		(													");
        query.append("    		SELECT 											");
        query.append("                x.femsusage_id							");
        query.append("              , x.comp_no  								");
        query.append("              , y.plant									");
        query.append("              , x.equip_id								");
        query.append("              , y.eqloc_id								");
        query.append("              , y.usage_dept								");
        query.append("              , y.eqctg_id								");
        query.append("              , SUM(x.USAGE) 			usageAmt			");
        query.append("            FROM TAFEMSUSAGE x INNER JOIN TAEQUIPMENT y	");
        query.append("            ON x.comp_no = y.comp_no 						");
        query.append("            AND x.equip_id = y.equip_id					");
        query.append("            WHERE 1=1										");
        query.append(this.getWhere(workRptDailyEngListForm,loginUser));
        query.append("            GROUP BY x.femsusage_id, x.comp_no, x.equip_id , y.plant, y.eqloc_id, y.eqctg_id, y.usage_dept		");
        query.append("    	) x													");
        query.append("	) y														");
        query.append("WHERE 1=1													");
        query.append("AND y.plantDesc 	  IS NOT NULL							");
        query.append("GROUP BY y.plant, y.plantDesc								");
        query.append("ORDER BY y.plant, y.plantDesc 					        ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }

    public List findEqLocList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT											");
        query.append("    '' 							seqNo 			");
        query.append("    , y.plant           			PLANTID			");
        query.append("    , y.plantDesc					PLANTDESC		");
        query.append("    , y.eqloc_id                  EQLOCID			");
        query.append("    , y.eqlocDesc              	EQLOCDESC		");
        query.append("    , ''          			   	USAGEDEPTID		");
        query.append("    , ''							USAGEDEPTDESC	");
        query.append("    , ''                  		EQUIPID			");
        query.append("    , ''              			EQUIPDESC		");
    	query.append("    , ''					        EQCTGID			");
        query.append("    , ''              			EQCTGDESC		");
        query.append("    , ''              			ITEMNO			");
        query.append("    , ROUND(SUM(y.usageAmt),2)	USAGEAMT		");
        query.append("FROM												");
        query.append("(													");
        query.append("    SELECT 										");
        query.append("        x.femsusage_id							");
        query.append("      , x.plant                            		");
        query.append("      , x.eqloc_id								");
        query.append("      , x.usage_dept        						");
    	query.append("      , x.eqctg_id								");
        query.append("      , x.equip_id        						");
        query.append("      , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant)             plantDesc		");
        query.append("      , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = x.eqloc_id)         eqlocDesc		");
        query.append("      , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.usage_dept)       usageDeptDesc	");
        query.append("      , (SELECT a.description FROM TAEQCTG a WHERE a.comp_no = x.comp_no AND a.eqctg_id = x.eqctg_id)       eqCtgDesc		");
        query.append("      , (SELECT a.item_no FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)       itemNo		");
        query.append("      , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)   equipDesc		");
        query.append("      , x.usageAmt																						  usageAmt		");
        query.append("    FROM 													");
        query.append("		(													");
        query.append("    		SELECT 											");
        query.append("                x.femsusage_id							");
        query.append("              , x.comp_no  								");
        query.append("              , y.plant									");
        query.append("              , x.equip_id								");
        query.append("              , y.eqloc_id								");
        query.append("              , y.usage_dept								");
        query.append("              , y.eqctg_id								");
        query.append("              , SUM(x.USAGE) 			usageAmt			");
        query.append("            FROM TAFEMSUSAGE x INNER JOIN TAEQUIPMENT y	");
        query.append("            ON x.comp_no = y.comp_no 						");
        query.append("            AND x.equip_id = y.equip_id					");
        query.append("            WHERE 1=1										");
        query.append(this.getWhere(workRptDailyEngListForm,loginUser));
        query.append("            GROUP BY x.femsusage_id, x.comp_no, x.equip_id , y.plant, y.eqloc_id, y.eqctg_id, y.usage_dept		");
        query.append("    	) x													");
        query.append("	) y														");
        query.append("WHERE 1=1													");
        query.append("AND y.plant 	     IS NOT NULL							");
        query.append("AND y.eqloc_id     IS NOT NULL							");
        query.append("GROUP BY y.plant, y.plantDesc, y.eqloc_id, y.eqlocDesc 	");
        query.append("ORDER BY y.plant, y.plantDesc, y.eqloc_id, y.eqlocDesc 	");
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }

    public List findUsageDeptList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT											");
        query.append("    '' 							seqNo 			");
        query.append("    , MAX(y.femsusage_id)			FEMSUSAGEID 	");
        query.append("    , y.plant           			PLANTID			");
        query.append("    , y.plantDesc					PLANTDESC		");
        query.append("    , y.eqloc_id					EQLOCID			");
        query.append("    , y.eqlocDesc					EQLOCDESC		");
        query.append("    , Y.usage_dept             	USAGEDEPTID		");
        query.append("    , y.usageDeptDesc				USAGEDEPTDESC	");
        query.append("    , ''              			EQUIPDESC		");
    	query.append("    , ''					        EQCTGID			");
        query.append("    , ''              			EQCTGDESC		");
        query.append("    , ''              			ITEMNO			");
        query.append("    , ROUND(SUM(y.usageAmt),2)	USAGEAMT		");
        query.append("FROM												");
        query.append("(													");
        query.append("    SELECT 										");
        query.append("        x.femsusage_id							");
        query.append("      , x.plant                            		");
        query.append("      , x.eqloc_id								");
        query.append("      , x.usage_dept        						");
    	query.append("      , x.eqctg_id								");
        query.append("      , x.equip_id        						");
        query.append("      , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant)             plantDesc		");
        query.append("      , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = x.eqloc_id)         eqlocDesc		");
        query.append("      , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.usage_dept)       usageDeptDesc	");
        query.append("      , (SELECT a.description FROM TAEQCTG a WHERE a.comp_no = x.comp_no AND a.eqctg_id = x.eqctg_id)       eqCtgDesc		");
        query.append("      , (SELECT a.item_no FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)       itemNo		");
        query.append("      , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)   equipDesc		");
        query.append("      , x.usageAmt																						  usageAmt		");
        query.append("    FROM 													");
        query.append("		(													");
        query.append("    		SELECT 											");
        query.append("                x.femsusage_id							");
        query.append("              , x.comp_no  								");
        query.append("              , y.plant									");
        query.append("              , x.equip_id								");
        query.append("              , y.eqloc_id								");
        query.append("              , y.usage_dept								");
        query.append("              , y.eqctg_id								");
        query.append("              , SUM(x.USAGE) 			usageAmt			");
        query.append("            FROM TAFEMSUSAGE x INNER JOIN TAEQUIPMENT y	");
        query.append("            ON x.comp_no = y.comp_no 						");
        query.append("            AND x.equip_id = y.equip_id					");
        query.append("            WHERE 1=1										");
        query.append(this.getWhere(workRptDailyEngListForm,loginUser));
        query.append("            GROUP BY x.femsusage_id, x.comp_no, x.equip_id , y.plant, y.eqloc_id, y.eqctg_id, y.usage_dept		");
        query.append("    	) x													");
        query.append("	) y														");
        query.append("WHERE 1=1													");
        query.append("AND y.plant 	     IS NOT NULL							");
        query.append("AND y.eqloc_id     IS NOT NULL							");
        query.append("AND y.usage_dept	 IS NOT NULL							");
        query.append("GROUP BY y.plant, y.plantDesc, y.eqloc_id, y.eqlocDesc, y.usage_dept, y.usageDeptDesc ");
        query.append("ORDER BY y.plant, y.plantDesc, y.eqloc_id, y.eqlocDesc, y.usage_dept, y.usageDeptDesc ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqCtgList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT							        				        ");
    	query.append("    '' 							        seqNo 			        ");
    	query.append("    , MAX(y.femsusage_id)			        FEMSUSAGEID 	        ");
    	query.append("    , y.plant           			        PLANTID			        ");
    	query.append("    , y.plantDesc					        PLANTDESC		        ");
    	query.append("    , Y.usage_dept             	        USAGEDEPTID		        ");
    	query.append("    , y.usageDeptDesc				        USAGEDEPTDESC	        ");
    	query.append("    , y.eqloc_id					        EQLOCID			        ");
    	query.append("    , y.eqlocDesc					        EQLOCDESC		        ");
    	query.append("    , y.eqctg_id					        EQCTGID			        ");
    	query.append("    , y.eqCtgDesc        			        EQCTGDESC		        ");
    	query.append("    , ''              			        EQUIPDESC		        ");
    	query.append("    , ''              			        ITEMNO			        ");
        query.append("    , ROUND(SUM(y.usageAmt),2)	USAGEAMT		");
    	query.append("FROM								        				        ");
    	query.append("(									        				        ");
    	query.append("    SELECT 						        				        ");
    	query.append("        x.femsusage_id			        				        ");
    	query.append("      , x.plant                                    		        ");
    	query.append("      , x.eqloc_id				        				        ");
    	query.append("      , x.usage_dept        		        						");
    	query.append("      , x.eqctg_id				        						");
    	query.append("      , x.equip_id        		        						");
    	query.append("      , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant)             plantDesc		");
    	query.append("      , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = x.eqloc_id)         eqlocDesc		");
    	query.append("      , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.usage_dept)       usageDeptDesc	");
    	query.append("      , (SELECT a.description FROM TAEQCTG a WHERE a.comp_no = x.comp_no AND a.eqctg_id = x.eqctg_id)       eqCtgDesc		");
    	query.append("      , (SELECT a.item_no FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)       itemNo		");
    	query.append("      , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)   equipDesc		");
    	query.append("      , x.usageAmt																						  usageAmt		");
    	query.append("    FROM 															");
    	query.append("		(															");
    	query.append("    		SELECT 													");
    	query.append("                x.femsusage_id									");
    	query.append("              , x.comp_no  										");
    	query.append("              , y.plant											");
    	query.append("              , x.equip_id										");
    	query.append("              , y.eqloc_id										");
    	query.append("              , y.usage_dept										");
    	query.append("              , y.eqctg_id										");
    	query.append("              , SUM(x.USAGE) 			usageAmt					");
    	query.append("            FROM TAFEMSUSAGE x INNER JOIN TAEQUIPMENT y			");
    	query.append("            ON x.comp_no = y.comp_no 								");
    	query.append("            AND x.equip_id = y.equip_id							");
    	query.append("            WHERE 1=1												");
    	query.append(this.getWhere(workRptDailyEngListForm,loginUser));
    	query.append("            GROUP BY x.femsusage_id, x.comp_no, x.equip_id , y.plant, y.eqloc_id, y.eqctg_id, y.usage_dept		");
    	query.append("    	) x															");
    	query.append("	) y																");
    	query.append("WHERE 1=1															");
        query.append("AND y.plant 	     IS NOT NULL									");
        query.append("AND y.eqloc_id     IS NOT NULL									");
        query.append("AND y.usage_dept	 IS NOT NULL									");
        query.append("AND y.eqctg_id     IS NOT NULL									");
        query.append("GROUP BY y.plant, y.plantDesc, y.eqloc_id, y.eqlocDesc, y.usage_dept, y.usageDeptDesc, y.eqctg_id, y.eqCtgDesc ");
        query.append("ORDER BY y.plant, y.plantDesc, y.eqloc_id, y.eqlocDesc, y.usage_dept, y.usageDeptDesc, y.eqctg_id, y.eqCtgDesc ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    	
    }

    public List findEqpList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT							        				        ");
        query.append("    '' 							        seqNo 			        ");
        query.append("    , y.plant           					PLANTID					");
        query.append("    , y.plantDesc							PLANTDESC				");
        query.append("    , Y.usage_dept             			USAGEDEPTID				");
        query.append("    , y.usageDeptDesc						USAGEDEPTDESC			");
        query.append("    , y.eqloc_id							EQLOCID					");
        query.append("    , y.eqlocDesc							EQLOCDESC				");
        query.append("    , y.equip_id							EQUIPID					");
        query.append("    , y.equipDesc					        EQUIPDESC		        ");
    	query.append("    , y.eqctg_id					        EQCTGID			        ");
        query.append("    , y.eqCtgDesc		                    EQCTGDESC               ");
        query.append("    , MAX(y.itemNo)                       ITEMNO					");
        query.append("    , ROUND(SUM(y.usageAmt),2)			USAGEAMT				");
        query.append("FROM								        				        ");
        query.append("(									        				        ");
        query.append("    SELECT 						        				        ");
        query.append("        x.femsusage_id			        				        ");
        query.append("      , x.plant                            						");
        query.append("      , x.eqloc_id												");
        query.append("      , x.usage_dept        										");
    	query.append("      , x.eqctg_id				        						");
        query.append("      , x.equip_id        										");
        query.append("      , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant)             plantDesc		");
        query.append("      , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = x.eqloc_id)         eqlocDesc		");
        query.append("      , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.usage_dept)       usageDeptDesc	");
        query.append("      , (SELECT a.description FROM TAEQCTG a WHERE a.comp_no = x.comp_no AND a.eqctg_id = x.eqctg_id)       eqCtgDesc		");
        query.append("      , (SELECT a.item_no FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)       itemNo		");
        query.append("      , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)   equipDesc		");
        query.append("      , x.usageAmt																						  usageAmt		");
        query.append("    FROM 													        ");
        query.append("		(													        ");
        query.append("    		SELECT 											        ");
        query.append("                x.femsusage_id							        ");
        query.append("              , x.comp_no  								        ");
        query.append("              , y.plant									        ");
        query.append("              , x.equip_id								        ");
        query.append("              , y.eqloc_id								        ");
        query.append("              , y.usage_dept								        ");
        query.append("              , y.eqctg_id								        ");
        query.append("              , SUM(x.USAGE) 			usageAmt			        ");
        query.append("            FROM TAFEMSUSAGE x INNER JOIN TAEQUIPMENT y	        ");
        query.append("            ON x.comp_no = y.comp_no 						        ");
        query.append("            AND x.equip_id = y.equip_id					        ");
        query.append("            WHERE 1=1										        ");
        query.append(this.getWhere(workRptDailyEngListForm,loginUser));
        query.append("            GROUP BY x.femsusage_id, x.comp_no, x.equip_id , y.plant, y.eqloc_id, y.eqctg_id, y.usage_dept		");
        query.append("    	) x															");
        query.append("	) y																");
        query.append("WHERE 1=1															");
        query.append("AND y.plant 	     IS NOT NULL									");
        query.append("AND y.eqloc_id     IS NOT NULL									");
        query.append("AND y.usage_dept	 IS NOT NULL									");
        query.append("AND y.eqctg_id     IS NOT NULL									");
        query.append("AND y.equip_id     IS NOT NULL									");
        query.append("GROUP BY y.plant, y.plantDesc, y.eqloc_id, y.eqlocDesc, y.usage_dept, y.usageDeptDesc, y.eqctg_id, y.eqCtgDesc, y.equip_id, y.equipDesc ");
        query.append("ORDER BY y.plant, y.plantDesc, y.eqloc_id, y.eqlocDesc, y.usage_dept, y.usageDeptDesc, y.eqctg_id, y.eqCtgDesc, y.equip_id, y.equipDesc ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }
    

    public String getWhere(WorkRptDailyEngListForm workRptDailyEngListForm,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        WorkRptDailyEngCommonDTO workRptDailyEngCommonDTO = workRptDailyEngListForm.getWorkRptDailyEngCommonDTO();
        
        String fromDate = CommonUtil.dateToData(workRptDailyEngCommonDTO.getFilterStartDate());
        String toDate   = CommonUtil.dateToData(workRptDailyEngCommonDTO.getFilterEndDate());
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND x.usage_date >= '"+fromDate+"'     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND x.usage_date <= '"+toDate+"'       ");
        }        

        // 공장
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.plant = y.plant )", 
        		workRptDailyEngCommonDTO.getFilterPlant(), workRptDailyEngCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }
}