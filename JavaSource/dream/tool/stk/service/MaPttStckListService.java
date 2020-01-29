package dream.tool.stk.service;

import java.util.List;

import common.bean.User;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.form.MaPttStckListForm;

/**
 * 자재재고 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPttStckListService
{     
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @param maPttStckCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPtStckList(MaPttStckCommonDTO maPttStckCommonDTO);
    
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
    public int deletePtStck(String compNo, String[] deleteRows, String[] deleteRowsExt, User loginUser) throws Exception;
    /**
     * req
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckListForm
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int reqPtStck(MaPttStckListForm maPttStckListForm, User loginUser) throws Exception;
    
    public String findTotalCount(MaPttStckCommonDTO maPttStckCommonDTO, User user);
}
