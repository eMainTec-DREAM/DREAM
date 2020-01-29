package dream.consult.comp.terminal.dao;

import common.bean.User;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExCommonDTO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExDetailDTO;

/**
 * 접근터미널 - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultCompTerminalExDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultCompTerminalExDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompTerminalExDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExCommonDTO
     * @param user
     * @return
     */
    public ConsultCompTerminalExDetailDTO findDetail(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO, User user);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompTerminalExDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompTerminalExDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO, User user);
}