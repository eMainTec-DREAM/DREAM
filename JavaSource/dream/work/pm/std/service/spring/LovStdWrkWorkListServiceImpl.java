package dream.work.pm.std.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.work.pm.std.dao.LovStdWrkWorkListDAO;
import dream.work.pm.std.dto.LovStdWrkWorkListDTO;
import dream.work.pm.std.form.LovStdWrkWorkListForm;
import dream.work.pm.std.service.LovStdWrkWorkListService;

/**
 * 표준점검항목 ServiceImpl
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovStdWrkWorkListServiceTarget"
 * @spring.txbn id="lovStdWrkWorkListService"
 * @spring.property name="lovStdWrkWorkListDAO" ref="lovStdWrkWorkListDAO"
 */
public class LovStdWrkWorkListServiceImpl implements LovStdWrkWorkListService
{
    /** 표준점검항목팝업 DAO */
    private LovStdWrkWorkListDAO lovStdWrkWorkListDAO = null;

	public LovStdWrkWorkListDAO getLovStdWrkWorkListDAO()
    {
        return lovStdWrkWorkListDAO;
    }

    public void setLovStdWrkWorkListDAO(LovStdWrkWorkListDAO lovStdWrkWorkListDAO)
    {
        this.lovStdWrkWorkListDAO = lovStdWrkWorkListDAO;
    }

    public List findStdCheckPointList(LovStdWrkWorkListDTO lovStdWrkWorkListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovStdWrkWorkListDAO.findStdCheckPointList(lovStdWrkWorkListDTO,loginUser);
        
        return resultList;
    }

	@Override
	public List findStdCheckPointAcList(LovStdWrkWorkListDTO lovStdWrkWorkListDTO, User user, LovStdWrkWorkListForm lovStdWrkWorkListForm) {
		String jsonParam    = lovStdWrkWorkListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovStdWrkWorkListDAO.findStdCheckPointAcList(lovStdWrkWorkListDTO, user ,conditionMap);
	}
}