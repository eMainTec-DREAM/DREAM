package dream.consult.comp.wrkcal.dao;

import common.bean.User;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetDetailDTO;

/**
 * 회사설정 - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultCompWrkcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultCompWrkcalDowsetDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public ConsultCompWrkcalDowsetDetailDTO findDetail(String wrkcalDowsetId, String lang);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDowsetDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO, ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDowsetDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO);
}