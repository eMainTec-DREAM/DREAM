package dream.consult.comp.wrkcal.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalLovDTO;
import dream.consult.comp.wrkcal.form.ConsultCompWrkcalLovForm;

/**
 * 근무달력 Service
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalLovService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface ConsultCompWrkcalLovService
{

    /**
     * 무정지라인 검색
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalLovService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param ConsultCompWrkcalLovDTO
     * @param loginUser
     * @return
     */
    List findWrkcalList(ConsultCompWrkcalLovDTO consultCompWrkcalLovDTO, User loginUser);
    
    List findWrkcalAcList(ConsultCompWrkcalLovForm consultCompWrkcalLovForm, User loginUser);
}