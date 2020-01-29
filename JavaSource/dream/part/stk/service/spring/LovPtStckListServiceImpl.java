package dream.part.stk.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.part.stk.dao.LovPtStckListDAO;
import dream.part.stk.dto.LovPtStckListDTO;
import dream.part.stk.form.LovPtStckListForm;
import dream.part.stk.service.LovPtStckListService;

/**
 * 營堅で機 ServiceImpl
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovPtStckListServiceTarget"
 * @spring.txbn id="lovPtStckListService"
 * @spring.property name="lovPtStckListDAO" ref="lovPtStckListDAO"
 */
public class LovPtStckListServiceImpl implements LovPtStckListService
{
    /** 營堅で機 DAO */
    private LovPtStckListDAO lovPtStckListDAO = null;

    public LovPtStckListDAO getLovPtStckListDAO() {
		return lovPtStckListDAO;
	}

	public void setLovPtStckListDAO(LovPtStckListDAO lovPtStckListDAO) {
		this.lovPtStckListDAO = lovPtStckListDAO;
	}
	
	public List findAcList(LovPtStckListForm lovPtStckListForm, User loginUser)
    {
		LovPtStckListDTO lovPtStckListDTO = lovPtStckListForm.getLovPtStckListDTO();
		
		String code         = lovPtStckListForm.getCode();    //Search Value
        String keyCode      = lovPtStckListForm.getKeyCode();  //Search Column
        String codeType     = lovPtStckListForm.getCodeType(); //Table
        String jsonParam    = lovPtStckListForm.getParam();  //Condition
        String jaonCol      = lovPtStckListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

        return lovPtStckListDAO.findAcList(lovPtStckListDTO, loginUser, columnMap ,conditionMap);
    }
	
	public String findTotalCount(LovPtStckListForm lovPtStckListForm, User user)  throws Exception
    {
	    LovPtStckListDTO lovPtStckListDTO = lovPtStckListForm.getLovPtStckListDTO();
	    
	    String code         = lovPtStckListForm.getCode();    //Search Value
        String keyCode      = lovPtStckListForm.getKeyCode();  //Search Column
        String codeType     = lovPtStckListForm.getCodeType(); //Table
        String jsonParam    = lovPtStckListForm.getParam();  //Condition
        String jaonCol      = lovPtStckListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
	    
        return lovPtStckListDAO.findTotalCount(lovPtStckListDTO, user, columnMap ,conditionMap);
    }
}