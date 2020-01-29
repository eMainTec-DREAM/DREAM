package dream.work.pm.list.service.spring;

import dream.work.pm.list.dao.WorkPmListSchdDetailDAO;
import dream.work.pm.list.dto.WorkPmListSchdDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.service.WorkPmListSchdDetailService;

/**
 * 예방작업 일자 상세
 * @author kim21017
 * @version $Id: WorkPmListSchdDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmListSchdDetailServiceTarget"
 * @spring.txbn id="workPmListSchdDetailService"
 * @spring.property name="workPmListSchdDetailDAO" ref="workPmListSchdDetailDAO"
 */
public class WorkPmListSchdDetailServiceImpl implements WorkPmListSchdDetailService
{
    private WorkPmListSchdDetailDAO workPmListSchdDetailDAO = null;
    
    public WorkPmListSchdDetailDAO getWorkPmListSchdDetailDAO() {
		return workPmListSchdDetailDAO;
	}

	public void setWorkPmListSchdDetailDAO(WorkPmListSchdDetailDAO workPmListSchdDetailDAO) {
		this.workPmListSchdDetailDAO = workPmListSchdDetailDAO;
	}

	public WorkPmListSchdDetailDTO findDetail(String pmId, String pmEventSchedId, String compNo)throws Exception
    {
        return workPmListSchdDetailDAO.findDetail(pmId, pmEventSchedId, compNo);
    }
    
	public int updateDetail(WorkPmListSchdDetailDTO workPmListSchdDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return workPmListSchdDetailDAO.updateDetail(workPmListSchdDetailDTO, maPmMstrCommonDTO);
    }
	public int insertDetail(WorkPmListSchdDetailDTO workPmListSchdDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return workPmListSchdDetailDAO.insertDetail( workPmListSchdDetailDTO, maPmMstrCommonDTO);
    }
}
