package dream.work.pm.std.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.work.pm.std.dao.WorkPmStdCalibValLovDAO;
import dream.work.pm.std.dto.WorkPmStdCalibValLovDTO;
import dream.work.pm.std.form.WorkPmStdCalibValLovForm;
import dream.work.pm.std.service.WorkPmStdCalibValLovService;

/**
 * 교정표준값 LOV - List Service implements
 * @author kim21017
 * @version $Id: WorkPmStdCalibValLovServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workPmStdCalibValLovServiceTarget"
 * @spring.txbn id="workPmStdCalibValLovService"
 * @spring.property name="workPmStdCalibValLovDAO" ref="workPmStdCalibValLovDAO"
 */
public class WorkPmStdCalibValLovServiceImpl implements WorkPmStdCalibValLovService
{
	private WorkPmStdCalibValLovDAO workPmStdCalibValLovDAO = null;

	public List findList(WorkPmStdCalibValLovForm workPmStdCalibValLovForm, User user) throws Exception
    {      
		WorkPmStdCalibValLovDTO workPmStdCalibValLovDTO = workPmStdCalibValLovForm.getWorkPmStdCalibValLovDTO();
		
		String code         = workPmStdCalibValLovForm.getCode();    //Search Value
        String keyCode      = workPmStdCalibValLovForm.getKeyCode();  //Search Column
        String codeType     = workPmStdCalibValLovForm.getCodeType(); //Table
        String jsonParam    = workPmStdCalibValLovForm.getParam();  //Condition
        String jaonCol      = workPmStdCalibValLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return workPmStdCalibValLovDAO.findList(workPmStdCalibValLovDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(WorkPmStdCalibValLovForm workPmStdCalibValLovForm, User user) throws Exception
    {      
		WorkPmStdCalibValLovDTO workPmStdCalibValLovDTO = workPmStdCalibValLovForm.getWorkPmStdCalibValLovDTO();
		
		String code         = workPmStdCalibValLovForm.getCode();    //Search Value
        String keyCode      = workPmStdCalibValLovForm.getKeyCode();  //Search Column
        String codeType     = workPmStdCalibValLovForm.getCodeType(); //Table
        String jsonParam    = workPmStdCalibValLovForm.getParam();  //Condition
        String jaonCol      = workPmStdCalibValLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return workPmStdCalibValLovDAO.findTotalCount(workPmStdCalibValLovDTO,user, columnMap ,conditionMap);
    }

	public WorkPmStdCalibValLovDAO getWorkPmStdCalibValLovDAO() {
		return workPmStdCalibValLovDAO;
	}

	public void setWorkPmStdCalibValLovDAO(WorkPmStdCalibValLovDAO workPmStdCalibValLovDAO) {
		this.workPmStdCalibValLovDAO = workPmStdCalibValLovDAO;
	}
	
}

