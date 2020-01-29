package dream.consult.comp.wrkcal.dao;

import java.util.List;

import common.bean.User;

import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;

/**
 * 근무일달력 - 목록 dao
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface ConsultCompWrkcalListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalCommonDTO
     * @return List
     */
    public List findWrkcalList(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user);

    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompWrkcalListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteWrkcal(String wrkCalListId, String compNo, User user);
    
    public String findTotalCount(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user);
}