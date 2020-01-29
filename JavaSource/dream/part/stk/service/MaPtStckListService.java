package dream.part.stk.service;

import java.util.List;

import common.bean.User;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.form.MaPtStckListForm;

/**
 * 자재재고 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtStckListService
{     
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @param maPtStckCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPtStckList(MaPtStckCommonDTO maPtStckCommonDTO, User user);
    
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
    public int reqPtStck(MaPtStckListForm maPtStckListForm, User loginUser) throws Exception;

    /**
     * req
     * @author pochul2423
     * @version $Id:$
     * @since   1.0
     * 
     * @param selectRows
     * @param compNo
     * @param user
     * @throws Exception
     * @return
     */
	public int insertQrCode(String[] selectRows, String[] wcodeId,String compNo, User user) throws Exception;
	public int insertListQrCode(MaPtStckCommonDTO maPtStckCommonDTO, User user) throws Exception;

    public String findTotalCount(MaPtStckCommonDTO maPtStckCommonDTO, User user) throws Exception;
}
