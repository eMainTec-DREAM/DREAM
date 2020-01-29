package dream.consult.comp.terminal.service;

import common.bean.User;
import dream.consult.comp.terminal.dto.ConsultCompTerminalCommonDTO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalDetailDTO;

/**
 * 접근터미널 - 상세 service
 *
 * @author kim21017
 * @version $Id: ConsultCompTerminalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultCompTerminalDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompTerminalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public ConsultCompTerminalDetailDTO findDetail(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompTerminalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompTerminalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO, User user) throws Exception;
}
