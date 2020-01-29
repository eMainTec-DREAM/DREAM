package dream.consult.program.page.service.spring;

import common.bean.User;
import dream.consult.program.page.dao.MaPgMngFieldValueDetailDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueListDTO;
import dream.consult.program.page.service.MaPgMngFieldValueDetailService;

/**
 * 화면별 필드 기본값 상세
 * @author kim2107
 * @version $Id: MaPgMngFieldValueDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPgMngFieldValueDetailServiceTarget"
 * @spring.txbn id="maPgMngFieldValueDetailService"
 * @spring.property name="maPgMngFieldValueDetailDAO" ref="maPgMngFieldValueDetailDAO"
 */
public class MaPgMngFieldValueDetailServiceImpl implements MaPgMngFieldValueDetailService
{
    private MaPgMngFieldValueDetailDAO maPgMngFieldValueDetailDAO = null;

	public MaPgMngFieldValueDetailDAO getMaPgMngFieldValueDetailDAO() {
		return maPgMngFieldValueDetailDAO;
	}

	public void setMaPgMngFieldValueDetailDAO(MaPgMngFieldValueDetailDAO maPgMngFieldValueDetailDAO) {
		this.maPgMngFieldValueDetailDAO = maPgMngFieldValueDetailDAO;
	}

	@Override
	public MaPgMngFieldValueDetailDTO findDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO,
			MaPgMngFieldValueListDTO maPgMngFieldValueListDTO, User user) {
		return maPgMngFieldValueDetailDAO.findDetail(maPgMngCommonDTO, maPgMngFieldDetailDTO, maPgMngFieldValueListDTO, user);
	}

	@Override
	public int updateDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO,
			MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user) throws Exception {
		return maPgMngFieldValueDetailDAO.updateDetail(maPgMngCommonDTO, maPgMngFieldDetailDTO, maPgMngFieldValueDetailDTO, user);
	}

	@Override
	public int insertDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO,
			MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user) throws Exception {
		return maPgMngFieldValueDetailDAO.insertDetail(maPgMngCommonDTO, maPgMngFieldDetailDTO, maPgMngFieldValueDetailDTO, user);
	}

	@Override
	public String validCompNo(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO,
			MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user) throws Exception {
		return maPgMngFieldValueDetailDAO.validCompNo(maPgMngCommonDTO, maPgMngFieldDetailDTO, maPgMngFieldValueDetailDTO, user);
	}
    
    
}
