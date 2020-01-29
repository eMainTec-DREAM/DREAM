package dream.work.rpt.pmiequipcmptrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmiequipcmptrate.dao.WorkRptPmiEquipCmptRateListDAO;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptRateCommonDTO;
import dream.work.rpt.pmiequipcmptrate.service.WorkRptPmiEquipCmptRateListService;

/**
 * 예방점검설비 실행 비율 목록 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptPmiEquipCmptRateListServiceTarget"
 * @spring.txbn id="workRptPmiEquipCmptRateListService"
 * @spring.property name="workRptPmiEquipCmptRateListDAO" ref="workRptPmiEquipCmptRateListDAO"
 */
public class WorkRptPmiEquipCmptRateListServiceImpl implements WorkRptPmiEquipCmptRateListService
{
    private WorkRptPmiEquipCmptRateListDAO workRptPmiEquipCmptRateListDAO = null;

    public List findList(WorkRptPmiEquipCmptRateCommonDTO workRptPmiEquipCmptRateCommonDTO, User user) throws Exception
    {      
        return workRptPmiEquipCmptRateListDAO.findList(workRptPmiEquipCmptRateCommonDTO,user);
    }

    public WorkRptPmiEquipCmptRateListDAO getWorkRptPmiEquipCmptRateListDAO() {
        return workRptPmiEquipCmptRateListDAO;
    }

    public void setWorkRptPmiEquipCmptRateListDAO(WorkRptPmiEquipCmptRateListDAO workRptPmiEquipCmptRateListDAO) {
        this.workRptPmiEquipCmptRateListDAO = workRptPmiEquipCmptRateListDAO;
    }    
    
    public String findTotalCount(WorkRptPmiEquipCmptRateCommonDTO workRptPmiEquipCmptRateCommonDTO,User user)  throws Exception
    {
        return workRptPmiEquipCmptRateListDAO.findTotalCount(workRptPmiEquipCmptRateCommonDTO, user);
    }
}
