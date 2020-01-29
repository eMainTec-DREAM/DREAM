package dream.mgr.usrrpt.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.mgr.usrrpt.dao.LovUsrRptTabAcListDAO;
import dream.mgr.usrrpt.dto.LovUsrRptTabAcListDTO;
import dream.mgr.usrrpt.form.LovUsrRptTabAcListForm;
import dream.mgr.usrrpt.service.LovUsrRptTabAcListService;

/**
 * LOV - List Service implements
 * @author youngjoo38
 * @version $Id: LovUsrRptTabAcListServiceImpl.java,v 1.0 2017/09/12 16:12:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="lovUsrRptTabAcListServiceTarget"
 * @spring.txbn id="lovUsrRptTabAcListService"
 * @spring.property name="lovUsrRptTabAcListDAO" ref="lovUsrRptTabAcListDAO"
 */
public class LovUsrRptTabAcListServiceImpl implements LovUsrRptTabAcListService
{
	private LovUsrRptTabAcListDAO lovUsrRptTabAcListDAO = null;

	public List findList(LovUsrRptTabAcListForm lovUsrRptTabAcListForm, User user) throws Exception
    {      
		LovUsrRptTabAcListDTO lovUsrRptTabAcListDTO = lovUsrRptTabAcListForm.getLovUsrRptTabAcListDTO();
		
		String code         = lovUsrRptTabAcListForm.getCode();    //Search Value
        String keyCode      = lovUsrRptTabAcListForm.getKeyCode();  //Search Column
        String codeType     = lovUsrRptTabAcListForm.getCodeType(); //Table
        String jsonParam    = lovUsrRptTabAcListForm.getParam();  //Condition
        String jaonCol      = lovUsrRptTabAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return lovUsrRptTabAcListDAO.findList(lovUsrRptTabAcListDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(LovUsrRptTabAcListForm lovUsrRptTabAcListForm, User user) throws Exception
    {      
		LovUsrRptTabAcListDTO lovUsrRptTabAcListDTO = lovUsrRptTabAcListForm.getLovUsrRptTabAcListDTO();
		
		String code         = lovUsrRptTabAcListForm.getCode();    //Search Value
        String keyCode      = lovUsrRptTabAcListForm.getKeyCode();  //Search Column
        String codeType     = lovUsrRptTabAcListForm.getCodeType(); //Table
        String jsonParam    = lovUsrRptTabAcListForm.getParam();  //Condition
        String jaonCol      = lovUsrRptTabAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return lovUsrRptTabAcListDAO.findTotalCount(lovUsrRptTabAcListDTO,user, columnMap ,conditionMap);
    }

	public LovUsrRptTabAcListDAO getLovUsrRptTabAcListDAO() {
		return lovUsrRptTabAcListDAO;
	}

	public void setLovUsrRptTabAcListDAO(LovUsrRptTabAcListDAO lovUsrRptTabAcListDAO) {
		this.lovUsrRptTabAcListDAO = lovUsrRptTabAcListDAO;
	}
	
}

