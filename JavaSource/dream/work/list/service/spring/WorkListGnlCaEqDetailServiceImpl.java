package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.WorkListGnlCaEqDetailDAO;
import dream.work.list.dto.WorkListGnlCaEqDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.service.WorkListGnlCaEqDetailService;

/**
 * 작업상세  - 검교정 - 표준기
 * @author kim2107
 * @version $Id: WorkListGnlCaEqDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListGnlCaEqDetailServiceTarget"
 * @spring.txbn id="workListGnlCaEqDetailService"
 * @spring.property name="workListGnlCaEqDetailDAO" ref="workListGnlCaEqDetailDAO"
 */
public class WorkListGnlCaEqDetailServiceImpl implements WorkListGnlCaEqDetailService
{
    private WorkListGnlCaEqDetailDAO workListGnlCaEqDetailDAO = null;
    
    public WorkListGnlCaEqDetailDAO getWorkListGnlCaEqDetailDAO() {
		return workListGnlCaEqDetailDAO;
	}

	public void setWorkListGnlCaEqDetailDAO(WorkListGnlCaEqDetailDAO workListGnlCaEqDetailDAO) {
		this.workListGnlCaEqDetailDAO = workListGnlCaEqDetailDAO;
	}

	public WorkListGnlCaEqDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        return workListGnlCaEqDetailDAO.findDetail(maWoResultMstrCommonDTO, user);
    }
    
	public int updateDetail(WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return workListGnlCaEqDetailDAO.updateDetail(workListGnlCaEqDetailDTO, maWoResultMstrCommonDTO);
    }
	public int insertDetail(WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return workListGnlCaEqDetailDAO.insertDetail( workListGnlCaEqDetailDTO, maWoResultMstrCommonDTO);
    }

    @Override
    public String checkDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO,User user) throws Exception
    {
        return workListGnlCaEqDetailDAO.checkDetail(maWoResultMstrCommonDTO,workListGnlCaEqDetailDTO,user);
    }
}
