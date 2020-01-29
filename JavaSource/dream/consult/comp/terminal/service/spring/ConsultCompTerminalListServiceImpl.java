package dream.consult.comp.terminal.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.terminal.dao.ConsultCompTerminalListDAO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalCommonDTO;
import dream.consult.comp.terminal.service.ConsultCompTerminalListService;

/**
 * 접근터미널 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: ConsultCompTerminalListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="consultCompTerminalListServiceTarget"
 * @spring.txbn id="consultCompTerminalListService"
 * @spring.property name="consultCompTerminalListDAO" ref="consultCompTerminalListDAO"
 */
public class ConsultCompTerminalListServiceImpl implements ConsultCompTerminalListService
{
    private ConsultCompTerminalListDAO consultCompTerminalListDAO = null;

    public ConsultCompTerminalListDAO getConsultCompTerminalListDAO() {
		return consultCompTerminalListDAO;
	}

	public void setConsultCompTerminalListDAO(ConsultCompTerminalListDAO consultCompTerminalListDAO) {
		this.consultCompTerminalListDAO = consultCompTerminalListDAO;
	}

	public List findTerminalList(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user)
    {
        return consultCompTerminalListDAO.findTerminalList(consultCompTerminalCommonDTO, user);
    }

	public int deleteTerminal(String[] deleteRows,String[] deleteRowsExt, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
        	for(int i=0; i<deleteRows.length; i++){
        		result = result + consultCompTerminalListDAO.deleteTerminal(deleteRowsExt[i], deleteRows[i], user);
        		
        	}
        return result;
    }

	public String findTotalCount(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user) {
		return consultCompTerminalListDAO.findTotalCount(consultCompTerminalCommonDTO, user);
	}
}

