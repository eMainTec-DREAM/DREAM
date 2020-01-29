package dream.work.cal.pminsappr.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.cal.pminsappr.dao.WorkCalPmInsApprListDAO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprListService;

/**
 * øππÊ¡°∞À∞Ë»πΩ¬¿Œ - ∏Ò∑œ serviceimpl
 * @author kim21017
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="workCalPmInsApprListServiceTarget"
 * @spring.txbn id="workCalPmInsApprListService"
 * @spring.property name="workCalPmInsApprListDAO" ref="workCalPmInsApprListDAO"
 */
public class WorkCalPmInsApprListServiceImpl implements WorkCalPmInsApprListService
{
    private WorkCalPmInsApprListDAO workCalPmInsApprListDAO = null;
    
	public WorkCalPmInsApprListDAO getWorkCalPmInsApprListDAO() {
		return workCalPmInsApprListDAO;
	}
	public void setWorkCalPmInsApprListDAO(WorkCalPmInsApprListDAO workCalPmInsApprListDAO) {
		this.workCalPmInsApprListDAO = workCalPmInsApprListDAO;
	}
	public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user)
    {      
        return workCalPmInsApprListDAO.findList(workCalPmInsApprCommonDTO,user);
    }
	public int deleteList(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workCalPmInsApprListDAO.deleteList(id,user);
            }
        return result;
    }
	
    public String findTotalCount( WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user)
    {
        return workCalPmInsApprListDAO.findTotalCount(workCalPmInsApprCommonDTO,user);
    }
}

