package dream.asset.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.asset.list.dao.LovEqToolAcListDAO;
import dream.asset.list.dto.LovEqToolAcListDTO;
import dream.asset.list.form.LovEqToolAcListForm;
import dream.asset.list.service.LovEqToolAcListService;

/**
 * LOV - List Service implements
 * @author youngjoo38
 * @version $Id: LovEqToolAcListServiceImpl.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="lovEqToolAcListServiceTarget"
 * @spring.txbn id="lovEqToolAcListService"
 * @spring.property name="lovEqToolAcListDAO" ref="lovEqToolAcListDAO"
 */
public class LovEqToolAcListServiceImpl implements LovEqToolAcListService
{
	private LovEqToolAcListDAO lovEqToolAcListDAO = null;

	public List findList(LovEqToolAcListForm lovEqToolAcListForm, User user) throws Exception
    {      
		LovEqToolAcListDTO lovEqToolAcListDTO = lovEqToolAcListForm.getLovEqToolAcListDTO();
		
		String code         = lovEqToolAcListForm.getCode();    //Search Value
        String keyCode      = lovEqToolAcListForm.getKeyCode();  //Search Column
        String codeType     = lovEqToolAcListForm.getCodeType(); //Table
        String jsonParam    = lovEqToolAcListForm.getParam();  //Condition
        String jaonCol      = lovEqToolAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return lovEqToolAcListDAO.findList(lovEqToolAcListDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(LovEqToolAcListForm lovEqToolAcListForm, User user) throws Exception
    {      
		LovEqToolAcListDTO lovEqToolAcListDTO = lovEqToolAcListForm.getLovEqToolAcListDTO();
		
		String code         = lovEqToolAcListForm.getCode();    //Search Value
        String keyCode      = lovEqToolAcListForm.getKeyCode();  //Search Column
        String codeType     = lovEqToolAcListForm.getCodeType(); //Table
        String jsonParam    = lovEqToolAcListForm.getParam();  //Condition
        String jaonCol      = lovEqToolAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return lovEqToolAcListDAO.findTotalCount(lovEqToolAcListDTO,user, columnMap ,conditionMap);
    }

	public LovEqToolAcListDAO getLovEqToolAcListDAO() {
		return lovEqToolAcListDAO;
	}

	public void setLovEqToolAcListDAO(LovEqToolAcListDAO lovEqToolAcListDAO) {
		this.lovEqToolAcListDAO = lovEqToolAcListDAO;
	}
	
}

