package dream.work.let.service.spring;

import common.bean.User;
import dream.req.work.service.MaWoReqResDetailService;
import dream.work.let.dao.WorkLetDetailDAO;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;
import dream.work.let.service.WorkLetDetailService;

/**
 * 안전작업- 상세 serviceimpl 
 * @author  syyang
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workLetDetailServiceTarget"
 * @spring.txbn id="workLetDetailService"
 * @spring.property name="workLetDetailDAO" ref="workLetDetailDAO"
 * @spring.property name="maWoReqResDetailService" ref="maWoReqResDetailService"
 */
public class WorkLetDetailServiceImpl implements WorkLetDetailService 
{
    private WorkLetDetailDAO workLetDetailDAO = null;
    private MaWoReqResDetailService maWoReqResDetailService = null;

	public MaWoReqResDetailService getMaWoReqResDetailService()
    {
        return maWoReqResDetailService;
    }
    public void setMaWoReqResDetailService(
            MaWoReqResDetailService maWoReqResDetailService)
    {
        this.maWoReqResDetailService = maWoReqResDetailService;
    }
    public WorkLetDetailDAO getWorkLetDetailDAO() {
		return workLetDetailDAO;
	}
	public void setWorkLetDetailDAO(WorkLetDetailDAO workLetDetailDAO) {
		this.workLetDetailDAO = workLetDetailDAO;
	}
	
	public WorkLetDetailDTO findDetail(WorkLetCommonDTO workLetCommonDTO,User user)throws Exception
    {
        return workLetDetailDAO.findDetail(workLetCommonDTO, user);
    }
	
	public int insertDetail(WorkLetDetailDTO workLetDetailDTO, WorkLetCommonDTO workLetCommonDTO, User loginUser) throws Exception
    {
		return workLetDetailDAO.insertDetail(workLetDetailDTO, loginUser);
    }
	
	public int updateDetail(WorkLetDetailDTO workLetDetailDTO, User user) throws Exception
    {        
        return workLetDetailDAO.updateDetail(workLetDetailDTO, user);
    }
	public int createPoint(WorkLetDetailDTO workLetDetailDTO) throws Exception
    {
//		return workLetDetailDAO.createPoint(workLetDetailDTO);
		return 0;
    }
	
	public int completeDetail(WorkLetDetailDTO workLetDetailDTO, User user) throws Exception
	{
		// 안전작업허가상태 완료
        workLetDetailDTO.setWoLetStatus("COM");
        // 안전작업 완료
        workLetDetailDAO.completeDetail(workLetDetailDTO, user);
        
	    return 0;
	}
	@Override
	public String completeCancelDetail(WorkLetDetailDTO workLetDetailDTO, User user) throws Exception
	{
		String woLetStatus = "";
		
		// 안전작업허가상태
		// 안전작업 - 안전작업허가서유형 중 '완료'가 존재하지 않으면 상태 '작성중'/ 존재하면 '진행중'
		woLetStatus = workLetDetailDAO.getWoLetStatus(workLetDetailDTO, user);
        workLetDetailDTO.setWoLetStatus(woLetStatus);
        workLetDetailDTO.setLetBy("");
        workLetDetailDTO.setLetDate("");
        
        // 안전작업 완료취소
        workLetDetailDAO.completeDetail(workLetDetailDTO, user);
        
		return woLetStatus;
	}

}
