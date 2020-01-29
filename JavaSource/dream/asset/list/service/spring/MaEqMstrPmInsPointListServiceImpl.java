package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrPmInsPointListDAO;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;
import dream.asset.list.service.MaEqMstrPmInsPointListService;

/**
 * 설비 예방점검 항목 목록
 * @author kim21017
 * @version $Id: MaEqMstrPmInsPointListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrPmInsPointListServiceTarget"
 * @spring.txbn id="maEqMstrPmInsPointListService"
 * @spring.property name="maEqMstrPmInsPointListDAO" ref="maEqMstrPmInsPointListDAO"
 */
public class MaEqMstrPmInsPointListServiceImpl implements MaEqMstrPmInsPointListService
{
    private MaEqMstrPmInsPointListDAO maEqMstrPmInsPointListDAO = null;

	public MaEqMstrPmInsPointListDAO getMaEqMstrPmInsPointListDAO() {
		return maEqMstrPmInsPointListDAO;
	}

	public void setMaEqMstrPmInsPointListDAO(MaEqMstrPmInsPointListDAO maEqMstrPmInsPointListDAO) {
		this.maEqMstrPmInsPointListDAO = maEqMstrPmInsPointListDAO;
	}

	@Override
	public List findEqPmInsPointList(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user) throws Exception {
		return maEqMstrPmInsPointListDAO.findEqPmInsPointList(maEqMstrPmInsDetailDTO, maEqMstrPmInsPointListDTO, user);
	}

	@Override
	public int deleteEqPmInsPointList(String[] deletePmIdRows, String[] deletePmPointIdRows, User user)
			throws Exception {
        int result = 0;
        if(deletePmIdRows != null && deletePmPointIdRows != null){
        	for (int i = 0; i < deletePmIdRows.length; i++) {
        		result = result + maEqMstrPmInsPointListDAO.deleteEqPmInsPointList(deletePmIdRows[i], deletePmPointIdRows[i], user);
			}
        }
        return result;
    }

	@Override
	public String findTotalCount(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user) throws Exception {
		return maEqMstrPmInsPointListDAO.findTotalCount(maEqMstrPmInsDetailDTO, maEqMstrPmInsPointListDTO, user);
	}
    

}