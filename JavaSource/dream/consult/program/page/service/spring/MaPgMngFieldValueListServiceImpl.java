package dream.consult.program.page.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dao.MaPgMngFieldValueListDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueListDTO;
import dream.consult.program.page.service.MaPgMngFieldValueListService;

/**
 * 화면별 필드 기본값 목록    
 * @author kim21017
 * @version $Id: MaPgMngFieldValueListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPgMngFieldValueListServiceTarget"
 * @spring.txbn id="maPgMngFieldValueListService"
 * @spring.property name="maPgMngFieldValueListDAO" ref="maPgMngFieldValueListDAO"
 */
public class MaPgMngFieldValueListServiceImpl implements MaPgMngFieldValueListService
{
    private MaPgMngFieldValueListDAO maPgMngFieldValueListDAO = null;

	public MaPgMngFieldValueListDAO getMaPgMngFieldValueListDAO() {
		return maPgMngFieldValueListDAO;
	}

	public void setMaPgMngFieldValueListDAO(MaPgMngFieldValueListDAO maPgMngFieldValueListDAO) {
		this.maPgMngFieldValueListDAO = maPgMngFieldValueListDAO;
	}

	@Override
	public List findList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO,
			MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueListDTO maPgMngFieldValueListDTO, User user) throws Exception {
		return maPgMngFieldValueListDAO.findList(maPgMngCommonDTO, maPgMngFieldListDTO,maPgMngFieldDetailDTO, maPgMngFieldValueListDTO, user);
	}

	@Override
	public int deleteList(String[] deleteRows, User user) throws Exception {
		int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maPgMngFieldValueListDAO.deleteList(id,user);
            }
        return result;
	}

	@Override
	public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO,
			MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueListDTO maPgMngFieldValueListDTO, User user) throws Exception {
		return maPgMngFieldValueListDAO.findTotalCount(maPgMngCommonDTO, maPgMngFieldListDTO,maPgMngFieldDetailDTO, maPgMngFieldValueListDTO, user);
	}
    
}