package dream.cert.list.service.spring;

import common.bean.User;
import dream.cert.list.dao.CertDetailDAO;
import dream.cert.list.dto.CertDetailDTO;
import dream.cert.list.service.CertDetailService;

/**
 * 자격증분류 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="certDetailServiceTarget"
 * @spring.txbn id="certDetailService"
 * @spring.property name="certDetailDAO" ref="certDetailDAO"
 */
public class CertDetailServiceImpl implements CertDetailService
{
    private CertDetailDAO certDetailDAO = null;
    
    public CertDetailDAO getCertDetailDAO() 
    {
		return certDetailDAO;
	}

	public void setCertDetailDAO(CertDetailDAO certDetailDAO) 
	{
		this.certDetailDAO = certDetailDAO;
	}

	public CertDetailDTO findDetail(User user, String certListId)throws Exception
    {
        CertDetailDTO certDetailDTO = certDetailDAO.findDetail(user, certListId);
        
        return certDetailDTO;
    }
	
	public int insertDetail(CertDetailDTO certDetailDTO) throws Exception
    {   
        return certDetailDAO.insertDetail(certDetailDTO);
    }
	
	public int updateDetail(CertDetailDTO certDetailDTO) throws Exception
    {   
	    int resultCnt = 0;
	    String compNo = certDetailDTO.getCompNo();
	    String prRecListId = certDetailDTO.getCertListId();
	    

	    resultCnt = certDetailDAO.updateDetail(certDetailDTO);

	    
        return resultCnt;
    }
	

}
