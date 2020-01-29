package dream.invt.prc.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.invt.prc.dao.InvtPrcTpLovDAO;
import dream.invt.prc.dto.InvtPrcTpLovDTO;
import dream.invt.prc.form.InvtPrcTpLovForm;
import dream.invt.prc.service.InvtPrcTpLovService;

/**
 * 구매절차 ServiceImpl
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="invtPrcTpLovServiceTarget"
 * @spring.txbn id="invtPrcTpLovService"
 * @spring.property name="invtPrcTpLovDAO" ref="invtPrcTpLovDAO"
 */
public class InvtPrcTpLovServiceImpl implements InvtPrcTpLovService
{
    /** Task Map 팝업 DAO */
    private InvtPrcTpLovDAO invtPrcTpLovDAO = null;

	public InvtPrcTpLovDAO getInvtPrcTpLovDAO()
    {
        return invtPrcTpLovDAO;
    }
	
    public void setInvtPrcTpLovDAO(InvtPrcTpLovDAO invtPrcTpLovDAO)
    {
        this.invtPrcTpLovDAO = invtPrcTpLovDAO;
    }


    @Override
    public List findTaskMapAcList(InvtPrcTpLovDTO invtPrcTpLovDTO,
            User loginUser, InvtPrcTpLovForm invtPrcTpLovForm)
    {

        String jsonParam    = invtPrcTpLovForm.getParam();  //Condition



        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);


        return invtPrcTpLovDAO.findTaskMapAcList(invtPrcTpLovDTO, loginUser, conditionMap);

    }

	@Override
	public String findTotalCount(InvtPrcTpLovDTO invtPrcTpLovDTO, User user, InvtPrcTpLovForm invtPrcTpLovForm)
	{

        String jsonParam    = invtPrcTpLovForm.getParam();  //Condition
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
		return invtPrcTpLovDAO.findTotalCount(invtPrcTpLovDTO, user, conditionMap);
	}
}