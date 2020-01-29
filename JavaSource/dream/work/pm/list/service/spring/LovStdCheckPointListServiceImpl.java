package dream.work.pm.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.work.pm.list.dao.LovStdCheckPointListDAO;
import dream.work.pm.list.dto.LovStdCheckPointListDTO;
import dream.work.pm.list.form.LovStdCheckPointListForm;
import dream.work.pm.list.service.LovStdCheckPointListService;

/**
 * 표준점검항목 ServiceImpl
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovStdCheckPointListServiceTarget"
 * @spring.txbn id="lovStdCheckPointListService"
 * @spring.property name="lovStdCheckPointListDAO" ref="lovStdCheckPointListDAO"
 */
public class LovStdCheckPointListServiceImpl implements LovStdCheckPointListService
{
    /** 표준점검항목팝업 DAO */
    private LovStdCheckPointListDAO lovStdCheckPointListDAO = null;

	public LovStdCheckPointListDAO getLovStdCheckPointListDAO()
    {
        return lovStdCheckPointListDAO;
    }

    public void setLovStdCheckPointListDAO(LovStdCheckPointListDAO lovStdCheckPointListDAO)
    {
        this.lovStdCheckPointListDAO = lovStdCheckPointListDAO;
    }

    public List findStdCheckPointList(LovStdCheckPointListDTO lovStdCheckPointListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovStdCheckPointListDAO.findStdCheckPointList(lovStdCheckPointListDTO,loginUser);
        
        return resultList;
    }

	@Override
	public List findStdCheckPointAcList(LovStdCheckPointListDTO lovStdCheckPointListDTO, User user, LovStdCheckPointListForm lovStdCheckPointListForm) {
		String jsonParam    = lovStdCheckPointListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovStdCheckPointListDAO.findStdCheckPointAcList(lovStdCheckPointListDTO, user ,conditionMap);
	}
}