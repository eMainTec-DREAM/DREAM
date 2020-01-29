package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmListMsTimeLovDTO;

/**
 * �۾���û���� �����˾�
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface WorkPmListMsTimeLovDAO
{
    /**
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findList(User loginUser, WorkPmListMsTimeLovDTO workPmListMsTimeLovDTO);
}