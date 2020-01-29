package dream.work.let.categ.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.work.let.categ.dao.LovWorkLetCategPointListDAO;
import dream.work.let.categ.dto.LovWorkLetCategPointListDTO;
import dream.work.let.categ.form.LovWorkLetCategPointListForm;
import dream.work.let.categ.service.LovWorkLetCategPointListService;

/**
 * 안전작업허가서 표준점검항목 Lov ServiceImpl
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovWorkLetCategPointListServiceTarget"
 * @spring.txbn id="lovWorkLetCategPointListService"
 * @spring.property name="lovWorkLetCategPointListDAO" ref="lovWorkLetCategPointListDAO"
 */
public class LovWorkLetCategPointListServiceImpl implements LovWorkLetCategPointListService
{
    /** 안전작업허가서 표준점검항목 Lov DAO */
    private LovWorkLetCategPointListDAO lovWorkLetCategPointListDAO = null;

	public LovWorkLetCategPointListDAO getLovWorkLetCategPointListDAO()
    {
        return lovWorkLetCategPointListDAO;
    }

    public void setLovWorkLetCategPointListDAO(LovWorkLetCategPointListDAO lovWorkLetCategPointListDAO)
    {
        this.lovWorkLetCategPointListDAO = lovWorkLetCategPointListDAO;
    }

	@Override
	public List findWorkLetCategPointAcList(LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO, User user, LovWorkLetCategPointListForm lovWorkLetCategPointListForm)
	{
		String jsonParam    = lovWorkLetCategPointListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
		return lovWorkLetCategPointListDAO.findWorkLetCategPointAcList(lovWorkLetCategPointListDTO, user, conditionMap);
	}

	@Override
	public String findTotalCount(LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO, User user, LovWorkLetCategPointListForm lovWorkLetCategPointListForm) throws Exception
	{
		String jsonParam    = lovWorkLetCategPointListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        return lovWorkLetCategPointListDAO.findTotalCount(lovWorkLetCategPointListDTO, user, conditionMap);
	}
	
}