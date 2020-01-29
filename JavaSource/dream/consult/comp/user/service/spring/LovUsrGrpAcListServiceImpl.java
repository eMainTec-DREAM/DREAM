package dream.consult.comp.user.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.comp.user.dao.LovUsrGrpAcListDAO;
import dream.consult.comp.user.dto.LovUsrGrpAcListDTO;
import dream.consult.comp.user.form.LovUsrGrpAcListForm;
import dream.consult.comp.user.service.LovUsrGrpAcListService;

/**
 * LOV - List Service implements
 * @author youngjoo38
 * @version $Id: LovUsrGrpAcListServiceImpl.java,v 1.0 2017/09/12 16:12:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="lovUsrGrpAcListServiceTarget"
 * @spring.txbn id="lovUsrGrpAcListService"
 * @spring.property name="lovUsrGrpAcListDAO" ref="lovUsrGrpAcListDAO"
 */
public class LovUsrGrpAcListServiceImpl implements LovUsrGrpAcListService
{
	private LovUsrGrpAcListDAO lovUsrGrpAcListDAO = null;

	public List findList(LovUsrGrpAcListForm lovUsrGrpAcListForm, User user) throws Exception
    {      
		LovUsrGrpAcListDTO lovUsrGrpAcListDTO = lovUsrGrpAcListForm.getLovUsrGrpAcListDTO();
		
		String code         = lovUsrGrpAcListForm.getCode();    //Search Value
        String keyCode      = lovUsrGrpAcListForm.getKeyCode();  //Search Column
        String codeType     = lovUsrGrpAcListForm.getCodeType(); //Table
        String jsonParam    = lovUsrGrpAcListForm.getParam();  //Condition
        String jaonCol      = lovUsrGrpAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return lovUsrGrpAcListDAO.findList(lovUsrGrpAcListDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(LovUsrGrpAcListForm lovUsrGrpAcListForm, User user) throws Exception
    {      
		LovUsrGrpAcListDTO lovUsrGrpAcListDTO = lovUsrGrpAcListForm.getLovUsrGrpAcListDTO();
		
		String code         = lovUsrGrpAcListForm.getCode();    //Search Value
        String keyCode      = lovUsrGrpAcListForm.getKeyCode();  //Search Column
        String codeType     = lovUsrGrpAcListForm.getCodeType(); //Table
        String jsonParam    = lovUsrGrpAcListForm.getParam();  //Condition
        String jaonCol      = lovUsrGrpAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return lovUsrGrpAcListDAO.findTotalCount(lovUsrGrpAcListDTO,user, columnMap ,conditionMap);
    }

	public LovUsrGrpAcListDAO getLovUsrGrpAcListDAO() {
		return lovUsrGrpAcListDAO;
	}

	public void setLovUsrGrpAcListDAO(LovUsrGrpAcListDAO lovUsrGrpAcListDAO) {
		this.lovUsrGrpAcListDAO = lovUsrGrpAcListDAO;
	}
	
}

