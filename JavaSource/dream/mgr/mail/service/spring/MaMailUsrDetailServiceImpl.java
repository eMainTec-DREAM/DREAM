package dream.mgr.mail.service.spring;

import dream.mgr.mail.dao.MaMailUsrDetailDAO;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrDetailDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;
import dream.mgr.mail.service.MaMailUsrDetailService;

/**
 * 메일수신자설정 - 수신자
 * @author kim2107
 * @version $Id: MaMailUsrDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maMailUsrDetailServiceTarget"
 * @spring.txbn id="maMailUsrDetailService"
 * @spring.property name="maMailUsrDetailDAO" ref="maMailUsrDetailDAO"
 */
public class MaMailUsrDetailServiceImpl implements MaMailUsrDetailService
{
    private MaMailUsrDetailDAO maMailUsrDetailDAO = null;
    
    public MaMailUsrDetailDAO getMaMailUsrDetailDAO() {
		return maMailUsrDetailDAO;
	}

	public void setMaMailUsrDetailDAO(MaMailUsrDetailDAO maMailUsrDetailDAO) {
		this.maMailUsrDetailDAO = maMailUsrDetailDAO;
	}

	public MaMailUsrDetailDTO findDetail(MaMailUsrListDTO maMailUsrListDTO, MaMailCommonDTO maMailCommonDTO)throws Exception
    {
        return maMailUsrDetailDAO.findDetail(maMailUsrListDTO, maMailCommonDTO);
    }
    
	public int updateDetail(MaMailUsrDetailDTO maMailUsrDetailDTO, MaMailCommonDTO maMailCommonDTO) throws Exception
    {        
        return maMailUsrDetailDAO.updateDetail(maMailUsrDetailDTO, maMailCommonDTO);
    }
	public int insertDetail(MaMailUsrDetailDTO maMailUsrDetailDTO, MaMailCommonDTO maMailCommonDTO) throws Exception
    {        
        return maMailUsrDetailDAO.insertDetail( maMailUsrDetailDTO, maMailCommonDTO);
    }
}
