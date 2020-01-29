package dream.pers.priv.pgm.service.spring;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdUsrColDetailDTO;
import dream.pers.priv.pgm.dao.MaGrdUsrColDetailDAO;
import dream.pers.priv.pgm.dao.MaGrdUsrDetailDAO;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;
import dream.pers.priv.pgm.service.MaGrdUsrColDetailService;

/**
 * Ä®·³ »ó¼¼
 * @author jung7126
 * @version $Id: MaGrdUsrColDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maGrdUsrColDetailServiceTarget"
 * @spring.txbn id="maGrdUsrColDetailService"
 * @spring.property name="maGrdUsrColDetailDAO" ref="maGrdUsrColDetailDAO"
 * @spring.property name="maGrdUsrDetailDAO" ref="maGrdUsrDetailDAO"
 */
public class MaGrdUsrColDetailServiceImpl implements MaGrdUsrColDetailService
{
    private MaGrdUsrColDetailDAO maGrdUsrColDetailDAO = null;

    private MaGrdUsrDetailDAO maGrdUsrDetailDAO = null;
    
    public MaGrdUsrDetailDAO getMaGrdUsrDetailDAO() {
        return maGrdUsrDetailDAO;
    }

    public void setMaGrdUsrDetailDAO(MaGrdUsrDetailDAO maGrdUsrDetailDAO) {
        this.maGrdUsrDetailDAO = maGrdUsrDetailDAO;
    }
    
    public MaGrdUsrColDetailDAO getMaGrdUsrColDetailDAO() {
		return maGrdUsrColDetailDAO;
	}

	public void setMaGrdUsrColDetailDAO(MaGrdUsrColDetailDAO maGrdUsrColDetailDAO) {
		this.maGrdUsrColDetailDAO = maGrdUsrColDetailDAO;
	}

	public MaGrdUsrColDetailDTO findDetail(String pgGridColId, String usrPgGridColId, User user)throws Exception
    {
	    MaGrdUsrColDetailDTO resultDTO;
	    if(!usrPgGridColId.equals(""))
	        resultDTO = maGrdUsrColDetailDAO.findUsrDetail(pgGridColId, usrPgGridColId, user);
	    else
	        resultDTO = maGrdUsrColDetailDAO.findDetail(pgGridColId, usrPgGridColId, user);

	    
	    return resultDTO;
    }
    
	public int updateDetail(MaGrdUsrColDetailDTO maGrdUsrColDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO) throws Exception
    {        
        return maGrdUsrColDetailDAO.updateDetail(maGrdUsrColDetailDTO, maGrdUsrCommonDTO);
    }
	public int insertDetail(MaGrdUsrColDetailDTO maGrdUsrColDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user, MaGrdUsrDetailDTO maGrdUsrDetailDTO) throws Exception
    {        
	    MaGrdUsrDetailDTO resultDTO = maGrdUsrDetailDAO.findUsrDetail(maGrdUsrCommonDTO, user);

	    if(resultDTO.getUsrPgGridId().equals(""))
	    {
	        maGrdUsrDetailDAO.insertDetail(maGrdUsrDetailDTO, maGrdUsrCommonDTO, user);
	    }
	    
        return maGrdUsrColDetailDAO.insertDetail( maGrdUsrColDetailDTO, maGrdUsrCommonDTO, user);
    }
}
