package dream.rcm.pmtask.service.spring;

import java.util.List;

import com.fins.org.json.JSONObject;

import common.bean.User;
import dream.rcm.pmtask.dao.RcmPmtaskMapListDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;
import dream.rcm.pmtask.service.RcmPmtaskMapListService;


/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id: RcmPmtaskMapListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmPmtaskMapListServiceTarget"
 * @spring.txbn id="rcmPmtaskMapListService"
 * @spring.property name="rcmPmtaskMapListDAO" ref="rcmPmtaskMapListDAO"
 */
public class RcmPmtaskMapListServiceImpl implements RcmPmtaskMapListService
{
    private RcmPmtaskMapListDAO rcmPmtaskMapListDAO = null;

    public RcmPmtaskMapListDAO getRcmPmtaskMapListDAO() {
		return rcmPmtaskMapListDAO;
	}
	public void setRcmPmtaskMapListDAO(RcmPmtaskMapListDAO rcmPmtaskMapListDAO) {
		this.rcmPmtaskMapListDAO = rcmPmtaskMapListDAO;
	}
	
	public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO)
    {      
        return rcmPmtaskMapListDAO.findList(rcmPmtaskCommonDTO, rcmPmtaskMapListDTO);
    }
	

	public List findQuestionList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO) {
		// TODO Auto-generated method stub
		return rcmPmtaskMapListDAO.findQuestionList(rcmPmtaskCommonDTO, rcmPmtaskMapListDTO);
	}
	@Override
	public void insertMapList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO) {
		// TODO Auto-generated method stub
		String jsonMap = rcmPmtaskCommonDTO.getPmtaskmapRsltVal();
		
		JSONObject json = new JSONObject(jsonMap);

		rcmPmtaskMapListDAO.insertMapList(rcmPmtaskCommonDTO, rcmPmtaskMapListDTO, json);
	}
	@Override
	public void deleteList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO) 
	{
		rcmPmtaskMapListDAO.deleteList(rcmPmtaskCommonDTO, rcmPmtaskMapListDTO);
	}
	@Override
	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO, User user) {
        return rcmPmtaskMapListDAO.findTotalCount(rcmPmtaskCommonDTO, rcmPmtaskMapListDTO, user);
        
	}
}

