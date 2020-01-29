package dream.consult.comp.wrkcal.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalLovDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalLovDTO;
import dream.consult.comp.wrkcal.form.ConsultCompWrkcalLovForm;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalLovService;

/**
 * 근무달력 ServiceImpl
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalLovServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="consultCompWrkcalLovServiceTarget"
 * @spring.txbn id="consultCompWrkcalLovService"
 * @spring.property name="consultCompWrkcalLovDAO" ref="consultCompWrkcalLovDAO"
 */
public class ConsultCompWrkcalLovServiceImpl implements ConsultCompWrkcalLovService
{
    /**  DAO */
    private ConsultCompWrkcalLovDAO consultCompWrkcalLovDAO = null;

    public ConsultCompWrkcalLovDAO getConsultCompWrkcalLovDAO() {
		return consultCompWrkcalLovDAO;
	}

	public void setConsultCompWrkcalLovDAO(ConsultCompWrkcalLovDAO consultCompWrkcalLovDAO) {
		this.consultCompWrkcalLovDAO = consultCompWrkcalLovDAO;
	}

	public List findWrkcalList(ConsultCompWrkcalLovDTO consultCompWrkcalLovDTO, User loginUser)
    {
        List resultList = null;
        resultList = consultCompWrkcalLovDAO.findWrkcalList(consultCompWrkcalLovDTO, loginUser);
        
        return resultList;
    }
	
	@Override
    public List findWrkcalAcList(ConsultCompWrkcalLovForm consultCompWrkcalLovForm, User loginUser)
    {
	    ConsultCompWrkcalLovDTO consultCompWrkcalLovDTO= consultCompWrkcalLovForm.getConsultCompWrkcalLovDTO();
	    
        String code         = consultCompWrkcalLovForm.getCode();    //Search Value
        String keyCode      = consultCompWrkcalLovForm.getKeyCode();  //Search Column
        String codeType     = consultCompWrkcalLovForm.getCodeType(); //Table
        String jsonParam    = consultCompWrkcalLovForm.getParam();  //Condition
        String jaonCol      = consultCompWrkcalLovForm.getResultCol();  //Result Column


        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type); // <maPtMstrList.partNo:part_no>
        
        return consultCompWrkcalLovDAO.findWrkcalAcList(consultCompWrkcalLovDTO, loginUser, columnMap ,conditionMap);
    }
}