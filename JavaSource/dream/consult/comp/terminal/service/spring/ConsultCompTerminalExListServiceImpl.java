package dream.consult.comp.terminal.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.terminal.dao.ConsultCompTerminalExListDAO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExCommonDTO;
import dream.consult.comp.terminal.service.ConsultCompTerminalExListService;

/**
 * 접근터미널 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: ConsultCompTerminalExListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="consultCompTerminalExListServiceTarget"
 * @spring.txbn id="consultCompTerminalExListService"
 * @spring.property name="consultCompTerminalExListDAO" ref="consultCompTerminalExListDAO"
 */
public class ConsultCompTerminalExListServiceImpl implements ConsultCompTerminalExListService
{
    private ConsultCompTerminalExListDAO consultCompTerminalExListDAO = null;

    public ConsultCompTerminalExListDAO getConsultCompTerminalExListDAO() {
		return consultCompTerminalExListDAO;
	}

	public void setConsultCompTerminalExListDAO(ConsultCompTerminalExListDAO consultCompTerminalExListDAO) {
		this.consultCompTerminalExListDAO = consultCompTerminalExListDAO;
	}

	public List findTerminalExList(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO, User user)
    {
        return consultCompTerminalExListDAO.findTerminalExList(consultCompTerminalExCommonDTO, user);
    }

	public int deleteTerminal(String[] deleteRows,String[] deleteRowsExt, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
        	for(int i=0; i<deleteRows.length; i++){
        		result = result + consultCompTerminalExListDAO.deleteTerminal(deleteRowsExt[i], deleteRows[i], user);
        		
        	}
        return result;
    }

	public String findTotalCount(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO, User user) {
		return consultCompTerminalExListDAO.findTotalCount(consultCompTerminalExCommonDTO, user);
	}
}

