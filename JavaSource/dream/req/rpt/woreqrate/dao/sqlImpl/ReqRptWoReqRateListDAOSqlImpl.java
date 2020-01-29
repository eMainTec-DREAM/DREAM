package dream.req.rpt.woreqrate.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.req.rpt.woreqrate.dao.ReqRptWoReqRateListDAO;
import dream.req.rpt.woreqrate.dto.ReqRptWoReqRateCommonDTO;

/**
 * 요청접수율(처리자) 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="reqRptWoReqRateListDAOTarget"
 * @spring.txbn id="reqRptWoReqRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqRptWoReqRateListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ReqRptWoReqRateListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptWoReqRateCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(ReqRptWoReqRateCommonDTO reqRptWoReqRateCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                    ");
        query.append("    x.rec_dept_id             AS DEPTID   ");
        query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.rec_dept_id) AS PARTDESC    ");
        query.append("  , COUNT(1)                  AS WOREQCNT ");
        query.append("  , ISNULL(COUNT(1)-COUNT( CASE x.woreq_status WHEN 'COM' THEN 1 END),0) AS INCOMPLETECNT ");
//        query.append("  , COUNT( CASE (SELECT a.param1 FROM TACDSYSD a WHERE a.list_type = 'WOREQ_STATUS' AND a.cdsysd_no = x.woreq_status)       ");
//        query.append("           WHEN 'C' THEN 1 END            ");
//        query.append("          )                   AS WORESCNT ");
        query.append(", COUNT( CASE y.param1 WHEN 'C' THEN 1 END) AS WORESCNT ");
        query.append("  , COUNT( CASE x.woreq_status WHEN 'COM' THEN 1 END) AS COMPLETECNT  ");
        query.append("  , CASE WHEN (COUNT(1)) = 0              ");
        query.append("         THEN 0                           ");
        query.append("         ELSE (ROUND(COUNT(CASE WHEN x.woreq_status = 'COM' THEN 1 END)/COUNT(1)*100, 2))         ");
        query.append("         END                  AS COMPLETERATE      ");
        query.append("FROM TAWOREQ x INNER JOIN TACDSYSD y      ");
        query.append("ON x.woreq_status = y.cdsysd_no           ");
        query.append("AND y.list_type = 'WOREQ_STATUS'			");
        query.append("WHERE 1=1                                 ");
        query.append(this.getWhere(reqRptWoReqRateCommonDTO, loginUser));
        query.append("GROUP BY x.comp_no, x.rec_dept_id         ");
        query.getOrderByQuery("x.comp_no","(SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.rec_dept_id)"
                , reqRptWoReqRateCommonDTO.getOrderBy(), reqRptWoReqRateCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(reqRptWoReqRateCommonDTO.getIsLoadMaxCount(), reqRptWoReqRateCommonDTO.getFirstRow()));
    }
    
    public String getWhere(ReqRptWoReqRateCommonDTO reqRptWoReqRateCommonDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
            
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.append("AND x.rec_dept_id IS NOT NULL     ");
        query.append("AND x.rec_emp_id IS NOT NULL     ");

        // 일자
        if("Y".equals(reqRptWoReqRateCommonDTO.getFilterIsReqDate())) {
            query.getAndDateQuery("x.req_date", reqRptWoReqRateCommonDTO.getFilterStartDate(), reqRptWoReqRateCommonDTO.getFilterEndDate());
        }
        else {
            query.getAndDateQuery("x.req_com_date", reqRptWoReqRateCommonDTO.getFilterStartDate(), reqRptWoReqRateCommonDTO.getFilterEndDate());
        }
        
        // 부서
        query.getDeptLevelQuery("x.rec_dept_id", reqRptWoReqRateCommonDTO.getFilterDeptId(), reqRptWoReqRateCommonDTO.getFilterDeptDesc(), loginUser.getCompNo());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.plant = x.plant )", 
                reqRptWoReqRateCommonDTO.getFilterPlantId(), reqRptWoReqRateCommonDTO.getFilterPlantDesc());
                
        return query.toString();
    }
    
    @Override
    public String findTotalCount(ReqRptWoReqRateCommonDTO reqRptWoReqRateCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                    ");
        query.append("       COUNT(1)                           ");
        query.append("FROM (                                    ");
        query.append("SELECT                                    ");
        query.append("    x.rec_dept_id             AS PARTID   ");
        query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.rec_dept_id) AS PARTDESC    ");
        query.append("  , COUNT( CASE x.woreq_status WHEN 'REQ' THEN 1 END) AS WOREQCNT     "); //요청(건)
        query.append("  , COUNT( CASE x.woreq_status WHEN 'REC' THEN 1 END) AS WORESCNT     "); //접수(건)
        query.append("  , COUNT( CASE x.woreq_status WHEN 'COM' THEN 1 END) AS COMPLETECNT  "); //완료(건)
        query.append("  , ROUND(COUNT(CASE WHEN x.woreq_status = 'COM' THEN 1 END)/COUNT( CASE x.woreq_status WHEN 'REQ' THEN 1 END)*100, 2) AS COMPLETERATE    ");
        query.append("FROM TAWOREQ x INNER JOIN TACDSYSD y      ");
        query.append("ON x.woreq_status = y.cdsysd_no           ");
        query.append("AND y.list_type = 'WOREQ_STATUS'			");
        query.append("WHERE 1=1                                 ");
        query.append(this.getWhere(reqRptWoReqRateCommonDTO, loginUser));
        query.append("GROUP BY x.comp_no, x.rec_dept_id         ");
        query.append(") a                                       ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
}