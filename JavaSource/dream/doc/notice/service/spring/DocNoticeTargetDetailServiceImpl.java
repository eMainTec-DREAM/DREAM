package dream.doc.notice.service.spring;

import common.bean.User;
import dream.doc.notice.dao.DocNoticeTargetDetailDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeTargetDetailDTO;
import dream.doc.notice.dto.DocNoticeTargetListDTO;
import dream.doc.notice.service.DocNoticeTargetDetailService;

/**
 * DocNoticeTarget - Detail Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="docNoticeTargetDetailServiceTarget"
 * @spring.txbn id="docNoticeTargetDetailService"
 * @spring.property name="docNoticeTargetDetailDAO" ref="docNoticeTargetDetailDAO"
 */
public class DocNoticeTargetDetailServiceImpl implements DocNoticeTargetDetailService
{
    private DocNoticeTargetDetailDAO docNoticeTargetDetailDAO = null;
    
    public DocNoticeTargetDetailDTO findDetail(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeTargetListDTO docNoticeTargetListDTO, User user) throws Exception
    {
    	return docNoticeTargetDetailDAO.findDetail(docNoticeCommonDTO,docNoticeTargetListDTO, user);
    }
    
    public int insertDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeTargetDetailDTO docNoticeTargetDetailDTO, User user) throws Exception
    {
     	return docNoticeTargetDetailDAO.insertDetail(docNoticeCommonDTO,docNoticeTargetDetailDTO, user);
    }
    
    public int updateDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeTargetDetailDTO docNoticeTargetDetailDTO, User user) throws Exception
    {
     	return docNoticeTargetDetailDAO.updateDetail(docNoticeCommonDTO,docNoticeTargetDetailDTO, user);
    }

	public DocNoticeTargetDetailDAO getDocNoticeTargetDetailDAO() {
		return docNoticeTargetDetailDAO;
	}

	public void setDocNoticeTargetDetailDAO(DocNoticeTargetDetailDAO docNoticeTargetDetailDAO) {
		this.docNoticeTargetDetailDAO = docNoticeTargetDetailDAO;
	}
    

}
