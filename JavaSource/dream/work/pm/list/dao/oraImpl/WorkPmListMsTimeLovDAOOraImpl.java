package dream.work.pm.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmListMsTimeLovDAO;
import dream.work.pm.list.dto.WorkPmListMsTimeLovDTO;

/**
 * 작업요청유형 선택팝업
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="workPmListMsTimeLovDAOTarget"
 * @spring.txbn id="workPmListMsTimeLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmListMsTimeLovDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmListMsTimeLovDAO
{
    /**
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findList(User loginUser, WorkPmListMsTimeLovDTO workPmListMsTimeLovDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT ''				seqNo			");
        query.append("      ,x.cdsysd_no	measureTimeId	");
        query.append("      ,x.description	measureTime		");
        query.append("FROM TACDSYSD x						");
        query.append("WHERE 1=1								");
        query.getStringEqualQuery("list_type", "MEASURE_TIME");
        query.getStringEqualQuery("is_use", "Y");
        query.append("ORDER BY ord_no						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}