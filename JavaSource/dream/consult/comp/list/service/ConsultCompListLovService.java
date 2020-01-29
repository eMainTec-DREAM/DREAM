package dream.consult.comp.list.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.list.dto.ConsultCompListLovDTO;
import dream.consult.comp.list.form.ConsultCompListLovForm;


/**
 * 회사코드 Service
 * @author  hyosung
 * @version $Id: ConsultCompListLovService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
 * @since   1.0
 *
 */
public interface ConsultCompListLovService
{

    /**
     * 회사코드 검색
     * @author  hyosung
     * @version $Id: ConsultCompListLovService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
     * @since   1.0
     * 
     * @param ConsultCompListLovDTO
     * @param loginUser
     * @return
     */
    List findCompList(ConsultCompListLovDTO consultCompListLovDTO, User loginUser);
    
    List findCompAcList(ConsultCompListLovDTO consultCompListLovDTO, User user, ConsultCompListLovForm consultCompListLovForm);
}