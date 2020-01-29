package dream.pers.priv.pgm.service.spring;

import common.bean.User;

import dream.pers.priv.pgm.dao.PersPrivUsrFieldDetailDAO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldDetailDTO;
import dream.pers.priv.pgm.service.PersPrivUsrFieldDetailService;

/**
 * 화면별 필드 상세
 * @author kim2107
 * @version $Id: MaPgUsrFieldDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="persPrivUsrFieldDetailServiceTarget"
 * @spring.txbn id="persPrivUsrFieldDetailService"
 * @spring.property name="persPrivUsrFieldDetailDAO" ref="persPrivUsrFieldDetailDAO"
 */
public class PersPrivUsrFieldDetailServiceImpl implements PersPrivUsrFieldDetailService
{
    private PersPrivUsrFieldDetailDAO persPrivUsrFieldDetailDAO = null;
    
    
	public PersPrivUsrFieldDetailDAO getPersPrivUsrFieldDetailDAO() {
		return persPrivUsrFieldDetailDAO;
	}


	public void setPersPrivUsrFieldDetailDAO(
			PersPrivUsrFieldDetailDAO persPrivUsrFieldDetailDAO) {
		this.persPrivUsrFieldDetailDAO = persPrivUsrFieldDetailDAO;
	}


	public PersPrivUsrFieldDetailDTO findDetail(PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO, User user)
    {
        return persPrivUsrFieldDetailDAO.findDetail(persPrivUsrFieldDetailDTO, user);
    }
	
    
	public int updateDetail(PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO, PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO) throws Exception
    {        
		return persPrivUsrFieldDetailDAO.updateDetail(persPrivUsrFieldDetailDTO, persPrivUsrFieldCommonDTO);
    }
	
	public String insertDetail(PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO, User user) throws Exception
    {   
	    String usrPgFieldId = persPrivUsrFieldDetailDAO.getNextSequence("SQAUSRPGFIELD_ID");
	    persPrivUsrFieldDetailDTO.setUsrPgFieldId(usrPgFieldId);
	    
	    persPrivUsrFieldDetailDAO.insertDetail( persPrivUsrFieldDetailDTO, user);
        
        return usrPgFieldId;
    }
}
