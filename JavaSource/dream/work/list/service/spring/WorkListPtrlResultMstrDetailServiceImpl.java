package dream.work.list.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;
import dream.work.list.dao.WorkListPtrlResultMstrDetailDAO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.dto.WorkListPtrlResultMstrDetailDTO;
import dream.work.list.service.WorkListPtrlResultMstrDetailService;
import dream.work.rpt.mabdpoint.service.MaBdPointDetailService;

/**
 * 순회점검 작업결과 목록
 * @author youngjoo38
 * @version $Id: WorkListPtrlResultMstrDetailServiceImpl.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListPtrlResultMstrDetailServiceTarget"
 * @spring.txbn id="workListPtrlResultMstrDetailService"
 * @spring.property name="workListPtrlResultMstrDetailDAO" ref="workListPtrlResultMstrDetailDAO"
 */
public class WorkListPtrlResultMstrDetailServiceImpl implements WorkListPtrlResultMstrDetailService
{
    private WorkListPtrlResultMstrDetailDAO workListPtrlResultMstrDetailDAO = null;
    
    public WorkListPtrlResultMstrDetailDAO getWorkListPtrlResultMstrDetailDAO() {
		return workListPtrlResultMstrDetailDAO;
	}

	public void setWorkListPtrlResultMstrDetailDAO(WorkListPtrlResultMstrDetailDAO workListPtrlResultMstrDetailDAO) {
		this.workListPtrlResultMstrDetailDAO = workListPtrlResultMstrDetailDAO;
	}

	public WorkListPtrlResultMstrDetailDTO findDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user)
    {
        return workListPtrlResultMstrDetailDAO.findDetail(workListPtrlResultCommonDTO, workCalPmPtrlMonthCommonDTO,user);
    }
    
	public int updateDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO,User user) throws Exception
    {        
        return workListPtrlResultMstrDetailDAO.updateDetail(workListPtrlResultMstrDetailDTO, user);
    }
	public String checkDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) throws Exception
    {
        return workListPtrlResultMstrDetailDAO.checkDetail(workListPtrlResultMstrDetailDTO, user);
    }
	public int ptrlCompletedDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) throws Exception
	{        
		workListPtrlResultMstrDetailDAO.completeSched(workListPtrlResultMstrDetailDTO, user);
		workListPtrlResultMstrDetailDAO.executePmUpdate(workListPtrlResultMstrDetailDTO, user);
		workListPtrlResultMstrDetailDAO.ptrlCompletedDetail(workListPtrlResultMstrDetailDTO, user);
		
		// 이상점검항목 처리 
        MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) CommonUtil.getBean("maBdPointDetailService", user);
        maBdPointDetailService.checkNgPoint("PINS", workListPtrlResultMstrDetailDTO.getPmPtrlRsltListId(), "C", CommonUtil.getRowDateToNum(workListPtrlResultMstrDetailDTO.getPtrlComDate()), user);
    
		return 0;
	}
	public String getPtrlComDate(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) throws Exception
    {
        return workListPtrlResultMstrDetailDAO.getPtrlComDate(workListPtrlResultMstrDetailDTO, user);
    }
    
    public String getPtrlComTime(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) throws Exception
    {
        return workListPtrlResultMstrDetailDAO.getPtrlComTime(workListPtrlResultMstrDetailDTO, user);
    }
}
