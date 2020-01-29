package dream.consult.comp.wrkcal.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.comp.list.dto.ConsultCompListLovDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalLovDTO;

/**
 * 근무달력 팝업
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalLovDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface ConsultCompWrkcalLovDAO
{
    /**
     *  검색
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalLovDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param consultCompWrkcalLovDTO
     * @param loginUser
     * @return
     */
    public List findWrkcalList(ConsultCompWrkcalLovDTO consultCompWrkcalLovDTO, User loginUser);
    
    public List findWrkcalAcList(ConsultCompWrkcalLovDTO consultCompWrkcalLovDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap);

}