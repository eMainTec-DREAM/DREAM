package dream.doc.file.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.file.FileUploadUtil;
import common.finder.valid.dao.ValidationDAO;
import dream.doc.file.dao.DocFileLovDAO;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.service.DocFileLovService;


/**
 * 첨부파일 Lov - 목록 serviceimpl
 * @author jung7126
 * @version $Id: DocFileLovServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="docFileLovServiceTarget"
 * @spring.txbn id="docFileLovService"
 * @spring.property name="docFileLovDAO" ref="docFileLovDAO"
 * @spring.property name="validationDAO" ref="validationDAO"
 */
public class DocFileLovServiceImpl implements DocFileLovService
{
    private DocFileLovDAO docFileLovDAO = null;

    private ValidationDAO validationDAO = null;
    
    
    public ValidationDAO getValidationDAO()
    {
        return validationDAO;
    }

    public void setValidationDAO(ValidationDAO validationDAO)
    {
        this.validationDAO = validationDAO;
    }

    public DocFileLovDAO getDocFileLovDAO() {
		return docFileLovDAO;
	}

	public void setDocFileLovDAO(DocFileLovDAO docFileLovDAO) {
		this.docFileLovDAO = docFileLovDAO;
	}

	public List findList(MaDocLibCommonDTO maDocLibCommonDTO,User user)
    {      
        return docFileLovDAO.findList(maDocLibCommonDTO, user);
    }
}

