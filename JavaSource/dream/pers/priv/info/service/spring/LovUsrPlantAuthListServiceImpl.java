package dream.pers.priv.info.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.pers.priv.info.dao.LovUsrPlantAuthListDAO;
import dream.pers.priv.info.dto.LovUsrPlantAuthListDTO;
import dream.pers.priv.info.form.LovUsrPlantAuthListForm;
import dream.pers.priv.info.service.LovUsrPlantAuthListService;

/**
 * 공장권한 ServiceImpl
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @spring.bean id="lovUsrPlantAuthListServiceTarget"
 * @spring.txbn id="lovUsrPlantAuthListService"
 * @spring.property name="lovUsrPlantAuthListDAO" ref="lovUsrPlantAuthListDAO"
 */
public class LovUsrPlantAuthListServiceImpl implements LovUsrPlantAuthListService
{
    /**  DAO */
    private LovUsrPlantAuthListDAO lovUsrPlantAuthListDAO = null;

    public LovUsrPlantAuthListDAO getLovUsrPlantAuthListDAO() {
		return lovUsrPlantAuthListDAO;
	}

	public void setLovUsrPlantAuthListDAO(LovUsrPlantAuthListDAO lovUsrPlantAuthListDAO) {
		this.lovUsrPlantAuthListDAO = lovUsrPlantAuthListDAO;
	}

	public List findUsrPlantAuthList(LovUsrPlantAuthListDTO lovUsrPlantAuthListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovUsrPlantAuthListDAO.findUsrPlantAuthList(lovUsrPlantAuthListDTO, loginUser);
        
        return resultList;
    }
	public List findPlantAcList(LovUsrPlantAuthListForm lovUsrPlantAuthListForm, User loginUser)
    {
		LovUsrPlantAuthListDTO lovUsrPlantAuthListDTO = lovUsrPlantAuthListForm.getLovUsrPlantAuthListDTO();
		
		String code         = lovUsrPlantAuthListForm.getCode();    //Search Value
        String keyCode      = lovUsrPlantAuthListForm.getKeyCode();  //Search Column
        String codeType     = lovUsrPlantAuthListForm.getCodeType(); //Table
        String jsonParam    = lovUsrPlantAuthListForm.getParam();  //Condition
        String jaonCol      = lovUsrPlantAuthListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        List list = null;
		list = lovUsrPlantAuthListDAO.findPlantAcList(lovUsrPlantAuthListDTO, loginUser, columnMap ,conditionMap);
		
        return list;
    }
}