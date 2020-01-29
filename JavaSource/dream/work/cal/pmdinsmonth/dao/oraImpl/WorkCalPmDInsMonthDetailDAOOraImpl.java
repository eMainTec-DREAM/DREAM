package dream.work.cal.pmdinsmonth.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.cal.pmdinsmonth.dao.WorkCalPmDInsMonthDetailDAO;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthDetailDTO;

/**
 * 월간예방일정 - 상세 dao
 *
 * @author kim21017
 * @version $Id: WorkCalPmDInsMonthDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workCalPmDInsMonthDetailDAOTarget"
 * @spring.txbn id="workCalPmDInsMonthDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmDInsMonthDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkCalPmDInsMonthDetailDAO
{
    @Override
    public int[] update(final List<WorkCalPmDInsMonthDetailDTO> list, final User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPMINSDSCHED SET      ");
        query.append("  sched_date      = ?         ");
        query.append("WHERE pminsdsched_id  = ?     ");
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
                WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO = list.get(i);

                Object[] objects = new Object[] {
                        CommonUtil.getRowDateToNum(workCalPmDInsMonthDetailDTO.getSchedDate()),
                        workCalPmDInsMonthDetailDTO.getPmInsDSchedId(),
                        user.getCompNo()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
}