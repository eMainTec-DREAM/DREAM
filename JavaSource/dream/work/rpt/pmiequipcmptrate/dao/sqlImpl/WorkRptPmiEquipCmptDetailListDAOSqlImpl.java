package dream.work.rpt.pmiequipcmptrate.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.pmiequipcmptrate.dao.WorkRptPmiEquipCmptDetailListDAO;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptDetailListDTO;

/**
 * 예방점검 실행 상세 목록 DAO implements
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmiEquipCmptDetailListDAOTarget"
 * @spring.txbn id="workRptPmiEquipCmptDetailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmiEquipCmptDetailListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements WorkRptPmiEquipCmptDetailListDAO
{

    /**
     * grid find
     * @author  sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiEquipCmptDetailListDTO
     * @return List
     */
    public List findList(WorkRptPmiEquipCmptDetailListDTO workRptPmiEquipCmptDetailListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 																		");
        query.append("		''												AS seqNo 				");
        query.append("    , a.pminslist_id									AS pminslistNo			");
        query.append("    ,a.description									AS description			");
        query.append("    ,b.sched_date										AS schedDate			");
        query.append("    ,(CASE WHEN a.pmsched_status IN ('C', 'PRC') THEN a.start_date ELSE '' END)								AS endDate		");
        query.append("    ,(SELECT aa.item_no FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) 		AS itemNo		");
        query.append("    ,(SELECT aa.description FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id)	AS itemDesc 	");
        query.append("    ,(SELECT aa.description FROM TADEPT aa WHERE a.comp_no = aa.comp_no AND a.dept_id = aa.dept_id) 			AS deptDesc 	");
        query.append("    ,(SELECT aa.emp_name FROM TAEMP aa WHERE a.comp_no = aa.comp_no AND a.emp_id = aa.emp_id) 				AS empName 		");
        query.append("    ,a.equip_id										AS equipId 				");
        query.append("    ,(SELECT aa.eqctg_type FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) 	AS eqCtgType	");
        query.append("FROM TAPMINSLIST a INNER JOIN TAPMINSSCHED b  						 		");
        query.append("ON a.comp_no = b.comp_no		 												");
        query.append(" AND a.pminssched_id = b.pminssched_id										");
        query.append("WHERE 1=1																		");
        query.append(this.getWhere(workRptPmiEquipCmptDetailListDTO, user));
//        query.append("ORDER BY a.wkor_date ASC														");
        query.getOrderByQuery("a.comp_no", "a.wkor_date ASC", workRptPmiEquipCmptDetailListDTO.getOrderBy(), workRptPmiEquipCmptDetailListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPmiEquipCmptDetailListDTO.getIsLoadMaxCount(), workRptPmiEquipCmptDetailListDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  sy.yang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workRptPmiEquipCmptDetailListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptPmiEquipCmptDetailListDTO workRptPmiEquipCmptDetailListDTO, User user) throws Exception
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());

        query.getAndQuery("a.is_deleted", "N");
        
        // 공장
        query.getCodeLikeQuery("a.plant", "(SELECT aa.description FROM TAPLANT aa WHERE aa.comp_no = '"+user.getCompNo()+"' AND aa.plant = a.plant )", 
        		workRptPmiEquipCmptDetailListDTO.getPlantId(), workRptPmiEquipCmptDetailListDTO.getPlantDesc());

        // 월
        String yyyyMm = workRptPmiEquipCmptDetailListDTO.getYyyymm();
        
        if (yyyyMm != null && !"".equals(yyyyMm))
        {
        	// toMonth가 오늘이후면 오늘까지만 조회
        	if(DateUtil.compareDate(DateUtil.getDate(), DateUtil.plusLastDayOfMonth(yyyyMm)))
        	{
        		query.getAndDateQuery("b.sched_date", yyyyMm +"01", DateUtil.getDate());
        	}
        	else{
        		query.getAndDateQuery("b.sched_date", yyyyMm +"01", DateUtil.plusLastDayOfMonth(yyyyMm));
        	}
        }
        
        // 예방작업설비 삭제여부
        query.append("AND EXISTS (SELECT aa.equip_id                    ");
        query.append("            FROM TAPMEQUIP aa                    	");
        query.append("            WHERE 1=1                           	");
        query.append("			   AND aa.comp_no = b.comp_no			");
    	query.append("			    AND aa.pmequip_id = b.pmequip_id	");
        query.getAndQuery("aa.is_deleted", "N");
        query.append("           )                   					");
        
        return query.toString();
    }
    
    public String findTotalCount(WorkRptPmiEquipCmptDetailListDTO workRptPmiEquipCmptDetailListDTO, User user) throws Exception 
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     ");
        query.append("SELECT                                            	");
        query.append("    	count(1)										");
        query.append("FROM TAPMINSLIST a INNER JOIN TAPMINSSCHED b  		");
        query.append("ON a.comp_no = b.comp_no		 						");
        query.append(" AND a.pminssched_id = b.pminssched_id				");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(workRptPmiEquipCmptDetailListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
