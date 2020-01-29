package dream.consult.comp.config.service;

import common.bean.User;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;
import dream.consult.comp.config.dto.ConsultCompConfigDetailDTO;

/**
 * 시스템 환경변수 - 상세 service
 * 
 * @author syyang
 * @version $Id: ConsultCompConfigDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 */
public interface ConsultCompConfigDetailService
{    
    /**
     * detail find
     * @author syyang
     * @version $Id: ConsultCompConfigDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigCommonDTO
     * @return
     * @throws Exception
     */
    public ConsultCompConfigDetailDTO findDetail(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author syyang
     * @version $Id: ConsultCompConfigDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultCompConfigDetailDTO consultCompConfigDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author syyang
     * @version $Id: ConsultCompConfigDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultCompConfigDetailDTO consultCompConfigDetailDTO, User user) throws Exception;
}
