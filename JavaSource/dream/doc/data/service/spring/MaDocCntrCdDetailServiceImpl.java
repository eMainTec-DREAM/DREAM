package dream.doc.data.service.spring;

import common.bean.User;
import dream.doc.data.dao.MaDocCntrCdDetailDAO;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;
import dream.doc.data.dto.MaDocCntrCdDetailDTO;
import dream.doc.data.service.MaDocCntrCdDetailService;

/**
 * 일반자료실 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maDocCntrCdDetailServiceTarget"
 * @spring.txbn id="maDocCntrCdDetailService"
 * @spring.property name="maDocCntrCdDetailDAO" ref="maDocCntrCdDetailDAO"
 */
public class MaDocCntrCdDetailServiceImpl implements MaDocCntrCdDetailService
{
    private MaDocCntrCdDetailDAO maDocCntrCdDetailDAO = null;
    
    public MaDocCntrCdDetailDAO getMaDocCntrCdDetailDAO() 
    {
		return maDocCntrCdDetailDAO;
	}

	public void setMaDocCntrCdDetailDAO(MaDocCntrCdDetailDAO maDocCntrCdDetailDAO) 
	{
		this.maDocCntrCdDetailDAO = maDocCntrCdDetailDAO;
	}

	public MaDocCntrCdDetailDTO findDetail(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User loginUser)
    {	    
        return maDocCntrCdDetailDAO.findDetail(maDocCntrCdCommonDTO, loginUser);
    }
    
	public int insertDetail(MaDocCntrCdDetailDTO maDocCntrCdDetailDTO, User loginUser) throws Exception
    {        
        return maDocCntrCdDetailDAO.insertDetail(maDocCntrCdDetailDTO, loginUser);
    }
	
	public int updateDetail(MaDocCntrCdDetailDTO maDocCntrCdDetailDTO, User loginUser) throws Exception
    {        
        return maDocCntrCdDetailDAO.updateDetail(maDocCntrCdDetailDTO, loginUser);
    }
}
