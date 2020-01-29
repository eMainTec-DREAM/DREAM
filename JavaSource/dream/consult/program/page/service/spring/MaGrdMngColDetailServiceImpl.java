package dream.consult.program.page.service.spring;

import common.bean.User;
import dream.consult.program.page.dao.MaGrdMngColDetailDAO;
import dream.consult.program.page.dto.MaGrdMngColDetailDTO;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.service.MaGrdMngColDetailService;

/**
 * Ä®·³ »ó¼¼
 * @author jung7126
 * @version $Id: MaGrdMngColDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maGrdMngColDetailServiceTarget"
 * @spring.txbn id="maGrdMngColDetailService"
 * @spring.property name="maGrdMngColDetailDAO" ref="maGrdMngColDetailDAO"
 */
public class MaGrdMngColDetailServiceImpl implements MaGrdMngColDetailService
{
    private MaGrdMngColDetailDAO maGrdMngColDetailDAO = null;
    
    public MaGrdMngColDetailDAO getMaGrdMngColDetailDAO() {
		return maGrdMngColDetailDAO;
	}

	public void setMaGrdMngColDetailDAO(MaGrdMngColDetailDAO maGrdMngColDetailDAO) {
		this.maGrdMngColDetailDAO = maGrdMngColDetailDAO;
	}

	public MaGrdMngColDetailDTO findDetail(String pageId, String grdColId, User user)throws Exception
    {
        return maGrdMngColDetailDAO.findDetail(pageId, grdColId, user);
    }
    
	public int updateDetail(MaGrdMngColDetailDTO maGrdMngColDetailDTO, MaGrdMngCommonDTO maGrdMngCommonDTO) throws Exception
    {        
        return maGrdMngColDetailDAO.updateDetail(maGrdMngColDetailDTO, maGrdMngCommonDTO);
    }
	public int insertDetail(MaGrdMngColDetailDTO maGrdMngColDetailDTO, MaGrdMngCommonDTO maGrdMngCommonDTO) throws Exception
    {        
	    if(!"".equals(maGrdMngColDetailDTO.getPageId()) && !"".equals(maGrdMngColDetailDTO.getGridObjId())) {
	        String pgGridId = maGrdMngColDetailDAO.getPgGridId(maGrdMngColDetailDTO.getPageId(), maGrdMngColDetailDTO.getGridObjId());
	        
	        if(!"".equals(pgGridId) && pgGridId != null) maGrdMngCommonDTO.setPgGridId(pgGridId);
	    }
        return maGrdMngColDetailDAO.insertDetail( maGrdMngColDetailDTO, maGrdMngCommonDTO);
    }
}
