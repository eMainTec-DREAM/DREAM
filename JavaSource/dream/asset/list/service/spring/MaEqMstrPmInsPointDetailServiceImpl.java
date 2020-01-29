package dream.asset.list.service.spring;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrPmInsPointDetailDAO;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;
import dream.asset.list.service.MaEqMstrPmInsPointDetailService;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;
import dream.work.pm.list.service.MaPmMstrPointDetailService;

/**
 * 설비 예방작업 항목 상세
 * @author kim2107
 * @version $Id: MaEqMstrPmInsPointDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrPmInsPointDetailServiceTarget"
 * @spring.txbn id="maEqMstrPmInsPointDetailService"
 * @spring.property name="maEqMstrPmInsPointDetailDAO" ref="maEqMstrPmInsPointDetailDAO"
 * @spring.property name="maPmMstrPointDetailService" ref="maPmMstrPointDetailService"
 */
public class MaEqMstrPmInsPointDetailServiceImpl implements MaEqMstrPmInsPointDetailService
{
    private MaEqMstrPmInsPointDetailDAO maEqMstrPmInsPointDetailDAO = null;
    private MaPmMstrPointDetailService maPmMstrPointDetailService = null;

	public MaPmMstrPointDetailService getMaPmMstrPointDetailService() {
		return maPmMstrPointDetailService;
	}

	public void setMaPmMstrPointDetailService(MaPmMstrPointDetailService maPmMstrPointDetailService) {
		this.maPmMstrPointDetailService = maPmMstrPointDetailService;
	}

	public MaEqMstrPmInsPointDetailDAO getMaEqMstrPmInsPointDetailDAO() {
		return maEqMstrPmInsPointDetailDAO;
	}

	public void setMaEqMstrPmInsPointDetailDAO(MaEqMstrPmInsPointDetailDAO maEqMstrPmInsPointDetailDAO) {
		this.maEqMstrPmInsPointDetailDAO = maEqMstrPmInsPointDetailDAO;
	}

	@Override
	public MaEqMstrPmInsPointDetailDTO findDetail(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO,
			MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user) throws Exception {
		return maEqMstrPmInsPointDetailDAO.findDetail(maEqMstrPmInsDetailDTO, maEqMstrPmInsPointListDTO, user);
	}

	@Override
	public int updateDetail(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO,
			MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO, User user) throws Exception {
		
		MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = setMaPmMstrPointDetailDTO(maEqMstrPmInsDetailDTO, maEqMstrPmInsPointDetailDTO, user);
		MaPmMstrCommonDTO maPmMstrCommonDTO = setMaPmMstrCommonDTO(maEqMstrPmInsDetailDTO, maEqMstrPmInsPointDetailDTO, user);
		
		return maPmMstrPointDetailService.updateDetail(maPmMstrPointDetailDTO, maPmMstrCommonDTO, user);
	}

	@Override
	public int insertDetail(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO, User user) throws Exception {
		
		MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = setMaPmMstrPointDetailDTO(maEqMstrPmInsDetailDTO, maEqMstrPmInsPointDetailDTO, user);
		MaPmMstrCommonDTO maPmMstrCommonDTO = setMaPmMstrCommonDTO(maEqMstrPmInsDetailDTO, maEqMstrPmInsPointDetailDTO, user);
		
		return maPmMstrPointDetailService.insertDetail(maPmMstrPointDetailDTO, maPmMstrCommonDTO, user);
	}
	
	private MaPmMstrPointDetailDTO setMaPmMstrPointDetailDTO(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO,
			MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO, User user){
		
		MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = new MaPmMstrPointDetailDTO();
		maPmMstrPointDetailDTO.setStepNum(maEqMstrPmInsPointDetailDTO.getStepNum());
		maPmMstrPointDetailDTO.setEqAsmbId(maEqMstrPmInsPointDetailDTO.getEqAsmbId());
		maPmMstrPointDetailDTO.setEqAsmbDesc(maEqMstrPmInsPointDetailDTO.getEqAsmbDesc());
		maPmMstrPointDetailDTO.setCheckPoint(maEqMstrPmInsPointDetailDTO.getCheckPoint());
		maPmMstrPointDetailDTO.setCheckMethod(maEqMstrPmInsPointDetailDTO.getCheckMethod());
		maPmMstrPointDetailDTO.setFitBasis(maEqMstrPmInsPointDetailDTO.getFitBasis());
		maPmMstrPointDetailDTO.setCheckType(maEqMstrPmInsPointDetailDTO.getCheckTypeId());
		maPmMstrPointDetailDTO.setCheckMin(maEqMstrPmInsPointDetailDTO.getCheckMin());
		maPmMstrPointDetailDTO.setCheckBasisVal(maEqMstrPmInsPointDetailDTO.getCheckBasisVal());
		maPmMstrPointDetailDTO.setCheckMax(maEqMstrPmInsPointDetailDTO.getCheckMax());
		maPmMstrPointDetailDTO.setUom(maEqMstrPmInsPointDetailDTO.getUom());
		maPmMstrPointDetailDTO.setIsActive(maEqMstrPmInsPointDetailDTO.getIsActiveId());
		maPmMstrPointDetailDTO.setRemark(maEqMstrPmInsPointDetailDTO.getRemark());
		maPmMstrPointDetailDTO.setPmPointId(maEqMstrPmInsPointDetailDTO.getPmPointId());
		
		return maPmMstrPointDetailDTO;
	}
	
	private MaPmMstrCommonDTO setMaPmMstrCommonDTO(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO,
			MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO, User user){
		
		MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
		maPmMstrCommonDTO.setCompNo(user.getCompNo());
		maPmMstrCommonDTO.setPmId(maEqMstrPmInsDetailDTO.getPmId());
		maPmMstrCommonDTO.setPmEquipId(maEqMstrPmInsDetailDTO.getPmEquipId());
		
		return maPmMstrCommonDTO;
	}

}
