package dream.part.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.list.dto.MaPtMstrStockListDTO;
import dream.part.list.form.MaPtMstrStockListForm;

/**
 * 자재재고 - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtMstrStockListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrStockListDTO
     * @return List
     */
    public List findPtMstrStockList(MaPtMstrStockListDTO maPtMstrStockListDTO,User user);
    
    public String findTotalCount(MaPtMstrStockListDTO maPtMstrStockListDTO, User user) throws Exception;
}