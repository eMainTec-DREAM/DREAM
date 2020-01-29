package dream.asset.loc.list.service.spring;

import common.bean.User;
import dream.asset.loc.list.dao.MaEqLocDetailDAO;
import dream.asset.loc.list.dto.MaEqLocDetailDTO;
import dream.asset.loc.list.service.MaEqLocDetailService;

/**
 * 설비위치 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaEqLocDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqLocDetailServiceTarget"
 * @spring.txbn id="maEqLocDetailService"
 * @spring.property name="maEqLocDetailDAO" ref="maEqLocDetailDAO"
 */
public class MaEqLocDetailServiceImpl implements MaEqLocDetailService
{
    private MaEqLocDetailDAO maEqLocDetailDAO = null;
    
    public MaEqLocDetailDAO getMaEqLocDetailDAO() {
		return maEqLocDetailDAO;
	}

	public void setMaEqLocDetailDAO(MaEqLocDetailDAO maEqLocDetailDAO) {
		this.maEqLocDetailDAO = maEqLocDetailDAO;
	}

	public MaEqLocDetailDTO findDetail(String eqLocId,User user)throws Exception
    {
        return maEqLocDetailDAO.findDetail(eqLocId,user);
    }
	
	public int insertDetail(MaEqLocDetailDTO maEqLocDetailDTO) throws Exception
    {   
		maEqLocDetailDAO.insertDetail(maEqLocDetailDTO);
	    if("Y".equals(maEqLocDetailDTO.getIsBdLoc())){
	    	maEqLocDetailDAO.insertBuildingEquipment(maEqLocDetailDTO);
	    }
    	maEqLocDetailDAO.insertFullDescDetail(maEqLocDetailDTO);
    	maEqLocDetailDAO.insertLevelNDetail(maEqLocDetailDTO);
        return maEqLocDetailDAO.insertLevelYDetail(maEqLocDetailDTO);
    }
	
	
	public int updateDetail(MaEqLocDetailDTO maEqLocDetailDTO, User user) throws Exception
    {        
		maEqLocDetailDAO.updateDetail(maEqLocDetailDTO, user);
		if("Y".equals(maEqLocDetailDTO.getIsBdLoc())){
			maEqLocDetailDAO.updateBuildingEquipment(maEqLocDetailDTO, user);
	    }
		maEqLocDetailDAO.insertFullDescDetail(maEqLocDetailDTO);
		maEqLocDetailDAO.insertLevelNDetail(maEqLocDetailDTO);
		maEqLocDetailDAO.updateRunTime(maEqLocDetailDTO,user);
		return maEqLocDetailDAO.insertLevelYDetail(maEqLocDetailDTO); 
    }
	
	public String findEqLocId(String eqLocNo, User user) throws Exception {
		return maEqLocDetailDAO.findEqLocId(eqLocNo, user);
	}
}
