package dream.work.pm.list.service.spring;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmPointUInsDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmPointUInsDetailDTO;
import dream.work.pm.list.service.WorkPmPointUInsDetailService;

/**
 * 사용량 항목 - Detail Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workPmPointUInsDetailServiceTarget"
 * @spring.txbn id="workPmPointUInsDetailService"
 * @spring.property name="workPmPointUInsDetailDAO" ref="workPmPointUInsDetailDAO"
 */
public class WorkPmPointUInsDetailServiceImpl implements WorkPmPointUInsDetailService
{
    private WorkPmPointUInsDetailDAO workPmPointUInsDetailDAO = null;
    
	public WorkPmPointUInsDetailDAO getWorkPmPointUInsDetailDAO() {
		return workPmPointUInsDetailDAO;
	}

	public void setWorkPmPointUInsDetailDAO(WorkPmPointUInsDetailDAO workPmPointUInsDetailDAO) {
		this.workPmPointUInsDetailDAO = workPmPointUInsDetailDAO;
	}

	@Override
	public WorkPmPointUInsDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception {
		return workPmPointUInsDetailDAO.findDetail(maPmMstrMstrCommonDTO, user);
	}

	@Override
	public int insertDetail(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO, User user) throws Exception {
		return workPmPointUInsDetailDAO.insertDetail(workPmPointUInsDetailDTO, user);
	}

	@Override
	public int updateDetail(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO, User user) throws Exception {
		return workPmPointUInsDetailDAO.updateDetail(workPmPointUInsDetailDTO, user);
	}

	@Override
	public String setStepNum(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO, User user) throws Exception {
		return workPmPointUInsDetailDAO.setStepNum(workPmPointUInsDetailDTO, user);
	}
}
