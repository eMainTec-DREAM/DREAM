package dream.work.alarm.req.service;

import java.util.List;

import common.bean.User;
import dream.work.alarm.req.dto.WorkAlarmReqDTO;

/**
 * 荐府夸没 立荐 - 格废 service 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public interface WorkAlarmReqService
{     
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id:$
     * @param 	workAlarmReqDTO
     * @param 	user
     * @since   1.0
     * 
     * @return list
     * @throws Exception
     */
    public List<WorkAlarmReqDTO> findList(WorkAlarmReqDTO workAlarmReqDTO, User user) throws Exception;
    
    /**
     * grid delete
     * @author  nhkim8548
     * @version $Id:$
     * @param 	deleteRows
     * @param 	user
     * @since   1.0
     * 
     * @return  
     * @throws Exception
     */
    public int[] deleteList(String[] deleteRows, User user) throws Exception;
    
    /**
     * grid count
     * @author  nhkim8548
     * @version $Id:$
     * @param 	workAlarmReqDTO
     * @param 	user
     * @since   1.0
     * 
     * @return 
     * @throws Exception
     */
    public String findTotalCount(WorkAlarmReqDTO workAlarmReqDTO, User user);
    
    /**
     * detail find
     * @author  nhkim8548
     * @version $Id:$
     * @param 	workAlarmReqDTO
     * @param 	user
     * @since   1.0
     * 
     * @return WorkAlarmReqDTO
     * @throws Exception
     */
    public WorkAlarmReqDTO findDetail(WorkAlarmReqDTO workAlarmReqDTO, User user) throws Exception;
    
    /**
     * detail insert 
     * @author  nhkim8548
     * @version $Id:$
     * @param 	workAlarmReqDTO
     * @param 	user
     * @since   1.0
     * 
     * @return 
     * @throws Exception
     */
    public int insertDetail(WorkAlarmReqDTO workAlarmReqDTO, User user) throws Exception;
    
    /**
     * detail insert 
     * @author  nhkim8548
     * @version $Id:$
     * @param 	list
     * @param 	user
     * @since   1.0
     * 
     * @return 
     * @throws Exception
     */
    public int[] insertDetail(List<WorkAlarmReqDTO> list, User user) throws Exception;
    
    /**
     * exist request insert 
     * @author  nhkim8548
     * @version $Id:$
     * @param 	workAlarmReqDTO
     * @param 	user
     * @since   1.0
     * 
     * @return 
     * @throws Exception
     */
    public int[] linkWoReq(WorkAlarmReqDTO workAlarmReqDTO, User user) throws Exception;
}
