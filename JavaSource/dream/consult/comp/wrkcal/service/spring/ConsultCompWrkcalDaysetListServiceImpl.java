package dream.consult.comp.wrkcal.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalDaysetListDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetListDTO;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalDaysetListService;

/**
 * 휴무일 설정  - 목록 serviceimpl
 * @author kim21017
 * @version $Id: ConsultCompWrkcalDaysetListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="consultCompWrkcalDaysetListServiceTarget"
 * @spring.txbn id="consultCompWrkcalDaysetListService"
 * @spring.property name="consultCompWrkcalDaysetListDAO" ref="consultCompWrkcalDaysetListDAO"
 */
public class ConsultCompWrkcalDaysetListServiceImpl implements ConsultCompWrkcalDaysetListService
{
    private ConsultCompWrkcalDaysetListDAO consultCompWrkcalDaysetListDAO = null;

    public ConsultCompWrkcalDaysetListDAO getConsultCompWrkcalDaysetListDAO() {
		return consultCompWrkcalDaysetListDAO;
	}

	public void setConsultCompWrkcalDaysetListDAO(ConsultCompWrkcalDaysetListDAO consultCompWrkcalDaysetListDAO) {
		this.consultCompWrkcalDaysetListDAO = consultCompWrkcalDaysetListDAO;
	}

	public List findDaysetList(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO, User user)
    {
        return consultCompWrkcalDaysetListDAO.findDaysetList(consultCompWrkcalCommonDTO, consultCompWrkcalDaysetListDTO, user);
    }

	public int deleteWrkcal(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultCompWrkcalDaysetListDAO.deleteWrkcal(id);
            }
        return result;
    }
	
	public String findTotalCount(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO,
			ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO, User user) 
	{
		return consultCompWrkcalDaysetListDAO.findTotalCount(consultCompWrkcalCommonDTO, consultCompWrkcalDaysetListDTO, user);
	}
}

