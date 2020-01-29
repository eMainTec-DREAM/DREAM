package dream.doc.notice.service;

import common.bean.User;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDetailDTO;

/**
 * DocNotice Page - Detail Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface DocNoticeDetailService
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
     * INSERT
     * @param docNoticeDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(DocNoticeDetailDTO docNoticeDetailDTO, User user) throws Exception;
    /**
     * UPDATE
     * @param docNoticeDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(DocNoticeDetailDTO docNoticeDetailDTO, User user) throws Exception;
    /**
     * NOTICE
     * @param docNoticeDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int goNotice(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDetailDTO docNoticeDetailDTO, User user) throws Exception;

}
