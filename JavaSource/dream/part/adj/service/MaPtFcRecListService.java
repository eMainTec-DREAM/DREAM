package dream.part.adj.service;

import java.util.List;

import common.bean.User;
import dream.part.adj.dto.MaPtFcRecCommonDTO;
import dream.part.adj.dto.MaPtFcRecDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;

/**
 * 무상입고 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtFcRecListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param maPtFcRecCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPtFcRecCommonDTO maPtFcRecCommonDTO,User user);
    
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

    public int insertPtFcRec(MaPtFcRecDetailDTO maPtFcRecDetailDTO, User user) throws Exception;
    
    public String findTotalCount(MaPtFcRecCommonDTO maPtFcRecCommonDTO, User user) throws Exception;
}
