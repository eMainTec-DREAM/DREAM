package dream.work.let.permit.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.permit.dao.WorkLetPermitListDAO;
import dream.work.let.permit.dao.WorkLetPermitPointDetailDAO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitListDTO;
import dream.work.let.permit.service.WorkLetPermitDetailService;
import dream.work.let.permit.service.WorkLetPermitListService;

/**
 * 안전작업 - 안전작업허가서 목록
 * @author syyang
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="workLetPermitListServiceTarget"
 * @spring.txbn id="workLetPermitListService"
 * @spring.property name="workLetPermitListDAO" ref="workLetPermitListDAO"
 * @spring.property name="workLetPermitDetailService" ref="workLetPermitDetailService"
 * @spring.property name="workLetPermitPointDetailDAO" ref="workLetPermitPointDetailDAO"
 */
public class WorkLetPermitListServiceImpl implements WorkLetPermitListService
{
    private WorkLetPermitListDAO workLetPermitListDAO = null;
    private WorkLetPermitDetailService workLetPermitDetailService = null;
    
    private WorkLetPermitPointDetailDAO workLetPermitPointDetailDAO = null;


	public WorkLetPermitDetailService getWorkLetPermitDetailService()
    {
        return workLetPermitDetailService;
    }
    public void setWorkLetPermitDetailService(
    		WorkLetPermitDetailService workLetPermitDetailService)
    {
        this.workLetPermitDetailService = workLetPermitDetailService;
    }
	public WorkLetPermitListDAO getWorkLetPermitListDAO() {
		return workLetPermitListDAO;
	}
	public void setWorkLetPermitListDAO(WorkLetPermitListDAO workLetPermitListDAO) {
		this.workLetPermitListDAO = workLetPermitListDAO;
	}
	public WorkLetPermitPointDetailDAO getWorkLetPermitPointDetailDAO() {
		return workLetPermitPointDetailDAO;
	}
	public void setWorkLetPermitPointDetailDAO(WorkLetPermitPointDetailDAO workLetPermitPointDetailDAO) {
		this.workLetPermitPointDetailDAO = workLetPermitPointDetailDAO;
	}
	
	
	public List findWoLetPermitList(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitListDTO workLetPermitListDTO, User loginUser)
	{      
		return workLetPermitListDAO.findWoLetPermitList(workLetCommonDTO, workLetPermitListDTO, loginUser);
	}
	
	public int deleteWoLetList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workLetPermitListDAO.deleteWoLetList(id, compNo);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitListDTO workLetPermitListDTO, User user) throws Exception
	{
		return workLetPermitListDAO.findTotalCount(workLetCommonDTO, workLetPermitListDTO, user);
	}

    @Override
    public int inputWoLetList(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = workLetPermitDetailDTO.getMultiKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
            workLetPermitDetailDTO.setWoLetCtgType(multiKey[i]);
            
            if(!"".equals(workLetPermitDetailDTO.getWoLetCtgType()))
            {
                workLetPermitDetailDTO.setWoLetListId(workLetPermitListDAO.getNextSequence("SQAWOLETLIST_ID"));
                workLetPermitDetailDTO.setWoLetListNo(workLetPermitDetailDTO.getWoLetListId());
                
                if("".equals(workLetPermitDetailDTO.getWoLetListStatus()))
                	workLetPermitDetailDTO.setWoLetListStatus("DNG");
                
                result = result + workLetPermitDetailService.insertDetail(workLetCommonDTO, workLetPermitDetailDTO, user);
                
                // 표준점검항목 추가
                workLetPermitPointDetailDAO.insertStdPointDetail(workLetPermitDetailDTO.getWoLetListId(), multiKey[i], user);
            }
        }
        
        return result;
    }
}

