package dream.work.let.categ.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.let.categ.dao.WorkLetCategEtcListDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategEtcListDTO;
import dream.work.let.categ.service.WorkLetCategEtcListService;

/**
 * 안전작업유형 보완사항 list Page - List Service implements
 * @author euna0207
 * @version $Id: WorkLetCategEtcListServiceImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @spring.bean id="workLetCategEtcListServiceTarget"
 * @spring.txbn id="workLetCategEtcListService"
 * @spring.property name="workLetCategEtcListDAO" ref="workLetCategEtcListDAO"
 */
public class WorkLetCategEtcListServiceImpl implements WorkLetCategEtcListService
{
	private WorkLetCategEtcListDAO workLetCategEtcListDAO = null;

	public List findList(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcListDTO workLetCategEtcListDTO, User user) throws Exception
    {      
        return workLetCategEtcListDAO.findList(workLetCategCommonDTO, workLetCategEtcListDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workLetCategEtcListDAO.deleteList(id, user);
            }
        return result;
    }


	public WorkLetCategEtcListDAO getWorkLetCategEtcListDAO() {
		return workLetCategEtcListDAO;
	}

	public void setWorkLetCategEtcListDAO(WorkLetCategEtcListDAO workLetCategEtcListDAO) {
		this.workLetCategEtcListDAO = workLetCategEtcListDAO;
	}

	public String findTotalCount(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcListDTO workLetCategEtcListDTO,User user) throws Exception
    {
        return workLetCategEtcListDAO.findTotalCount(workLetCategCommonDTO, workLetCategEtcListDTO, user);
    }
}

