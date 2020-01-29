package dream.asset.list.service.spring;

import dream.asset.list.dao.MaEqMstrMoldModHistDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistDetailDTO;
import dream.asset.list.service.MaEqMstrMoldModHistDetailService;

/**
 * 구성자재
 * @author kim2107
 * @version $Id: MaEqMstrPartDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrMoldModHistDetailServiceTarget"
 * @spring.txbn id="maEqMstrMoldModHistDetailService"
 * @spring.property name="maEqMstrMoldModHistDetailDAO" ref="maEqMstrMoldModHistDetailDAO"
 */
public class MaEqMstrMoldModHistDetailServiceImpl implements MaEqMstrMoldModHistDetailService
{
    private MaEqMstrMoldModHistDetailDAO maEqMstrMoldModHistDetailDAO = null;
    
	public MaEqMstrMoldModHistDetailDAO getMaEqMstrMoldModHistDetailDAO() {
		return maEqMstrMoldModHistDetailDAO;
	}

	public void setMaEqMstrMoldModHistDetailDAO(
			MaEqMstrMoldModHistDetailDAO maEqMstrMoldModHistDetailDAO) {
		this.maEqMstrMoldModHistDetailDAO = maEqMstrMoldModHistDetailDAO;
	}

	public MaEqMstrMoldModHistDetailDTO findDetail(String equipId, String eqMoldModHistId, String compNo)throws Exception
    {
        return maEqMstrMoldModHistDetailDAO.findDetail(equipId, eqMoldModHistId, compNo);
    }
    
	public int updateDetail(MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO) throws Exception
    {        
        return maEqMstrMoldModHistDetailDAO.updateDetail(maEqMstrMoldModHistDetailDTO, maEqMstrCommonDTO);
    }
	
	public int insertDetail(MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO) throws Exception
    {        
        return maEqMstrMoldModHistDetailDAO.insertDetail( maEqMstrMoldModHistDetailDTO, maEqMstrCommonDTO);
    }
	
}
