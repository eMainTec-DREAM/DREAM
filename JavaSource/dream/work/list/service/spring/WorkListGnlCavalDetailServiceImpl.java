package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.WorkListGnlCavalDetailDAO;
import dream.work.list.dto.WorkListGnlCavalDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.service.WorkListGnlCavalDetailService;

/**
 * 작업상세  - 검교정 - 측정값
 * @author kim2107
 * @version $Id: WorkListGnlCavalDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListGnlCavalDetailServiceTarget"
 * @spring.txbn id="workListGnlCavalDetailService"
 * @spring.property name="workListGnlCavalDetailDAO" ref="workListGnlCavalDetailDAO"
 */
public class WorkListGnlCavalDetailServiceImpl implements WorkListGnlCavalDetailService
{
    private WorkListGnlCavalDetailDAO workListGnlCavalDetailDAO = null;
    
    public WorkListGnlCavalDetailDAO getWorkListGnlCavalDetailDAO() {
		return workListGnlCavalDetailDAO;
	}

	public void setWorkListGnlCavalDetailDAO(WorkListGnlCavalDetailDAO workListGnlCavalDetailDAO) {
		this.workListGnlCavalDetailDAO = workListGnlCavalDetailDAO;
	}

	public WorkListGnlCavalDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        return workListGnlCavalDetailDAO.findDetail(maWoResultMstrCommonDTO, user);
    }
    
	public int updateDetail(WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return workListGnlCavalDetailDAO.updateDetail(workListGnlCavalDetailDTO, maWoResultMstrCommonDTO);
    }
	public int insertDetail(WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return workListGnlCavalDetailDAO.insertDetail( workListGnlCavalDetailDTO, maWoResultMstrCommonDTO);
    }
}
