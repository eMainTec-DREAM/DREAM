package dream.work.let.categ.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.work.let.categ.dao.LovWorkLetCategListDAO;
import dream.work.let.categ.dto.LovWorkLetCategListDTO;
import dream.work.let.categ.form.LovWorkLetCategListForm;
import dream.work.let.categ.service.LovWorkLetCategListService;

/**
 * 안전작업유형 Lov ServiceImpl
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovWorkLetCategListServiceTarget"
 * @spring.txbn id="lovWorkLetCategListService"
 * @spring.property name="lovWorkLetCategListDAO" ref="lovWorkLetCategListDAO"
 */
public class LovWorkLetCategListServiceImpl implements LovWorkLetCategListService
{
    /** 안전작업유형 팝업 DAO */
    private LovWorkLetCategListDAO lovWorkLetCategListDAO = null;

	public LovWorkLetCategListDAO getLovWorkLetCategListDAO()
    {
        return lovWorkLetCategListDAO;
    }

    public void setLovWorkLetCategListDAO(LovWorkLetCategListDAO lovWorkLetCategListDAO)
    {
        this.lovWorkLetCategListDAO = lovWorkLetCategListDAO;
    }

	@Override
	public List findWorkLetCategAcList(LovWorkLetCategListDTO lovWorkLetCategListDTO, User user, LovWorkLetCategListForm lovWorkLetCategListForm)
	{
		String jsonParam    = lovWorkLetCategListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
		return lovWorkLetCategListDAO.findWorkLetCategAcList(lovWorkLetCategListDTO, user, conditionMap);
	}

	@Override
	public String findTotalCount(LovWorkLetCategListDTO lovWorkLetCategListDTO, User user, LovWorkLetCategListForm lovWorkLetCategListForm) throws Exception
	{
		String jsonParam    = lovWorkLetCategListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        return lovWorkLetCategListDAO.findTotalCount(lovWorkLetCategListDTO, user, conditionMap);
	}
	
}