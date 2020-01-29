package dream.tool.stk.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.form.MaPttStckListForm;

/**
 * 자재재고 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPttStckListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckCommonDTO
     * @return List
     */
    public List findPtStckList(MaPttStckCommonDTO maPttStckCommonDTO);
    
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
    public int reqHdrPtStck(MaPttStckListForm maPttStckListForm, String wcodeId, String partId,User user);

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
    public int reqDtlPtStck(MaPttStckListForm maPttStckListForm, String partId, User user);
    
    public String findTotalCount(MaPttStckCommonDTO maPttStckCommonDTO, User user);
}