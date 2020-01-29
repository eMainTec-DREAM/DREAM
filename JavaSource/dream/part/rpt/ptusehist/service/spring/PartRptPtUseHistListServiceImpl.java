package dream.part.rpt.ptusehist.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rpt.ptusehist.dao.PartRptPtUseHistListDAO;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistCommonDTO;
import dream.part.rpt.ptusehist.service.PartRptPtUseHistListService;

/**
 * 부품 사용 분석 - List Service implements
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="partRptPtUseHistListServiceTarget"
 * @spring.txbn id="partRptPtUseHistListService"
 * @spring.property name="partRptPtUseHistListDAO" ref="partRptPtUseHistListDAO"
 */
public class PartRptPtUseHistListServiceImpl implements PartRptPtUseHistListService
{
	private PartRptPtUseHistListDAO partRptPtUseHistListDAO = null;

	public PartRptPtUseHistListDAO getPartRptPtUseHistListDAO() {
		return partRptPtUseHistListDAO;
	}
	public void setPartRptPtUseHistListDAO(PartRptPtUseHistListDAO partRptPtUseHistListDAO) {
		this.partRptPtUseHistListDAO = partRptPtUseHistListDAO;
	}
	
	public List findPtUseList(PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO, User user) throws Exception
    {      
        return partRptPtUseHistListDAO.findPtUseList(partRptPtUseHistCommonDTO,user);
    }
	public String findTotalCount(PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO,User user) throws Exception
    {
        return partRptPtUseHistListDAO.findTotalCount(partRptPtUseHistCommonDTO, user);
    }
}

