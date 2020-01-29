package dream.consult.comp.terminal.service.spring;

import common.bean.User;
import dream.consult.comp.terminal.dao.ConsultCompTerminalExDetailDAO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExCommonDTO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExDetailDTO;
import dream.consult.comp.terminal.service.ConsultCompTerminalExDetailService;

/**
 * 접근터미널 - 상세 serviceimpl
 * @author  kim21017
 * @version $Id: ConsultCompTerminalExDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompTerminalExDetailServiceTarget"
 * @spring.txbn id="consultCompTerminalExDetailService"
 * @spring.property name="consultCompTerminalExDetailDAO" ref="consultCompTerminalExDetailDAO"
 */
public class ConsultCompTerminalExDetailServiceImpl implements ConsultCompTerminalExDetailService
{
    private ConsultCompTerminalExDetailDAO consultCompTerminalExDetailDAO = null;

    public ConsultCompTerminalExDetailDAO getConsultCompTerminalExDetailDAO() {
		return consultCompTerminalExDetailDAO;
	}

	public void setConsultCompTerminalExDetailDAO(ConsultCompTerminalExDetailDAO consultCompTerminalExDetailDAO) {
		this.consultCompTerminalExDetailDAO = consultCompTerminalExDetailDAO;
	}

	public ConsultCompTerminalExDetailDTO findDetail(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO, User user)throws Exception
    {
        return consultCompTerminalExDetailDAO.findDetail(consultCompTerminalExCommonDTO, user);
    }

	public int insertDetail(ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO, User user) throws Exception
    {
        return consultCompTerminalExDetailDAO.insertDetail(consultCompTerminalExDetailDTO, user);
    }

	public int updateDetail(ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO, User user) throws Exception
    {
        return consultCompTerminalExDetailDAO.updateDetail(consultCompTerminalExDetailDTO, user);
    }
}
