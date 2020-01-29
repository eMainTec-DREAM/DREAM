package dream.part.rep.service;

import java.util.List;

import common.bean.User;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;

/**
 * 부품수리 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtRepListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param maPtRepCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPtRepCommonDTO maPtRepCommonDTO,User user);
    
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
    
    public String findTotalCount(MaPtRepCommonDTO maPtRepCommonDTO,User user);

    public int insertPtRepList(MaPtRepDetailDTO maPtRepDetailDTO, User user) throws Exception;

}
