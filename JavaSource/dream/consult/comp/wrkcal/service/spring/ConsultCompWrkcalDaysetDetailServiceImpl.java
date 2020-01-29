package dream.consult.comp.wrkcal.service.spring;

import common.bean.User;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalDaysetDetailDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetDetailDTO;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalDaysetDetailService;

/**
 * 휴무일 설정 - 상세 serviceimpl
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDaysetDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompWrkcalDaysetDetailServiceTarget"
 * @spring.txbn id="consultCompWrkcalDaysetDetailService"
 * @spring.property name="consultCompWrkcalDaysetDetailDAO" ref="consultCompWrkcalDaysetDetailDAO"
 */
public class ConsultCompWrkcalDaysetDetailServiceImpl implements ConsultCompWrkcalDaysetDetailService
{
    private ConsultCompWrkcalDaysetDetailDAO consultCompWrkcalDaysetDetailDAO = null;

    public ConsultCompWrkcalDaysetDetailDAO getConsultCompWrkcalDaysetDetailDAO() {
		return consultCompWrkcalDaysetDetailDAO;
	}

	public void setConsultCompWrkcalDaysetDetailDAO(ConsultCompWrkcalDaysetDetailDAO consultCompWrkcalDaysetDetailDAO) {
		this.consultCompWrkcalDaysetDetailDAO = consultCompWrkcalDaysetDetailDAO;
	}

	public ConsultCompWrkcalDaysetDetailDTO findDetail(String wrkcalDaysetId)throws Exception
    {
        return consultCompWrkcalDaysetDetailDAO.findDetail(wrkcalDaysetId);
    }

	public int insertDetail(ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO, ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO) throws Exception
    {
        return consultCompWrkcalDaysetDetailDAO.insertDetail(consultCompWrkcalDaysetDetailDTO, consultCompWrkcalCommonDTO);
    }

	public int updateDetail(ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO) throws Exception
    {
        return consultCompWrkcalDaysetDetailDAO.updateDetail(consultCompWrkcalDaysetDetailDTO);
    }
}
