package dream.work.rpt.pm.month.rate.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.rpt.pm.month.rate.dao.WorkRptPmMonthRateListDAO;
import dream.work.rpt.pm.month.rate.dto.WorkRptPmMonthRateListDTO;

/**
 * 월별점검실행율 DAO
 * @author  sy.yang
 * @version $Id: WorkRptPmMonthRateListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
 * @since   1.0
 * @spring.bean id="workRptPmMonthRateListDAOTarget"
 * @spring.txbn id="workRptPmMonthRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmMonthRateListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptPmMonthRateListDAO
{
    /**
     * grid find
     * @author  sy.yang
     * @version $Id: WorkRptPmMonthRateListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
     * @since   1.0
     * 
     * @param workRptPmMonthRateListDTO
     * @return List
     */
    public List findWoList(WorkRptPmMonthRateListDTO workRptPmMonthRateListDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        String[] dateArr = workRptPmMonthRateListDTO.getDateArrStr().split(",");
        String[] typeArr = {"P","C","R"};
        String deptId = "".equals(workRptPmMonthRateListDTO.getFilterDeptId())?"0":workRptPmMonthRateListDTO.getFilterDeptId();
        String compNo = workRptPmMonthRateListDTO.getCompNo();
        
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
					query.append(",(	SELECT NVL(SUM(tb."+typeArr[j]+date+"),0)  							");
					query.append("		FROM (	SELECT td.dept_id , td.p_dept_id, tdw."+typeArr[j]+date+"	");
					query.append("				FROM TADEPT td, 											");
					query.append("					( SELECT x.dept_id,x.p_dept_id, x.comp_no				");
					switch (j) {
					case 0:
						query.append(",SUM(DECODE(SUBSTR(y.wkor_date,1,6),'"+date+"',1,0)) AS \""+typeArr[0]+date+"\"	");
						break;
					case 1:
						query.append(",SUM(DECODE(SUBSTR(y.wkor_date,1,6),'"+date+"'									");
			        	query.append("			,DECODE(y.wo_status,'C',1,0),0))			AS \""+typeArr[1]+date+"\"	");
						break;
					}
					query.append("					  FROM TADEPT x, TAWORKORDER y										");
					query.append("					  WHERE x.comp_no = y.comp_no 										");
					query.append("					   AND x.dept_id = y.dept_id 										");
					query.append(this.getWhere(workRptPmMonthRateListDTO));
			        query.append("					  GROUP BY x.comp_no, x.dept_id,x.p_dept_id	) tdw					");
			        query.append("				WHERE td.comp_no = tdw.comp_no(+)										");
					query.append("				 AND td.dept_id =tdw.dept_id(+)											");
					query.getAndQuery("td.comp_no", compNo);
			        query.append("				) tb																	");
					query.append("		START WITH    tb.dept_id = a.dept_id											");
					query.append("		CONNECT BY PRIOR tb.dept_id = tb.p_dept_id)			AS \""+typeArr[j]+dateArr[i]+"\"	");
				}else{
					query.append(",ROUND(NVL((SELECT NVL(SUM(tb."+typeArr[1]+date+"),0) FROM 						");
					query.append("(SELECT td.dept_id , td.p_dept_id													");
					query.append("	, tdw."+typeArr[1]+date+" FROM TADEPT td, 										");
					query.append("		(SELECT    x.dept_id,x.p_dept_id, x.comp_no									");
					query.append(",SUM(DECODE(SUBSTR(y.wkor_date,1,6),'"+date+"'												");
		        	query.append("			,DECODE(y.wo_status,'C',1,0),0))	AS \""+typeArr[1]+date+"\" 			");
					query.append("FROM TADEPT x, TAWORKORDER y														");
					query.append("WHERE x.comp_no = y.comp_no 														");
					query.append("   AND x.dept_id = y.dept_id 														");
					query.append(this.getWhere(workRptPmMonthRateListDTO));
			        query.append("GROUP BY x.comp_no, x.dept_id,x.p_dept_id) tdw									");
			        query.append("WHERE td.comp_no = tdw.comp_no(+)													");
					query.append("AND td.dept_id =tdw.dept_id(+)													");
					query.getAndQuery("td.comp_no", compNo);
			        query.append(") tb																				");
					query.append("START WITH    tb.dept_id = a.dept_id												");
					query.append("CONNECT BY PRIOR tb.dept_id = tb.p_dept_id)/										");
					query.append("DECODE((SELECT NVL(SUM(tb."+typeArr[0]+date+"),0) FROM 							");
					query.append("(SELECT td.dept_id , td.p_dept_id													");
					query.append("	, tdw."+typeArr[0]+date+" FROM TADEPT td, 										");
					query.append("		(SELECT    x.dept_id,x.p_dept_id, x.comp_no									");
					query.append(",SUM(DECODE(SUBSTR(y.wkor_date,1,6),'"+date+"',1,0)) AS \""+typeArr[0]+date+"\" 				");
					query.append("FROM TADEPT x, TAWORKORDER y														");
					query.append("WHERE x.comp_no = y.comp_no 														");
					query.append("   AND x.dept_id = y.dept_id 														");
					query.append(this.getWhere(workRptPmMonthRateListDTO));
			        query.append("GROUP BY x.comp_no, x.dept_id,x.p_dept_id) tdw									");
			        query.append("WHERE td.comp_no = tdw.comp_no(+)													");
					query.append("AND td.dept_id =tdw.dept_id(+)													");
					query.getAndQuery("td.comp_no", compNo);
			        query.append(") tb																				");
					query.append("START WITH    tb.dept_id = a.dept_id												");
					query.append("CONNECT BY PRIOR tb.dept_id = tb.p_dept_id),0,NULL,								");
					query.append("(SELECT NVL(SUM(tb."+typeArr[0]+date+"),0) FROM 									");
					query.append("(SELECT td.dept_id , td.p_dept_id													");
					query.append("	, tdw."+typeArr[0]+date+" FROM TADEPT td, 										");
					query.append("		(SELECT    x.dept_id,x.p_dept_id, x.comp_no									");
					query.append(",SUM(DECODE(SUBSTR(y.wkor_date,1,6),'"+date+"',1,0)) AS \""+typeArr[0]+date+"\" 				");
					query.append("FROM TADEPT x, TAWORKORDER y														");
					query.append("WHERE x.comp_no = y.comp_no 														");
					query.append("   AND x.dept_id = y.dept_id 														");
					query.append(this.getWhere(workRptPmMonthRateListDTO));
			        query.append("GROUP BY x.comp_no, x.dept_id,x.p_dept_id) tdw									");
			        query.append("WHERE td.comp_no = tdw.comp_no(+)													");
					query.append("AND td.dept_id =tdw.dept_id(+)													");
					query.getAndQuery("td.comp_no", compNo);
			        query.append(") tb																				");
					query.append("START WITH    tb.dept_id = a.dept_id												");
					query.append("CONNECT BY PRIOR tb.dept_id = tb.p_dept_id))*100,0),2) AS \""+typeArr[j]+dateArr[i]+"\"	");
				}
			}
		}
        query.append("FROM	TADEPT a,											");
        query.append("		( SELECT x.dept_id ,x.comp_no						");
        query.append("		  FROM TADEPT x, TAWORKORDER y						");
        query.append("		  WHERE x.comp_no = y.comp_no						");
        query.append("		    AND x.dept_id = y.dept_id						");
        query.append(this.getWhere(workRptPmMonthRateListDTO));
        query.append("		  GROUP BY x.comp_no, x.dept_id	) b					");
        query.append("WHERE a.comp_no = b.comp_no(+)							");
        query.append("  AND a.dept_id = b.dept_id(+)							");
        query.getAndQuery("a.comp_no", compNo);
        query.getAndQuery("a.is_use", "Y");
        query.getAndQuery("a.is_monitoring", "Y");
        if(!"0".equals(deptId)){
        	query.append(" AND a.dept_id IN ( SELECT dept_id FROM TADEPT WHERE comp_no='"+compNo+"' START WITH dept_id="+deptId+" CONNECT BY PRIOR dept_id = p_dept_id )	");
        } 
        query.append("START WITH a.p_dept_id = 0 								");
        query.append("CONNECT BY PRIOR a.dept_id = a.p_dept_id					");
      
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    
    /**
     * Filter 조건
     * @author  sy.yang
     * @version $Id: WorkRptPmMonthRateListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
     * @since   1.0
     * 
     * @param workRptPmMonthRateListDTO
     * @param dateColName
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptPmMonthRateListDTO workRptPmMonthRateListDTO)
    {        
        QueryBuffer query = new QueryBuffer();

        String compNo = workRptPmMonthRateListDTO.getCompNo();
        String startDate = workRptPmMonthRateListDTO.getFilterStartDate().replace("-", "")+"01";
    	String endDate = workRptPmMonthRateListDTO.getFilterEndDate().replace("-", "")+"31";

    	query.getAndQuery("x.comp_no", compNo);
    	query.getAndQuery("x.is_use", "Y");
        query.getAndQuery("x.is_monitoring", "Y");
		query.getAndQuery("y.wo_type", "PMI");
		query.getAndDateQuery("y.wkor_date", startDate, endDate);

        return query.toString();
    }
    
}