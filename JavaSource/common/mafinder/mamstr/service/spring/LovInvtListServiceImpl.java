package common.mafinder.mamstr.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import common.mafinder.mamstr.dao.LovInvtListDAO;
import common.mafinder.mamstr.dto.LovInvtListDTO;
import common.mafinder.mamstr.form.LovInvtListForm;
import common.mafinder.mamstr.service.LovInvtListService;

/**
 * 투자 목록 팝업 ServiceImpl
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 *
 * @spring.bean id="lovInvtListServiceTarget"
 * @spring.txbn id="lovInvtListService"
 * @spring.property name="lovInvtListDAO" ref="lovInvtListDAO"
 */
public class LovInvtListServiceImpl implements LovInvtListService
{
    /** 투자 목록 팝업 DAO */
    private LovInvtListDAO lovInvtListDAO = null;

    public LovInvtListDAO getLovInvtListDAO() {
		return lovInvtListDAO;
	}

	public void setLovInvtListDAO(LovInvtListDAO lovInvtListDAO) {
		this.lovInvtListDAO = lovInvtListDAO;
	}

    @Override
    public List findInvtList(LovInvtListDTO lovInvtListDTO, User user, LovInvtListForm lovInvtListForm)
    {
        String jsonParam    = lovInvtListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        // TODO Auto-generated method stub
        return lovInvtListDAO.findInvtList(lovInvtListDTO, user, conditionMap);
    }

}