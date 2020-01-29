package dream.work.pmi.list.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import dream.work.pmi.list.dao.WorkPmiListDAO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiDetailDTO;
import dream.work.pmi.list.service.WorkPmiListService;

/**
 * 점검작업 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: WorkPmiListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmiListServiceTarget"
 * @spring.txbn id="workPmiListService"
 * @spring.property name="workPmiListDAO" ref="workPmiListDAO"
 */
public class WorkPmiListServiceImpl implements WorkPmiListService
{
    private WorkPmiListDAO workPmiListDAO = null;

    public WorkPmiListDAO getWorkPmiListDAO() {
		return workPmiListDAO;
	}

	public void setWorkPmiListDAO(WorkPmiListDAO workPmiListDAO) {
		this.workPmiListDAO = workPmiListDAO;
	}

	public List findList(WorkPmiCommonDTO workPmiCommonDTO, User user)
    {      
        return workPmiListDAO.findList(workPmiCommonDTO,user);
    }
	
	public int deleteList(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null) && deleteRows.length>0){
            //DELETE TAPMINSLIST
            List list = new ArrayList();
            WorkPmiDetailDTO workPmiDetailDTO = new WorkPmiDetailDTO();
            for(String id:deleteRows){
                workPmiDetailDTO.setPminslistId(id);
                list.add(BeanUtils.cloneBean(workPmiDetailDTO));
            }
            result = workPmiListDAO.updateDeleteTag(list, user).length;
        }
        return result;
    }
	
	@Override
    public String findTotalCount( WorkPmiCommonDTO workPmiCommonDTO, User user)
    {
        return workPmiListDAO.findTotalCount(workPmiCommonDTO,user);
    }
}

