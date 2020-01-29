package dream.mgr.plant.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.plant.dao.MgrPlantMngDetailDAO;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;
import dream.mgr.plant.dto.MgrPlantMngDetailDTO;
import dream.mgr.plant.service.MgrPlantMngDetailService;
import dream.mgr.plant.service.MgrPlantMngListService;

/**
 * 공장설정 - 상세 serviceimpl
 * @author  euna0207
 * @version $Id: MgrPlantMngDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="mgrPlantMngDetailServiceTarget"
 * @spring.txbn id="mgrPlantMngDetailService"
 * @spring.property name="mgrPlantMngDetailDAO" ref="mgrPlantMngDetailDAO"
 */
public class MgrPlantMngDetailServiceImpl implements MgrPlantMngDetailService
{
    private MgrPlantMngDetailDAO mgrPlantMngDetailDAO = null;

    public MgrPlantMngDetailDAO getMgrPlantMngDetailDAO() {
		return mgrPlantMngDetailDAO;
	}

	public void setMgrPlantMngDetailDAO(MgrPlantMngDetailDAO mgrPlantMngDetailDAO) {
		this.mgrPlantMngDetailDAO = mgrPlantMngDetailDAO;
	}

	public MgrPlantMngDetailDTO findDetail(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user)throws Exception
    {
        return mgrPlantMngDetailDAO.findDetail(mgrPlantMngCommonDTO, user);
    }

	public int insertDetail(MgrPlantMngDetailDTO mgrPlantMngDetailDTO, User user) throws Exception
    {
        return mgrPlantMngDetailDAO.insertDetail(mgrPlantMngDetailDTO, user);
    }

	public int updateDetail(MgrPlantMngDetailDTO mgrPlantMngDetailDTO, User user) throws Exception
    {
        return mgrPlantMngDetailDAO.updateDetail(mgrPlantMngDetailDTO, user);
    }

	@Override
	public int valPlantDesc(MgrPlantMngDetailDTO mgrPlantMngDetailDTO, User user) throws Exception {
		
		MgrPlantMngCommonDTO mgrPlantMngCommonDTO = new MgrPlantMngCommonDTO();
		MgrPlantMngListService mgrPlantMngListService = (MgrPlantMngListService) CommonUtil.getBean("mgrPlantMngListService");

		mgrPlantMngCommonDTO.setPlantIdValid(mgrPlantMngDetailDTO.getPlantId());
		mgrPlantMngCommonDTO.setPlantNoValid(mgrPlantMngDetailDTO.getPlantNo());
		
		int plantCnt = Integer.parseInt(mgrPlantMngListService.findTotalCount(mgrPlantMngCommonDTO, user));

		return plantCnt;
		
	}
}
