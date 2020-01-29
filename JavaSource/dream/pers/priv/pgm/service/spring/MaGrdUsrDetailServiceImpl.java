package dream.pers.priv.pgm.service.spring;

import common.bean.User;
import dream.pers.priv.pgm.dao.MaGrdUsrDetailDAO;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;
import dream.pers.priv.pgm.service.MaGrdUsrDetailService;

/**
 * Ä®·³ »ó¼¼
 * @author jung7126
 * @version $Id: MaGrdUsrDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maGrdUsrDetailServiceTarget"
 * @spring.txbn id="maGrdUsrDetailService"
 * @spring.property name="maGrdUsrDetailDAO" ref="maGrdUsrDetailDAO"
 */
public class MaGrdUsrDetailServiceImpl implements MaGrdUsrDetailService
{
    private MaGrdUsrDetailDAO maGrdUsrDetailDAO = null;
    
    public MaGrdUsrDetailDAO getMaGrdUsrDetailDAO() {
		return maGrdUsrDetailDAO;
	}

	public void setMaGrdUsrDetailDAO(MaGrdUsrDetailDAO maGrdUsrDetailDAO) {
		this.maGrdUsrDetailDAO = maGrdUsrDetailDAO;
	}

	public MaGrdUsrDetailDTO findDetail(MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user)throws Exception
    {
	    MaGrdUsrDetailDTO resultDTO = null;

	    resultDTO = maGrdUsrDetailDAO.findUsrDetail(maGrdUsrCommonDTO, user);

//	    if(resultDTO.getPgGridId() == "") resultDTO = maGrdUsrDetailDAO.findDetail(maGrdUsrCommonDTO, user);

	    
	    return resultDTO;
    }
    
	public int updateDetail(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user) throws Exception
    {        
        return maGrdUsrDetailDAO.updateDetail(maGrdUsrDetailDTO, maGrdUsrCommonDTO, user);
    }
	public int insertDetail(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user) throws Exception
    {        
        return maGrdUsrDetailDAO.insertDetail( maGrdUsrDetailDTO, maGrdUsrCommonDTO, user);
    }
}
