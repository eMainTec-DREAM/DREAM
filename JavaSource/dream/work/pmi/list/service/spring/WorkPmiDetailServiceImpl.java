package dream.work.pmi.list.service.spring;

import java.util.ArrayList;
import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.pmi.list.dao.WorkPmiDetailDAO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiDetailDTO;
import dream.work.pmi.list.service.WorkPmiDetailService;
import dream.work.pmi.list.service.WorkPmiListService;
import dream.work.rpt.mabdpoint.service.MaBdPointDetailService;

/**
 * 점검작업- 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: WorkPmiDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workPmiDetailServiceTarget"
 * @spring.txbn id="workPmiDetailService"
 * @spring.property name="workPmiDetailDAO" ref="workPmiDetailDAO"
 */
public class WorkPmiDetailServiceImpl implements WorkPmiDetailService 
{
    private WorkPmiDetailDAO workPmiDetailDAO = null;
    
	public WorkPmiDetailDAO getWorkPmiDetailDAO() {
		return workPmiDetailDAO;
	}

	public void setWorkPmiDetailDAO(WorkPmiDetailDAO workPmiDetailDAO) {
		this.workPmiDetailDAO = workPmiDetailDAO;
	}

	public WorkPmiDetailDTO findDetail(WorkPmiCommonDTO workPmiCommonDTO, User user)throws Exception
    {
	    WorkPmiListService workPmiListService = (WorkPmiListService) CommonUtil.getBean("workPmiListService", user);
	    return (WorkPmiDetailDTO)CommonUtil.makeDetailFromList(workPmiListService.findList(workPmiCommonDTO, user), new WorkPmiDetailDTO());
    }
	
	@Override
    public int[] updateList(List<WorkPmiDetailDTO> list, User user) throws Exception
    {
        return workPmiDetailDAO.update(list, user);
    }
	
	public int updateDetail(WorkPmiDetailDTO workPmiDetailDTO, User user) throws Exception
    {        
	    List<WorkPmiDetailDTO> workPmiDetailDTOList = new ArrayList<WorkPmiDetailDTO>();

	    workPmiDetailDTOList.add(workPmiDetailDTO);
	    return this.updateList(workPmiDetailDTOList, user)[0];
    }
	
	public int completeDetail(WorkPmiDetailDTO workPmiDetailDTO, User user) throws Exception
    {        
		workPmiDetailDTO.setPmschedStatusId("C");
		
		workPmiDetailDAO.completeSched(workPmiDetailDTO, user);
		workPmiDetailDAO.executePmUpdate(workPmiDetailDTO, user);
		
		workPmiDetailDAO.completeDetail(workPmiDetailDTO, user);
		
		//이상점검항목 처리
		MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) CommonUtil.getBean("maBdPointDetailService", user);
		maBdPointDetailService.checkNgPoint("RINS", workPmiDetailDTO.getPminslistId(), workPmiDetailDTO.getPmschedStatusId(), CommonUtil.getRowDateToNum(workPmiDetailDTO.getEndDate()), user);
		
		return 0;
    }
	public String checkPoint(WorkPmiDetailDTO workPmiDetailDTO,User user) throws Exception
	{
		return workPmiDetailDAO.checkPoint(workPmiDetailDTO,user );
	}
	
	public int completeCancelDetail(WorkPmiDetailDTO workPmiDetailDTO, User user) throws Exception
	{        
		workPmiDetailDTO.setPmschedStatusId("S");
		
		workPmiDetailDAO.completeCancelSched(workPmiDetailDTO, user);
		workPmiDetailDAO.executePmUpdate(workPmiDetailDTO, user);
		
		workPmiDetailDAO.completeCancelDetail(workPmiDetailDTO, user);
		
		//이상점검항목 처리
		MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) CommonUtil.getBean("maBdPointDetailService", user);
		maBdPointDetailService.checkNgPoint("RINS", workPmiDetailDTO.getPminslistId(), workPmiDetailDTO.getPmschedStatusId(), CommonUtil.getRowDateToNum(workPmiDetailDTO.getEndDate()), user);

		return 0;
	}
	

    @Override
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
        if("C".equals(appReqDetailDTO.getParentStatus()))
        {
            WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();
            workPmiCommonDTO.setPminslistId(appReqDetailDTO.getObjectId());
            workPmiCommonDTO.setCompNo(user.getCompNo());
            WorkPmiDetailDTO workPmiDetailDTO = this.findDetail(workPmiCommonDTO, user);
            
            this.completeDetail(workPmiDetailDTO, user);
        }
        else
        {
            workPmiDetailDAO.setStatus(appReqDetailDTO, user);
        }
    }
}
