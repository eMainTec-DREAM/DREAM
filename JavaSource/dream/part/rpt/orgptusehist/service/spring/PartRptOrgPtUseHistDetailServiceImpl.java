package dream.part.rpt.orgptusehist.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rpt.orgptusehist.dao.PartRptOrgPtUseHistDetailDAO;
import dream.part.rpt.orgptusehist.dto.PartRptOrgPtUseHistDetailDTO;
import dream.part.rpt.orgptusehist.service.PartRptOrgPtUseHistDetailService;

/**
 * PartRptOrgPtUseHist Page - Detail Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="partRptOrgPtUseHistDetailServiceTarget"
 * @spring.txbn id="partRptOrgPtUseHistDetailService"
 * @spring.property name="partRptOrgPtUseHistDetailDAO" ref="partRptOrgPtUseHistDetailDAO"
 */
public class PartRptOrgPtUseHistDetailServiceImpl implements PartRptOrgPtUseHistDetailService
{
    private PartRptOrgPtUseHistDetailDAO partRptOrgPtUseHistDetailDAO = null;

    public List findDetail(PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO, User user) throws Exception
    {      
        return partRptOrgPtUseHistDetailDAO.findDetail(partRptOrgPtUseHistDetailDTO,user);
    }
    public List findChart(PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO, User user) throws Exception
    {      
        return partRptOrgPtUseHistDetailDAO.findChart(partRptOrgPtUseHistDetailDTO,user);
    }

    public PartRptOrgPtUseHistDetailDAO getPartRptOrgPtUseHistDetailDAO() {
        return partRptOrgPtUseHistDetailDAO;
    }

    public void setPartRptOrgPtUseHistDetailDAO(PartRptOrgPtUseHistDetailDAO partRptOrgPtUseHistDetailDAO) {
        this.partRptOrgPtUseHistDetailDAO = partRptOrgPtUseHistDetailDAO;
    }    
}
