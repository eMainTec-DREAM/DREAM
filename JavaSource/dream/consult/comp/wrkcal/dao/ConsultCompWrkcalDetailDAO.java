package dream.consult.comp.wrkcal.dao;

import common.bean.User;

import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDetailDTO;

/**
 * 회사설정 - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultCompWrkcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultCompWrkcalDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public ConsultCompWrkcalDetailDTO findDetail(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO, User user);
}