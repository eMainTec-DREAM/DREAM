package dream.req.rpt.woplancmptrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.req.rpt.woplancmptrate.dao.ReqRptWoPlanCmptDetailListDAO;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptDetailListDTO;

/**
 * 작업의뢰 초기계획 요청 목록 - List DAO implements
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 * 
 * @spring.bean id="reqRptWoPlanCmptDetailListDAOTarget"
 * @spring.txbn id="reqRptWoPlanCmptDetailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqRptWoPlanCmptDetailListDAOOraImpl  extends BaseJdbcDaoSupportOra implements ReqRptWoPlanCmptDetailListDAO
{

    /**
     * grid find
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param   reqRptWoPlanCmptDetailListDTO
     * @param   user
     * @return  List
     */
    public List findList(ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                        ");
        query.append("       ''                                                                                                     AS seqNo        ");
        query.append("     , a.wo_no                                                                                                AS woNo         ");
        query.append("     , a.description                                                                                          AS description, ");
        query.getDate("a.wkor_date", "wkorDate");
        query.append("     , (SELECT aa.item_no FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id)      AS equipNo      ");
        query.append("     , (SELECT aa.description FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id)  AS equipDesc    ");
        query.append("     , (SELECT aa.description FROM TADEPT aa WHERE a.comp_no = aa.comp_no AND a.dept_id = aa.dept_id)         AS deptDesc     ");
        query.append("     , (SELECT aa.emp_name FROM TAEMP aa WHERE a.comp_no = aa.comp_no AND a.emp_id = aa.emp_id)               AS empName,     ");
        query.getDate("NVL(E.end_date, a.end_date)", "initDate");
        query.append("     , d.woreq_no                                                                                             AS woReqNo      ");
        query.append("     , d.description                                                                                          AS woReqName,   ");
        query.getDate("d.req_date", "reqDate");
        query.append("     , (SELECT aa.description FROM TADEPT aa WHERE d.comp_no = aa.comp_no AND d.req_dept_id = aa.dept_id)     AS reqDept      ");
        query.append("     , (SELECT aa.emp_name FROM TAEMP aa WHERE d.comp_no = aa.comp_no AND d.req_emp_id = aa.emp_id)           AS reqEmp       ");
        query.append("  FROM TAWORKORDER a INNER JOIN TAWOEQUIP b ON a.comp_no= b.comp_no AND a.wkor_id = b.wkor_id                                 ");
        query.append("                     INNER JOIN TAWOREQRES c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id                               ");
        query.append("                     INNER JOIN TAWOREQ d ON c.comp_no = d.comp_no AND c.woreq_id = d.woreq_id                                ");
        query.append("                      LEFT OUTER JOIN TAWOPLAN E ON a.comp_no = E.comp_no AND a.wkor_id = E.wkor_id                           ");
        query.append(" WHERE 1 = 1                                                                                                                  ");
        query.append(this.getWhere(reqRptWoPlanCmptDetailListDTO, user));
        query.getOrderByQuery("a.wkor_id", "a.wkor_date ASC", reqRptWoPlanCmptDetailListDTO.getOrderBy(), reqRptWoPlanCmptDetailListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(reqRptWoPlanCmptDetailListDTO.getIsLoadMaxCount(), reqRptWoPlanCmptDetailListDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  nhkim8548
     * @version $Id: $
     * @since   1.0
     * 
     * @param   reqRptWoPlanCmptDetailListDTO
     * @param   user
     * @return
     * @throws  Exception
     */
    private String getWhere(ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        
        // 공장
        query.getCodeLikeQuery("a.plant", "(SELECT xx.description FROM  TAPLANT xx WHERE xx.comp_no = '"+ user.getCompNo() +"' AND a.plant = xx.plant)", 
        		reqRptWoPlanCmptDetailListDTO.getPlantId(), reqRptWoPlanCmptDetailListDTO.getPlantDesc());
        // 월
        query.getAndDateQuery("a.wkor_date", reqRptWoPlanCmptDetailListDTO.getMonth().replace("-", "")+"01", DateUtil.plusLastDayOfMonth(reqRptWoPlanCmptDetailListDTO.getMonth().replace("-", "")));
		
        query.getAndQuery("a.is_deleted", "N");
        
		query.append(" AND a.wo_status IN('PRC', 'C')   ");
		
        return query.toString();
    }
    
    public String findTotalCount(ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                ");
        query.append("       COUNT(1)                                                                                       ");
        query.append("  FROM TAWORKORDER a INNER JOIN TAWOEQUIP b ON a.comp_no= b.comp_no AND a.wkor_id = b.wkor_id         ");
        query.append("                     INNER JOIN TAWOREQRES c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id       ");
        query.append("                     INNER JOIN TAWOREQ d ON c.comp_no = d.comp_no AND c.woreq_id = d.woreq_id        ");
        query.append("                      LEFT OUTER JOIN TAWOPLAN E ON a.comp_no = E.comp_no AND a.wkor_id = E.wkor_id   ");
        query.append(" WHERE 1 = 1                                                                                          ");
        query.append(this.getWhere(reqRptWoPlanCmptDetailListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
