package dream.consult.comp.wrkcal.service.spring;

import java.util.List;

import common.bean.User;

import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalListDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalListService;

/**
 * 근무일달력 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: ConsultCompWrkcalListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="consultCompWrkcalListServiceTarget"
 * @spring.txbn id="consultCompWrkcalListService"
 * @spring.property name="consultCompWrkcalListDAO" ref="consultCompWrkcalListDAO"
 */
public class ConsultCompWrkcalListServiceImpl implements ConsultCompWrkcalListService
{
    private ConsultCompWrkcalListDAO consultCompWrkcalListDAO = null;

    public ConsultCompWrkcalListDAO getConsultCompWrkcalListDAO() {
		return consultCompWrkcalListDAO;
	}

	public void setConsultCompWrkcalListDAO(ConsultCompWrkcalListDAO consultCompWrkcalListDAO) {
		this.consultCompWrkcalListDAO = consultCompWrkcalListDAO;
	}

	public List findWrkcalList(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user)
    {
        return consultCompWrkcalListDAO.findWrkcalList(consultCompWrkcalCommonDTO,user);
    }

	public int deleteWrkcal(String[] deleteRows, String[] deleteRowsExt, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
        	for(int i = 0; i< deleteRows.length; i++){
        		
        		result = result + consultCompWrkcalListDAO.deleteWrkcal(deleteRows[i], deleteRowsExt[i], user);
        	}
        return result;
    }

	public String findTotalCount(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user)
	{
		return consultCompWrkcalListDAO.findTotalCount(consultCompWrkcalCommonDTO, user);
	}
}

