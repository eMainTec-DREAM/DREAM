package dream.tool.rec.service;

import java.util.List;

import common.bean.User;
import dream.tool.rec.dto.MaPttRecCommonDTO;

/**
 * 구매입고 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPttRecListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param maPttRecCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPttRecCommonDTO maPttRecCommonDTO, User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;
    
    public String findTotalCount(MaPttRecCommonDTO maPttRecCommonDTO, User user) throws Exception;

}
