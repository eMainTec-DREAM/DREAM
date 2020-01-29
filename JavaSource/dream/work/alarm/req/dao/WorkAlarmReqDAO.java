package dream.work.alarm.req.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.alarm.req.dto.WorkAlarmReqDTO;

/**
 * 荐府夸没 立荐 - 格废 dao 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public interface WorkAlarmReqDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param workAlarmReqDTO
     * @return List
     */
    public List findList(WorkAlarmReqDTO workAlarmReqDTO, User user) throws Exception;
    
    public String findTotalCount(WorkAlarmReqDTO workAlarmReqDTO, User user);
    
    public int[] insertDetail(final List<WorkAlarmReqDTO> list, User user) throws Exception;
    
    public int[] deleteList(final List<WorkAlarmReqDTO> list, final User user) throws Exception;

    public String getColums(WorkAlarmReqDTO workAlarmReqDTO, User user);
    
    public String getTables(WorkAlarmReqDTO workAlarmReqDTO, User user);
    
    public String getOrderBy(WorkAlarmReqDTO workAlarmReqDTO, User user);
    
    public String getWhere(WorkAlarmReqDTO workAlarmReqDTO, User user);
}