package dream.asset.list.service.spring;

import dream.asset.list.dao.MaEqMstrMoldProdDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldProdDetailDTO;
import dream.asset.list.service.MaEqMstrMoldProdDetailService;

/**
 * 구성자재
 * @author kim2107
 * @version $Id: MaEqMstrPartDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrMoldProdDetailServiceTarget"
 * @spring.txbn id="maEqMstrMoldProdDetailService"
 * @spring.property name="maEqMstrMoldProdDetailDAO" ref="maEqMstrMoldProdDetailDAO"
 */
public class MaEqMstrMoldProdDetailServiceImpl implements MaEqMstrMoldProdDetailService
{
    private MaEqMstrMoldProdDetailDAO maEqMstrMoldProdDetailDAO = null;
    
    

	public MaEqMstrMoldProdDetailDAO getMaEqMstrMoldProdDetailDAO() {
		return maEqMstrMoldProdDetailDAO;
	}

	public void setMaEqMstrMoldProdDetailDAO(
			MaEqMstrMoldProdDetailDAO maEqMstrMoldProdDetailDAO) {
		this.maEqMstrMoldProdDetailDAO = maEqMstrMoldProdDetailDAO;
	}

	public MaEqMstrMoldProdDetailDTO findDetail(String equipId, String eqMoldProductId, String compNo)throws Exception
    {
        return maEqMstrMoldProdDetailDAO.findDetail(equipId, eqMoldProductId, compNo);
    }
    
	public int updateDetail(MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO) throws Exception
    {        
        return maEqMstrMoldProdDetailDAO.updateDetail(maEqMstrMoldProdDetailDTO, maEqMstrCommonDTO);
    }
	
	public int insertDetail(MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO) throws Exception
    {        
        return maEqMstrMoldProdDetailDAO.insertDetail( maEqMstrMoldProdDetailDTO, maEqMstrCommonDTO);
    }
	
}
