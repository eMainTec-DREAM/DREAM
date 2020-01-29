package dream.work.rpt.mawodaily.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.mawodaily.dao.MaWoDailyChartDAO;
import dream.work.rpt.mawodaily.dto.MaWoDailyChartDTO;

/**
 * 일별작업실행율 DAO
 * @author  kim21017
 * @version $Id: MaWoDailyChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoDailyChartDAOTarget"
 * @spring.txbn id="maWoDailyChartDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoDailyChartDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoDailyChartDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoDailyChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoDailyChartDTO
     * @return List
     */
    public List findWoList(MaWoDailyChartDTO maWoDailyChartDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String[] dateArr = maWoDailyChartDTO.getDateArrStr().split(",");
        String[] typeArr = {"P","C","R"};
        String compNo = maWoDailyChartDTO.getCompNo();
        
        query.append("SELECT	a.dept_id AS ID									");
        query.append("			,da.lvl LVL										");
        query.append("			,MIN(da.lvl) OVER( ORDER BY a.ord_no) AS MINLVL	");
        query.append("			,a.dept_id AS DEPTID							");
        query.append("			,a.p_dept_id AS PDEPTID							");
        query.append("			,a.description AS DEPTDESC						");
        for (int i = 1; i < dateArr.length; i++) {
        	String date = CommonUtil.convertDate(dateArr[i]);
			for (int j = 0; j < 3; j++) {
				if(j<2){
					query.append(",(SELECT ISNULL(SUM(tb."+typeArr[j]+date+"),0)  									");
					query.append("    FROM (SELECT td.dept_id , td.p_dept_id										");
					query.append("	       , tdw."+typeArr[j]+date+" FROM TADEPT td LEFT OUTER JOIN					");
					query.append("			(SELECT    x.dept_id,x.p_dept_id, x.comp_no								");
					switch (j) {
					case 0:
						query.append(",SUM(CASE y.start_date WHEN '"+date+"' THEN 1 ELSE 0 END) AS \""+typeArr[0]+date+"\"	");
			        	
						break;
					case 1:
						query.append(",SUM(CASE y.start_date WHEN '"+date+"'										");
			        	query.append("			THEN (CASE y.wo_status WHEN 'C' THEN 1 ELSE 0 END) ELSE 0 END)	AS \""+typeArr[1]+date+"\"	");
						break;
					}
					query.append("			  FROM TADEPT x, TAWORKORDER y												");
					query.append("			 WHERE x.comp_no = y.comp_no 												");
					query.append("   		   AND x.dept_id = y.dept_id 												");
					query.getAndDateQuery("y.start_date", maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
			        query.getAndQuery("x.comp_no", compNo);
			        query.append(" 			 GROUP BY x.comp_no, x.dept_id,x.p_dept_id) tdw								");
			        query.append("	  ON td.comp_no = tdw.comp_no														");
					query.append("   AND td.dept_id =tdw.dept_id														");
					query.append("WHERE 1=1													");
					query.getAndQuery("td.comp_no", compNo);
			        query.append("	) tb																				");
					query.append("WHERE tb.dept_id IN (SELECT dept_id FROM dbo.SFADEPT_CALL('"+compNo+"',a.dept_id,''))	");
					query.append(")	AS \""+typeArr[j]+dateArr[i]+"\"													");
				}else{
					query.append(",ROUND(ISNULL(CONVERT(FLOAT,(SELECT ISNULL(SUM(tb."+typeArr[1]+date+"),0)  		");
					query.append("								 FROM (SELECT td.dept_id , td.p_dept_id				");
					query.append("	, tdw."+typeArr[1]+date+" FROM TADEPT td LEFT OUTER JOIN						");
					query.append("		(SELECT    x.dept_id,x.p_dept_id, x.comp_no									");
					query.append(",SUM(CASE y.start_date WHEN '"+date+"'											");
		        	query.append("			THEN (CASE y.wo_status WHEN 'C' THEN 1 ELSE 0 END) ELSE 0 END)	AS \""+typeArr[1]+date+"\"	");
					query.append("FROM TADEPT x, TAWORKORDER y														");
					query.append("WHERE x.comp_no = y.comp_no 														");
					query.append("   AND x.dept_id = y.dept_id 														");
					query.getAndDateQuery("y.start_date", maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
			        query.getAndQuery("x.comp_no", compNo);
			        query.append("GROUP BY x.comp_no, x.dept_id,x.p_dept_id) tdw									");
			        query.append("ON td.comp_no = tdw.comp_no														");
					query.append("AND td.dept_id =tdw.dept_id														");
					query.append("WHERE 1=1													");
					query.getAndQuery("td.comp_no", compNo);
			        query.append(") tb																				");
			        query.append("WHERE tb.dept_id IN (SELECT dept_id FROM dbo.SFADEPT_CALL('"+compNo+"',a.dept_id,''))	");
					query.append("))/																				");
					query.append("(CASE (SELECT ISNULL(SUM(tb."+typeArr[0]+date+"),0) FROM 							");
					query.append("(SELECT td.dept_id , td.p_dept_id													");
					query.append("	, tdw."+typeArr[0]+date+" FROM TADEPT td LEFT OUTER JOIN						");
					query.append("		(SELECT    x.dept_id,x.p_dept_id, x.comp_no									");
					query.append(",SUM(CASE y.start_date WHEN '"+date+"' THEN 1 ELSE 0 END) AS \""+typeArr[0]+date+"\"	");
					query.append("FROM TADEPT x, TAWORKORDER y														");
					query.append("WHERE x.comp_no = y.comp_no 														");
					query.append("   AND x.dept_id = y.dept_id 														");
					query.getAndDateQuery("y.start_date", maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
			        query.getAndQuery("x.comp_no", compNo);
			        query.append("GROUP BY x.comp_no, x.dept_id,x.p_dept_id) tdw									");
			        query.append("ON td.comp_no = tdw.comp_no														");
					query.append("AND td.dept_id =tdw.dept_id														");
					query.append("WHERE 1=1													");
					query.getAndQuery("td.comp_no", maWoDailyChartDTO.getCompNo());
			        query.append(") tb																				");
			        query.append("WHERE tb.dept_id IN (SELECT dept_id FROM dbo.SFADEPT_CALL('"+compNo+"',a.dept_id,''))	");
					query.append(") WHEN 0 THEN NULL ELSE															");
					query.append("(SELECT ISNULL(SUM(tb."+typeArr[0]+date+"),0) FROM 								");
					query.append("(SELECT td.dept_id , td.p_dept_id													");
					query.append("	, tdw."+typeArr[0]+date+" FROM TADEPT td LEFT OUTER JOIN						");
					query.append("		(SELECT    x.dept_id,x.p_dept_id, x.comp_no									");
					query.append(",SUM(CASE y.start_date WHEN '"+date+"' THEN 1 ELSE 0 END) AS \""+typeArr[0]+date+"\"	");
					query.append("FROM TADEPT x, TAWORKORDER y														");
					query.append("WHERE x.comp_no = y.comp_no 														");
					query.append("   AND x.dept_id = y.dept_id 														");
					query.getAndDateQuery("y.start_date", maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
			        query.getAndQuery("x.comp_no", compNo);
			        query.append("GROUP BY x.comp_no, x.dept_id,x.p_dept_id) tdw									");
			        query.append("ON td.comp_no = tdw.comp_no														");
					query.append("AND td.dept_id =tdw.dept_id														");
					query.append("WHERE 1=1													");
					query.getAndQuery("td.comp_no", compNo);
			        query.append(") tb																				");
			        query.append("WHERE tb.dept_id IN (SELECT dept_id FROM dbo.SFADEPT_CALL('"+compNo+"',a.dept_id,''))	");
					query.append(") END)*100,0),2) AS \""+typeArr[j]+dateArr[i]+"\"									");
				}
			}
		}
        query.append("FROM	(SELECT * FROM dbo.SFADEPT_ALL('"+compNo+"','0')) da");
        query.append("		,TADEPT a LEFT OUTER JOIN							");
        query.append("		(SELECT	x.dept_id									");
        query.append("				,x.comp_no									");
        query.append("		   FROM TADEPT x, TAWORKORDER y						");
        query.append("		  WHERE x.comp_no = y.comp_no						");
        query.append("  		AND x.dept_id = y.dept_id						");
        query.getAndDateQuery("y.start_date", maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
        query.getAndQuery("x.comp_no", compNo);
        query.append("		  GROUP BY x.comp_no, x.dept_id) b					");
        query.append(" ON a.comp_no = b.comp_no									");
        query.append("AND a.dept_id = b.dept_id									");
        query.append("WHERE da.DEPT_ID = a.DEPT_ID													");
        query.getAndQuery("a.comp_no", compNo);      
        query.getDeptLevelQuery("a.dept_id", maWoDailyChartDTO.getFilterDeptId(), maWoDailyChartDTO.getFilterDeptDesc(), maWoDailyChartDTO.getCompNo());
        
        //query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", maWoDailyChartDTO.getFilterPlantId(), maWoDailyChartDTO.getFilterPlantDesc());
              
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaWoDailyChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoDailyChartDTO
     * @param dateColName
     * @return
     * @throws Exception
     */
    private String getWhere(MaWoDailyChartDTO maWoDailyChartDTO,String dateColName)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", maWoDailyChartDTO.getCompNo());
        query.getAndDateQuery(dateColName, maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
        
        //공장코드
        //query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        //		maWoDailyChartDTO.getFilterPlantId(), maWoDailyChartDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
}