package dream.invt.list.service;

import java.util.List;

import common.bean.User;
import dream.invt.list.dto.InvtLovDTO;
import dream.invt.list.form.InvtLovForm;

/**
 * 설비투자 Service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface InvtLovService
{

    /**
     * 
     * @author  USER
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtLovDTO
     * @param loginUser
     * @param invtLovForm
     * @return
     */
    List findTaskMapAcList(InvtLovDTO invtLovDTO, User loginUser, InvtLovForm invtLovForm);
    
    public String findTotalCount(InvtLovDTO invtLovDTO, User loginUser, InvtLovForm invtLovForm);

}