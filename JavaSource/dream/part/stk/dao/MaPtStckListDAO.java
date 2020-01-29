package dream.part.stk.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.form.MaPtStckListForm;

/**
 * 자재재고 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtStckListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckCommonDTO
     * @return List
     */
    public List findPtStckList(MaPtStckCommonDTO maPtStckCommonDTO,User user);

    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePtStck(String compNo, String wcodeId, String partId);

    /**
     * req hdr create
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partId
     * @param user
     * @return
     */
    public int reqHdrPtStck(MaPtStckListForm maPtStckListForm, String partId, User user);

    /**
     * req dtl create
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partId
     * @param user
     * @return
     */
    public int reqDtlPtStck(MaPtStckListForm maPtStckListForm, String partId, String recQty, User user, String partGrade);
    
    public int insertQrCode(String id, String wcodeId,String compNo, User loginUser);
    
    public int insertListQrCode(MaPtStckCommonDTO maPtStckCommonDTO, User loginUser); 

	public int deleteQrCode(User loginUser) throws Exception;

    public String findTotalCount(MaPtStckCommonDTO maPtStckCommonDTO, User user) throws Exception;
}