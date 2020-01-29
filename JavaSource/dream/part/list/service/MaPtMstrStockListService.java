package dream.part.list.service;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.MaPtMstrStockListDTO;
import dream.part.list.form.MaPtMstrStockListForm;

/**
 * ������� - ��� service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtMstrStockListService
{     
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @param maPtMstrStockListDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findPtMstrStockList(MaPtMstrStockListDTO maPtMstrStockListDTO, User user);
    
    public String findTotalCount(MaPtMstrStockListDTO maPtMstrStockListDTO, User user) throws Exception;
}
