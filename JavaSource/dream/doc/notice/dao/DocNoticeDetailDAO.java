package dream.doc.notice.dao;

import common.bean.User;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDetailDTO;

/**
 * DocNotice Page - Detail DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface DocNoticeDetailDAO
{
    /**
     * FIND DETAIL
     * @param docNoticeCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public DocNoticeDetailDTO findDetail(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param docNoticeDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(DocNoticeDetailDTO docNoticeDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param docNoticeDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(DocNoticeDetailDTO docNoticeDetailDTO, User user) throws Exception;

    /**
     * GO NOTICE
     * @param docNoticeDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int goNotice(DocNoticeDetailDTO docNoticeDetailDTO, User user) throws Exception;
    
    public int updateUserDetail(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception;
}
