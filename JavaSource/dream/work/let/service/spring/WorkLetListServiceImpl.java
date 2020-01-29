package dream.work.let.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.let.dao.WorkLetListDAO;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.service.WorkLetListService;

/**
 * 안전작업 - 목록 serviceimpl
 * @author syyang
 * @version $Id: WorkLetListServiceImpl.java,v 1.0 2015/12/02 09:12:51 syyang Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetListServiceTarget"
 * @spring.txbn id="workLetListService"
 * @spring.property name="workLetListDAO" ref="workLetListDAO"
 */
public class WorkLetListServiceImpl implements WorkLetListService
{
    private WorkLetListDAO workLetListDAO = null;

    
    public WorkLetListDAO getWorkLetListDAO() {
		return workLetListDAO;
	}
	public void setWorkLetListDAO(WorkLetListDAO workLetListDAO) {
		this.workLetListDAO = workLetListDAO;
	}

	
	public List findWoLetList(WorkLetCommonDTO workLetCommonDTO, User user)
    {      
        return workLetListDAO.findWoLetList(workLetCommonDTO,user);
    }
	
	public int deleteWoLet(String[] deleteRows, User user) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            	//삭제한 데이타는 삭제형태로 변경처리 함.
                result = result + workLetListDAO.deleteWoLet(id,user);
            }
        
        return result;
    }
	

    @Override
    public String findTotalCount( WorkLetCommonDTO workLetCommonDTO, User user, String woType)
    {
        return workLetListDAO.findTotalCount(workLetCommonDTO,user,woType);
    }

    
}

