package dream.consult.comp.terminal.service.spring;

import common.bean.User;
import dream.consult.comp.terminal.dao.ConsultCompTerminalDetailDAO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalCommonDTO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalDetailDTO;
import dream.consult.comp.terminal.service.ConsultCompTerminalDetailService;

/**
 * 접근터미널 - 상세 serviceimpl
 * @author  kim21017
 * @version $Id: ConsultCompTerminalDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompTerminalDetailServiceTarget"
 * @spring.txbn id="consultCompTerminalDetailService"
 * @spring.property name="consultCompTerminalDetailDAO" ref="consultCompTerminalDetailDAO"
 */
public class ConsultCompTerminalDetailServiceImpl implements ConsultCompTerminalDetailService
{
    private ConsultCompTerminalDetailDAO consultCompTerminalDetailDAO = null;

    public ConsultCompTerminalDetailDAO getConsultCompTerminalDetailDAO() {
		return consultCompTerminalDetailDAO;
	}

	public void setConsultCompTerminalDetailDAO(ConsultCompTerminalDetailDAO consultCompTerminalDetailDAO) {
		this.consultCompTerminalDetailDAO = consultCompTerminalDetailDAO;
	}

	public ConsultCompTerminalDetailDTO findDetail(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user)throws Exception
    {
        return consultCompTerminalDetailDAO.findDetail(consultCompTerminalCommonDTO, user);
    }

	public int insertDetail(ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO, User user) throws Exception
    {
        return consultCompTerminalDetailDAO.insertDetail(consultCompTerminalDetailDTO, user);
    }

	public int updateDetail(ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO, User user) throws Exception
    {
        return consultCompTerminalDetailDAO.updateDetail(consultCompTerminalDetailDTO, user);
    }
}
