package dream.invt.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.invt.list.dto.InvtLovDTO;

/**
 * �������� �˾�
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface InvtLovDAO
{
    
    public List findTaskMapAcList(InvtLovDTO invtLovDTO,User loginUser,Map<String, String> conditionMap);
    
    public String findTotalCount(InvtLovDTO invtLovDTO, User loginUser,Map<String, String> conditionMap);
}