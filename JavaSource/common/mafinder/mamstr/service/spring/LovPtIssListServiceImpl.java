package common.mafinder.mamstr.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import common.mafinder.mamstr.dao.LovPtIssListDAO;
import common.mafinder.mamstr.dto.LovPtIssListDTO;
import common.mafinder.mamstr.form.LovPtIssListForm;
import common.mafinder.mamstr.service.LovPtIssListService;

/**
 * Ãâ°íºÎÇ° ÆË¾÷ ServiceImpl
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovPtIssListServiceTarget"
 * @spring.txbn id="lovPtIssListService"
 * @spring.property name="lovPtIssListDAO" ref="lovPtIssListDAO"
 */
public class LovPtIssListServiceImpl implements LovPtIssListService
{
    /** ÀÛ¾÷ ÆË¾÷ DAO */
    private LovPtIssListDAO lovPtIssListDAO = null;

    public LovPtIssListDAO getLovPtIssListDAO() {
		return lovPtIssListDAO;
	}

	public void setLovPtIssListDAO(LovPtIssListDAO lovPtIssListDAO) {
		this.lovPtIssListDAO = lovPtIssListDAO;
	}
	
	@Override
	public List findPtIssAcList(LovPtIssListDTO lovPtIssListDTO, User user, LovPtIssListForm lovPtIssListForm) {
		String jsonParam    = lovPtIssListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovPtIssListDAO.findPtIssAcList(lovPtIssListDTO, user ,conditionMap);
	}

	@Override
	public String findPtIssListTotalCount(LovPtIssListDTO lovPtIssListDTO,User user, LovPtIssListForm lovPtIssListForm)
	{
		String jsonParam    = lovPtIssListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
	    return lovPtIssListDAO.findPtIssListTotalCount(lovPtIssListDTO, user, conditionMap);
	}


}