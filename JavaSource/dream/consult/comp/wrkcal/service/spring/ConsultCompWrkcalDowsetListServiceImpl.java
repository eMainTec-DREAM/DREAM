package dream.consult.comp.wrkcal.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalDowsetListDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetListDTO;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalDowsetListService;

/**
 * 휴무요일 설정  - 목록 serviceimpl
 * @author kim21017
 * @version $Id: ConsultCompWrkcalDowsetListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="consultCompWrkcalDowsetListServiceTarget"
 * @spring.txbn id="consultCompWrkcalDowsetListService"
 * @spring.property name="consultCompWrkcalDowsetListDAO" ref="consultCompWrkcalDowsetListDAO"
 */
public class ConsultCompWrkcalDowsetListServiceImpl implements ConsultCompWrkcalDowsetListService
{
    private ConsultCompWrkcalDowsetListDAO consultCompWrkcalDowsetListDAO = null;

    public ConsultCompWrkcalDowsetListDAO getConsultCompWrkcalDowsetListDAO() {
		return consultCompWrkcalDowsetListDAO;
	}

	public void setConsultCompWrkcalDowsetListDAO(ConsultCompWrkcalDowsetListDAO consultCompWrkcalDowsetListDAO) {
		this.consultCompWrkcalDowsetListDAO = consultCompWrkcalDowsetListDAO;
	}

	public List findDowsetList(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO, User user)
    {
        return consultCompWrkcalDowsetListDAO.findDowsetList(consultCompWrkcalCommonDTO, consultCompWrkcalDowsetListDTO, user);
    }

	public int deleteWrkcal(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultCompWrkcalDowsetListDAO.deleteWrkcal(id);
            }
        return result;
    }

	public String findTotalCount(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO,
			ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO, User user) {
		return consultCompWrkcalDowsetListDAO.findTotalCount(consultCompWrkcalCommonDTO, consultCompWrkcalDowsetListDTO, user);
	}
}

