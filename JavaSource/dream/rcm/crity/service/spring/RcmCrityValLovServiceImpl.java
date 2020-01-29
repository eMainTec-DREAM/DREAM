package dream.rcm.crity.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.rcm.crity.dao.RcmCrityValLovDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.form.RcmCrityValLovForm;
import dream.rcm.crity.service.RcmCrityValLovService;

/**
 * Criticality Matrix Page - List Service implements
 * @author kim21017
 * @version $Id: RcmCrityValLovServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmCrityValLovServiceTarget"
 * @spring.txbn id="rcmCrityValLovService"
 * @spring.property name="rcmCrityValLovDAO" ref="rcmCrityValLovDAO"
 */
public class RcmCrityValLovServiceImpl implements RcmCrityValLovService
{
	private RcmCrityValLovDAO rcmCrityValLovDAO = null;

	public List findList(RcmCrityValLovForm rcmCrityValLovForm, User user) throws Exception
    {      
		String code         = rcmCrityValLovForm.getCode();    //Search Value
        String keyCode      = rcmCrityValLovForm.getKeyCode();  //Search Column
        String codeType     = rcmCrityValLovForm.getCodeType(); //Table
        String jsonParam    = rcmCrityValLovForm.getParam();  //Condition
        String jaonCol      = rcmCrityValLovForm.getResultCol();  //Result Column


        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type); // <maPtMstrList.partNo:part_no>

        return rcmCrityValLovDAO.findList(rcmCrityValLovForm.getRcmCrityCommonDTO(),user, columnMap ,conditionMap);
    }

	public RcmCrityValLovDAO getRcmCrityValLovDAO() {
		return rcmCrityValLovDAO;
	}

	public void setRcmCrityValLovDAO(RcmCrityValLovDAO rcmCrityValLovDAO) {
		this.rcmCrityValLovDAO = rcmCrityValLovDAO;
	}
}

