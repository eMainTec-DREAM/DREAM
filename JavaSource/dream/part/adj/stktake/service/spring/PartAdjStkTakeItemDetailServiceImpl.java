package dream.part.adj.stktake.service.spring;

import common.bean.User;
import dream.part.adj.stktake.dao.PartAdjStkTakeItemDetailDAO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeItemDetailDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;
import dream.part.adj.stktake.service.PartAdjStkTakeItemDetailService;

/**
 * 부품실사 item - 상세
 * @author kim2107
 * @version $Id: PartAdjStkTakeDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="partAdjStkTakeItemDetailServiceTarget"
 * @spring.txbn id="partAdjStkTakeItemDetailService"
 * @spring.property name="partAdjStkTakeItemDetailDAO" ref="partAdjStkTakeItemDetailDAO"
 */
public class PartAdjStkTakeItemDetailServiceImpl implements PartAdjStkTakeItemDetailService
{
    private PartAdjStkTakeItemDetailDAO partAdjStkTakeItemDetailDAO = null;
    
    public PartAdjStkTakeItemDetailDAO getPartAdjStkTakeItemDetailDAO() {
		return partAdjStkTakeItemDetailDAO;
	}

	public void setPartAdjStkTakeItemDetailDAO(PartAdjStkTakeItemDetailDAO partAdjStkTakeItemDetailDAO) {
		this.partAdjStkTakeItemDetailDAO = partAdjStkTakeItemDetailDAO;
	}

	public PartAdjStkTakeItemDetailDTO findDetail(PartAdjStkTakeListDTO partAdjStkTakeListDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)throws Exception
    {
        return partAdjStkTakeItemDetailDAO.findDetail(partAdjStkTakeListDTO, partAdjStkTakeCommonDTO, user);
    }
    
	public int updateDetail(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception
    {        
		partAdjStkTakeItemDetailDAO.updateDetail(partAdjStkTakeItemDetailDTO, partAdjStkTakeCommonDTO, user);
		partAdjStkTakeItemDetailDAO.insertGap(partAdjStkTakeItemDetailDTO, partAdjStkTakeCommonDTO, user);
        return 0;
    }
	public int insertDetail(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception
    {
		// 품번, 등급 중복 확인
		String cnt = this.validItem(partAdjStkTakeItemDetailDTO, partAdjStkTakeCommonDTO, user);
        
        if("0".equals(cnt))
        {
			partAdjStkTakeItemDetailDAO.insertDetail( partAdjStkTakeItemDetailDTO, partAdjStkTakeCommonDTO, user);
			partAdjStkTakeItemDetailDAO.insertGap(partAdjStkTakeItemDetailDTO, partAdjStkTakeCommonDTO, user);
        }
        
        return 0;
    }

    public String validItem(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception
    {
    	return partAdjStkTakeItemDetailDAO.validItem(partAdjStkTakeItemDetailDTO, partAdjStkTakeCommonDTO, user);
    }
}
