package dream.work.let.permit.service.spring;

import common.bean.User;
import dream.work.let.dao.WorkLetDetailDAO;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;
import dream.work.let.permit.dao.WorkLetPermitDetailDAO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.service.WorkLetPermitDetailService;

/**
 * 안전작업 - 안전작업허가서 상세
 * @author syyang
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="workLetPermitDetailServiceTarget"
 * @spring.txbn id="workLetPermitDetailService"
 * @spring.property name="workLetPermitDetailDAO" ref="workLetPermitDetailDAO"
 * @spring.property name="workLetDetailDAO" ref="workLetDetailDAO"
 */
public class WorkLetPermitDetailServiceImpl implements WorkLetPermitDetailService
{
    private WorkLetPermitDetailDAO workLetPermitDetailDAO = null;
    private WorkLetDetailDAO workLetDetailDAO = null;
    
    public WorkLetPermitDetailDAO getWorkLetPermitDetailDAO() {
		return workLetPermitDetailDAO;
	}

	public void setWorkLetPermitDetailDAO(WorkLetPermitDetailDAO workLetPermitDetailDAO) {
		this.workLetPermitDetailDAO = workLetPermitDetailDAO;
	}
	
	public WorkLetDetailDAO getWorkLetDetailDAO() {
		return workLetDetailDAO;
	}

	public void setWorkLetDetailDAO(WorkLetDetailDAO workLetDetailDAO) {
		this.workLetDetailDAO = workLetDetailDAO;
	}

	
	public WorkLetPermitDetailDTO findDetail(String woLetId, String woLetListId, User user)throws Exception
    {
        return workLetPermitDetailDAO.findDetail(woLetId, woLetListId, user);
    }
    
	public int updateDetail(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception
    {        
        return workLetPermitDetailDAO.updateDetail(workLetCommonDTO, workLetPermitDetailDTO, user);
    }
	public int insertDetail(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception
    {        
        return workLetPermitDetailDAO.insertDetail(workLetCommonDTO, workLetPermitDetailDTO, user);
    }

	@Override
	public String completeDetail(WorkLetDetailDTO workLetDetailDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception
	{
		String woLetStatus = "";
		
		// 안전작업허가서유형 상태 완료
		workLetPermitDetailDTO.setWoLetListStatus("COM");
        // 안전작업허가서유형 완료
		workLetPermitDetailDAO.completeDetail(workLetDetailDTO, workLetPermitDetailDTO, user);
		
		// 안전작업 상태 변경
		// 상위메뉴 안전작업 상태가 완료가 아니라면 상태 진행중으로 변경
		woLetStatus = workLetDetailDAO.getWoLetStatus(workLetDetailDTO, user);
		
		workLetDetailDTO.setWoLetStatus(woLetStatus);
		workLetDetailDAO.completeDetail(workLetDetailDTO, user);
		
	    return woLetStatus;
	}
	@Override
	public String completeCancelDetail(WorkLetDetailDTO workLetDetailDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception
	{
		String woLetStatus = "";
		
		// 안전작업허가서유형 상태 완료취소 (완료->진행중)
		workLetPermitDetailDTO.setWoLetListStatus("DNG");
        // 안전작업허가서유형 완료취소 (완료->진행중)
		workLetPermitDetailDAO.completeDetail(workLetDetailDTO, workLetPermitDetailDTO, user);

		// 안전작업 상태 변경
		// 안전작업 - 안전작업허가서유형 중 '완료' 상태가 존재하지 않으면 상태 작성중으로 변경
		woLetStatus = workLetDetailDAO.getWoLetStatus(workLetDetailDTO, user);
		
		workLetDetailDTO.setWoLetStatus(woLetStatus);
		workLetDetailDAO.completeDetail(workLetDetailDTO, user);
        
		return woLetStatus;
	}

}
