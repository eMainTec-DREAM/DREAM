package dream.work.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.WorkListCinsPlanMstrDetailDAO;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;
import dream.work.list.dto.WorkListCinsPlanMstrDetailDTO;

/**
 * 일상점검목록 - 상세 dao
 * 
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workListCinsPlanMstrDetailDAOTarget"
 * @spring.txbn id="workListCinsPlanMstrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListCinsPlanMstrDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkListCinsPlanMstrDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id: WorkListCinsPlanMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListCinsPlanMstrCommonDTO
     * @return
     */
    String compNo = "";
    public WorkListCinsPlanMstrDetailDTO findDetail(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("  SELECT                                                  ");
        query.append("        x.pm_id           pmId                            ");
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
        query.append("      , ISNULL((SELECT (CASE WHEN a.pmsched_status = 'C' THEN 'Y' ELSE 'N' END )FROM TAPMINSDLIST a WHERE a.comp_no = x.comp_no AND a.pminsdlist_id = x.pminsdlist_id),'N') isComplete                   ");
        query.append("      , (CASE WHEN (ISNULL((SELECT (CASE WHEN a.pmsched_status = 'C' THEN 'Y' ELSE 'N' END) FROM TAPMINSDLIST a WHERE a.comp_no = x.comp_no AND a.pminsdlist_id = x.pminsdlist_id),'N')) = 'Y'           ");
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
        query.append("      , (SELECT param2 FROM TACDSYSD WHERE cdsysd_no = z.pm_type AND list_type= z.wo_type+'_TYPE')   param                               ");
        query.append("      , x.is_deleted                                                                                                                      ");
        query.append("  FROM TAPMINSDSCHED x INNER JOIN TAPMEQUIP y  ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id AND x.pmequip_id = y.pmequip_id             ");
        query.append("                       INNER JOIN TAPMLST z ON x.comp_no = z.comp_no AND x.pm_id = z.pm_id                                                ");
        query.append("  WHERE 1=1                                           ");
        query.append("    AND x.comp_no           = ?       ");
        query.append("    AND x.pminsdsched_id    = ?       ");

        Object[] objects = new Object[] {
                user.getCompNo()
              , workListCinsPlanMstrCommonDTO.getPmInsDSchedId()
        };
    
        compNo = user.getCompNo();
        
        WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO = 
                (WorkListCinsPlanMstrDetailDTO)getJdbcTemplate().query(query.toString(), getObject(objects), new MwareExtractor(new WorkListCinsPlanMstrDetailDTO()));
        
        return workListCinsPlanMstrDetailDTO;
        
    }
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id: WorkListCinsPlanMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListCinsPlanMstrDetailDTO
     * @return
     */
    public int insertDetail(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
        
    	query.append("  INSERT INTO TAPMINSDSCHED                   ");
        query.append("  (  comp_no            , pminsdsched_id      ");
        query.append("   , pm_id              , pmequip_id          ");
        query.append("   , equip_id           , plan_init_date      ");
        query.append("   , plan_date          , sched_date          ");
        query.append("   , pmsched_status                           ");
        query.append("  )                                           ");
        query.append("  SELECT                                      ");
        query.append("     comp_no            , ?                   ");
        query.append("   , pm_id              , ?                   ");
        query.append("   , (SELECT a.equip_id FROM TAPMEQUIP a WHERE a.pmequip_id = ? )  , CONVERT(VARCHAR, GETDATE(), 112)       ");
        query.append("   , ?                  , ?                   ");
        query.append("   , ?                                        ");
        query.append("   FROM TAPMLST x                             ");
        query.append("   WHERE pm_id = ?                            ");
        query.append("   AND comp_no = ?                            ");

        Object[] objects = new Object[] {
                workListCinsPlanMstrCommonDTO.getPmInsDSchedId()
              , workListCinsPlanMstrDetailDTO.getPmEquipId()
              , workListCinsPlanMstrDetailDTO.getPmEquipId()
              , workListCinsPlanMstrDetailDTO.getPlanDate()
              , workListCinsPlanMstrDetailDTO.getPlanDate()
              , "S"
              , workListCinsPlanMstrDetailDTO.getPmId()
              , user.getCompNo()
        };
        
        
        rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
        
        return rtnValue;
    }
    /**
     * detail update
     * @author youngjoo38
     * @version $Id: WorkListCinsPlanMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListCinsPlanMstrDetailDTO
     * @return
     */
    public int updateDetail(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user)
    {   
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSDSCHED SET           ");
        query.append("        comp_no           = ?      ");
        query.append("      , pm_id             = ?      ");
        query.append("      , pmequip_id        = ?      ");
        query.append("      , equip_id          = (SELECT a.equip_id FROM TAPMEQUIP a WHERE a.pmequip_id = ? )       ");
        query.append("      , plan_date         = ?      "); // 계획일자 
        query.append("      , sched_date        = ?      "); 
        query.append("WHERE  comp_no            = ?      ");
        query.append("  AND  pminsdsched_id     = ?      ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                , workListCinsPlanMstrDetailDTO.getPmId()
                , workListCinsPlanMstrDetailDTO.getPmEquipId()
                , workListCinsPlanMstrDetailDTO.getPmEquipId()
                , workListCinsPlanMstrDetailDTO.getPlanDate()
                , workListCinsPlanMstrDetailDTO.getPlanDate()
                , user.getCompNo()
                , workListCinsPlanMstrDetailDTO.getPmInsDSchedId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
        
        return rtnValue;
    }

    @Override
    public String checkList(
            WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                        ");
        query.append("       pminsdlist_id          ");
        query.append("FROM   TAPMINSDLIST a         ");
        query.append("WHERE  1 = 1                  ");
        query.getAndQuery("a.pminsdlist_id", workListCinsPlanMstrCommonDTO.getPmInsDListId()); // 점검기준ID
        query.getAndQuery("a.wkor_date", workListCinsPlanMstrCommonDTO.getPopupWorkDate()); // 작업일자

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
    
    public String checkPoint(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO,User user) {

        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT ISNULL(val,0)                                             ");
        query.append("  FROM (SELECT CONVERT((SUM(DECODE(DECODE(y.check_type,'SEN',x.pm_point_rslt_status,'VAL',x.result_value),null,1,0))) val  ");
        query.append("          FROM TAPMINSDPOINT x RIGHT OUTER JOIN TAPMPOINT y   ");
        query.append("            ON x.comp_no = y.comp_no                          ");
        query.append("           AND x.pm_point_id = y.pm_point_id                  ");
        query.append("         WHERE 1=1                                            ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x. pminsdlist_id", workListCinsPlanMstrDetailDTO.getPmInsDListId());
        query.append("        )                                                     ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id: workListCinsPlanMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListCinsPlanMstrDetailDTO
     * @return
     */
    public int completeDetail(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("INSERT INTO TAPMINSDLIST (                ");
        query.append("         comp_no         , pminsdlist_id  ");
        query.append("       , pminsdsched_id  , pm_id          ");
        query.append("       , equip_id                         ");
        query.append("       , pmsched_status  , description    ");
        query.append("       , wo_type         , pm_type        ");
        query.append("       , wkor_date       , shift_type     ");
        query.append("       , start_date      , start_time     ");
        query.append("       , end_date        , end_time       ");
        query.append("       , dept_id         , wkctr_id       ");
        query.append("       , emp_id          , plant          ");
        query.append("       , work_number     , product_id     ");
        query.append(") VALUES (                                ");
        query.append("      ?             , NEXT VALUE FOR sqapminsdlist_id        ");
        query.append("    , ?             , ?                   ");
        query.append("    , (SELECT a.equip_id FROM TAPMINSDSCHED a WHERE a.pminsdsched_id = ?)                                   ");
        query.append("    , 'S'           , (SELECT a.description FROM TAPMLST a WHERE a.pm_id = ?)     ");
        query.append("    , 'PMI'         , 'CINS'              ");
        query.append("    , ?             , ?                   ");    // plandate
        query.append("    , ?             , ?                   ");
        query.append("    , ?             , ?                   ");
        query.append("    , ?             , ?                   ");
        query.append("    , ?             , ?                   ");
        query.append("    , (SELECT a.work_number FROM TAPMLST a WHERE a.pm_id = ?)   , (SELECT a.product_id FROM TAPMLST a WHERE a.pm_id = ?)    ");
        query.append("    )                                     ");

        Object[] objects = new Object[] {
                workListCinsPlanMstrDetailDTO.getCompNo(),
                workListCinsPlanMstrDetailDTO.getPmInsDSchedId(),
                workListCinsPlanMstrDetailDTO.getPmId(),
                workListCinsPlanMstrDetailDTO.getPmInsDSchedId(),
                workListCinsPlanMstrDetailDTO.getPmId(),
                workListCinsPlanMstrDetailDTO.getPlanDate(),
                workListCinsPlanMstrDetailDTO.getShiftTypeId(),
                workListCinsPlanMstrDetailDTO.getStartDate(),
                workListCinsPlanMstrDetailDTO.getStartTime(),
                workListCinsPlanMstrDetailDTO.getEndDate(),
                workListCinsPlanMstrDetailDTO.getEndTime(),
                workListCinsPlanMstrDetailDTO.getDeptId(),
                workListCinsPlanMstrDetailDTO.getWkCtrId(),
                workListCinsPlanMstrDetailDTO.getEmpId(),
                user.getPlant(),
                workListCinsPlanMstrDetailDTO.getPmId(),
                workListCinsPlanMstrDetailDTO.getPmId()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
        
        return rtnValue;
    }
    
 // 해당 PM_ID의 PMPOINT 갯수 알아오기
    public int getPmInsdPointCnt(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("  SELECT COUNT(1)    ");
        query.append("  FROM TAPMPOINT     ");
        query.append("  WHERE pm_id =   ?  ");
        
        Object[] objects = new Object[] {
                workListCinsPlanMstrDetailDTO.getPmId(),
        };
        
        rtnValue = Integer.parseInt(QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects)));
        
        return rtnValue;
    }
    
    // PMPOINT 갯수만큼 INSERT 
    public int inserPmInsdPoint(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("  INSERT INTO TAPMINSDPOINT (                         ");
        query.append("        comp_no                , pminsdpoint_id       ");
        query.append("      , pminsdlist_id          , pminsdsched_id       ");
        query.append("      , pm_id                  , pmequip_id           ");
        query.append("      , equip_id               , pmsched_status       ");
        query.append("      , pm_point_id                                   ");
        query.append("      , REMARK                 , pm_point_rslt_status ");
        query.append("  )                                                   ");
        query.append("  ( SELECT                                            ");
        query.append("        comp_no                , NEXT VALUE FOR sqapminsdpoint_id     ");
        query.append("      , pminsdlist_id          , ?                    ");
        query.append("      , ?                      , ?                    ");
        query.append("      , equip_id               , 'P'                  ");
        query.append("      , pm_point_id            ,remark , 'GD'                 ");
        query.append("  FROM (                                              ");
        query.append("    SELECT                                            ");
        query.append("        comp_no                , row_number() over(order by x.pm_point_id) rno           ");
        query.append("      , (SELECT MAX(pminsdlist_id)                    ");
        query.append("          FROM TAPMINSDLIST ) AS pminsdlist_id        ");
        query.append("      , (SELECT equip_id FROM TAPMEQUIP WHERE pmequip_id = ? )    AS equip_id                  ");
        query.append("      , pm_point_id   , remark 				        ");
        query.append("      FROM TAPMPOINT x                                ");
        query.append("      WHERE x.pm_id = ?                               ");
        query.append("        ) a                                           ");
        query.append("  WHERE a.rno = ?                                     ");
        query.append("  )                                                   ");

        
        Object[] objects = new Object[] {
                workListCinsPlanMstrDetailDTO.getPmInsDSchedId()
              , workListCinsPlanMstrDetailDTO.getPmId()
              , workListCinsPlanMstrDetailDTO.getPmEquipId()
              , workListCinsPlanMstrDetailDTO.getPmEquipId()
              , workListCinsPlanMstrDetailDTO.getPmId()
              , workListCinsPlanMstrDetailDTO.getPmpointCnt()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
        
        return rtnValue;
    }
    
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id: WorkPmiDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiDetailDTO
     * @return
     */
    public int completeSched(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("UPDATE TAPMINSDSCHED SET      ");
        //query.append("     pmsched_status = 'C'     ");
        query.append("     check_by = ?             ");
        query.append("   , actual_date = ?          ");
        query.append("   , actual_time = ?          ");
        query.append("   , pminsdlist_id =          ");
        query.append("   (SELECT MAX(pminsdlist_id) ");
        query.append("    FROM TAPMINSDLIST )       ");
        query.append("WHERE pminsdsched_id = ?      ");
        query.append("AND comp_no = ?               ");

        Object[] objects = new Object[] {
                workListCinsPlanMstrDetailDTO.getEmpId()
                ,workListCinsPlanMstrDetailDTO.getStartDate()
                ,workListCinsPlanMstrDetailDTO.getStartTime()
                ,workListCinsPlanMstrDetailDTO.getPmInsDSchedId()
                ,compNo
        };

        rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
        
        return rtnValue;
    }
    
}