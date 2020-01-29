package dream.work.alarm.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.alarm.dto.WorkAlarmDTO;

/**
 * Alarm List - ¸ñ·Ï dao 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public interface WorkAlarmDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param workAlarmDTO
     * @return List
     */
    public List findList(WorkAlarmDTO workAlarmDTO, User user) throws Exception;
    
    public String findTotalCount(WorkAlarmDTO workAlarmDTO, User user);
    
    public int[] insertDetail(final List<WorkAlarmDTO> list, final User user) throws Exception;
    
    public int[] deleteList(final List<WorkAlarmDTO> list, final User user) throws Exception;

    public int[] updateDetail(final List<WorkAlarmDTO> list, final User user) throws Exception;

    public String getColums(WorkAlarmDTO workAlarmDTO, User user);
    
    public String getTables(WorkAlarmDTO workAlarmDTO, User user);
    
    public String getOrderBy(WorkAlarmDTO workAlarmDTO, User user);
    
    public String getWhere(WorkAlarmDTO workAlarmDTO, User user);
}