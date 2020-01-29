package dream.consult.comp.wrkcal.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetListDTO;

/**
 * 휴무요일 설정  - 목록 dao
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDowsetListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface ConsultCompWrkcalDowsetListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDowsetListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalCommonDTO
     * @return List
     */
    public List findDowsetList(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO, User user);

    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDowsetListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteWrkcal(String id);
    
    public String findTotalCount(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO, User user);
}