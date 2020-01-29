package dream.doc.manual.service.spring;

import common.bean.User;
import dream.doc.manual.dao.DocManualDetailDAO;
import dream.doc.manual.dto.DocManualDetailDTO;
import dream.doc.manual.service.DocManualDetailService;

/**
 * 사용자매뉴얼 - 상세 serviceimpl 
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="docManualDetailServiceTarget"
 * @spring.txbn id="docManualDetailService"
 * @spring.property name="docManualDetailDAO" ref="docManualDetailDAO"
 */
public class DocManualDetailServiceImpl implements DocManualDetailService
{
    private DocManualDetailDAO docManualDetailDAO = null;
    
    public DocManualDetailDAO getDocManualDetailDAO() {
		return docManualDetailDAO;
	}

	public void setDocManualDetailDAO(DocManualDetailDAO docManualDetailDAO) {
		this.docManualDetailDAO = docManualDetailDAO;
	}

	public DocManualDetailDTO findDetail(String id, User user)throws Exception
    {
        return docManualDetailDAO.findDetail(id, user);
    }
	
	public int insertDetail(DocManualDetailDTO docManualDetailDTO, User user) throws Exception
    {        
        return docManualDetailDAO.insertDetail(docManualDetailDTO, user);
    }
	
	public int updateDetail(DocManualDetailDTO docManualDetailDTO, User user) throws Exception
    {        
        return docManualDetailDAO.updateDetail(docManualDetailDTO, user);
    }
}
