package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrPmInsListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;
import dream.asset.list.service.MaEqMstrPmInsListService;

/**
 * ���� �������� ���
 * @author kim21017
 * @version $Id: MaEqMstrPmInsListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrPmInsListServiceTarget"
 * @spring.txbn id="maEqMstrPmInsListService"
 * @spring.property name="maEqMstrPmInsListDAO" ref="maEqMstrPmInsListDAO"
 */
public class MaEqMstrPmInsListServiceImpl implements MaEqMstrPmInsListService
{
    private MaEqMstrPmInsListDAO maEqMstrPmInsListDAO = null;

	public MaEqMstrPmInsListDAO getMaEqMstrPmInsListDAO() {
		return maEqMstrPmInsListDAO;
	}

	public void setMaEqMstrPmInsListDAO(MaEqMstrPmInsListDAO maEqMstrPmInsListDAO) {
		this.maEqMstrPmInsListDAO = maEqMstrPmInsListDAO;
	}

	@Override
	public List findEqPmInsList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO, User user)
			throws Exception {
		return maEqMstrPmInsListDAO.findEqPmInsList(maEqMstrCommonDTO, maEqMstrPmInsListDTO, user);
	}

	@Override
	public int deleteEqPmInsList(String[] deletePmIdRows, String[] deletePmEquipIdRows, User user) throws Exception {
		int result = 0;
		
        if(deletePmIdRows!=null && deletePmEquipIdRows!=null){
        	
        	//���μ����� sched �� work order, wo point ����� �� �߰�
        	
        	for (int i = 0; i < deletePmIdRows.length; i++) {
        		//delete pmequip
        		result += maEqMstrPmInsListDAO.deletePmEquip(deletePmIdRows[i], deletePmEquipIdRows[i], user);
        		// �ٸ� PMEQUIP�� �����ϴ� �� Ȯ��
        		int pmEquipCnt = maEqMstrPmInsListDAO.checkOtherPmEquip(deletePmIdRows[i], user);
        		if(pmEquipCnt==0){
            		//delete pmpoint
        			result += maEqMstrPmInsListDAO.deletePmPoint(deletePmIdRows[i], user);
            		//delete pmlist
        			result += maEqMstrPmInsListDAO.deletePmList(deletePmIdRows[i], user);
        		}
        	}
        }
        return result;
	}

	@Override
	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO, User user)
			throws Exception {
		return maEqMstrPmInsListDAO.findTotalCount(maEqMstrCommonDTO, maEqMstrPmInsListDTO, user);
	}
    
}