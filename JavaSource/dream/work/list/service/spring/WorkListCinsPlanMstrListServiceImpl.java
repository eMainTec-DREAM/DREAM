package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dao.WorkListCinsPlanMstrListDAO;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;
import dream.work.list.service.WorkListCinsPlanMstrListService;

/**
 * WorkListCinsPlanMstr Page - List Service implements
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workListCinsPlanMstrListServiceTarget"
 * @spring.txbn id="workListCinsPlanMstrListService"
 * @spring.property name="workListCinsPlanMstrListDAO" ref="workListCinsPlanMstrListDAO"
 */
public class WorkListCinsPlanMstrListServiceImpl implements WorkListCinsPlanMstrListService
{
    private WorkListCinsPlanMstrListDAO workListCinsPlanMstrListDAO = null;

    public List findList(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, User user) throws Exception
    {      
        return workListCinsPlanMstrListDAO.findList(workListCinsPlanMstrCommonDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;
        String pSchedStatus = "";

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            	pSchedStatus =  workListCinsPlanMstrListDAO.checkSched(id, user);
            	
            	if("S".equals(pSchedStatus)){
            		result = result + workListCinsPlanMstrListDAO.updateDeleteTagSched(id, user);
            	}else if("P".equals(pSchedStatus)){
            		result = result + workListCinsPlanMstrListDAO.deleteSched(id, user);
            	}
            	
            }
        return result;
    }

    public WorkListCinsPlanMstrListDAO getWorkListCinsPlanMstrListDAO() {
        return workListCinsPlanMstrListDAO;
    }

    public void setWorkListCinsPlanMstrListDAO(WorkListCinsPlanMstrListDAO workListCinsPlanMstrListDAO) {
        this.workListCinsPlanMstrListDAO = workListCinsPlanMstrListDAO;
    }    
    
    public String findTotalCount(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO,User user)  throws Exception
    {
        return workListCinsPlanMstrListDAO.findTotalCount(workListCinsPlanMstrCommonDTO, user);
    }
}
