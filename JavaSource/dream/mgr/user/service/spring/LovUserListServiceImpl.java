package dream.mgr.user.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.mgr.user.dao.LovUserListDAO;
import dream.mgr.user.dto.LovUserListDTO;
import dream.mgr.user.form.LovUserListForm;
import dream.mgr.user.service.LovUserListService;

/**
 * »ç¿ëÀÚ ÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovUserListServiceTarget"
 * @spring.txbn id="lovUserListService"
 * @spring.property name="lovUserListDAO" ref="lovUserListDAO"
 */
public class LovUserListServiceImpl implements LovUserListService
{
    /** ÆË¾÷ DAO */
    private LovUserListDAO lovUserListDAO = null;

    public LovUserListDAO getLovUserListDAO() 
    {
		return lovUserListDAO;
	}

	public void setLovUserListDAO(LovUserListDAO lovUserListDAO) 
	{
		this.lovUserListDAO = lovUserListDAO;
	}

	public List findUserList(LovUserListDTO lovUserListDTO, User loginUser)
    {
        List resultList = lovUserListDAO.findUserList(lovUserListDTO, loginUser);
        
        return resultList;
    }

	@Override
	public List findUserList(LovUserListDTO lovUserListDTO, User user, LovUserListForm lovUserListForm) {
		// TODO Auto-generated method stub
		String jsonParam    = lovUserListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovUserListDAO.findUserAcList(lovUserListDTO, user ,conditionMap);
	}
}