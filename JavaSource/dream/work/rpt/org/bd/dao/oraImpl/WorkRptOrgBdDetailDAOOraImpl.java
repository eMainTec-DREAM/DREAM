package dream.work.rpt.org.bd.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.rpt.org.bd.dao.WorkRptOrgBdDetailDAO;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdDetailDTO;

/**
 * 조직별 고장분석 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptOrgBdDetailDAOTarget"
 * @spring.txbn id="workRptOrgBdDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptOrgBdDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptOrgBdDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgBdDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT   											                ");
        query.append("      ''            				AS SEQNO		                ");
        query.append("    , COUNT(x.item_no)            AS BDCNT		                ");
        query.append("    , ROUND(NVL(SUM(x.work_time),0)/60,1)		AS WOTIMEHOUR	    ");
        query.append("    , NVL(SUM(x.tot_amt),0)       AS MAINTAMT		                ");
        query.append("    , y.tmonth                 	AS MONTH		                ");
        query.append("FROM (                                                            ");
        query.append("    SELECT                                                        ");
        query.append("        a.item_no                                                 ");
        query.append("        ,a.work_time                                              ");
        query.append("        ,a.tot_amt                                                ");
        query.append("        ,a.wkor_date                                              ");
        query.append("    FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b                   ");
        query.append("    ON a.item_no=b.item_no        		                        ");
        query.append("    AND a.comp_no=b.comp_no        		                        ");
        query.append("    WHERE 1=1     						                        ");
        query.append(this.getWhere(workRptOrgBdDetailDTO,loginUser));
        query.append(") x RIGHT OUTER JOIN TADAY y                                      ");
        query.append("ON x.wkor_date=y.tday                                             ");
        query.append("WHERE 1=1                                                         ");
        query.append(this.getDateWhere(workRptOrgBdDetailDTO,loginUser));
        query.append("GROUP BY y.tmonth                                                 ");
        query.getOrderByQuery("y.tmonth", workRptOrgBdDetailDTO.getOrderBy(), workRptOrgBdDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptOrgBdDetailDTO.getIsLoadMaxCount(), workRptOrgBdDetailDTO.getFirstRow()));
    }
    
    public String getWhere(WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("a.wo_type", "BM");
        query.append("AND a.wkor_date IS NOT NULL       ");
        
        // 부서
        query.append("AND a.dept_id IS NOT NULL						");
        query.append("AND b.dept_id IS NOT NULL						");
        query.append("  AND b.dept_id IN (SELECT dept_id         	");
        query.append("                    FROM TADEPT        		");
        query.append("                    WHERE 1=1             	");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.append("                    START WITH dept_id = "
        					+ workRptOrgBdDetailDTO.getDeptId() + "	");
        query.append("                    CONNECT BY PRIOR dept_id = p_dept_id)	");

        // 공장
        query.getCodeLikeQuery("a.plant"
                , "(SELECT z.description FROM TAPLANT z WHERE z.comp_no = a.comp_no AND z.plant = a.plant) "
                , workRptOrgBdDetailDTO.getPlantId(), workRptOrgBdDetailDTO.getPlantDesc());
        return query.toString();
    }
    
    public String getDateWhere(WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        // 월
        String fromDate = CommonUtil.dateToData(workRptOrgBdDetailDTO.getStartDate()).replace("-", "") + "01";
        String toDate   = CommonUtil.dateToData(workRptOrgBdDetailDTO.getEndDate()).replace("-", "") + "31";
        query.getAndDateQuery("y.tday", fromDate, toDate);
        
        return query.toString();
    }
    
    public String findTotalCount(WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(1)                                               ");
        query.append("  FROM (                                                      ");
        query.append("    SELECT y.tmonth                                           ");
        query.append("      FROM (                                                  ");
        query.append("            SELECT                                            ");
        query.append("                  a.item_no                                   ");
        query.append("                , a.work_time                                 ");
        query.append("                , a.tot_amt                                   ");
        query.append("                , a.wkor_date                                 ");
        query.append("              FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b     ");
        query.append("                                    ON a.item_no=b.item_no    ");
        query.append("                                   AND a.comp_no=b.comp_no    ");
        query.append("             WHERE 1=1                                        ");
        query.append(this.getWhere(workRptOrgBdDetailDTO,loginUser));
        query.append("            ) x RIGHT OUTER JOIN TADAY y                      ");
        query.append("                   ON x.wkor_date=y.tday                      ");
        query.append("     WHERE 1=1                                                ");
        query.append(this.getDateWhere(workRptOrgBdDetailDTO,loginUser));
        query.append("     GROUP BY y.tmonth                                        ");
        query.append("  ) x                                                         ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}