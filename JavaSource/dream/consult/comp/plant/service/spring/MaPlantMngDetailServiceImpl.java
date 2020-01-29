package dream.consult.comp.plant.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.consult.comp.plant.dao.MaPlantMngDetailDAO;
import dream.consult.comp.plant.dto.MaPlantMngCommonDTO;
import dream.consult.comp.plant.dto.MaPlantMngDetailDTO;
import dream.consult.comp.plant.service.MaPlantMngDetailService;
import dream.consult.comp.plant.service.MaPlantMngListService;

/**
 * 회사설정 - 상세 serviceimpl
 * @author  kim21017
 * @version $Id: MaPlantMngDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPlantMngDetailServiceTarget"
 * @spring.txbn id="maPlantMngDetailService"
 * @spring.property name="maPlantMngDetailDAO" ref="maPlantMngDetailDAO"
 */
public class MaPlantMngDetailServiceImpl implements MaPlantMngDetailService
{
    private MaPlantMngDetailDAO maPlantMngDetailDAO = null;

    public MaPlantMngDetailDAO getMaPlantMngDetailDAO() {
		return maPlantMngDetailDAO;
	}

	public void setMaPlantMngDetailDAO(MaPlantMngDetailDAO maPlantMngDetailDAO) {
		this.maPlantMngDetailDAO = maPlantMngDetailDAO;
	}

	public MaPlantMngDetailDTO findDetail(MaPlantMngCommonDTO maPlantMngCommonDTO, User user)throws Exception
    {
        return maPlantMngDetailDAO.findDetail(maPlantMngCommonDTO, user);
    }

	public int insertDetail(MaPlantMngDetailDTO maPlantMngDetailDTO, User user) throws Exception
    {
        return maPlantMngDetailDAO.insertDetail(maPlantMngDetailDTO, user);
    }

	public int updateDetail(MaPlantMngDetailDTO maPlantMngDetailDTO, User user) throws Exception
    {
        return maPlantMngDetailDAO.updateDetail(maPlantMngDetailDTO, user);
    }

	@Override
	public int valPlantNo(MaPlantMngDetailDTO maPlantMngDetailDTO, User user) throws Exception {
		
		MaPlantMngListService maPlantMngListService = (MaPlantMngListService) CommonUtil.getBean("maPlantMngListService");
		MaPlantMngCommonDTO maPlantMngCommonDTO = new MaPlantMngCommonDTO();

		maPlantMngCommonDTO.setPlantNo(maPlantMngDetailDTO.getPlantNo());
		
		int plantCnt = Integer.parseInt(maPlantMngListService.findTotalCount(maPlantMngCommonDTO, user));

		return plantCnt;
	}
}
