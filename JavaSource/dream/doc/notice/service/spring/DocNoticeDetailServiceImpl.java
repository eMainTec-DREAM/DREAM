package dream.doc.notice.service.spring;

import common.bean.User;
import dream.doc.notice.dao.DocNoticeDetailDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDetailDTO;
import dream.doc.notice.service.DocNoticeDetailService;

/**
 * DocNotice Page - Detail Service implements
 * @author youngjoo38
 * @version $Id: DocNoticeDetailServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="docNoticeDetailServiceTarget"
 * @spring.txbn id="docNoticeDetailService"
 * @spring.property name="docNoticeDetailDAO" ref="docNoticeDetailDAO"
 */
public class DocNoticeDetailServiceImpl implements DocNoticeDetailService
{
    private DocNoticeDetailDAO docNoticeDetailDAO = null;
    
    public DocNoticeDetailDTO findDetail(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception
    {
    	// TANOTIUSR Å×ÀÌºí UPDATE
    	docNoticeDetailDAO.updateUserDetail(docNoticeCommonDTO,user);

        return docNoticeDetailDAO.findDetail(docNoticeCommonDTO, user);
    }
    
    public int insertDetail(DocNoticeDetailDTO docNoticeDetailDTO, User user) throws Exception
    {
         return docNoticeDetailDAO.insertDetail(docNoticeDetailDTO, user);
    }
    
    public int updateDetail(DocNoticeDetailDTO docNoticeDetailDTO, User user) throws Exception
    {
    	return docNoticeDetailDAO.updateDetail(docNoticeDetailDTO, user);
    }

    public int goNotice(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDetailDTO docNoticeDetailDTO, User user) throws Exception
    {
         return docNoticeDetailDAO.goNotice(docNoticeDetailDTO, user);
    }
    public DocNoticeDetailDAO getDocNoticeDetailDAO() {
        return docNoticeDetailDAO;
    }

    public void setDocNoticeDetailDAO(DocNoticeDetailDAO docNoticeDetailDAO) {
        this.docNoticeDetailDAO = docNoticeDetailDAO;
    }
}
