package dream.consult.program.msg.service.spring;

import common.bean.User;
import dream.consult.program.msg.dao.ConsultPgmMsgDetailDAO;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;
import dream.consult.program.msg.dto.ConsultPgmMsgDetailDTO;
import dream.consult.program.msg.service.ConsultPgmMsgDetailService;

/**
 * 메시지 설정(메일, SMS) - 상세 serviceimpl
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="consultPgmMsgDetailServiceTarget"
 * @spring.txbn id="consultPgmMsgDetailService"
 * @spring.property name="consultPgmMsgDetailDAO" ref="consultPgmMsgDetailDAO"
 */
public class ConsultPgmMsgDetailServiceImpl implements ConsultPgmMsgDetailService
{
    private ConsultPgmMsgDetailDAO consultPgmMsgDetailDAO = null;

    public ConsultPgmMsgDetailDAO getConsultPgmMsgDetailDAO() {
		return consultPgmMsgDetailDAO;
	}

	public void setConsultPgmMsgDetailDAO(ConsultPgmMsgDetailDAO consultPgmMsgDetailDAO) {
		this.consultPgmMsgDetailDAO = consultPgmMsgDetailDAO;
	}

	public ConsultPgmMsgDetailDTO findDetail(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user)throws Exception
    {
        return consultPgmMsgDetailDAO.findDetail(consultPgmMsgCommonDTO, user);
    }

	public int insertDetail(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user) throws Exception
    {
		consultPgmMsgDetailDAO.insertDetail(consultPgmMsgDetailDTO, user);
		return consultPgmMsgDetailDAO.updateLang(consultPgmMsgDetailDTO, user);
    }

	public int updateDetail(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user) throws Exception
    {
        consultPgmMsgDetailDAO.updateDetail(consultPgmMsgDetailDTO, user);
        return consultPgmMsgDetailDAO.updateLang(consultPgmMsgDetailDTO, user);
    }

	public String validMsgObjType(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user) throws Exception {
		return consultPgmMsgDetailDAO.validMsgObjType(consultPgmMsgDetailDTO, user);
	}
}
