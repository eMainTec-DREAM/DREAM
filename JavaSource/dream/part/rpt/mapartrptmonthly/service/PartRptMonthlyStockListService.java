package dream.part.rpt.mapartrptmonthly.service;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mapartrptmonthly.dto.PartRptMonthlyStockListDTO;

/**
 * 부품수불부 요약 service
 * @author  euna0207
 * @version $Id: PartRptMonthlyStockListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since   1.0
 */
public interface PartRptMonthlyStockListService
{     
    /**
     * grid find
     * @author  euna0207
     * @version $Id: PartRptMonthlyStockListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @param partRptMonthlyStockListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(PartRptMonthlyStockListDTO partRptMonthlyStockListDTO,User user);  
    
    /**
     * find Total Count
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param partRptMonthlyStockListDTO
     * @return
     */
    public String findTotalCount(PartRptMonthlyStockListDTO partRptMonthlyStockListDTO, User user) throws Exception;
}
