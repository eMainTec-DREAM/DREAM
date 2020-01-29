package dream.work.rpt.energyuseprecon.year.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.energyuseprecon.year.dao.EnergyUsePreConYearListDAO;
import dream.work.rpt.energyuseprecon.year.dto.EnergyUsePreConYearCommonDTO;
import dream.work.rpt.energyuseprecon.year.service.EnergyUsePreConYearListService;

/**
 * EnergyUsePreConYear Page - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="energyUsePreConYearListServiceTarget"
 * @spring.txbn id="energyUsePreConYearListService"
 * @spring.property name="energyUsePreConYearListDAO" ref="energyUsePreConYearListDAO"
 */
public class EnergyUsePreConYearListServiceImpl implements EnergyUsePreConYearListService
{
    private EnergyUsePreConYearListDAO energyUsePreConYearListDAO = null;

    public List findList(EnergyUsePreConYearCommonDTO energyUsePreConYearCommonDTO, User user) throws Exception
    {      
        return energyUsePreConYearListDAO.findList(energyUsePreConYearCommonDTO,user);
    }

    public EnergyUsePreConYearListDAO getEnergyUsePreConYearListDAO() {
        return energyUsePreConYearListDAO;
    }

    public void setEnergyUsePreConYearListDAO(EnergyUsePreConYearListDAO energyUsePreConYearListDAO) {
        this.energyUsePreConYearListDAO = energyUsePreConYearListDAO;
    }    
    
    public String findTotalCount(EnergyUsePreConYearCommonDTO energyUsePreConYearCommonDTO,User user)  throws Exception
    {
        return energyUsePreConYearListDAO.findTotalCount(energyUsePreConYearCommonDTO, user);
    }
}
