package dream.consult.comp.warehouse.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;

/**
 * 자재재고 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtWhListService
{     
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @param maPtWhCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPtWhList(MaPtWhCommonDTO maPtWhCommonDTO, User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @param deleteRowsExt
     * @param deleteRowsExt1
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deletePtWh(String[] deleteRows, String[] deleteRowsExt, User user) throws Exception;

    public String findTotalCount(MaPtWhCommonDTO maPtWhCommonDTO, User user);
}
