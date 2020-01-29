package dream.budget.account.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.budget.account.dao.LovAccountListDAO;
import dream.budget.account.dto.LovAccountListDTO;
import dream.budget.account.form.LovAccountListForm;
import dream.budget.account.service.LovAccountListService;

/**
 * 예산계정LOV ServiceImpl
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovAccountListServiceTarget"
 * @spring.txbn id="lovAccountListService"
 * @spring.property name="lovAccountListDAO" ref="lovAccountListDAO"
 */
public class LovAccountListServiceImpl implements LovAccountListService
{
    /** 자산팝업 DAO */
    private LovAccountListDAO lovAccountListDAO = null;

    public LovAccountListDAO getLovAccountListDAO() {
		return lovAccountListDAO;
	}

	public void setLovAccountListDAO(LovAccountListDAO lovAccountListDAO) {
		this.lovAccountListDAO = lovAccountListDAO;
	}

	public List findList(LovAccountListDTO lovAccountListDTO, User loginUser, LovAccountListForm  lovAccountListForm)
    {
     
	    String jsonParam    = lovAccountListForm.getParam();  //Condition
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
	    
        return lovAccountListDAO.findList(lovAccountListDTO, loginUser, conditionMap);
    }

    @Override
    public String findTotalCount(LovAccountListDTO lovAccountListDTO, User loginUser, LovAccountListForm lovAccountListForm)
    {
        String jsonParam    = lovAccountListForm.getParam();  //Condition
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        return lovAccountListDAO.findTotalCount(lovAccountListDTO, loginUser, conditionMap);
    }
}