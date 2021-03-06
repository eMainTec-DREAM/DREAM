package dream.work.pmi.list.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.pmi.list.dao.WorkPmiDetailDAO;
import dream.work.pmi.list.dto.WorkPmiDetailDTO;

/**
 * 점검작업 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: WorkPmiDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workPmiDetailDAOTarget"
 * @spring.txbn id="workPmiDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmiDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmiDetailDAO
{
    public int[] update(final List<WorkPmiDetailDTO> list, final User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int[] rtnValue;
    	
    	query.append("UPDATE TAPMINSLIST SET	");
    	query.append("	description			= ?	");
    	query.append("	,wo_type			= ?	");
    	query.append("	,dept_id			= ?	");
    	query.append("	,pm_type			= ?	");
    	query.append("	,emp_id				= ?	");
    	query.append("	,start_date			= ?	");
    	query.append("	,start_time			= ?	");
    	query.append("	,end_date			= ?	");
    	query.append("	,end_time			= ?	");
    	query.append("	,work_time			= ?	");
    	query.append("	,wkor_date			= ?	");
    	query.append("	,shift_type			= ?	");
    	query.append("	,equip_id			= ?	");
    	query.append("	,wkctr_id			= ?	");
    	query.append("	,remark				= ?	");
    	query.append("	,upd_date			= ?	");
    	query.append("	,upd_by				= ?	");
    	query.append("WHERE pminslist_id	= ?	");
    	query.append("  AND comp_no			= ?	");
    	
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
                WorkPmiDetailDTO workPmiDetailDTO = list.get(i);

                Object[] objects = new Object[] {
                        workPmiDetailDTO.getPmiDesc()
                        ,workPmiDetailDTO.getWoTypeId()
                        ,workPmiDetailDTO.getDeptId()
                        ,workPmiDetailDTO.getPmTypeId()
                        ,workPmiDetailDTO.getEmpId()
                        ,CommonUtil.getRowDateToNum(workPmiDetailDTO.getStartDate())
                        ,CommonUtil.getRowDateToNum(workPmiDetailDTO.getStartTime())
                        ,CommonUtil.getRowDateToNum(workPmiDetailDTO.getEndDate())
                        ,CommonUtil.getRowDateToNum(workPmiDetailDTO.getEndTime())
                        ,CommonUtil.getRowMoneyToNum(workPmiDetailDTO.getWorkTime())
                        ,CommonUtil.getRowDateToNum(workPmiDetailDTO.getWkorDate())
                        ,workPmiDetailDTO.getShiftTypeId()
                        ,workPmiDetailDTO.getEquipId()
                        ,workPmiDetailDTO.getWkCtrId()
                        ,workPmiDetailDTO.getRemark()
                        ,workPmiDetailDTO.getUpdDate()
                        ,workPmiDetailDTO.getUpdById()
                        ,workPmiDetailDTO.getPminslistId()
                        ,user.getCompNo()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    	
        query = new QueryBuffer();
        
        query.append("update TAPMINSSCHED set  ");
        query.append("    sched_date = ?        ");
        query.append("where pminslist_id = ?   ");
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
                WorkPmiDetailDTO workPmiDetailDTO = list.get(i);

                Object[] objects = new Object[] {
                        CommonUtil.getRowDateToNum(workPmiDetailDTO.getWkorDate())
                        ,workPmiDetailDTO.getPminslistId()
                        ,user.getCompNo()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    	
        return rtnValue;
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkPmiDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiDetailDTO
     * @return
     */
    public int completeDetail(WorkPmiDetailDTO workPmiDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSLIST SET		");
    	query.append("	pmsched_status		= ?,	");
    	query.append("	close_id			= ?,  	");
    	query.append("	close_date			= to_char(sysdate,'yyyymmdd')  	");
    	query.append("WHERE pminslist_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			workPmiDetailDTO.getPmschedStatusId()
    			,user.getUserId()
    			,workPmiDetailDTO.getPminslistId()
    			,user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int completeCancelDetail(WorkPmiDetailDTO workPmiDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSLIST SET		");
    	query.append("	pmsched_status		= ?,	");
    	query.append("	close_id			= '',  	");
    	query.append("	close_date			= ''  	");
    	query.append("WHERE pminslist_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			workPmiDetailDTO.getPmschedStatusId()
    			,workPmiDetailDTO.getPminslistId()
    			,user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public String checkPoint(WorkPmiDetailDTO workPmiDetailDTO,User user) {

		QueryBuffer query = new QueryBuffer();
		query.append("SELECT NVL(val,0)												");
		query.append("	FROM (SELECT TO_CHAR(SUM(DECODE(DECODE(y.check_type,'SEN',x.pm_point_rslt_status,'VAL',x.result_value),null,1,0))) val	");
		query.append("			FROM TAPMINSPOINT x RIGHT OUTER JOIN TAPMPOINT y	");
		query.append("		   	  ON x.comp_no = y.comp_no							");
		query.append("			 AND x.pm_point_id = y.pm_point_id					");
		query.append("		   WHERE 1=1											");
		query.getStringEqualQuery("x.comp_no", user.getCompNo());
		query.getStringEqualQuery("x. pminslist_id", workPmiDetailDTO.getPminslistId());
		query.getAndQuery("y.is_deleted", "N");
		query.append("		  )														");
		
		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkPmiDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiDetailDTO
     * @return
     */
    public int completeSched(WorkPmiDetailDTO workPmiDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSSCHED SET		");
    	query.append("	pmsched_status		= ?,	");
    	query.append("	actual_date			= ?,  	");
    	query.append("	actual_time			= ?,  	");
    	query.append("	check_by			= ? 	");
    	query.append("WHERE pminslist_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			workPmiDetailDTO.getPmschedStatusId()
    			,workPmiDetailDTO.getEndDate().replaceAll("-", "")
    			,workPmiDetailDTO.getEndTime().replaceAll(":", "")
    			,user.getEmpId()
    			,workPmiDetailDTO.getPminslistId()
    			,user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int completeCancelSched(WorkPmiDetailDTO workPmiDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSSCHED SET		");
    	query.append("	pmsched_status		= ?,	");
    	query.append("	actual_date			= '',  	");
    	query.append("	actual_time			= '',  	");
    	query.append("	check_by			= '' 	");
    	query.append("WHERE pminslist_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			workPmiDetailDTO.getPmschedStatusId()
    			,workPmiDetailDTO.getPminslistId()
    			,user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     *  SP_PT_INSTOCK 프로시져 호출 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executePmUpdate(WorkPmiDetailDTO workPmiDetailDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("begin                                                     ");
        query.append("SP_PM_UPDATE_LASTCPLT_DATE('"+user.getCompNo()+"', '"+workPmiDetailDTO.getPmId()+"','"+workPmiDetailDTO.getPmInscchedId()+"' );          ");
        query.append("end;                                                      ");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
    
    @Override
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPMINSLIST SET                  ");
        query.append("       pmsched_status   = CASE WHEN pmsched_status='C' THEN 'C' ELSE ? END             ");
        query.append("WHERE  pminslist_id     = ?             ");
        query.append("  AND  comp_no       	  = ?             ");
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}
