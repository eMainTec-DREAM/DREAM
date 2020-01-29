package dream.work.rpt.pmiequipcmptrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmiequipcmptrate.dao.WorkRptPmiEquipCmptDetailListDAO;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptDetailListDTO;
import dream.work.rpt.pmiequipcmptrate.service.WorkRptPmiEquipCmptDetailListService;

/**
 * 예방점검 실행 상세 목록 Service implements
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptPmiEquipCmptDetailListServiceTarget"
 * @spring.txbn id="workRptPmiEquipCmptDetailListService"
 * @spring.property name="workRptPmiEquipCmptDetailListDAO" ref="workRptPmiEquipCmptDetailListDAO"
 */
public class WorkRptPmiEquipCmptDetailListServiceImpl implements WorkRptPmiEquipCmptDetailListService
{
    private WorkRptPmiEquipCmptDetailListDAO workRptPmiEquipCmptDetailListDAO = null;

    public List findList(WorkRptPmiEquipCmptDetailListDTO workRptPmiEquipCmptDetailListDTO, User user) throws Exception
    {      
        return workRptPmiEquipCmptDetailListDAO.findList(workRptPmiEquipCmptDetailListDTO,user);
    }

    public WorkRptPmiEquipCmptDetailListDAO getWorkRptPmiEquipCmptDetailListDAO() {
        return workRptPmiEquipCmptDetailListDAO;
    }

    public void setWorkRptPmiEquipCmptDetailListDAO(WorkRptPmiEquipCmptDetailListDAO workRptPmiEquipCmptDetailListDAO) {
        this.workRptPmiEquipCmptDetailListDAO = workRptPmiEquipCmptDetailListDAO;
    }    
    
    public String findTotalCount(WorkRptPmiEquipCmptDetailListDTO workRptPmiEquipCmptDetailListDTO,User user)  throws Exception
    {
        return workRptPmiEquipCmptDetailListDAO.findTotalCount(workRptPmiEquipCmptDetailListDTO, user);
    }
}
