package dream.consult.comp.terminal.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.terminal.dto.ConsultCompTerminalCommonDTO;

/**
 * 접근터미널 - 목록 service
 * @author  kim21017
 * @version $Id: ConsultCompTerminalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface ConsultCompTerminalListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: ConsultCompTerminalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param consultCompTerminalCommonDTO
     * @param user
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findTerminalList(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompTerminalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteTerminal(String[] deleteRows, String[] deleteRowsExt, User user) throws Exception;
    
    public String findTotalCount(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user);
}
