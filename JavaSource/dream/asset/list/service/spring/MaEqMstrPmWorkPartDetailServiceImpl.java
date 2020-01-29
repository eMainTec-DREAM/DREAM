package dream.asset.list.service.spring;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrPmWorkPartDetailDAO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;
import dream.asset.list.service.MaEqMstrPmWorkPartDetailService;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPartDetailDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;
import dream.work.pm.list.service.MaPmMstrPartDetailService;

/**
 * 설비 예방작업 부품 상세
 * @author kim2107
 * @version $Id: MaEqMstrPmWorkPartDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrPmWorkPartDetailServiceTarget"
 * @spring.txbn id="maEqMstrPmWorkPartDetailService"
 * @spring.property name="maEqMstrPmWorkPartDetailDAO" ref="maEqMstrPmWorkPartDetailDAO"
 * @spring.property name="maPmMstrPartDetailService" ref="maPmMstrPartDetailService"
 */
public class MaEqMstrPmWorkPartDetailServiceImpl implements MaEqMstrPmWorkPartDetailService
{
    private MaEqMstrPmWorkPartDetailDAO maEqMstrPmWorkPartDetailDAO = null;
    private MaPmMstrPartDetailService maPmMstrPartDetailService = null;

    
	public MaPmMstrPartDetailService getMaPmMstrPartDetailService() {
		return maPmMstrPartDetailService;
	}

	public void setMaPmMstrPartDetailService(MaPmMstrPartDetailService maPmMstrPartDetailService) {
		this.maPmMstrPartDetailService = maPmMstrPartDetailService;
	}

	public MaEqMstrPmWorkPartDetailDAO getMaEqMstrPmWorkPartDetailDAO() {
		return maEqMstrPmWorkPartDetailDAO;
	}

	public void setMaEqMstrPmWorkPartDetailDAO(MaEqMstrPmWorkPartDetailDAO maEqMstrPmWorkPartDetailDAO) {
		this.maEqMstrPmWorkPartDetailDAO = maEqMstrPmWorkPartDetailDAO;
	}

	@Override
	public MaEqMstrPmWorkPartDetailDTO findDetail(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO,
			MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user) throws Exception {
		return maEqMstrPmWorkPartDetailDAO.findDetail(maEqMstrPmWorkDetailDTO, maEqMstrPmWorkPartListDTO, user);
	}

	@Override
	public int updateDetail(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO,
			MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO, User user) throws Exception {
		
		MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = setMaPmMstrPartDetailDTO(maEqMstrPmWorkDetailDTO, maEqMstrPmWorkPartDetailDTO, user);
		MaPmMstrCommonDTO maPmMstrCommonDTO = setMaPmMstrCommonDTO(maEqMstrPmWorkDetailDTO, maEqMstrPmWorkPartDetailDTO, user);

		return maPmMstrPartDetailService.updateDetail(maPmMstrPartDetailDTO, maPmMstrCommonDTO);
	}

	@Override
	public int insertDetail(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO, User user) throws Exception {
		
		MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = setMaPmMstrPartDetailDTO(maEqMstrPmWorkDetailDTO, maEqMstrPmWorkPartDetailDTO, user);
		MaPmMstrCommonDTO maPmMstrCommonDTO = setMaPmMstrCommonDTO(maEqMstrPmWorkDetailDTO, maEqMstrPmWorkPartDetailDTO, user);
		
		return maPmMstrPartDetailService.insertDetail(maPmMstrPartDetailDTO, maPmMstrCommonDTO);
	}
	
	private MaPmMstrPartDetailDTO setMaPmMstrPartDetailDTO(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO,
			MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO, User user){
		
		MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = new MaPmMstrPartDetailDTO();

		maPmMstrPartDetailDTO.setPartId(maEqMstrPmWorkPartDetailDTO.getPartId());
		maPmMstrPartDetailDTO.setUseQty(maEqMstrPmWorkPartDetailDTO.getUseQty());
		maPmMstrPartDetailDTO.setPmPartId(maEqMstrPmWorkPartDetailDTO.getPmPartId());
		
		return maPmMstrPartDetailDTO;
	}
	
	private MaPmMstrCommonDTO setMaPmMstrCommonDTO(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO,
			MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO, User user){
		
		MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
		maPmMstrCommonDTO.setCompNo(user.getCompNo());
		maPmMstrCommonDTO.setPmId(maEqMstrPmWorkDetailDTO.getPmId());
		maPmMstrCommonDTO.setPmEquipId(maEqMstrPmWorkDetailDTO.getPmEquipId());
		
		return maPmMstrCommonDTO;
	}

}
