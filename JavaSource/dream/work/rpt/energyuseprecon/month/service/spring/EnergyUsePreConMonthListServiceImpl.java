package dream.work.rpt.energyuseprecon.month.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.energyuseprecon.month.dao.EnergyUsePreConMonthListDAO;
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthCommonDTO;
import dream.work.rpt.energyuseprecon.month.service.EnergyUsePreConMonthListService;

/**
 * EnergyUsePreConMonth Page - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="energyUsePreConMonthListServiceTarget"
 * @spring.txbn id="energyUsePreConMonthListService"
 * @spring.property name="energyUsePreConMonthListDAO" ref="energyUsePreConMonthListDAO"
 */
public class EnergyUsePreConMonthListServiceImpl implements EnergyUsePreConMonthListService
{
    private EnergyUsePreConMonthListDAO energyUsePreConMonthListDAO = null;

    public List findList(EnergyUsePreConMonthCommonDTO energyUsePreConMonthCommonDTO, User user) throws Exception
    {      
        return energyUsePreConMonthListDAO.findList(energyUsePreConMonthCommonDTO,user);
    }

    public EnergyUsePreConMonthListDAO getEnergyUsePreConMonthListDAO() {
        return energyUsePreConMonthListDAO;
    }

    public void setEnergyUsePreConMonthListDAO(EnergyUsePreConMonthListDAO energyUsePreConMonthListDAO) {
        this.energyUsePreConMonthListDAO = energyUsePreConMonthListDAO;
    }    
    
    public String findTotalCount(EnergyUsePreConMonthCommonDTO energyUsePreConMonthCommonDTO,User user)  throws Exception
    {
        return energyUsePreConMonthListDAO.findTotalCount(energyUsePreConMonthCommonDTO, user);
    }
}
