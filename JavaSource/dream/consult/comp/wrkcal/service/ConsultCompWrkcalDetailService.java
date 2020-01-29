package dream.consult.comp.wrkcal.service;

import common.bean.User;

import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDetailDTO;

/**
 * 회사설정 - 상세 service
 *
 * @author kim21017
 * @version $Id: ConsultCompWrkcalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultCompWrkcalDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     * @throws Exception
     */
    public ConsultCompWrkcalDetailDTO findDetail(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO, User user) throws Exception;
}
