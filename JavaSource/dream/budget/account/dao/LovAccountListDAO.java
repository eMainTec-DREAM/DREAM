package dream.budget.account.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.budget.account.dto.LovAccountListDTO;

/**
 * 예산계정Lov
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface LovAccountListDAO
{
    public List findList(LovAccountListDTO lovAccountListDTO, User loginUser, Map<String, String> conditionMap);

    public String findTotalCount(LovAccountListDTO lovAccountListDTO, User loginUser, Map<String, String> conditionMap);
}