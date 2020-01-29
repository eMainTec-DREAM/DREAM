package dream.consult.comp.wrkcal.service.spring;

import common.bean.User;

import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalDetailDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDetailDTO;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalDetailService;

/**
 * 회사설정 - 상세 serviceimpl
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompWrkcalDetailServiceTarget"
 * @spring.txbn id="consultCompWrkcalDetailService"
 * @spring.property name="consultCompWrkcalDetailDAO" ref="consultCompWrkcalDetailDAO"
 */
public class ConsultCompWrkcalDetailServiceImpl implements ConsultCompWrkcalDetailService
{
    private ConsultCompWrkcalDetailDAO consultCompWrkcalDetailDAO = null;

    public ConsultCompWrkcalDetailDAO getConsultCompWrkcalDetailDAO() {
		return consultCompWrkcalDetailDAO;
	}

	public void setConsultCompWrkcalDetailDAO(ConsultCompWrkcalDetailDAO consultCompWrkcalDetailDAO) {
		this.consultCompWrkcalDetailDAO = consultCompWrkcalDetailDAO;
	}

	public ConsultCompWrkcalDetailDTO findDetail(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user)throws Exception
    {
        return consultCompWrkcalDetailDAO.findDetail(consultCompWrkcalCommonDTO, user);
    }

	public int insertDetail(ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO, User user) throws Exception
    {
        return consultCompWrkcalDetailDAO.insertDetail(consultCompWrkcalDetailDTO, user);
    }

	public int updateDetail(ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO, User user) throws Exception
    {
        return consultCompWrkcalDetailDAO.updateDetail(consultCompWrkcalDetailDTO, user);
    }
}
