package common.mafinder.mamstr.service;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dto.LovPageListDTO;
import common.mafinder.mamstr.form.LovPageListForm;

/**
 * ∆‰¿Ã¡ˆ Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovPageListService
{

    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovLinkedFuncAcListDTO
     * @param loginUser
     * @return
     */
    List findPageList(LovPageListDTO lovPageListDTO, User loginUser);

    List findPageAcList(LovPageListDTO lovPageListDTO, User user,
            LovPageListForm lovPageListForm);
    
    public String findTotalCount(LovPageListForm lovPageListForm, User loginUser) throws Exception;
}