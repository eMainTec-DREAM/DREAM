package dream.req.rpt.woreqrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.req.rpt.woreqrate.dao.ReqRptWoReqRateDetailDAO;
import dream.req.rpt.woreqrate.dto.ReqRptWoReqRateDetailDTO;

/**
 * 夸没立荐啦(贸府磊) 惑技 格废 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="reqRptWoReqRateDetailDAOTarget"
 * @spring.txbn id="reqRptWoReqRateDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqRptWoReqRateDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ReqRptWoReqRateDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptWoReqRateDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(ReqRptWoReqRateDetailDTO reqRptWoReqRateDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT        ");
        query.append("       x.rec_emp_id REQID       ");
        query.append("     , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.rec_emp_id) REQBY       ");
        query.append("     , COUNT(1) AS WOREQCNT     ");
        query.append("     , NVL(COUNT(1)-COUNT( CASE x.woreq_status WHEN 'COM' THEN 1 WHEN 'INC' THEN 1 END),0) AS INCOMPLETECNT ");
        query.append("     , COUNT( CASE (SELECT a.param1 FROM TACDSYSD a WHERE a.list_type = 'WOREQ_STATUS' AND a.cdsysd_no = x.woreq_status)     ");
        query.append("              WHEN 'C' THEN 1 END      ");
        query.append("            ) AS WORESCNT        ");
        query.append("     , COUNT( CASE x.woreq_status WHEN 'COM' THEN 1 WHEN 'INC' THEN 1 END) COMPLETECNT     ");
        query.append("  , CASE WHEN COUNT( CASE (SELECT A.param1 FROM TACDSYSD A WHERE A.list_type = 'WOREQ_STATUS' AND A.cdsysd_no = x.woreq_status)  WHEN 'C' THEN 1 END ) = 0            ");
        query.append("            THEN 0       ");
        query.append("ELSE (ROUND(COUNT(CASE WHEN x.woreq_status = 'COM' THEN 1 WHEN x.woreq_status = 'INC' THEN 1 END)/ COUNT( CASE (SELECT A.param1 FROM TACDSYSD A WHERE A.list_type = 'WOREQ_STATUS' AND A.cdsysd_no = x.woreq_status)  WHEN 'C' THEN 1 END ) *100, 2))         ");
        query.append("            END AS COMPLETERATE      ");
        query.append("FROM TAWOREQ x        ");
        query.append("WHERE 1=1     ");
        query.append(this.getWhere(reqRptWoReqRateDetailDTO,loginUser));
        query.append("GROUP BY x.comp_no, x.rec_dept_id, x.rec_emp_id       ");
        query.getOrderByQuery("x.comp_no","(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.rec_emp_id)", reqRptWoReqRateDetailDTO.getOrderBy(), reqRptWoReqRateDetailDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString());
    }

    public String getWhere(ReqRptWoReqRateDetailDTO reqRptWoReqRateDetailDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.append("AND x.rec_dept_id IS NOT NULL     "); 
        query.append("AND x.rec_emp_id IS NOT NULL     ");
        
        // 老磊
        if("Y".equals(reqRptWoReqRateDetailDTO.getIsReqDate())) {
            query.getAndDateQuery("x.req_date", reqRptWoReqRateDetailDTO.getStartDate().replaceAll("-", ""), reqRptWoReqRateDetailDTO.getEndDate().replaceAll("-", ""));
        }
        else {
            query.getAndDateQuery("x.req_com_date", reqRptWoReqRateDetailDTO.getStartDate().replaceAll("-", ""), reqRptWoReqRateDetailDTO.getEndDate().replaceAll("-", ""));
        }
        
        // 何辑
        query.getAndQuery("x.rec_dept_id", reqRptWoReqRateDetailDTO.getDeptId());
        
        return query.toString();
    }
    
}