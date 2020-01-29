package dream.consult.program.page.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dao.MaGrdMngListDAO;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.service.MaGrdMngListService;

/**
 * ¸ñ·Ï serviceimpl
 * @author jung7126
 * @version $Id: MaGrdMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maGrdMngListServiceTarget"
 * @spring.txbn id="maGrdMngListService"
 * @spring.property name="maGrdMngListDAO" ref="maGrdMngListDAO"
 */
public class MaGrdMngListServiceImpl implements MaGrdMngListService
{
    private MaGrdMngListDAO maGrdMngListDAO = null;

    public MaGrdMngListDAO getMaGrdMngListDAO() {
		return maGrdMngListDAO;
	}

	public void setMaGrdMngListDAO(MaGrdMngListDAO maGrdMngListDAO) {
		this.maGrdMngListDAO = maGrdMngListDAO;
	}

	public List findList(MaPgMngCommonDTO maPgMngCommonDTO,MaGrdMngCommonDTO maGrdMngCommonDTO)
    {      
        return maGrdMngListDAO.findGrdList(maPgMngCommonDTO, maGrdMngCommonDTO);
    }
	
	public int deleteList(String[] deleteRows) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maGrdMngListDAO.deletePage(id);
            }
        return result;
    }

	@Override
	public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, MaGrdMngCommonDTO maGrdMngCommonDTO, User user)
			throws Exception {
        return maGrdMngListDAO.findTotalCount(maPgMngCommonDTO, maGrdMngCommonDTO, user);
	}
}

