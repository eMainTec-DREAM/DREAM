package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrPmWorkPartListDAO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;
import dream.asset.list.service.MaEqMstrPmWorkPartListService;

/**
 * 설비 예방작업 부품 목록
 * @author kim21017
 * @version $Id: MaEqMstrPmWorkPartListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrPmWorkPartListServiceTarget"
 * @spring.txbn id="maEqMstrPmWorkPartListService"
 * @spring.property name="maEqMstrPmWorkPartListDAO" ref="maEqMstrPmWorkPartListDAO"
 */
public class MaEqMstrPmWorkPartListServiceImpl implements MaEqMstrPmWorkPartListService
{
    private MaEqMstrPmWorkPartListDAO maEqMstrPmWorkPartListDAO = null;

	public MaEqMstrPmWorkPartListDAO getMaEqMstrPmWorkPartListDAO() {
		return maEqMstrPmWorkPartListDAO;
	}

	public void setMaEqMstrPmWorkPartListDAO(MaEqMstrPmWorkPartListDAO maEqMstrPmWorkPartListDAO) {
		this.maEqMstrPmWorkPartListDAO = maEqMstrPmWorkPartListDAO;
	}

	@Override
	public List findEqPmWorkPartList(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user) throws Exception {
		return maEqMstrPmWorkPartListDAO.findEqPmWorkPartList(maEqMstrPmWorkDetailDTO, maEqMstrPmWorkPartListDTO, user);
	}

	@Override
	public int deleteEqPmWorkPartList(String[] deletePmIdRows, String[] deletePmParkIdRows, User user)
			throws Exception {
        int result = 0;
        if(deletePmIdRows != null && deletePmParkIdRows != null){
        	for (int i = 0; i < deletePmIdRows.length; i++) {
        		result = result + maEqMstrPmWorkPartListDAO.deleteEqPmWorkPartList(deletePmIdRows[i], deletePmParkIdRows[i], user);
			}
        }
        return result;
    }

	@Override
	public String findTotalCount(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user) throws Exception {
		return maEqMstrPmWorkPartListDAO.findTotalCount(maEqMstrPmWorkDetailDTO, maEqMstrPmWorkPartListDTO, user);
	}
    

}