package dream.budget.account.service;

import java.util.List;

import common.bean.User;
import dream.budget.account.dto.LovAccountListDTO;
import dream.budget.account.form.LovAccountListForm;

/**
 * 예산계정 Service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovAccountListService
{
    List findList(LovAccountListDTO lovAccountListDTO, User loginUser, LovAccountListForm  lovAccountListForm);

    String findTotalCount(LovAccountListDTO lovAccountListDTO, User loginUser, LovAccountListForm lovAccountListForm);
}