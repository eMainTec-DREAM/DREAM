package dream.work.rpt.pmiequipplanrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmiequipplanrate.dao.WorkRptPmiEquipPlanRateListDAO;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanRateCommonDTO;
import dream.work.rpt.pmiequipplanrate.service.WorkRptPmiEquipPlanRateListService;

/**
 * 예방점검 설비 계획대비 실행 비율 목록 - List Service implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptPmiEquipPlanRateListServiceTarget"
 * @spring.txbn id="workRptPmiEquipPlanRateListService"
 * @spring.property name="workRptPmiEquipPlanRateListDAO" ref="workRptPmiEquipPlanRateListDAO"
 */
public class WorkRptPmiEquipPlanRateListServiceImpl implements WorkRptPmiEquipPlanRateListService
{
    private WorkRptPmiEquipPlanRateListDAO workRptPmiEquipPlanRateListDAO = null;

    public List findList(WorkRptPmiEquipPlanRateCommonDTO workRptPmiEquipPlanRateCommonDTO, User user) throws Exception
    {      
        return workRptPmiEquipPlanRateListDAO.findList(workRptPmiEquipPlanRateCommonDTO,user);
    }

    public WorkRptPmiEquipPlanRateListDAO getWorkRptPmiEquipPlanRateListDAO() {
        return workRptPmiEquipPlanRateListDAO;
    }

    public void setWorkRptPmiEquipPlanRateListDAO(WorkRptPmiEquipPlanRateListDAO workRptPmiEquipPlanRateListDAO) {
        this.workRptPmiEquipPlanRateListDAO = workRptPmiEquipPlanRateListDAO;
    }    
    
    public String findTotalCount(WorkRptPmiEquipPlanRateCommonDTO workRptPmiEquipPlanRateCommonDTO,User user)  throws Exception
    {
        return workRptPmiEquipPlanRateListDAO.findTotalCount(workRptPmiEquipPlanRateCommonDTO, user);
    }
}
