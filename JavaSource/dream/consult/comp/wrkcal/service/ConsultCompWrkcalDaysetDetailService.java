package dream.consult.comp.wrkcal.service;

import common.bean.User;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetDetailDTO;

/**
 * 휴무일 설정 - 상세 service
 *
 * @author kim21017
 * @version $Id: ConsultCompWrkcalDaysetDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultCompWrkcalDaysetDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDaysetDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param wrkcalDaysetId
     * @param lang
     * @return
     * @throws Exception
     */
    public ConsultCompWrkcalDaysetDetailDTO findDetail(String wrkcalDaysetId)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDaysetDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDaysetDetailDTO
     * @param consultCompWrkcalCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO, ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDaysetDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDaysetDetailDTO
     * @param consultCompWrkcalCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO) throws Exception;
}
