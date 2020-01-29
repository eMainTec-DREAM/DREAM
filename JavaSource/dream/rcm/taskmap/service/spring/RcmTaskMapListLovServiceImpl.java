package dream.rcm.taskmap.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.rcm.taskmap.dao.RcmTaskMapListLovDAO;
import dream.rcm.taskmap.dto.RcmTaskMapListLovDTO;
import dream.rcm.taskmap.form.RcmTaskMapListLovForm;
import dream.rcm.taskmap.service.RcmTaskMapListLovService;

/**
 * Task Map ServiceImpl
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="rcmTaskMapListLovServiceTarget"
 * @spring.txbn id="rcmTaskMapListLovService"
 * @spring.property name="rcmTaskMapListLovDAO" ref="rcmTaskMapListLovDAO"
 */
public class RcmTaskMapListLovServiceImpl implements RcmTaskMapListLovService
{
    /** Task Map ÆË¾÷ DAO */
    private RcmTaskMapListLovDAO rcmTaskMapListLovDAO = null;

	public RcmTaskMapListLovDAO getRcmTaskMapListLovDAO()
    {
        return rcmTaskMapListLovDAO;
    }
	
    public void setRcmTaskMapListLovDAO(RcmTaskMapListLovDAO rcmTaskMapListLovDAO)
    {
        this.rcmTaskMapListLovDAO = rcmTaskMapListLovDAO;
    }


    @Override
    public List findTaskMapAcList(RcmTaskMapListLovDTO rcmTaskMapListLovDTO,
            User loginUser, RcmTaskMapListLovForm rcmTaskMapListLovForm)
    {

        String jsonParam    = rcmTaskMapListLovForm.getParam();  //Condition



        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);


        return rcmTaskMapListLovDAO.findTaskMapAcList(rcmTaskMapListLovDTO, loginUser, conditionMap);

    }
}