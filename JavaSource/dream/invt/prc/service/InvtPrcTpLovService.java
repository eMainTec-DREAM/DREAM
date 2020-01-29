package dream.invt.prc.service;

import java.util.List;

import common.bean.User;
import dream.invt.prc.dto.InvtPrcTpLovDTO;
import dream.invt.prc.form.InvtPrcTpLovForm;

/**
 * 구매절차 Service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface InvtPrcTpLovService
{

    /**
     * 
     * @author  USER
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtPrcTpLovDTO
     * @param loginUser
     * @param invtPrcTpLovForm
     * @return
     */
    List findTaskMapAcList(InvtPrcTpLovDTO invtPrcTpLovDTO, User loginUser, InvtPrcTpLovForm invtPrcTpLovForm);
    
    public String findTotalCount(InvtPrcTpLovDTO invtPrcTpLovDTO, User user, InvtPrcTpLovForm invtPrcTpLovForm);

}