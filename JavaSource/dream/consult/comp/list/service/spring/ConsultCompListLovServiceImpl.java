package dream.consult.comp.list.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.comp.list.dao.ConsultCompListLovDAO;
import dream.consult.comp.list.dto.ConsultCompListLovDTO;
import dream.consult.comp.list.form.ConsultCompListLovForm;
import dream.consult.comp.list.service.ConsultCompListLovService;

/**
 * 회사코드 ServiceImpl
 * @author  hyosung
 * @version $Id: ConsultCompListLovServiceImpl.java,v 1.5 2015/01/09 00:16:42 hyosung Exp $
 * @since   1.0
 *
 * @spring.bean id="consultCompListLovServiceTarget"
 * @spring.txbn id="consultCompListLovService"
 * @spring.property name="consultCompListLovDAO" ref="consultCompListLovDAO"
 */
public class ConsultCompListLovServiceImpl implements ConsultCompListLovService
{
    /**  DAO */
    private ConsultCompListLovDAO consultCompListLovDAO = null;

    public ConsultCompListLovDAO getConsultCompListLovDAO() {
		return consultCompListLovDAO;
	}

	public void setConsultCompListLovDAO(ConsultCompListLovDAO consultCompListLovDAO) {
		this.consultCompListLovDAO = consultCompListLovDAO;
	}

	public List findCompList(ConsultCompListLovDTO consultCompListLovDTO, User loginUser)
    {
        List resultList = null;
        resultList = consultCompListLovDAO.findCompList(consultCompListLovDTO, loginUser);
        
        return resultList;
    }

    
    public List findCompAcList(ConsultCompListLovDTO consultCompListLovDTO,
            User user, ConsultCompListLovForm consultCompListLovForm)
    {
        String code         = consultCompListLovForm.getCode();    //Search Value
        String keyCode      = consultCompListLovForm.getKeyCode();  //Search Column
        String codeType     = consultCompListLovForm.getCodeType(); //Table
        String jsonParam    = consultCompListLovForm.getParam();  //Condition
        String jaonCol      = consultCompListLovForm.getResultCol();  //Result Column


        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type); // <maPtMstrList.partNo:part_no>
        
   
        return consultCompListLovDAO.findCompAcList(consultCompListLovDTO, user, columnMap ,conditionMap);
    }
	
	
	
}