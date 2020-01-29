package dream.consult.comp.wrkcal.dao;

import common.bean.User;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetDetailDTO;

/**
 * 휴무일 설정 - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultCompWrkcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultCompWrkcalDaysetDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public ConsultCompWrkcalDaysetDetailDTO findDetail(String wrkcalDaysetId);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDaysetDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO, ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDaysetDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO);
}