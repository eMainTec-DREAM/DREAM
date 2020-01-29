package dream.consult.comp.plant.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.plant.dao.MaPlantMngListDAO;
import dream.consult.comp.plant.dto.MaPlantMngCommonDTO;
import dream.consult.comp.plant.service.MaPlantMngListService;

/**
 * 회사설정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaPlantMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="maPlantMngListServiceTarget"
 * @spring.txbn id="maPlantMngListService"
 * @spring.property name="maPlantMngListDAO" ref="maPlantMngListDAO"
 */
public class MaPlantMngListServiceImpl implements MaPlantMngListService
{
    private MaPlantMngListDAO maPlantMngListDAO = null;

    public MaPlantMngListDAO getMaPlantMngListDAO() {
		return maPlantMngListDAO;
	}

	public void setMaPlantMngListDAO(MaPlantMngListDAO maPlantMngListDAO) {
		this.maPlantMngListDAO = maPlantMngListDAO;
	}

	public List findPlantList(MaPlantMngCommonDTO maPlantMngCommonDTO, User user)
    {
        return maPlantMngListDAO.findPlantList(maPlantMngCommonDTO, user);
    }

	public int deletePlant(String[] deleteRows, String[] deleteRowsExt, User user) throws Exception{
        int result = 0;

        
        if(!deleteRows.equals(null)){
        	for(int i = 0; i< deleteRows.length; i++){
        		
        		result = result + maPlantMngListDAO.deletePlant(deleteRows[i], deleteRowsExt[i], user);
        	}
        }
        
        
        
        return result;
    }

	@Override
	public String findTotalCount(MaPlantMngCommonDTO maPlantMngCommonDTO, User user) {
		
		return maPlantMngListDAO.findTotalCount(maPlantMngCommonDTO, user);
	}
}

