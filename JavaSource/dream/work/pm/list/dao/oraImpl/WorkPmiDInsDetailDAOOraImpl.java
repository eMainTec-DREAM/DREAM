package dream.work.pm.list.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmiDInsDetailDAO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsDetailDTO;

/**
 * WorkPmiDIns Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id: WorkPmiDInsDetailDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmiDInsDetailDAOTarget"
 * @spring.txbn id="workPmiDInsDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmiDInsDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmiDInsDetailDAO
{
    public int insertDetail(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, WorkPmiDInsDetailDTO workPmiDInsDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("INSERT INTO TAPMINSDLIST                  ");
        query.append("    (                                     ");
        query.append("        comp_no       , pminsdlist_id     ");
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
        query.append("      , plant                             ");
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
        query.append("        , ?                               ");
        query.append("    )                                     ");

        Object[] objects = new Object[] {
                user.getCompNo()
              , workPmiDInsDetailDTO.getPmInsDListId()
              , workPmiDInsCommonDTO.getPopupPmId()
              , workPmiDInsDetailDTO.getEquipId()
              , "PMI"  
              , workPmiDInsDetailDTO.getDescription()
              , workPmiDInsDetailDTO.getPmType()  
              , workPmiDInsCommonDTO.getPopupWorkDate()
              , workPmiDInsDetailDTO.getShiftType()
              , "P"
              , workPmiDInsDetailDTO.getOperatingTime()
              , workPmiDInsDetailDTO.getStartDate()
              , workPmiDInsDetailDTO.getStartTime()
              , workPmiDInsDetailDTO.getEndDate()
              , workPmiDInsDetailDTO.getEndTime()
              , workPmiDInsDetailDTO.getWorkTime() 
              , workPmiDInsDetailDTO.getDeptId()
              , workPmiDInsDetailDTO.getWkCtrId()
              , workPmiDInsDetailDTO.getEmpId()
              , workPmiDInsDetailDTO.getCloseId()
              , workPmiDInsDetailDTO.getCloseDate()
              , workPmiDInsDetailDTO.getWorkNumber()
              , workPmiDInsDetailDTO.getRemark()
              , workPmiDInsDetailDTO.getMeasureTime() 
              , user.getPlant()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    @Override
    public int[] update(final List<WorkPmiDInsDetailDTO> list, final User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        int[] rtnValue;
        
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
        
        rtnValue = getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {

            @Override
            public int getBatchSize()
            {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                WorkPmiDInsDetailDTO workPmiDInsDetailDTO = list.get(i);

                Object[] objects = new Object[] {
                        user.getCompNo()
                        , workPmiDInsDetailDTO.getEquipId()
                        , "PMI"
                        , workPmiDInsDetailDTO.getDescription()
                        , workPmiDInsDetailDTO.getPmType()
                        , CommonUtil.getRowDateToNum(workPmiDInsDetailDTO.getWkorDate())
                        , workPmiDInsDetailDTO.getShiftType()
                        , CommonUtil.getRowDateToNum(workPmiDInsDetailDTO.getOperatingTime())
                        , CommonUtil.getRowDateToNum(workPmiDInsDetailDTO.getStartDate())
                        , CommonUtil.getRowDateToNum(workPmiDInsDetailDTO.getStartTime())
                        , CommonUtil.getRowDateToNum(workPmiDInsDetailDTO.getEndDate())
                        , CommonUtil.getRowDateToNum(workPmiDInsDetailDTO.getEndTime())
                        , CommonUtil.getRowDateToNum(workPmiDInsDetailDTO.getWorkTime())
                        , workPmiDInsDetailDTO.getDeptId()
                        , workPmiDInsDetailDTO.getWkCtrId()
                        , workPmiDInsDetailDTO.getEmpId()
                        , workPmiDInsDetailDTO.getCloseId()
                        , CommonUtil.getRowDateToNum(workPmiDInsDetailDTO.getCloseDate())
                        , workPmiDInsDetailDTO.getWorkNumber()
                        , workPmiDInsDetailDTO.getRemark()
                        , CommonUtil.getRowDateToNum(workPmiDInsDetailDTO.getMeasureTime())
                        , user.getCompNo()
                        , workPmiDInsDetailDTO.getPmInsDListId()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
        
        query = new QueryBuffer();
        
        query.append("update TAPMINSDSCHED set  ");
        query.append("    sched_date = ?        ");
        query.append("where pminsdlist_id = ?   ");
        query.append("    and comp_no = ?       ");
        
        getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {

            @Override
            public int getBatchSize()
            {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                WorkPmiDInsDetailDTO workPmiDInsDetailDTO = list.get(i);

                Object[]  objects = new Object[] {
                        CommonUtil.getRowDateToNum(workPmiDInsDetailDTO.getWkorDate())
                        ,workPmiDInsDetailDTO.getPmInsDListId()
                        ,user.getCompNo()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
        
        return rtnValue;
    }

    public String checkPoint(WorkPmiDInsDetailDTO workPmiDInsDetailDTO,User user) {

        QueryBuffer query = new QueryBuffer();
        query.append("SELECT NVL(val,0)                                             ");
        query.append("  FROM (SELECT TO_CHAR(SUM(DECODE(DECODE(y.check_type,'SEN',x.pm_point_rslt_status,'VAL',x.result_value),null,1,0))) val  ");
        query.append("          FROM TAPMINSDPOINT x RIGHT OUTER JOIN TAPMPOINT y   ");
        query.append("            ON x.comp_no = y.comp_no                          ");
        query.append("           AND x.pm_point_id = y.pm_point_id                  ");
        query.append("         WHERE 1=1                                            ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x. pminsdlist_id", workPmiDInsDetailDTO.getPmInsDListId());
        query.append("        )                                                     ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id: workPmiDInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiDInsDetailDTO
     * @return
     */
    public int completeDetail(WorkPmiDInsDetailDTO workPmiDInsDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("UPDATE TAPMINSDLIST SET        ");
        query.append("  pmsched_status      = 'C',  ");
        query.append("  close_id            = ?,    ");
        query.append("  close_date          = to_char(sysdate,'yyyymmdd')   ");
        query.append("WHERE pminsdlist_id    = ?     ");
        query.append("  AND comp_no         = ?     ");
        
        Object[] objects = new Object[] {
                workPmiDInsDetailDTO.getCloseId(),
                workPmiDInsDetailDTO.getPmInsDListId(),
                workPmiDInsDetailDTO.getCompNo()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
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
    public int completeSched(WorkPmiDInsDetailDTO workPmiDInsDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("UPDATE TAPMINSDSCHED SET       ");
        query.append("  pmsched_status      = 'C',  ");
        query.append("  actual_date         = ?,    ");
        query.append("  actual_time         = ?,    ");
        query.append("  check_by            = ?     ");
        query.append("WHERE pminsdlist_id    = ?     ");
        query.append("  AND comp_no         = ?     ");
        
        Object[] objects = new Object[] {
                workPmiDInsDetailDTO.getStartDate()
                ,workPmiDInsDetailDTO.getStartTime()
                ,workPmiDInsDetailDTO.getEmpId()
                ,workPmiDInsDetailDTO.getPmInsDListId()
                ,workPmiDInsDetailDTO.getCompNo()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
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
    public int executePmUpdate(WorkPmiDInsDetailDTO workPmiDInsDetailDTO) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("begin                                                     ");
        query.append("SP_PM_UPDATE_LASTCPLT_DATE('"+workPmiDInsDetailDTO.getCompNo()+"', '"+workPmiDInsDetailDTO.getPmId()+"','"+workPmiDInsDetailDTO.getPmInsDSchedId()+"' );          ");
        query.append("end;                                                      ");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
}
