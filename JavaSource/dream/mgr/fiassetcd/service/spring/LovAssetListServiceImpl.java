package dream.mgr.fiassetcd.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.mgr.fiassetcd.LovAssetListDAO;
import dream.mgr.fiassetcd.dto.LovAssetListDTO;
import dream.mgr.fiassetcd.form.LovAssetListForm;
import dream.mgr.fiassetcd.service.LovAssetListService;

/**
 * ÀÚ»êÆË¾÷ ServiceImpl
 * @author  kim21017
 * @version $Id: LovAssetListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovAssetListServiceTarget"
 * @spring.txbn id="lovAssetListService"
 * @spring.property name="lovAssetListDAO" ref="lovAssetListDAO"
 */
public class LovAssetListServiceImpl implements LovAssetListService
{
    /** ÀÚ»êÆË¾÷ DAO */
    private LovAssetListDAO lovAssetListDAO = null;

    public LovAssetListDAO getLovAssetListDAO() {
		return lovAssetListDAO;
	}

	public void setLovAssetListDAO(LovAssetListDAO lovAssetListDAO) {
		this.lovAssetListDAO = lovAssetListDAO;
	}

	public List findAssetList(LovAssetListDTO lovAssetListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovAssetListDAO.findAssetList(lovAssetListDTO, loginUser);
        
        return resultList;
    }

	@Override
	public List findAssetAcList(LovAssetListDTO lovAssetListDTO, User user, LovAssetListForm lovAssetListForm) {
		String jsonParam    = lovAssetListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovAssetListDAO.findAssetAcList(lovAssetListDTO, user ,conditionMap);
	}

	@Override
	public String findTotalCount(LovAssetListDTO lovAssetListDTO, User user, LovAssetListForm lovAssetListForm)
	{
		String jsonParam    = lovAssetListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
		return lovAssetListDAO.findTotalCount(lovAssetListDTO, user, conditionMap);
	}
}