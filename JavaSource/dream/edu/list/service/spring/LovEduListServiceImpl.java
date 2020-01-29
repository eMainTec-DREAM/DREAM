package dream.edu.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.edu.list.dao.LovEduListDAO;
import dream.edu.list.dto.LovEduListDTO;
import dream.edu.list.form.LovEduListForm;
import dream.edu.list.service.LovEduListService;

/**
 * ±³À°°úÁ¤ ServiceImpl
 * @author  hyosung
 * @version $Id: LovEduListServiceImpl.java,v 1.5 2015/01/09 00:16:42 hyosung Exp $
 * @since   1.0
 *
 * @spring.bean id="lovEduListServiceTarget"
 * @spring.txbn id="lovEduListService"
 * @spring.property name="lovEduListDAO" ref="lovEduListDAO"
 */
public class LovEduListServiceImpl implements LovEduListService
{
    /** ÀÚ»êÆË¾÷ DAO */
    private LovEduListDAO lovEduListDAO = null;

    public LovEduListDAO getLovEduListDAO() {
		return lovEduListDAO;
	}

	public void setLovEduListDAO(LovEduListDAO lovEduListDAO) {
		this.lovEduListDAO = lovEduListDAO;
	}

	public List findEduList(LovEduListDTO lovEduListDTO, User loginUser,LovEduListForm lovEduListForm)
    {
	    
	    String jsonParam    = lovEduListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
    
        return lovEduListDAO.findEduList(lovEduListDTO, loginUser,conditionMap);
    }
}