package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrPmWorkListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;
import dream.asset.list.service.MaEqMstrPmWorkListService;

/**
 * 설비 예방작업 목록
 * @author kim21017
 * @version $Id: MaEqMstrPmWorkListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrPmWorkListServiceTarget"
 * @spring.txbn id="maEqMstrPmWorkListService"
 * @spring.property name="maEqMstrPmWorkListDAO" ref="maEqMstrPmWorkListDAO"
 */
public class MaEqMstrPmWorkListServiceImpl implements MaEqMstrPmWorkListService
{
    private MaEqMstrPmWorkListDAO maEqMstrPmWorkListDAO = null;

	public MaEqMstrPmWorkListDAO getMaEqMstrPmWorkListDAO() {
		return maEqMstrPmWorkListDAO;
	}

	public void setMaEqMstrPmWorkListDAO(MaEqMstrPmWorkListDAO maEqMstrPmWorkListDAO) {
		this.maEqMstrPmWorkListDAO = maEqMstrPmWorkListDAO;
	}

	@Override
	public List findEqPmWorkList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO, User user)
			throws Exception {
		return maEqMstrPmWorkListDAO.findEqPmWorkList(maEqMstrCommonDTO, maEqMstrPmWorkListDTO, user);
	}

	@Override
	public int deleteEqPmWorkList(String[] deletePmIdRows, String[] deletePmEquipIdRows, User user) throws Exception {
		int result = 0;
		
        if(deletePmIdRows!=null && deletePmEquipIdRows!=null){
        	
        	//프로세스에 sched 및 work order, wo point 지우는 것 추가
        	
        	for (int i = 0; i < deletePmIdRows.length; i++) {
        		//delete pmequip
        		result += maEqMstrPmWorkListDAO.deletePmEquip(deletePmIdRows[i], deletePmEquipIdRows[i], user);
        		// 다른 PMEQUIP이 존재하는 지 확인
        		int pmEquipCnt = maEqMstrPmWorkListDAO.checkOtherPmEquip(deletePmIdRows[i], user);
        		if(pmEquipCnt==0){
            		//delete pmpart
        			result += maEqMstrPmWorkListDAO.deletePmPart(deletePmIdRows[i], user);
            		//delete pmlist
        			result += maEqMstrPmWorkListDAO.deletePmList(deletePmIdRows[i], user);
        		}
        	}
        }
        return result;
	}

	@Override
	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO, User user)
			throws Exception {
		return maEqMstrPmWorkListDAO.findTotalCount(maEqMstrCommonDTO, maEqMstrPmWorkListDTO, user);
	}
    
}