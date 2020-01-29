package dream.pers.priv.notice.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.notice.dto.PersPrivNoticeDetailDTO;

/**
 * Notice 설정 - 상세 Dao
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 */
public interface PersPrivNoticeDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @param user 
     * @return
     */
    public PersPrivNoticeDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user);

    /**
     * detail insert
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivNoticeDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int insertDetail(PersPrivNoticeDetailDTO persPrivNoticeDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user);
    
    /**
     * detail update
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivNoticeDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int updateDetail(PersPrivNoticeDetailDTO persPrivNoticeDetailDTO, User user);
}