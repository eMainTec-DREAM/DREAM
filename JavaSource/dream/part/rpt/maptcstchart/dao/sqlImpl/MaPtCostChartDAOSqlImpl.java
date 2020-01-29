package dream.part.rpt.maptcstchart.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.rpt.maptcstchart.dao.MaPtCostChartDAO;
import dream.part.rpt.maptcstchart.dto.MaPtCostChartDTO;

/**
 * 자재비용분석 DAO
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maPtCostChartDAOTarget"
 * @spring.txbn id="maPtCostChartDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtCostChartDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtCostChartDAO
{
    /**
     * 과별종합분석 find grid
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findList(MaPtCostChartDTO maPtCostChartDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String yyyy = maPtCostChartDTO.getFilterYyyy();
        
        query.append("SELECT deptId, ");
        query.append("       dbo.SFAIDTODESC(deptId, '', 'DEPT', compNo) deptDesc, ");
        for (int i = 1; i <= 12; i++) 
        {
            String month = (i<10?"0"+i:i+"");
        query.append("       SUM(CASE WHEN rmonth = '"+yyyy+month+"' AND kind='REC'    THEN totPrice ELSE 0 END)   AS REC"+month+",     ");
        query.append("       SUM(CASE WHEN rmonth = '"+yyyy+month+"' AND kind='REPAIR' THEN totPrice ELSE 0 END)   AS REPAIR"+month+",  ");
        query.append("       SUM(CASE WHEN rmonth = '"+yyyy+month+"' THEN totPrice ELSE 0 END)                     AS TOT"+month+",     ");
        }
        query.append("       SUM(CASE kind WHEN 'REC' THEN totPrice ELSE 0 END)    AS RECTOT,    ");
        query.append("       SUM(CASE kind WHEN 'REPAIR' THEN totPrice ELSE 0 END) AS REPAIRTOT, ");
        query.append("       SUM(totPrice)                            AS TOTAL      ");
        query.append("FROM (                                                    	");
        query.append("       SELECT x.comp_no                  AS compNo,       	");
        query.append("              x.dept_id+''              AS deptId,       		");
        query.append("              SUBSTRING(x.rec_date,1,6)     AS rmonth,       	");
        query.append("              SUM(ISNULL(x.tot_price, 0))   AS totPrice,     	");
        query.append("              'REC'                      AS kind          	");
        query.append("       FROM   TAPTPRRECLIST x                             	");
        query.append("       WHERE  1=1                                         	");
        query.append("       	and x.prreclist_status = 'C'  --완료된것만               	");
        query.append(this.getRecWhere(maPtCostChartDTO));
        query.append("       GROUP BY comp_no, dept_id, SUBSTRING(x.rec_date,1,6)  	");
        query.append("       UNION ALL ");
        query.append("       SELECT x.comp_no                  AS compNo,       	");
        query.append("              x.dept_id+''              AS deptId,       		");
        query.append("              SUBSTRING(x.repair_date,1,6)  AS rmonth,       	");
        query.append("              SUM(ISNULL(x.tot_price, 0))   AS totPrice,     	");
        query.append("              'REPAIR'                   AS kind          	");
        query.append("       FROM   TAPTREPAIRLIST x                            	");
        query.append("       WHERE  1=1                                         	");
        query.append("       	and x.ptrepairlist_status = 'V'   --외주수리만       	");
        query.append(this.getRepairWhere(maPtCostChartDTO));
        query.append("       GROUP BY comp_no, dept_id, SUBSTRING(x.repair_date,1,6)");
        query.append(") pc 															");
        query.append("GROUP BY compNo, deptId                                   	");
        query.append("ORDER BY deptId                                           	");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 입고금액 find chart
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findRecChart(MaPtCostChartDTO maPtCostChartDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String yyyy   = maPtCostChartDTO.getYyyy().substring(0, 4);
        String lastYyyy  = (Integer.parseInt(maPtCostChartDTO.getYyyy().substring(0, 4)) - 1) + "";
        String lang   = maPtCostChartDTO.getUserLang();
        String deptId = maPtCostChartDTO.getDeptId();
        
        query.append("SELECT 																		");
        query.append("		ROW_NUMBER() OVER(ORDER BY tmonth) 					AS	ID        		");
        query.append("		,y.tmonth + (SELECT key_name  											");
        query.append("					 FROM TALANG                  								");
        query.append("             		 WHERE lang='"+lang+"' 										");
        query.append("					  AND key_no='month') 					AS	MONTH			");
        query.append("		,SUM(ISNULL(x.totPrice,0))     						AS	totPrice		");
        query.append("		,SUM(ISNULL(x.lastTotPrice,0))     					AS	lastTotPrice	");
        query.append("FROM (                                                						");
        query.append("       SELECT 																");
        query.append("			x.comp_no                  						AS compNo   		");
        query.append("          ,x.dept_id+''              						AS deptId  			");
        query.append("          ,SUBSTRING(x.rec_date,5,2)     					AS rmonth  			");
        query.append("          ,SUBSTRING(x.rec_date,1,6)  					AS xmonth  			");
        query.append("          ,CASE WHEN SUBSTRING(x.rec_date,1,4)='"+yyyy+"'          			");
        query.append("          	THEN SUM(ISNULL(x.tot_price,0)) ELSE 0 END	AS totPrice			");
        query.append("          ,CASE WHEN SUBSTRING(x.rec_date,1,4)='"+lastYyyy+"'      			");
        query.append("     		 	THEN SUM(ISNULL(x.tot_price,0)) ELSE 0 END	AS lastTotPrice		");
        query.append("       FROM   TAPTPRRECLIST x                             					");
        query.append("       WHERE  1=1                                         					");
        query.append("        and x.prreclist_status = 'C'  --완료된것만               						");
        query.append(this.getWhereChart(maPtCostChartDTO,"RecChart"));
        if(!"".equals(deptId)){
        	query.getAndQuery("x.dept_id", deptId);
        }
        query.append("       GROUP BY comp_no,dept_id,SUBSTRING(x.rec_date,1,4),SUBSTRING(x.rec_date,1,6),SUBSTRING(x.rec_date,5,2)					");
        query.append(")x RIGHT OUTER JOIN (SELECT DISTINCT SUBSTRING(tmonth,5,2) tmonth FROM TADAY WHERE tyyyy in ('"+lastYyyy+"', '"+yyyy+"'))y	");
        query.append("ON x.rmonth = y.tmonth                              							");
        query.append("GROUP BY tmonth                              									");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 수리금액 find chart
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findRepairChart(MaPtCostChartDTO maPtCostChartDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String yyyy   = maPtCostChartDTO.getYyyy().substring(0, 4);
        String lastYyyy  = (Integer.parseInt(maPtCostChartDTO.getYyyy().substring(0, 4)) - 1) + "";
        String lang   = maPtCostChartDTO.getUserLang();
        String deptId = maPtCostChartDTO.getDeptId();
        
        query.append("SELECT 																		");
        query.append("		ROW_NUMBER() OVER(ORDER BY tmonth) 					AS	ID        		");
        query.append("		,y.tmonth + (SELECT key_name  											");
        query.append("					 FROM TALANG                 						 		");
        query.append("             	   	 WHERE lang='"+lang+"' 										");
        query.append("					  AND key_no='month') 					AS 	month 			");
        query.append("		,SUM(ISNULL(x.totPrice,0))     						AS	totPrice		");
        query.append("		,SUM(ISNULL(x.lastTotPrice,0))     					AS	lastTotPrice	");
        query.append("FROM (                                                    					");
        query.append("      SELECT  																");
        query.append("			x.comp_no                  						AS compNo       	");
        query.append("			,x.dept_id+''              						AS deptId       	");
        query.append("          ,SUBSTRING(x.repair_date,5,2)     				AS rmonth  			");
        query.append("          ,SUBSTRING(x.repair_date,1,6)  					AS xmonth  			");
        query.append("          ,CASE WHEN SUBSTRING(x.repair_date,1,4)='"+yyyy+"'					");
        query.append("          	THEN SUM(ISNULL(x.tot_price,0)) ELSE 0 END	AS totPrice			");
        query.append("          ,CASE WHEN SUBSTRING(x.repair_date,1,4)='"+lastYyyy+"'				");
        query.append("          	THEN SUM(ISNULL(x.tot_price,0)) ELSE 0 END	AS lastTotPrice		");
        query.append("       FROM   TAPTREPAIRLIST x                            					");
        query.append("       WHERE  1=1                                         					");
        query.append("        and x.ptrepairlist_status = 'V'   --외주수리만      		 				");
        query.append(this.getWhereChart(maPtCostChartDTO,"RepairChart"));
        if(!"".equals(deptId)){
        	query.getAndQuery("x.dept_id", deptId);
        }
        query.append("       GROUP BY comp_no,dept_id,SUBSTRING(x.repair_date,1,4),SUBSTRING(x.repair_date,1,6),SUBSTRING(x.repair_date,5,2)		");
        query.append(")x RIGHT OUTER JOIN (SELECT DISTINCT SUBSTRING(tmonth,5,2) tmonth FROM TADAY WHERE tyyyy in ('"+lastYyyy+"', '"+yyyy+"'))y	");
        query.append("ON x.rmonth = y.tmonth                              							");
        query.append("GROUP BY tmonth                              									");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 월별 합계 find chart
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return List
     */
    public List findMonthTotalChart(MaPtCostChartDTO maPtCostChartDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String yyyy   = maPtCostChartDTO.getYyyy().substring(0, 4);
        String lastYyyy = (Integer.parseInt(maPtCostChartDTO.getYyyy().substring(0, 4)) - 1) + "";
        String lang   = maPtCostChartDTO.getUserLang();
        String deptId = maPtCostChartDTO.getDeptId();
        
        query.append("SELECT 									          							");
        query.append("		ROW_NUMBER() OVER(ORDER BY tmonth) 					AS	id				");
        query.append("		,y.tmonth + (SELECT key_name                   							");
        query.append("             		 FROM TALANG                  								");
        query.append("             		 WHERE lang='"+lang+"' 										");
        query.append("             		  AND key_no='month') 					AS 	month 			");
        query.append("		,SUM(ISNULL(x.totPrice,0))     						AS	totPrice		");
        query.append("		,SUM(ISNULL(x.lastTotPrice,0))     					AS	lastTotPrice	");
        query.append("FROM (                                                  			  			");
        query.append("       SELECT   																");
        query.append("		 	x.comp_no										AS compNo       	");
        query.append("          ,x.dept_id+''              						AS deptId       	");
        query.append("          ,SUBSTRING(x.rec_date,5,2)    					AS rmonth       	");
        query.append("          ,SUBSTRING(x.rec_date,1,6)     					AS xmonth       	");
        query.append("    		,CASE WHEN SUBSTRING(x.rec_date,1,4)='"+yyyy+"'        	   			");
        query.append("          	THEN SUM(ISNULL(x.tot_price,0)) ELSE 0 END	AS totPrice   	 	");
        query.append("         	,CASE WHEN SUBSTRING(x.rec_date,1,4)='"+lastYyyy+"'          		");
        query.append("             	THEN SUM(ISNULL(x.tot_price,0)) ELSE 0 END	AS lastTotPrice		");
        query.append("       FROM   TAPTPRRECLIST x                             					");
        query.append("       WHERE  1=1                                         					");
        query.append("        and x.prreclist_status = 'C'  --완료된것만               						");
        query.append(this.getWhereChart(maPtCostChartDTO,"RecChart"));
        if(!"".equals(deptId)){
        	query.getAndQuery("x.dept_id", deptId);
            
        }
        query.append("       GROUP BY comp_no, dept_id,SUBSTRING(x.rec_date,1,4), SUBSTRING(x.rec_date,1,6), SUBSTRING(x.rec_date,5,2)  ");
        query.append("       UNION ALL                                          					");
        query.append("       SELECT    																");
        query.append("		 	x.comp_no                  						AS compNo       	");
        query.append("          ,x.dept_id+''              						AS deptId       	");
        query.append("          ,SUBSTRING(x.repair_date,5,2)     				AS rmonth    	  	");
        query.append("          ,SUBSTRING(x.repair_date,1,6)     				AS xmonth      		");
        query.append("          ,CASE WHEN SUBSTRING(x.repair_date,1,4)='"+yyyy+"'        			");
        query.append("          	THEN SUM(ISNULL(x.tot_price,0)) ELSE 0 END	AS totPrice    		");
        query.append("          ,CASE WHEN SUBSTRING(x.repair_date,1,4)='"+lastYyyy+"'     			");
        query.append("          	THEN SUM(ISNULL(x.tot_price,0)) ELSE 0 END	AS lastTotPrice		");
        query.append("       FROM   TAPTREPAIRLIST x                            					");
        query.append("       WHERE  1=1                                         					");
        query.append("        and x.ptrepairlist_status = 'V'   --외주수리만        						");
        query.append(this.getWhereChart(maPtCostChartDTO,"RepairChart"));
        if(!"".equals(deptId)){
        	query.getAndQuery("x.dept_id", deptId);
        }
        query.append("       GROUP BY comp_no, dept_id,SUBSTRING(x.repair_date,1,4),SUBSTRING(x.repair_date,1,6),SUBSTRING(x.repair_date,5,2)		");
        query.append(")x RIGHT OUTER JOIN (SELECT DISTINCT SUBSTRING(tmonth,5,2) tmonth FROM TADAY WHERE tyyyy IN('"+lastYyyy+"', '"+yyyy+"')) y	");
        query.append("	ON x.rmonth = y.tmonth                              						");
        query.append("GROUP BY tmonth                                           					");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtCostChartDTO
     * @return
     * @throws Exception
     */
    private String getRecWhere(MaPtCostChartDTO maPtCostChartDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", maPtCostChartDTO.getCompNo());
        query.getAndDateQuery("x.rec_date", maPtCostChartDTO.getFilterYyyy()+"0101", maPtCostChartDTO.getFilterYyyy()+"1231");
        //담당부서
        query.getDeptLevelQuery("x.dept_id", maPtCostChartDTO.getFilterDeptId(),maPtCostChartDTO.getFilterDeptDesc(), maPtCostChartDTO.getCompNo());

    	//공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        		maPtCostChartDTO.getFilterPlantId(), maPtCostChartDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    private String getRepairWhere(MaPtCostChartDTO maPtCostChartDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", maPtCostChartDTO.getCompNo());
        query.getAndDateQuery("x.repair_date", maPtCostChartDTO.getFilterYyyy()+"0101", maPtCostChartDTO.getFilterYyyy()+"1231");
        //담당부서
        query.getDeptLevelQuery("x.dept_id", maPtCostChartDTO.getFilterDeptId(),maPtCostChartDTO.getFilterDeptDesc(), maPtCostChartDTO.getCompNo());

    	//공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        		maPtCostChartDTO.getFilterPlantId(), maPtCostChartDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    private String getWhereChart(MaPtCostChartDTO maPtCostChartDTO, String chart)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        // 회사
    	query.getAndQuery("x.comp_no", maPtCostChartDTO.getCompNo());
    	// 이전년도
    	String lastYyyy = (Integer.parseInt(maPtCostChartDTO.getYyyy().substring(0, 4)) - 1) + "";
    	//일자
    	if(chart == "RecChart" || chart.equals("RecChart")){
        	query.getAndDateQuery("x.rec_date", lastYyyy+"0101", maPtCostChartDTO.getFilterYyyy()+"1231");
    	}
    	else if(chart == "RepairChart" || chart.equals("RepairChart")){
            query.getAndDateQuery("x.repair_date", lastYyyy+"0101", maPtCostChartDTO.getFilterYyyy()+"1231");
    	}
    	//공장코드
    	query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
    			maPtCostChartDTO.getFilterPlantId(), maPtCostChartDTO.getFilterPlantDesc());
    	
    	return query.toString();
    }
    
}