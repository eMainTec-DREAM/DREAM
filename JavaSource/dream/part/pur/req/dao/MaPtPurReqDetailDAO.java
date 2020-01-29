package dream.part.pur.req.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;

/**
 * 부품수리 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.2
 */
public interface MaPtPurReqDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtReqCommonDTO111
     * @param loginUser
     * @return
     */
    public MaPtPurReqDetailDTO findDetail(MaPtReqCommonDTO maPtReqCommonDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailDTO
     * @return
     */
    public int insertDetail(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser);
    
    /**
     * detail 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailDTO
     * @return
     */
    public int updateDetail(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser);
    
    /**
     * update 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailDTO
     * @return
     */
    public int updateStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser);

    /**
     * update rec
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailDTO
     * @return
     */
    public int recStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser);
    /**
     * 작성상태 확인
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param id
     * @param user
     * @return
     */
    public String chkPurStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User user);
    /**
     * 작성자 확인
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param id
     * @param user
     * @return
     */
    public String chkDelUser(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User user);

	public int recCancel(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser);
}