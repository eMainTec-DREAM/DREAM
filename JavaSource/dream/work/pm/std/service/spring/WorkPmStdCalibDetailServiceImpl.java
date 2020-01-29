package dream.work.pm.std.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.pm.std.dao.WorkPmStdCalibDetailDAO;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibDetailDTO;
import dream.work.pm.std.service.WorkPmStdCalibDetailService;
import dream.work.pm.std.service.WorkPmStdCalibListService;

/**
 * 교정표준값 타입- Detail Service implements
 * @author kim21017
 * @version $Id: WorkPmStdCalibDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workPmStdCalibDetailServiceTarget"
 * @spring.txbn id="workPmStdCalibDetailService"
 * @spring.property name="workPmStdCalibDetailDAO" ref="workPmStdCalibDetailDAO"
 */
public class WorkPmStdCalibDetailServiceImpl implements WorkPmStdCalibDetailService
{
    private WorkPmStdCalibDetailDAO workPmStdCalibDetailDAO = null;
    
    public WorkPmStdCalibDetailDTO findDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user) throws Exception
    {
    	return workPmStdCalibDetailDAO.findDetail(workPmStdCalibCommonDTO, user);
    }
    
    public int insertDetail(WorkPmStdCalibDetailDTO workPmStdCalibDetailDTO, User user) throws Exception
    {
    	return workPmStdCalibDetailDAO.insertDetail(workPmStdCalibDetailDTO, user);
    }
    
    public int updateDetail(WorkPmStdCalibDetailDTO workPmStdCalibDetailDTO, User user) throws Exception
    {
    	 return workPmStdCalibDetailDAO.updateDetail(workPmStdCalibDetailDTO, user);
    }

	public WorkPmStdCalibDetailDAO getWorkPmStdCalibDetailDAO() {
		return workPmStdCalibDetailDAO;
	}

	public void setWorkPmStdCalibDetailDAO(WorkPmStdCalibDetailDAO workPmStdCalibDetailDAO) {
		this.workPmStdCalibDetailDAO = workPmStdCalibDetailDAO;
	}

	public int validDetail(WorkPmStdCalibDetailDTO workPmStdCalibDetailDTO, User user) throws Exception 
	{
		WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO = new WorkPmStdCalibCommonDTO();
		WorkPmStdCalibListService workPmStdCalibListService = (WorkPmStdCalibListService) CommonUtil.getBean("workPmStdCalibListService");
		
		workPmStdCalibCommonDTO.setValidId(workPmStdCalibDetailDTO.getPmCalibStdTpId());
		workPmStdCalibCommonDTO.setValidPmcTypeId(workPmStdCalibDetailDTO.getPmcTypeId());
		
		int pmcTypeCnt = Integer.parseInt(workPmStdCalibListService.findTotalCount(workPmStdCalibCommonDTO, user));
		
		return pmcTypeCnt;
	}
}
