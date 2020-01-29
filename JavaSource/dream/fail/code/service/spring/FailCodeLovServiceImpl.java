package dream.fail.code.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.fail.code.dao.FailCodeLovDAO;
import dream.fail.code.dto.MaFailureCommonDTO;
import dream.fail.code.form.FailCodeLovForm;
import dream.fail.code.service.FailCodeLovService;

/**
 * 고장코드 - 목록 serviceimpl
 * @author ssong
 * @version
 * @since 1.0
 * 
 * @spring.bean id="failCodeLovServiceTarget"
 * @spring.txbn id="failCodeLovService"
 * @spring.property name="failCodeLovDAO" ref="failCodeLovDAO"
 */
public class FailCodeLovServiceImpl implements FailCodeLovService
{
    private FailCodeLovDAO failCodeLovDAO = null;

    public FailCodeLovDAO getFailCodeLovDAO() 
    {
		return failCodeLovDAO;
	}

	public void setFailCodeLovDAO(FailCodeLovDAO failCodeLovDAO) 
	{
		this.failCodeLovDAO = failCodeLovDAO;
	}

	@Override
	public List findList(MaFailureCommonDTO maFailureCommonDTO, User user, FailCodeLovForm failCodeLovForm) {
		
		String code         = failCodeLovForm.getCode();    //Search Value
        String keyCode      = failCodeLovForm.getKeyCode();  //Search Column
        String codeType     = failCodeLovForm.getCodeType(); //Table
        String jsonParam    = failCodeLovForm.getParam();  //Condition
        String jaonCol      = failCodeLovForm.getResultCol();  //Result Column


        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type); // <maPtMstrList.partNo:part_no>

		return failCodeLovDAO.findList(maFailureCommonDTO,user, columnMap ,conditionMap);
	}
}

