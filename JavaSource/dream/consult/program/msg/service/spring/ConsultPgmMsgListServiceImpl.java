package dream.consult.program.msg.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.msg.dao.ConsultPgmMsgListDAO;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;
import dream.consult.program.msg.service.ConsultPgmMsgListService;

/**
 * 메시지 설정(메일, SMS) - 목록 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 * @spring.bean id="consultPgmMsgListServiceTarget"
 * @spring.txbn id="consultPgmMsgListService"
 * @spring.property name="consultPgmMsgListDAO" ref="consultPgmMsgListDAO"
 */
public class ConsultPgmMsgListServiceImpl implements ConsultPgmMsgListService
{
    private ConsultPgmMsgListDAO consultPgmMsgListDAO = null;

    public ConsultPgmMsgListDAO getConsultPgmMsgListDAO() {
		return consultPgmMsgListDAO;
	}

	public void setConsultPgmMsgListDAO(ConsultPgmMsgListDAO consultPgmMsgListDAO) {
		this.consultPgmMsgListDAO = consultPgmMsgListDAO;
	}

	public List findList(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user)
    {
        return consultPgmMsgListDAO.findList(consultPgmMsgCommonDTO, user);
    }
	public String findTotalCount(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user)
    {
        return consultPgmMsgListDAO.findTotalCount(consultPgmMsgCommonDTO, user);
    }

	public int deleteList(String[] deleteRows, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
        	for(int i=0; i<deleteRows.length; i++){
        		result = result + consultPgmMsgListDAO.deleteList(deleteRows[i], user);
        		
        	}
        return result;
    }
}

