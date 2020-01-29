package dream.work.rpt.org.mtbfmttr.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.org.mtbfmttr.dao.WorkRptOrgMtbfmttrDetailDAO;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrDetailDTO;

/**
 * 조직별MTBF,MTTR 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptOrgMtbfmttrDetailDAOTarget"
 * @spring.txbn id="workRptOrgMtbfmttrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptOrgMtbfmttrDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptOrgMtbfmttrDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgMtbfmttrDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                            ");
        query.append("    ''                                              AS SEQNO      ");
        query.append("  , SUBSTR(w.wrk_date,1,6)                          AS MONTH      ");
        query.append("  , ROUND(  NVL(  ROUND(SUM( (NVL(w.dtime,0)+NVL(w.ntime,0)+NVL(w.etime,0))),1)/60 / NVL(SUM(v.bmCnt),1)  ,0)  ,2)    AS MTBFHOUR ");
        query.append("  , ROUND(  NVL(SUM(v.totWorkHour)/SUM(v.bmCnt),0)  ,2)    AS MTTRHOUR   ");
        query.append("FROM (                                                            ");
        query.append("    SELECT                                                        ");
        query.append("        a.wkor_date                                 AS wkor_date  ");
        query.append("      , a.comp_no                                   AS comp_no    ");
        query.append("      , COUNT(eqhistory_id)                         AS BMCNT      ");
        query.append("      , ROUND(NVL(SUM(a.work_time),0)/60,1)         AS TOTWORKHOUR");
        query.append("FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b                       ");
        query.append("ON a.item_no=b.item_no                                            ");
        query.append("AND a.comp_no=b.comp_no                                           ");
        query.append("WHERE 1=1                                                         ");
        query.append(this.getWhere(workRptOrgMtbfmttrDetailDTO,loginUser));
        query.append("GROUP BY a.comp_no, a.wkor_date                                   ");
        query.append(") v RIGHT OUTER JOIN TALNWRKTIME w                                ");
        query.append("ON v.wkor_date=w.wrk_date                                         ");
        query.append("AND v.comp_no=w.comp_no                                           ");
        query.append("WHERE 1=1                                                         ");
        query.append(this.getWrkTimeWhere(workRptOrgMtbfmttrDetailDTO,loginUser));
        query.append("GROUP BY SUBSTR(w.wrk_date,1,6)                                   ");
        query.append("ORDER BY SUBSTR(w.wrk_date,1,6)                                   ");

        return getJdbcTemplate().queryForList(query.toString());
    }
     	
    public String getWrkTimeWhere(WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO,User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("AND w.DTIME+w.NTIME+w.ETIME >0                       ");

        //공장코드
        query.getCodeLikeQuery("w.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = w.comp_no AND plant = w.plant )", 
                workRptOrgMtbfmttrDetailDTO.getPlantId(), workRptOrgMtbfmttrDetailDTO.getPlantDesc());
        
    	// 월
        String fromDate = workRptOrgMtbfmttrDetailDTO.getStartDate().replaceAll("-", "");
        String toDate   = workRptOrgMtbfmttrDetailDTO.getEndDate().replaceAll("-", "");
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
        	query.append("AND w.wrk_date >=  TO_CHAR(TO_DATE('"+fromDate+"','YYYYMM'),'YYYYMMDD')     ");
        }
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
        	query.append("AND w.wrk_date <  TO_CHAR(ADD_MONTHS(TO_DATE('"+toDate+"','YYYYMM'),1),'YYYYMMDD')        ");
        }        
        
    	return query.toString();
    }    	
    public String getWhere(WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("a.wo_type", "BM");
        query.getAndQuery("b.is_last_version", "Y");
        query.append("AND a.wkor_date IS NOT NULL       ");
        query.append("AND a.dept_id IS NOT NULL        	");
        query.append("AND b.dept_id IS NOT NULL        	");
        
        // 월
        String fromDate = workRptOrgMtbfmttrDetailDTO.getStartDate().replaceAll("-", "");
        String toDate   = workRptOrgMtbfmttrDetailDTO.getEndDate().replaceAll("-", "");
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
        	query.append("AND a.wkor_date >=  TO_CHAR(TO_DATE('"+fromDate+"','YYYYMM'),'YYYYMMDD')     ");
        }
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
        	query.append("AND a.wkor_date <  TO_CHAR(ADD_MONTHS(TO_DATE('"+toDate+"','YYYYMM'),1),'YYYYMMDD')        ");
        }        
        
        query.append("AND b.dept_id IN (SELECT dept_id              ");
        query.append("FROM TADEPT                             		");
        query.append("WHERE 1=1                 					");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.append("START WITH dept_id = "
        		+	workRptOrgMtbfmttrDetailDTO.getDeptId() + "     ");
        query.append("CONNECT BY PRIOR dept_id = p_dept_id)   		");
        
        //공장코드
        query.getCodeLikeQuery("b.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = b.plant )", 
                workRptOrgMtbfmttrDetailDTO.getPlantId(), workRptOrgMtbfmttrDetailDTO.getPlantDesc());

        return query.toString();
    }

    @Override
    public String findTotalCount(WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT                                                    ");
        query.append("   COUNT(1)  	 	                                        ");
        query.append("	FROM (                                                  ");
        query.append("		SELECT                                              ");
        query.append("    		v.comp_no                                       ");
        query.append("		  FROM (                                            ");
        query.append("    		SELECT                                          ");
        query.append("      		a.comp_no                                   ");
        query.append("      	  , a.wkor_date                                	");
        query.append("			  FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b   ");
        query.append("									ON a.item_no=b.item_no	");
        query.append("								   AND a.comp_no=b.comp_no  ");
        query.append("								 WHERE 1=1                  ");
        query.append(this.getWhere(workRptOrgMtbfmttrDetailDTO,loginUser));
        query.append("			 GROUP BY a.comp_no, a.wkor_date                ");
        query.append("				) v RIGHT OUTER JOIN TALNWRKTIME w          ");
        query.append("							 ON v.wkor_date=w.wrk_date      ");
        query.append("						    AND v.comp_no=w.comp_no         ");
        query.append("		 WHERE 1=1                                          ");
        query.append(this.getWrkTimeWhere(workRptOrgMtbfmttrDetailDTO,loginUser));
        query.append("		 GROUP BY SUBSTR(w.wrk_date,1,6), v.comp_no      	");
        query.append(") x        		                                        ");

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}