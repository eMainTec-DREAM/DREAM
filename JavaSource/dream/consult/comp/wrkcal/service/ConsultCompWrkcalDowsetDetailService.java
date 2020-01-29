package dream.consult.comp.wrkcal.service;

import common.bean.User;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetDetailDTO;

/**
 * 회사설정 - 상세 service
 *
 * @author kim21017
 * @version $Id: ConsultCompWrkcalDowsetDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultCompWrkcalDowsetDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDowsetDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param wrkcalDowsetId
     * @param lang
     * @return
     * @throws Exception
     */
    public ConsultCompWrkcalDowsetDetailDTO findDetail(String wrkcalDowsetId, String lang)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDowsetDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDowsetDetailDTO
     * @param consultCompWrkcalCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO, ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDowsetDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDowsetDetailDTO
     * @param consultCompWrkcalCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO) throws Exception;
}
