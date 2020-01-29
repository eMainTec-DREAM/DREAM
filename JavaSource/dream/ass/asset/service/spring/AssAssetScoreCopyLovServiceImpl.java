package dream.ass.asset.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.ass.asset.dao.AssAssetScoreCopyLovDAO;
import dream.ass.asset.dto.AssAssetScoreCopyLovDTO;
import dream.ass.asset.form.AssAssetScoreCopyLovForm;
import dream.ass.asset.service.AssAssetScoreCopyLovService;

/**
 * 평가결과복사 LOV ServiceImpl
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 *
 * @spring.bean id="assAssetScoreCopyLovServiceTarget"
 * @spring.txbn id="assAssetScoreCopyLovService"
 * @spring.property name="assAssetScoreCopyLovDAO" ref="assAssetScoreCopyLovDAO"
 */
public class AssAssetScoreCopyLovServiceImpl implements AssAssetScoreCopyLovService
{
    /** 평가결과복사 LOV DAO */
    private AssAssetScoreCopyLovDAO assAssetScoreCopyLovDAO = null;

    public AssAssetScoreCopyLovDAO getAssAssetScoreCopyLovDAO() {
		return assAssetScoreCopyLovDAO;
	}

	public void setAssAssetScoreCopyLovDAO(AssAssetScoreCopyLovDAO assAssetScoreCopyLovDAO) {
		this.assAssetScoreCopyLovDAO = assAssetScoreCopyLovDAO;
	}

	@Override
	public List findAssList(AssAssetScoreCopyLovForm assAssetScoreCopyLovForm, User user) throws Exception {
		AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO = assAssetScoreCopyLovForm.getAssAssetScoreCopyLovDTO();
		
		String code         = assAssetScoreCopyLovForm.getCode();    //Search Value
        String keyCode      = assAssetScoreCopyLovForm.getKeyCode();  //Search Column
        String codeType     = assAssetScoreCopyLovForm.getCodeType(); //Table
        String jsonParam    = assAssetScoreCopyLovForm.getParam();  //Condition
        String jaonCol      = assAssetScoreCopyLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
		
		return assAssetScoreCopyLovDAO.findAssList(assAssetScoreCopyLovDTO, user, columnMap ,conditionMap);
	}

	@Override
	public String findTotalCount(AssAssetScoreCopyLovForm assAssetScoreCopyLovForm, User user) throws Exception {
		AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO = assAssetScoreCopyLovForm.getAssAssetScoreCopyLovDTO();
		
		String code         = assAssetScoreCopyLovForm.getCode();    //Search Value
        String keyCode      = assAssetScoreCopyLovForm.getKeyCode();  //Search Column
        String codeType     = assAssetScoreCopyLovForm.getCodeType(); //Table
        String jsonParam    = assAssetScoreCopyLovForm.getParam();  //Condition
        String jaonCol      = assAssetScoreCopyLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
		return assAssetScoreCopyLovDAO.findTotalCount(assAssetScoreCopyLovDTO, user, columnMap, conditionMap);
	}
	
	@Override
	public List findAssScoreList(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user)
	{
        return assAssetScoreCopyLovDAO.findAssScoreList(assAssetScoreCopyLovDTO, user);
	}

}