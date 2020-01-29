package dream.part.rpt.orgptusehist.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rpt.orgptusehist.dao.PartRptOrgPtUseHistListDAO;
import dream.part.rpt.orgptusehist.dto.PartRptOrgPtUseHistCommonDTO;
import dream.part.rpt.orgptusehist.service.PartRptOrgPtUseHistListService;

/**
 * PartRptOrgPtUseHist Page - List Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="partRptOrgPtUseHistListServiceTarget"
 * @spring.txbn id="partRptOrgPtUseHistListService"
 * @spring.property name="partRptOrgPtUseHistListDAO" ref="partRptOrgPtUseHistListDAO"
 */
public class PartRptOrgPtUseHistListServiceImpl implements PartRptOrgPtUseHistListService
{
    private PartRptOrgPtUseHistListDAO partRptOrgPtUseHistListDAO = null;

    public List findList(PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO, User user) throws Exception
    {      
        return partRptOrgPtUseHistListDAO.findList(partRptOrgPtUseHistCommonDTO,user);
    }

    public PartRptOrgPtUseHistListDAO getPartRptOrgPtUseHistListDAO() {
        return partRptOrgPtUseHistListDAO;
    }

    public void setPartRptOrgPtUseHistListDAO(PartRptOrgPtUseHistListDAO partRptOrgPtUseHistListDAO) {
        this.partRptOrgPtUseHistListDAO = partRptOrgPtUseHistListDAO;
    }    
    
    public String findTotalCount(PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO,User user)  throws Exception
    {
        return partRptOrgPtUseHistListDAO.findTotalCount(partRptOrgPtUseHistCommonDTO, user);
    }
}
