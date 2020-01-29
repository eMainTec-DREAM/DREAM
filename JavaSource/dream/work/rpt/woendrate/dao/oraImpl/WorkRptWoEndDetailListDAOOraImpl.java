package dream.work.rpt.woendrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.woendrate.dao.WorkRptWoEndDetailListDAO;
import dream.work.rpt.woendrate.dto.WorkRptWoEndDetailListDTO;

/**
 * 작업오더 일마감 처리율 목록  - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptWoEndDetailListDAOTarget"
 * @spring.txbn id="workRptWoEndDetailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptWoEndDetailListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkRptWoEndDetailListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptWoEndDetailListDTO
     * @return List
     */
    public List findList(WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 		");
        query.append("		''																									       AS SEQNO 		");
        query.append("     ,a.wo_no																								       AS WoNum			");
        query.append("    ,a.description																				 		       AS WODESC		");
        query.append("    ,a.start_date																					 		       AS STARTDATE		");
        query.append("    ,a.end_date																						 	       AS woComplete	");
        query.append("    ,(SELECT aa.item_no FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) 	       AS EQUIPNO		");
        query.append("    ,(SELECT aa.description FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id)       AS equipName		");
        query.append("    ,(SELECT aa.description FROM TADEPT aa WHERE a.comp_no = aa.comp_no AND a.dept_id = aa.dept_id) 		       AS workDept		");
        query.append("    ,(SELECT aa.emp_name FROM TAEMP aa WHERE a.comp_no = aa.comp_no AND a.emp_id = aa.emp_id)			 	       AS WoManager 	");
        query.append("    ,ROUND(a.close_date,8)																				       AS WoEndDate		");
        query.append("    ,b.wkor_id                                                                                            	   AS WKORID		");
        query.append("    ,(select aa.equip_id from taequipment aa where aa.comp_no = b.comp_no and aa.equip_id = b.equip_id) 		   AS EQUIPID		");
        query.append("    ,(SELECT aa.eqctg_type from taequipment aa where aa.comp_no = b.comp_no and aa.equip_id = b.equip_id) 	   AS EQCTGTYPE		");
        query.append("    ,(SELECT aa.param1 FROM TACDSYSD aa WHERE aa.list_Type = a.wo_type||'_TYPE' AND aa.cdsysd_no = a.pm_type)    AS PARAM			");
        query.append("FROM TAWORKORDER a INNER JOIN TAWOEQUIP b ON a.comp_no= b.comp_no AND a.wkor_id = b.wkor_id								");
        query.append("WHERE 1=1																													");
        query.append(" AND a.wo_status IN('PRC', 'C')																							");
        query.append(this.getWhere(workRptWoEndDetailListDTO, user)																				);
        query.getOrderByQuery("a.wkor_date", workRptWoEndDetailListDTO.getOrderBy(), workRptWoEndDetailListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptWoEndDetailListDTO.getIsLoadMaxCount(), workRptWoEndDetailListDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     *   
     * @param workRptWoEndDetailListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        //공장
        query.getCodeLikeQuery("a.plant", "(SELECT xx.description FROM TAPLANT xx WHERE xx.comp_no = '"+user.getCompNo()+"' AND a.plant = xx.plant )", 
        		workRptWoEndDetailListDTO.getPlantId(), workRptWoEndDetailListDTO.getPlantDesc());
        query.getStringEqualQuery("a.is_deleted", "N");
        //일자
        //query.getAndDateQuery("a.wkor_date", workRptWoEndDetailListDTO.getStartDate(), workRptWoEndDetailListDTO.getEndDate());
        query.getLikeQuery("a.wkor_date", workRptWoEndDetailListDTO.getMonth());
        
        return query.toString();
    }

    public String findTotalCount(
            WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 							");
        query.append(" COUNT(1)							");
        query.append("FROM TAWORKORDER a				");
        query.append("WHERE 1=1							");
        query.append(" AND a.wo_status IN ('PRC', 'C')	");
        query.append(this.getWhere(workRptWoEndDetailListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
