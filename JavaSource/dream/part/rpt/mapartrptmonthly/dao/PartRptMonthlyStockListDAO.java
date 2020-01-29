package dream.part.rpt.mapartrptmonthly.dao;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mapartrptmonthly.dto.PartRptMonthlyStockListDTO;

/**
 * 부품수불부 요약 DAO
 * @author  euna0207
 * @version $Id: PartRptMonthlyStockListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 */
public interface PartRptMonthlyStockListDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: PartRptMonthlyStockListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     * 
     * @param partRptMonthlyStockListDTO
     * @return List
     */
    public List findList(PartRptMonthlyStockListDTO partRptMonthlyStockListDTO, User user);
    /**
     * FIND TOTAL LIST
     * @param partRptMonthlyStockListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(PartRptMonthlyStockListDTO partRptMonthlyStockListDTO, User user) throws Exception;
 

}