package dream.part.stk.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.part.stk.dao.PartStkCurrentDAO;
import dream.part.stk.dto.PartStkCurrentDTO;
import dream.part.stk.service.PartStkCurrentService;

/**
 * ¸ñ·Ï
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="partStkCurrentServiceTarget"
 * @spring.txbn id="partStkCurrentService"
 * @spring.property name="partStkCurrentDAO" ref="partStkCurrentDAO"
 */
public class PartStkCurrentServiceImpl implements PartStkCurrentService
{
    private PartStkCurrentDAO partStkCurrentDAO = null;

	public PartStkCurrentDAO getPartStkCurrentDAO() {
		return partStkCurrentDAO;
	}

	public void setPartStkCurrentDAO(PartStkCurrentDAO partStkCurrentDAO) {
		this.partStkCurrentDAO = partStkCurrentDAO;
	}

	@Override
	public List findPtStckList(PartStkCurrentDTO partStkCurrentDTO, User user) throws Exception {
		return partStkCurrentDAO.findPtStckList(partStkCurrentDTO, user);
	}
    
	@Override
	public String findTotalCount(PartStkCurrentDTO partStkCurrentDTO, User user) throws Exception {
		return partStkCurrentDAO.findTotalCount(partStkCurrentDTO, user);
	}

	@Override
	public PartStkCurrentDTO findDetail(PartStkCurrentDTO partStkCurrentDTO, User user) throws Exception {
		return (PartStkCurrentDTO)CommonUtil.makeDetailFromList(partStkCurrentDAO.findPtStckList(partStkCurrentDTO, user), partStkCurrentDTO);
	}

}

