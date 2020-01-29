package dream.consult.program.page.service.spring;

import java.util.List;

import dream.consult.program.page.dao.MaPgMngPageListDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageListDTO;
import dream.consult.program.page.service.MaPgMngPageListService;

/**
 * 화면별 탭페이지 목록
 * @author kim21017
 * @version $Id: MaPgMngPageListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPgMngPageListServiceTarget"
 * @spring.txbn id="maPgMngPageListService"
 * @spring.property name="maPgMngPageListDAO" ref="maPgMngPageListDAO"
 */
public class MaPgMngPageListServiceImpl implements MaPgMngPageListService
{
    private MaPgMngPageListDAO maPgMngPageListDAO = null;


	public List findPageList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngPageListDTO maPgMngPageListDTO)
    {      
        return maPgMngPageListDAO.findPageList(maPgMngCommonDTO, maPgMngPageListDTO);
    }

	public MaPgMngPageListDAO getMaPgMngPageListDAO() {
		return maPgMngPageListDAO;
	}

	public void setMaPgMngPageListDAO(MaPgMngPageListDAO maPgMngPageListDAO) {
		this.maPgMngPageListDAO = maPgMngPageListDAO;
	}
	
	public int deletePageList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maPgMngPageListDAO.deletePageList(id);
            }
        
        return result;
    }
}

