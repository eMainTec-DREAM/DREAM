package dream.work.rpt.energyuseprecon.month.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.energyuseprecon.month.dao.EnergyUsePreConMonthDetailDAO;
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthDetailDTO;
import dream.work.rpt.energyuseprecon.month.service.EnergyUsePreConMonthDetailService;

/**
 * EnergyUsePreConMonth Page - Detail Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="energyUsePreConMonthDetailServiceTarget"
 * @spring.txbn id="energyUsePreConMonthDetailService"
 * @spring.property name="energyUsePreConMonthDetailDAO" ref="energyUsePreConMonthDetailDAO"
 */
public class EnergyUsePreConMonthDetailServiceImpl implements EnergyUsePreConMonthDetailService
{
    private EnergyUsePreConMonthDetailDAO energyUsePreConMonthDetailDAO = null;

    public List findDetail(EnergyUsePreConMonthDetailDTO energyUsePreConMonthDetailDTO, User user) throws Exception
    {      
        return energyUsePreConMonthDetailDAO.findDetail(energyUsePreConMonthDetailDTO,user);
    }

    public EnergyUsePreConMonthDetailDAO getEnergyUsePreConMonthDetailDAO() {
        return energyUsePreConMonthDetailDAO;
    }

    public void setEnergyUsePreConMonthDetailDAO(EnergyUsePreConMonthDetailDAO energyUsePreConMonthDetailDAO) {
        this.energyUsePreConMonthDetailDAO = energyUsePreConMonthDetailDAO;
    }    
    
    public String findTotalCount(EnergyUsePreConMonthDetailDTO energyUsePreConMonthDetailDTO,User user)  throws Exception
    {
        return energyUsePreConMonthDetailDAO.findTotalCount(energyUsePreConMonthDetailDTO, user);
    }
}
