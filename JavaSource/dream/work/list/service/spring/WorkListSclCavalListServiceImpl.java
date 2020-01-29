package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.WorkListSclCavalListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListSclCavalListDTO;
import dream.work.list.service.WorkListSclCavalListService;

/**
 * 작업상세  - 검교정(저울) - 측정값목록
 * @author kim21017
 * @version $Id: WorkListSclCavalListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListSclCavalListServiceTarget"
 * @spring.txbn id="workListSclCavalListService"
 * @spring.property name="workListSclCavalListDAO" ref="workListSclCavalListDAO"
 */
public class WorkListSclCavalListServiceImpl implements WorkListSclCavalListService
{
    private WorkListSclCavalListDAO workListSclCavalListDAO = null;

    public WorkListSclCavalListDAO getWorkListSclCavalListDAO() {
		return workListSclCavalListDAO;
	}

	public void setWorkListSclCavalListDAO(WorkListSclCavalListDAO workListSclCavalListDAO) {
		this.workListSclCavalListDAO = workListSclCavalListDAO;
	}

	public WorkListSclCavalListDTO findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User loginUser)
    {      
        return workListSclCavalListDAO.findCavalList(maWoResultMstrCommonDTO, loginUser);
    }
	
	public int mergeCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListSclCavalListDTO workListSclCavalListDTO, User loginUser) throws Exception{
		int result = 0;
        result = result + workListSclCavalListDAO.mergeCavalList(maWoResultMstrCommonDTO, workListSclCavalListDTO, loginUser);
		return result;
	}
}

