package dream.work.pm.list.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmiDInsListDAO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsDetailDTO;
import dream.work.pm.list.service.WorkPmiDInsListService;

/**
 * WorkPmiDIns Page - List Service implements
 * @author youngjoo38
 * @version $Id: WorkPmiDInsListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmiDInsListServiceTarget"
 * @spring.txbn id="workPmiDInsListService"
 * @spring.property name="workPmiDInsListDAO" ref="workPmiDInsListDAO"
 */
public class WorkPmiDInsListServiceImpl implements WorkPmiDInsListService
{
    private WorkPmiDInsListDAO workPmiDInsListDAO = null;

    public List findList(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception
    {      
        return workPmiDInsListDAO.findList(workPmiDInsCommonDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null) && deleteRows.length>0){
            //DELETE TAPMINSDLIST
            List list = new ArrayList();
            WorkPmiDInsDetailDTO workPmiDInsDetailDTO = new WorkPmiDInsDetailDTO();
            for(String id:deleteRows){
                workPmiDInsDetailDTO.setPmInsDListId(id);
                list.add(BeanUtils.cloneBean(workPmiDInsDetailDTO));
            }
            result = workPmiDInsListDAO.updateDeleteTag(list, user).length;
        }
        
        return result;
    }

    public WorkPmiDInsListDAO getWorkPmiDInsListDAO() {
        return workPmiDInsListDAO;
    }

    public void setWorkPmiDInsListDAO(WorkPmiDInsListDAO workPmiDInsListDAO) {
        this.workPmiDInsListDAO = workPmiDInsListDAO;
    }    
    
    public String findTotalCount(WorkPmiDInsCommonDTO workPmiDInsCommonDTO,User user)  throws Exception
    {
        return workPmiDInsListDAO.findTotalCount(workPmiDInsCommonDTO, user);
    }
}
