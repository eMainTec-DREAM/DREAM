package dream.consult.comp.terminal.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExCommonDTO;

/**
 * 접근터미널 - 목록 dao
 * @author  kim21017
 * @version $Id: ConsultCompTerminalExListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface ConsultCompTerminalExListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: ConsultCompTerminalExListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExCommonDTO
     * @param user
     * @return List
     */
    public List findTerminalExList(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO, User user);

    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompTerminalExListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param user
     * @return
     */
    public int deleteTerminal(String compNo, String acdessMnlId, User user);
    
    public String findTotalCount(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO, User user);
}