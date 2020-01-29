package dream.invt.prc.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.invt.prc.dto.InvtPrcTpLovDTO;

/**
 * �������� �˾�
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface InvtPrcTpLovDAO
{
    
    public List findTaskMapAcList(InvtPrcTpLovDTO invtPrcTpLovDTO,User loginUser,Map<String, String> conditionMap);
    
    public String findTotalCount(InvtPrcTpLovDTO invtPrcTpLovDTO, User user,Map<String, String> conditionMap);
}