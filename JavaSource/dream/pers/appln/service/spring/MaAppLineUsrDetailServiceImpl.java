package dream.pers.appln.service.spring;

import dream.pers.appln.dao.MaAppLineUsrDetailDAO;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrDetailDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;
import dream.pers.appln.service.MaAppLineUsrDetailService;

/**
 * 
 * @author kim2107
 * @version $Id: MaAppLineUsrDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maAppLineUsrDetailServiceTarget"
 * @spring.txbn id="maAppLineUsrDetailService"
 * @spring.property name="maAppLineUsrDetailDAO" ref="maAppLineUsrDetailDAO"
 */
public class MaAppLineUsrDetailServiceImpl implements MaAppLineUsrDetailService
{
    private MaAppLineUsrDetailDAO maAppLineUsrDetailDAO = null;
    
    public MaAppLineUsrDetailDAO getMaAppLineUsrDetailDAO() {
		return maAppLineUsrDetailDAO;
	}

	public void setMaAppLineUsrDetailDAO(MaAppLineUsrDetailDAO maAppLineUsrDetailDAO) {
		this.maAppLineUsrDetailDAO = maAppLineUsrDetailDAO;
	}

	public MaAppLineUsrDetailDTO findDetail(MaAppLineUsrListDTO maAppLineUsrListDTO, MaAppLineCommonDTO maAppLineCommonDTO)throws Exception
    {
        return maAppLineUsrDetailDAO.findDetail(maAppLineUsrListDTO, maAppLineCommonDTO);
    }
    
	public int updateDetail(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO) throws Exception
    {        
        return maAppLineUsrDetailDAO.updateDetail(maAppLineUsrDetailDTO, maAppLineCommonDTO);
    }
	public int insertDetail(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO) throws Exception
    {        
        return maAppLineUsrDetailDAO.insertDetail( maAppLineUsrDetailDTO, maAppLineCommonDTO);
    }
	public String checkAppSeq(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO) throws Exception
    {        
        return maAppLineUsrDetailDAO.checkAppSeq( maAppLineUsrDetailDTO, maAppLineCommonDTO);
    }
	
	public String appSeq(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO) throws Exception
    {        
        return maAppLineUsrDetailDAO.appSeq( maAppLineUsrDetailDTO, maAppLineCommonDTO);
    }
}
