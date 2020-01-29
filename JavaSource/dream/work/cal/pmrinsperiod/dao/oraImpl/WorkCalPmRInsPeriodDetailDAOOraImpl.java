package dream.work.cal.pmrinsperiod.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.cal.pmrinsperiod.dao.WorkCalPmRInsPeriodDetailDAO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodDetailDTO;

/**
 * 예방작업일정(기간) - 상세 dao
 *
 * @author kim21017
 * @version $Id: WorkCalPmRInsPeriodDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workCalPmRInsPeriodDetailDAOTarget"
 * @spring.txbn id="workCalPmRInsPeriodDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmRInsPeriodDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkCalPmRInsPeriodDetailDAO
{
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkCalPmRInsPeriodDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsPeriodDetailDTO
     * @return
     */
    public int inputDetail(WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPMINSSCHED (                               	");
    	query.append("  comp_no,    pminssched_id,  pm_id,   plan_init_date,		");
    	query.append("  plan_date, pmsched_status,       sched_date    			");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         SQAPMINSSCHED_ID.NEXTVAL, ?,            ?,              ");
    	query.append("  ?,         ?,             ?            					");
    	query.append(")                                                         ");

    	Object[] objects = new Object[] {
    	        user.getCompNo(),
    			workCalPmRInsPeriodDetailDTO.getPmId(),
    			workCalPmRInsPeriodDetailDTO.getSchedDate(),
    			workCalPmRInsPeriodDetailDTO.getSchedDate(),
    			"P",
    			workCalPmRInsPeriodDetailDTO.getSchedDate()
    	};

    	return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public int[] update(final List<WorkCalPmRInsPeriodDetailDTO> list, final User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPMINSSCHED SET       ");
        query.append("  sched_date          = ?     ");
        query.append("  ,remark             = ?     ");
        query.append("WHERE pminssched_id   = ?     ");
        query.append("  AND comp_no         = ?     ");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {

            @Override
            public int getBatchSize()
            {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO = list.get(i);

                Object[] objects = new Object[] {
                        CommonUtil.getRowDateToNum(workCalPmRInsPeriodDetailDTO.getSchedDate()),
                        workCalPmRInsPeriodDetailDTO.getRemark(),
                        workCalPmRInsPeriodDetailDTO.getPmInsSchedId(),
                        user.getCompNo()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
}