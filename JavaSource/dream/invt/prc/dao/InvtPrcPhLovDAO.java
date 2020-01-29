package dream.invt.prc.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.invt.prc.dto.InvtPrcPhLovDTO;

/**
 * 구매절차 소분류 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface InvtPrcPhLovDAO
{
    
    public List findTaskMapAcList(InvtPrcPhLovDTO invtPrcPhLovDTO,User loginUser,Map<String, String> conditionMap);
}