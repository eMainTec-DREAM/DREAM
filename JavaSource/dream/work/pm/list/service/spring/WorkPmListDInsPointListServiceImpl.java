package dream.work.pm.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmListDInsPointListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListDInsPointDetailDTO;
import dream.work.pm.list.service.WorkPmListDInsPointDetailService;
import dream.work.pm.list.service.WorkPmListDInsPointListService;

/**
 * WorkPmListDInsPoint Page - List Service implements
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmListDInsPointListServiceTarget"
 * @spring.txbn id="workPmListDInsPointListService"
 * @spring.property name="workPmListDInsPointListDAO" ref="workPmListDInsPointListDAO"
 * @spring.property name="workPmListDInsPointDetailService" ref="workPmListDInsPointDetailService"
 */
public class WorkPmListDInsPointListServiceImpl implements WorkPmListDInsPointListService
{
    private WorkPmListDInsPointListDAO workPmListDInsPointListDAO = null;
    private WorkPmListDInsPointDetailService workPmListDInsPointDetailService = null;
    
    public WorkPmListDInsPointDetailService getWorkPmListDInsPointDetailService() {
		return workPmListDInsPointDetailService;
	}

	public void setWorkPmListDInsPointDetailService(WorkPmListDInsPointDetailService workPmListDInsPointDetailService) {
		this.workPmListDInsPointDetailService = workPmListDInsPointDetailService;
	}

	public List findList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception
    {      
        return workPmListDInsPointListDAO.findList(maPmMstrCommonDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workPmListDInsPointListDAO.deleteList(id, user);
            }
        return result;
    }

    public WorkPmListDInsPointListDAO getWorkPmListDInsPointListDAO() {
        return workPmListDInsPointListDAO;
    }

    public void setWorkPmListDInsPointListDAO(WorkPmListDInsPointListDAO workPmListDInsPointListDAO) {
        this.workPmListDInsPointListDAO = workPmListDInsPointListDAO;
    }    
    
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)  throws Exception
    {
        return workPmListDInsPointListDAO.findTotalCount(maPmMstrCommonDTO, user);
    }

	@Override
	public int insertLovDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO,
			User user) throws Exception {
		
    	int result = 0;
    	
    	String[] multiKey = maPmMstrMstrCommonDTO.getMultiKey().split("\\+");

    	for(int i=0; multiKey.length > i; i++)
        {	
    		maPmMstrMstrCommonDTO.setPmPointId(workPmListDInsPointListDAO.getNextSequence("SQAMTPOINT_ID"));//list id
    		workPmListDInsPointDetailDTO.setEqCtgPmPointId(multiKey[i]);//lov id
   
            result = result + workPmListDInsPointDetailService.insertLovDetail(workPmListDInsPointDetailDTO, maPmMstrMstrCommonDTO, user);
        }
    	return result;
	}
}
