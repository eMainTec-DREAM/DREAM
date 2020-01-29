package dream.consult.comp.terminal.dao;

import common.bean.User;
import dream.consult.comp.terminal.dto.ConsultCompTerminalCommonDTO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalDetailDTO;

/**
 * 접근터미널 - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultCompTerminalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultCompTerminalDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompTerminalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalCommonDTO
     * @param user
     * @return
     */
    public ConsultCompTerminalDetailDTO findDetail(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompTerminalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompTerminalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO, User user);
}