package dream.consult.comp.terminal.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.terminal.dto.ConsultCompTerminalCommonDTO;

/**
 * 접근터미널 - 목록 dao
 * @author  kim21017
 * @version $Id: ConsultCompTerminalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface ConsultCompTerminalListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: ConsultCompTerminalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalCommonDTO
     * @param user
     * @return List
     */
    public List findTerminalList(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user);

    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompTerminalListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param user
     * @return
     */
    public int deleteTerminal(String compNo, String acdessMnlId, User user);
    
    public String findTotalCount(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user);
}