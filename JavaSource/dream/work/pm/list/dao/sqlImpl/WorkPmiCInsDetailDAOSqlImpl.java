package dream.work.pm.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.WorkPmiCInsDetailDAO;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsDetailDTO;

/**
 * 일상점검목록 - 상세 dao
 * 
 * @author youngjoo38
 * @version $Id: WorkPmiCInsDetailDAO.java,v 1.0 2015/12/02 08:25:47 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmiCInsDetailDAOTarget"
 * @spring.txbn id="workPmiCInsDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmiCInsDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmiCInsDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id: WorkPmiCInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmiCInsCommonDTO
     * @return
     */
    public WorkPmiCInsDetailDTO findDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                   ");
        query.append("        ''                                                               seqNo           ");
        query.append("      , ''                                                               isDelCheck      ");
        query.append("      , x.pmInsDlist_id                                                  pmInsDListId    ");
        query.append("      , x.pmInsDlist_id                                                  pmInsDListNo    ");
        query.append("      , x.description                                                    description     ");
        query.append("      , x.wkor_date                                                      wkorDate        ");
        query.append("      , x.eqruntime                                                      operatingTime   ");
        query.append("      , x.equip_id                                                       equipId         ");
        query.append("      , (SELECT a.description                                                            ");
        query.append("         FROM TAEQUIPMENT a                                                              ");
        query.append("         WHERE a.equip_id = x.equip_id)                                  equipDesc       ");
        query.append("      , x.dept_id                                                        deptId          ");
        query.append("      , (SELECT a.description                                                            ");
        query.append("           FROM TADEPT a                                                                 ");
        query.append("          WHERE a.comp_no = x.comp_no                                                    ");
        query.append("            AND a.dept_id = x.dept_id)                                   deptDesc        ");
        query.append("       ,  x.wkctr_id                                                     wkctrId         ");
        query.append("       , (SELECT a.description                                                           ");
        query.append("          FROM TAWKCTR a                                                                 ");
        query.append("          WHERE a.comp_no = x.comp_no                                                    ");
        query.append("            AND a.wkctr_id = x.wkctr_id)                                 wkCtrDesc       ");
        query.append("       , x.shift_type                                                    shiftTypeId     ");
        query.append("       , dbo.SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+user.getLangId()+"')    shiftTypeDesc       ");
        query.append("       , x.wo_type                                                       woTypeId        ");
        query.append("       , dbo.SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+user.getLangId()+"')          woTypeDesc      ");
        query.append("       , x.pm_type                                                       pmTypeId        ");
        query.append("       , dbo.SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+user.getLangId()+"')          pmTypeDesc      ");
        query.append("       , x.work_time                                                     workTime        ");
        query.append("       , x.start_time                                                    startTime       ");
        query.append("       , x.end_time                                                      endTime         ");
        query.append("       , x.emp_id                                                        empId           ");
        query.append("       , (SELECT a.emp_name                                                              ");
        query.append("          FROM TAEMP a                                                                   ");
        query.append("          WHERE a.comp_no = x.comp_no                                                    ");
        query.append("             AND a.emp_id = x.emp_id)                                    empDesc         ");
        query.append("       , (SELECT a.param1 FROM TACDSYSD a WHERE a.list_Type= x.wo_type+'_TYPE' AND a.cdsysd_no=x.pm_type) param ");
        query.append("       , (SELECT a.eqctg_type                                                            ");
        query.append("          FROM TAEQUIPMENT a                                                             ");
        query.append("          WHERE a.equip_id = x.equip_id)                                 eqctgTypeId     ");
        query.append("       , (SELECT dbo.SFACODE_TO_DESC(a.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  eqctgTypeDesc  ");
        query.append("          FROM TAEQUIPMENT a                                                             ");
        query.append("          WHERE a.equip_id = x.equip_id)                                 eqctgType       ");
        query.append("       , (SELECT a.description                                                           ");
        query.append("          FROM TAPMLST a                                                                 ");
        query.append("          WHERE a.pm_id = x.pm_id)                                       pmDesc          ");
        query.append("       , x.start_date                                                    startDate       ");
        query.append("       , x.end_date                                                      endDate         ");
        query.append("       , x.measure_time                                                  measureTime     ");
        query.append("       , x.close_id                                                      closeById       ");
        query.append("       , (SELECT a.emp_name                                                              ");
        query.append("          FROM TAEMP a                                                                   ");
        query.append("          WHERE a.comp_no = x.comp_no                                                    ");
        query.append("             AND a.emp_id = x.close_id)                                  closeBy         ");
        query.append("       , x.remark                                                        remark          ");
        query.append("       , x.pmsched_status                                                schedStatusId   ");
        query.append("       , dbo.SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','"+user.getCompNo()+"','"+user.getLangId()+"')    schedStatusDesc     ");
        query.append("       , pm_id                                                           pmId            ");
//        query.append("       , (SELECT a.pm_no                                                                 ");
//        query.append("          FROM TAPMLST a                                                                 ");
//        query.append("          WHERE a.pm_id = x.pm_id)                                       pmInsDListNo    ");
        query.append("       , x.work_number                                                   workNumber      ");
        query.append("       , x.product_id                                                    productId       ");
        query.append("       , (SELECT a.description FROM TAPRODUCT a WHERE a.product_id=x.product_id AND a.comp_no=x.comp_no)       productDesc     ");
        query.append("       , x.pminsdsched_id                                                pminsDSchedId   ");   
        query.append("		,( SELECT param2  															   	   ");
        query.append("		   FROM TACDSYSD  															   	   ");
        query.append("		   WHERE cdsysd_no=x.pm_type 												   	   ");
        query.append("		    AND list_type= x.wo_type+'_TYPE' )        					   pmParam	   	   ");
        query.append("FROM TAPMINSDLIST x                                                                      ");
        query.append("WHERE  1=1                                                                               ");
        query.append("AND    x.comp_no    = ?                                                                  ");
        query.append("AND    x.pmInsDlist_id    = ?                                                            ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
              , workPmiCInsCommonDTO.getPmInsDListId()
        };
    
        WorkPmiCInsDetailDTO workPmiCInsDetailDTO = 
                (WorkPmiCInsDetailDTO)getJdbcTemplate().query(query.toString(), getObject(objects), new MwareExtractor(new WorkPmiCInsDetailDTO()));
        
        return workPmiCInsDetailDTO;
    }
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id: WorkPmiCInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmiCInsDetailDTO
     * @return
     */
    public int insertDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPMINSDLIST                  ");
        query.append("    (                                     ");
        query.append("        comp_no                           ");
        query.append("      , pm_id         , equip_id          ");
        query.append("      , wo_type       , description       ");
        query.append("      , pm_type       , wkor_date         "); 
        query.append("      , shift_type    , pmsched_status    "); 
        query.append("      , eqruntime     , start_date        "); 
        query.append("      , start_time    , end_date          ");
        query.append("      , end_time      , work_time         "); 
        query.append("      , dept_id       , wkctr_id          ");
        query.append("      , emp_id        , close_id          ");
        query.append("      , close_date    , work_number       ");
        query.append("      , remark        , measure_time      ");
        query.append("    ) VALUES (                            ");
        query.append("          ?           , ?                 ");
        query.append("        , ?           , ?                 ");
        query.append("        , ?           , ?                 ");
        query.append("        , ?           , ?                 ");
        query.append("        , ?           , ?                 ");
        query.append("        , ?           , ?                 ");
        query.append("        , ?           , ?                 ");
        query.append("        , ?           , ?                 ");
        query.append("        , ?           , ?                 ");
        query.append("        , ?           , ?                 ");
        query.append("        , ?           , ?                 ");
        query.append("        , ?           , ?                 ");
        query.append("    )                                     ");

        Object[] objects = new Object[] {
                user.getCompNo()
              , workPmiCInsCommonDTO.getPopupPmId()
              , workPmiCInsDetailDTO.getEquipId()
              , "PMI"  
              , workPmiCInsDetailDTO.getDescription()
              , workPmiCInsDetailDTO.getPmTypeId()  
              , workPmiCInsCommonDTO.getPopupWorkDate()
              , workPmiCInsDetailDTO.getShiftTypeId()
              , "P"
              , workPmiCInsDetailDTO.getOperatingTime()
              , workPmiCInsDetailDTO.getStartDate()
              , workPmiCInsDetailDTO.getStartTime()
              , workPmiCInsDetailDTO.getEndDate()
              , workPmiCInsDetailDTO.getEndTime()
              , workPmiCInsDetailDTO.getWorkTime() 
              , workPmiCInsDetailDTO.getDeptId()
              , workPmiCInsDetailDTO.getWkCtrId()
              , workPmiCInsDetailDTO.getEmpId()
              , workPmiCInsDetailDTO.getCloseById()
              , workPmiCInsDetailDTO.getCloseDate()
              , workPmiCInsDetailDTO.getWorkNumber()
              , workPmiCInsDetailDTO.getRemark()
              , workPmiCInsDetailDTO.getMeasureTime() 
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
        
    	return rtnValue;
    }
    /**
     * detail update
     * @author youngjoo38
     * @version $Id: WorkPmiCInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmiCInsDetailDTO
     * @return
     */
    public int updateDetail(WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user)
    {   
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAPMINSDLIST SET                        ");
        query.append("        comp_no    =?                          ");
        query.append("      , equip_id   =?                          ");
        query.append("      , wo_type    =?    , description    =?   ");
        query.append("      , pm_type    =?    , wkor_date      =?   ");
        query.append("      , shift_type =?                          "); 
        query.append("      , eqruntime  =?    , start_date     =?   ");
        query.append("      , start_time =?    , end_date       =?   ");
        query.append("      , end_time   =?    , work_time      =?   ");
        query.append("      , dept_id    =?    , wkctr_id       =?   ");
        query.append("      , emp_id     =?    , close_id       =?   ");
        query.append("      , close_date =?    , work_number    =?   ");
        query.append("      , remark     =?    , measure_time   =?   ");
        query.append("WHERE  comp_no             = ?                 ");
        query.append("  AND  pminsdlist_id       = ?                 ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                , workPmiCInsDetailDTO.getEquipId()
                , "PMI"  
                , workPmiCInsDetailDTO.getDescription()
                , workPmiCInsDetailDTO.getPmTypeId()
                , workPmiCInsDetailDTO.getWkorDate()
                , workPmiCInsDetailDTO.getShiftTypeId()
                , workPmiCInsDetailDTO.getOperatingTime() 
                , workPmiCInsDetailDTO.getStartDate()
                , workPmiCInsDetailDTO.getStartTime()
                , workPmiCInsDetailDTO.getEndDate()
                , workPmiCInsDetailDTO.getEndTime()
                , workPmiCInsDetailDTO.getWorkTime() 
                , workPmiCInsDetailDTO.getDeptId()
                , workPmiCInsDetailDTO.getWkCtrId()
                , workPmiCInsDetailDTO.getEmpId()
                , workPmiCInsDetailDTO.getCloseById()
                , workPmiCInsDetailDTO.getCloseDate()
                , workPmiCInsDetailDTO.getWorkNumber()
                , workPmiCInsDetailDTO.getRemark()
                , workPmiCInsDetailDTO.getMeasureTime()  
                
                , user.getCompNo()
                , workPmiCInsDetailDTO.getPmInsDListId()
        };
        
        return  getJdbcTemplate().update(query.toString(), getObject(objects));
        
    }
    
    
    public int updatePmInsDSched(WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user)
    {   
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
        query.append("update TAPMINSDSCHED set  ");
        query.append("    sched_date = ?        ");
        query.append("where pminsdlist_id = ?   ");
        query.append("    and comp_no = ?       ");
        
        Object[] objects = new Object[] {
        		workPmiCInsDetailDTO.getWkorDate()
        		,workPmiCInsDetailDTO.getPmInsDListId()
        		,user.getCompNo()
        };
        
        return  getJdbcTemplate().update(query.toString(), getObject(objects));
        
    }

    @Override
    public String checkList(
            WorkPmiCInsCommonDTO workPmiCInsCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                        ");
        query.append("       pminsdlist_id          ");
        query.append("FROM   TAPMINSDLIST a         ");
        query.append("WHERE  1 = 1                  ");
        query.getAndQuery("a.pminsdlist_id", workPmiCInsCommonDTO.getPmInsDListId()); 
        query.getAndQuery("a.wkor_date", workPmiCInsCommonDTO.getPopupWorkDate());

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
    
    public String checkPoint(WorkPmiCInsDetailDTO workPmiCInsDetailDTO,User user) {

        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT ISNULL(a.val,0)                                             ");
        query.append("  FROM (SELECT CONVERT(VARCHAR, SUM(CASE (CASE WHEN y.check_type = 'SEN' THEN x.pm_point_rslt_status WHEN y.check_type = 'VAL' THEN x.result_value END) WHEN NULL THEN 1 ELSE 0 END)) AS val          ");
        query.append("          FROM TAPMINSDPOINT x RIGHT OUTER JOIN TAPMPOINT y   ");
        query.append("            ON x.comp_no = y.comp_no                          ");
        query.append("           AND x.pm_point_id = y.pm_point_id                  ");
        query.append("         WHERE 1=1                                            ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x. pminsdlist_id", workPmiCInsDetailDTO.getPmInsDListId());
        query.append("        )  a                                                  ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id: workPmiCInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiCInsDetailDTO
     * @return
     */
    public int completeDetail(WorkPmiCInsDetailDTO workPmiCInsDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("UPDATE TAPMINSDLIST SET       ");
        query.append("  pmsched_status      = 'C',  ");
        query.append("  close_id            = ?,    ");
        query.append("  close_date          = CONVERT(CHAR(8),GETDATE(),112)   ");
        query.append("WHERE pminsdlist_id   = ?     ");
        query.append("  AND comp_no         = ?     ");
        
        Object[] objects = new Object[] {
                workPmiCInsDetailDTO.getCloseById(),
                workPmiCInsDetailDTO.getPmInsDListId(),
                workPmiCInsDetailDTO.getCompNo()
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
    public int completeSched(WorkPmiCInsDetailDTO workPmiCInsDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("UPDATE TAPMINSDSCHED SET       ");
        query.append("  pmsched_status      = 'C',  ");
        query.append("  actual_date         = ?,    ");
        query.append("  actual_time         = ?,    ");
        query.append("  check_by            = ?     ");
        query.append("WHERE pminsdlist_id    = ?     ");
        query.append("  AND comp_no         = ?     ");
        
        Object[] objects = new Object[] {
                workPmiCInsDetailDTO.getStartDate()
                ,workPmiCInsDetailDTO.getStartTime()
                ,workPmiCInsDetailDTO.getEmpId()
                ,workPmiCInsDetailDTO.getPmInsDListId()
                ,workPmiCInsDetailDTO.getCompNo()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
        
        return rtnValue;
    }
    
    /**
     *  SP_PT_INSTOCK 프로시져 호출 
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executePmUpdate(WorkPmiCInsDetailDTO workPmiCInsDetailDTO) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("EXEC dbo.SP_PM_UPDATE_LASTCPLT_DATE               ");
        query.append("    '"+workPmiCInsDetailDTO.getCompNo()+"'        ");
        query.append("  , '"+workPmiCInsDetailDTO.getPmId()+"'          ");
        query.append("  , '"+workPmiCInsDetailDTO.getPmInsDSchedId()+"' ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
    
}