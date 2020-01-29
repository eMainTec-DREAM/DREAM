package dream.req.rpt.preworeqrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.req.rpt.preworeqrate.dao.ReqRptPreWoreqDetailListDAO;
import dream.req.rpt.preworeqrate.dto.ReqRptPreWoreqDetailListDTO;

/**
 * 작업의뢰 시스템 요청 목록 - List DAO implements
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 * 
 * @spring.bean id="reqRptPreWoreqDetailListDAOTarget"
 * @spring.txbn id="reqRptPreWoreqDetailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqRptPreWoreqDetailListDAOOraImpl extends BaseJdbcDaoSupportOra implements ReqRptPreWoreqDetailListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptPreWoreqDetailListDTO
     * @return List
     */
    public List findList(ReqRptPreWoreqDetailListDTO reqRptPreWoreqDetailListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                        ");
        query.append("       ''                                                                                                     AS seqNo        ");
        query.append("     , a.wo_no                                                                                                AS woNo         ");
        query.append("     , a.description                                                                                          AS description, ");
        query.getDate("a.wkor_date", "wkorDate");
        query.append("     , (SELECT aa.item_no FROM taequipment aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id)      AS equipNo      ");
        query.append("     , (SELECT aa.description FROM taequipment aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id)  AS equipDesc    ");
        query.append("     , (SELECT aa.description FROM tadept aa WHERE a.comp_no = aa.comp_no AND a.dept_id = aa.dept_id)         AS deptDesc     ");
        query.append("     , (SELECT aa.emp_name FROM taemp aa WHERE a.comp_no = aa.comp_no AND a.emp_id = aa.emp_id)               AS empName      ");
        query.append("     , d.woreq_no                                                                                             AS woReqNo      ");
        query.append("     , d.description                                                                                          AS woReqName,   ");
        query.getDate("d.req_date", "reqDate");
        query.append("     , (SELECT aa.description FROM tadept aa WHERE d.comp_no = aa.comp_no AND d.req_dept_id = aa.dept_id)     AS reqDept      ");
        query.append("     , (SELECT aa.emp_name FROM taemp aa WHERE d.comp_no = aa.comp_no AND d.req_emp_id = aa.emp_id)           AS reqEmp       ");
        query.append("  FROM TAWORKORDER a INNER JOIN TAWOEQUIP b ON a.comp_no = b.comp_no AND a.wkor_id = b.wkor_id                                ");
        query.append("                      LEFT OUTER JOIN TAWOREQRES c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id                         ");
        query.append("                      LEFT OUTER JOIN TAWOREQ d ON c.comp_no = d.comp_no AND c.woreq_id = d.woreq_id                          ");
        query.append(" WHERE 1 = 1                                                                                                                  ");
        query.append(this.getWhere(reqRptPreWoreqDetailListDTO, user));
        query.getOrderByQuery("a.wkor_id", "a.wkor_date ASC", reqRptPreWoreqDetailListDTO.getOrderBy(), reqRptPreWoreqDetailListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(reqRptPreWoreqDetailListDTO.getIsLoadMaxCount(), reqRptPreWoreqDetailListDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  nhkim8548
     * @version $Id: $
     * @since   1.0
     * @param reqRptPreWoreqDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    private String getWhere(ReqRptPreWoreqDetailListDTO reqRptPreWoreqDetailListDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        // 회사번호
        query.getAndQuery("a.comp_no", user.getCompNo());
        
        // 공장
        query.getCodeLikeQuery("a.plant", "(SELECT x.description FROM TAPLANT x WHERE x.comp_no = '"+user.getCompNo()+"' AND x.plant = a.plant )", 
        		reqRptPreWoreqDetailListDTO.getPlantId(), reqRptPreWoreqDetailListDTO.getPlantDesc());
        
        // 삭제여부
        query.append(" AND a.is_deleted = 'N' ");
        
        // 작업서 발생 구분
        query.append(" AND a.wo_gen_type != 'PMPLAN' ");
        
        //월
        query.getAndDateQuery("a.wkor_date", reqRptPreWoreqDetailListDTO.getMonth().replace("-", "")+"01", DateUtil.plusLastDayOfMonth(reqRptPreWoreqDetailListDTO.getMonth().replace("-", "")));
        // 작업상태
        query.append(" AND a.wo_status IN ('PRC', 'C') ");
        
        return query.toString();
    }
    
    public String findTotalCount(ReqRptPreWoreqDetailListDTO reqRptPreWoreqDetailListDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                    ");
        query.append("       COUNT(1)                                                                                           ");
        query.append("  FROM TAWORKORDER a INNER JOIN TAWOEQUIP b ON a.comp_no = b.comp_no AND a.wkor_id = b.wkor_id            ");
        query.append("                      LEFT OUTER JOIN TAWOREQRES c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id     ");
        query.append("                      LEFT OUTER JOIN TAWOREQ d ON c.comp_no = d.comp_no AND c.woreq_id = d.woreq_id      ");
        query.append(" WHERE 1 = 1                                                                                              ");
        query.append(this.getWhere(reqRptPreWoreqDetailListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
