package dream.work.pm.list.service.spring;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmListShiftDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListShiftDetailDTO;
import dream.work.pm.list.service.WorkPmListShiftDetailService;

/**
 * ±≥¥Î¡∂
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workPmListShiftDetailServiceTarget"
 * @spring.txbn id="workPmListShiftDetailService"
 * @spring.property name="workPmListShiftDetailDAO" ref="workPmListShiftDetailDAO"
 */
public class WorkPmListShiftDetailServiceImpl implements WorkPmListShiftDetailService
{
    private WorkPmListShiftDetailDAO workPmListShiftDetailDAO = null;
    
    public WorkPmListShiftDetailDAO getWorkPmListShiftDetailDAO() {
		return workPmListShiftDetailDAO;
	}

	public void setWorkPmListShiftDetailDAO(WorkPmListShiftDetailDAO workPmListShiftDetailDAO) {
		this.workPmListShiftDetailDAO = workPmListShiftDetailDAO;
	}

	public WorkPmListShiftDetailDTO findDetail(String pmId, String pmWrkShiftId, User loginUser)throws Exception
    {
        return workPmListShiftDetailDAO.findDetail(pmId, pmWrkShiftId, loginUser);
    }
    
	public int updateDetail(WorkPmListShiftDetailDTO workPmListShiftDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return workPmListShiftDetailDAO.updateDetail(workPmListShiftDetailDTO, maPmMstrCommonDTO);
    }
	public int insertDetail(WorkPmListShiftDetailDTO workPmListShiftDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return workPmListShiftDetailDAO.insertDetail( workPmListShiftDetailDTO, maPmMstrCommonDTO);
    }
}
