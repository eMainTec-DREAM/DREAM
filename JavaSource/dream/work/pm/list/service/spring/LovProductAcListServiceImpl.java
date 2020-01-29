package dream.work.pm.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.work.pm.list.dao.LovProductAcListDAO;
import dream.work.pm.list.dto.LovProductAcListDTO;
import dream.work.pm.list.form.LovProductAcListForm;
import dream.work.pm.list.service.LovProductAcListService;

/**
 * 积魂力前 ServiceImpl
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovProductAcListServiceTarget"
 * @spring.txbn id="lovProductAcListService"
 * @spring.property name="lovProductAcListDAO" ref="lovProductAcListDAO"
 */
public class LovProductAcListServiceImpl implements LovProductAcListService
{
    /** 积魂力前 扑诀 DAO */
    private LovProductAcListDAO lovProductAcListDAO = null;

	public LovProductAcListDAO getLovProductAcListDAO()
    {
        return lovProductAcListDAO;
    }

    public void setLovProductAcListDAO(LovProductAcListDAO lovProductAcListDAO)
    {
        this.lovProductAcListDAO = lovProductAcListDAO;
    }

    public List findProductAcList(LovProductAcListDTO lovProductAcListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovProductAcListDAO.findProductAcList(lovProductAcListDTO,loginUser);
        
        return resultList;
    }

	@Override
	public List findProductAcAcList(LovProductAcListDTO lovProductAcListDTO, User user, LovProductAcListForm lovProductAcListForm) {
		String jsonParam    = lovProductAcListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovProductAcListDAO.findProductAcAcList(lovProductAcListDTO, user ,conditionMap);
	}
}