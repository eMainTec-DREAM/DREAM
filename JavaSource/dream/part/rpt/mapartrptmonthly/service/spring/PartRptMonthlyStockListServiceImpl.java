package dream.part.rpt.mapartrptmonthly.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mapartrptmonthly.dao.PartRptMonthlyStockListDAO;
import dream.part.rpt.mapartrptmonthly.dto.PartRptMonthlyStockListDTO;
import dream.part.rpt.mapartrptmonthly.service.PartRptMonthlyStockListService;

/**
 * 부품수불부 요약 serviceimpl
 * @author euna0207
 * @version $Id: PartRptMonthlyStockListServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="partRptMonthlyStockListServiceTarget"
 * @spring.txbn id="partRptMonthlyStockListService"
 * @spring.property name="partRptMonthlyStockListDAO" ref="partRptMonthlyStockListDAO"
 */
public class PartRptMonthlyStockListServiceImpl implements PartRptMonthlyStockListService
{
    private PartRptMonthlyStockListDAO partRptMonthlyStockListDAO = null;

    public PartRptMonthlyStockListDAO getPartRptMonthlyStockListDAO() {
		return partRptMonthlyStockListDAO;
	}

	public void setPartRptMonthlyStockListDAO(PartRptMonthlyStockListDAO partRptMonthlyStockListDAO) {
		this.partRptMonthlyStockListDAO = partRptMonthlyStockListDAO;
	}

	public List findList(PartRptMonthlyStockListDTO partRptMonthlyStockListDTO, User user)
    {      
        return partRptMonthlyStockListDAO.findList(partRptMonthlyStockListDTO,user);
    }

	@Override
	public String findTotalCount(PartRptMonthlyStockListDTO partRptMonthlyStockListDTO, User user) throws Exception {

		return partRptMonthlyStockListDAO.findTotalCount(partRptMonthlyStockListDTO, user);
	}
}