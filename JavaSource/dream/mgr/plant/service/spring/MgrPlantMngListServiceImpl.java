package dream.mgr.plant.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.plant.dao.MgrPlantMngListDAO;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;
import dream.mgr.plant.service.MgrPlantMngListService;

/**
 * 공장설정 - 목록 serviceimpl
 * @author euna0207
 * @version $Id: MgrPlantMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since 1.0
 *
 * @spring.bean id="mgrPlantMngListServiceTarget"
 * @spring.txbn id="mgrPlantMngListService"
 * @spring.property name="mgrPlantMngListDAO" ref="mgrPlantMngListDAO"
 */
public class MgrPlantMngListServiceImpl implements MgrPlantMngListService
{
    private MgrPlantMngListDAO mgrPlantMngListDAO = null;

    public MgrPlantMngListDAO getMgrPlantMngListDAO() {
		return mgrPlantMngListDAO;
	}

	public void setMgrPlantMngListDAO(MgrPlantMngListDAO mgrPlantMngListDAO) {
		this.mgrPlantMngListDAO = mgrPlantMngListDAO;
	}

	public List findPlantList(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user)
    {
        return mgrPlantMngListDAO.findPlantList(mgrPlantMngCommonDTO, user);
    }

	public int deletePlant(String[] deleteRows, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null)){
        	for(int i = 0; i< deleteRows.length; i++){
        		
        		result = result + mgrPlantMngListDAO.deletePlant(deleteRows[i], user);
        	}
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user) {
		return mgrPlantMngListDAO.findTotalCount(mgrPlantMngCommonDTO, user);
	}
}

