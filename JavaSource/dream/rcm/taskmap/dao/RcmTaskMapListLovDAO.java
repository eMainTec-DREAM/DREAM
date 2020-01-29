package dream.rcm.taskmap.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.rcm.taskmap.dto.RcmTaskMapListLovDTO;

/**
 * Task Map ÆË¾÷
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface RcmTaskMapListLovDAO
{
    
    public List findTaskMapAcList(RcmTaskMapListLovDTO rcmTaskMapListLovDTO,User loginUser,Map<String, String> conditionMap);
}