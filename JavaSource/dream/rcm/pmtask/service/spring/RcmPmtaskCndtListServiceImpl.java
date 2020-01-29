package dream.rcm.pmtask.service.spring;

import java.util.List;

import com.fins.org.json.JSONObject;

import common.bean.User;
import dream.rcm.pmtask.dao.RcmPmtaskCndtListDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;
import dream.rcm.pmtask.service.RcmPmtaskCndtListService;


/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id: RcmPmtaskCndtListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmPmtaskCndtListServiceTarget"
 * @spring.txbn id="rcmPmtaskCndtListService"
 * @spring.property name="rcmPmtaskCndtListDAO" ref="rcmPmtaskCndtListDAO"
 */
public class RcmPmtaskCndtListServiceImpl implements RcmPmtaskCndtListService
{
    private RcmPmtaskCndtListDAO rcmPmtaskCndtListDAO = null;

    public RcmPmtaskCndtListDAO getRcmPmtaskCndtListDAO() {
		return rcmPmtaskCndtListDAO;
	}
	public void setRcmPmtaskCndtListDAO(RcmPmtaskCndtListDAO rcmPmtaskCndtListDAO) {
		this.rcmPmtaskCndtListDAO = rcmPmtaskCndtListDAO;
	}
	
	public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO)
    {      
        return rcmPmtaskCndtListDAO.findList(rcmPmtaskCommonDTO, rcmPmtaskCndtListDTO);
    }

	@Override
	public int deleteList(String[] deleteRows) {
		int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + rcmPmtaskCndtListDAO.deleteList(deleteRows[i] );
        }
        
        return result;
	}
	@Override
	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO,
			User user) {
		 return rcmPmtaskCndtListDAO.findTotalCount(rcmPmtaskCommonDTO, rcmPmtaskCndtListDTO, user);
	}
}

