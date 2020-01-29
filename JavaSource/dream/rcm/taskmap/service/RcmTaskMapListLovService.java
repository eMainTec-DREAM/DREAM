package dream.rcm.taskmap.service;

import java.util.List;

import common.bean.User;
import dream.rcm.taskmap.dto.RcmTaskMapListLovDTO;
import dream.rcm.taskmap.form.RcmTaskMapListLovForm;

/**
 * Task Map Service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface RcmTaskMapListLovService
{

    /**
     * 
     * @author  USER
     * @version $Id:$
     * @since   1.0
     * 
     * @param rcmTaskMapListLovDTO
     * @param loginUser
     * @param rcmTaskMapListLovForm
     * @return
     */
    List findTaskMapAcList(RcmTaskMapListLovDTO rcmTaskMapListLovDTO, User loginUser, RcmTaskMapListLovForm rcmTaskMapListLovForm);

}