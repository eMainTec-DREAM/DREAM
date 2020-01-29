package dream.req.rpt.prewoplanrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.req.rpt.prewoplanrate.dao.ReqRptPreWoPlanDetailListDAO;
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanDetailListDTO;

/**
 * 작업오더 사전 계획 수립률 목록  - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="reqRptPreWoPlanDetailListDAOTarget"
 * @spring.txbn id="reqRptPreWoPlanDetailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqRptPreWoPlanDetailListDAOOraImpl  extends BaseJdbcDaoSupportOra implements ReqRptPreWoPlanDetailListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptPreWoPlanDetailListDTO
     * @return List
     */
    public List findList(ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 																											");
        query.append("     a.wo_no																									AS WoNum			");
        query.append("    ,a.description																							AS WODESC			");
        query.append("    ,a.start_date																								AS STARTDATE		");
        query.append("    ,a.end_date																								AS woComplete		");
        query.append("    ,(select aa.item_no from taequipment aa where b.comp_no = aa.comp_no and b.equip_id = aa.equip_id) 		AS EQUIPNO			");
        query.append("    ,(select aa.description from taequipment aa where b.comp_no = aa.comp_no and b.equip_id = aa.equip_id) 	AS equipName 		");
        query.append("    ,(select aa.description from tadept aa where a.comp_no = aa.comp_no and a.dept_id = aa.dept_id) 			AS workDept 		");
        query.append("    ,(select aa.emp_name from taemp aa where a.comp_no = aa.comp_no and a.emp_id = aa.emp_id)					AS WoManager 		");
        query.append("    ,round(a.cre_date,8)																						AS WoCreDate		");
        query.append("    ,b.wkor_id                                                                                            	AS WKORID			");
        query.append("    ,(select aa.equip_id from taequipment aa where aa.comp_no = b.comp_no and aa.equip_id = b.equip_id) 		AS EQUIPID			");
        query.append("    ,(SELECT aa.eqctg_type from taequipment aa where aa.comp_no = b.comp_no and aa.equip_id = b.equip_id) 	AS EQCTGTYPE		");
        query.append("    ,(SELECT aa.param1 FROM TACDSYSD aa WHERE aa.list_Type = a.wo_type||'_TYPE' AND aa.cdsysd_no = a.pm_type) AS PARAM			");
        query.append("FROM TAWORKORDER a INNER JOIN TAWOEQUIP b ON a.comp_no= b.comp_no AND a.wkor_id = b.wkor_id					");
        query.append("WHERE 1=1																										");
        query.append("  AND a.wo_status in('PRC', 'C')																				");
        query.append(this.getWhere(reqRptPreWoPlanDetailListDTO, user));
        query.getOrderByQuery("a.wkor_date ASC", reqRptPreWoPlanDetailListDTO.getOrderBy(), reqRptPreWoPlanDetailListDTO.getDirection());
                
        return getJdbcTemplate().queryForList(query.toString(reqRptPreWoPlanDetailListDTO.getIsLoadMaxCount(), reqRptPreWoPlanDetailListDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     *   
     * @param reqRptPreWoPlanDetailListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        //공장
        query.getCodeLikeQuery("a.plant", "(SELECT xx.description FROM TAPLANT xx WHERE xx.comp_no = '"+user.getCompNo()+"' AND a.plant = xx.plant )", 
        		reqRptPreWoPlanDetailListDTO.getPlantId(), reqRptPreWoPlanDetailListDTO.getPlantDesc());
        query.getStringEqualQuery("a.is_deleted", "N");
        //일자
        query.getLikeQuery("a.wkor_date", reqRptPreWoPlanDetailListDTO.getMonth());
        //query.getAndDateQuery("a.wkor_date", reqRptPreWoPlanDetailListDTO.getStartDate(), reqRptPreWoPlanDetailListDTO.getEndDate());
        
       
        return query.toString();
    }

    public String findTotalCount(
            ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT 																											");
        query.append("  COUNT(1)																										");
        query.append("FROM TAWORKORDER a INNER JOIN TAWOEQUIP b ON a.comp_no= b.comp_no AND a.wkor_id = b.wkor_id						");
        query.append("WHERE 1=1																											");
        query.append(" AND a.wo_status in('PRC', 'C')		");
        query.append(this.getWhere(reqRptPreWoPlanDetailListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
