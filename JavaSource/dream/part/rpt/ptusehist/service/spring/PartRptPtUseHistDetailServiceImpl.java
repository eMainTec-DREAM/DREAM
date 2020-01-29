package dream.part.rpt.ptusehist.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rpt.ptusehist.dao.PartRptPtUseHistDetailDAO;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistDetailDTO;
import dream.part.rpt.ptusehist.service.PartRptPtUseHistDetailService;

/**
 * 부품사용분석 - Detail Service implements
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="partRptPtUseHistDetailServiceTarget"
 * @spring.txbn id="partRptPtUseHistDetailService"
 * @spring.property name="partRptPtUseHistDetailDAO" ref="partRptPtUseHistDetailDAO"
 */
public class PartRptPtUseHistDetailServiceImpl implements PartRptPtUseHistDetailService
{
    private PartRptPtUseHistDetailDAO partRptPtUseHistDetailDAO = null;
    
	public PartRptPtUseHistDetailDAO getPartRptPtUseHistDetailDAO() {
		return partRptPtUseHistDetailDAO;
	}

	public void setPartRptPtUseHistDetailDAO(PartRptPtUseHistDetailDAO partRptPtUseHistDetailDAO) 
	{
		this.partRptPtUseHistDetailDAO = partRptPtUseHistDetailDAO;
	}

	@Override
	public List findPartDetailList(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception 
	{
        return partRptPtUseHistDetailDAO.findPartDetailList(partRptPtUseHistDetailDTO,user);
	}
	@Override
	public String findPartTotalCount(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception 
	{
		return partRptPtUseHistDetailDAO.findPartTotalCount(partRptPtUseHistDetailDTO, user);
	}
	
	@Override
	public List findEqDetailList(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception 
	{
		return partRptPtUseHistDetailDAO.findEqDetailList(partRptPtUseHistDetailDTO,user);
	}
	@Override
	public String findEqTotalCount(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception 
	{
		return partRptPtUseHistDetailDAO.findEqTotalCount(partRptPtUseHistDetailDTO, user);
	}
	
}
