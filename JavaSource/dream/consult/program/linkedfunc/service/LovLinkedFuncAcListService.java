package dream.consult.program.linkedfunc.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.linkedfunc.dto.LovLinkedFuncAcListDTO;
import dream.consult.program.linkedfunc.form.LovLinkedFuncAcListForm;

/**
 * Linked Function Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovLinkedFuncAcListService
{

    /**
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovLinkedFuncAcListDTO
     * @param loginUser
     * @return
     */
    List findLinkedFuncAcList(LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO, User loginUser);

    List findAcList(LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO, User user,
            LovLinkedFuncAcListForm lovLinkedFuncAcListForm);
}