package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmListMsTimeLovDTO;

/**
 * �۾���û���� �˾� Service
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface WorkPmListMsTimeLovService
{

    /**
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param workPmListMsTimeLovDTO
     * @return
     */
    List findList(User loginUser, WorkPmListMsTimeLovDTO workPmListMsTimeLovDTO);
}