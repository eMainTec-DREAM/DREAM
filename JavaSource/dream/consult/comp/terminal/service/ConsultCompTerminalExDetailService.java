package dream.consult.comp.terminal.service;

import common.bean.User;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExCommonDTO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExDetailDTO;

/**
 * 접근터미널 - 상세 service
 *
 * @author kim21017
 * @version $Id: ConsultCompTerminalExDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultCompTerminalExDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompTerminalExDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public ConsultCompTerminalExDetailDTO findDetail(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompTerminalExDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompTerminalExDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO, User user) throws Exception;
}
