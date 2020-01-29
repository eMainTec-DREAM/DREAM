package dream.invt.prc.service;

import java.util.List;

import common.bean.User;
import dream.invt.prc.dto.InvtPrcPhLovDTO;
import dream.invt.prc.form.InvtPrcPhLovForm;

/**
 * 구매절차 소분류 Service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface InvtPrcPhLovService
{

    /**
     * 
     * @author  USER
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtPrcPhLovDTO
     * @param loginUser
     * @param invtPrcPhLovForm
     * @return
     */
    List findTaskMapAcList(InvtPrcPhLovDTO invtPrcPhLovDTO, User loginUser, InvtPrcPhLovForm invtPrcPhLovForm);

}