package dream.work.rpt.mawodaily.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
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
public class MaWoDailyChartDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoDailyChartDAO
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
        QueryBuffer query = new QueryBuffer();
        String[] dateArr = maWoDailyChartDTO.getDateArrStr().split(",");
        String[] typeArr = {"P","C","R"};
        query.append("SELECT	a.dept_id AS ID									");
        query.append("			,LEVEL lvl										");
        query.append("			,MIN(LEVEL) OVER() AS minLvl	");
        query.append("			,a.dept_id AS deptId							");
        query.append("			,a.p_dept_id AS pdeptId							");
        query.append("			,a.description AS deptDesc						");
        for (int i = 1; i < dateArr.length; i++) {
        	String date = CommonUtil.convertDate(dateArr[i]);
			for (int j = 0; j < 3; j++) {
				if(j<2){
					query.append(",(SELECT NVL(SUM(tb."+typeArr[j]+date+"),0) FROM 							");
					query.append("(SELECT td.dept_id , td.p_dept_id											");
					query.append("	, tdw."+typeArr[j]+date+" FROM TADEPT td, 								");
					query.append("		(SELECT    x.dept_id,x.p_dept_id, x.comp_no							");
					switch (j) {
					case 0:
						query.append(",SUM(DECODE(y.start_date,'"+date+"',1,0)) AS \""+typeArr[0]+date+"\" 				");
			        	
						break;
					case 1:
						query.append(",SUM(DECODE(y.start_date,'"+date+"'												");
			        	query.append("			,DECODE(y.wo_status,'C',1,0),0))	AS \""+typeArr[1]+date+"\" 			");
						break;
					}
					query.append("FROM TADEPT x, TAWORKORDER y														");
					query.append("WHERE x.comp_no = y.comp_no 														");
					query.append("   AND x.dept_id = y.dept_id 														");
					query.getAndDateQuery("y.start_date", maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
			        query.getAndQuery("x.comp_no", maWoDailyChartDTO.getCompNo());
			        query.append("GROUP BY x.comp_no, x.dept_id,x.p_dept_id) tdw									");
			        query.append("WHERE td.comp_no = tdw.comp_no(+)													");
					query.append("AND td.dept_id =tdw.dept_id(+)													");
					query.getAndQuery("td.comp_no", maWoDailyChartDTO.getCompNo());
			        query.append(") tb																				");
					query.append("START WITH    tb.dept_id = a.dept_id												");
					query.append("CONNECT BY PRIOR tb.dept_id = tb.p_dept_id)	AS \""+typeArr[j]+dateArr[i]+"\"	");
				}else{
					query.append(",ROUND(NVL((SELECT NVL(SUM(tb."+typeArr[1]+date+"),0) FROM 						");
					query.append("(SELECT td.dept_id , td.p_dept_id													");
					query.append("	, tdw."+typeArr[1]+date+" FROM TADEPT td, 										");
					query.append("		(SELECT    x.dept_id,x.p_dept_id, x.comp_no									");
					query.append(",SUM(DECODE(y.start_date,'"+date+"'												");
		        	query.append("			,DECODE(y.wo_status,'C',1,0),0))	AS \""+typeArr[1]+date+"\" 			");
					query.append("FROM TADEPT x, TAWORKORDER y														");
					query.append("WHERE x.comp_no = y.comp_no 														");
					query.append("   AND x.dept_id = y.dept_id 														");
					query.getAndDateQuery("y.start_date", maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
			        query.getAndQuery("x.comp_no", maWoDailyChartDTO.getCompNo());
			        query.append("GROUP BY x.comp_no, x.dept_id,x.p_dept_id) tdw									");
			        query.append("WHERE td.comp_no = tdw.comp_no(+)													");
					query.append("AND td.dept_id =tdw.dept_id(+)													");
					query.getAndQuery("td.comp_no", maWoDailyChartDTO.getCompNo());
			        query.append(") tb																				");
					query.append("START WITH    tb.dept_id = a.dept_id												");
					query.append("CONNECT BY PRIOR tb.dept_id = tb.p_dept_id)/										");
					query.append("DECODE((SELECT NVL(SUM(tb."+typeArr[0]+date+"),0) FROM 							");
					query.append("(SELECT td.dept_id , td.p_dept_id													");
					query.append("	, tdw."+typeArr[0]+date+" FROM TADEPT td, 										");
					query.append("		(SELECT    x.dept_id,x.p_dept_id, x.comp_no									");
					query.append(",SUM(DECODE(y.start_date,'"+date+"',1,0)) AS \""+typeArr[0]+date+"\" 				");
					query.append("FROM TADEPT x, TAWORKORDER y														");
					query.append("WHERE x.comp_no = y.comp_no 														");
					query.append("   AND x.dept_id = y.dept_id 														");
					query.getAndDateQuery("y.start_date", maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
			        query.getAndQuery("x.comp_no", maWoDailyChartDTO.getCompNo());
			        query.append("GROUP BY x.comp_no, x.dept_id,x.p_dept_id) tdw									");
			        query.append("WHERE td.comp_no = tdw.comp_no(+)													");
					query.append("AND td.dept_id =tdw.dept_id(+)													");
					query.getAndQuery("td.comp_no", maWoDailyChartDTO.getCompNo());
			        query.append(") tb																				");
					query.append("START WITH    tb.dept_id = a.dept_id												");
					query.append("CONNECT BY PRIOR tb.dept_id = tb.p_dept_id),0,NULL,								");
					query.append("(SELECT NVL(SUM(tb."+typeArr[0]+date+"),0) FROM 									");
					query.append("(SELECT td.dept_id , td.p_dept_id													");
					query.append("	, tdw."+typeArr[0]+date+" FROM TADEPT td, 										");
					query.append("		(SELECT    x.dept_id,x.p_dept_id, x.comp_no									");
					query.append(",SUM(DECODE(y.start_date,'"+date+"',1,0)) AS \""+typeArr[0]+date+"\" 				");
					query.append("FROM TADEPT x, TAWORKORDER y														");
					query.append("WHERE x.comp_no = y.comp_no 														");
					query.append("   AND x.dept_id = y.dept_id 														");
					query.getAndDateQuery("y.start_date", maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
			        query.getAndQuery("x.comp_no", maWoDailyChartDTO.getCompNo());
			        query.append("GROUP BY x.comp_no, x.dept_id,x.p_dept_id) tdw									");
			        query.append("WHERE td.comp_no = tdw.comp_no(+)													");
					query.append("AND td.dept_id =tdw.dept_id(+)													");
					query.getAndQuery("td.comp_no", maWoDailyChartDTO.getCompNo());
			        query.append(") tb																				");
					query.append("START WITH    tb.dept_id = a.dept_id												");
					query.append("CONNECT BY PRIOR tb.dept_id = tb.p_dept_id))*100,0),2) AS \""+typeArr[j]+dateArr[i]+"\"	");
				}
			}
		}
        query.append("FROM	TADEPT a,											");
        query.append("		(SELECT	x.dept_id									");
        query.append("				,x.comp_no									");
        query.append("FROM TADEPT x, TAWORKORDER y								");
        query.append("WHERE x.comp_no = y.comp_no								");
        query.append("  AND x.dept_id = y.dept_id								");
        query.getAndDateQuery("y.start_date", maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
        query.getAndQuery("x.comp_no", maWoDailyChartDTO.getCompNo());
        query.append("GROUP BY x.comp_no, x.dept_id) b							");
        query.append("WHERE a.comp_no = b.comp_no(+)							");
        query.append("  AND a.dept_id = b.dept_id(+)							");
        query.getAndQuery("a.comp_no", maWoDailyChartDTO.getCompNo());
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", maWoDailyChartDTO.getFilterPlantId(), maWoDailyChartDTO.getFilterPlantDesc());
        query.getDeptLevelQuery("a.dept_id", maWoDailyChartDTO.getFilterDeptId(), maWoDailyChartDTO.getFilterDeptDesc(), maWoDailyChartDTO.getCompNo());
        query.append("START WITH a.p_dept_id = 0												");
        query.append("CONNECT BY PRIOR a.dept_id = a.p_dept_id									");
		
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
    private String getWhere(MaWoDailyChartDTO maWoDailyChartDTO, String dateColName)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("x.comp_no", maWoDailyChartDTO.getCompNo());
        query.getAndDateQuery(dateColName, maWoDailyChartDTO.getFilterStartDate(), maWoDailyChartDTO.getFilterEndDate());
        
        //공장코드
        //query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        //maWoDailyChartDTO.getFilterPlantId(), maWoDailyChartDTO.getFilterPlantDesc());
        return query.toString();
    }
    
}