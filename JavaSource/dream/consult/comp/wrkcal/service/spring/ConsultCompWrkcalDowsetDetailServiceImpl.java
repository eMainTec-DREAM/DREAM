package dream.consult.comp.wrkcal.service.spring;

import common.bean.User;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalDowsetDetailDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetDetailDTO;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalDowsetDetailService;

/**
 * 회사설정 - 상세 serviceimpl
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDowsetDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompWrkcalDowsetDetailServiceTarget"
 * @spring.txbn id="consultCompWrkcalDowsetDetailService"
 * @spring.property name="consultCompWrkcalDowsetDetailDAO" ref="consultCompWrkcalDowsetDetailDAO"
 */
public class ConsultCompWrkcalDowsetDetailServiceImpl implements ConsultCompWrkcalDowsetDetailService
{
    private ConsultCompWrkcalDowsetDetailDAO consultCompWrkcalDowsetDetailDAO = null;

    public ConsultCompWrkcalDowsetDetailDAO getConsultCompWrkcalDowsetDetailDAO() {
		return consultCompWrkcalDowsetDetailDAO;
	}

	public void setConsultCompWrkcalDowsetDetailDAO(ConsultCompWrkcalDowsetDetailDAO consultCompWrkcalDowsetDetailDAO) {
		this.consultCompWrkcalDowsetDetailDAO = consultCompWrkcalDowsetDetailDAO;
	}

	public ConsultCompWrkcalDowsetDetailDTO findDetail(String wrkcalDowsetId, String lang)throws Exception
    {
        return consultCompWrkcalDowsetDetailDAO.findDetail(wrkcalDowsetId, lang);
    }

	public int insertDetail(ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO, ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO) throws Exception
    {
        return consultCompWrkcalDowsetDetailDAO.insertDetail(consultCompWrkcalDowsetDetailDTO, consultCompWrkcalCommonDTO);
    }

	public int updateDetail(ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO) throws Exception
    {
        return consultCompWrkcalDowsetDetailDAO.updateDetail(consultCompWrkcalDowsetDetailDTO);
    }
}
