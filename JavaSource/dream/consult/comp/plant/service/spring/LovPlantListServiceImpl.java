package dream.consult.comp.plant.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.comp.plant.dao.LovPlantListDAO;
import dream.consult.comp.plant.dto.LovPlantListDTO;
import dream.consult.comp.plant.form.LovPlantListForm;
import dream.consult.comp.plant.service.LovPlantListService;

/**
 * ÇÃ·£Æ® ServiceImpl
 * @author  kim21017
 * @version $Id: LovPlantListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovPlantListServiceTarget"
 * @spring.txbn id="lovPlantListService"
 * @spring.property name="lovPlantListDAO" ref="lovPlantListDAO"
 */
public class LovPlantListServiceImpl implements LovPlantListService
{
    /**  DAO */
    private LovPlantListDAO lovPlantListDAO = null;

    public LovPlantListDAO getLovPlantListDAO() {
		return lovPlantListDAO;
	}

	public void setLovPlantListDAO(LovPlantListDAO lovPlantListDAO) {
		this.lovPlantListDAO = lovPlantListDAO;
	}

	public List findPlantList(LovPlantListDTO lovPlantListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovPlantListDAO.findPlantList(lovPlantListDTO, loginUser);
        
        return resultList;
    }
	public List findPlantAcList(LovPlantListForm lovPlantListForm, User loginUser)
    {
		LovPlantListDTO lovPlantListDTO = lovPlantListForm.getLovPlantListDTO();
		
		String code         = lovPlantListForm.getCode();    //Search Value
        String keyCode      = lovPlantListForm.getKeyCode();  //Search Column
        String codeType     = lovPlantListForm.getCodeType(); //Table
        String jsonParam    = lovPlantListForm.getParam();  //Condition
        String jaonCol      = lovPlantListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        List list = null;
		list = lovPlantListDAO.findPlantAcList(lovPlantListDTO, loginUser, columnMap ,conditionMap);
		
        return list;
    }
}