package dream.work.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.list.dao.WorkListCinsPlanMstrListDAO;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;

/**
 * WorkListCinsPlanMstr Page - List DAO implements
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListCinsPlanMstrListDAOTarget"
 * @spring.txbn id="workListCinsPlanMstrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListCinsPlanMstrListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkListCinsPlanMstrListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: workListCinsPlanMstrListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListCinsPlanMstrCommonDTO
     * @return List
     */
    public List findList(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();

        query.append("  SELECT                                                  ");
        query.append("        ''                seqNo                           ");
        query.append("      , ''                isDelCheck                      ");
        query.append("      , x.pm_id           pmId                            ");
        query.append("      , z.pm_no           pmNo                            ");
        query.append("      , z.description     pmDesc                          ");
        query.append("      , x.pmequip_id      pmEquipId                       ");
        query.append("      , x.pminsdsched_id  pminsdschedId                   ");
        query.append("      , x.pminsdlist_id   pminsdlistId                    ");
        query.append("      , x.plan_date       planDate                        ");
        query.append("      , (SELECT a.item_no FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)         equipNo             ");
        query.append("      , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id)     equipDesc           ");
        query.append("      , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = (SELECT d.eqloc_id FROM TAEQUIPMENT d WHERE d.comp_no = x.comp_no AND d.equip_id = x.equip_id))       eqLocDesc               ");
        query.append("      , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = z.dept_id)            deptDesc            ");
        query.append("      , (CASE WHEN x.pminsdlist_id IS NOT NULL THEN 'Y' ELSE 'N' END)                                         isActive            ");
        query.append("      , NVL((SELECT (CASE WHEN a.pmsched_status = 'C' THEN 'Y' ELSE 'N' END )FROM TAPMINSDLIST a WHERE a.comp_no = x.comp_no AND a.pminsdlist_id = x.pminsdlist_id),'N') isComplete                   ");
        query.append("      , (CASE WHEN (NVL((SELECT (CASE WHEN a.pmsched_status = 'C' THEN 'Y' ELSE 'N' END) FROM TAPMINSDLIST a WHERE a.comp_no = x.comp_no AND a.pminsdlist_id = x.pminsdlist_id),'N')) = 'Y'           ");
        query.append("              THEN ((SELECT a.wkor_date FROM TAPMINSDLIST a WHERE a.comp_no = x.comp_no AND a.pminsdlist_id = x.pminsdlist_id)) ELSE '' END)         insDate              ");
        query.append("      , (SELECT a.emp_id FROM TAPMINSDLIST a WHERE a.comp_no = x.comp_no AND a.pminsdlist_id = x.pminsdlist_id)       insById             ");
        query.append("      , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = (SELECT b.emp_id FROM TAPMINSDLIST b WHERE b.comp_no = x.comp_no AND b.pminsdlist_id = x.pminsdlist_id))   insByDesc               ");
        query.append("      , x.sched_date                                  ");
        query.append("      , x.pmsched_status  pmStatusCode                ");
        query.append("      , x.equip_id        equipId                     ");
        query.append("      , z.product_id      productId                   ");
        query.append("      , (SELECT a.description FROM TAPRODUCT a WHERE a.product_id = z.product_id)                     productDesc                         ");
        query.append("      , z.emp_id          empId                                                                                                           ");
        query.append("      , (SELECT a.emp_name FROM TAEMP a WHERE z.emp_id = a.emp_id) empDesc                                                                ");
        query.append("      , (SELECT param2 FROM TACDSYSD WHERE cdsysd_no = z.pm_type AND list_type= z.wo_type||'_TYPE')   param                               ");
        query.append("      , x.is_deleted                                                                                                                      ");
        query.append("  FROM TAPMINSDSCHED x INNER JOIN TAPMEQUIP y  ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id AND x.pmequip_id = y.pmequip_id             ");
        query.append("                       INNER JOIN TAPMLST z ON x.comp_no = z.comp_no AND x.pm_id = z.pm_id                                                ");
        query.append("  WHERE 1=1                                           ");
        query.append(this.getWhere(workListCinsPlanMstrCommonDTO, user));
        
        query.getOrderByQuery("x.plan_date", workListCinsPlanMstrCommonDTO.getOrderBy(), workListCinsPlanMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workListCinsPlanMstrCommonDTO.getIsLoadMaxCount(), workListCinsPlanMstrCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param workListCinsPlanMstrCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        String lang = user.getLangId();
        
        String startDate = workListCinsPlanMstrCommonDTO.getFilterStartPlanDate();
        String endDate = workListCinsPlanMstrCommonDTO.getFilterEndPlanDate();
        String compNo  = user.getCompNo();
        
        if(!"".equals(workListCinsPlanMstrCommonDTO.getPmInsDSchedId())){
            query.getAndQuery("x.pminsdsched_id", workListCinsPlanMstrCommonDTO.getPmInsDSchedId());
            return query.toString();
        }
        
        //회사코드
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("z.pm_type", "CINS");
        query.getStringEqualQuery("z.wo_type", "PMI");
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        //작업일자
        query.getAndDateQuery("x.plan_date", startDate, endDate);
        
        //생산제품
        if(!"".equals(workListCinsPlanMstrCommonDTO.getFilterProductId()))
        {
            query.getAndQuery("z.product_id", workListCinsPlanMstrCommonDTO.getFilterProductId());
        }
        else if(!"".equals(workListCinsPlanMstrCommonDTO.getFilterProductDesc()))
        {
            query.append("AND z.product_id IN (SELECT a.product_id  ");
            query.append("                     FROM TAPRODUCT a     ");
            query.append("                     WHERE 1=1            ");
            query.getStringEqualQuery("a.comp_no", user.getCompNo());
            query.getLikeQuery("a.description", workListCinsPlanMstrCommonDTO.getFilterProductDesc());
            query.append("                   )                      ");
        }
        
        // 설비 (ID/DESC)
        if(!"".equals(workListCinsPlanMstrCommonDTO.getFilterEquipDesc()) && !"".equals(workListCinsPlanMstrCommonDTO.getFilterEquipId()))
        {
            query.getAndQuery("x.equip_id", workListCinsPlanMstrCommonDTO.getFilterEquipId());
        }
        else if(!"".equals(workListCinsPlanMstrCommonDTO.getFilterEquipDesc()))
        {
            query.append("AND x.equip_id IN (SELECT b.equip_id       ");
            query.append("                      FROM TAEQUIPMENT b   ");
            query.append("                      WHERE 1=1            ");
            query.getStringEqualQuery("b.comp_no", user.getCompNo());
            query.getLikeQuery("b.description", workListCinsPlanMstrCommonDTO.getFilterEquipDesc());
            query.append("                   )                       ");
        }
        
        //위치
        if(!"".equals(workListCinsPlanMstrCommonDTO.getFilterEqLocId())||!"".equals(workListCinsPlanMstrCommonDTO.getFilterEqLocDesc())){
                
            query.append("AND x.equip_id IN (SELECT b.equip_id       ");
            query.append("                     FROM  TAEQUIPMENT b   ");
            query.append("                     WHERE 1=1             ");
            query.getStringEqualQuery("b.comp_no", user.getCompNo());
            query.getEqLocLevelQuery("b.eqloc_id", workListCinsPlanMstrCommonDTO.getFilterEqLocId(), workListCinsPlanMstrCommonDTO.getFilterEqLocDesc(), user.getCompNo());
            query.append("                                    )      ");
        }
        
        //부서명
        query.getDeptLevelQuery("z.dept_id", workListCinsPlanMstrCommonDTO.getFilterDeptId(), workListCinsPlanMstrCommonDTO.getFilterDeptDesc(), user.getCompNo());
        
        //담당자
        query.getCodeLikeQuery("z.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = z.emp_id AND a.comp_no='"+compNo+"')", 
                workListCinsPlanMstrCommonDTO.getFilterEmpId(), workListCinsPlanMstrCommonDTO.getFilterEmpDesc());

        //점검일정발행여부
        if("Y".equals(workListCinsPlanMstrCommonDTO.getFilterIsActive().toUpperCase()))
        {
            query.append("AND x.pminsdlist_id IS NOT NULL");
        }
        else if ("N".equals(workListCinsPlanMstrCommonDTO.getFilterIsActive().toUpperCase()))
        {
            query.append("AND x.pminsdlist_id IS NULL");
        }
        
        //점검완료여부
        if(!"".equals(workListCinsPlanMstrCommonDTO.getFilterIsComplete().toUpperCase()))
        {
            query.append("AND UPPER('"+workListCinsPlanMstrCommonDTO.getFilterIsComplete()+"') ");
            query.append(" = NVL((SELECT (CASE WHEN a.pmsched_status = 'C' THEN 'Y' ELSE 'N' END )FROM TAPMINSDLIST a WHERE a.comp_no = x.comp_no AND a.pminsdlist_id = x.pminsdlist_id),'N') ");
        }
        
        //최신버전의 설비의 작업만 보여줌.
        query.append("AND NOT EXISTS (SELECT a.equip_id							");
    	query.append("					 FROM  TAEQUIPMENT a            		");
    	query.append("					 WHERE 1=1                    			");
    	query.append("					 AND   a.equip_id = x.equip_id			");
    	query.getStringEqualQuery("a.comp_no", user.getCompNo());
    	query.getAndQuery("a.is_last_version", "N");
        query.append("									)						");

        return query.toString();
    }

    public String findTotalCount(
            WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("    count(1)                                          ");
        query.append("  FROM TAPMINSDSCHED x INNER JOIN TAPMEQUIP y  ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id AND x.pmequip_id = y.pmequip_id             ");
        query.append("                       INNER JOIN TAPMLST z ON x.comp_no = z.comp_no AND x.pm_id = z.pm_id                                                ");
        query.append("  WHERE 1=1                                           ");
        query.append(this.getWhere(workListCinsPlanMstrCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
    
    public String checkSched(String pmInsSchedId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT  PMSCHED_STATUS         ");
    	query.append("FROM TAPMINSDSCHED             ");
        query.append("WHERE comp_no = ?              ");
        query.append("  AND PMINSDSCHED_ID =  ?      ");
        Object[] objects = new Object[]{
        		user.getCompNo()
        		,pmInsSchedId
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    public int updateDeleteTagSched(String pmInsSchedId, User user)
    {
    	
    	QueryBuffer query = new QueryBuffer();
    	
    	int result = 0;
    	
        query.append("UPDATE TAPMINSDLIST  SET      ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?      ");
        query.append("WHERE COMP_NO = ?             ");
    	query.append("  AND PMINSDLIST_ID = (        ");
    	query.append("                         SELECT PMINSDLIST_ID              ");
    	query.append("                         FROM TAPMINSDSCHED                ");
    	query.append("                         WHERE COMP_NO = ?                 ");
    	query.append("                              AND PMINSDSCHED_ID = ?       ");
    	query.append("                        )                                  ");
    	Object[] objects = new Object[]{
    			user.getEmpId()
    			,DateUtil.getDateTime()
        		,user.getCompNo()
        		,user.getCompNo()
        		,pmInsSchedId
        };
        result = this.getJdbcTemplate().update(query.toString(),objects);
        
        
        query.setClear();
        query.append("UPDATE TAPMINSDSCHED  SET     ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?     ");
        query.append("WHERE COMP_NO = ?              ");
        query.append("  AND PMINSDSCHED_ID =  ?      ");
        objects = new Object[]{
        		user.getEmpId()
        		,DateUtil.getDateTime()
        		,user.getCompNo()
        		,pmInsSchedId
        };
        
        return  this.getJdbcTemplate().update(query.toString(),objects);
    }
    
    public int deleteSched(String pmInsSchedId, User user)
    {
    	
    	QueryBuffer query = new QueryBuffer();
    	
    	int result = 0;
    	
    	query.append("DELETE FROM TAPMINSDSCHED      ");
    	query.append("WHERE comp_no = ?              ");
    	query.append("  AND PMINSDSCHED_ID =  ?      ");
    	Object[] objects = new Object[]{
    			user.getCompNo()
    			,pmInsSchedId
    	};
    	
    	return  this.getJdbcTemplate().update(query.toString(),objects);
    }
    
}
