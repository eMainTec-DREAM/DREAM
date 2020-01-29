package dream.doc.notice.service.spring;

import common.bean.User;
import dream.doc.notice.dao.DocNoticeDeptDetailDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDeptDetailDTO;
import dream.doc.notice.dto.DocNoticeDeptListDTO;
import dream.doc.notice.service.DocNoticeDeptDetailService;

/**
 * DocNoticeDept - Detail Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="docNoticeDeptDetailServiceTarget"
 * @spring.txbn id="docNoticeDeptDetailService"
 * @spring.property name="docNoticeDeptDetailDAO" ref="docNoticeDeptDetailDAO"
 */
public class DocNoticeDeptDetailServiceImpl implements DocNoticeDeptDetailService
{
    private DocNoticeDeptDetailDAO docNoticeDeptDetailDAO = null;
    
    public DocNoticeDeptDetailDTO findDetail(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDeptListDTO docNoticeDeptListDTO, User user) throws Exception
    {
    	return docNoticeDeptDetailDAO.findDetail(docNoticeCommonDTO,docNoticeDeptListDTO, user);
    }
    
    public int insertDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeDeptDetailDTO docNoticeDeptDetailDTO, User user) throws Exception
    {
//    	return docNoticeDeptDetailDAO.insertDetail(docNoticeCommonDTO,docNoticeDeptDetailDTO, user);
    	
    	docNoticeDeptDetailDAO.insertDetail(docNoticeCommonDTO,docNoticeDeptDetailDTO, user);
    	
    	// 선택한 부서의 모든 사원을 TANOTIUSR에도 INSERT 한다.
    	docNoticeDeptDetailDAO.insertNotiUsrDetail(docNoticeCommonDTO,docNoticeDeptDetailDTO, user);
    	
    	return 0;
    }
    
    public int updateDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeDeptDetailDTO docNoticeDeptDetailDTO, User user) throws Exception
    {
//    	return docNoticeDeptDetailDAO.updateDetail(docNoticeCommonDTO,docNoticeDeptDetailDTO, user);
    	docNoticeDeptDetailDAO.updateDetail(docNoticeCommonDTO,docNoticeDeptDetailDTO, user);
    	
    	// 선택한 부서의 모든 사원을 TANOTIUSR에도 INSERT 한다.
    	docNoticeDeptDetailDAO.insertNotiUsrAgain(docNoticeCommonDTO,docNoticeDeptDetailDTO, user);
    	
    	return 0;

    }

	public DocNoticeDeptDetailDAO getDocNoticeDeptDetailDAO() {
		return docNoticeDeptDetailDAO;
	}

	public void setDocNoticeDeptDetailDAO(DocNoticeDeptDetailDAO docNoticeDeptDetailDAO) {
		this.docNoticeDeptDetailDAO = docNoticeDeptDetailDAO;
	}

	@Override
	public String checkDetail(DocNoticeDeptDetailDTO docNoticeDeptDetailDTO, User user) throws Exception {
		return docNoticeDeptDetailDAO.checkDetail(docNoticeDeptDetailDTO, user);
	}
    

}
