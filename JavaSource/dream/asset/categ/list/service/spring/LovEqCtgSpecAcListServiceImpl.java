package dream.asset.categ.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.asset.categ.list.dao.LovEqCtgSpecAcListDAO;
import dream.asset.categ.list.dto.LovEqCtgSpecAcListDTO;
import dream.asset.categ.list.form.LovEqCtgSpecAcListForm;
import dream.asset.categ.list.service.LovEqCtgSpecAcListService;

/**
 * 설비종류별제원 ServiceImpl
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovEqCtgSpecAcListServiceTarget"
 * @spring.txbn id="lovEqCtgSpecAcListService"
 * @spring.property name="lovEqCtgSpecAcListDAO" ref="lovEqCtgSpecAcListDAO"
 */
public class LovEqCtgSpecAcListServiceImpl implements LovEqCtgSpecAcListService
{
    /** 설비종류별제원 팝업 DAO */
    private LovEqCtgSpecAcListDAO lovEqCtgSpecAcListDAO = null;

	public LovEqCtgSpecAcListDAO getLovEqCtgSpecAcListDAO()
    {
        return lovEqCtgSpecAcListDAO;
    }

    public void setLovEqCtgSpecAcListDAO(LovEqCtgSpecAcListDAO lovEqCtgSpecAcListDAO)
    {
        this.lovEqCtgSpecAcListDAO = lovEqCtgSpecAcListDAO;
    }

    public List findEqCtgSpecAcList(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovEqCtgSpecAcListDAO.findEqCtgSpecAcList(lovEqCtgSpecAcListDTO,loginUser);
        
        return resultList;
    }

	@Override
	public List findEqCtgSpecAcAcList(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User user, LovEqCtgSpecAcListForm lovEqCtgSpecAcListForm) throws Exception {
		String jsonParam    = lovEqCtgSpecAcListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovEqCtgSpecAcListDAO.findEqCtgSpecAcAcList(lovEqCtgSpecAcListDTO, user ,conditionMap);
	}

	@Override
	public String findTotalCount(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User loginUser, LovEqCtgSpecAcListForm lovEqCtgSpecAcListForm) throws Exception 
	{
		String jsonParam    = lovEqCtgSpecAcListForm.getParam();  //Condition
		
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
		
		return lovEqCtgSpecAcListDAO.findTotalCount(lovEqCtgSpecAcListDTO, loginUser ,conditionMap);
	}
}